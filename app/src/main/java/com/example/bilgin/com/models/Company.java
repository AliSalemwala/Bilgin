package com.example.bilgin.com.models;

import java.util.ArrayList;

/**
 * Assumed simulations:
 * VirtualCard
 */
public class Company {

    // variables
    private String name;
    private ArrayList<Employee> employees;
    private float paymentsOwedToTheInstitution;

    // constructor(s)
    public Company(String name) {
        this.name = name;
        employees = new ArrayList<>();
        paymentsOwedToTheInstitution = 0;

        for (Employee employee : employees) {
            boolean hasCard = employee.getHasCard();

            if (!hasCard)
                paymentsOwedToTheInstitution += 0;
            else {
                VirtualCard card = employee.getCard();
                paymentsOwedToTheInstitution += card.getDebt();
            }
        }
    }

    public Company(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;

        for (Employee employee : employees) {
            boolean hasCard = employee.getHasCard();

            if (!hasCard)
                paymentsOwedToTheInstitution += 0;
            else {
                VirtualCard card = employee.getCard();
                paymentsOwedToTheInstitution += card.getDebt();
            }
        }

    }

    // methods
    public boolean addVirtualCard(String id, float limit) {
        Employee found = null;

        searchforloop:
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                found = employee;
                break searchforloop;
            }
        }

        if (found == null)
            return false;

        found.createCard(id, limit);
        return true;
    }
}