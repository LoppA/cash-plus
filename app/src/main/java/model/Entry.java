package model;

public class Entry {
    private Integer _id;
    private String title;
    private double value;
    private String type;
    private String date;
    private Integer recurrence;
    private Integer category_id;

    public Entry() {}

    public Entry(Integer id, String title, double value, String type, String date,
                 Integer recurrence, Integer catogory_id) {
        this._id = id;
        this.title = title;
        this.value = value;
        this.type = type;
        this.date = date;
        this.recurrence = recurrence;
        this.category_id = catogory_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
