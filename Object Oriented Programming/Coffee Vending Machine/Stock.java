
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Stock {
    private Map<String, Integer> coffeeStock = new HashMap<>();
    private int sugar;
    private int milk;

    public Stock(int espresso, int latte, int cappuccino, int sugar, int milk) {
        coffeeStock.put("Espresso", espresso);
        coffeeStock.put("Latte", latte);
        coffeeStock.put("Cappuccino", cappuccino);
        this.sugar = sugar;
        this.milk = milk;
    }

    public boolean isAvailable(Order order) {
        String coffeeName = order.getCoffee().getName();
        return coffeeStock.getOrDefault(coffeeName, 0) >= 1 &&
               sugar >= order.getSugar() &&
               milk >= (order.hasMilk() ? 1 : 0);
    }

    public void reduceStock(Order order) {
        String coffeeName = order.getCoffee().getName();
        coffeeStock.put(coffeeName, coffeeStock.get(coffeeName) - 1);
        sugar -= order.getSugar();
        if(order.hasMilk()) milk -= 1;
    }

    public void displayStock() {
        System.out.println("Stock - Espresso: " + coffeeStock.get("Espresso") +
                           ", Latte: " + coffeeStock.get("Latte") +
                           ", Cappuccino: " + coffeeStock.get("Cappuccino") +
                           ", Sugar: " + sugar + ", Milk: " + milk);
    }

    public int getSugar() {
        return sugar;
    }

    public int getMilk() {
        return milk;
    }

    public Set<String> getCoffeeNames() {
        return coffeeStock.keySet();
    }

    public int getCoffeeStock(String name) {
        return coffeeStock.getOrDefault(name, 0);
    }

    public void refillCoffee(String name, int amount) {
        coffeeStock.put(name, coffeeStock.getOrDefault(name, 0) + amount);
    }

    public void refillSugar(int amount) {
        sugar += amount;
    }

    public void refillMilk(int amount) {
        milk += amount;
    }
}
