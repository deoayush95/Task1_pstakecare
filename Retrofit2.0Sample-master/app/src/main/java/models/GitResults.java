package models;

import java.util.ArrayList;
import java.util.List;

public class GitResults {

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    private ArrayList<Items> items = new ArrayList<Items>();
}