package sample;

import java.sql.Date;

public class Geolocation {
    private int zipCode;
    private Date date;
    private int patient_idPatient;

    public Geolocation(int inputZipCode, Date inputDate, int inputPatient_idPatient) {
        this.zipCode = inputZipCode;
        this.date = inputDate;
        this.patient_idPatient = inputPatient_idPatient;
    }

    public Date getDate() {
        return date;
    }

    public int getPatient_idPatient() {
        return patient_idPatient;
    }

    public int getZipCode() {
        return zipCode;
    }

}
