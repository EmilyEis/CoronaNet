package sample;

import java.sql.*;
import java.util.ArrayList;

public class PatientRegister {

    public static Patient dbConFindPatient(String inputCPR) {
            Patient currentPatient = null;
            String url = "jdbc:mysql://127.0.0.1:/?user=root";
            String password = "1234";

            String query = "SELECT CoronaNet.Person.*, CoronaNet.Patient.* FROM CoronaNet.Person INNER JOIN CoronaNet.Patient " +
                    "ON CoronaNet.Person.idPerson = " +
                    "CoronaNet.Patient.idPatient WHERE CoronaNet.Person.CPR = " + "'" + inputCPR + "'";

            try (Connection con = DriverManager.getConnection(url, null, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {
                    int idPatient = rs.getInt(1);
                    String CPR = rs.getString(2);
                    String firstName = rs.getString(3);
                    String lastName = rs.getString(4);
                    String mail = rs.getString(8);
                    String phone = rs.getString(9);
                    String consent = rs.getString(10);
                    String clinic = rs.getString(11);

                    currentPatient = new Patient(idPatient, CPR, firstName, lastName, mail, phone, consent, clinic);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return currentPatient;

        } return currentPatient;
    }

    public static void dbConAddPatient(Patient newPatient) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "INSERT INTO CoronaNet.Patient (idPatient, mail, phone, consent, clinic, Person_idPerson, HP_idHP) " +
                "VALUES ("
                + "'" + newPatient.getIdPerson() + "','" + newPatient.getMail() + "','" + newPatient.getPhoneNumber() +
                "','" + newPatient.getConsent() + "','" + newPatient.getClinic() + "','" + newPatient.getIdPerson() +
                "', '0')";

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

        String update1 = "UPDATE CoronaNet.Person " +
                "INNER JOIN CoronaNet.Patient " +
                "ON CoronaNet.Person.idPerson = CoronaNet.Patient.idPatient " +
                "SET " +
                "Person.CPR = '" + inputPatient.getCPR() +
                "', Person.firstName = '" + inputPatient.getFirstName() +
                "', Person.lastName = '"  + inputPatient.getLastName() +
                "' WHERE Person.CPR = '" + inputCPR + "'";

        String update2 = "UPDATE CoronaNet.Patient " +
                "INNER JOIN CoronaNet.Person " +
                "ON CoronaNet.Person.idPerson = CoronaNet.Patient.idPatient " +
                "SET " +
                "Patient.mail = '" + inputPatient.getMail() +
                "', Patient.phone = '" + inputPatient.getPhoneNumber() +
                "', Patient.consent = '" + inputPatient.getConsent() +
                "', Patient.clinic = '" + inputPatient.getClinic() +
                "' WHERE Person.CPR = '" + inputCPR + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update1);
            st.executeUpdate(update2);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static ArrayList<Integer> dbConAddressFindPerson(int inputZipCode) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        ArrayList<Integer> personList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Address, CoronaNet.Geolocation " +
                             "WHERE CoronaNet.Address.zipCode = '" + inputZipCode +
                             "' OR CoronaNet.Geolocation.zipCode = '" + inputZipCode + "'")) {

            while (rs.next()) {
                if (rs.getInt(3) == inputZipCode) {
                personList.add(rs.getInt(5));
            }
                else if (rs.getInt(7) == inputZipCode) {
                    personList.add(rs.getInt(9));
                }
                }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personList;
    }

    public static boolean findPatient(Patient currentPatient) {
        if (dbConFindPatient(currentPatient.getCPR()) != null &&
                currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR())) {
            return true;

        } else {
            System.out.println("No patient found.");
            return false;
        }
    }

    // Method for checking if the patient already exists in the register
    public static void addPatient(Patient currentPatient) {

        // If current patient does not exist in the database 'patients' add patient to the database
        if (!findPatient(currentPatient)) {
            dbConAddPatient(currentPatient);
        }
        else {
            System.out.println("Patient already registered.");
        }
    }

    //Method removing patient from the pt list
    public static void removePatient(Patient currentPatient) {
        if (dbConFindPatient(currentPatient.getCPR()) != null &&
                currentPatient.getCPR().equals(dbConFindPatient(currentPatient.getCPR()).getCPR())) {
            dbConRemovePatient(currentPatient);
        }
    }

    // Modify the patient's info. CurrentPatient replaces the patient object which matches inputCPR
    public static Patient modifyPatient(String inputCPR, Patient currentPatient) {
        if (!findPatient(currentPatient) && findPatient(dbConFindPatient(inputCPR))) {
            dbConModifyPatient(inputCPR, currentPatient);
            System.out.println("Patient successfully modified.");
            return dbConFindPatient(currentPatient.getCPR());

        } else {
            System.out.println("Patient could not be modified.");
            return null;
        }
    }


    public static Patient modifyCPR(String newCPR, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), newCPR, currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(),
                currentPatient.getConsent(), currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyFirstName(String newFirstName, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), newFirstName,
                currentPatient.getLastName(), currentPatient.getMail(),
                currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyLastName(String newLastName, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), currentPatient.getFirstName(),
                newLastName, currentPatient.getMail(), currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyPhoneNumber(String newPhoneNumber, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), newPhoneNumber, currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyMail(String newMail, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), newMail, currentPatient.getPhoneNumber(), currentPatient.getConsent(),
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyConsent(String newConsent, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(), newConsent,
                currentPatient.getClinic());

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static Patient modifyClinic(String newClinic, Patient currentPatient) {
        Patient modifiedPatient = new Patient(currentPatient.getIdPerson(), currentPatient.getCPR(), currentPatient.getFirstName(),
                currentPatient.getLastName(), currentPatient.getMail(), currentPatient.getPhoneNumber(),
                currentPatient.getConsent(), newClinic);

        return modifyPatient(modifiedPatient.getCPR(), modifiedPatient);
    }

    public static ArrayList<Integer> findAddress(int inputZipCode) {
        return dbConAddressFindPerson(inputZipCode);
    }
}

// Saving the following code for future use (does not work atm)!
    /*
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
    }*/