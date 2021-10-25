package org.project.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;

public class ChangeAllureJson {
    public static boolean changeAttachmentAllureJson(File fileJson, File video, String nameOfAttachment) {

        int i = 0;
        while (!fileJson.exists() || !video.exists()) {

            if (i < 300)
                try {
                    Thread.sleep(100);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            else
                return false;
        }

        Gson gson = new Gson();
        JsonObject jObject = null;

        try (Reader read = new BufferedReader(new FileReader(fileJson));) {

            JsonElement jElement = gson.fromJson(read, JsonElement.class);
            jObject = jElement.getAsJsonObject();
            JsonElement jArrayAttachments = jObject.get("attachments");

            JsonObject jObjectAttach = new JsonObject();

            jObjectAttach.addProperty("name", nameOfAttachment);
            jObjectAttach.addProperty("source", video.getAbsolutePath());
            jObjectAttach.addProperty("type", "video/mp4");

            jArrayAttachments.getAsJsonArray().add(jObjectAttach);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter(fileJson);) {

            gson.toJson(jObject, fileWriter);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
