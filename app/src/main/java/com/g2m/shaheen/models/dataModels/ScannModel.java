package com.g2m.shaheen.models.dataModels;

import java.util.Date;

public class ScannModel {

     public String barcode;
    public Date date;
    public ScannModel(String barcode, Date date) {
        this.barcode = barcode;
        this.date = date;
    }
}
