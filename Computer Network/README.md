[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tPVgLsdF)
| Name                   | NRP        | Class |
| Lyonel Oliver Dwiputra | 5025241145 | A     |

## Task 1

- Flag

  `JARKOM25{Ja0G_Bbbb4ng3t_S1_NLD7LB8K7Z2XPG127JU2JZCHBK39VI0xl0vel1ah2ly9z4ibo6l5oxtohmbb5_eaedb174d11ef490382e7d5a565c70cb}`

> a. Berapa banyak packet yang terekam pada file pcapng?

> _a. How many packets are recorded in the pcapng file?_

**Answer:** `9596`

- Filter expression

  ``

- Explanation

  `lihat di bagian bawah wireshark ada Packets: 9596`

- Output result

  `put your output result here`

<br>
<br>

> b. Ada berapa jenis protocol (total) yang terekam pada traffic?

> _b. How many types of protocol (totals) are recorded in the traffic?_

**Answer:** `12`

- Filter expression

  ``

- Explanation

  `Statistic->Protocol Hierarcy`

- Output result

  `Frame, Linux cooked-mode capture, Internet Protocol Version 4 (IPv4), Transmission Control Protocol (TCP), Virtual Network Computing, Thrift Protocol, Telnet, Signaling Compression, Hypertext Transfer Protocol (HTTP), JavaScript Object Notation (JSON), HiPerConTracer Trace Service, Data`

<br>
<br>

> c. Ada berapa jenis protocol berbasis TCP yang terekam pada traffic?

> _c. How many types of TCP-based applications protocol are recorded in the traffic?_

**Answer:** `8`

- Filter expression

  ``

- Explanation

  `Statistic->Protocol Hierarcy`

- Output result

  `Virtual Network Computing (VNC), Thrift Protocol, Telnet, Signaling Compression, Hypertext Transfer Protocol (HTTP), JavaScript Object Notation (JSON), HiPerCon Tracer Trace Service, Data`

  <br>
  <br>

> d. Ada berapa banyak packet dengan protokol TCP murni yang terekam pada traffic (tanpa data)?

> _d. How many packets with pure TCP protocol are recorded in the traffic (without data)?_

**Answer:** `3223`

- Filter expression

  `_ws.col.protocol == "TCP" and not data`

- Explanation

  `Filter ini menampilkan hanya paket TCP yang tidak membawa payload`

- Output result

  `Displayed: 3223`

## Task 2

- Flag

  `JARKOM25{N1c3_0ne_b4nggg_EGZFQSSLRRyuMM13yhyjivywnurdhqwbhvmxpc3r4t0ps74482165123619669690_1c2077eac488c322347a440d11296775}`

> a. Berapa banyak packet berhasil yang berbasis murni TCP dan memiliki flag [ACK]?

> _a. How many packets succeed that are pure TCP based and have [ACK] flag?_

**Answer:** `3209`

- Filter expression

  `_ws.col.protocol == "TCP" and not data and tcp.flags.ack ==1`

- Explanation

  `menampilkan semua paket TCP tanpa payload yang memiliki flag ACK. ada 2 row hitam`

- Output result

  `3211 terus dikurangi 2 yang hitam, hasilnya 3209 `

  <br>
  <br>

> b. Berapa banyak packet berhasil yang berbasis murni TCP yang hanya memiliki flag [ACK]?

> _b. How many packets succeed that are pure TCP based and have only [ACK] flag?_

**Answer:** `3172`

- Filter expression

  `tcp.len == 0 && tcp.flags.ack ==1 and tcp.flags.fin == 0 and tcp.flags.syn == 0`

- Explanation

  `mengambil paket ACK murni tanpa flag tambahan seperti SYN atau FIN`

- Output result

  `3172`

  <br>
  <br>

> c. Berapa banyak packet berhasil yang berbasis murni TCP dan memiliki flag selain hanya [ACK]?

