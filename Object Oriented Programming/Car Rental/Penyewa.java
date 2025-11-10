public class Penyewa {
    private String nama;
    private Kendaraan kendaraanDisewa;

    public Penyewa(String nama, Kendaraan kendaraanDisewa) {
        this.nama = nama;
        this.kendaraanDisewa = kendaraanDisewa;
    }

    public String getNama() {
        return nama;
    }

    public Kendaraan getKendaraanDisewa() {
        return kendaraanDisewa;
    }

    public String getInfo() {
        return "Nama: " + nama + "\nKendaraan: " + kendaraanDisewa.getInfo();
    }
}