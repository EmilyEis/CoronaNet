package sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient extends Person {

    private String mail;
    private String phoneNumber;
    private String consent;
    private String clinic;

    // Registering patient with valid CPR
    public Patient(String inputCPR, String inputFirstName, String inputLastName, String inputMail, String inputPhone,
                   String inputConsent, String inputClinic) {
        super(inputCPR, inputFirstName, inputLastName);
        this.mail = inputMail;
        this.phoneNumber = inputPhone;
        this.consent = inputConsent;
        this.clinic = inputClinic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = PatientRegister.modifyPhoneNumber(newPhoneNumber, this).getPhoneNumber();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String newMail) {
        this.mail = PatientRegister.modifyMail(newMail, this).getMail();
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String newConsent) {
        this.consent = PatientRegister.modifyConsent(newConsent, this).getConsent();
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String newClinic) {
        this.clinic = PatientRegister.modifyClinic(newClinic,this).getClinic();
    }


}