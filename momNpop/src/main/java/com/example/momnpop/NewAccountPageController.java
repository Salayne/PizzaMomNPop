package com.example.momnpop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class NewAccountPageController {

    @FXML private TextField phoneNumberTextField;
    @FXML private PasswordField passwordField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField zipTextField;
    @FXML private Label validNumber;


    @FXML
    protected void signUp(ActionEvent event) throws IOException {
        String phoneNumber = phoneNumberTextField.getText();
        String password = passwordField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String street = streetTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zip = zipTextField.getText();


        if (phoneNumber.length() == 10){

            UserInfo userInfo = new UserInfo(firstName,lastName,street,city,state,zip);
            NewUserModel newUserModel = new NewUserModel();
            newUserModel.saveLoginInformation(phoneNumber,password);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(MomNPopApplication.class.getResource("pizzaWeb.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            validNumber.setVisible(true);

        }

    }


    @FXML
    protected void bkButton(ActionEvent event) throws IOException {


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MomNPopApplication.class.getResource("login-page.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
