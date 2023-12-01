package com.example.momnpop;

import java.util.ArrayList;


public class PizzaOrder
{
    ArrayList<String> toppings= new ArrayList<>();
    String base;
    String crust;
    String size;
    PizzaOrder()
    {

    }
    PizzaOrder(ArrayList<String> toppings, String base, String crust, String size)
    {
        this.toppings = toppings;
        this.base=base;
        this.crust= crust;
        this.size=size;
    }
    @Override
    public String toString()
    { StringBuilder toppingsString = new StringBuilder();
        for(String topping: toppings)
        {
            toppingsString.append(topping).append(", ");
        }
        if(toppingsString.length()>2)
        {
            toppingsString.setLength((toppingsString.length()-2));
        }
        return   "-Toppings: "+ toppingsString +"\n"
                +"- Base: "+base+"\n"
                +"- Crust: " +crust+"\n"
                +"- Size: "+ size+"\n";



    }

}
