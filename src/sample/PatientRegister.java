package sample;
import java.sql.*;

public class PatientRegister {

    public static Patient dbConPatient(String inputCPR) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "SELECT * FROM CoronaNet.Patient WHERE CPR = " + "'" + inputCPR + "'";
        Patient currentPatient = null;

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                String CPR = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String consent = rs.getString(6);
                String clinic = rs.getString(7);

                currentPatient = new Patient(CPR, firstName, lastName, mail, phoneNumber, consent, clinic);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return currentPatient;
    }

}












 /*   // Array to hold registered patients
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
*/