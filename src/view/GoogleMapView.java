package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoogleMapView {

    public GoogleMapView() throws IOException {
        Stage signUpStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("googlemap.fxml"));
        signUpStage.setTitle("Google Map");
        signUpStage.setScene(new Scene(root, 971.0, 555));
        signUpStage.show();
    }
}
