package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Product addProduct;
    Part selectedPart;
    Product newProduct;
    int index = Inventory.productsList.size();
    int id = Inventory.getProductId();

    @FXML
    Button btnCancel;
    @FXML
    Label productId;
    @FXML
    TextField addName;
    @FXML
    TextField addInv;
    @FXML
    TextField addPrice;
    @FXML
    TextField addMin;
    @FXML
    TextField addMax;
    @FXML
    Button btnSearch;
    @FXML
    TextField searchField;
    @FXML
    TableView tableAssociatedParts;
    @FXML
    Button btnAdd;
    @FXML
    TableView tableParts;
    @FXML
    Button btnDelete;
    @FXML
    Button btnSave;
    @FXML
    TableColumn partIdColumn;
    @FXML
    TableColumn partNameColumn;
    @FXML
    TableColumn partInvColumn;
    @FXML
    TableColumn partPriceColumn;
    @FXML
    TableColumn associatedIdColumn;
    @FXML
    TableColumn associatedNameColumn;
    @FXML
    TableColumn associatedInvColumn;
    @FXML
    TableColumn associatedPriceColumn;

    ObservableList<Part> associatedPartsList;


    public AddProductController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getPartsTable();
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id")); //'id' is the actual variable name from part class
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productId.setText(Integer.toString(id));


        newProduct = new Product(0, null, 0.0, 0, 0, 0);

        getAssociatedPartsTable();
        associatedIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        id++;
    }

    public void getAssociatedPartsTable() {
        tableAssociatedParts.setItems(newProduct.getAllAssociatedParts());
    }

    @FXML
    public void clickBtnAdd() {
        selectedPart = (Part) tableParts.getSelectionModel().getSelectedItem();   //fixme not sure why not working
        newProduct.addAssociatedPart(selectedPart);        //is it because you don't have proper controller?


    }

    public void getPartsTable() {  //get all parts
        tableParts.setItems(Inventory.getAllParts());  //gets all parts
    }

    @FXML //for when you push enter when nothing there so it refreshes
    public void enterBtnSearch(ActionEvent event) {
        if (Objects.equals(searchField.getText(), "")) { //checks for an empty field
            getPartsTable();  //calls method to refill parts table
        } else
            clickBtnSearch(event);  //call the search method
        searchField.clear();  //clean the text field
    }

    @FXML
    public void clickBtnSearch(ActionEvent actionEvent) {
        String search = searchField.getText();  //capture the text in the search field
        ObservableList<Part> partMatches = FXCollections.observableArrayList();
        for (Part part : Inventory.getAllParts()) {  //call the getAllParts method
            if (Objects.equals(Integer.toString(part.getId()), search.trim()) ||
                    Objects.equals(part.getName().toLowerCase(), search.toLowerCase().trim())) {
                partMatches.add(part);
            }
        }
        tableParts.setItems(partMatches); //update window (tableParts) with matches
        searchField.clear();  //clean the text field
    }


    @FXML
    public void clickBtnSave(ActionEvent actionEvent) throws IOException {
        id = Integer.valueOf(productId.getText());
        String name = addName.getText();
        int inv = Integer.valueOf(addInv.getText());
        Double price = Double.valueOf(addPrice.getText());
        int min = Integer.valueOf(addMin.getText());
        int max = Integer.valueOf(addMax.getText());

        Product updatedProduct = new Product(id, name, price, inv, min, max);
        Inventory.addProduct(updatedProduct);
        associatedPartsList = tableAssociatedParts.getItems(); //this gets the entire table vs. selected items (like other usage here)
        for (Part part : associatedPartsList) { //go through list of parts on table and add to AssociatedParts list
            updatedProduct.addAssociatedPart(part);
        }
        index++;
        goToMainScreen();
    }

    @FXML
    public void clickBtnDelete(ActionEvent actionEvent) {
        //todo
    }

    public void goToMainScreen() throws IOException {
        MainScreenController mainScreenController = new MainScreenController();
        mainScreenController.goToMainStage();
    }

    @FXML
    public void clickBtnCancel(ActionEvent actionEvent) throws IOException {
        goToMainScreen();
    }

}
