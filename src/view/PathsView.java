package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PathsView {

    public PathsView() throws IOException {
        Stage signUpStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("paths.fxml"));
        signUpStage.setTitle("Paths");
        signUpStage.setScene(new Scene(root, 600, 400.0));
        signUpStage.show();
    }
}
