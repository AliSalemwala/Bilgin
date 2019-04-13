package com.example.bilgin.com.models;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Assumed simulations:
 * Company,
 * Utilities,
 * Visual Credit Card,
 *
 */

public class Employee {
    private String name;
    private String id;
    private String company;
    private float salary;
    private float debt;
    private VirtualCard card;
    private boolean hasCard;

    public Employee(String name, String id, String company, float salary) {
        this.name = name;
        this.id = id;
        this.company = company;
        this.salary = salary;

        this.debt = 0;
        this.card = null;
        this.hasCard = false;
    }

    //Make Bank Details Class.
    //Make LoanHistory Class.

    /**
     * Check if the user is eligible for the requested amount of a loan
     * @param amountRequested
     * @return true if eligible, else false
     */
    private boolean isEligibleForLoan(float amountRequested) {
        boolean isEligible = false;

        if (salary - (debt + amountRequested) >= 0) {
            isEligible = true;
        }

        return isEligible;
    }

    /**
     * Calculate and return the weekly earnings
     * @return
     */
    public float calculateDaily() {
        String month = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()).substring(4, 6);
        int monthNum = Integer.parseInt(month);
        int days;

        if (monthNum == 2)
            days =  29;
        else if (monthNum == 7 || monthNum % 2 == 0)
            days = 31;
        else
            days = 30;


        return (salary - debt) / days;
    }

    /**
     * Checks if the user is eligible for a monthly loan
     * @param amountRequested requested amount of the loan
     * @return true if the requested loan is transferrable, false if not
     */
    public boolean requestLoan(int amountRequested) {
        boolean isEligible = this.isEligibleForLoan(amountRequested);

        if (isEligible) {
            debt += amountRequested;
        }

        return isEligible;
    }

    public boolean payUtilities(float amountToPay) {
        boolean isEligible = this.isEligibleForLoan(amountToPay);

        if(isEligible)
            debt += amountToPay;

        return isEligible;
    }

    public boolean createCard(String id, float limit) {
        if (hasCard)
            return false;

        this.card = new VirtualCard(id, limit);
        return true;
    }

    public boolean makePayment(float amount)  {
        if (card == null)
            return false;

        return card.makePayment(amount);
    }


    public boolean getHasCard() {
        return hasCard;
    }

    public VirtualCard getCard() {
        return card;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}