package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;

import java.util.HashMap;
import java.util.List;

public class RomanToNumberConverter {

    public static String romanNumberValidator = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    public static int getValue(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    public static String romanToArabic(String roman) throws ConverterException {
        String result = "";

        if (isValidRomanNumber(roman)) {
            result = Integer.toString(convertRomanToNumber(roman));
        } else {
            throw new ConverterException(ParseErrors.INVALID_ROMAN_STRING, roman + " is an invalid string", roman, ParseErrors.INVALID_ROMAN_STRING.getErrorCode());

        }

        return result;
    }

    /**
     * <p>This method validates a given roman number<br>
     * Return 1 when roman number is in correct format or 0 otherwise
     * </p>
     *
     * @param roman String
     * @return boolean
     */
    public static boolean isValidRomanNumber(String roman) {


        if (roman.matches(romanNumberValidator)) {
            return true;
        }

        return false;
    }

    public static int convertRomanToNumber(String str) {
        // Initialize result
        int res = 0;

        for (int i = 0; i < str.length(); i++) {
            // Getting value of symbol s[i]
            int s1 = getValue(str.charAt(i));

            // Getting value of symbol s[i+1]
            if (i + 1 < str.length()) {
                int s2 = getValue(str.charAt(i + 1));

                // Comparing both values
                if (s1 >= s2) {
                    // Value of current symbol is greater
                    // or equalto the next symbol
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++; // Value of current symbol is
                    // less than the next symbol
                }
            } else {
                res = res + s1;
                i++;
            }
        }

        return res;
    }

}