> _c. How many packets succeed that are pure TCP based and contain flags other than just [ACK] flag?_

**Answer:** 
  `49`

- Filter expression

  `_ws.col.protocol == "TCP" and not data and tcp.flags != 0x010`

- Explanation

  `mengecualikan ACK murni dan menampilkan paket pure TCP dengan flag lain`

- Output result

  `49`

  <br>
  <br>

## Task 3

- Flag

  `put your flag here`

> a. Pada port berapa client telnet terbuka?

> _a. In what port is the telnet client open?_

**Answer:** `JARKOM25{W0w_Y0uU_h4V33e_d0n3_444_90od_j0bB_5MOR3g0dl1k332sy0r4s6gvvegmtkappci_ad33f5afdf30c9ceb63504c973d27105}`

- Filter expression

  `telnet`

- Explanation

  `klik kanan paket telnet
follow->tcp stream
pencet entire conversation
172.16.16.102:23-172.16.16.101:54184 (1449 bytes)`

- Output result

  `port client 54184`

  <br>
  <br>

> b. Berapa byte file response yang dikirim dari server?

> _b. How many bytes of the response files are sent from the server?_

**Answer:** 
  `1449`

- Filter expression

  `telnet`

- Explanation

  `klik kanan paket telnet
follow->tcp stream
pencet entire conversation
172.16.16.102:23-172.16.16.101:54184 (1449 bytes)`

- Output result

  `1449 bytes`

  <br>
  <br>

> c. Apa username yang digunakan client telnet untuk berhubungan dengan server?

> _c. What telnet client's username is used to connect with the server?_

**Answer:** 
  `jovyan`

- Filter expression

  `telnet`

- Explanation

  `klik kanan paket telnet
follow->tcp stream`

- Output result

  `f3d75df06236 login: 
j
j
o
o
v
v
y
y
a
a
n
n

.

Password: 
123
.`

  <br>
  <br>

> d. Apa password client telnet?

> _d. What is the telnet client's password?_

**Answer:** 
  `123`
- Filter expression

  `telnet`

- Explanation

  `klik kanan paket telnet
follow->tcp stream`

- Output result

   `f3d75df06236 login: 
j
j
o
o
v
v
y
y
a
a
n
n

.

Password: 
123
.`

  <br>
  <br>

## Task 4

- Flag

  `JARKOM25{G04t__a4n4liz333er_BE639W6QN5T0X7EFDXO8fr0gnm5gv6wlskz17utr3ys9533977147_6029e4c45f6508daf3a03cf1933336c7}`

> a. Apa perintah pertama yang ditulis client pada koneksi telnet?

> _a. What is the first command that client wrote on telnet connection?_

**Answer:** `echo`

- Filter expression

  `telnet`

- Explanation

  `
klik kanan paket telnet
follow->tcp stream`

- Output result

  `e
e
c
c
h
h
o
o
 
 
"
"
F
F
a
a
l
l
e
e
.
. .
.
. .
k
k
e
e
F
F
l
l
a
a
g
g
{
{
L
L
i
i
n
n
g
g
G
G
a
a
n
n
g
g
G
G
u
u
_
_
.
. .
}
}
"
"`

  <br>
  <br>

> b. Apa nama file .txt di server (ditulis bersama ekstensinya)?

> _b. What is the name of .txt file on the server (write with the extension)?_

**Answer:** `test.txt`

- Filter expression

  `telnet`

- Explanation

  `klik kanan paket telnet
follow->tcp stream`

- Output result

  `c
c
a
a
t
t
.
. .
.
. .
.
. .
l
l
s
s

.

.[?2004l
.test.txt`

  <br>
  <br>

> c. Apa kata pertama dari frasa yang dimasukkan client ke dalam file sebelumnya?

> _c. What is the first word that the client inserted into the previous file?_

**Answer:** `Jarkom`

- Filter expression

  `telnet`
- Explanation

  `klik kanan paket telnet
follow->tcp stream`

