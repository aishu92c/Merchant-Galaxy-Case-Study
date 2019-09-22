package assignment.merchantgalaxy.model;

public enum ParseErrors {

    /**
     * This code specifies that there is no error
     */
    SUCCESS_OK(101, "No error"),

    /**
     * This code specifies that there is input error. Input provided is empty
     */
    NO_INPUT(102, "Input provided is empty"),


    /**
     * This code specifies that it does not match with any conversation line type specified in paragraph conversationType enum
     */
    INVALID_VALUE_EXCEPTION(103, "Invalid value"),

    /**
     * This error code specifies that roman number have some illegal characters
     */
    INVALID_ROMAN_CHARACTER(105, "Invalid Roman charater"),

    /**
     * This error code specifies that roman literal is in invalid format.
     */
    INVALID_ROMAN_STRING(106, "Roman string is of invalid format"),

    /**
     * This code specifies that a line has been identified as different type instead of its actual type
     */
    INCORRECT_LINE_TYPE(107, "incorrect line type supplied"),


    NO_IDEA(108, " I have no idea what you are talking about");

    private final int errorCode;
    private final String description;

    ParseErrors(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
