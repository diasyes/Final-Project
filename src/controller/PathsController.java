package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.Set;

public class PathsController {

    public ListView listView;
    private Set<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();

    public void initialize(){
        listView.getItems().add("Hello");
    }
}
