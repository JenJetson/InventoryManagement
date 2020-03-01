package View_Controller;

import Kari_Cathey.Main;
import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.addPart;
import static Model.Inventory.partsList;

public class ModifyPartController {
    Part selected;
    int index;

    @FXML
    RadioButton radioInHouse;
    @FXML
    RadioButton radioOutsourced;
    @FXML
    Label modifyPartIdField;
    @FXML
    TextField modifyPartNameField;
    @FXML
    TextField modifyPartInvField;
    @FXML
    TextField modifyPartPriceField;
    @FXML
    TextField modifyPartMaxField;
    @FXML
    TextField modifyPartMinField;
    @FXML
    Label modifyPartMachineId;
    @FXML
    TextField modifyPartMachineIdField;
    @FXML
    Button btnSave;
    @FXML
    Button btnCancel;


    public ModifyPartController() {

    }

    public void setModifyPartIdField(String field) {
        this.modifyPartIdField.setText(field);
    }

    public void setModifyPartNameField(String modifyPartNameField) {
        this.modifyPartNameField.setText(modifyPartNameField);
    }

    public void setModifyPartInvField(TextField modifyPartInvField) {
        this.modifyPartInvField = modifyPartInvField;
    }

    public void setModifyPartPriceField(TextField modifyPartPriceField) {
        this.modifyPartPriceField = modifyPartPriceField;
    }

    public void setModifyPartMaxField(TextField modifyPartMaxField) {
        this.modifyPartMaxField = modifyPartMaxField;
    }

    public void setModifyPartMinField(TextField modifyPartMinField) {
        this.modifyPartMinField = modifyPartMinField;
    }

    public void setModifyPartMachineId(Label modifyPartMachineId) {
        this.modifyPartMachineId = modifyPartMachineId;
    }

    public void setModifyPartMachineIdField(TextField modifyPartMachineIdField) {
        this.modifyPartMachineIdField = modifyPartMachineIdField;
    }


    public void setPart(Part part, int index) {  //have to cast to get to the machine id/company

        selected = part;  //sets the selected part so it can be saved on clickBtnSave
        index = index;    //for use on save

        if (part instanceof InhousePart) {
            InhousePart modifyPart = (InhousePart) part;
            System.out.println("inhouse part initiated"); //fixme test data
            this.radioInHouse.setSelected(true);
            modifyPartMachineId.setText("Machine ID");
            this.modifyPartIdField.setText(Integer.toString(part.getId()));
            this.modifyPartNameField.setText(modifyPart.getName());
            this.modifyPartInvField.setText(Integer.toString(modifyPart.getStock()));
            this.modifyPartPriceField.setText(Double.toString(modifyPart.getPrice()));
            this.modifyPartMinField.setText(Integer.toString(modifyPart.getMin()));
            this.modifyPartMaxField.setText(Integer.toString(modifyPart.getMax()));
            this.modifyPartMachineIdField.setText(Integer.toString(modifyPart.getMachineId()));
        }
        if (part instanceof OutsourcedPart) {  //have to cast to get to the machine id/company
            OutsourcedPart modifyOutPart = (OutsourcedPart) part;
            radioOutsourced.setSelected(true);
            modifyPartMachineId.setText("Company Name");
            this.modifyPartIdField.setText(Integer.toString(part.getId()));
            this.modifyPartNameField.setText(modifyOutPart.getName());
            this.modifyPartInvField.setText(Integer.toString(modifyOutPart.getStock()));
            this.modifyPartPriceField.setText(Double.toString(modifyOutPart.getPrice()));
            this.modifyPartMinField.setText(Integer.toString(modifyOutPart.getMin()));
            this.modifyPartMaxField.setText(Integer.toString(modifyOutPart.getMax()));
            this.modifyPartMachineIdField.setText(modifyOutPart.getCompanyName());
        }
    }

    @FXML
    public void clickRadioInHouse() {
        radioOutsourced.setSelected(false);  //make radio buttons toggle correctly
        radioInHouse.setSelected(true);
        modifyPartMachineId.setText("Machine ID");
    }

    @FXML
    public void clickRadioOutsourced() {
        radioInHouse.setSelected(false);
        radioOutsourced.setSelected(true);
        modifyPartMachineId.setText("Company Name");
    }

    @FXML
    public void clickSave() throws IOException {
        //Assign variables so it's more legible
        Double price = Double.valueOf(modifyPartPriceField.getText());
        int id = selected.getId();
        int index = id - 1; //the actual index is the id minus 1
        String name = modifyPartNameField.getText();
        int inv = Integer.valueOf(modifyPartInvField.getText());
        int min = Integer.valueOf(modifyPartMinField.getText());
        int max = Integer.valueOf(modifyPartMaxField.getText());
       // partsList.remove(id-1); //the id is one off the index
        ///write it where it creates a new one with the same ID and removes the old one.
        if (radioInHouse.isSelected() == true){
            int machId = Integer.valueOf(modifyPartMachineIdField.getText());
            InhousePart modifyIn = new InhousePart(id,name,price, inv, min, max, machId);
            Inventory.getAllParts().set(index, modifyIn);
        } else if (radioOutsourced.isSelected() == true) {
            System.out.println("fired test outsourced");
            String company = modifyPartMachineIdField.getText();
            OutsourcedPart modifyOut = new OutsourcedPart(id,name,price, inv, min, max, company);
            Inventory.getAllParts().set(index, modifyOut);
        }
        goToMainStage();
    }

    @FXML
    public void goToMainStage() throws IOException {
        Main main = new Main();
        Stage primaryStage = main.getPrimaryStage();
        Parent window = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(window));
        primaryStage.show();
    }

    @FXML
    public void clickCancel() throws IOException {
        goToMainStage();
    }
}
