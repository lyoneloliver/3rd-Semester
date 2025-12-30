[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/oYnIPZ_t)
| Name           | NRP        | Kelas     |
| ---            | ---        | ----------|
| Lyonel Oliver Dwiputra| 5025241145 | A |



## Put your topology config image here!

<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/cd74076a-b453-4c68-a33e-9464ace104bb" />


## Put your GNS3 Pr
oject file here!

https://drive.google.com/file/d/1S-9ZoPHDffOfYZHwC7jabGThPqumITRZ/view?usp=sharing

<br>

## Soal 1

> Lakukan subnetting pada topologi diatas menggunakan metode VLSM: [Referensi](https://github.com/arsitektur-jaringan-komputer/Modul-Jarkom/tree/master/Modul-4/Subnetting#2-vlsm-variable-length-subnet-masking)  
*Cantumkan juga tabel dan diagram pembagian subnet pada laporan praktikum*.


> _Subnet the topology above using the VLSM method: [Reference](https://github.com/arsitektur-jaringan-komputer/Modul-Jarkom/tree/master/Modul-4/Subnetting#2-vlsm-variable-length-subnet-masking)_  
_Also include the subnet table and diagram in the lab report._

**Answer:**

- Screenshot

<img width="981" height="413" alt="image" src="https://github.com/user-attachments/assets/3f2880ef-98dc-4cb4-861e-c63844b1bd6a" />


- Explanation

IT-PC-1 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.1
    netmask 255.255.255.128
    gateway 10.47.2.126
```
HR-PC-1 
```bash
auto eth0
iface eth0 inet static
    address 10.47.0.1
    netmask 255.255.254.0
    gateway 10.47.1.254
```
Web-Server-1 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.129
    netmask 255.255.255.224
    gateway 10.47.2.158
```
Web-Server-2 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.161
    netmask 255.255.255.224
    gateway 10.47.2.190
```
DB-Server-1 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.225
    netmask 255.255.255.240
    gateway 10.47.2.238
```
DB-Server-2 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.193
    netmask 255.255.255.224
    gateway 10.47.2.222
```
Router-1 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.126
    netmask 255.255.255.128
auto eth1
iface eth1 inet static
    address 10.47.2.241
    netmask 255.255.255.252
```
Router-3 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.250
    netmask 255.255.255.252

auto eth1
iface eth1 inet static
    address 10.47.2.238
    netmask 255.255.255.240

auto eth2
iface eth2 inet static
    address 10.47.2.222
    netmask 255.255.255.224
```
Router-4 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.246
    netmask 255.255.255.252

auto eth1
iface eth1 inet static
    address 10.47.2.158
    netmask 255.255.255.224

auto eth2
iface eth2 inet static
    address 10.47.2.190
    netmask 255.255.255.224

auto eth3
iface eth3 inet static
    address 10.47.2.253
    netmask 255.255.255.252
```
Router-5 
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.254
    netmask 255.255.255.252

auto eth1
iface eth1 inet static
    address 10.47.1.254
    netmask 255.255.254.0
```
Di semua Router 
```bash
echo "net.ipv4.ip_forward=1" >> /etc/sysctl.conf
sysctl -p`
```
<br>

## Soal 2

> Buatlah agar router-2 dapat melakukan koneksi ke internet. [Dapat menggunakan static routing].

> _Make sure router-2 can connect to the internet. [Can use static routing]._

**Answer:**

- Screenshot

  <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/611a0c95-3d32-427f-9458-de11734f0c1a" />


- Explanation
Router-2
```bash
auto eth0
iface eth0 inet static
    address 10.47.2.242
    netmask 255.255.255.252

auto eth1
iface eth1 inet static
    address 10.47.2.245
    netmask 255.255.255.252

auto eth2
iface eth2 inet static
    address 10.47.2.249
    netmask 255.255.255.252

auto eth3
iface eth3 inet static
    address 10.47.3.1
    netmask 255.255.255.252
    up ip route add 0.0.0.0/0 via 10.47.3.2
<br>
```
## Soal 3

> Setelah mengimplementasi subnetting, buatlah agar seluruh topologi dapat terhubung. Lakukan Dynamic Routing pada topologi tersebut.
*Pastikan seluruh node yang ada dapat mengakses internet*.

> _After implementing subnetting, ensure the entire topology is connected. Perform dynamic routing on the topology._  
_Ensure all existing nodes can access the internet._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/59ac2678-398b-40c4-ae6f-4c68cf439a36" />


- Explanation
di semua router 
```bash
vtysh
```
Router-1 
```bash
conf t
router rip
network 10.47.2.0/25
network 10.47.2.240/30
exit
exit
write
```
Router-2 
```bash
conf t
router rip
network 10.47.2.240/30
network 10.47.2.244/30
network 10.47.2.248/30
redistribute static
redistribute kernel
redistribute connected
exit
exit
write
exit

iptables -t nat -A POSTROUTING -o eth3 -j MASQUERADE
```
Router-3 
```bash
conf t
router rip
network 10.47.2.248/30
network 10.47.2.224/28
network 10.47.2.192/27
exit
exit
write
```
Router-4 
```bash
conf t
router rip
network 10.47.2.244/30
network 10.47.2.252/30
network 10.47.2.128/27
network 10.47.2.160/27
exit
exit
write
```
Router-5 
```bash
conf t
router rip
network 10.47.2.252/30
network 10.47.0.0/23
exit
exit
write
```
<br>

## Soal 4

> Lakukan setup web server dengan file html di attachment berikut: [ Attachment ](https://drive.google.com/file/d/199qwfTNJCkxDV7mdO-MsaDdApkmKsnAG/view?usp=sharing)  menggunakan nginx pada “Web-Server-1” dan “Web-Server-2”.  
*Config dibebaskan kepada praktikkan dengan catatan menggunakan port 80*.

> _Set up a web server with the HTML file in the following attachment: [ Attachment ](https://drive.google.com/file/d/199qwfTNJCkxDV7mdO-MsaDdApkmKsnAG/view?usp=sharing) using nginx on “Web-Server-1” and “Web-Server-2”._
_Configuration is free to practice, but note that it uses port 80._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/bff498e4-9165-43d0-a6f1-43de4c365048" />
  <img width="1600" height="901" alt="image" src="https://github.com/user-attachments/assets/b0079d17-800d-405f-855a-48c4777654f8" />



- Explanation

Web-Server-1 & Web-Server-2
```bash
echo "nameserver 8.8.8.8" > /etc/resolv.conf

apt-get update
apt-get install nginx -y

mkdir -p /var/www/jarkom
```

```bash
cat <<EOF > /var/www/jarkom/index.html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Page</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }
        .container {
            text-align: center;
            padding: 20px 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            margin-bottom: 10px;
            font-size: 24px;
            font-weight: 600;
        }
        p {
            margin: 0;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Hello!</h1>
        <p>Web Server | Jarkom Praktikum Modul 4 </p>
    </div>
</body>
</html>
EOF
```

nano /etc/nginx/sites-available/jarkom
```bash
server {
    listen 80;
    server_name _; 

    root /var/www/jarkom;  
    index index.html;

    location / {
        try_files $uri $uri/ =404;
    }
}
```
```bash
ln -s /etc/nginx/sites-available/jarkom /etc/nginx/sites-enabled/
rm /etc/nginx/sites-enabled/default
service nginx restart
```
<br>

## Soal 5

> Kalian diminta untuk melakukan drop semua paket TCP yang masuk  ke subnet HR dengan port 1337 dan 4444. Lakukan testing dengan netcat.

> _You are asked to drop all incoming TCP packets to the HR subnet with ports 1337 and 4444. Test with netcat._

**Answer:**

- Screenshot
  1377
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/c7d0ae26-44f1-4703-8680-9c6ce7d147f5" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/b91cbfef-28fb-4791-bb2f-312b6afe28d6" />
  
  4444
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/ee646e8e-b8e6-458c-b2db-9319fb5b8c4f" />
  <img width="1919" height="930" alt="image" src="https://github.com/user-attachments/assets/75c3611b-d94d-4761-bb89-56b6792aa616" />

  netcat
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/e9e811a0-d09a-461a-9f2d-a24057b55847" />
  <img width="1600" height="901" alt="image" src="https://github.com/user-attachments/assets/cbf074eb-5cfb-4f63-83fb-dd92138c2f7a" />





- Explanation

  Router-5
```bash
echo "nameserver 8.8.8.8" > /etc/resolv.conf

apt-get update
apt-get install iptables -y

iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 1337 -j DROP
iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 4444 -j DROP
```
<br>

## Soal 6

> Lakukan pembatasan sehingga koneksi SSH pada semua Web Server hanya dapat dilakukan oleh user yang berada pada node IT-PC-1, IT-PC-2, dan IT-PC-3. 

> _Implement restrictions so that SSH connections to all Web Servers can only be made by users on nodes IT-PC-1, IT-PC-2, and IT-PC-3._

**Answer:**

- Screenshot

  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/b3219ec4-7273-434b-a136-5fb9ec082417" />
<img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/205949ba-9ee4-423d-8a10-81d7b9b5fbca" />
<img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/b53aa293-c85c-4cb4-bf1f-dfe4dc0c24c2" />



- Explanation

Web-Server-1 & Web-Server-2
```bash
echo "nameserver 8.8.8.8" > /etc/resolv.conf

apt-get install iptables openssh-server -y

service ssh start

iptables -A INPUT -p tcp -s 10.47.2.1 --dport 22 -j ACCEPT
iptables -A INPUT -p tcp -s 10.47.2.2 --dport 22 -j ACCEPT
iptables -A INPUT -p tcp -s 10.47.2.3 --dport 22 -j ACCEPT
iptables -A INPUT -p tcp --dport 22 -j DROP
```
<br>

## Soal 7

> Semua subnet hanya dapat mengakses semua DB-Server pada port 80 dan 443 (DB-Server-1 dan DB-Server-2) pada hari Senin-Sabtu, pukul 07:00- 22:00.

> _All subnets can only access all DB-Servers on ports 80 and 443 (DB-Server-1 and DB-Server-2) on Monday-Saturday, 07:00-22:00._

**Answer:**

- Screenshot
  hari senin jam 10 pagi, maka berhasil
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/f46f2e30-31f1-4400-b7a2-58adf65ec217" />
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/7998b724-4309-4c0c-9926-b7e1f1b57505" />
  
  hari minggu maka gagal
  <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/7de32b03-3c73-45ba-b36e-ba0a34f24e4a" />
  <img width="1910" height="1061" alt="image" src="https://github.com/user-attachments/assets/218b4aba-2f88-4bf6-9cbe-f39200b29665" />







- Explanation

DB-Server-1 & DB-Server-2
```bash
echo "nameserver 8.8.8.8" > /etc/resolv.conf

apt-get update
apt-get install iptables -y

iptables -A INPUT -p tcp -m multiport --dports 80,443 -m time --timestart 07:00 --timestop 22:00 --weekdays Mon,Tue,Wed,Thu,Fri,Sat -j ACCEPT
iptables -A INPUT -p tcp -m multiport --dports 80,443 -j DROP
```
<br>

## Soal 8

> Kemudian, buat agar “Web-Server-1” dan “Web-Server-2” hanya memperbolehkan traffic bertipe HTTP.

> _Then, make sure that “Web-Server-1” and “Web-Server-2” only allow HTTP type traffic._

**Answer:**

- Screenshot
berhasil
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/96e7e51f-7780-439b-b5f0-55a2221289d0" />
  gagal
  <img width="1600" height="894" alt="image" src="https://github.com/user-attachments/assets/16e0f567-0704-4016-ae50-ef7a8a3aed93" />




- Explanation

Web-Server-1 & Web-Server-2
```bash
iptables -A INPUT -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -p tcp -j DROP
```
<br>

## Soal 9

> Pilih salah satu Subnet dan lakukan blokir terhadap semua request protokol ICMP (ping) dari luar subnet terhadap subnet tersebut.

> _Select one of the Subnets and block all ICMP protocol requests (ping) from outside the subnet to that subnet._

**Answer:**

- Screenshot
  gagal
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/900220bb-8390-4ad2-a0ca-357f7faa96b2" />
  
  berhasil
  <img width="1600" height="899" alt="image" src="https://github.com/user-attachments/assets/e3e3f038-0110-4ba0-8c38-9262f6bdad62" />


- Explanation

Router-5
```bash
iptables -A FORWARD -p icmp --icmp-type echo-request -d 10.47.0.0/23 -j DROP
```
<br>

## Soal 10

> Konfigurasikan fitur logging untuk melakukan log terhadap seluruh paket yang di-DROP pada lalu lintas setiap node.

> _Configure the logging feature to log all dropped packets on each node's traffic._

**Answer:**

- Screenshot
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/0ba3fa43-be76-40a2-85fe-617dd355aae9" />

<img width="1913" height="312" alt="image" src="https://github.com/user-attachments/assets/45cc6ac7-4dec-47eb-ae34-76dfa9a3ab27" />


- Explanation


Router-5
```bash
apt-get update
apt-get install ulogd2 -y

iptables -F FORWARD
iptables -A FORWARD -p icmp --icmp-type echo-request -d 10.47.0.0/23 -j LOG --log-prefix "DROP-PING-HR: " --log-level 6
iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 1337 -j LOG --log-prefix "DROP-PORT-1337: " --log-level 6
iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 4444 -j LOG --log-prefix "DROP-PORT-4444: " --log-level 6
iptables -A FORWARD -p icmp --icmp-type echo-request -d 10.47.0.0/23 -j DROP
iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 1337 -j DROP
iptables -A FORWARD -p tcp -d 10.47.0.0/23 --dport 4444 -j DROP
```
Web-Server-1 & Web-Server-2
```bash
iptables -D INPUT -p tcp -j DROP
iptables -A INPUT -p tcp -j LOG --log-prefix "DROP-WEB-TCP: " --log-level 6
iptables -A INPUT -p tcp -j DROP
```
DB-Server-1 & DB-Server-2
```bash
iptables -D INPUT -p tcp -m multiport --dports 80,443 -j DROP
iptables -A INPUT -p tcp -m multiport --dports 80,443 -j LOG --log-prefix "DROP-DB-TIME: " --log-level 6
iptables -A INPUT -p tcp -m multiport --dports 80,443 -j DROP
```
<br>
  
## Problems

## Revisions (if any)
