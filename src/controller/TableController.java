package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import model.UserDB;

import java.util.ArrayList;

public class TableController {
    public TableColumn<User, String> firstNameCol;
    public TableColumn<User, String> lastNameCol;
    public TableColumn<User, String> usernameCol;
    public TableColumn<User, String> addressCol;
    public TableColumn<User, String> cityCol;
    public TableColumn<User, String> stateCol;
    public TableView<User> table;
    public TableColumn<User, Integer> zipCol;
    public ArrayList<User> myArrayList = new ArrayList<>();
    public ObservableList<User> myList;

    @FXML
    public void initialize(){
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));
        cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(
                new PropertyValueFactory<>("state"));
        zipCol.setCellValueFactory(
                new PropertyValueFactory<>("zip"));

        for (User user: UserDB.getUsers1()) {
            myArrayList.add(user);
        }
        myList = FXCollections.observableArrayList(myArrayList);
        table.setItems(myList);
    }
}
