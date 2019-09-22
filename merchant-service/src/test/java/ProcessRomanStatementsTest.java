import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;
import com.assignment.merchantgalaxy.service.ProcessRomanStatements;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.fail;

public class ProcessRomanStatementsTest {

    private static final Logger
            logger = LoggerFactory.getLogger(ProcessRomanStatementsTest.class);
    HashMap<String, ParseErrors> errors = new HashMap<String, ParseErrors>();
    HashMap<String, String> constantAssignments = new HashMap<String, String>();
    HashMap<String, String> computedLietrals = new HashMap<String, String>();
    ProcessRomanStatements processRomanStatements = ProcessRomanStatements.getInstance();


    @Test
    public void testValidRomanCharacter() {
        try {
            processRomanStatements.parseLine("glob is IV", constantAssignments, errors, null);
            Assert.assertEquals(
                    "Parsing of line is not successful inspite of valid roman value",
                    "IV", constantAssignments.get("glob"));
        } catch (ConverterException e) {
            logger.error("Content Exception : ", e);
            Assert.fail("Parsing of line failed");
        }
    }

    @Test
    public void testInValidRomanCharacter() {
        try {
            processRomanStatements.parseLine("x is z", constantAssignments, errors, null);
            fail();

        } catch (ConverterException e) {
            Assert.assertEquals("Line not parsed successful", ParseErrors.INVALID_ROMAN_CHARACTER, e.getParseErrors());
        }

    }


    @Test
    public void testInValidRomanValue() {
        try {
            processRomanStatements.parseLine("x is IL", constantAssignments, errors, null);
            fail();

        } catch (ConverterException e) {
            Assert.assertEquals("Line not parsed successful", ParseErrors.INVALID_ROMAN_CHARACTER, e.getParseErrors());
        }

    }


    @Test
    public void testInValidRomanLineType() {
        try {
            processRomanStatements.parseLine("x is", constantAssignments, errors, null);
            fail();

        } catch (ConverterException e) {
            Assert.assertEquals("Line not parsed successful", ParseErrors.INCORRECT_LINE_TYPE, e.getParseErrors());
        }

    }

    @Test
    public void testEvaluateValidRomanLineQuestion() {
        try {
            constantAssignments.put("glob", "I");
            String value = processRomanStatements.evaluateLine("how much is  glob glob ?", constantAssignments, errors, null);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    "glob glob is 2", value);

        } catch (ConverterException e) {
            logger.error("Content Exception : ", e);
            Assert.fail("Evaluation   of line failed");
        }

    }

    @Test
    public void testEvaluateInValidRomanLineQuestion() {
        try {
            constantAssignments.put("glob", "I");
            String value = processRomanStatements.evaluateLine("how much is  glob glob xyz ?", constantAssignments, errors, null);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    ParseErrors.NO_IDEA.getDescription(), errors.get("how much is  glob glob xyz ?").getDescription());
        } catch (ConverterException e) {

            logger.error("Content Exception : ", e);
            Assert.fail("Evaluation of line failed");
        }

    }


}
