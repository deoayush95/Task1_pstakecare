package com.iayon.retrofit20tutorial;

import java.util.Comparator;

import models.Items;

/**
 * Created by ayush on 12/1/16.
 */
public class CustomComparator implements Comparator<Items> {
    @Override
    public int compare(Items o1, Items o2) {
        float i1 = Float.parseFloat(o1.getArea());
        float i2 = Float.parseFloat(o2.getArea());
        if (i1 > i2) {
            return 1;
        } else if (i1 < i2){
            return -1;
        } else {
            return 0;
        }
    }
}
