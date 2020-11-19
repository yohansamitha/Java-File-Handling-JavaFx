package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerDTO;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable{
    private final String filePath = "C:\\Users\\Public\\FileHandlingThogakade\\Customer\\";
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public TableView<CustomerDTO> tblCustomerDetail;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public AnchorPane root;
    ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
    private String fileName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            colCustomerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            fileCheck();
            loadAllCustomers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomers() throws IOException, ClassNotFoundException {
//        File file = new File(filePath);
//        int fileCount = Objects.requireNonNull(new File(filePath).list(), "load all Customer").length;
        String[] list = new File(filePath).list();
        allCustomer = new ArrayList<>();
//        for (int i = 1; i <= fileCount; i++) {
        for (String fileName : list) {
            FileInputStream inputStream = new FileInputStream(filePath + fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            CustomerDTO c1 = (CustomerDTO) objectInputStream.readObject();
            CustomerDTO customer = new CustomerDTO(fileName, c1.getID(), c1.getName(), c1.getAddress(), c1.getSalary());
            allCustomer.add(customer);
            System.out.println(customer.toString());
//            allCustomer.add(c1);
            inputStream.close();
            objectInputStream.close();
        }
        tblCustomerDetail.setItems(FXCollections.observableArrayList(allCustomer));
    }

    private void fileCheck() throws IOException {
        File file = new File(filePath);
        System.out.println(file.exists() + " file check");
        if (!file.exists()) {
            file.mkdir();
            System.out.println(file.exists() + " file check");
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        String id = txtCustomerID.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
        System.out.println("qqqq");
        File file = new File(filePath);
        int fileCount = file.list().length;
        System.out.println(fileCount);
        if (fileCount == 0) {
            createFile(customerDTO, id);
        } else {
            createFile(customerDTO, id);
        }
        try {
            loadAllCustomers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFile(CustomerDTO customerDTO, String fileCount) {
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath + "customer " + fileCount + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(customerDTO);
            objectOutputStream.close();
            outputStream.close();
            System.out.println(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        String id = txtCustomerID.getText();
//        File file = new File(filePath)
        File file = new File(filePath + fileName);
        System.out.println(filePath + fileName + " fuck");
        if (file.delete()) {
            JOptionPane.showMessageDialog(null, "Customer Deleted successfully!");
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Something went Wrong!");
            System.out.println("I cannot find '" + file + "' ('" + file.getAbsolutePath() + "')");
            clear();
        }
        try {
            loadAllCustomers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String id = txtCustomerID.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);

        try {
            FileOutputStream file_out_stm = new FileOutputStream(filePath+fileName);
            ObjectOutputStream obj_out_stm = new ObjectOutputStream(file_out_stm);
            obj_out_stm.writeObject(customerDTO);
            obj_out_stm.flush();
            file_out_stm.close();
            obj_out_stm.close();
            clear();
            loadAllCustomers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSearch(ActionEvent actionEvent) {

    }

    public void btnClear(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"))));
        stage.show();
    }

    public void tblOnMouseRelease(MouseEvent mouseEvent) {
        fileName = "";
        CustomerDTO selectedItem = tblCustomerDetail.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fileName = selectedItem.getFileID();
        System.out.println(fileName + " file name");
        txtCustomerID.setText(selectedItem.getID());
        txtCustomerName.setText(selectedItem.getName());
        txtAddress.setText(selectedItem.getAddress());
        txtSalary.setText(String.valueOf(selectedItem.getSalary()));

    }
}
