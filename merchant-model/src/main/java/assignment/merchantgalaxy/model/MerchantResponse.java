package assignment.merchantgalaxy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MerchantResponse {

    private ArrayList<String> output;
    private String status;
    private HashMap<String, ParseErrors> errors;

    public MerchantResponse(ArrayList<String> output, String status, HashMap<String, ParseErrors> errors) {
        this.output = output;
        this.status = status;
        this.errors = errors;
    }

    public MerchantResponse(ArrayList<String> output, HashMap<String, ParseErrors> errors) {
        this.output = output;
        this.errors = errors;
    }

    public MerchantResponse() {

    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, ParseErrors> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, ParseErrors> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "MerchantResponse{" +
                "output=" + output +
                ", status='" + status + '\'' +
                ", errors=" + errors +
                '}';
    }
}
