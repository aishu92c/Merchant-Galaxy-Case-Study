package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;

import java.util.HashMap;
import java.util.List;

public interface LineOperations {

    void parseLine(String line, HashMap<String, String> constantAssignments, HashMap<String, ParseErrors> errors, HashMap<String, String> computedLiterals) throws ConverterException;

    String evaluateLine(String line, HashMap<String, String> constantAssignments, HashMap<String, ParseErrors> errors, HashMap<String, String> computedLiterals) throws ConverterException;
}
