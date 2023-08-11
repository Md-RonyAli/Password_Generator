package com.example.savepass;

public class cAdd {
    private  String title;
    private  String name;
    private  String mobile;
    private  String emial;
    private  String add1;
    private  String add2;

    public cAdd(String title, String name, String mobile, String emial, String add1, String add2) {
        this.title = title;
        this.name = name;
        this.mobile = mobile;
        this.emial = emial;
        this.add1 = add1;
        this.add2 = add2;
    }

    public cAdd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }
}
