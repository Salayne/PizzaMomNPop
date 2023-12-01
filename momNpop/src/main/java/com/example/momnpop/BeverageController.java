package com.example.momnpop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class BeverageController extends OrderPopAndMom {
    @FXML
    RadioButton pepsiRadioButton1, pepsiRadioButton2, pepsiRadioButton3;
    @FXML
    RadioButton spriteRadioButton1, spriteRadioButton2, spriteRadioButton3;
    @FXML
    RadioButton cokeRadioButton1, cokeRadioButton2, cokeRadioButton3;
    @FXML
    TextField pepsiTextField1, pepsiTextField2, pepsiTextField3, spriteTextField1, spriteTextField2, spriteTextField3,
            cokeTextField1, cokeTextField2, cokeTextField3;
    @FXML
    TextField fantaTextField1, fantaTextField2, fantaTextField3, upTextField1, upTextField2, upTextField3;
    @FXML
    RadioButton fantaRadioButton1, fantaRadioButton2, fantaRadioButton3;
    @FXML
    RadioButton upRadioButton1, upRadioButton2, upRadioButton3;
    @FXML
    Button addToCart;
    @FXML
    private TextFlow textFlow;
    @FXML
    private AnchorPane anchorPane;

    String smallPepsiSize = " ", mediumPepsiSize = " ", largePepsiSize = " ";
    String smallSpriteSize = " ", mediumSpriteSize = " ", largeSpriteSize = " ";
    String smallCokeSize = " ", mediumCokeSize = " ", largeCokeSize = " ";
    String smallFantaSize = " ", mediumFantaSize = " ", largeFantaSize = " ";
    String smallUpSize = " ", mediumUpSize = " ", largeUpSize = " ";
    double pepsiPrice = 0.0, smallPepsiPrice = 0.0, mediumPepsiPrice = 0.0, largePepsiPrice = 0.0;
    double cokePrice = 0.0, smallCokePrice = 0.0, mediumCokePrice = 0.0, largeCokePrice = 0.0;
    double spritePrice = 0.0, smallSpritePrice = 0.0, mediumSpritePrice = 0.0, largeSpritePrice = 0.0;
    double fantaPrice = 0.0, smallFantaPrice = 0.0, mediumFantaPrice = 0.0, largeFantaPrice = 0.0;
    double upPrice = 0.0, smallUpPrice = 0.0, mediumUpPrice = 0.0, largeUpPrice = 0.0;
    int smallPepsiQuantity = 0, mediumPepsiQuantity = 0, largePepsiQuantity = 0;
    int cokeQuantity = 0, smallCokeQuantity = 0, mediumCokeQuantity = 0, largeCokeQuantity = 0;
    int spriteQuantity = 0, smallSpriteQuantity = 0, mediumSpriteQuantity = 0, largeSpriteQuantity = 0;
    int fantaQuantity = 0, smallFantaQuantity = 0, mediumFantaQuantity = 0, largeFantaQuantity = 0;
    int upQuantity = 0, smallUpQuantity = 0, mediumUpQuantity = 0, largeUpQuantity = 0;
    double smallPrice = 1.99, mediumPrice = 2.99, largePrice = 3.99;
    String smallSize ="Small,  Price: $1.99/each";
    String mediumSize ="Medium, Price: $2.99/each";
    String largeSize ="Large,  Price: $3.99/each";
    ArrayList<OrderPopAndMom> orderPopAndMoms = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;
    double totalPrice = 0.0;

    @FXML
    private void switchToPopandMom(ActionEvent event) throws IOException // function helps switches to the main page.
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pop&MomPizza.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void pepsiSelection(ActionEvent event) // Method to select small, medium and large Pepsi
    {
        if (pepsiRadioButton1.isSelected()) {
            smallPepsiSize = smallSize;
        } else if (pepsiRadioButton2.isSelected()) {
            mediumPepsiSize = mediumSize;
        } else if (pepsiRadioButton3.isSelected()) {
            largePepsiSize = largeSize;
        }
    }


    public void cokeSelection(ActionEvent event) {
        if (cokeRadioButton1.isSelected()) {
            smallCokeSize = smallSize;
        } else if (cokeRadioButton2.isSelected()) {
            mediumCokeSize = mediumSize;
        } else if (cokeRadioButton3.isSelected()) {
            largeCokeSize = largeSize;
        }
    }

    //Select small, medium and large sizes of sprite
    public void spriteSelection(ActionEvent event) {
        if (spriteRadioButton1.isSelected()) {
            smallSpriteSize = smallSize;

        } else if (spriteRadioButton2.isSelected()) {
            mediumSpriteSize =mediumSize;

        } else if (spriteRadioButton3.isSelected()) {
            largeSpriteSize = largeSize;

        }
    }

    public void fanta_Selection(ActionEvent event) {
        if (fantaRadioButton1.isSelected()) {
            smallFantaSize =smallSize;
        } else if (fantaRadioButton2.isSelected()) {
            mediumFantaSize = mediumSize;

        } else if (fantaRadioButton3.isSelected()) {
            largeFantaSize = largeSize;
        }

    }

    public void upSelection(ActionEvent event) {
        if (upRadioButton1.isSelected()) {
            smallUpSize = smallSize;
        } else if (upRadioButton2.isSelected()) {
            mediumUpSize = mediumSize;
        } else if (upRadioButton3.isSelected()) {
            largeUpSize = largeSize;

        }

    }

    // Add quantity for drinks
    double[] handlerAddQuantity(TextField textField, double unitPrice) {
        double[] quantityPriceArray = new double[2];
        int drinkQuantity = 0;
        double drinkPrice = 0.0;

        try {
            drinkQuantity = Integer.parseInt(textField.getText());
            double drinkQuantity1 = drinkQuantity;
            drinkPrice = unitPrice * drinkQuantity1;

            quantityPriceArray[0] = drinkQuantity;
            quantityPriceArray[1] = drinkPrice;
        } catch (NumberFormatException e) {
            textFlow.getChildren().add(new Text("Invalid quantity format. Please enter a valid number.")); // Print out the warning on text_field
            textField.clear();
        }
        return quantityPriceArray;
    }




    public void addQuantity(ActionEvent event)

    {
        double[] pepsiQuantityPrice = new double[2];
        if (pepsiRadioButton1.isSelected() && !pepsiTextField1.getText().isEmpty())
        // the call function handlerAddQuantity to get the value quantity and price order that assigned in array"pepsiQuantityPrice"
        {   // When small pepsi Radio Button check, medium and large pepsi Radio Button unchecked
            pepsiTextField2.clear();
           pepsiTextField3.clear();
            pepsiQuantityPrice = handlerAddQuantity(pepsiTextField1, smallPrice);// get quantity and price of drink order and assigns to array pepsiQuantiyPrice.
            smallPepsiPrice = pepsiQuantityPrice[1];// Assign the price of order contained in pepsiQuantityPrice to smallPepsiPrice
            smallPepsiQuantity = (int) pepsiQuantityPrice[0];// Assign quantity of small pepsi order to smallPepsiQuantity
        } else if (pepsiRadioButton2.isSelected() && !pepsiTextField2.getText().isEmpty()) {
            pepsiTextField1.clear();
            pepsiTextField3.clear();
            pepsiQuantityPrice = handlerAddQuantity(pepsiTextField2, mediumPrice);
            mediumPepsiPrice = pepsiQuantityPrice[1];
            mediumPepsiQuantity = (int) pepsiQuantityPrice[0];
        } else if (pepsiRadioButton3.isSelected() && !pepsiTextField3.getText().isEmpty()) {
            pepsiTextField1.clear();pepsiTextField2.clear();
            pepsiQuantityPrice = handlerAddQuantity(pepsiTextField3, largePrice);
            largePepsiPrice = pepsiQuantityPrice[1];
            largePepsiQuantity = (int) pepsiQuantityPrice[0];

        }
        double[] spriteQuantityPrice = new double[2];//first element contents the drink price,
        // second element contents drink quantity

        //Add quantity to Sprite Drink
        if (spriteRadioButton1.isSelected() && !spriteTextField1.getText().isEmpty()) {
            spriteTextField2.clear();
            spriteTextField3.clear();
            spriteQuantityPrice = handlerAddQuantity(spriteTextField1, smallPrice);
            smallSpritePrice = spriteQuantityPrice[1];
            smallSpriteQuantity = (int) spriteQuantityPrice[0];
        } else if (spriteRadioButton2.isSelected() && !spriteTextField2.getText().isEmpty()) {
            spriteTextField1.clear();
            spriteTextField3.clear();
            spriteQuantityPrice = handlerAddQuantity(spriteTextField2, mediumPrice);
            mediumSpritePrice = spriteQuantityPrice[1];
            mediumSpriteQuantity = (int) spriteQuantityPrice[0];

        } else if (spriteRadioButton3.isSelected() && !spriteTextField3.getText().isEmpty()) {
            spriteTextField1.clear();
            spriteTextField2.clear();
            spriteQuantityPrice = handlerAddQuantity(spriteTextField3, largePrice);
            largeSpritePrice = spriteQuantityPrice[1];
            largeSpriteQuantity = (int) spriteQuantityPrice[0];
        }
        //Add quantity to Coke Drink
        double[] cokeQuantityPrice = new double[2];
        if (cokeRadioButton1.isSelected() && !cokeTextField1.getText().isEmpty()) {
            cokeTextField3.clear();
            cokeTextField2.clear();
            cokeQuantityPrice = handlerAddQuantity(cokeTextField1, smallPrice);
            smallCokePrice = cokeQuantityPrice[1];
            System.out.println("smallCokePrice: " + smallCokePrice);
            smallCokeQuantity = (int) cokeQuantityPrice[0];


        } else if (cokeRadioButton2.isSelected() && !cokeTextField2.getText().isEmpty()) {
            cokeTextField3.clear();
            cokeTextField1.clear();
            cokeQuantityPrice = handlerAddQuantity(cokeTextField2, mediumPrice);
            mediumCokePrice = cokeQuantityPrice[1];
            mediumCokeQuantity = (int) cokeQuantityPrice[0];

        } else if (cokeRadioButton3.isSelected() && !cokeTextField3.getText().isEmpty()) {
            cokeTextField1.clear();
            cokeTextField2.clear();
            cokeQuantityPrice = handlerAddQuantity(cokeTextField3, largePrice);
            largeCokePrice = cokeQuantityPrice[1];
            largeCokeQuantity = (int) cokeQuantityPrice[0];

        }

        double[] upQuantityPrice = new double[2];
        //Add quantity to upDrink
        if (upRadioButton1.isSelected() && !upTextField1.getText().isEmpty()) {
            upTextField2.clear();
            upTextField3.clear();
            upQuantityPrice = handlerAddQuantity(upTextField1, smallPrice);
            smallUpPrice = upQuantityPrice[1];
            smallUpQuantity = (int) upQuantityPrice[0];

        } else if (upRadioButton2.isSelected() && !upTextField2.getText().isEmpty()) {
            upTextField3.clear();
            upTextField1.clear();
            upQuantityPrice = handlerAddQuantity(upTextField2, mediumPrice);
            mediumUpPrice = upQuantityPrice[1];
            mediumUpQuantity = (int) upQuantityPrice[0];


        } else if (upRadioButton3.isSelected() && !upTextField3.getText().isEmpty()) {
            upTextField2.clear();
            upTextField1.clear();
            upQuantityPrice = handlerAddQuantity(upTextField3, mediumPrice);
            largeUpPrice = upQuantityPrice[1];
            largeUpQuantity = (int) upQuantityPrice[0];

        }

        // Add quantity to FanTa drink
        double[] fantaQuantityPrice = new double[2];
        if (fantaRadioButton1.isSelected() && !fantaTextField1.getText().isEmpty()) {
            fantaTextField2.clear();
            fantaTextField3.clear();
            fantaQuantityPrice = handlerAddQuantity(fantaTextField1, smallPrice);
            smallFantaPrice = fantaQuantityPrice[1];
            smallFantaQuantity = (int) fantaQuantityPrice[0];

        } else if (fantaRadioButton2.isSelected() && !fantaTextField2.getText().isEmpty()) {
            fantaQuantityPrice = handlerAddQuantity(fantaTextField2, mediumPrice);
            fantaTextField1.clear();
            fantaTextField3.clear();
            mediumFantaPrice = fantaQuantityPrice[1];
            mediumFantaQuantity = (int) fantaQuantityPrice[0];

        } else if (fantaRadioButton3.isSelected() && !fantaTextField3.getText().isEmpty()) {
            fantaTextField1.clear();
            fantaTextField2.clear();
            fantaQuantityPrice = handlerAddQuantity(fantaTextField3, largePrice);
            largeFantaPrice = fantaQuantityPrice[1];
            largeFantaQuantity = (int) fantaQuantityPrice[0];

        }


    }
     public void clearTextField()
     {
         pepsiTextField1.clear();
         pepsiTextField2.clear();
         pepsiTextField3.clear();
         cokeTextField1.clear();
         cokeTextField2.clear();
         cokeTextField3.clear();
         spriteTextField1.clear();
         spriteTextField2.clear();
         spriteTextField3.clear();
         fantaTextField1.clear();
         fantaTextField2.clear();
         fantaTextField3.clear();
         upTextField1.clear();
         upTextField2.clear();
         upTextField3.clear();
     }
     public void clearSize()
     {
         smallPepsiSize = "";
         mediumPepsiSize = "";
         largePepsiSize = "";
         smallCokeSize = " ";
         mediumCokeSize = " ";
         largeCokeSize = "";
         smallSpriteSize = " ";
         mediumSpriteSize = "";
         largeSpriteSize = "";
         smallFantaSize = "";
         mediumFantaSize = "";
         largeFantaSize = "";
         smallUpSize = "";
         mediumUpSize = "";
         largeUpSize = "";
     }
     public void reSetQuantity()
     {
         smallCokeQuantity = 0;
         mediumCokeQuantity = 0;
         largeCokeQuantity = 0;
         smallPepsiQuantity = 0;
         mediumPepsiQuantity = 0;
         largePepsiQuantity = 0;
         smallSpriteQuantity = 0;
         mediumSpriteQuantity = 0;
         largeSpriteQuantity = 0;
         smallFantaQuantity = 0;
         mediumFantaQuantity = 0;
         largeFantaQuantity = 0;
         smallUpQuantity = 0;
         mediumUpQuantity = 0;
         largeUpQuantity = 0;
     }
     public void resetPrice()
     {
         cokePrice = 0.0;
         cokeQuantity = 0;
         smallCokePrice = 0;
         mediumCokePrice = 0;
         largeCokePrice = 0;
         smallSpritePrice = 0;
         mediumSpritePrice = 0;
         largeSpritePrice = 0;
         smallPepsiPrice = 0;
         mediumPepsiPrice = 0;
         largePepsiPrice = 0;
         smallFantaPrice = 0;
         mediumFantaPrice = 0;
         largeFantaPrice = 0;
         smallUpPrice = 0;
         mediumUpPrice = 0;
         largeUpPrice = 0;

         pepsiPrice = 0.0;
         spritePrice = 0.0;
         spriteQuantity = 0;
         fantaPrice = 0.0;
         fantaQuantity = 0;
         upPrice = 0.0;
         upQuantity = 0;
     }
     public void reSetRadioButton()
     {
         pepsiRadioButton1.setSelected(false);
         pepsiRadioButton2.setSelected(false);
         pepsiRadioButton3.setSelected(false);
         spriteRadioButton1.setSelected(false);
         spriteRadioButton2.setSelected(false);
         spriteRadioButton3.setSelected(false);
         cokeRadioButton1.setSelected(false);
         cokeRadioButton2.setSelected(false);
         cokeRadioButton3.setSelected(false);
         fantaRadioButton1.setSelected(false);
         fantaRadioButton2.setSelected(false);
         fantaRadioButton3.setSelected(false);
         upRadioButton1.setSelected(false);
         upRadioButton2.setSelected(false);
         upRadioButton3.setSelected(false);
     }

    public void resetOrder()// reset every component
    {   clearTextField();
        clearSize();
        reSetQuantity();
        textFlow.getChildren().clear();
        resetOrder();
        resetPrice();

       reSetRadioButton();
       if (orderPopAndMoms == null) {
            orderPopAndMoms = new ArrayList<>();
        }
        orderPopAndMoms.clear();
        anchorPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(null, null, null)
        ));

    }

    public double calculationBeverage() // Calculate total price of drink.
    {
        cokePrice = smallCokePrice + mediumCokePrice + largeCokePrice;
        pepsiPrice = smallPepsiPrice + mediumPepsiPrice + largePepsiPrice;
        spritePrice = smallSpritePrice + mediumSpritePrice + largeSpritePrice;
        fantaPrice = smallFantaPrice + mediumFantaPrice + largeFantaPrice;
        upPrice = smallUpPrice + mediumUpPrice + largeUpPrice;
        double totalDrinkPrice = 0.0;
        totalDrinkPrice = cokePrice + pepsiPrice + spritePrice + fantaPrice + upPrice;
        String formattedNumber = String.format("%.2f", totalDrinkPrice); // Displace total price in 2 decimals
        totalDrinkPrice = Double.parseDouble(formattedNumber);
        return totalDrinkPrice;
    }

    public void handlerAddToCart(String drinkType, int drinkQuantity, String drinkSize, double drinkPrice)// method store each order in the array orderPopAndMoms
    {
        if (drinkQuantity != 0 && !drinkSize.isEmpty()) {
            String formattedNumber = String.format("%.2f", drinkPrice); // Displace total price in 2 decimals
            drinkPrice = Double.parseDouble(formattedNumber);
            OrderPopAndMom orderPopAndMom1 = new OrderPopAndMom(drinkType, drinkSize, drinkQuantity, drinkPrice);
            orderPopAndMoms.add(orderPopAndMom1);
            System.out.println(orderPopAndMoms);
        }

    }

    public void addToCart(ActionEvent event) //add orders to the cart
    {
        applyDarkGreyGradient();
        orderPopAndMoms.clear();
        // Add COKE orders to Cart
        // add quantity, and total price of small size of coke to cart
        handlerAddToCart("Coke", smallCokeQuantity, smallCokeSize, smallCokePrice);
        // add quantity, and total price of medium size of coke to cart
        handlerAddToCart("Coke", mediumCokeQuantity, mediumCokeSize, mediumCokePrice);
        // add quantity, and total price of large size of coke to cart
        handlerAddToCart("Coke", largeCokeQuantity, largeCokeSize, largeCokePrice);
        // Add PEPSI orders to Cart
        handlerAddToCart("Pepsi", smallPepsiQuantity, smallPepsiSize, smallPepsiPrice);
        handlerAddToCart("Pepsi", mediumPepsiQuantity, mediumPepsiSize, mediumPepsiPrice);
        handlerAddToCart("Pepsi", largePepsiQuantity, largePepsiSize, largePepsiPrice);
        // Add SPRITE orders to Cart
        handlerAddToCart("Sprite", smallSpriteQuantity, smallSpriteSize, smallSpritePrice);
        handlerAddToCart("Sprite", mediumSpriteQuantity, mediumSpriteSize, mediumSpritePrice);
        handlerAddToCart("Sprite", largeSpriteQuantity, largeSpriteSize, largeSpritePrice);
        // Add FanTa orders to Cart
        handlerAddToCart("FanTa", smallFantaQuantity, smallFantaSize, smallFantaPrice);
        handlerAddToCart("FanTa", mediumFantaQuantity, mediumFantaSize, mediumFantaPrice);
        handlerAddToCart("FanTa", largeFantaQuantity, largeFantaSize, largeFantaPrice);
        // // Add 7Up orders to Cart
        handlerAddToCart("7Up", smallUpQuantity, smallUpSize, smallUpPrice);
        handlerAddToCart("7Up", mediumUpQuantity, mediumUpSize, mediumUpPrice);
        handlerAddToCart("7Up", largeUpQuantity, largeUpSize, largeUpPrice);

        printOutOrder();
    }

    public void printOutOrder() // Print all orders on screen(textFlow)
    {
        textFlow.getChildren().clear();
        textFlow.getChildren().add(new Text("You just added to the Cart: "+"\n"));
        for (int i = 0; i < orderPopAndMoms.size(); i++) {
            System.out.println(orderPopAndMoms.get(i).toString());
            disPlay(textFlow, orderPopAndMoms.get(i).type, orderPopAndMoms.get(i).getSize(), orderPopAndMoms.get(i).quantity, orderPopAndMoms.get(i).getPrice());
        }

        textFlow.getChildren().add(new Text("--------------------------------------" + "\n"));
        totalPrice = calculationBeverage();
        if (totalPrice != 0.0) {
            textFlow.getChildren().add(new Text(", total price: $" + totalPrice + "\n"));
        } else {
            textFlow.getChildren().add(new Text("Your Cart is Empty"));
        }

    }

    public void clear(ActionEvent event) {
       if(totalPrice!=0.0)

       {resetOrder();}

    }

    public void checkOutDrinkOrder(ActionEvent event) throws IOException {
        if (totalPrice == 0.0) {
            textFlow.getChildren().add(new Text("Add order to the cart please"+"\n"+"Press CLEAR button to restart order! "));
            applyDarkGreyGradient();
        } else {
            saveOrdersToFile();
            totalPrice = 0.0;
            scene(event);

        }

    }
    @FXML
    public void cart(ActionEvent event )throws IOException
    {
        scene(event);
    }
    public void scene(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckOut.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void saveOrdersToFile() // Function saves the total orders to the text file named drinkOrder.text
    {
        try { int i=1;
            FileWriter writer = new FileWriter("drinkOrder.txt");
            writer.write("Your Drink Order: "+"\n");
            for (OrderPopAndMom order : orderPopAndMoms) {
                writer.write(""+i+" "+order.toString()+"\n");
            }

            writer.write("--------------------------------"+"\n");
            writer.write("Total Price: " + totalPrice+"\n");

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    // Helper method helps to display the type , size, quantity and price of drink on the text field.
    public void disPlay(TextFlow textFlow, String drinkType, String size, int quantity, double price) {
        textFlow.getChildren().add(new Text(drinkType + "\n"));
        textFlow.getChildren().add(new Text("Size: " + size));
        textFlow.getChildren().add(new Text(", Quantity: " + quantity));
        textFlow.getChildren().add(new Text(", Price: $" + price + "\n"));
        textFlow.getChildren().add(new Text("\n"));
    }


    private void applyDarkGreyGradient() {
        Stop[] stops = new Stop[]{

                new Stop(0, Color.web("#DDDDDD")),  // light grey
                new Stop(1, Color.web("#EEEEEE"))   // Slightly lighter grey
        };
        LinearGradient gradient = new LinearGradient(0, 1, 1.5, 1.5, true, null, stops);

        // Apply the gradient to the AnchorPane background
        anchorPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(gradient, null, null)


        ));


    }
}










