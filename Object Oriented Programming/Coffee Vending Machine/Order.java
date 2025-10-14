public class Order {
    private Coffee coffee;
    private String size;
    private int sugar;
    private boolean milk;
    private double totalPrice;

    public Order(Coffee coffee, String size, int sugar, boolean milk) {
        this.coffee = coffee;
        this.size = size;
        this.sugar = sugar;
        this.milk = milk;
        calculatePrice();
    }

    private void calculatePrice() {
        totalPrice = coffee.getBasePrice();
        switch(size.toLowerCase()) {
            case "small": totalPrice += 0; break;
            case "medium": totalPrice += 2; break;
            case "large": totalPrice += 4; break;
        }
        if(milk) totalPrice += 2;
        totalPrice += sugar * 0.5;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public String getSize() {
        return size;
    }

    public int getSugar() {
        return sugar;
    }

    public boolean hasMilk() {
        return milk;
    }
}
