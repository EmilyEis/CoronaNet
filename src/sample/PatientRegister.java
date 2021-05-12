package sample;
import java.sql.*;
import java.util.ArrayList;

public class PatientRegister {

    public static void dbConPatient() {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "SELECT * FROM CoronaNet.Patient";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int CPR = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String phone = rs.getString(5);
                String consent = rs.getString(6);
                int clinic = rs.getInt(7);
                System.out.println("| CPR: " + CPR + " | Name: " + firstName + " " + lastName +
                        " | Consent given (yes/no): " + consent + " | Clinic no.: " + clinic + " |");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }












    // Array to hold registered patients
    public ArrayList<Patient> patientList;

    // Constructor
    public PatientRegister() {
        this.patientList = new ArrayList<Patient>();
    }

    // Method for checking if the patient already exists in the register
    public void addPatient (Patient currentPatient) throws Exception{
        // If current patient does not exist in the list 'patients' add patient to the list
        if (!this.patientList.contains(currentPatient)) {
            this.patientList.add(currentPatient);
        }
        else {
            throw new Exception("Patient already registered.");
        }
    }

    //Method removing patient from the pt list
    public void removePatient(Patient patient) throws Exception {
        if (!this.patientList.contains(patient)) {
            patientList.remove(patient);
        }
        else {
            throw new Exception("No such patient registered");
        }
    }

    // Method for printing current number of patients in list + their name and cpr
    public void printPatients() {
        // Number of patients in list
        System.out.println("Current number of patients registered: " + patientList.size());

        // Printing patient info
        for (Patient currentPatient : patientList) {
            System.out.println(currentPatient.getFirstName() + " " + currentPatient.getLastName() + " " + currentPatient.getCPR());
        }

    }
}
