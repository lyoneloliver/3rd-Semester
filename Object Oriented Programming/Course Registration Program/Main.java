import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Buat Dosen
        Dosen d1 = new Dosen("Budi Santoso", "D001");
        Dosen d2 = new Dosen("Ani Wijaya", "D002");
        Dosen d3 = new Dosen("Rudi Pratama", "D003");

        // Buat Mata Kuliah
        ArrayList<MataKuliah> daftarMK = new ArrayList<>();
        daftarMK.add(new MataKuliah("MK101", "Pemrograman Java", d1, 2));
        daftarMK.add(new MataKuliah("MK102", "Basis Data", d2, 1));
        daftarMK.add(new MataKuliah("MK103", "Struktur Data", d3, 3));

        int pilihanMenu = 0;

        while (pilihanMenu != 4) {
            System.out.println("1. Daftar Mata Kuliah");
            System.out.println("2. Lihat Daftar Mata Kuliah");
            System.out.println("3. Lihat Histori Mahasiswa");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihanMenu = sc.nextInt();
            sc.nextLine(); 

            switch (pilihanMenu) {
                case 1:
                    System.out.print("Masukkan nama mahasiswa: ");
                    String nama = sc.nextLine();
                    System.out.print("Masukkan NRP: ");
                    String nrp = sc.nextLine();
                    Mahasiswa mhs = new Mahasiswa(nama, nrp);

                    System.out.println("\nDaftar Mata Kuliah:");
                    for (int i = 0; i < daftarMK.size(); i++) {
                        MataKuliah mk = daftarMK.get(i);
                        System.out.println((i+1) + ". " + mk.getNama() + " [" + mk.getKode() + "] - Kapasitas: " 
                                           + mk.getJumlahPeserta() + "/" + mk.getKapasitas());
                    }

                    System.out.print("Nomor Mata Kuliah: ");
                    int pilihMK = sc.nextInt();
                    sc.nextLine();

                    if (pilihMK > 0 && pilihMK <= daftarMK.size()) {
                        MataKuliah mk = daftarMK.get(pilihMK - 1);
                        if (mk.tambahMahasiswa(mhs)) {
                            System.out.println("Pendaftaran berhasil");
                        } else {
                            System.out.println("Pendaftaran gagal.");
                        }
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;

                case 2:
                    System.out.println("\nDaftar Mata Kuliah:");
                    for (MataKuliah mk : daftarMK) {
                        mk.infoMatkul();
                    }
                    break;

                case 3:
                    System.out.print("Masukkan NRP mahasiswa: ");
                    String nrpCari = sc.nextLine();
                    boolean ada = false;
                    System.out.println("\nHistori mata kuliah untuk NRP " + nrpCari + ":");
                    for (MataKuliah mk : daftarMK) {
                        if (mk.cekTerdaftar(new Mahasiswa("", nrpCari))) {
                            System.out.println("- " + mk.getNama() + " [" + mk.getKode() + "] - Dosen: " 
                                               + mk.getDosenPengampu().getNama());
                            ada = true;
                        }
                    }
                    if (!ada) {
                        System.out.println("Belum ada mata kuliah yang diambil.");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak valid!");
            }
        }

        sc.close();
    }
}