- Output result

  `c
c
a
a
t
t
 
 
t
t
e
e
s
s
t
t
	
.txt 

.

.[?2004l
.Halo gan
Jarkom gampang`

  <br>
  <br>

## Task 5

- Flag

  `JARKOM25{n4il0ng_m1lk_dr4g000n_TMSRXZFRLGJQSP86QL3722N6085V6Ncr0c796g63i5xpfj14covot5b435_9874b289b2c568cee1115c0a120f68ea}`

> a. Berapa banyak packet berbasis HTTP yang terekam pada file pcapng?

> _a. How many HTTP packets are recorded in the pcapng file?_

**Answer:** `298`

- Filter expression

  `http`

- Explanation

  `lihat displayed`

- Output result

  `298`

  <br>
  <br>

> b. Ada berapa HTTP packet yang berupa response?

> _b. How many response HTTP packets are recorded in the traffic?_

**Answer:** `149`

- Filter expression

  `http.response`

- Explanation

  `cek displayed`

- Output result

  `149`

  <br>
  <br>

> c. Ada berapa paket berbasis HTTP yang berhasil?

> _c. How many HTTP packets that succeed?_

**Answer:** `296`

- Filter expression

  `http`

- Explanation

  `cek yang tidak berhasil ada 2`

- Output result

  `298-2=296`

  <br>
  <br>

> d. Apa alamat IP dari client HTTP yang tersambung lokal dengan mesin lain?

> _d. What is the client HTTP IP Address in connection with other local machine?_

**Answer:** `172.16.16.101`

- Filter expression

  `http`

- Explanation

  `cek kolom source`

- Output result

  `172.16.16.101`

  <br>
  <br>

## Task 6

- Flag

  `JARKOM25{br0mb44rdin0u_Cr0ccc0c0c0cdi1l10l_3586656793awaesajwpp8deujush1n0bu0RPTFNNVGD99KGA_75dfeef6fc3dcbecdf37a6d493f8cd81}`

> a. Apakah kamu menemukan fake flag? Tuliskan seluruhnya!

> _a. Did you find the fake flag? Write it whole!_

**Answer:** `FakeFlag{JarkomGampang}`

- Filter expression

  `frame contains "Flag{"`

- Explanation

  `follow->tcp stream`

- Output result

  `FakeFlag{JarkomGampang}`

  <br>
  <br>

> b. Tuliskan username dan password yang tertulis! (format username:password)

> _b. Write the written username and password! (format username:password)_

**Answer:** `Rey:123`

- Filter expression

  `http`

- Explanation

  `ctrl+f masukkan pass
  28431	122.631706942	172.16.16.101	172.16.16.102	HTTP	148	GET /passwd.txt HTTP/1.1 
  follow->TCP Stream`

- Output result

  `GET /passwd.txt HTTP/1.1
  Host: mesin2
  User-Agent: curl/7.81.0
  Accept: */*
  
  
  HTTP/1.0 200 OK
  Server: SimpleHTTP/0.6 Python/3.11.6
  Date: Sat, 23 Aug 2025 11:02:14 GMT
  Content-type: text/plain
  Content-Length: 8
  Last-Modified: Sat, 23 Aug 2025 11:00:52 GMT
  
  Rey
  123`

  <br>
  <br>

## Task 7

- Flag

  `JARKOM25{tr4l4lel0_tr1lil1_ka6j638t8zk3b0s0sZR070PACK684M3N_3fcae58ce53dbe87ce1bacd9c0001d22}`

> Apa nama gambar yang direquest oleh client? (tulis dengan ekstensinya)

> _What is the image that is being requested by the client? (write with its extension)_

**Answer:** `donalbebek.jpg`

- Filter expression

  `http.request && ip.src == 172.16.16.101`

- Explanation

  `53453	234.535382648	172.16.16.101	172.16.16.102	HTTP	152	GET /donalbebek.jpg HTTP/1.1`

