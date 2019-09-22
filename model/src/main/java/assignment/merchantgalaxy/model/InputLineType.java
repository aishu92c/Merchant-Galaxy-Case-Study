package assignment.merchantgalaxy.model;

public enum InputLineType {

    /**
     * This represents that line is of Assignment type. Ex: glob is V
     */
    ROMAN_TYPE_STATEMENT,

    /**
     * This represents that line is question asking how much. Ex : how much is pish tegj glob glob ?
     */
    ROMAN_TYPE_QUESTION,

    /**
     * This represents that line is of Credits type. Ex : glob glob Silver is 34 Credits
     */
    CREDITS_TYPE_STATEMENT,

    /**
     * This represents that line is question asking how many. Ex: how many Credits is glob prok Iron ?
     */
    CREDITS_TYPE_QUESTION,

    /**
     * This represents that line does not matched any of the line type mentioned above
     */
    NOMATCH;

    public static String patternRomanTypeStatement = "^([A-Za-z]+) is ([I|V|X|L|C|D|M]+)$";
    public static String patternCreditsTypeStatement = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
    public static String patternRomanTypeQuestion = "^how much is (([A-Za-z\\s])+)\\?$";
    public static String patternCreditsTypeQuestion = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";

    public static InputLineType getLineType(String line) {
        line = line.trim();

        if (line.matches(patternRomanTypeStatement)) {
            return InputLineType.ROMAN_TYPE_STATEMENT;
        } else if (line.matches(patternCreditsTypeStatement)) {
            return InputLineType.CREDITS_TYPE_STATEMENT;
        } else if (line.matches(patternRomanTypeQuestion)) {
            return InputLineType.ROMAN_TYPE_QUESTION;
        } else if (line.matches(patternCreditsTypeQuestion)) {
            return InputLineType.CREDITS_TYPE_QUESTION;
        }

        return InputLineType.NOMATCH;


    }

}
