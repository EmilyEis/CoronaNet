package sample;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class CoronaNet extends Application {

    // The following starts the primary stage from which the user can choose a login.
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CoronaNet.fxml"));
        primaryStage.setTitle("CoronaNet");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        /*Patient testPatient = new Patient(49, "2806992324", "Thomas",
                "Hendricks", null, null, "no", "0000");
        Patient testPatient1 = new Patient(0, "1212120102", "Elisa",
                "Martins");
        Patient testPatient2 = new Patient(0, "2806992323", "Thomas",
                "Hendricks", null, null, "no", "0000");
        PatientRegister.findPatient(testPatient1);

        TestRegister.dbConAddTest(new Test(100,"Negative",null, Date.valueOf("2020-06-28"),
               Time.valueOf("13:12:12"),14,0,0));
        Virus virus = new Virus("virus");
        virus.addVirus(virus.getStrain());
        virus.setStrain("NO");
        virus.removeVirus("NO");
        PatientRegister.removePatient(testPatient1); */
        launch(args);
    }

    // XML file writers
    private static void testToXML(String filename, Test test) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Test.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(test, file);
        jaxbMarshaller.marshal(test, System.out);
    }

    private static void patientToXML(String filename, Patient patient) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(patient, file);
        jaxbMarshaller.marshal(patient, System.out);
    }

    public static void infectionRateToXML(String filename, JAXBElement<String> infectionRate) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXBElement.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(infectionRate, file);
        jaxbMarshaller.marshal(infectionRate, System.out);
    }
}