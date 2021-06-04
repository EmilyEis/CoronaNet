package sample;

import jakarta.xml.bind.annotation.*;
import java.sql.Date;
import java.sql.Time;

@XmlRootElement
@XmlType(propOrder={"testID", "result", "strain", "date", "time"})

public class Test {

    public Test() {
        this.testID = Integer.parseInt(null);
        this.result = null;
        this.strain = null;
        this.date = null;
        this.time = null;
    }

    private int testID;
    private String result;
    private String strain;
    private Date date;
    private Time time;
    private int patient_idPatient;
    private int hp_idHP;
    private int virus_idVirus;

    public Test(int inputTestID, String inputResult, String inputStrain, Date inputDate, Time inputTime,
                int inputPatient_idPatient, int inputHp_idHP, int inputVirus_idVirus) {
        this.testID = inputTestID;
        this.result = inputResult;
        this.strain = inputStrain;
        this.date = inputDate;
        this.time = inputTime;
        this.patient_idPatient = inputPatient_idPatient;
        this.hp_idHP = inputHp_idHP;
        this.virus_idVirus = inputVirus_idVirus;
    }

    public int getPatient_idPatient() {
        return patient_idPatient;
    }

    public int getHp_idHP() {
        return hp_idHP;
    }

    public int getVirus_idVirus() {
        return virus_idVirus;
    }

    @XmlElement(name = "testID")
    public int getTestID() {
        return testID;
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
                "testID='" + testID + '\'' + ", result=" + result + ", strain=" + strain +
                ", date=" + date + ", time=" + time + '}';

    }
}

