package Kari_Cathey;
// Kari Cathey Final Project - Inventory System
// Software I - C482

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        mainStage = primaryStage;

        //Add sample data for evaluation.
        Part part1 = new InhousePart(1,"Screws",.55,99,10,5000,73);
        Part part2 = new InhousePart(2,"Bolts", 1.22, 222,10,50000,73);
        Part part3 = new OutsourcedPart(3,"Lathe", 3.33,99,10,5000,"Kinc");

        Product product1 = new Product(1, "Screws", 0.55, 55, 5, 5000);
        Product product2 = new Product(2, "Saws", 9.55, 55, 5, 5000);
        Product product3 = new Product(3, "Springs", 0.45, 155, 5, 500);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);



        //initialize the opening mainScreen
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
  }

    private void setPrimaryStage(Stage mainStage){
        Main.mainStage = mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

   public static Stage getPrimaryStage(){
        return mainStage;
   }

}
