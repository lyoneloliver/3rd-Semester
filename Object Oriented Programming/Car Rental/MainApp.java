import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Daftar kendaraan tersedia
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        daftarKendaraan.add(new Mobil("Toyota", "Avanza", 2020, 4));
        daftarKendaraan.add(new Motor("Honda", "Beat", 2022, 2));
        daftarKendaraan.add(new Sepeda("Polygon", "Helios", 2021, "Balap"));
        daftarKendaraan.add(new Mobil("Daihatsu", "Xenia", 2019, 4));
        daftarKendaraan.add(new Sepeda("BMX", "Street 3000", 2018, "BMX"));
        daftarKendaraan.add(new Motor("Yamaha", "NMax", 2023, 2));
        daftarKendaraan.add(new Mobil("Honda", "Civic", 2018, 4));
        daftarKendaraan.add(new Sepeda("United", "Trifold", 2020, "Lipat"));
        daftarKendaraan.add(new Motor("Suzuki", "Satria FU", 2017, 2));
        daftarKendaraan.add(new Mobil("Mitsubishi", "Pajero Sport", 2021, 4));

        // Daftar penyewa
        ArrayList<Penyewa> daftarPenyewa = new ArrayList<>();
        daftarPenyewa.add(new Penyewa("Andi", daftarKendaraan.get(0)));
        daftarPenyewa.add(new Penyewa("Budi", daftarKendaraan.get(2)));
        daftarPenyewa.add(new Penyewa("Citra", daftarKendaraan.get(1)));

        int pilihan;

        do {
            System.out.println("\n===== MENU RENTAL KENDARAAN =====");
            System.out.println("1. Tampilkan daftar kendaraan");
            System.out.println("2. Tampilkan daftar penyewa");
            System.out.println("3. Tambah penyewa baru");
            System.out.println("4. Hapus penyewa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); 
            
            switch(pilihan) {
                case 1:
                    System.out.println("\n=== DAFTAR KENDARAAN TERSEDIA ===");
                    int index = 1;
                    for (Kendaraan k : daftarKendaraan) {
                        System.out.println(index + ". " + k.getInfo());
                        index++;
                    }
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR PENYEWA ===");
                    if (daftarPenyewa.isEmpty()) {
                        System.out.println("Belum ada penyewa.");
                    } else {
                        for (Penyewa p : daftarPenyewa) {
                            System.out.println(p.getInfo());
                            System.out.println("---------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nTambahkan Penyewa Baru");
                    System.out.print("Nama penyewa: ");
                    String nama = input.nextLine();

                    System.out.println("\nPilih kendaraan yang ingin disewa:");
                    for (int i = 0; i < daftarKendaraan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarKendaraan.get(i).getInfo());
                    }
                    System.out.print("Masukkan nomor kendaraan: ");
                    int pilihKendaraan = input.nextInt();
                    input.nextLine();

                    if (pilihKendaraan < 1 || pilihKendaraan > daftarKendaraan.size()) {
                        System.out.println("Pilihan tidak valid!");
                    } else {
                        Kendaraan dipilih = daftarKendaraan.get(pilihKendaraan - 1);
                        daftarPenyewa.add(new Penyewa(nama, dipilih));
                        System.out.println("Penyewa berhasil ditambahkan!");
                    }
                    break;

                case 4:
                    System.out.println("\nMasukkan nama penyewa yang akan dihapus: ");
                    String hapusNama = input.nextLine();
                    
                    boolean ditemukan = false;
                    for (int i = 0; i < daftarPenyewa.size(); i++) {
                        if (daftarPenyewa.get(i).getNama().equalsIgnoreCase(hapusNama)) {
                            daftarPenyewa.remove(i);
                            System.out.println("Penyewa berhasil dihapus!");
                            ditemukan = true;
                            break;
                        }
                    }
                    if (!ditemukan) {
                        System.out.println("Penyewa tidak ditemukan.");
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem rental!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while(pilihan != 5);

        input.close();
    }
}