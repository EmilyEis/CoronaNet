package sample;

public class HP {

    // Setting attributes for the healthcare personnel.
    // Clinic refers to a number that is unique to said doctor's clinic.
    private String clinic;
    private String user;

    public HP (String inputClinic, String inputUser) {
        this.clinic = inputClinic;
        this.user = inputUser;
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




}
