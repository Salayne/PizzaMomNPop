package com.example.momnpop;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class checkOutController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    double totalPrice = 0.0;
    @FXML
    TextFlow textFlow;
    @FXML
    AnchorPane anchorPane;
    String pizzaTotalPriceString="";
    String drinkTotalPriceString="";
    double drinkTotalPrice;
    double pizzaTotalPrice;
    int i=0;
    public checkOutController()
    {

    }


    @FXML
    private void switchToPopandMom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pop&MomPizza.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public String getContentOfFile( String textFile)// Get the contents of the text files that contain
            //orders. Then print them out on screen
    {   String price="";
        String fileName =textFile;
        try {
            Path filePath = Paths.get(fileName);
            if(Files.exists((filePath)))
            {
            List<String> allLines = Files.readAllLines(filePath);
                if (!allLines.isEmpty())
                {

                    for(int i=0; i<allLines.size()-2;i++)

                    {

                        textFlow.getChildren().add( new Text(allLines.get(i)+"\n"));
                    }
                    price=allLines.get(allLines.size()-1);

                }

            }
            else
            {
                System.out.println("File not found "+ fileName);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return price;
    }

    public void readOrder()// Read all order from text Files
    {   if(i==0)// so customer is allowed to click the "see your orders button 1 time).
      { greyGradient();

        textFlow.setStyle("-fx-font-size: 15;");
        textFlow.getChildren().add(new Text(" \n"));
        // get contents of pizza orders from pizzaOrder textFile, then assign the last line of the file that contains
        //the total price of drink to pizzaTotalPrice.
        pizzaTotalPriceString=getContentOfFile("pizzaOrder.txt");
        drinkTotalPriceString=getContentOfFile("drinkOrder.txt");// get contents of pizza orders from pizzaOrder textFile
        convertStringToDouble();}
        i++;

    }
    public void placeOrder(ActionEvent event) throws IOException
    {    clearTextFiles();
        // Reset anchorPane to default.
        anchorPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(null, null, null)));
        i=0;

    }

    public void cancelOrder()
    {
        clearTextFiles();
        textFlow.getChildren().add( new Text(" You just canceled your orders"+"\n"));
    }

    public void clearTextFiles()
    {
        try {   // clear the pizzaOrder text File.
                    FileWriter writer = new FileWriter ("pizzaOrder.txt");
                    writer.write("");
                    writer.close();
        } catch (IOException e) {
                    e.printStackTrace();
                }
        try {   // clear the drinkOrder text File.
        FileWriter writer = new FileWriter ("drinkOrder.txt");
        writer.write("");
        writer.close();
        } catch (IOException e) {
        e.printStackTrace();
    }
        textFlow.getChildren().clear();// clear the "screen" flowText.
        // Reset anchorPane to default.



    }
    public double extractNumberFromString(String totalPrice)// Read number from the file
    { double price=0.0;
        Scanner scanner = new Scanner(totalPrice);
        while(scanner.hasNext())
        {
            if (scanner.hasNextDouble())
            {
                price= scanner.nextDouble();
            }
            else {
                scanner.next();
            }

        }
        scanner.close();
        return price;

    }
    public void convertStringToDouble() // convert drink price in the form of String to double price
    {
        drinkTotalPrice = extractNumberFromString(drinkTotalPriceString);
        pizzaTotalPrice=extractNumberFromString(pizzaTotalPriceString);
        totalPrice=drinkTotalPrice+pizzaTotalPrice;
        textFlow.getChildren().add(new Text("----------------------------------------------------------"+"\n"));
        textFlow.getChildren().add(new Text("Total Price: $"+totalPrice));
    }




    private void greyGradient() {
        Stop[] stop = new Stop[]
                {
                        new Stop(0, Color.web("#DDDDDD")),
                        new Stop(1, Color.web("#EEEEEE"))
                };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null, stop);
        anchorPane.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(gradient, null, null)));
    }



}
