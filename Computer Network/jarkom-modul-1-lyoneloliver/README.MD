[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tPVgLsdF)
| Name                   | NRP        | Class |
| ---------------------- | ---------- | ----- |
| Lyonel Oliver Dwiputra | 5025241145 | A     |

## Task 1

- Flag

  `JARKOM25{Ja0G_Bbbb4ng3t_S1_NLD7LB8K7Z2XPG127JU2JZCHBK39VI0xl0vel1ah2ly9z4ibo6l5oxtohmbb5_eaedb174d11ef490382e7d5a565c70cb}`

> a. Berapa banyak packet yang terekam pada file pcapng?  
> _How many packets are recorded in the pcapng file?_

**Answer:** `9596`

- Filter expression  
  `-`

- Explanation  
  `Jumlah total packet dapat dilihat pada bagian bawah aplikasi Wireshark yang menampilkan informasi "Packets: 9596".`

- Output result  
  `Packets: 9596`

<br><br>

> b. Ada berapa jenis protocol (total) yang terekam pada traffic?  
> _How many types of protocol are recorded in the traffic?_

**Answer:** `12`

- Filter expression  
  `-`

- Explanation  
  `Menggunakan menu Statistics → Protocol Hierarchy untuk melihat seluruh protokol yang terekam.`

- Output result  
  `Frame, Linux cooked-mode capture, IPv4, TCP, VNC, Thrift, Telnet, Signaling Compression, HTTP, JSON, HiPerConTracer, Data`

<br><br>

> c. Ada berapa jenis protocol berbasis TCP yang terekam pada traffic?  
> _How many TCP-based application protocols are recorded?_

**Answer:** `8`

- Filter expression  
  `-`

- Explanation  
  `Dilihat dari Protocol Hierarchy pada bagian TCP Application.`

- Output result  
  `VNC, Thrift, Telnet, Signaling Compression, HTTP, JSON, HiPerConTracer, Data`

<br><br>

> d. Ada berapa banyak packet TCP murni tanpa data?  
> _How many pure TCP packets (without payload)?_

**Answer:** `3223`

- Filter expression  
  `_ws.col.protocol == "TCP" and not data`

- Explanation  
  `Filter ini menampilkan paket TCP yang tidak membawa payload.`

- Output result  
  `Displayed: 3223`

---

## Task 2

- Flag

  `JARKOM25{N1c3_0ne_b4nggg_EGZFQSSLRRyuMM13yhyjivywnurdhqwbhvmxpc3r4t0ps74482165123619669690_1c2077eac488c322347a440d11296775}`

> a. Berapa banyak packet TCP murni dengan flag ACK?  

**Answer:** `3209`

- Filter expression  
  `_ws.col.protocol == "TCP" and not data and tcp.flags.ack == 1`

- Explanation  
  `Total packet yang tampil adalah 3211, namun terdapat 2 packet berwarna hitam (invalid), sehingga dikurangi.`

- Output result  
  `3211 - 2 = 3209`

<br><br>

> b. Berapa banyak packet TCP murni dengan flag hanya ACK saja?

**Answer:** `3172`

- Filter expression  
  `tcp.len == 0 && tcp.flags.ack == 1 && tcp.flags.syn == 0 && tcp.flags.fin == 0`

- Explanation  
  `Filter ini memastikan hanya ACK tanpa flag tambahan.`

- Output result  
  `3172`

<br><br>

> c. Berapa banyak packet TCP murni dengan flag selain ACK?

**Answer:** `49`

- Filter expression  
  `_ws.col.protocol == "TCP" and not data and tcp.flags != 0x010`

- Explanation  
  `Filter mengecualikan ACK murni dan menampilkan flag lain seperti SYN/FIN.`

- Output result  
  `49`

---

## Task 3

- Flag

  `JARKOM25{W0w_Y0uU_h4V33e_d0n3_444_90od_j0bB_5MOR3g0dl1k332sy0r4s6gvvegmtkappci_ad33f5afdf30c9ceb63504c973d27105}`

> a. Pada port berapa client telnet terbuka?

**Answer:** `54184`

- Filter expression  
  `telnet`

- Explanation  
  `Follow TCP Stream menunjukkan koneksi 172.16.16.102:23 ke 172.16.16.101:54184.`

- Output result  
  `Client port: 54184`

<br><br>

> b. Berapa byte response dari server?