- Output result

  `donalbebek.jpg`

  <br>
  <br>

## Task 8

- Flag

  `JARKOM25{y0u_4r3_s0_G00d_1n_F0r3nsic_J8JGV03STJBDIINSFLS7P12XXH8F8Nx45y4n6jyrovj2nqf991hph4icoaa9_3bf7b077bbd416f665ab9a4866589b6a}`

> a. Berapa banyak packet berbasis FTP yang terekam pada file pcapng? (with the data)

> _a. How many FTP packets are recorded in the pcapng file? (with the data)_

**Answer:** `81`

- Filter expression

  `ftp || ftp-data`

- Explanation

  `cek displayed`

- Output result

  `81`

  <br>
  <br>

> b. Apa username dan password client di koneksi FTP? (tulis dalam format username:password)

> _b. What is the client's username and password in FTP connection? (write in following format username:password)_

**Answer:** `rey:password123lingangu`

- Filter expression

  `ftp.request.command`

- Explanation

  `menampilkan paket FTP yang berupa perintah`

- Output result

  `rey:password123lingangu`

  <br>
  <br>

> c. What is the client's command for showing server directory that was sent on request packet?

> _c. Apa command client untuk melihat direktori server yang dikirimkan dalam request packet?_

**Answer:** `LIST`

- Filter expression

  `ftp.request`

- Explanation

  `cari LIST=menampilkan isi direktori`

- Output result

  `LIST`

  <br>
  <br>

## Task 9

- Flag

  `JARKOM25{j4rk000000mmm_g4mpp4444n9999999_10274654628i41L4hnv2poz0t26321k0ncolQ26HKII1M8SK5UN_c012837a90cc83e3ad7837481fd42cb3}`

> a. Apa alamat IP dari FTP server?

> _a. What is the FTP server IP Address?_

**Answer:** `172.16.16.101`

- Filter expression

  `ftp-data`

- Explanation

  `lihat kolom source`

- Output result

  `172.16.16.101`

  <br>
  <br>

> b. Berapa banyak file yang ada dalam direktori FTP server?

> _b. How many files are there inside the FTP server directory?_

**Answer:** `7`

- Filter expression

  `ftp-data`

- Explanation

  `cari yang LIST
follow->TCP Stream`

- Output result

  `klik kanan ->Follow->Tcp Stream
drwxr-xr-x   2 jovyan   users        4096 Aug 24 14:12 .ipynb_checkpoints
-rw-r--r--   1 jovyan   users       10729 Aug 24 13:58 donalbebek.jpg
-rwxr-xr-x   1 jovyan   users       15702 Aug 24 14:01 jakipon.png
-rwxr-xr-x   1 jovyan   users       15702 Aug 24 14:00 mike.pdf
-rwxr-xr-x   1 jovyan   users          86 Aug 24 14:10 page.html
-rwxr-xr-x   1 jovyan   users       15702 Aug 24 13:41 pokijan.jpg
-rwxr-xr-x   1 jovyan   users       75007 Aug 24 13:41 research_center.jpg
-rw-r--r--   1 jovyan   users         256 Aug 24 14:12 secret.txt`

  <br>
  <br>

> c. Apa nama dari file yang digunakan dalam page.html? (tulis lengkap namanya beserta ekstensinya dan dipisahkan dengan koma ',')

> _c. What are the filenames used in the page.html? (write the filebames with their extensions and separate them with comma ',')_

**Answer:** `pokijan.jpg,research_center.jpg`

- Filter expression

  `ftp-data`

- Explanation

  `cari html
3638	22.728843994	172.16.16.101	172.16.16.102	FTP-DATA	154	FTP Data: 86 bytes (EPASV) (RETR page.html)
follow->tcp stream`

- Output result

```
<html>
  <body>
    <img src="pokijan.jpg">
    <img src="research_center.jpg">
  </body>
</html>
```

## Task 10

