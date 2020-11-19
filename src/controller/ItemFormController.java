package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemFormController {

    public JFXTextField txtItemID;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public TableView tblItemDetail;
    public TableColumn colItemID;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public AnchorPane root;

    public void btnAdd(ActionEvent actionEvent) {

    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnSearch(ActionEvent actionEvent) {
    }

    public void btnClear(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"))));
        stage.show();
    }
}
