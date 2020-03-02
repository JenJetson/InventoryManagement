package View_Controller;

import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController {
    Product selectedProduct;
    Product product;
    Part selectedPart;
    int index;

    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TableView<Part> searchPartsTable;
    @FXML
    private TableColumn<Part, Integer> addPartId;
    @FXML
    private TableColumn<Product, String> addPartName;
    @FXML
    private TableColumn<Product, Integer> addPartInv;
    @FXML
    private TableColumn<Product, Double> addPartPrice;
    @FXML
    private TableColumn<Part, Integer> associatedId;
    @FXML
    private TableColumn<Product, String> associatedName;
    @FXML
    private TableColumn<Product, Integer> associatedInv;
    @FXML
    private TableColumn<Product, Double> associatedPrice;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productMin;
    @FXML
    private TextField productMax;
    @FXML
    private Label productID;

    ObservableList<Part> parts;

    public ModifyProductController() {
    }

    /*
    @Override  //when you use this you need the 'Initializable' in the main part of class
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updatePartTable();  //gets all parts
        this.addPartId.setCellValueFactory(new PropertyValueFactory<>("id")); //'id' is the actual variable name from part class
        this.addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.addPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.addPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        product = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartsTable.setItems((ObservableList<Part>) product.getAllAssociatedParts()); //cast is here kari

        associatedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        System.out.println(associatedPartsTable + "parts table");
    }
  */
    public void setProduct(Product product, int index) {
        this.selectedProduct = product;
        this. index = index;

        getPartsTable();  //gets all parts & then bind to the column fields
        addPartId.setCellValueFactory(new PropertyValueFactory<>("id")); //'id' is the actual variable name from part class
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        getAssociatedPartsTable(); //populate the associated parts table and then bind to columns
        associatedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        productID.setText(Integer.toString(product.getId()));  //binds data to input field on left side
        productName.setText(product.getName());
        productInv.setText(Integer.toString(product.getStock()));
        productPrice.setText(Double.toString(product.getPrice()));
        productMax.setText(Integer.toString(product.getMax()));
        productMin.setText(Integer.toString(product.getMin()));

    }

    public void getPartsTable() {  //get all parts
        searchPartsTable.setItems(Inventory.getAllParts());  //gets all parts
    }

    public void getAssociatedPartsTable(){
        associatedPartsTable.setItems(selectedProduct.getAllAssociatedParts());
    }

    public void clickBtnSearch() {
    }

    public void clickBtnAdd() throws IOException{
        Part selectedPart = searchPartsTable.getSelectionModel().getSelectedItem();
        selectedProduct.addAssociatedPart(selectedPart);
    }

    public void clickBtnDelete() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the product?");  //popup confirm window
        alert.setTitle("Delete product?");  //message for window
        Optional<ButtonType> result = alert.showAndWait();  //confirmation window button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ObservableList<Part> allParts, singlePart;
            allParts = associatedPartsTable.getItems();  //gets the entire list of in table
            singlePart = associatedPartsTable.getSelectionModel().getSelectedItems();  //highlighted item assigned as single part
            singlePart.forEach(allParts::remove);  //removes the selected part
        }
    }

    public void clickBtnSave() throws IOException {

        int id = (Integer.valueOf(productID.getText()));  //saves the id field
        int index = id - 1; //records the array index which is ID value minus 1
        String name = productName.getText();  //saves the productName field
        Double price = Double.valueOf(productPrice.getText());  //saves the priceField
        int inv = Integer.valueOf(productInv.getText());  //saves the inv field
        int min = Integer.valueOf(productMin.getText());  //saves the min field
        int max = Integer.valueOf(productMax.getText());  //saves the max field

        Product updatedProduct = new Product(id,name,price,inv, min, max);
        parts = associatedPartsTable.getItems(); //this gets the entire table vs. selected items (like other usage here)
        for (Part part : parts) { //go through list of parts on table and add to AssociatedParts list
            updatedProduct.addAssociatedPart(part);
        }

        Inventory.getAllProducts().set(index, updatedProduct);  //this replaces the object at index with new item

        goToMainScreen();
    }

    public void goToMainScreen() throws IOException {
        MainScreenController mainScreenController = new MainScreenController();
        mainScreenController.goToMainStage();
    }

    public void clickBtnCancel() throws IOException {
        goToMainScreen();
    }

}
