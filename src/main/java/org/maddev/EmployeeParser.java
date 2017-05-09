package org.maddev;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.maddev.models.Employee;
import org.maddev.models.EmployeeBuilder;
import org.maddev.models.Payroll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maddev on 5/9/17.
 */
public class EmployeeParser
{

    private final String path;

    public EmployeeParser(String path)
    {
        this.path = path;
    }

    private String getText()
    {
        final File file = new File(this.path);
        PDDocument doc = null;
        try
        {
            doc = PDDocument.load(file);
            final String text = new PDFTextStripper().getText(doc);
            return text;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                doc.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<String> parseToLines()
    {
        final String text = this.getText();
        if(text == null)
        {
            return Collections.emptyList();
        }
        final String[] lines = text.split("\\r?\\n");
        return Arrays.stream(lines)
                .map(String::trim)
                .filter(s -> !s.startsWith("$"))
                .filter(s -> Character.isDigit(s.charAt(3)))
                .map(s -> s.replaceAll(" +", " "))
                .collect(Collectors.toList());
    }

    public List<Employee> parseToEmployees()
    {
        return this.parseToLines()
                .stream().map(this::parseToEmployee)
                .collect(Collectors.toList());
    }

    private Employee parseToEmployee(String line)
    {
        final EmployeeBuilder builder = EmployeeBuilder.start();
        final List<String> parsed = this.parseToListNoName(line);
        for(int i = 0; i < parsed.size(); i++)
        {
            final String ele = parsed.get(i);
            if (i == 0)
            {
                builder.withID(Integer.parseInt(ele.trim()));
            }
            else if (i == 5)
            {
               builder.withPay(Double.parseDouble(ele.trim().replace("$", "").replace(",", "")));
            }
            else if (i == 6)
            {
                builder.withCheckNumber(Integer.parseInt(ele.trim()));
            }
        }
        return builder.build();
    }


    private List<String> parseToListNoName(String line)
    {
        final List<String> filtered = new ArrayList<>();
        final String[] split = line.split(" ");
        for (String s : split)
        {
            boolean isName = true;
            for (char c : s.toCharArray())
            {
                if(Character.isDigit(c)) {
                    isName = false;
                }
            }
            if(isName)
            {
                continue;
            }
            filtered.add(s);
        }
        return filtered;
    }

    public Payroll parseToPayroll()
    {
        final List<Employee> employees = this.parseToEmployees();
        double total = 0;
        for (Employee employee : employees)
        {
            total += employee.getPay();
        }
        return new Payroll(total, employees);
    }

}
