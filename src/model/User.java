package model;

import java.io.Serializable;
import java.util.Date;


public class User extends Person implements Comparable<User>, Serializable{

    private String username;
    private String email;
    private String phone;
    private String password;
    private String profilePhotoURL;

    public User(String username) {
        super();
        this.username = username;
    }

    public User(String firstName, String lastName, int SSN, Date dob, String gender, String username, String email, String phone, String password, String profilePhotoURL, String address, String city, String state, int zip) {
        super(firstName, lastName, SSN, dob, gender, address, city, state, zip);
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equalsIgnoreCase(user.username);
    }

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}
