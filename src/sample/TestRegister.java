package sample;

import java.sql.*;

public class TestRegister {
    public static Test dbConTest(String inputCPR) {
        Test currentTest = null;

        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "SELECT * FROM CoronaNet.Test WHERE CPR = " + "'" + inputCPR + "'";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int testID = rs.getInt(1);
                String CPR = rs.getString(2);
                String result = rs.getString(3);
                String strain = rs.getString(4);
                Date date = rs.getDate(5);
                Time time = rs.getTime(6);

                currentTest = new Test(testID, CPR, result, strain, date, time);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return currentTest;
    }
}