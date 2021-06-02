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

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String dbConFindStrain() {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        String strain = null;

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Virus")) {

            while (rs.next()) {
                strain = rs.getString(2);
                System.out.println(strain);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return strain;
    }
}
