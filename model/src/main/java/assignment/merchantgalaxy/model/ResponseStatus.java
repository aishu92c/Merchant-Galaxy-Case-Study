package assignment.merchantgalaxy.model;

public enum ResponseStatus {

    CONVERSION_SUCCESS("success"),
    CONVERSION_FAILED("failure");

    private String description;

    ResponseStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
