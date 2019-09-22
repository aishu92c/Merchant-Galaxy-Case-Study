package assignment.merchantgalaxy.model;

public class ConverterException extends Exception {

    private ParseErrors parseErrors;
    private String msg;
    private String line;
    private int code;

    public ConverterException(ParseErrors parseErrors, String msg, String line, int code) {
        this.parseErrors = parseErrors;
        this.msg = msg;
        this.line = line;
        this.code = code;
    }

    public ParseErrors getParseErrors() {
        return parseErrors;
    }

    @Override
    public String toString() {
        return "ConverterException{" +
                "parseErrors=" + parseErrors +
                ", msg='" + msg + '\'' +
                ", line='" + line + '\'' +
                ", code=" + code +
                '}';
    }

    public void setParseErrors(ParseErrors parseErrors) {
        this.parseErrors = parseErrors;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

