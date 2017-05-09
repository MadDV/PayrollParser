package org.maddev.models;

/**
 * Created by maddev on 5/9/17.
 */
public class Transaction
{
    private String date;
    private double total;
    private String vendor;
    private long number;
    private String category;
    private String meta;

    public String getDate()
    {
        return date;
    }

    public double getTotal()
    {
        return total;
    }

    public String getVendor()
    {
        return vendor;
    }

    public long getNumber()
    {
        return number;
    }

    public String getCategory()
    {
        return category;
    }

    public String getMeta()
    {
        return meta;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public void setNumber(long number)
    {
        this.number = number;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setMeta(String meta)
    {
        this.meta = meta;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("^").append("\n");
        builder.append("D").append(this.parseDate()).append("\n");
        builder.append("T").append("-").append(total).append("\n");
        builder.append("N").append(number).append("\n");
        builder.append("P").append(vendor).append("\n");
        builder.append("L").append(category).append("\n");
        return builder.toString();
    }

    public String toMoneyFormat(boolean disableCarrot)
    {
        final StringBuilder builder = new StringBuilder();
        if(!disableCarrot)
        {
            builder.append("^").append("\n");
        }
        builder.append("D").append(this.parseDate()).append("\n");
        builder.append("T").append("-").append(total).append("\n");
        builder.append("N").append(number).append("\n");
        builder.append("P").append(vendor).append("\n");
        builder.append("L").append(category).append("\n");
        return builder.toString();
    }

    private String parseDate()
    {
        final String[] split = this.date.split("/");
        String month = split[0];
        String day = split[1];
        String year = split[2];
        day = day.replace("0", "");
        month = month.replace("0", "");
        return month + "/" + day + "'" + year;
    }
}
