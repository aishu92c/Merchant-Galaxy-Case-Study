package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ProcessCreditsStatements implements LineOperations {

    private static final Logger
            logger = LoggerFactory.getLogger(ProcessCreditsStatements.class);

    public static ProcessCreditsStatements processCreditsStatements;


    private ProcessCreditsStatements() {
    }

    public static ProcessCreditsStatements getInstance() {
        if(processCreditsStatements == null) {
            processCreditsStatements = new ProcessCreditsStatements();
        }
        return processCreditsStatements;
    }

    //This will parse the credits Statement
    public  void parseLine(String line, HashMap<String, String> romanValues, HashMap<String,ParseErrors>errors, HashMap<String, String> computedValues) throws ConverterException {
        try {
            String formatted = line.replaceAll("(is\\s+)|([c|C]redits\\s*)", "").trim();
            String[] keys = formatted.split("\\s");
            String keyToBeComputed = keys[keys.length - 2];
            float value = Float.parseFloat(keys[keys.length - 1]);
            String roman = "";

            int romanNumber;
            for(romanNumber = 0; romanNumber < keys.length - 2; ++romanNumber) {
                if(romanValues.containsKey(keys[romanNumber])) {
                    roman = roman + (String) romanValues.get(keys[romanNumber]);
                } else {
                    errors.put(line,ParseErrors.NO_IDEA);

                }
            }
            if(StringUtils.isNotEmpty(roman)) {
                romanNumber = Integer.parseInt(RomanToNumberConverter.romanToArabic(roman));
                float credit = value / (float) romanNumber;
                computedValues.put(keyToBeComputed, Float.toString(credit));
            }
        } catch (ConverterException e) {
            errors.put(line,ParseErrors.INVALID_ROMAN_STRING);
            throw e;
        }
        catch (Exception e) {
            logger.error("Error while parsing the credits line" + e);
            throw new ConverterException(ParseErrors.INCORRECT_LINE_TYPE,e.getMessage(),line,ParseErrors.INCORRECT_LINE_TYPE.getErrorCode());
        }

    }

    //This method will evaluate the Credits question
    public String evaluateLine(String line, HashMap<String, String> romanValues, HashMap<String,ParseErrors> errors, HashMap<String, String> computedValues) throws ConverterException {

        try
        {
            //Remove the unwanted words like "is" and "?"
            String formatted = line.split("(\\sis\\s)")[1];

            formatted = formatted.replace("?","").trim();

            // search for all numerals for their values to compute the result
            String[] keys = formatted.split("\\s");
            String romanResult = "";
            String outputResult = null;
            float computedResult = 1;

            for(String key : keys)
            {
                if(romanValues.containsKey(key)) {
                    String romanValue = romanValues.get(key);
                    if(romanValue!=null)
                    {
                        romanResult += romanValue;
                    }

                } else if(computedValues.containsKey(key)) {
                    String computedValue = computedValues.get(key);
                    computedResult *= Float.parseFloat(computedValue);
                } else{
                    errors.put(line,ParseErrors.NO_IDEA);
                    return ParseErrors.NO_IDEA.getDescription();

                    }

            }
            if(errors.size() == 0) {
                int finalres = (int) computedResult;
                if (romanResult.length() > 0) {
                    finalres = (int) (Integer.parseInt(RomanToNumberConverter.romanToArabic(romanResult)) * computedResult);
                }
                outputResult = formatted + " is " + finalres + " Credits";
            }
            return outputResult;

        }
        catch(Exception e)
        {
            logger.error(ParseErrors.INCORRECT_LINE_TYPE.getDescription() + e);
            throw new ConverterException(ParseErrors.INCORRECT_LINE_TYPE,e.getMessage(),line,ParseErrors.INCORRECT_LINE_TYPE.getErrorCode());

        }

    }

}
