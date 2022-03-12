import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ValidateISBNTest{

    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result, "first value: ");
        result = validator.checkISBN("0140177396");
        assertTrue(result, "second value: ");
    }

    @Test
    public void ISBN10DigitNumbersEndingWithXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue(result);
        result = validator.checkISBN("9781853267338");
        assertTrue(result);
    }

    @Test
    public void checkAnInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853267332");
        assertFalse(result);
    }

    // tutorial used JUnit4, but I have JUnit5, therefore I had to do it differently. Not sure if this is correct, but
    // it seems to work.
    @Test   // @Test(expected = NumberFormatException.class) for JUnit4
    public void nineDigitISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("123456789");
        }, "NumberFormatException was expected");

        Assertions.assertEquals("ISBN numbers must be 10 or 13 digits long", thrown.getMessage());
    }

    @Test
    public void onlyDigitsAllowed() {
        ValidateISBN validator = new ValidateISBN();
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("helloworld");
            validator.checkISBN("hello world13");
        }, "NumberFormatException was expected");

        Assertions.assertEquals("ISBN numbers contain only digits", thrown.getMessage());
    }

    //
}
