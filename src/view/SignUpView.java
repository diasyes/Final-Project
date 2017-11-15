package view;

import controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpView {

    public SignUpView() throws IOException {
        Stage signUpStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        signUpStage.setTitle("Sign Up");
        signUpStage.setScene(new Scene(root, 428, 909.0));
        signUpStage.show();
    }
}
