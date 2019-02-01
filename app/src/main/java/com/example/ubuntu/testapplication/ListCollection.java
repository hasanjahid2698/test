package com.example.ubuntu.testapplication;

public class ListCollection {

    int id;
    String date;
    String category;
    Double amount;

    public ListCollection(int id, String date, String category, Double amount) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public ListCollection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
