package org.project.dataproviders;

import org.project.pojo.User;
import org.project.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {
    @DataProvider(parallel = false)
    public Object[] getValidEmailAddressAndPassword() throws IOException {
        return JacksonUtils.deserializeJson("users" + File.separator + "EmailAddressAndPasswordValid.json", User[].class);
    }
    @DataProvider(parallel = false)
    public Object[] getInvalidEmailAddressAndPassword() throws IOException {
        return JacksonUtils.deserializeJson("users" + File.separator + "EmailAddressAndPasswordInvalid.json", User[].class);
    }
}
