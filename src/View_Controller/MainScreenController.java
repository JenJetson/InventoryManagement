package View_Controller;

import Kari_Cathey.Main;
import Model.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private TableView<Part> tableParts;
    @FXML
    private TableView<Product> tableProducts;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInvColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInvColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    @FXML
    private Button btnPartSearch;
    @FXML
    private Button btnProductSearch;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;
    @FXML
    private Button btnPartModify;
    @FXML
    private Button btnPartAdd;
    @FXML
    private Button btnProductAdd;
    @FXML
    private Button btnProductModify;


    private Part modifyPart;
    private int modifyPartIndex;
    private Product modifyProduct;
    private int modifyProductIndex;


    public MainScreenController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load the parts into the table view 'tableParts'
        //example note:  "id" is the part variable name..it's not in the FXML file
        tableParts.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id")); //'id' is the actual variable name from part class
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //load the products into the table view 'tableProducts'.
        //example note:  "id" is the part variable name..it's not in the FXML file
        tableProducts.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void updatePartTableView() {
        tableParts.setItems(Inventory.getAllParts());
    }

    @FXML
    public void updateProductTableView() {
        tableProducts.setItems(Inventory.getAllProducts());
    }



    @FXML
    public void clickPartAdd(ActionEvent e) throws IOException {
        Stage stageAdd;
        Parent root;
        stageAdd = (Stage) btnPartAdd.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "AddPart.fxml"));
        root = loader.load();
        stageAdd.setTitle("Add Part");
        stageAdd.setScene(new Scene(root, 482, 480));
        stageAdd.show();
    }
    @FXML
    public void clickProductAdd(ActionEvent e) throws IOException {
        Stage stageModify;
        Parent root;
        stageModify = (Stage) btnProductAdd.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "AddProduct.fxml"));
        root = loader.load();
        stageModify.setTitle("Add Product");
        stageModify.setScene(new Scene(root, 802, 480));
        stageModify.show();
    }
    @FXML
    void clickPartModify(ActionEvent e) throws IOException {
        //Window switch
        Stage stage;
        Parent root;
        stage = (Stage) btnPartModify.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "ModifyPart.fxml"));
        stage.setTitle("Modify Part");
        root = loader.load();
        ModifyPartController controller = loader.getController();
        Part modifyPart = tableParts.getSelectionModel().getSelectedItem();
        int index = tableParts.getSelectionModel().getSelectedIndex();

        if (modifyPart != null) {
            controller.setPart(modifyPart, index);
        }
        Scene scene = new Scene(root, 482,480);
        Main.mainStage.setScene(scene);
    }

    @FXML
    public void clickProductModify(ActionEvent e) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnProductModify.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "ModifyProduct.fxml"));
        stage.setTitle("Modify Product");
        root = loader.load();
        ModifyProductController controller = loader.getController();
        Product modifyProduct = tableProducts.getSelectionModel().getSelectedItem();
        int index = tableProducts.getSelectionModel().getSelectedIndex();

        if (modifyProduct != null) {
            controller.setProduct(modifyProduct, index);
        }
        Scene scene = new Scene(root, 802,480);
        Main.mainStage.setScene(scene);
    }

    @FXML
    public void clickProductDelete(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the product?");
        alert.setTitle("Delete Product?");  //message for window
        Optional<ButtonType> result = alert.showAndWait();  //confirmation window button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ObservableList<Product> allProducts, singleProduct;
            allProducts = tableProducts.getItems();  //gets the entire list of in table
            singleProduct = tableProducts.getSelectionModel().getSelectedItems();  //highlighted item assigned as single part
            singleProduct.forEach(allProducts::remove);  //removes the selected part
        }
    }

    @FXML
    public void clickPartDelete(ActionEvent e) {  //delete the selected part in the table and confirm choice

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the part?");  //popup confirm window
        alert.setTitle("Delete part?");  //message for window
        Optional<ButtonType> result = alert.showAndWait();  //confirmation window button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ObservableList<Part> allParts, singlePart;
            allParts = tableParts.getItems();  //gets the entire list of in table
            singlePart = tableParts.getSelectionModel().getSelectedItems();  //highlighted item assigned as single part
            singlePart.forEach(allParts::remove);  //removes the selected part
        }
    }
    @FXML
        //this closes the program
    void exitApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void enterPartSearch(ActionEvent event) {
        if (Objects.equals(partSearchField.getText(), "")) { //checks foran empty field
            updatePartTableView();  //calls method to refill parts table
        } else
            clickPartSearch(event);  //call the search method
        partSearchField.clear();  //clean the text field
    }

    @FXML
    public void enterProductSearch(ActionEvent event) {
        if (Objects.equals(productSearchField.getText(), "")) { //checks for an empty field
            updateProductTableView();  //calls method to refill parts table
        } else
            clickProductSearch(event);  //call the search method
        productSearchField.clear();  //clean the text field
    }

    @FXML
    public void clickPartSearch(ActionEvent actionEvent) {
        String search = partSearchField.getText();  //capture the text in the search field
        ObservableList<Part> partMatches = FXCollections.observableArrayList();
        for (Part part : Inventory.getAllParts()) {  //call the getAllParts method
            if (Objects.equals(Integer.toString(part.getId()), search.trim()) ||
                    Objects.equals(part.getName().toLowerCase(), search.toLowerCase().trim())) {
                partMatches.add(part);
            }
        }
        tableParts.setItems(partMatches); //update window (tableParts) with matches
        partSearchField.clear();  //clean the text field
    }

    @FXML
    public void clickProductSearch(ActionEvent actionEvent) {
        String search = productSearchField.getText();  //capture the text in the search field
        ObservableList<Product> productMatches = FXCollections.observableArrayList();
        for (Product product : Inventory.getAllProducts()) {
            if (Objects.equals(Integer.toString(product.getId()), search.trim()) ||
                    Objects.equals(product.getName().toLowerCase(), search.trim())) {
                productMatches.add(product);
            }
        }
        tableProducts.setItems(productMatches); //update window (tableParts) with matches
        productSearchField.clear();  //clean the text field
    }

    @FXML
    public void goToMainStage() throws IOException {
        Main main = new Main();
        Stage primaryStage = main.getPrimaryStage();

        Parent window = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(window, 860, 485));
        primaryStage.show();
    }

}
