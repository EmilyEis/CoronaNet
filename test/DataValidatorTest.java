import org.junit.jupiter.api.Assertions;
import sample.DataValidator;

class DataValidatorTest {

    @org.junit.jupiter.api.Test
    void isValidCprNotValid() {
        DataValidator dv = new DataValidator();
        String input = "123456-ABCD";
        boolean result = dv.isValidCPR(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidCprNotValidTooShort() {
        DataValidator dv = new DataValidator();
        String input = "1234";
        boolean result = dv.isValidCPR(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidCprNotValidWrongFormat() {
        DataValidator dv = new DataValidator();
        String input = "1234567-890";
        boolean result = dv.isValidCPR(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidDOBNotValid() {
        DataValidator dv = new DataValidator();
        String input = "476501-1234";
        boolean result = dv.isValidCPR(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidCprValid() {
        DataValidator dv = new DataValidator();
        String input = "2806991234";
        boolean result = dv.isValidCPR(input);
        Assertions.assertTrue(result);
    }
}