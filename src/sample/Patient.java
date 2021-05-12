package sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient {

    // Creating fields/attributes for name/cpr
    private String CPR;
    private String firstName;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String consent;
    private String clinic;

    // Registering patient with valid CPR
    public Patient(String inputCPR, String inputFirstName, String inputLastName, String inputMail, String inputPhone,
                   String inputConsent, String inputClinic) {
        this.CPR = inputCPR;
        this.firstName = inputFirstName;
        this.lastName = inputLastName;
        this.mail = inputMail;
        this.phoneNumber = inputPhone;
        this.consent = inputConsent;
        this.clinic = inputClinic;
    }

    // Getters and setters for first name, last name, cpr, phone number and mail
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String newCPR) {
        CPR = newCPR;}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhone) {
        phoneNumber = newPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String newMail) {
        mail = newMail;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String newConsent) {
        consent = newConsent;
    }

    public String getClinic() {
        return clinic;
    }

    // Calculate age
    public int getAge () {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMuu");
        LocalDate today = LocalDate.now();
        LocalDate birthdate = LocalDate.parse(CPR.substring(0,6), dateFormatter);

        // If the birthdate is after today, it's probably in the 1900's. Subtract 100 years.
        if (birthdate.isAfter(today)) { birthdate = birthdate.minusYears(100); }
        int age = Period.between(birthdate, today).getYears();
        return age;
    }

    // If the last number in CPR is even, the person's female. Otherwise he's male.
    public String getGender() {
        String serial = CPR.substring(7, 11);
        if (Integer.parseInt(serial) % 2 == 0) {
            return "female";
        } else {
            return "male";
        }
    }
}