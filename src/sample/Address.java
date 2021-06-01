package sample;

public class Address {

    private String street;
    private int zipCode;
    private String city;

    public Address(String inputStreet, int inputZipCode, String inputCity) {
        this.street = inputStreet;
        this.zipCode = inputZipCode;
        this.city = inputCity;
    }

    // Getters and setters for street, zip code and city
    public String getStreet() {
        return street; }

    public void setStreet(String newStreet) {
        this.street = newStreet; }

    public int getZipCode() {
        return zipCode; }

    public void setZipCode(int newZipCode) {
        this.zipCode = newZipCode; }

    public String getCity() {
        return city; }
        
    public void setCity(String newCity) {
        this.city = newCity; }

}
