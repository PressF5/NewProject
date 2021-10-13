package org.project.constants;

public enum UrlType {
    BASE_URL("/");

    private final String url = "http://automationpractice.com/index.php";
    private String endpoint;

    UrlType(String endpoint){
        this.endpoint = url + endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
