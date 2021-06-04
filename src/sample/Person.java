package sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    // Creating fields/attributes for name/cpr
    private int idPerson;
    private String CPR;
    private String firstName;
    private String lastName;

    public Person(int inputIdPerson, String inputCPR, String inputFirstName, String inputLastName) {
        this.idPerson = inputIdPerson;
        this.CPR = inputCPR;
        this.firstName = inputFirstName;
        this.lastName = inputLastName;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) throws Exception {
        this.firstName = PatientRegister.modifyFirstName(newFirstName, (Patient) this).getFirstName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) throws Exception {
        this.lastName = PatientRegister.modifyLastName(newLastName, (Patient) this).getLastName();
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String newCPR) throws Exception {
        this.CPR = PatientRegister.modifyCPR(newCPR, (Patient) this).getCPR();
    }

    // Calculate age
    public int getAge () {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMuu");
        LocalDate today = LocalDate.now();
        LocalDate birthdate = LocalDate.parse(getCPR().substring(0,6), dateFormatter);

        // If the birthdate is after today, it's probably in the 1900's. Subtract 100 years.
        if (birthdate.isAfter(today)) { birthdate = birthdate.minusYears(100); }
        return Period.between(birthdate, today).getYears();
    }

    // If the last number in CPR is even, the person's female. Otherwise he's male.
    public String getGender() {
        String serial = getCPR().substring(7, 10);
        if (Integer.parseInt(serial) % 2 == 0) {
            return "F";
        } else {
            return "M";
        }
    }

}

