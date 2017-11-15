package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TableView {

    public TableView() throws IOException {
        Stage signUpStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        signUpStage.setTitle("Table");
        signUpStage.setScene(new Scene(root, 1107, 751));
        signUpStage.show();
    }
}
