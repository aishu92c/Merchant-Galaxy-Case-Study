import assignment.merchantgalaxy.model.ConverterException;
import assignment.merchantgalaxy.model.ParseErrors;
import com.assignment.merchantgalaxy.service.RomanToNumberConverter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

public class RomanToNumberConverterTest {
    private static final Logger
            logger = LoggerFactory.getLogger(RomanToNumberConverterTest.class);


    @Test
    public void testValidRomanToArabicConversion() {
        try {
            String val = RomanToNumberConverter.romanToArabic("MMVI");
            Assert.assertEquals("Conversion of value from roman to arabic is not successful", "2006", val);

        } catch (Exception e) {
            logger.error("Conversion  failed: ", e);
            Assert.fail("Conversion  failed");
        }

    }

    @Test
    public void testInValidRomanToArabicConversion() {
        try {
            String val = RomanToNumberConverter.romanToArabic("abcd");
            fail();

        } catch (ConverterException e) {
            logger.error("Conversion  failed: ", e);
            Assert.assertEquals("Conversion not  successful", ParseErrors.INVALID_ROMAN_STRING, e.getParseErrors());


        }

    }

    @Test
    public void testInValidRepeatedRomanCharacters() {
        try {

            String val = RomanToNumberConverter.romanToArabic("CCCC");
            fail();
            // Assert.assertEquals("result does not match with the expected output", ParseErrors.INVALID_ROMAN_STRING.getDescription(), val);

        } catch (ConverterException e) {
            logger.error("Conversion  failed: ", e);
            Assert.assertEquals("Conversion not  successful", ParseErrors.INVALID_ROMAN_STRING, e.getParseErrors());

        }

    }
}
