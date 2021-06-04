package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestRegister {

    public static Test dbConFindTest(int inputIdPatient) {
        Test currentTest = null;

        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String query = "SELECT * FROM CoronaNet.Test WHERE Patient_idPatient = " + "'" + inputIdPatient + "'";

        try (Connection con = DriverManager.getConnection(url, null, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int testID = rs.getInt(1);
                String result = rs.getString(2);
                String strain = rs.getString(3);
                Date date = rs.getDate(4);
                Time time = rs.getTime(5);
                int Patient_idPatient = rs.getInt(6);
                int HP_idHP = rs.getInt(7);
                int Virus_idVirus = rs.getInt(8);

                currentTest = new Test(testID, result, strain, date, time, Patient_idPatient, HP_idHP, Virus_idVirus);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return currentTest;
    }

    public static void dbConAddTest(Test newTest) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "INSERT INTO CoronaNet.Test (result, strain, date, time, Patient_idPatient, HP_idHP," +
                "Virus_idVirus) VALUES ('" + newTest.getResult() + "','" +
                newTest.getStrain() + "','" + newTest.getDate() + "','" + newTest.getTime() + "','" +
                newTest.getPatient_idPatient() + "','" + newTest.getHp_idHP() + "','" + newTest.getVirus_idVirus() +"')";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update);
            System.out.println("Test successfully added.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void dbConModifyTest(int inputTestID, Test inputTest) {
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";

        String update = "UPDATE CoronaNet.Test SET result = '"
                + inputTest.getResult() + "', strain = '" + inputTest.getStrain() + "', date = '" +
                inputTest.getDate() + "', time = '" + inputTest.getTime()
                + "' WHERE testID = '" + inputTestID + "'";

        try (Connection con = DriverManager.getConnection(url, null, password)) {
            Statement st = con.createStatement();
            st.executeUpdate(update);
            System.out.println("Test successfully modified.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void modifyTest(int inputTestID, Test inputTest) {
        if (dbConFindTest(inputTest.getPatient_idPatient()) != null) {
            dbConModifyTest(inputTestID, inputTest);
        }
    }

    public static ArrayList<Integer> findPositiveTests(ArrayList <Integer> personList, Date startDate, Date endDate) {
        int strainAlpha = 0; int strainBeta = 0; int strainGamma = 0; int strainDelta = 0; int strainEpsilon = 0;
        int strainZeta = 0; int strainEta = 0; int strainTheta = 0; int strainIota = 0; int strainKappa = 0;
        String url = "jdbc:mysql://127.0.0.1:/?user=root";
        String password = "1234";
        Set<Integer> uniqueList = new HashSet<>(personList);

        for (int i : uniqueList) {
            System.out.println(i);
            try (Connection con = DriverManager.getConnection(url, null, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT CoronaNet.Patient.*, CoronaNet.Test.* " +
                         "FROM CoronaNet.Patient INNER JOIN CoronaNet.Test " +
                         " ON CoronaNet.Patient.idPatient = CoronaNet.Test.Patient_idPatient " +
                         "WHERE CoronaNet.Patient.idPatient = '" + i +
                         "' AND CoronaNet.Test.date >= '" + startDate +
                         "' AND CoronaNet.Test.date <= '" + endDate +
                         "' AND CoronaNet.Test.result = 'Positive'")) {

                while (rs.next()) {

                    String consent = rs.getString(4);
                    Statement st1 = con.createStatement();
                    if (consent.equals("yes")) {
                        ResultSet rs1 = st1.executeQuery("SELECT CoronaNet.Test.*, CoronaNet.Geolocation.* " +
                                "FROM CoronaNet.Geolocation " +
                                "INNER JOIN CoronaNet.Test " +
                                "ON CoronaNet.Test.Patient_idPatient = CoronaNet.Geolocation.Patient_idPatient " +
                                "WHERE CoronaNet.Test.Patient_idPatient = '" + i +
                                "' AND CoronaNet.Test.date >= '" + startDate +
                                "' AND CoronaNet.Test.date <= '" + endDate +
                                "' AND CoronaNet.Test.result = 'Positive'");

                        while (rs1.next()) {

                            if (rs1.getInt(8) == 1) {
                                strainAlpha += 1;
                            } else if (rs1.getInt(8) == 2) {
                                strainBeta += 1;
                            } else if (rs1.getInt(8) == 3) {
                                strainGamma += 1;
                            } else if (rs1.getInt(8) == 4) {
                                strainDelta += 1;
                            } else if (rs1.getInt(8) == 5) {
                                strainEpsilon += 1;
                            } else if (rs1.getInt(8) == 6) {
                                strainZeta += 1;
                            } else if (rs1.getInt(8) == 7) {
                                strainEta += 1;
                            } else if (rs1.getInt(8) == 8) {
                                strainTheta += 1;
                            } else if (rs1.getInt(8) == 9) {
                                strainIota += 1;
                            } else if (rs1.getInt(8) == 10) {
                                strainKappa += 1;
                            }
                        }
                    }
                    else {
                        Statement st2 = con.createStatement();
                        ResultSet rs1 = st2.executeQuery("SELECT CoronaNet.Test.*, CoronaNet.Address.* " +
                                "FROM CoronaNet.Address " +
                                "INNER JOIN CoronaNet.Test " +
                                "ON CoronaNet.Test.Patient_idPatient = CoronaNet.Address.Person_idPerson " +
                                "WHERE CoronaNet.Test.Patient_idPatient = '" + i +
                                "' AND CoronaNet.Test.date >= '" + startDate +
                                "' AND CoronaNet.Test.date <= '" + endDate +
                                "' AND CoronaNet.Test.result = 'Positive'");

                        while (rs1.next()) {

                            if (rs1.getInt(8) == 1) {
                                strainAlpha += 1;
                            } else if (rs1.getInt(8) == 2) {
                                strainBeta += 1;
                            } else if (rs1.getInt(8) == 3) {
                                strainGamma += 1;
                            } else if (rs1.getInt(8) == 4) {
                                strainDelta += 1;
                            } else if (rs1.getInt(8) == 5) {
                                strainEpsilon += 1;
                            } else if (rs1.getInt(8) == 6) {
                                strainZeta += 1;
                            } else if (rs1.getInt(8) == 7) {
                                strainEta += 1;
                            } else if (rs1.getInt(8) == 8) {
                                strainTheta += 1;
                            } else if (rs1.getInt(8) == 9) {
                                strainIota += 1;
                            } else if (rs1.getInt(8) == 10) {
                                strainKappa += 1;
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return new ArrayList<>(Arrays.asList(strainAlpha, strainBeta, strainGamma, strainDelta, strainEpsilon,
                strainZeta, strainEta, strainTheta, strainIota, strainKappa));
    }
}