**Answer:** `1449`

- Explanation  
  `Informasi ukuran terlihat pada Follow TCP Stream (Entire Conversation).`

- Output result  
  `1449 bytes`

<br><br>

> c. Apa username client telnet?

**Answer:** `jovyan`

- Explanation  
  `Username terlihat jelas pada login prompt di TCP Stream.`

<br><br>

> d. Apa password client telnet?

**Answer:** `123`

- Explanation  
  `Password terlihat dalam plaintext pada TCP Stream.`

---

## Task 4

- Flag

  `JARKOM25{G04t__a4n4liz333er_BE639W6QN5T0X7EFDXO8fr0gnm5gv6wlskz17utr3ys9533977147_6029e4c45f6508daf3a03cf1933336c7}`

> a. Perintah pertama client di telnet?

**Answer:** `echo`

<br><br>

> b. Nama file .txt di server?

**Answer:** `test.txt`

<br><br>

> c. Kata pertama yang dimasukkan ke file?

**Answer:** `Jarkom`

---

## Task 5

- Flag

  `JARKOM25{n4il0ng_m1lk_dr4g000n_TMSRXZFRLGJQSP86QL3722N6085V6Ncr0c796g63i5xpfj14covot5b435_9874b289b2c568cee1115c0a120f68ea}`

> a. Berapa packet HTTP?

**Answer:** `298`

> b. Berapa response HTTP?

**Answer:** `149`

> c. Berapa HTTP sukses?

**Answer:** `296`

> d. IP client HTTP?

**Answer:** `172.16.16.101`

---

## Task 6

- Flag

  `JARKOM25{br0mb44rdin0u_Cr0ccc0c0c0cdi1l10l_3586656793awaesajwpp8deujush1n0bu0RPTFNNVGD99KGA_75dfeef6fc3dcbecdf37a6d493f8cd81}`

> a. Fake flag?

**Answer:** `FakeFlag{JarkomGampang}`

> b. Username dan password?

**Answer:** `Rey:123`

---

## Task 7

- Flag

  `JARKOM25{tr4l4lel0_tr1lil1_ka6j638t8zk3b0s0sZR070PACK684M3N_3fcae58ce53dbe87ce1bacd9c0001d22}`

**Answer:** `donalbebek.jpg`

---

## Task 8

- Flag

  `JARKOM25{y0u_4r3_s0_G00d_1n_F0r3nsic_J8JGV03STJBDIINSFLS7P12XXH8F8Nx45y4n6jyrovj2nqf991hph4icoaa9_3bf7b077bbd416f665ab9a4866589b6a}`

> a. Jumlah FTP packet?

**Answer:** `81`

> b. Username & password FTP?

**Answer:** `rey:password123lingangu`

> c. Command lihat direktori?

**Answer:** `LIST`

---

## Task 9

- Flag

  `JARKOM25{j4rk000000mmm_g4mpp4444n9999999_10274654628i41L4hnv2poz0t26321k0ncolQ26HKII1M8SK5UN_c012837a90cc83e3ad7837481fd42cb3}`

> a. IP FTP server?

**Answer:** `172.16.16.101`

> b. Jumlah file di server?

**Answer:** `7`

> c. File yang dipanggil di page.html?

**Answer:** `pokijan.jpg,research_center.jpg`

---

## Task 10

- Flag

  `JARKOM25{f1nisssshs55s5s533s_l1n333ee333E3_66467918822910y1rn9qom45345215123123WEZIZ1P8AAHD4PH_32a66eced5f136725967a162c0d39025}`

> a. File berisi string encoded?

**Answer:** `secret.txt`

> b. File hasil copy?

**Answer:** `secret1.txt`

> c. Decoded string?

**Answer:**  
`Pada suatu hari Rey bertemu dengan Nailong the Milk Dragon. Ketika bertemu, Rey mengajarkan Nailong apa itu Jaringan Komputer. Nailong pun senang karena ternyata Jaringan Komputer itu gampang.`

---

## Summary

**Problems**

Beberapa filter Wireshark yang digunakan tidak dijelaskan secara langsung pada materi, sehingga diperlukan eksplorasi mandiri dan trial-and-error untuk menemukan filter yang sesuai. Selain itu, beberapa analisis lanjutan juga membutuhkan pemahaman tambahan dari dokumentasi dan eksperimen langsung di Wireshark.
