package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.User;
import model.UserDB;
import org.json.JSONObject;
import java.io.IOException;
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
    public TableColumn latitudeCol;
    public TableColumn longitudeCol;
    public Button searchButton;
    public Label mainLabel;
    public TableColumn distanceCol;

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
        latitudeCol.setCellValueFactory(
                new PropertyValueFactory<>("latitude"));
        longitudeCol.setCellValueFactory(
                new PropertyValueFactory<>("longitude"));
        distanceCol.setCellValueFactory(
                new PropertyValueFactory<>("distance"));
        for (User user: UserDB.getUsers1()) {
            myArrayList.add(user);
        }
        myList = FXCollections.observableArrayList(myArrayList);
        table.setItems(myList);
        table.setRowFactory(new Callback<TableView<User>, TableRow<User>>(){
            @Override
            public TableRow<User> call(TableView<User> param) {
                final TableRow<User> row = new TableRow<User>() {
                    int value = 0;
                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        //there write your code to stylize row
                        for (int i = 0; i<table.getItems().size(); i++){
                            if (table.getItems().get(i).equals(SignInController.loggedInUser)){
                                value = i;
                            }
                        }
                        for (int i = 0; i < table.getItems().size(); i++){
                            if (getIndex()==i){
                                setStyle("-fx-background-color: white");
                            }
                        }
                        if(getIndex() == value){
                            setStyle("-fx-background-color: gold");
                        }
                    }
                };
                return row;
            }
        });
    }
    public void getLocationValues() throws ApiException, InterruptedException, IOException{
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDH_zgBjQ2F8oMZaTqCOKNzYAbQ9oDjvBw").build();
        for (int i = 0; i < UserDB.getUsers1().size(); i++){
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    UserDB.getUsers1().get(i).getAddress() + " " + UserDB.getUsers1().get(i).getCity() + ", "
            + UserDB.getUsers1().get(i).getState() + ", " + UserDB.getUsers1().get(i).getZip()).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JSONObject obj = new JSONObject(gson.toJson(results[0]));
            JSONObject loc = obj.getJSONObject("geometry").getJSONObject("location");
            System.out.println("lat: " + loc.getDouble("lat") +
                    ", lng: " + loc.getDouble("lng"));
            UserDB.getUsers1().get(i).setLatitude(loc.getDouble("lat"));
            UserDB.getUsers1().get(i).setLongitude(loc.getDouble("lng"));
            UserDB.getUsers1().get(i).setDistance((int)distance(SignInController.loggedInUser.getLatitude(), SignInController.loggedInUser.getLongitude(),loc.getDouble("lat"), loc.getDouble("lng")));
        }
        table.refresh();
        table.getSortOrder().add(distanceCol);
        distanceCol.setSortType(TableColumn.SortType.ASCENDING);
        distanceCol.setSortable(true);
        table.sort();
    }

    //functions for distance
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (Math.round(dist*100))/100;
    }

    //This function converts decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //This function converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
