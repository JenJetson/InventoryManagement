package View_Controller;

import Kari_Cathey.Main;
import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class AddPartController {
    @FXML
    private Button clickBtnCancel;
    @FXML
    private Button clickBtnSave;
    @FXML
    private Label addPartId;
    @FXML
    private TextField addPartName;
    @FXML
    private TextField addPartPrice;
    @FXML
    private TextField addPartInv;
    @FXML
    private TextField addPartMin;
    @FXML
    private TextField addPartMax;
    @FXML
    private TextField addPartMachIdField;
    @FXML
    private Label addPartMachId;
    @FXML
    private Label addPartCompany;
    @FXML
    private RadioButton radioInHouse;
    @FXML
    private RadioButton radioOutsourced;
    @FXML
    private ToggleGroup partSourceRadioButtons;


    public AddPartController() {
        //must use initialize to have fxml items accessible
    }

    @FXML
    public void initialize() { //@FXML constructor

        addPartId.setText(Integer.toString(Inventory.getIndex()));  //set the ID number
        addPartMachId.setText("Machine ID");  //this is the starting value. don't change
    }

    @FXML
    public void radioHouse(ActionEvent e) {
        if (radioInHouse.isSelected()) {
            addPartMachId.setText("Machine ID");
        }
    }

    @FXML
    public void radioOut(ActionEvent e) {
        if (radioOutsourced.isSelected()) {
            addPartMachId.setText("Company Name");
        }
    }

    @FXML
    public void goToMainStage() throws IOException {
        MainScreenController mainScreenController = new MainScreenController();
        mainScreenController.goToMainStage();
    }

    @FXML
    public void clickCancel() throws IOException {
        goToMainStage();
    }

    @FXML
    public void clickSave() throws IOException {
        //Assign variables so it's more legible
        Double price = Double.valueOf(addPartPrice.getText());
        int id = Inventory.getIndex();  //method that generates the next id number
        String name = addPartName.getText();
        int inv = Integer.valueOf(addPartInv.getText());
        int min = Integer.valueOf(addPartMin.getText());
        int max = Integer.valueOf(addPartMax.getText());

        if (radioInHouse.isSelected() == true) {
            int machId = Integer.valueOf(addPartMachIdField.getText());
            InhousePart newPart = new InhousePart(id, name, price, inv, min, max, machId);
            Inventory.addPart(newPart);
        } else if (radioOutsourced.isSelected() == true) {
            String company = addPartMachIdField.getText();
            OutsourcedPart newPart = new OutsourcedPart(id, name, price, inv, min, max, company);
            Inventory.addPart(newPart);
        }
        goToMainStage();
    }
}
