package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Healthcare.fxml"));
        primaryStage.setTitle("Logged in as healthcare");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Patient testPatient = new Patient("1234567897", "Janet", "Doe",
                null, null, "yes", "12345");
        // PatientRegister.addPatient(testPatient);
        PatientRegister.removePatient(testPatient);
         //System.out.println((PatientRegister.findPatient(testPatient).getFirstName()));
        //PatientRegister.findPatient(testPatient);

        //launch(args);

    }
}
