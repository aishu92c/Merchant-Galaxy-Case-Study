package com.assignment.merchantgalaxy.service;

import assignment.merchantgalaxy.model.ConverterException;
import assignment.merchantgalaxy.model.MerchantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MerchantApplication {

    private static final Logger
            logger = LoggerFactory.getLogger(MerchantApplication.class);

    public static void main(String[] args) {

        //com.galaxy.merchant.Utility.println("Welcome to GalaxyMerchant ! please provide input below and a blank new line to finish input");

        // Initialize a new paragraph
        InputReader inputReader = new InputReader();

        // Read the input from console, validate and process
        MerchantResponse merchantResponse= null;

            merchantResponse = inputReader.readInput();
            for(int i=0;i<merchantResponse.getOutput().size();i++)
            {
                System.out.println(merchantResponse.getOutput().get(i));
            }
            System.out.println(merchantResponse.toString());






    }
}
