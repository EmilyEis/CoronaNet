package sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient {

    // Creating fields/attributes for name/cpr
    private String firstName;
    private String lastName;
    private String CPR;
    private String phoneNumber;
    private String mail;
    private String consent;
    private int clinic;

    // Registering patient with valid CPR
    public Patient(String inputFirstName, String inputLastName, String inputCPR, String inputPhone, String inputMail) {
        this.firstName = inputFirstName;
        this.lastName = inputLastName;
        this.CPR = inputCPR;
        this.phoneNumber = inputPhone;
        this.mail = inputMail;
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
