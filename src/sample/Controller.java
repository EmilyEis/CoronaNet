package sample;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.ArrayList;

@XmlRootElement

public class Controller {

    @FXML
    private TextField zipCode;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private TextArea viewPatientInfo;
    @FXML
    private TextArea viewTests;
    @FXML
    private TextArea viewSensorGeo;
    @FXML
    private TextArea CPR;

    @FXML private TextArea infectionRate;

    @XmlElement(name = "infectionRate")
    String searchResultAsString = "";

    public void searchVirusTrendsButtonClicked() throws Exception {
        ArrayList<Integer> searchResult = TestRegister.findPositiveTests(PatientRegister.findAddress((Integer.parseInt(zipCode.getText()))),
                Date.valueOf(startDate.getText()), Date.valueOf(endDate.getText()));
        String Alpha = searchResult.get(0).toString();
        String Beta = searchResult.get(1).toString();
        String Gamma = searchResult.get(2).toString();
        String Delta = searchResult.get(3).toString();
        String Epsilon = searchResult.get(4).toString();
        String Zeta = searchResult.get(5).toString();
        String Eta = searchResult.get(6).toString();
        String Theta = searchResult.get(7).toString();
        String Iota = searchResult.get(8).toString();
        String Kappa = searchResult.get(9).toString();

        searchResultAsString = ("Alpha = " + Alpha + "\n" + "Beta = " + Beta + "\n" + "Gamma = " + Gamma + "\n" +
                "Delta = " + Delta + "\n" + "Epsilon = " + Epsilon + "\n" + "Zeta = " + Zeta + "\n" + "Eta = " + Eta +
                "\n" + "Theta = " + Theta + "\n" + "Iota = " + Iota + "\n" + "Kappa = " + Kappa);

        infectionRate.setText(searchResultAsString);


        Main.infectionRateToXML("testResult", searchResultAsString);
    }

    public void searchButtonClicked() {
        Patient searchResultPatient = PatientRegister.dbConFindPatient(CPR.getText());
        viewPatientInfo.setText("CPR: " + searchResultPatient.getCPR() + "\n" + "Name: " +
                searchResultPatient.getFirstName() + " " + searchResultPatient.getLastName() + "\n" + "Mail: "
                + searchResultPatient.getMail() + "\n" + "Phone: " + searchResultPatient.getPhoneNumber() + "\n" +
                "Consent: " + searchResultPatient.getConsent());

        Test searchResultTest = TestRegister.dbConFindTest(CPR.getText());
        viewTests.setText("Test ID: " + searchResultTest.getTestID() + "\n" + "CPR: " + searchResultTest.getCPR() + "\n" +
                "Result: " + searchResultTest.getResult() + "\n" + "Strain: " + searchResultTest.getStrain() + "\n" +
                "Date & time: " + searchResultTest.getDate() + " " + searchResultTest.getTime());

        viewSensorGeo.setText("""
                Blood pressure: 140/70
                Pulse: 80bpm
                Saturation: 99%
                Temperature: 37,5C
                EKG: normal
                Last geographical location (zip code): 2100""");
    }

    public void healthcareButtonClicked() {
        System.out.println("Logged in as healthcare.");
    }

    public void governmentalButtonClicked() {
        System.out.println("Logged in as government.");
    }

    @Override
    public String toString() {
        return "InfectionRate{" +
                "infectionRate='" + infectionRate + '}';
    }
}

