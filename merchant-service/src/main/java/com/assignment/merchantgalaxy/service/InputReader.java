package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.*;

import java.util.*;

public class InputReader {

    private Scanner scan;
    /**
     * This is the hash map that will contain the value for each identifier
     */
    private HashMap<String, String> romanValues;

    /**
     * This is the hash map for storing the value of the calculated literal
     */
    private HashMap<String, String> computedValues;

    private HashMap<String, ParseErrors> errors;

    private ArrayList<String> output;

    private MerchantResponse merchantResponse;

    private ProcessRomanStatements processRomanStatements = ProcessRomanStatements.getInstance();
    private ProcessCreditsStatements processCreditsStatements = ProcessCreditsStatements.getInstance();

    public InputReader() {
        this.scan = new Scanner(System.in);
        this.romanValues = new HashMap<String, String>();
        this.computedValues = new HashMap<String, String>();
        this.output = new ArrayList<String>();
        this.errors = new HashMap<String, ParseErrors>();
        this.merchantResponse = new MerchantResponse();
    }

    public MerchantResponse readInput() {
        String line;

        while (this.scan.hasNextLine() && (line = this.scan.nextLine()).length() > 0) {
            try {
                validate(line);
            } catch (ConverterException e) {
                merchantResponse.setStatus(ResponseStatus.CONVERSION_FAILED.getDescription());
                merchantResponse.setErrors(errors);
                output.add(e.getMsg());
                merchantResponse.setOutput(output);
                return merchantResponse;

            }
            if (errors.containsValue(ParseErrors.NO_IDEA)) {
                this.output.add(ParseErrors.NO_IDEA.getDescription());
                errors.remove(ParseErrors.NO_IDEA);
            }


        }

        if (errors.size() == 0 && output.isEmpty()) {
            errors.put(ParseErrors.NO_INPUT.getDescription(), ParseErrors.NO_INPUT);
        }
        if (errors.size() != 0) {
            merchantResponse.setStatus(ResponseStatus.CONVERSION_FAILED.getDescription());
        } else {
            merchantResponse.setStatus(ResponseStatus.CONVERSION_SUCCESS.getDescription());
        }
        merchantResponse.setErrors(errors);
        merchantResponse.setOutput(output);
        return merchantResponse;

    }

    private void validate(String line) throws ConverterException {

        ParseErrors error = ParseErrors.SUCCESS_OK;

        InputLineType lineType = InputLineType.getLineType(line);

        switch (lineType) {
            case ROMAN_TYPE_STATEMENT:
                processRomanStatements.parseLine(line, romanValues, errors, computedValues);
                break;

            case ROMAN_TYPE_QUESTION:
                output.add(processRomanStatements.evaluateLine(line, romanValues, errors, computedValues));
                break;


            case CREDITS_TYPE_STATEMENT:
                processCreditsStatements.parseLine(line, romanValues, errors, computedValues);
                break;

            case CREDITS_TYPE_QUESTION:
                output.add(processCreditsStatements.evaluateLine(line, romanValues, errors, computedValues));

                break;

            default:
                errors.put(line, ParseErrors.NO_IDEA);
                break;
        }

    }


}
