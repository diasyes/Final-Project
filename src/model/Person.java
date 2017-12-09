package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Person implements Serializable{
    private String firstName;
    private String lastName;
    private int SSN;
    private Date dob;
    private String gender;
    private String address;
    private String city;
    private String state;
    private int zip;
    private double latitude;
    private double longitude;
    private int distance;

    public Person() {
    }

    public Person(String firstName, String lastName, int SSN, Date dob, String gender, String address, String city, String state, int zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
