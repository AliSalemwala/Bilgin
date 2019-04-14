package com.example.bilgin.com.models;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.math.BigInteger;

/**
 * Assumed simulations:
 * VirtualCard
 */
public class EmployeeBuilder {

    private ArrayList<Employee> employees;
    private String company;

    public EmployeeBuilder()
    {
        employees = new ArrayList<Employee>();
        String[] names = {"Usama", "Ali", "Mehmet", "Ahmet", "Muhammed"};
        company = "Evil Corp";

        for (int i = 0; i < 5; i++)
        {
            Employee em = new Employee(names[i], Integer.toString(i), company, (i+1)*1000);
            createCard(em);
            employees.add(em);
        }
    }

    public Employee getEmployee(String empName){
        for (Employee e: employees){
            if (e.getName().equals(empName)){
                return e;
            }
        }
        return null;
    }

    private void createCard(Employee em)
    {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(em.getName().getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);

            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }

            em.createCard(hashtext, em.getSalary() / 2);
        } catch (Exception e)  {
            e.printStackTrace();
        }

    }

}