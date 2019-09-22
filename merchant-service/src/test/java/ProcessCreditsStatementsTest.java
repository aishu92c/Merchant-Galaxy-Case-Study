import assignment.merchantgalaxy.model.ParseErrors;
import assignment.merchantgalaxy.model.ConverterException;
import com.assignment.merchantgalaxy.service.ProcessCreditsStatements;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.fail;

public class ProcessCreditsStatementsTest {

    private static final Logger
            logger = LoggerFactory.getLogger(ProcessCreditsStatementsTest.class);

    public ProcessCreditsStatements processCreditsStatements = ProcessCreditsStatements.getInstance();
    HashMap<String, ParseErrors> errors = new HashMap<String, ParseErrors>();
    HashMap<String, String> constantAssignments = new HashMap<String, String>();
    HashMap<String, String> computedLietrals = new HashMap<String, String>();

    @Test
    public void testValidCreditsLine() {
        try {
            constantAssignments.put("glob", "I");
            processCreditsStatements.parseLine("glob glob Silver is 34 Credits", constantAssignments, errors, computedLietrals);
            Assert.assertEquals(
                    "Parsing of line is successful inspite of invalid roman value",
                    Double.toString(17.0), computedLietrals.get("Silver"));
        } catch (ConverterException e) {
            logger.error(" Exception : ", e);
            Assert.fail("Parsing of line failed");
        }
    }

    @Test
    public void testInValidCreditsLine() {
        try {
            constantAssignments.put("glob", "k");
            processCreditsStatements.parseLine("glob glob Silver is 34 Credits", constantAssignments, errors, computedLietrals);
            fail();
        } catch (ConverterException e) {
            logger.error("Content Exception : ", e);
            Assert.assertEquals("Line not parsed successful", ParseErrors.INVALID_ROMAN_STRING, e.getParseErrors());

        }
    }

    @Test
    public void testEvaluateValidCreditsQuestion() {
        constantAssignments.put("glob", "I");
        constantAssignments.put("prok", "V");
        computedLietrals.put("Silver", Double.toString(17.0));

        try {
            String value = processCreditsStatements.evaluateLine("how many Credits is glob prok Silver ?", constantAssignments, errors, computedLietrals);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    "glob prok Silver is 68 Credits", value);
        } catch (ConverterException e) {
            logger.error(" Exception : ", e);
            Assert.fail("Evaluation of line failed");
        }

    }

    @Test
    public void testEvaluateInValidCreditsQuestion() {
        constantAssignments.put("glob", "I");
        constantAssignments.put("prok", "V");
        computedLietrals.put("Silver", Double.toString(17.0));

        try {
            String value = processCreditsStatements.evaluateLine("how many Credits is glob quack Silver ?", constantAssignments, errors, computedLietrals);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    ParseErrors.NO_IDEA.getDescription(), value);
        } catch (ConverterException e) {
            logger.error(" Exception : ", e);
            Assert.fail("Evaluation of line failed");
        }

    }


    @Test
    //Has only computed values as part of the Question
    public void testValidComputedCreditsQuestion() {
        computedLietrals.put("Silver", Double.toString(17.0));

        try {
            String value = processCreditsStatements.evaluateLine("how many Credits is  Silver Silver ?", constantAssignments, errors, computedLietrals);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    "Silver Silver is 289 Credits", value);
        } catch (ConverterException e) {
            logger.error(" Exception : ", e);
            Assert.fail("Evaluation of line failed");
        }

    }

    @Test
    public void testInValidComputedCreditsQuestion() {

        computedLietrals.put("Silver", Double.toString(17.0));

        try {
            String value = processCreditsStatements.evaluateLine("how many Credits is  quack Silver ?", constantAssignments, errors, computedLietrals);
            Assert.assertEquals(
                    "Evaluation of line is not successful inspite of valid question",
                    ParseErrors.NO_IDEA.getDescription(), errors.get("how many Credits is  quack Silver ?").getDescription());
        } catch (ConverterException e) {
            logger.error(" Exception : ", e);
            Assert.fail("Evaluation of line failed");
        }

    }


}
