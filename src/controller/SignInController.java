package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.UserDB;
import view.SignUpView;
import view.TableView;

import java.io.IOException;

public class SignInController {

    public PasswordField password;
    public Button signIn;
    public Hyperlink toSignUp;
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField username;

    public void openSignUpView(ActionEvent event)throws IOException {
        Stage stage = (Stage)signIn.getScene().getWindow();
        stage.close();
        new SignUpView();
    }

    public void authorize() throws IOException{
        for (int i = 0; i < UserDB.getUsers1().size(); i++){
            if (username.getText().equals(UserDB.getUsers1().get(i).getUsername()) && password.getText().equals(UserDB.getUsers1().get(i).getPassword())){
                usernameLabel.setText("Welcome " + username.getText() + "!");
                passwordLabel.setText("You have successfully logged in!");
                Stage stage = (Stage)signIn.getScene().getWindow();
                stage.close();
                new TableView();
                break;
            }
            else{
                usernameLabel.setText("");
                passwordLabel.setText("username or password does not exist");
            }
        }
    }
    public void enterButtonPressed(javafx.scene.input.KeyEvent event) throws IOException{
        switch (event.getCode()){
            case ENTER:
                authorize();
        }
    }
}
