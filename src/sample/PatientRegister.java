package sample;
import java.sql.*;

public class PatientRegister {

    public static Patient dbConFindPatient(String inputCPR) {
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

    public static void dbConAddPatient(Patient newPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "INSERT INTO CoronaNet.Patient (CPR, firstName, lastName, mail, phone, consent, clinic) VALUES ("
                + newPatient.getCPR() + "," + newPatient.getFirstName() + "," + newPatient.getLastName() + "," +
                newPatient.getMail() + "," + newPatient.getPhoneNumber() + "," + newPatient.getConsent() + "," +
                newPatient.getClinic() + ")";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

        }

        catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }




    public static void dbConRemovePatient(Patient inputPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "DELETE FROM CoronaNet.Patient WHERE CPR = " + inputPatient.getCPR();

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
        }

        catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }


    // Method for checking if the patient already exists in the register
    public void addPatient (Patient currentPatient) throws Exception {
        // If current patient does not exist in the list 'patients' add patient to the list

        if (currentPatient != dbConFindPatient(currentPatient.getCPR())) {
            dbConAddPatient(currentPatient);
        } else {
            throw new Exception("Patient already registered.");
        }
    }


    //Method removing patient from the pt list
    public void removePatient(Patient currentPatient) throws Exception {
        if (currentPatient == dbConFindPatient(currentPatient.getCPR())) {
            dbConRemovePatient(currentPatient);
        } else {
            throw new Exception("No such patient registered");
        }
    }


    // Method for printing current number of patients in list + their name and cpr
    public void printPatients() {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "SELECT * FROM CoronaNet.Patient";
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
                System.out.println(currentPatient);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
