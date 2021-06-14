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
        // If string contains anything other than numbers, return false.
        return cpr.length() == 10 && cpr.charAt(6) != '-' && cprSubstring.matches("\\d+");
    }

    public boolean isValidFirstName (String firstName) {
        return firstName.length() >= 2 && firstName.length() <= 155;
    }

    public boolean isValidLastName (String lastName) {
        return lastName.length() >= 2 && lastName.length() <= 155;
    }

    public boolean isValidPhoneNumber (String phoneNumber) {
        return phoneNumber.length() == 11 && (phoneNumber.startsWith("+") || phoneNumber.startsWith("00")) && phoneNumber.substring(1).matches("\\d+");
    }

    public boolean isValidMail (String mail) {
        return mail.length() >= 5 && mail.contains("@") && mail.contains(".");
    }

    public boolean isValidConsent (String consent) {
        return consent.equals("yes") || consent.equals("no");
    }

    public boolean isValidClinic (String clinic) {
        return clinic.length() == 6 && !clinic.matches("[^0-9]");

    //public boolean isValidAddress (String address) {
        //return false;

    }