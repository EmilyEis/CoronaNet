package sample;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Healthcare.fxml"));
        primaryStage.setTitle("Logged in as healthcare");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Patient testPatient = new Patient("1212997897", "Janet", "Doe",
                null, null, "yes", "12345");
        Test newTest = new Test(0, "1234567890","Negative", "B.1.1.6", Date.valueOf("2020-8-10"), Time.valueOf("20:32:50"));

        System.out.println(testPatient.getAge());
        PatientRegister.findPatient(testPatient);
        PatientRegister.findAddress(1);

        testToXML("test.xml", newTest);

        //testPatient.setFirstName("Haley");
        //launch(args);
    }

    private static void testToXML(String filename, Test test) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Test.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(test, file);
        jaxbMarshaller.marshal(test, System.out);
    }
}