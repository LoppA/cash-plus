package com.cashpp.cash.model;

public class Category {
    private Integer _id;
    private String title;

    public Category() {}

    public Category(Integer id, String title) {
        this._id = id;
        this.title = title;
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
}
