package com.example.momnpop;

 class OrderPopAndMom

{
    protected String type, size;
    protected int quantity;
    protected double price;
    OrderPopAndMom()
    { type="";
        size="";
        quantity=0;
        price=0.0;
    }
    OrderPopAndMom(String type, String size, int quantity, double price)

    {
        this.type=type;
        this.size=size;
        this.quantity=quantity;
        this.price=price;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return
                "Drink: " + type + ", " +
                "Size:  " + size + ", " +
                "Quantity: " + quantity +", "+
                "Price: " + price +"\n";

    }

}
