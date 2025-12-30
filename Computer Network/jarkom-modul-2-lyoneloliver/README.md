[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/1niUih_B)

| Name | NRP | Kelas |
| --- | --- | --- |
| Lyonel Oliver Dwiputra | 5025241145 | A |

## Put your topology config image here!

<img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/afbb44f4-e4d7-4a7b-9c71-b429c919c4a5" />

## Put your GNS3 Project file here!

[GNS3 Project File](https://drive.google.com/file/d/1jPC1nmwJN2drdNP90UpNjo8GBL09K-mb/view?usp=drive_link)

<br>

## Soal 1

> Dokumentasikan hasil pengelompokan subnet yang telah dibuat.
>
> _Document the results of the subnet grouping that has been created._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/1fd865df-6447-47a1-bfdc-73899cb8bbd5" />

- Explanation

  <img width="931" height="190" alt="image" src="https://github.com/user-attachments/assets/caf8b3ae-bae7-4292-9e9c-5f18743f958c" />

<br>

## Soal 2

> Lakukan konfigurasi routing agar setiap node dapat saling berkomunikasi. Pastikan setiap router dapat mengirimkan paket ke jaringan lain melalui tabel routing yang sesuai. Sertakan bukti bahwa Falcon bisa melakukan ping ke SpiderMan, DoctorStrange, dan ScarletWitch.
>
> _Configure routing so that each node can communicate with each other. Ensure each router can forward packets to other networks through the appropriate routing table. Include proof that Falcon can ping SpiderMan, Doctor Strange, and ScarletWitch._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/18cfa9e5-6aa1-471a-ba5c-37d42f83f55b" />

- Explanation

  Langkah persiapan awal untuk instalasi, dan konfigurasi routing final.

  **IronMan**
  ```bash
  ip a add 192.168.122.50/24 dev eth0
  ip route add default via 192.168.122.1
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  apt-get update
  apt-get install -y isc-dhcp-client

  ip addr flush dev eth0
  dhclient -v eth0
  ```

  Sysctl untuk mengaktifkan forwarding, ip addr add untuk interface internal (eth1, eth2), dan ip route add untuk menambahkan rute ke semua subnet di bawahnya.

  **IronMan**
  ```bash
  sysctl -w net.ipv4.ip_forward=1
  ip addr add 10.47.1.1/24 dev eth1
  ip addr add 10.47.2.1/24 dev eth2
  ip route add 10.47.3.0/24 via 10.47.1.2
  ip route add 10.47.4.0/24 via 10.47.1.2
  ip route add 10.47.5.0/24 via 10.47.2.2
  ip route add 10.47.6.0/24 via 10.47.2.2
  ip route add 10.47.7.0/24 via 10.47.2.2
  iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  ```

  Sysctl untuk mengaktifkan forwarding, ip addr add untuk interface internal (eth1, eth2), dan ip route add untuk menambahkan rute ke semua subnet di bawahnya.

  **BlackPanther**
  ```bash
  sysctl -w net.ipv4.ip_forward=1
  ip addr add 10.47.1.2/24 dev eth0
  ip addr add 10.47.3.1/24 dev eth1
  ip addr add 10.47.4.1/24 dev eth2
  ip route add default via 10.47.1.1
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  ```

  Sysctl untuk forwarding, ip addr add untuk interfacenya, ip route add 10.47.7.0/24 sebagai rute spesifik ke subnet di bawah Vision, dan ip route add default ke IronMan.

  **BlackWidow**
  ```bash
  sysctl -w net.ipv4.ip_forward=1
  ip addr add 10.47.2.2/24 dev eth0
  ip addr add 10.47.6.1/24 dev eth1
  ip addr add 10.47.5.1/24 dev eth2
  ip route add 10.47.7.0/24 via 10.47.6.2
  ip route add default via 10.47.2.1
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  ```

  Sysctl untuk forwarding, ip addr add untuk interfacenya, dan ip route add default ke BlackWidow.

  **Vision**
  ```bash
  sysctl -w net.ipv4.ip_forward=1
  ip addr add 10.47.6.2/24 dev eth0
  ip addr add 10.47.7.1/24 dev eth1
  ip route add default via 10.47.6.1
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  ```

<br>

## Soal 3

> Lakukan konfigurasi agar semua node dapat terhubung ke internet. Sertakan hasil uji coba dengan melakukan ping ke google.com dari node Falcon, CaptainAmerica, SpiderMan, dan Thor.
>
> _Configure all nodes to connect to the internet. Include test results by pinging google.com from the Falcon, CaptainAmerica, SpiderMan, and Thor nodes._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/be87b0a7-20f1-411e-815a-2d2eaecaaf6b" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/89babeed-f12e-4edb-b23e-2b4f55dec2d0" />
  <img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/2165dfb3-c2fd-4e65-92a8-cfad51c1591e" />

- Explanation

  Perintah `dhclient -v eth0` untuk mendapatkan IP publik/internet dan perintah `iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE` yang mengaktifkan NAT.

  **IronMan**
  ```bash
  dhclient -v eth0
  sysctl -w net.ipv4.ip_forward=1
  ip addr add 10.47.1.1/24 dev eth1
  ip addr add 10.47.2.1/24 dev eth2
  ip route add 10.47.3.0/24 via 10.47.1.2
  ip route add 10.47.4.0/24 via 10.47.1.2
  ip route add 10.47.5.0/24 via 10.47.2.2
  ip route add 10.47.6.0/24 via 10.47.2.2
  ip route add 10.47.7.0/24 via 10.47.2.2
  iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
  echo "nameserver 8.8.8.8" > /etc/resolv.conf
  ```

  Membuktikan koneksi internet dengan:
  ```bash
  ping google.com -c 3
  ```

<br>

## Soal 4

> Berikan Falcon alamat IP dalam rentang [Prefix IP].3.20 - [Prefix IP].3.25
> <br> </br>
> Berikan Hawkeye alamat IP dalam rentang [Prefix IP].4.30 - [Prefix IP].4.35
> <br> </br>
> Berikan Hulk alamat IP dalam rentang [Prefix IP].6.50 - [Prefix IP].6.55
>
> _Give Falcon an IP address in the range [IP Prefix].3.20 - [IP Prefix].4.35_
> <br> </br>
> _Give Hawkeye an IP address in the range [IP Prefix].4.30 - [IP Prefix].4.35_
> <br> </br>
> _Give Hulk an IP address in the range [IP Prefix].6.50 - [IP Prefix].6.55_

**Answer:**

- Screenshot

  <img width="1600" height="902" alt="image" src="https://github.com/user-attachments/assets/98ee11fc-5bd9-4c3e-bf26-b4696a82e0ce" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/cde9393f-fd04-46f7-b817-cb44e0b358e3" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/ad17d737-a3bc-4af3-ac87-c750c90659d0" />

- Explanation

  Baris range di dalam subnet 10.47.3.0 (untuk Falcon), subnet 10.47.4.0 (untuk Hawkeye), dan subnet 10.47.6.0 (untuk Hulk). Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Soal 5

> Berikan ScarletWitch dan Thor alamat IP dalam rentang [Prefix IP].5.40 - [Prefix IP].5.45 dan [Prefix IP].5.100 - [Prefix IP].5.105
>
> _Give ScarletWitch and Thor IP addresses in the range [IP Prefix].5.40 - [IP Prefix].5.45 and [IP Prefix].5.100 - [IP Prefix].5.105_

**Answer:**

- Screenshot

  <img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/b5c4f43c-f200-4d97-8e71-603779f88755" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/33c14e09-e2a3-4e87-8184-5cad3f1bb1ed" />

- Explanation

  Dua baris range di dalam blok subnet 10.47.5.0 pada file dhcpd.conf. Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Soal 6

> Berikan SpiderMan dan DoctorStrange alamat IP dalam rentang [Prefix IP].7.60 - [Prefix IP].7.65  dan [Prefix IP].7.110 - [Prefix IP].7.115
>
> _Give SpiderMan and DoctorStrange IP addresses in the ranges [IP Prefix].7.60 - [IP Prefix].7.65 and [IP Prefix].7.110 - [IP Prefix].7.115_

**Answer:**

- Screenshot

  <img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/ed6c6598-ef6b-49a3-ac14-b0848275a161" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/cd62cd4b-3290-4720-a39f-b13db934d4aa" />

- Explanation

  Dua baris range di dalam blok subnet 10.47.7.0 pada file dhcpd.conf. Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Soal 7

> Tetapkan waktu peminjaman alamat IP pada DHCP server untuk client yang terhubung melalui Switch 2 selama 5 menit (Default), dan untuk client melalui Switch 5 selama 10 menit (Default). Tetapkan juga batas waktu peminjaman maksimal selama 2 jam.
> <br> </br>
> Tetapkan waktu peminjaman alamat IP pada DHCP server untuk client yang terhubung melalui Switch 1 dan Switch 3 selama 2 menit (Default). Tetapkan juga batas waktu peminjaman maksimal selama 100 menit.
>
> _Set the IP address lease period on the DHCP server for clients connected through Switch 2 to 5 minutes (default), and for clients connected through Switch 5 to 10 minutes (default). Also, set the maximum lease period to 2 hours._
> <br> </br>
> _Set the IP address lease time on the DHCP server for clients connected via Switch 1 and Switch 3 to 2 minutes (default). Also set the maximum lease time limit to 100 minutes._

**Answer:**

- Screenshot

  <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/928fa109-64e2-4321-82f9-7fc33675956c" />
  <img width="1919" height="937" alt="image" src="https://github.com/user-attachments/assets/288508c1-a797-477d-a090-6fac11ad869b" />

- Explanation

  Baris `default-lease-time` dan `max-lease-time` di dalam blok subnet untuk 10.47.3.0, 10.47.4.0, 10.47.5.0, dan 10.47.7.0. Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Soal 8

> Ubah konfigurasi DHCP Server agar Hawkeye, Thor, dan SpiderMan mendapatkan IP statis dengan [Prefix IP].x.5, namun masih menggunakan DHCP.
>
> _Change the DHCP Server configuration so that Hawkeye, Thor, and SpiderMan get static IPs with [Prefix IP].x.5, but still use DHCP._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/3cf65e38-d631-468b-82b5-3997eb833f02" />
  <img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/ff056559-a72a-48ba-8e51-100e7a41feb8" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/b9bb9316-4bb2-48b3-bba1-17e618975d8a" />

- Explanation

  Tiga blok host (hawkeye, thor, spiderman) di akhir file dhcpd.conf. Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Soal 9

> Buatlah konfigurasi DHCP Failover dengan WinterSoldier sebagai DHCP server backup untuk CaptainAmerica.
>
> _Create a DHCP Failover configuration with WinterSoldier as the backup DHCP server for CaptainAmerica._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/78c3a4b7-cc3c-436a-a512-9a1300824c1f" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/4a6860f2-ef6b-412e-8734-d53f00339408" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/bc3864f6-f68b-464a-a7db-5363c802a59e" />

- Explanation

  Blok `failover peer "..."` dan penggunaan `failover peer "avengers-failover";` di dalam setiap pool pada file dhcpd.conf.

  **CaptainAmerica**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }

  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }

  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

  **WinterSoldier**
  `/etc/dhcp/dhcpd.conf`

  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      secondary;
      address 10.47.4.2;
      port 647;
      peer address 10.47.3.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }

  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }

  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

  **Konfigurasi Relay**

  **BlackPanther**
  `nano /etc/default/isc-dhcp-relay`
  ```bash
  SERVERS="10.47.3.2 10.47.4.2"
  INTERFACES="eth1 eth2"
  OPTIONS=""
  ```

  **BlackWidow**
  `nano /etc/default/isc-dhcp-relay`
  ```bash
  SERVERS="10.47.3.2 10.47.4.2"
  INTERFACES="eth0 eth2"
  OPTIONS=""
  ```

  **Vision**
  `nano /etc/default/isc-dhcp-relay`
  ```bash
  SERVERS="10.47.3.2 10.47.4.2"
  INTERFACES="eth0 eth1"
  OPTIONS=""
  ```

