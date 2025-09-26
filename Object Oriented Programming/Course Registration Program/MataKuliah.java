import java.util.ArrayList;

public class MataKuliah {
    private String kode;
    private String nama;
    private Dosen dosenPengampu;
    private int kapasitas;
    private ArrayList<Mahasiswa> daftarPeserta;

    public MataKuliah(String kode, String nama, Dosen dosenPengampu, int kapasitas) {
        this.kode = kode;
        this.nama = nama;
        this.dosenPengampu = dosenPengampu;
        this.kapasitas = kapasitas;
        this.daftarPeserta = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public Dosen getDosenPengampu() {
        return dosenPengampu;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getJumlahPeserta() {
        return daftarPeserta.size();
    }

    public boolean tambahMahasiswa(Mahasiswa m) {
        if (daftarPeserta.size() >= kapasitas) return false;
        if (cekTerdaftar(m)) return false;
        daftarPeserta.add(m);
        return true;
    }

    public boolean cekTerdaftar(Mahasiswa m) {
        for (Mahasiswa mh : daftarPeserta) {
            if (mh.getNrp().equals(m.getNrp())) return true;
        }
        return false;
    }

    public void infoMatkul() {
        System.out.println("- " + nama + " [" + kode + "] - Dosen: " + dosenPengampu.getNama() 
                           + " - Kapasitas: " + getJumlahPeserta() + "/" + kapasitas);
    }
}
