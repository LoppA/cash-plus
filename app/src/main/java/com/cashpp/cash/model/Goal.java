package com.cashpp.cash.model;

public class Goal {
    private Integer _id;
    private String title;
    private double value;
    private String date;

    public Goal() {}

    public Goal(Integer id, String title, double value, String date) {
        this._id = id;
        this.title = title;
        this.value = value;
        this.date = date;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
