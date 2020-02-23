// Kari Cathey Final Project - Inventory System
// Software I - C482

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/OutsourcedPart.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 800, 530));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
