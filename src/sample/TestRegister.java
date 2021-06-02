package sample;

import java.sql.*;

public class TestRegister {

    public static Test dbConFindTest(String inputCPR) {
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

    public static void dbConAddTest(Test newTest) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "INSERT INTO CoronaNet.Test (testID, CPR, result, strain, date, time) VALUES ("
                + "'" + newTest.getTestID() + "','" + newTest.getCPR() + "','" + newTest.getResult() + "','" +
                newTest.getStrain() + "','" + newTest.getDate() + "','" + newTest.getTime() + "')";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update);
            System.out.println("Test successfully added.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void dbConModifyTest(String inputTestID, Test inputTest) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "UPDATE CoronaNet.Test SET CPR = " + "'" + inputTest.getCPR() + "'" + ", result = " +
                "'" + inputTest.getResult() + "'" + ", strain = " + "'" + inputTest.getStrain() + "'"
                + ", date = " + "'" + inputTest.getDate() + "'" + ", time = " + "'" + inputTest.getTime()
                + "'" + " WHERE testID = " + "'" + inputTestID + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            int rs = st.executeUpdate(update);
            System.out.println("Test successfully modified.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet findPositiveTests(int inputZipCode, Date startDate, Date endDate) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet result = st.executeQuery("SELECT COUNT(*) FROM CoronaNet.Test WHERE result = 'Positive' AND " +
                     "date >= " + "'" + startDate + "'" + "AND date <= " + "'" + endDate + "';")) {
            return result;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}