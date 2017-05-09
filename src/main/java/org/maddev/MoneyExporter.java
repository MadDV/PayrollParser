package org.maddev;

import org.maddev.models.Employee;
import org.maddev.models.Payroll;
import org.maddev.models.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maddev on 5/9/17.
 */
public class MoneyExporter
{
    private final Payroll payroll;

    public MoneyExporter(Payroll payroll)
    {
        this.payroll = payroll;
    }

    public final List<Transaction> parseToTransactions()
    {
        final List<Transaction> transactions = new ArrayList<>();
        final List<Employee> employees = this.payroll.getEmployees();
        for (Employee employee : employees)
        {
            transactions.add(this.parseToTransaction(employee));
        }
        return transactions;
    }

    private Transaction parseToTransaction(Employee employee) {
        final Transaction transaction = new Transaction();
        transaction.setDate(payroll.getDate());
        transaction.setTotal(employee.getPay());
        transaction.setNumber(employee.getCheckNumber());
        transaction.setCategory("Cost of Goods:Labor");
        transaction.setVendor("Payroll");
        return transaction;
    }

    public File exportFile()
    {
        final List<Transaction> transactions = this.parseToTransactions();
        final String desktop = System.getProperty("user.home") + "/Desktop";
        final File file = new File(desktop + "/ExportedPayroll.qif");
        if(file.exists()) {
           file.delete();
        }
        try
        {
            file.createNewFile();
            Files.write(file.toPath(), "!Type:Bank\n".getBytes(), StandardOpenOption.APPEND);
            for(int i = 0; i < transactions.size(); i++)
            {
                final Transaction trans = transactions.get(i);
                /**
                 * Skip the carrot if its the first transaction.
                 */
                Files.write(file.toPath(), trans.toMoneyFormat(i == 0).getBytes(), StandardOpenOption.APPEND);
            }
            for (Transaction transaction : transactions)
            {
                transaction.toMoneyFormat(false);
                Files.write(file.toPath(), transaction.toMoneyFormat(false).getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(file.toPath(), "^".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return file;
    }
}
