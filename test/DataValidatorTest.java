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

    @org.junit.jupiter.api.Test
    void isValidFirstNameValid() {
        DataValidator dv = new DataValidator();
        String input = "Thomas";
        String input2 = "Emily Eva";
        boolean result = dv.isValidFirstName(input);
        boolean result2 = dv.isValidFirstName(input2);
        Assertions.assertTrue(result);
        Assertions.assertTrue(result2);
    }

    @org.junit.jupiter.api.Test
    void isValidFirstNameNotValid() {
        DataValidator dv = new DataValidator();
        String input = "B";
        boolean result = dv.isValidFirstName(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidLastNameValid() {
        DataValidator dv = new DataValidator();
        String input = "Liberski";
        boolean result = dv.isValidLastName(input);
        Assertions.assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void isValidLastNameNotValid() {
        DataValidator dv = new DataValidator();
        String input = "A";
        boolean result = dv.isValidLastName(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidPhoneNumberValid() {
        DataValidator dv = new DataValidator();
        String input = "+4512345678";
        boolean result = dv.isValidPhoneNumber(input);
        Assertions.assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void isValidPhoneNumberNotValid() {
        DataValidator dv = new DataValidator();
        String input = "+4512345A78";
        boolean result = dv.isValidPhoneNumber(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidMailValid() {
        DataValidator dv = new DataValidator();
        String input = "john@dillermand.dk";
        String input2 = "mr24_cm@kvarti9.us";
        boolean result = dv.isValidMail(input);
        boolean result2 = dv.isValidMail(input2);
        Assertions.assertTrue(result);
        Assertions.assertTrue(result2);
    }

    @org.junit.jupiter.api.Test
    void isValidMailNotValid() {
        DataValidator dv = new DataValidator();
        String input = "mr24cm@kvarti9us";
        boolean result = dv.isValidMail(input);
        Assertions.assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void isValidLastConsentValid() {
        DataValidator dv = new DataValidator();
        String input = "yes";
        boolean result = dv.isValidConsent(input);
        Assertions.assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void isValidLastConsentNotValid() {
        DataValidator dv = new DataValidator();
        String input = "ofc";
        String input2 = "n0";
        boolean result = dv.isValidConsent(input);
        boolean result2 = dv.isValidConsent(input2);
        Assertions.assertFalse(result);
        Assertions.assertFalse(result2);
    }

    @org.junit.jupiter.api.Test
    void isValidClinicValid() {
        DataValidator dv = new DataValidator();
        String input = "123456";
        boolean result = dv.isValidClinic(input);
        Assertions.assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void isValidClinicNotValid() {
        DataValidator dv = new DataValidator();
        String input = "1A2B3C";
        String input2 = "1A2B3C4D5E6F";
        boolean result = dv.isValidClinic(input);
        boolean result2 = dv.isValidClinic(input2);
        Assertions.assertFalse(result);
        Assertions.assertFalse(result2);
    }

}
