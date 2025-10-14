import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {
    private List<Coffee> menu;
    private Stock stock;
    private TransactionLog transactions;
    private Scanner sc = new Scanner(System.in);

    public CoffeeMachine(List<Coffee> menu, Stock stock, TransactionLog transactions) {
        this.menu = menu;
        this.stock = stock;
        this.transactions = transactions;
    }

    public void displayMenu() {
        System.out.println("=== Menu Coffee ===");
        for(int i=0;i<menu.size();i++) {
            System.out.println((i+1)+". "+menu.get(i).getName()+" ($"+menu.get(i).getBasePrice()+")");
        }
    }

    public void start() {
        while(true) {
            displayMenu();

            int choice;
            do {
                System.out.print("Pilih kopi (angka) atau 0 untuk keluar: ");
                choice = sc.nextInt();
                if(choice < 0 || choice > menu.size()) {
                    System.out.println("Pilihan tidak valid!");
                }
            } while(choice < 0 || choice > menu.size());

            if(choice == 0) break;
            Coffee selectedCoffee = menu.get(choice-1);

            System.out.println("Pilih ukuran:");
            System.out.println("1. Small");
            System.out.println("2. Medium");
            System.out.println("3. Large");
            int sizeChoice;
            do {
                System.out.print("Pilihan: ");
                sizeChoice = sc.nextInt();
                if(sizeChoice < 1 || sizeChoice > 3) System.out.println("Pilihan tidak valid!");
            } while(sizeChoice < 1 || sizeChoice > 3);
            String size = (sizeChoice == 1) ? "Small" : (sizeChoice == 2) ? "Medium" : "Large";

            System.out.println("Tambahkan susu?");
            System.out.println("1. Tidak");
            System.out.println("2. Ya");
            int milkChoice;
            do {
                System.out.print("Pilihan: ");
                milkChoice = sc.nextInt();
                if(milkChoice < 1 || milkChoice > 2) System.out.println("Pilihan tidak valid!");
            } while(milkChoice < 1 || milkChoice > 2);
            boolean milk = milkChoice == 2;

            if(milk && stock.getMilk() <= 0) {
                System.out.println("Stok susu habis! Tidak bisa menambahkan susu.");
                milk = false;
            }

            int sugar;
            do {
                System.out.print("Jumlah gula (sendok, maksimal " + stock.getSugar() + "): ");
                sugar = sc.nextInt();
                if(sugar < 0 || sugar > stock.getSugar()) {
                    System.out.println("Jumlah gula tidak valid! Masukkan angka antara 0 dan " + stock.getSugar());
                }
            } while(sugar < 0 || sugar > stock.getSugar());

            Order order = new Order(selectedCoffee, size, sugar, milk);
            System.out.println("Pesanan: " + selectedCoffee.getName() + " (" + size + ")" +
                               (milk ? " + Milk" : "") + ", " + sugar + " gula");
            System.out.println("Total Harga: $" + order.getTotalPrice());

            System.out.print("Bayar: $");
            double payment = sc.nextDouble();

            if(payment >= order.getTotalPrice()) {
                if(stock.isAvailable(order)) {
                    stock.reduceStock(order);
                    System.out.println("Kopi " + size + " " + selectedCoffee.getName() + " siap! Selamat menikmati!");
                    transactions.addLog("Terjual: " + selectedCoffee.getName() + " (" + size + ") $" + order.getTotalPrice());

                    double change = payment - order.getTotalPrice();
                    if(change > 0) {
                        System.out.printf("Kembalian: $%.2f\n", change);
                    }

                    for(String coffeeName : stock.getCoffeeNames()) {
                        if(stock.getCoffeeStock(coffeeName) <= 0) {
                            System.out.println("Stok " + coffeeName + " habis! Admin harus refill.");
                        }
                    }
                    if(stock.getSugar() <= 0) {
                        System.out.println("Stok gula habis! Admin harus refill.");
                    }
                    if(stock.getMilk() <= 0) {
                        System.out.println("Stok susu habis! Admin harus refill.");
                    }

                } else {
                    System.out.println("Maaf, bahan tidak cukup!");
                }
            } else {
                System.out.println("Pembayaran kurang!");
            }

            stock.displayStock();
            System.out.println();
        }

        transactions.printLogs();
    }
}
