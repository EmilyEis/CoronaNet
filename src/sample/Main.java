package sample;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Government.fxml"));
        primaryStage.setTitle("Logged in as healthcare");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {



       /* Patient testPatient = new Patient("1212997897", "Janet", "Doe",
                null, null, "yes", "12345");
        Test newTest = new Test(0, "1234567890","Negative", "B.1.1.6", Date.valueOf("2020-8-10"), Time.valueOf("20:32:50"));

        System.out.println(testPatient.getAge());
        PatientRegister.findPatient(testPatient);
        PatientRegister.findAddress(1);

        System.out.println(TestRegister.findPositiveTests(PatientRegister.findAddress(2610),
                Date.valueOf("2020-01-01"), Date.valueOf("2021-01-01")));

        testToXML("test.xml", newTest);*/

        //testPatient.setFirstName("Haley");
        launch(args);
    }

    private static void testToXML(String filename, Test test) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Test.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(test, file);
        jaxbMarshaller.marshal(test, System.out);
    }

    public static void infectionRateToXML(String filename, String infectionRate) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(TextArea.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(infectionRate, file);
        jaxbMarshaller.marshal(infectionRate, System.out);
    }
}