package models;

import com.google.gson.annotations.SerializedName;

public class Items {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("area")
    private String area;

}