package com.example.translatedemo.model;

public class SearchModel {
    private String word;
    private String difinition;
    private int  id;

    public SearchModel(String word, String difinition, int id) {
        this.word = word;
        this.difinition = difinition;
        this.id = id;
    }
    public SearchModel(String word) {
        this.word = word;
    }

    public SearchModel() {
    }

    public String getDifinition() {
        return difinition;
    }

    public void setDifinition(String difinition) {
        this.difinition = difinition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


}
