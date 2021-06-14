package sample;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@XmlRootElement
public class DataHandler {

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
    private TextField CPR;
    @FXML
    private TextArea infectionRate;
    @FXML
    public static BarChart<?,?> virusChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;

    public void searchVirusTrendsButtonClicked() throws Exception {
        // If the input zip code is valid, move on to next section.
        // If input, zip code or date is invalid, the user is notified.
        if (DataValidator.isValidZipCode(zipCode.getText())) {
            try {
                ArrayList<Integer> searchResult = TestRegister.findPositiveTests(
                        PatientRegister.findAddress((Integer.parseInt(zipCode.getText()))),
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

                String searchResultString = "Zip code: " + zipCode.getText() + "\nDate range: "
                        + startDate.getText() + " to " + endDate.getText() + "\nNumber of positive test cases: " +
                        (searchResult.get(0) + searchResult.get(1) + searchResult.get(2) + searchResult.get(3) + searchResult.get(4)
                                + searchResult.get(5) + searchResult.get(6) + searchResult.get(7) + searchResult.get(8) + searchResult.get(9))
                        + "\n\nDistribution of mutations (strains defined by WHO):"
                        + "\nAlpha = " + Alpha + "\n" + "Beta = " + Beta + "\n" + "Gamma = " + Gamma + "\n" +
                        "Delta = " + Delta + "\n" + "Epsilon = " + Epsilon + "\n" + "Zeta = " + Zeta + "\n" + "Eta = " + Eta +
                        "\n" + "Theta = " + Theta + "\n" + "Iota = " + Iota + "\n" + "Kappa = " + Kappa;
                infectionRate.setText(searchResultString);

                JAXBElement<String> jaxbElement = new JAXBElement(new QName("root-element"),
                        String.class, searchResultString);
                CoronaNet.infectionRateToXML("searchResult.xml", jaxbElement);

            } catch (Exception e) {
                    infectionRate.setText("Please input a correct date.");
                }
        } else {
            infectionRate.setText("Please input numbers only in zip code.");
        }
    }

    public void searchButtonClicked() {
        try {
            // if a valid patient matches the input CPR, patient information is displayed. Otherwise, an error is displayed.
            if (PatientRegister.findPatient(PatientRegister.dbConFindPatient(CPR.getText()))) {
                Patient searchResultPatient = PatientRegister.dbConFindPatient(CPR.getText());
                viewPatientInfo.setText("CPR: " + searchResultPatient.getCPR() + "\n" + "Name: " +
                        searchResultPatient.getFirstName() + " " + searchResultPatient.getLastName() + "\n" + "Mail: "
                        + searchResultPatient.getMail() + "\n" + "Phone: " + searchResultPatient.getPhoneNumber() + "\n" +
                        "Consent: " + searchResultPatient.getConsent());

                Test searchResultTest = TestRegister.dbConFindTest((searchResultPatient.getIdPerson()));
                viewTests.setText("Test ID: " + searchResultTest.getTestID() + "\n" + "CPR: " + searchResultPatient.getCPR() + "\n" +
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

        }
        catch (Exception ex) {
            viewPatientInfo.setText("No such patient found.");
        }
    }

    public void healthcareButtonClicked() throws IOException {
        Stage secondStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Healthcare.fxml"));
        secondStage.setScene(new Scene(root, 800, 600));
        secondStage.setTitle("Logged in as healthcare worker");
        secondStage.show();
    }

    public void governmentalButtonClicked() throws IOException {
        Stage secondStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Government.fxml"));
        secondStage.setScene(new Scene(root, 800, 600));
        secondStage.setTitle("Logged in as decision maker");
        secondStage.show();
    }

    @Override
    public String toString() {
        return "InfectionRate{" +
                "infectionRate='" + infectionRate + '}';
    }
}

