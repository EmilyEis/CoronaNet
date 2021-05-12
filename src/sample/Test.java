package sample;

import java.sql.*;

public class Test {

    private int testID;
    private String CPR;
    private String result;
    private String strain;
    private Date date;
    private Time time;

    public Test (int inputTestID, String inputCPR, String inputResult, String inputStrain, Date inputDate, Time inputTime) {
        this.testID = inputTestID;
        this.CPR = inputCPR;
        this.result = inputResult;
        this.strain = inputStrain;
        this.date = inputDate;
        this.time = inputTime;
    }

    public int getTestID() {
        return testID;
    }

    public String getCPR() {
        return CPR;
    }

    public String getResult() {
        return result;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}

