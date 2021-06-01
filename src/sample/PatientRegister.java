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


    private static void dbConAddPatient(Patient newPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "INSERT INTO CoronaNet.Patient (CPR, firstName, lastName, mail, phone, consent, clinic) VALUES ("
                + "'" + newPatient.getCPR() + "','" + newPatient.getFirstName() + "','" + newPatient.getLastName() + "','" +
                newPatient.getMail() + "','" + newPatient.getPhoneNumber() + "','" + newPatient.getConsent() + "','" +
                newPatient.getClinic() + "')";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update);
            System.out.println("Patient successfully added.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void dbConRemovePatient(Patient inputPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        String update = "DELETE FROM CoronaNet.Patient WHERE CPR = " + "'" + inputPatient.getCPR() + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update);

            System.out.println("Patient successfully removed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void dbConModifyPatient(String inputCPR, Patient inputPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "UPDATE CoronaNet.Patient SET CPR = " + "'" + inputPatient.getCPR() + "'" + ", firstName = " +
                "'" + inputPatient.getFirstName() + "'" + ", lastName = " + "'" + inputPatient.getLastName() + "'"
                + ", mail = " + "'" + inputPatient.getMail() + "'" + ", phone = " + "'" + inputPatient.getPhoneNumber()
                + "'" + ", consent = " + "'" + inputPatient.getConsent() + "'" + ", clinic = " + "'" +
                inputPatient.getClinic() + "'" + " WHERE CPR = " + "'" + inputCPR + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Address dbConFindAddress(String inputCPR) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        Address currentAddress = null;

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Address WHERE CPR = " + "'" + inputCPR + "'")) {

            while (rs.next()) {
                String street = rs.getString(2);
                int zipCode = rs.getInt(3);
                String city = rs.getString(4);

                currentAddress = new Address(street, zipCode, city);

            }
            return currentAddress;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return currentAddress;
    }

    // Method for printing current number of patients in list + their name and cpr
    public static void showAllPatients() {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Patient")) {

            while (rs.next()) {
                String CPR = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String consent = rs.getString(6);
                String clinic = rs.getString(7);

                Patient currentPatient = new Patient(CPR, firstName, lastName, mail, phoneNumber, consent, clinic);
                System.out.println(currentPatient);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void findPatient(Patient currentPatient) {
        if (dbConFindPatient(currentPatient.getCPR()) != null &&
                currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR())) {
            dbConFindPatient(currentPatient.getCPR());

        } else {
            System.out.println("No patient found.");
        }
    }

    // Method for checking if the patient already exists in the register
    public static void addPatient(Patient currentPatient) {
        // If current patient does not exist in the database 'patients' add patient to the database
        if (!currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR())) {
            dbConAddPatient(currentPatient);

        } else {
            System.out.println("Patient already registered.");
        }
    }

    //Method removing patient from the pt list
    public static void removePatient(Patient currentPatient) {
        if (dbConFindPatient(currentPatient.getCPR()) != null &&
                currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR())) {
            dbConRemovePatient(currentPatient);
        } else {
            System.out.println("No such patient registered.");
        }
    }

    // Modify the patient's info. CurrentPatient replaces the patient object which matches inputCPR
    public static Patient modifyPatient(String inputCPR, Patient currentPatient) {
        if (inputCPR.equals(dbConFindPatient(inputCPR).getCPR()) &&
                (!currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR()))) {
            return dbConFindPatient(currentPatient.getCPR());
        } else {
            System.out.println("No such patient registered.");
            return null;
        }
    }

    public static Patient modifyCPR(String newCPR, Patient currentPatient) {
        Patient modifiedPatient = new Patient(newCPR, currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(),
                currentPatient.getConsent(), currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyFirstName(String newFirstName, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), newFirstName,
                currentPatient.getLastName(), currentPatient.getMail(),
                currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyLastName(String newLastName, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), currentPatient.getFirstName(),
                newLastName, currentPatient.getMail(), currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyPhoneNumber(String newPhoneNumber, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), newPhoneNumber, currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyMail(String newMail, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), newMail, currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyConsent(String newConsent, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(), newConsent,
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyClinic(String newClinic, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(),
                currentPatient.getConsent(), newClinic);

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Address findAddress(String inputCPR) {
        if (dbConFindAddress(inputCPR) != null) {
            return dbConFindAddress(inputCPR);
        }

        else {
            System.out.println("No address registered to the CPR " + inputCPR);
            return null;
        }
    }
}