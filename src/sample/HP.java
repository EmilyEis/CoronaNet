package sample;

public class HP extends Person {

    // Setting attributes for the healthcare personnel.
    // Clinic refers to a number that is unique to said doctor's clinic.
    private String clinic;
    private String user;
    private int idHP;

    public HP (int inputIdPerson, String inputCPR, String inputFirstName, String inputLastName,  String inputClinic, String inputUser, int inputIdHP) {
        super(inputIdPerson, inputCPR, inputFirstName, inputLastName);
        this.clinic = inputClinic;
        this.user = inputUser;
        this.idHP = inputIdHP;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String newClinic) {
        this.clinic = newClinic;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String newUser) {
        this.user = newUser;
    }

    public int getIdHP() {
        return idHP;
    }



}
