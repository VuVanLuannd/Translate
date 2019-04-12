package com.example.translatedemo.model;

public class InForMationModel {
    private String name;
    private String Link;

    public InForMationModel(String name, String key) {
        this.name = name;
        Link = key;
    }

    public InForMationModel(){}

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
