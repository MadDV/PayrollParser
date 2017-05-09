package org.maddev.models;

import java.util.List;

/**
 * Created by maddev on 5/9/17.
 */
public class Payroll
{
    private final double total;
    private String date;
    private final List<Employee> employees;

    public Payroll(double total, List<Employee> employees)
    {
        this.total = total;
        this.employees = employees;
    }

    public double getTotal()
    {
        return total;
    }

    public List<Employee> getEmployees()
    {
        return employees;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
