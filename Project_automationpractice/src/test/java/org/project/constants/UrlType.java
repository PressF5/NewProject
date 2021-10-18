package org.project.constants;

public enum UrlType {
    BASE_URL("/"),
    AUTHENTICATION_URL("?controller=authentication&back=my-account"),
    ACCOUNT_URL("?controller=my-account");

    private final String url = "http://automationpractice.com/index.php";
    private String endpoint;

    UrlType(String endpoint){
        this.endpoint = url + endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
