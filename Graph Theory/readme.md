## 1. Program Knight’s Tour (Open & Closed Tour)

Deskripsi Singkat:
Program ini mencoba menjalankan Knight’s Tour pada papan 8x8. Kuda bergerak mengikuti pola L (gerakan standar bidak kuda). Program akan mencari rute sehingga seluruh 64 kotak dikunjungi tanpa mengulang.

Cara Menggunakan:
1. Jalankan program.
2. Masukkan koordinat awal kuda dalam format: x y
   Contoh: 0 0
3. Program akan menampilkan:
   - Langkah demi langkah posisi kuda
   - Papan 8x8 berisi nomor urutan kunjungan
   - Posisi terakhir perjalanan
   - Apakah tour yang dihasilkan merupakan closed tour

Input:
Dua angka (x y) posisi awal kuda, masing-masing 0–7.

Output:
- Urutan langkah perjalanan
- Papan berisi angka 1–64
- Posisi akhir kuda
- Status closed tour

Penjelasan Logika Program:
- Fungsi move() menghasilkan semua gerakan kuda yang masih berada di dalam papan dan belum pernah dikunjungi.
- Fungsi open_tour() menjalankan pencarian rute dengan heuristik Warnsdorff: memilih langkah berikutnya yang memiliki jumlah gerakan lanjut paling sedikit (minimum degree).
- Jika buntu, dilakukan backtracking dengan menghapus langkah terakhir dan mencari alternatif lain.
- Setelah rute selesai (64 kotak tercapai), fungsi closed_tour() mengecek apakah langkah terakhir bisa menyerang posisi awal (syarat closed tour).


## 2. Program Largest Monotonically Increasing Subsequence (LIS)

Deskripsi Singkat:
Program ini mencari panjang subsekuens meningkat paling panjang dari sebuah daftar angka. Implementasi menggunakan teknik patience sorting dan binary search sehingga waktu eksekusi lebih cepat (O(n log n)).

Cara Menggunakan:
1. Jalankan program.
2. Masukkan angka-angka dalam satu baris yang dipisahkan spasi.
   Contoh: 3 10 2 1 20

Input:
Daftar integer dipisahkan spasi.

Output:
Satu angka yang menunjukkan panjang LIS.

Penjelasan Logika Program:
- tails[] menyimpan elemen terakhir dari subsekuens dengan berbagai panjang.
- Untuk setiap angka:
  - Jika lebih besar dari elemen terakhir tails → ditambahkan (subsekuens bertambah panjang).
  - Jika lebih kecil → gunakan binary search (binser) untuk menemukan posisi penggantinya.
- Panjang LIS ditentukan oleh panjang array tails.
