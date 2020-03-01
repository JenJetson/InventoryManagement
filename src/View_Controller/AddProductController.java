package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void clickBtnAdd(ActionEvent actionEvent) {
        //todo

    }

    public void clickBtnSearch(ActionEvent actionEvent) {
        //todo
    }

    public void clickBtnSave(ActionEvent actionEvent) {
        //todo
    }

    public void clickBtnCancel(ActionEvent actionEvent) throws IOException {
        goToMainScreen();
    }

    public void goToMainScreen() throws IOException {
        MainScreenController mainScreenController = new MainScreenController();
        mainScreenController.goToMainStage();
    }


    public void clickBtnDelete(ActionEvent actionEvent) {
        //todo
    }
}
