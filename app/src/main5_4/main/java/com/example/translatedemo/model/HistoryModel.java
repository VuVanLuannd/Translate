package com.example.translatedemo.model;

public class HistoryModel {
    private String word;
    private String difinition;
    private int  id;
    private String cheked;

    public HistoryModel(String word, String difinition, int id, String cheked) {
        this.word = word;
        this.difinition = difinition;
        this.id = id;
        this.cheked = cheked;
    }

    public HistoryModel(String word, String difinition, int id) {
        this.word = word;
        this.difinition = difinition;
        this.id = id;

    }
    public HistoryModel(String word) {
        this.word = word;
    }

    public HistoryModel() {
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

    public String getCheked() {
        return cheked;
    }

    public void setCheked(String cheked) {
        this.cheked = cheked;
    }
}
