package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<Integer> findPositiveTests(ArrayList <Integer> personList, Date startDate, Date endDate) {
        int strainAlpha = 0; int strainBeta = 0; int strainGamma = 0; int strainDelta = 0; int strainEpsilon = 0;
        int strainZeta = 0; int strainEta = 0; int strainTheta = 0; int strainIota = 0; int strainKappa = 0;

        for (int i : personList) {
            String url = "jdbc:mysql://127.0.0.1:/?user=root";
            String password = "1234";

            try (Connection con = DriverManager.getConnection(url, null, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM CoronaNet.Test WHERE Patient_idPatient = " + "'" + i + "'"
                         + "AND date >= " + "'" + startDate + "'" + "AND date <= " + "'" + endDate + "'" + " AND result = 'Positive';")) {

                while (rs.next()) {
                    if (rs.getInt(9) == 1) {
                        strainAlpha += 1;
                    }
                    else if (rs.getInt(9) == 2) {
                        strainBeta += 1;
                    }
                    else if (rs.getInt(9) == 3) {
                        strainGamma += 1;
                    }
                    else if (rs.getInt(9) == 4) {
                        strainDelta += 1;
                    }
                    else if (rs.getInt(9) == 5) {
                        strainEpsilon += 1;
                    }
                    else if (rs.getInt(9) == 6) {
                        strainZeta += 1;
                    }
                    else if (rs.getInt(9) == 7) {
                        strainEta += 1;
                    }
                    else if (rs.getInt(9) == 8) {
                        strainTheta += 1;
                    }
                    else if (rs.getInt(9) == 9) {
                        strainIota += 1;
                    }
                    else if (rs.getInt(9) == 10) {
                        strainKappa += 1;
                    }

            }
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return new ArrayList<>(Arrays.asList(strainAlpha, strainBeta, strainGamma, strainDelta, strainEpsilon, strainZeta, strainEta, strainTheta, strainIota, strainKappa));

    }
}