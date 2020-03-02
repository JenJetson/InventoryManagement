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

    Part selectedPart;
    Product newProduct;
    int index = Inventory.productsList.size() + 1;

    public AddProductController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getPartsTable();
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id")); //'id' is the actual variable name from part class
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //new item so no associated parts to fill into screen
        tableAssociatedParts.setItems(associatedPartsList);
    }


    @FXML
    public void clickBtnAdd() {
        //selectedPart = tableParts.getSelectionModel().getSelectedItem();
        newProduct.addAssociatedPart(selectedPart);
    }

    public void getAssociatedPartsTable() {
        tableAssociatedParts.setItems(newProduct.getAllAssociatedParts());
    }

    @FXML //for when you push enter when nothing there so it refreshes
    public void enterBtnSearch(ActionEvent event) {
        if (Objects.equals(searchField.getText(), "")) { //checks for an empty field
            getPartsTable();  //calls method to refill parts table
        } else
            clickBtnSearch(event);  //call the search method
        searchField.clear();  //clean the text field
    }

    public void getPartsTable() {  //get all parts
        tableParts.setItems(Inventory.getAllParts());  //gets all parts
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
        String name = addName.getText();
        int inv = Integer.valueOf(addInv.getText());
        Double price = Double.valueOf(addPrice.getText());
        int min = Integer.valueOf(addMin.getText());
        int max = Integer.valueOf(addMax.getText());
        newProduct = new Product(index, name, price, inv, min, max);
        Inventory.addProduct(newProduct);
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
