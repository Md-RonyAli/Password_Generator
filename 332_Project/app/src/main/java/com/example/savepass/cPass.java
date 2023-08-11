package com.example.savepass;

public class cPass {
    private String title;
    private String url;
    private String username;
    private String pass;

    public cPass(String title, String url, String username, String pass) {
        this.title = title;
        this.url = url;
        this.username = username;
        this.pass = pass;
    }

    public cPass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
