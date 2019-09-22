package com.assignment.merchantgalaxy.service;

import java.util.HashMap;
import java.util.List;

import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessRomanStatements implements LineOperations {

    private static final Logger
            logger = LoggerFactory.getLogger(ProcessRomanStatements.class);

    public static ProcessRomanStatements processRomanStatements;

    private ProcessRomanStatements() {
    }

    public static ProcessRomanStatements getInstance() {
        if (processRomanStatements == null) {
            processRomanStatements = new ProcessRomanStatements();
        }
        return processRomanStatements;
    }

    // Populates the romanValues map with key->glob value-> I if value is valid
    public void parseLine(String line, HashMap<String, String> romanValues, HashMap<String, ParseErrors> errors, HashMap<String, String> computedValues) throws ConverterException {
        String[] splited = line.trim().split("\\s+");

        try {
            if (RomanToNumberConverter.isValidRomanNumber(splited[2])) {
                romanValues.put(splited[0], splited[2]);
            } else {
                errors.put(line, ParseErrors.INVALID_ROMAN_CHARACTER);
                throw new ConverterException(ParseErrors.INVALID_ROMAN_CHARACTER, splited[2] + "is invalid", line, ParseErrors.INVALID_ROMAN_CHARACTER.getErrorCode());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            errors.put(line, ParseErrors.INCORRECT_LINE_TYPE);
            logger.error("Error while parsing the assignment line" + e);
            throw new ConverterException(ParseErrors.INCORRECT_LINE_TYPE, e.getMessage(), line, ParseErrors.INCORRECT_LINE_TYPE.getErrorCode());
        }

    }

    /**
     * <p>This method process the line for type how_much question<br>
     * It extracts all the constant identifiers from line and calculates the value from the romanValues hashMap<br>
     * It will generate an error
     *
     * @param line
     * @throws Exception
     */
    public String evaluateLine(String line, HashMap<String, String> romanValues, HashMap<String, ParseErrors> errors, HashMap<String, String> computedValues) throws ConverterException {

        {
            try {
                // will contain the 2nd half example glob glob ?
                String question = line.split("\\sis\\s")[1].trim();

                //Remove the question mark from the string
                question = question.replace("?", "").trim();

                String keys[] = question.split("\\s+");
                String romanResult = "";
                String resultString = null;
                Boolean isInvalid = false;

                for (String key : keys) {
                    String romanValue = romanValues.get(key);
                    if (romanValue == null) {
                        // key not present in the hash map
                        //errors.add(ParseErrors.NO_IDEA);
                        isInvalid = true;
                        break;
                    }
                    romanResult += romanValue;
                }

                if (!isInvalid) {
                    romanResult = RomanToNumberConverter.romanToArabic(romanResult);
                    resultString = question + " is " + romanResult;
                } else {
                    errors.put(line, ParseErrors.NO_IDEA);
                    resultString = ParseErrors.NO_IDEA.getDescription();
                }

                return resultString;

            } catch (ConverterException e) {
                errors.put(line, e.getParseErrors());
                throw e;
            } catch (Exception e) {
                logger.error(ParseErrors.INCORRECT_LINE_TYPE.getDescription() + e);
                throw new ConverterException(ParseErrors.INCORRECT_LINE_TYPE, e.getMessage(), line, ParseErrors.INCORRECT_LINE_TYPE.getErrorCode());


            }
        }
    }

}
