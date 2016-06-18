package model;

public class Reminder {
    private Integer _id;
    private String title;
    private double value;
    private String date;
    private Integer recurrence;

    public Reminder() {}

    public Reminder(Integer id, String title, double value, String date, Integer recurrence) {
        this._id = id;
        this.title = title;
        this.value = value;
        this.date = date;
        this.recurrence = recurrence;
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

    public Integer getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Integer recurrence) {
        this.recurrence = recurrence;
    }
}
