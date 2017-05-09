package org.maddev.util;

/**
 * Created by maddev on 5/9/17.
 */
public class DateUtil
{
    public static boolean isValid(String input)
    {
        final String[] split = input.split("/");
        if(split.length != 3) return false;
        for (String s : split)
        {
            try {
                Integer.parseInt(s);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
