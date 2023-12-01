package com.example.momnpop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class pizzaWebController extends PizzaOrder
{
    ArrayList<String> selectedToppings = new ArrayList<>();
    String sizeChoice = " ", crustChoice = "Regular Crust ", baseChoice = "No Base ";
    double toppingPrice=0.0;
    double sizePrice=0.0;
    double crustPrice=0.0;
    double basePrice=0.0;
    double totalPrice=0.0;

    @FXML
    AnchorPane anchorPane;
    @FXML
    TextFlow textFlow;
    @FXML
    private CheckBox tcheckBox1, tcheckBox2, tcheckBox3, tcheckBox4, tcheckBox5, tcheckBox6, tcheckBox7, tcheckBox8, tcheckBox9;
    CheckBox[] checkBoxes = new CheckBox[9];
    @FXML
    private RadioButton crustRadioButton1, crustRadioButton2, crustRadioButton3, crustRadioButton4, crustRadioButton5,
            crustRadioButton6;

    @FXML
    private RadioButton baseRadioButton1, baseRadioButton2, baseRadioButton3;
    @FXML
    private RadioButton sizeRadioButton1, sizeRadioButton2, sizeRadioButton3,sizeRadioButton4;
    @FXML
    private Button addToCart;
    private static final int MAX_SELECTED_CHECKBOXES = 4;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double pizzaPrice=0.0;
    ArrayList<PizzaOrder> pizzaOrderList = new ArrayList<>();
    private static final String path = "pizzaOrder.txt";

    public void switchToPopandMom(ActionEvent event) throws IOException// switch to main page"pop and Mom page"
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pop&MomPizza.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();





    }


    @FXML
    public void initialize() {
        checkBoxes = new CheckBox[]{tcheckBox1, tcheckBox2, tcheckBox3, tcheckBox4, tcheckBox5, tcheckBox6, tcheckBox7, tcheckBox8, tcheckBox9};
    }




    public void toppingsSelection(ActionEvent event) throws IOException// topping selections
    {
        int count = 0;

        for (CheckBox checkBox : Arrays.asList(tcheckBox1, tcheckBox2, tcheckBox3, tcheckBox4, tcheckBox5, tcheckBox6, tcheckBox7, tcheckBox8, tcheckBox9)) {
            if (checkBox.isSelected() && count <=3)
            {
                selectedToppings.add(checkBox.getText());
                count++;
            }
            else {
                // Unselect checkboxes if count exceeds 4
                checkBox.setSelected(false);
            }
        }

            selectedToppings.clear();
        List<CheckBox> checkBoxList = Arrays.asList(tcheckBox1, tcheckBox2, tcheckBox3, tcheckBox4, tcheckBox5, tcheckBox6, tcheckBox7, tcheckBox8, tcheckBox9);
        toppingPrice=0.0;
        for (int i = 0; i < checkBoxList.size(); i++)
        {CheckBox checkBox = checkBoxList.get(i);

            if (checkBox.isSelected())
            { if (checkBox == tcheckBox4||checkBox==tcheckBox5||checkBox==tcheckBox6)
            {
                toppingPrice+=0.99;
            }
            else {
                toppingPrice+=0.49;
            }
                selectedToppings.add(checkBox.getText());
            }
        }

        }




    public void crustSelection(ActionEvent event) throws IOException {// assign the crust choice selected to string crustChoice

        crustPrice=0;
        if (crustRadioButton1.isSelected()) {
            crustChoice = crustRadioButton1.getText();
        } else if (crustRadioButton2.isSelected()) {
            crustChoice = crustRadioButton2.getText();
        } else if (crustRadioButton3.isSelected()) {
            crustChoice = crustRadioButton3.getText();
        } else if (crustRadioButton4.isSelected()) {
            crustChoice = crustRadioButton4.getText();
            crustPrice+=0.49;
        } else if (crustRadioButton5.isSelected()) {
            crustChoice = crustRadioButton5.getText();
            crustPrice+=0.49;

        } else if (crustRadioButton6.isSelected()) {
            crustChoice = crustRadioButton6.getText();
            crustPrice = 0.49;
        }
    }


    public void sizeSelection(ActionEvent event) throws IOException // method for size seletion of pizzas
    {

        sizePrice=0.0;
        if (sizeRadioButton1.isSelected()) {
            sizePrice+=5.99;
            sizeChoice = sizeRadioButton1.getText();
        } else if (sizeRadioButton2.isSelected()) {
            sizeChoice = sizeRadioButton2.getText();
            sizePrice+=8.99;
        } else if (sizeRadioButton3.isSelected()) {
            sizeChoice = sizeRadioButton3.getText();
            sizePrice+=10.99;
        }
        else if(sizeRadioButton4.isSelected())
        {
            sizePrice+=12.99;
            sizeChoice= sizeRadioButton4.getText();
        }
        System.out.println("size Choice: "+sizeRadioButton4);

    }

    public void baseSelection(ActionEvent event) throws IOException// method assigns the type of base to base choice.
            //also calculate the total price of base option.
    {
        basePrice=0;
        if (baseRadioButton1.isSelected()) {
            baseChoice = baseRadioButton1.getText();
        } else if (baseRadioButton2.isSelected()) {
            baseChoice = baseRadioButton2.getText();
            basePrice +=0.99;
        } else if (baseRadioButton3.isSelected()) {
            baseChoice = baseRadioButton3.getText();
            basePrice+=0.99;
        }

    }




    public void addtoCart(ActionEvent event) throws IOException// when customers click addtoCart button
            // this method will display the total pizza order on screen
    {
        greyGradient();
        textFlow.getChildren().clear();
        pizzaCalculation();

        if(pizzaPrice<5.99)
        {textFlow.getChildren().add(new Text("You must select SIZE options)!"));}
        else {
            textFlow.getChildren().add(new Text("You added Pizza order to Cart:" + "\n"));
            pizzaOrder();
            textFlow.getChildren().add(new Text("------------------------------------" + "\n"));
            textFlow.getChildren().add(new Text("SubtotalPrice: $" + totalPrice + "\n"));

        }


       }
       public void continueOrder(ActionEvent event)
       {
           clearOptions();
       }


    public void pizzaCalculation() // calculate total price of pizza order.
    {   pizzaPrice=0;
        pizzaPrice = toppingPrice+sizePrice+basePrice+crustPrice;
        totalPrice+=pizzaPrice;
        String formattedNumber = String.format("%.2f", totalPrice); // Displace total price in 2 decimals
        totalPrice= Double.parseDouble(formattedNumber);
    }

    public void pizzaOrder() // Method helps store pizza order to pizzaOrderList List
    {
        if (!crustChoice.isEmpty() && !baseChoice.isEmpty() && !sizeChoice.isEmpty()) {
            int i = 1;

            PizzaOrder pizzaOrder = new PizzaOrder(new ArrayList<>(selectedToppings), baseChoice, crustChoice, sizeChoice);
            pizzaOrderList.add(pizzaOrder);

            for (PizzaOrder order : pizzaOrderList) {
                textFlow.getChildren().add(new Text("" + i + ". Pizza with\n " + order.toString()));
                i++;
            }
        }
    }


    public void clearOptions() // Clear some components.
      {   textFlow.getChildren().clear();
          anchorPane.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(null, null, null)));
          selectedToppings.clear();
         sizeChoice=""; crustChoice="Regular Crust"; baseChoice="No Base";
         sizePrice=0.0;crustPrice=0.0;basePrice=0.0;toppingPrice=0.0;

          crustRadioButton1.setSelected(false);crustRadioButton2.setSelected(false);crustRadioButton3.setSelected(false);
          crustRadioButton4.setSelected(false);crustRadioButton5.setSelected(false);crustRadioButton6.setSelected(false);
          baseRadioButton1.setSelected(false);baseRadioButton2.setSelected(false);baseRadioButton3.setSelected(false);
          sizeRadioButton1.setSelected(false);sizeRadioButton2.setSelected(false);sizeRadioButton3.setSelected(false);
          for(CheckBox checkBox: checkBoxes)
          {
              checkBox.setSelected(false);
          }
          if (selectedToppings == null)

              selectedToppings= new ArrayList<>();
          }


    public void clear(ActionEvent event) // Method resets all variables
    {
        clearOptions();
        totalPrice=0.0;

        if (pizzaOrderList == null)// handling if pizzaOrderList Array List is null
        {
            pizzaOrderList = new ArrayList<>();
        }
        pizzaOrderList.clear();

    }
    public void checkOutOrder(ActionEvent event) throws IOException {
        if (totalPrice == 0) // Warning if customers click checkout button, but not add items to cart yet.
        {
            textFlow.getChildren().add(new Text("Add order to the cart please" + "\n" + "Press CLEAR button to restart order!" + "\n "
                    + "Or add items to the cart" + "\n"));
            greyGradient(); // Change the background of the screen(textFlow)

        } else {
            saveOrdersToFile();// If customers press checkout Button, orders will be stored in textFile.
            Scene(event);

        }
    }
    public void cart(ActionEvent event) throws IOException
    {
        Scene(event);
    }
    public void Scene(ActionEvent event)throws IOException
    {FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckOut.fxml"));// switch to check Out Screen.
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}


    public void saveOrdersToFile() // method helps to save pizza orders in textFile named pizzaOrder
    {
        try { int i=1;
            FileWriter writer = new FileWriter("pizzaOrder.txt");// Create file named pizzaOrder
            writer.write("Your Order Pizza: "+"\n");
            for(PizzaOrder order:pizzaOrderList) // The loop reads pizzaOrder stored in the arrayList named pizzaOrderList,
                // then write them in the text file
            {
                writer.write(""+i+".Pizza "+"\n"+order.toString());
                System.out.println(i);
                i++;


            }

            writer.write("--------------------------------"+"\n");
            writer.write("Total Price: " + totalPrice+"\n");
            // Add a newline at the end for better readability

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void greyGradient()  // method change the backGround of the anchor that contained the TextFlow(Screen show customer's orders)
    {
        Stop[] stop = new Stop[]
                {
                        new Stop(0, Color.web("#DDDDDD")),
                        new Stop(1, Color.web("#EEEEEE"))
                };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null, stop);
        anchorPane.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(gradient, null, null)));
    }
}


