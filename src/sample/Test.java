package sample;

import jakarta.xml.bind.annotation.*;
import java.sql.Date;
import java.sql.Time;

@XmlRootElement
@XmlType(propOrder={"testID", "CPR", "result", "strain", "date", "time"})

public class Test {

    public Test() {
        this.testID = Integer.parseInt(null);
        this.CPR = null;
        this.result = null;
        this.strain = null;
        this.date = null;
        this.time = null;
    }

    private int testID;
    private String CPR;
    private String result;
    private String strain;
    private Date date;
    private Time time;

    public Test(int inputTestID, String inputCPR, String inputResult, String inputStrain, Date inputDate, Time inputTime) {
        this.testID = inputTestID;
        this.CPR = inputCPR;
        this.result = inputResult;
        this.strain = inputStrain;
        this.date = inputDate;
        this.time = inputTime;
    }

    @XmlElement(name = "testID")
    public int getTestID() {
        return testID;
    }

    @XmlElement(name = "CPR")
    public String getCPR() {
        return CPR;
    }

    @XmlElement(name = "result")
    public String getResult() {
        return result;
    }

    @XmlElement(name = "strain")
    public String getStrain() {
        return strain;
    }

    @XmlElement(name = "date")
    public Date getDate() {
        return date;
    }

    @XmlElement(name = "time")
    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testID='" + testID + '\'' + ", CPR=" + CPR + ", result=" + result + ", strain=" + strain +
                ", date=" + date + ", time=" + time + '}';

    }
}

