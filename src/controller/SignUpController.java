package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import javafx.scene.control.*;
import model.UserDB;
import model.UserIO;
import view.SignInView;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    //creating variables from fxml
    //text fields, etc
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField email;
    public PasswordField password;
    public PasswordField confirmPassword;
    public TextField number;
    public TextField dob;
    public TextField ssn;
    public ChoiceBox<String> gender;
    public TextField address;
    public TextField city;
    public TextField state;
    public TextField zip;
    //labels
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label usernameLabel;
    public Label emailLabel;
    public Label passwordLabel;
    public Label confirmPasswordLabel;
    public Label phoneNumberLabel;
    public Label dobLabel;
    public Label ssnLabel;
    public Label genderLabel;
    public Label addressLabel;
    public Label cityLabel;
    public Label stateLabel;
    public Label zipLabel;
    //buttons
    public Button signUpButton;
    public Button uploadButton;

    //creating variables for the actions
    private String ssnText;
    private String userPhone;
    private String firstNameText;
    private String lastNameText;
    private String usernameText;
    private String emailText;
    private String passwordText;
    private String confirmPasswordText;
    private String numberText;
    private String dobText;
    private String genderText;
    private User myUser;
    private String photoURL = "favourite.jpg";
    private Date birthday;
    private String addressText;
    private String cityText;
    private String stateText;
    private String zipText;

    //creating patterns to check the interior of the string
    private Pattern noSpecialCharacters = Pattern.compile("[^a-zA-Z0-9]");
    private Pattern noNumbersOrSpecialCharacters = Pattern.compile("[^A-Za-z_]+( [a-zA-Z_]+)*$");
    private Pattern passwordUpperCase = Pattern.compile("[^A-Z]");
    private Pattern passwordNumber = Pattern.compile("[^0-9]");
    private Pattern passwordSpecial = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

    //method which is checking whether
    public void validSignUp(){
        //first name
        try {
            firstNameText = firstName.getText();
            Matcher m = noNumbersOrSpecialCharacters.matcher(firstNameText);
            boolean b = m.find();
            if (b || firstNameText.equals("")){
                firstNameLabel.setText("Wrong First Name");
                firstNameText = null;
            }
        } catch (Exception e) {
            firstNameLabel.setText("Wrong First Name");
        }
        //last name
        try {
            lastNameText = lastName.getText();
            Matcher m = noNumbersOrSpecialCharacters.matcher(lastNameText);
            boolean b = m.find();
            if (b || lastNameText.equals("")) {
                lastNameLabel.setText("Wrong First Name");
                lastNameText = null;
            }
        } catch (Exception e) {
            lastNameLabel.setText("Wrong Last Name");
        }

        //username
        try {
            usernameText = username.getText();
            Matcher m = noSpecialCharacters.matcher(usernameText);
            boolean b = m.find();
            if (b || usernameText.equals("")) {
                usernameLabel.setText("Wrong username");
                usernameText = null;
            }
        } catch (Exception e) {
            usernameLabel.setText("Wrong username");
        }

        //email
        try {
            emailText = email.getText();
            if(!emailText.contains("@") || !emailText.contains(".")){
                emailLabel.setText("Wrong email");
                emailText = "xxxx@email.xxx";
            }
            else{
                emailLabel.setText("");
            }
            if (emailText.equals("")){
                emailLabel.setText("");
            }
        }catch (Exception e){
            emailLabel.setText("Wrong email");
        }

        //password
        try {
            passwordText = password.getText();
            Matcher pwuc = passwordUpperCase.matcher(passwordText);
            boolean pwucb = pwuc.find();
            Matcher pwn = passwordNumber.matcher(passwordText);
            boolean pwnb = pwn.find();
            Matcher pws = passwordSpecial.matcher(passwordText);
            boolean pwsb = pws.find();
            if (!pwucb && !pwnb && !pwsb){
                passwordLabel.setText("Wrong password");
                passwordText = null;
            }
        }catch (Exception e){
            passwordLabel.setText("Wrong password");
        }

        //confirm password
        try {
            confirmPasswordText = confirmPassword.getText();
            if (!confirmPasswordText.equals(passwordText)){
                confirmPasswordText = null;
                confirmPasswordLabel.setText("Passwords don't match!");
            }
        }catch (Exception e){
            confirmPasswordLabel.setText("Not same passwords");
        }

        //number
        try {
            numberText = number.getText();
            Matcher m = passwordNumber.matcher(numberText);
            boolean b = m.find();
            if (b){
                phoneNumberLabel.setText("Wrong Phone number");
                numberText = "00000000000";
                }
        }catch (Exception e){
            phoneNumberLabel.setText("Wrong Phone number");
            numberText = "00000000000";
            }

        //dob
        try {
            dobText = dob.getText();
            SimpleDateFormat sdfBirthday = new SimpleDateFormat("MM/dd/yyyy");
            birthday = sdfBirthday.parse(dobText);
        }catch (Exception e){
            dobLabel.setText("Wrong birthday");
            dobText = null;
        }

        //ssn
        try{
            ssnText = ssn.getText();
            Matcher m = passwordNumber.matcher(ssnText);
            boolean b = m.find();
            if (b) {
                ssnLabel.setText("Wrong SSN");
                ssnText = "0000000";
            }
            if (ssnText.equals("")){
                ssnText = "0000000";
            }
        }catch (Exception e){
            ssnLabel.setText("Wrong SSN");
        }

        //gender
        try {
            genderText = gender.getValue();
        }catch (Exception e){
            genderLabel.setText("Wrong gender");
        }

        //address
        try {
            addressText = address.getText();
            if (addressText.equals("")){
                addressLabel.setText("Wrong address");
                addressText = null;
            }
        }catch (Exception e){
            addressLabel.setText("Wrong address");
            addressText = null;
        }

        //city
        try {
            cityText = city.getText();
            Matcher m = noNumbersOrSpecialCharacters.matcher(cityText);
            boolean b = m.find();
            if (cityText.equals("") || b){
                cityLabel.setText("Wrong city");
                cityText = null;
            }
        }catch (Exception e){
            cityLabel.setText("Wrong city");
            cityText = null;
        }

        //state
        try {
            stateText = state.getText();
            Matcher m = noNumbersOrSpecialCharacters.matcher(stateText);
            boolean b = m.find();
            if (stateText.equals("") || b){
                stateLabel.setText("Wrong state");
                stateText = null;
            }
        }catch (Exception e){
            stateLabel.setText("Wrong state");
            stateText = null;
        }

        //zip
        try {
            zipText = zip.getText();
            Matcher m = passwordNumber.matcher(zipText);
            boolean b = m.find();
            if (zipText.equals("") || b) {
                zipLabel.setText("Wrong zip code");
                zipText = null;
            }
        }catch (Exception e){
            zipLabel.setText("Wrong zip code");
        }

        //if not all required fields are entered
        if (firstNameText == null || lastNameText == null || dobText == null || usernameText == null || passwordText == null || confirmPasswordText == null || genderText == null || addressText == null  || cityText == null  || stateText == null  || zipText == null ){
            genderLabel.setText("Enter the required fields");
        }

        //clearing the labels if
        if (firstNameText != null){
            firstNameLabel.setText("");
        }
        if (lastNameText != null){
            lastNameLabel.setText("");
        }
        if (usernameText != null){
            usernameLabel.setText("");
        }
        if (dobText != null){
            dobLabel.setText("");
        }
        if (passwordText != null){
            passwordLabel.setText("");
        }
        if (confirmPasswordText != null){
            confirmPasswordLabel.setText("");
        }
        if (addressText != null){
            addressLabel.setText("");
        }
        if (stateText != null){
            stateLabel.setText("");
        }
        if (cityText != null){
            cityLabel.setText("");
        }
        if (zipText != null){
            zipLabel.setText("");
        }
        //checking if username already exists
        for (int i = 0; i< UserDB.getUsers1().size(); i++){
            try {
                if (usernameText.equals(UserDB.getUsers1().get(i).getUsername())){
                    usernameText = null;
                    usernameLabel.setText("Username already exists");
                    break;
                }
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        //checking if everything is valid
        if (firstNameText != null && lastNameText != null && dobText != null && usernameText != null && passwordText != null && confirmPasswordText != null && addressText != null && cityText != null && stateText != null && zipText != null ){
            genderLabel.setText("You have signed up");
            myUser = new User(firstNameText, lastNameText, Integer.parseInt(ssnText), birthday, genderText, usernameText, emailText, numberText, passwordText, photoURL, addressText, cityText, stateText, Integer.parseInt(zipText));
            try {
                UserIO.writeExcel(myUser, UserDB.getUsers1().size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserDB.getUsers1().add(myUser);
            try {
                UserIO.writeUsers(UserDB.getUsers1());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)signUpButton.getScene().getWindow();
            stage.close();
            try {
                new SignInView().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
    }
}
