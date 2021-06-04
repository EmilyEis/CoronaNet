package sample;

public class Patient extends Person {

    private String mail;
    private String phoneNumber;
    private String consent;
    private String clinic;

    public Patient(int inputIdPatient, String inputCPR, String inputFirstName, String inputLastName) {
        super(inputIdPatient, inputCPR, inputFirstName, inputLastName);
    }

    // Registering patient with valid CPR
    public Patient(int inputIdPatient, String inputCPR, String inputFirstName, String inputLastName, String inputMail, String inputPhone,
                   String inputConsent, String inputClinic) {
        super(inputIdPatient, inputCPR, inputFirstName, inputLastName);
        this.mail = inputMail;
        this.phoneNumber = inputPhone;
        this.consent = inputConsent;
        this.clinic = inputClinic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) throws Exception {
        this.phoneNumber = PatientRegister.modifyPhoneNumber(newPhoneNumber, this).getPhoneNumber();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String newMail) throws Exception {
        this.mail = PatientRegister.modifyMail(newMail, this).getMail();
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String newConsent) throws Exception {
        this.consent = PatientRegister.modifyConsent(newConsent, this).getConsent();
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String newClinic) throws Exception {
        this.clinic = PatientRegister.modifyClinic(newClinic,this).getClinic();
    }


}