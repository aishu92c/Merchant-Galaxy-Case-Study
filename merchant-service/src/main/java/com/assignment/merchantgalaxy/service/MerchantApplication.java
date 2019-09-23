package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.ConverterException;
import assignment.merchantgalaxy.model.MerchantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MerchantApplication {

    private static final Logger
            logger = LoggerFactory.getLogger(MerchantApplication.class);

    public static void main(String[] args) {


        InputReader inputReader = new InputReader();

        MerchantResponse merchantResponse = null;
        //reads input from the console
        merchantResponse = inputReader.readInput();
        merchantResponse.getOutput().forEach(val -> System.out.println(val));

        System.out.println(merchantResponse.toString());


    }
}
