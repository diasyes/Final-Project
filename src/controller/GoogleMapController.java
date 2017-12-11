package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DistanceComparator;
import model.User;
import model.UserDB;
import model.WeightedGraph;
import view.PathsView;


public class GoogleMapController implements Initializable, MapComponentInitializedListener {

    public static WeightedGraph<User> myGraph;
    Marker loggedUserMarker = null;
    public Button setButton;
    public TextField radiusText;
    public static double radius = 0;
    public Marker[] markers = new Marker[UserDB.getUsers1().size()];
    public Button findButton;
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    private int done = 1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }
    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(SignInController.loggedInUser.getLatitude(), SignInController.loggedInUser.getLongitude()))
              .mapType(MapTypeIdEnum.ROADMAP)
              .overviewMapControl(false)
              .panControl(false)
              .rotateControl(false)
              .scaleControl(false)
              .streetViewControl(false)
              .zoomControl(false)
              .zoom(4);
        map = mapView.createMap(mapOptions);

        int count = 0;

        for (User user : UserDB.getUsers1()){
            LatLong userLocation = new LatLong(user.getLatitude(), user.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(userLocation);
            Marker userMarker = new Marker(markerOptions);
            map.addMarker(userMarker);
            markers[count] = userMarker;
            if (user.equals(SignInController.loggedInUser)){
                loggedUserMarker = userMarker;
            }
            count++;
        }


        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>You are " + SignInController.loggedInUser.getFirstName() + " " + SignInController.loggedInUser.getLastName() + "</h2>");

        InfoWindow loggedUserWindow = new InfoWindow(infoWindowOptions);
        loggedUserWindow.open(map, loggedUserMarker);
        findButton.setDisable(true);
    }
    public void setRadius() {
        for (int i = 0; i < UserDB.getUsers1().size(); i++) {
            map.addMarker(markers[i]);
            if (!TableController.myList.contains(UserDB.getUsers1().get(i)))
                TableController.myList.add(UserDB.getUsers1().get(i));
        }
        for (int i = 0; i < UserDB.getUsers1().size(); i++) {
            if (UserDB.getUsers1().get(i).getDistance() > Double.parseDouble(radiusText.getText())) {
                map.removeMarker(markers[i]);
                TableController.myList.remove(UserDB.getUsers1().get(i));
                UserDB.getUsersWithinSpecificRadius().remove(UserDB.getUsers1().get(i));
            }
            map.setCenter(new LatLong(SignInController.loggedInUser.getLatitude(), SignInController.loggedInUser.getLongitude()));
            map.setZoom(5);
        }
        for (User user : TableController.myList) {
            if (!UserDB.getUsersWithinSpecificRadius().contains(user)) {
                UserDB.getUsersWithinSpecificRadius().add(user);
                }
        }
        Collections.sort(TableController.myList, new DistanceComparator());
        findButton.setDisable(false);
    }
    public void find() throws IOException{
        ArrayList<User> forPath = new ArrayList<>();
        forPath.addAll(TableController.myList);
        map.setCenter(new LatLong(TableController.myList.get(1).getLatitude(),TableController.myList.get(1).getLongitude()));
        SignInController.loggedInUser = TableController.myList.get(1);
        System.out.println(SignInController.loggedInUser.getUsername());
        TableController.myList.clear();
        System.out.println(forPath.size());
        System.out.println(forPath.get(0).getUsername());
        for (int i = 0; i < forPath.size(); i ++){
            forPath.get(i).setDistance((int)distance(SignInController.loggedInUser.getLatitude(), SignInController.loggedInUser.getLongitude(),forPath.get(i).getLatitude(),forPath.get(i).getLongitude()));
            TableController.myList.add(forPath.get(i));
        }
        TableController.myList.remove(0);
        Collections.sort(TableController.myList, new DistanceComparator());
        done++;
        System.out.println(done);
        System.out.println(UserDB.getUsersWithinSpecificRadius().size());
        if (done < UserDB.getUsersWithinSpecificRadius().size()){
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h2>The #" + (done-1) + " step is to go to " + SignInController.loggedInUser.getFirstName() + " " + SignInController.loggedInUser.getLastName() + "</h2>");
            InfoWindow loggedUserWindow = new InfoWindow(infoWindowOptions);
            LatLong userLocation = new LatLong(TableController.myList.get(0).getLatitude(), TableController.myList.get(0).getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(userLocation);
            Marker userMarker = new Marker(markerOptions);
            map.addMarker(userMarker);
            loggedUserWindow.open(map, userMarker);
        }
        if (done == UserDB.getUsersWithinSpecificRadius().size()){
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h2>You have arrived to " + SignInController.loggedInUser.getFirstName() + " " + SignInController.loggedInUser.getLastName() + "</h2>");
            InfoWindow loggedUserWindow = new InfoWindow(infoWindowOptions);
            LatLong userLocation = new LatLong(TableController.myList.get(0).getLatitude(), TableController.myList.get(0).getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(userLocation);
            Marker userMarker = new Marker(markerOptions);
            map.addMarker(userMarker);
            loggedUserWindow.open(map, userMarker);
        }
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
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