package sample;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataValidator {
    public DataValidator() {
    }

    public boolean isValidCPR(String cpr) {
        // Make temporary string
        String cprSubstring = "";
        try {
            // Make substring without '-'.
            cprSubstring = cpr.substring(0, 6) + cpr.substring(7);
            // If index error is found, e.g. CPR is "1234" return false
        }
        catch(StringIndexOutOfBoundsException ex) {
            return false;
        }

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMuu");
            LocalDate birthdate = LocalDate.parse(cpr.substring(0,6), dateFormatter);
        }
        catch (java.time.format.DateTimeParseException ex) {
            return false;
        }

        // If length is not 10 or character at i = 6 is '-', return false.
        if (cpr.length() != 10 || cpr.charAt(6) == '-') {
            return false;
        }

        // If string contains anything other than numbers, return false.
        else if (cprSubstring.matches("[^0-9]")) {
            return false;
        }

        else {
            return true;
        }
    }

    public boolean isValidName (String name) {
        if (name.length() < 2 || name.length() > 155) {
            return false;
        }
        return true;
    }


    public boolean isValidAddress (String address) {
        return false;

    }}