- Flag
- 
`JARKOM25{f1nisssshs55s5s533s_l1n333ee333E3_66467918822910y1rn9qom45345215123123WEZIZ1P8AAHD4PH_32a66eced5f136725967a162c0d39025}`

> a. Apa nama file yang mengandung string terencode?

> _a. What is the filename that contains encoded string?_

**Answer:** `secret.txt`

- Filter expression

  `ftp`

- Explanation

  `cari file yang mungkin mengandung encoded string`

- Output result

  `secret.txt`

  <br>
  <br>

> b. Apa nama file hasil copy file sebelumnya?

> _b. What is the filename of the previous file copy?_

**Answer:** `secret1.txt`

- Filter expression

  `ftp`

- Explanation

  `Ada perintah STOR secret1.txt.
STOR digunakan untuk menyimpan/upload file ke server, jadi di sini server membuat salinan dari secret.txt menjadi secret1.txt.`

- Output result

  `secret1.txt`

  <br>
  <br>

> c. What is the decoded string from the previous file?

> _c. Apa decoded string dari file tersebut?_

**Answer:** `Pada suatu hari Rey bertemu dengan Nailong the Milk Dragon. Ketika bertemu, Rey mengajarkan Nailong apa itu Jaringan Komputer. Nailong pun senang karena ternyata Jaringan Komputer itu gampang.`

- Filter expression

  `ftp-data`

- Explanation

  `7630	52.286252464	172.16.16.101	172.16.16.102	FTP-DATA	324	FTP Data: 256 bytes (EPASV) (RETR secret.txt)
follow->tcp stream
UGFkYSBzdWF0dSBoYXJpIFJleSBiZXJ0ZW11IGRlbmdhbiBOYWlsb25nIHRoZSBNaWxrIERyYWdvbi4gS2V0aWthIGJlcnRlbXUsIFJleSBtZW5nYWphcmthbiBOYWlsb25nIGFwYSBpdHUgSmFyaW5nYW4gS29tcHV0ZXIuIE5haWxvbmcgcHVuIHNlbmFuZyBrYXJlbmEgdGVybnlhdGEgSmFyaW5nYW4gS29tcHV0ZXIgaXR1IGdhbXBhbmcu

ke cipher identifier

dapet kalau itu Base64 encoded string

pake python untuk decode 

import base64

encoded = "UGFkYSBzdWF0dSBoYXJpIFJleSBiZXJ0ZW11IGRlbmdhbiBOYWlsb25nIHRoZSBNaWxrIERyYWdvbi4gS2V0aWthIGJlcnRlbXUsIFJleSBtZW5nYWphcmthbiBOYWlsb25nIGFwYSBpdHUgSmFyaW5nYW4gS29tcHV0ZXIuIE5haWxvbmcgcHVuIHNlbmFuZyBrYXJlbmEgdGVybnlhdGEgSmFyaW5nYW4gS29tcHV0ZXIgaXR1IGdhbXBhbmcu"
decoded = base64.b64decode(encoded).decode('utf-8')
print(decoded)
`

- Output result

  `Pada suatu hari Rey bertemu dengan Nailong the Milk Dragon. Ketika bertemu, Rey mengajarkan Nailong apa itu Jaringan Komputer. Nailong pun senang karena ternyata Jaringan Komputer itu gampang.`

  <br>
  <br>

## Summary

## Problems
Beberapa filter Wireshark yang digunakan untuk menjawab soal tidak pernah diajarkan secara langsung di materi sebelumnya, sehingga untuk menemukan ekspresi filter yang tepat diperlukan proses trial and error terlebih dahulu. Hal ini cukup memakan waktu karena harus mencoba berbagai kombinasi filter sampai benar-benar menampilkan hasil sesuai kebutuhan.

Selain itu, terdapat juga beberapa bagian analisis yang tidak diberikan contoh konkrit di materi. Akibatnya, harus mencari tahu sendiri melalui eksplorasi fitur Wireshark maupun referensi tambahan di luar materi praktikum.
