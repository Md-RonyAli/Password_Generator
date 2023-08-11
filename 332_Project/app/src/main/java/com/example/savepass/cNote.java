package com.example.savepass;

public class cNote {

    private String title;
    private String note;

    public cNote(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public cNote() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