<br>

## Soal 10

> Buatlah konfigurasi agar CaptainAmerica dan WinterSoldier berjalan dengan mode Load Balancing.
>
> _Create a configuration so that CaptainAmerica and WinterSoldier run in Load Balancing mode._

**Answer:**

- Screenshot

  <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/928fa109-64e2-4321-82f9-7fc33675956c" />
  <img width="1919" height="937" alt="image" src="https://github.com/user-attachments/assets/288508c1-a797-477d-a090-6fac11ad869b" />

- Explanation

  Baris `split 128;` (membagi beban 50/50) dan `load balance max seconds 3;` di dalam blok `failover peer` pada file dhcpd.conf. Konfigurasi WinterSoldier identik untuk soal ini.

  **CaptainAmerica dan WinterSoldier**
  ```bash
  option domain-name "avengers.local";
  option domain-name-servers 8.8.8.8, 8.8.4.4;
  default-lease-time 600;
  max-lease-time 7200;
  authoritative;
  log-facility local7;

  failover peer "avengers-failover" {
      primary;
      address 10.47.3.2;
      port 647;
      peer address 10.47.4.2;
      peer port 647;
      max-response-delay 60;
      max-unacked-updates 10;
      mclt 3600;
      split 128;
      load balance max seconds 3;
  }

  subnet 10.47.3.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.3.20 10.47.3.25;
      }
      option routers 10.47.3.1;
      option broadcast-address 10.47.3.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.4.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.4.30 10.47.4.35;
      }
      option routers 10.47.4.1;
      option broadcast-address 10.47.4.255;
      default-lease-time 300;
      max-lease-time 7200;
  }

  subnet 10.47.5.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.5.40 10.47.5.45;
          range 10.47.5.100 10.47.5.105;
      }
      option routers 10.47.5.1;
      option broadcast-address 10.47.5.255;
      default-lease-time 120;
      max-lease-time 6000;
  }

  subnet 10.47.6.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.6.50 10.47.6.55;
      }
      option routers 10.47.6.1;
      option broadcast-address 10.47.6.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  subnet 10.47.7.0 netmask 255.255.255.0 {
      pool {
          failover peer "avengers-failover";
          range 10.47.7.60 10.47.7.65;
          range 10.47.7.110 10.47.7.115;
      }
      option routers 10.47.7.1;
      option broadcast-address 10.47.7.255;
      default-lease-time 600;
      max-lease-time 7200;
  }

  host hawkeye {
      hardware ethernet 02:42:74:3d:ef:00;
      fixed-address 10.47.4.5;
  }
  host thor {
      hardware ethernet 02:42:b7:0f:ca:00;
      fixed-address 10.47.5.5;
  }
  host spiderman {
      hardware ethernet 02:42:10:d0:6f:00;
      fixed-address 10.47.7.5;
  }
  ```

<br>

## Problems

## Revisions (if any)