package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MyLinkedList;
import model.User;
import model.UserDB;
import model.UserIO;

public class SignInView extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadUserDB();
        Parent root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(new Scene(root, 356.0, 245.0));
        primaryStage.show();
    }

    private void loadUserDB(){
        try {
            UserDB.setUsers1((MyLinkedList<User>)UserIO.readUsers());
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
