import java.util.Arrays;
import java.util.List;

public class VendingCoffeeMachineSim {
    public static void main(String[] args) {
        List<Coffee> menu = Arrays.asList(
            new Coffee("Espresso", 5),
            new Coffee("Latte", 6),
            new Coffee("Cappuccino", 7)
        );

        Stock stock = new Stock(10, 10, 10, 20, 10);

        TransactionLog log = new TransactionLog();

        CoffeeMachine machine = new CoffeeMachine(menu, stock, log);
        machine.start();
    }
}
