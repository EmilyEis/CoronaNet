package sample;

import java.sql.*;

public class Virus {

    private String strain;

    public Virus(String inputStrain) {
        this.strain = inputStrain;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String newStrain) {
        String oldStrain = this.getStrain();
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update1 = "UPDATE CoronaNet.Virus " +
                "SET Virus.strain = '" + newStrain +
                "' WHERE Virus.strain = '" + oldStrain + "'";

        String update2 = "UPDATE CoronaNet.Test " +
                "SET Test.strain = '" + newStrain +
                "' WHERE Test.strain = '" + oldStrain + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            System.out.println(st.executeUpdate(update1));
            System.out.println(st.executeUpdate(update2));

            this.strain = newStrain;
            System.out.println("Strain name " + oldStrain + " successfully modified to " + newStrain);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addVirus(String strain) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        if (findStrain(strain) == null) {

            try (Connection con = DriverManager.getConnection(url, null, password)) {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO CoronaNet.Virus (strain) VALUES ('" + strain + "')");
                System.out.println("Mutation successfully added.");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("Mutation could not be added. Name is already in use.");
        }
    }

        public void removeVirus (String strain){
            String url = "jdbc:mysql://127.0.0.1:/?user=root";
            String password = "1234";

            if (findStrain(strain) != null) {

            try (Connection con = DriverManager.getConnection(url, null, password)) {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM CoronaNet.Virus WHERE strain = '" + strain + "'");
                System.out.println(strain + " successfully removed.");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            } else {
                System.out.println("No such name exists.");
            }
        }

    public String findStrain(String strain) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        String rsStrain = null;

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Virus WHERE strain = '" + strain + "'")) {

            while (rs.next()) {
                rsStrain = rs.getString(2);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsStrain;
    }

}
