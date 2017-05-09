package org.maddev.models;

/**
 * Created by maddev on 5/9/17.
 */
public class EmployeeBuilder
{
    private int id;
    private double pay;
    private long checkNumber;

    public static EmployeeBuilder start()
    {
        return new EmployeeBuilder();
    }

    public Employee build()
    {
        return new Employee(id, pay, checkNumber);
    }

    public EmployeeBuilder withID(int id)
    {
        this.id = id;
        return this;
    }

    public EmployeeBuilder withPay(double pay)
    {
        this.pay = pay;
        return this;
    }

    public EmployeeBuilder withCheckNumber(long checkNumber)
    {
        this.checkNumber = checkNumber;
        return this;
    }
}
