package org.maddev.models;

/**
 * Created by maddev on 5/9/17.
 */
public class Employee
{
    private final int id;
    private final double pay;
    private final long checkNumber;

    public Employee(int id, double pay, long checkNumber)
    {
        this.id = id;
        this.pay = pay;
        this.checkNumber = checkNumber;
    }

    public int getId()
    {
        return id;
    }

    public double getPay()
    {
        return pay;
    }

    public long getCheckNumber()
    {
        return checkNumber;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", pay=" + pay +
                ", checkNumber=" + checkNumber +
                '}';
    }
}
