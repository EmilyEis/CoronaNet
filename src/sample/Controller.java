package sample;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private TextArea viewPatientInfo;
    @FXML private TextArea viewTests;
    @FXML private TextArea viewSensorGeo;
    @FXML private TextField CPR;

    public void searchButtonClicked() {
        Patient searchResultPatient = PatientRegister.dbConPatient(CPR.getText());
        viewPatientInfo.setText("CPR: " + searchResultPatient.getCPR() + "\n" + "Name: " +
                searchResultPatient.getFirstName() + " " + searchResultPatient.getLastName() + "\n" + "Mail: "
                + searchResultPatient.getMail() + "\n" + "Phone: " + searchResultPatient.getPhoneNumber() + "\n" +
                "Consent: " + searchResultPatient.getConsent());

        Test searchResultTest = TestRegister.dbConTest(CPR.getText());
        viewTests.setText("Test ID: " + searchResultTest.getTestID() + "\n" + "CPR: " + searchResultTest.getCPR() + "\n" +
                "Result: " + searchResultTest.getResult() + "\n" + "Strain: " + searchResultTest.getStrain() + "\n" +
                "Date & time: " + searchResultTest.getDate() + " " + searchResultTest.getTime());

        viewSensorGeo.setText("Blood pressure: 140/70" + "\n" + "Pulse: 80bpm" + "\n" + "Saturation: 99%" +
                "\n" + "Temperature: 37,5C" + "\n" + "EKG: normal" + "\n" + "Last geographical location (zip code): 2100");
    }

    public void healthcareButtonClicked() {
        System.out.println("Logged in as healthcare.");
    }

    public void governmentalButtonClicked() {
        System.out.println("Logged in as government.");
    }

}