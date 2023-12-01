package com.example.momnpop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.SplittableRandom;

public class LoginPageController {

    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalid;







   @FXML
    protected void login(ActionEvent event) throws IOException {

       if (authentication(phoneNumberTextField.getText(),passwordField.getText())){
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

           FXMLLoader fxmlLoader = new FXMLLoader(MomNPopApplication.class.getResource("pizzaWeb.fxml"));
           Parent root = fxmlLoader.load();

           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
       else{
           invalid.setVisible(true);
       }



   }


    @FXML
    protected void signUp(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MomNPopApplication.class.getResource("new-account-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public boolean authentication(String enteredNumber, String enteredPassword) throws IOException{
       return checkCredentials(enteredNumber,enteredPassword);
    }


    public boolean checkCredentials(String phoneNum, String passwd) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("login_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");

            if (line.contains("Phone Number: " + phoneNum) && line.contains("Password: " + passwd)) {
                br.close();
                return true;
            }
        }

        br.close();
        return false;

    }

    @FXML
    protected void backButton(ActionEvent event)throws IOException{

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MomNPopApplication.class.getResource("Pop&MomPizza.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }







}
