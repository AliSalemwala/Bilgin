package com.example.bilgin.com.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan {

    private String takenIn;
    private String dueUntil;
    private float amount;

    public Loan(float amount) {
        // date stuff
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.takenIn = format.format(new Date());
        // due date is next month
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        this.dueUntil = format.format(cal.getTime());
    }

    public String getTakenIn() {
        return takenIn;
    }

    public String getDueUntil() {
        return dueUntil;
    }

    public float getAmount() {
        return amount;
    }

}