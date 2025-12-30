[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/e_s827HM)
| Name | NRP | Kelas |
| --- | --- | --- |
| Lyonel Oliver Dwiputra | 5025241145 | A |



## Put your topology config image here!

`<img width="1600" height="807" alt="unnamed" src="https://github.com/user-attachments/assets/6b27a174-3f57-4c1b-ac07-8ee2b7b159f3" />

`


## Put your GNS3 Project file here!

`
https://drive.google.com/file/d/10TlNnlETEjxtnJg4k2UfbcSpJs5xlEWT/view?usp=sharing
`

<br>

## Soal 1

> Setup Topo

> _Document the results of the subnet grouping that has been created._

**Answer:**

- Screenshot

  `<img width="1600" height="806" alt="no 1" src="https://github.com/user-attachments/assets/55fcdebf-0b9e-4907-8c2b-eae6280bb8ba" />
  
`

- Explanation

## Di Node Lune

```bash
ip addr add 10.47.2.1/16 dev eth0
````

## Di Node Sciel

```bash
ip addr add 10.47.2.2/16 dev eth0
```


## Di Node Gustave

```bash
ip addr add 10.47.2.3/16 dev eth0
```



## Di Node Renoir

```bash
ip addr add 10.47.3.1/16 dev eth0
```



## Di Node Verso

```bash
ip addr add 10.47.3.2/16 dev eth0
```



## Di Node Alicia

```bash
ip addr add 10.47.4.1/16 dev eth0
```



## Di Node Maelle

```bash
ip addr add 10.47.5.3/16 dev eth0
```



## Di Node Monocco

```bash
ip addr add 10.47.5.2/16 dev eth0
```



## Di Node Esquie

```bash
ip addr add 10.47.5.1/16 dev eth0
```

setiap node seperti Lune, Sciel, dan Renoir diberikan alamat IP yang unik menggunakan perintah ip addr add


<br>

## Soal 2

> Buatlah konfigurasi untuk domain 
> **lune33.com** → ke IP node Lune , 
> **sciel33.com** → ke IP node Sciel ,
> **gustave33.com** → ke IP node Gustave 
> pada DNS Master Renoir. Kemudian konfigurasikan node Verso sebagai DNS Slave yang bekerja untuk DNS Master Renoir.

> _Dns Configuration , on  the DNS Master (Renoir)_
> _lune33.com → IP of node Lune ,_
> _sciel33.com → IP of node Sciel ,_
> _gustave33.com → IP of node Gustave_
> _Configure Verso as the DNS Slave that works with DNS Master Renoir._

**Answer:**

- Screenshot

  `<img width="1600" height="808" alt="no2 1" src="https://github.com/user-attachments/assets/13c1ebc0-3ba0-4e69-b9eb-01810945287e" />
  
`

- Explanation


## Di RENOIR
```bash
nano /etc/bind/named.conf.local
````

Isi file:

```bash
zone "lune33.com" {
    type master;
    file "/etc/bind/db.lune33.com";
    allow-transfer { 10.47.3.2; }; 
};

zone "sciel33.com" {
    type master;
    file "/etc/bind/db.sciel33.com";
    allow-transfer { 10.47.3.2; }; 
};

zone "gustave33.com" {
    type master;
    file "/etc/bind/db.gustave33.com";
    allow-transfer { 10.47.3.2; }; 
};
```

```bash
nano /etc/bind/db.lune33.com
```

Isi file:

```dns
$TTL    604800
@       IN      SOA     renoir.lune33.com. admin.lune33.com. (
                              2
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.1
```

```bash
nano /etc/bind/db.sciel33.com
```

Isi file:

```dns
$TTL    604800
@       IN      SOA     renoir.sciel33.com. admin.sciel33.com. (
                              2         
                         604800         
                          86400         
                        2419200         
                         604800 )     
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.2      
```

```bash
nano /etc/bind/db.gustave33.com
```

Isi file:

```dns
$TTL    604800
@       IN      SOA     renoir.gustave33.com. admin.gustave33.com. (
                              2         
                         604800         
                          86400         
                        2419200         
                         604800 )       
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.3       
```

```bash
killall named  
named -u bind
```



## Di VERSO

```bash
nano /etc/bind/named.conf.local
```

Isi file:

```bash
zone "lune33.com" {
    type slave;
    file "db.lune33.com"; 
    masters { 10.47.3.1; };
};

zone "sciel33.com" {
    type slave;
    file "db.sciel33.com";
    masters { 10.47.3.1; };
};

zone "gustave33.com" {
    type slave;
    file "db.gustave33.com";
    masters { 10.47.3.1; }; 
};
```

```bash
killall named
named -u bind
```



## Di LUNE, SCIEL, GUSTAVE, ALICIA, ESQUIE, MONOCCO, dan MAELLE

```bash
cat << EOF > /etc/resolv.conf
nameserver 10.47.3.1
nameserver 10.47.3.2
EOF
```



## Di ESQUIE

```bash
dig lune33.com
dig sciel33.com
dig gustave33.com
```

Node Renoir diatur sebagai DNS Master. Tiga domain, yaitu lune33.com, sciel33.com, dan gustave33.com, didaftarkan dan dipetakan ke alamat IP node yang sesuai. Node Verso diatur sebagai DNS Slave, yang otomatis menyalin data DNS dari Renoir. Ini berfungsi sebagai backup jika Renoir mati. Node lainnya kemudian diatur untuk menggunakan Renoir atau Verso sebagai nameserver mereka
<br>

## Soal 3

> Tambahkan subdomain alias berupa exp.lune33.com yang mengarah ke alamat lune33.com dan exp.sciel33.com yang mengarah ke alamat sciel33.com (HINT: CNAME). Selain itu, tambahkan konfigurasi untuk melakukan reverse DNS lookup untuk domain gustave33.com

> _Subdomain Configuration,_ 
> _Add alias subdomains (HINT: CNAME)._
> _exp.lune33.com → alias to lune33.com_
> _exp.sciel33.com → alias to sciel33.com_
> _Also, configure reverse DNS lookup for the domain gustave33.com._

**Answer:**

- Screenshot

  `<img width="1600" height="808" alt="no3 1" src="https://github.com/user-attachments/assets/38d73c72-82e4-44c0-92f6-c80ef0606866" />
  <img width="805" height="362" alt="no3 2" src="https://github.com/user-attachments/assets/ed123c8b-ce6b-4e36-b058-511b19717e85" />

`

- Explanation

## Di RENOIR

Ganti isi file `/etc/bind/db.lune33.com`:
```bash
nano /etc/bind/db.lune33.com
````

```dns
$TTL    604800
@       IN      SOA     renoir.lune33.com. admin.lune33.com. (
                              3
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.1
exp     IN      CNAME   lune33.com.
```

Ganti isi file `/etc/bind/db.sciel33.com`:

```bash
nano /etc/bind/db.sciel33.com
```

```dns
$TTL    604800
@       IN      SOA     renoir.sciel33.com. admin.sciel33.com. (
                              3
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.2
exp     IN      CNAME   sciel33.com.
```

Tambahkan di akhir file `/etc/bind/named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

```bash
zone "143.10.in-addr.arpa" {
    type master;
    file "/etc/bind/db.10.47";
    allow-transfer { 10.47.3.2; }; // Izinkan Verso
};
```

Buat file baru `/etc/bind/db.10.47`:

```bash
nano /etc/bind/db.10.47
```

```dns
$TTL    604800
@       IN      SOA     renoir. admin.renoir. (
                              1
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
```

```bash
killall named && named -u bind
```



## Di VERSO

Tambahkan di akhir file `/etc/bind/named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

```bash
zone "143.10.in-addr.arpa" {
    type slave;
    file "db.10.47";
    masters { 10.47.3.1; }; // Tunjuk ke Renoir
};
```

```bash
killall named && named -u bind
```



## Di ESQUIE

```bash
dig exp.lune33.com
dig exp.sciel33.com
dig -x 10.47.2.3
```

Pertama, CNAME digunakan untuk membuat alias. exp.lune33.com dibuat sebagai nama alias untuk lune33.com, sehingga permintaan ke alias tersebut akan diarahkan ke tujuan yang sama. 
Kedua, Reverse DNS dikonfigurasi untuk mengubah alamat IP (10.47.2.3) kembali menjadi nama domain (gustave33.com), yang merupakan kebalikan dari proses DNS biasa.

<br>

## Soal 4

> Buatlah subdomain berupa expedition.gustave33.com dan delegasikan subdomain tersebut dari Renoir ke Verso dengan alamat IP tujuan adalah node Gustave. Kemudian, matikan Renoir dan coba lakukan ping ke semua domain dan subdomain yang telah dikonfigurasikan pada nomor 2, 3, dan 4.

> _Create a subdomain expedition.gustave33.com and delegate it from Renoir to Verso, with the target IP being node Gustave.Then, turn off Renoir and try pinging all domains and subdomains configured in tasks 2, 3, and 4 to verify delegation works correctly._

**Answer:**

- Screenshot

  `<img width="1600" height="811" alt="no 4" src="https://github.com/user-attachments/assets/31f5bcd9-57cc-4ee5-9cbc-661085c118a9" />
  
`

- Explanation


## Di RENOIR

Edit file zona `gustave33.com`:
```bash
nano /etc/bind/db.gustave33.com
````

Isi file zona (perhatikan baris `expedition`):

```dns
$TTL    604800
@       IN      SOA     renoir.gustave33.com. admin.gustave33.com. (
                              3
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.3
expedition    IN      NS      verso.
```

Restart BIND di Renoir:

```bash
killall named && named -u bind
```



## Di VERSO

```bash
nano /etc/bind/named.conf.local
```

Tambahkan blok zona master di akhir file:

```bash
zone "expedition.gustave33.com" {
    type master;
    file "/etc/bind/db.expedition.gustave33.com";
};
```

```bash
nano /etc/bind/db.expedition.gustave33.com
```

Buat file baru ini dan isi dengan:

```dns
$TTL    604800
@       IN      SOA     verso.expedition.gustave33.com. admin.gustave33.com. (
                              1
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      verso.gustave33.com.
@       IN      A       10.47.2.3
```

```bash
killall named && named -u bind
```


## Di RENOIR

```bash
killall named
```


## Di ESQUIE

```bash
ping -c 3 lune33.com
ping -c 3 exp.lune33.com
ping -c 3 expedition.gustave33.com
```
Renoir, sebagai server DNS utama, diberi instruksi untuk melimpahkan wewenang pengelolaan subdomain expedition.gustave33.com kepada Verso. Akibatnya, Verso bertindak sebagai master baru khusus untuk subdomain tersebut. Pengujian dilakukan dengan mematikan Renoir, dan DNS terbukti tetap berfungsi karena Verso mengambil alih peran.



<br>

## Soal 5

> Konfigurasi node Lune, Sciel, dan Gustave agar berfungsi sebagai web server Nginx yang akan menyajikan halaman profil, dimana halaman profil akan berbeda untuk setiap node. Dari folder berikut, gunakan profile_lune.html untuk menyajikan halaman profil di node Lune, profile_sciel.html untuk menyajikan halaman profil di node Sciel, dan profile_gustave.html untuk menyajikan halaman profil di node Gustave. Konfigurasikan Nginx di setiap node untuk menyimpan custom access log ke file /tmp/access.log dan error log ke file /tmp/error.log. 

> _Configure Lune, Sciel, and Gustave as Nginx web servers serving profile pages, where each node has a unique profile page:_
> _- Use profile_lune.html for Lune_
> _- Use profile_sciel.html for Sciel_
> _- Use profile_gustave.html for Gustave_
> _In each web server, Configure Nginx to store custom logs:_
> _- Access log: /tmp/access.log_
> _- Error log: /tmp/error.log_

**Answer:**

- Screenshot

  `<img width="1600" height="815" alt="no 5 1" src="https://github.com/user-attachments/assets/da092875-dad3-48c3-9509-62e94d1e4e9e" />
  <img width="1600" height="808" alt="no 5 2" src="https://github.com/user-attachments/assets/c23650a0-6121-4b93-8af3-477f478a22f0" />
<img width="1600" height="810" alt="no 5 3" src="https://github.com/user-attachments/assets/db4c8ea2-15e5-4323-99b7-6b740357d86b" />

`

- Explanation

## Di RENOIR

Ganti isi file `/etc/bind/db.gustave33.com`:
```bash
nano /etc/bind/db.gustave33.com
````

```dns
$TTL    604800
@       IN      SOA     renoir.gustave33.com. admin.gustave33.com. (
                              3
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.2.3
expedition    IN      NS      verso.
```

```bash
killall named && named -u bind
```



## Di VERSO

Tambahkan di akhir file `/etc/bind/named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

```bash
zone "expedition.gustave33.com" {
    type master;
    file "/etc/bind/db.expedition.gustave33.com";
};
```

Buat file baru `/etc/bind/db.expedition.gustave33.com`:

```bash
nano /etc/bind/db.expedition.gustave33.com
```

```dns
$TTL    604800
@       IN      SOA     verso.expedition.gustave33.com. admin.gustave33.com. (
                              1
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      verso.gustave33.com.
@       IN      A       10.47.2.3
```

```bash
killall named && named -u bind
```



## Di RENOIR

```bash
killall named
```



## Di ESQUIE

```bash
ping -c 3 lune33.com
ping -c 3 exp.lune33.com
ping -c 3 expedition.gustave33.com
```



## Di RENOIR

```bash
named -u bind
```



## Di LUNE

```bash
mkdir -p /var/www/html

cat << EOF > /var/www/html/profile_lune.html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lune</title>
<style>
  body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    color: #fff;
    background: linear-gradient(270deg, #0a043c, #4a0c25, #7b1b0c, #f9a826);
    background-size: 800% 800%;
    animation: bgShift 20s ease infinite;
  }
  @keyframes bgShift {
    0% {background-position: 0% 50%;}
    50% {background-position: 100% 50%;}
    100% {background-position: 0% 50%;}
  }
  .container {
    max-width: 700px;
    margin: 4rem auto;
    background: rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    padding: 2.5rem;
    box-shadow: 0 4px 20px rgba(0,0,0,0.5);
    backdrop-filter: blur(6px);
  }
  h1 {
    text-align: center;
    color: #00e5ff;
    text-shadow: 0 0 10px #00e5ff;
  }
  h2 {
    color: #ffd166;
    margin-top: 1.5rem;
  }
  p, li { line-height: 1.6; }
  ul { list-style: none; padding-left: 0; }
  li::before { content: "▹ "; color: #00e5ff; }
  footer { text-align: center; font-size: 0.85rem; color: #ccc; margin-top: 2rem; }
</style>
</head>
<body>
  <div class="container">
    <h1>Lune</h1>
    <p><i>“When one falls, we continue.”</i></p>

    <h2>Core Expertise</h2>
    <ul>
      <li>Analysis of Ancient Technologies</li>
      <li>Equipment Calibration & Field Maintenance</li>
      <li>Defense System Deployment</li>
      <li>Data Collection & Anomaly Detection</li>
    </ul>

    <h2>Profile Summary</h2>
    <p>Lune is the mind behind the expedition’s technology.  
       Her understanding of ancient mechanisms and magical artifacts  
       is key to decoding the Paintress’ secrets and keeping the team operational.</p>
  </div>

  <footer>© 2025 Expedition 33 — For Those Who Come After</footer>
</body>
</html>
EOF

cat << EOF > /etc/nginx/sites-available/default
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;
    index profile_lune.html;
    server_name lune33.com;
    location / {
        try_files \$uri \$uri/ =404;
    }
    access_log /tmp/access.log;
    error_log /tmp/error.log;
}
EOF

killall nginx
nginx
```



## Di SCIEL

```bash
mkdir -p /var/www/html

cat << EOF > /var/www/html/profile_sciel.html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sciel</title>
<style>
  body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    color: #fff;
    background: linear-gradient(270deg, #0a043c, #4a0c25, #7b1b0c, #f9a826);
    background-size: 800% 800%;
    animation: bgShift 20s ease infinite;
  }
  @keyframes bgShift {
    0% {background-position: 0% 50%;}
    50% {background-position: 100% 50%;}
    100% {background-position: 0% 50%;}
  }
  .container {
    max-width: 700px;
    margin: 4rem auto;
    background: rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    padding: 2.5rem;
    box-shadow: 0 4px 20px rgba(0,0,0,0.5);
    backdrop-filter: blur(6px);
  }
  h1 {
    text-align: center;
    color: #00e5ff;
    text-shadow: 0 0 10px #00e5ff;
  }
  h2 {
    color: #ffd166;
    margin-top: 1.5rem;
  }
  p, li { line-height: 1.6; }
  ul { list-style: none; padding-left: 0; }
  li::before { content: "▹ "; color: #00e5ff; }
  footer { text-align: center; font-size: 0.85rem; color: #ccc; margin-top: 2rem; }
</style>
</head>
<body>
  <div class="container">
    <h1>Sciel</h1>
    <p><i>“Tomorrow comes.”</i></p>

    <h2>Core Expertise</h2>
    <ul>
      <li>Stealth and Infiltration Tactics</li>
      <li>Long-Range Surveillance</li>
      <li>Navigation and Terrain Mapping</li>
      <li>Rapid Response & Target Acquisition</li>
    </ul>

    <h2>Profile Summary</h2>
    <p>Sciel serves as the eyes and ears of Expedition 33.  
       Agile and perceptive, he scouts ahead, tracks enemy movement,  
       and secures safe passage before every major confrontation.</p>
  </div>

  <footer>© 2025 Expedition 33 — For Those Who Come After</footer>
</body>
</html>
EOF

cat << EOF > /etc/nginx/sites-available/default
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;
    index profile_sciel.html;
    server_name sciel33.com;
    location / {
        try_files \$uri \$uri/ =404;
    }
    access_log /tmp/access.log;
    error_log /tmp/error.log;
}
EOF

killall nginx
nginx
```



## Di GUSTAVE

```bash
mkdir -p /var/www/html

cat << EOF > /var/www/html/profile_gustave.html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gustave</title>
<style>
  body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    color: #fff;
    background: linear-gradient(270deg, #0a043c, #4a0c25, #7b1b0c, #f9a826);
    background-size: 800% 800%;
    animation: bgShift 20s ease infinite;
  }
  @keyframes bgShift {
    0% {background-position: 0% 50%;}
    50% {background-position: 100% 50%;}
    100% {background-position: 0% 50%;}
  }
  .container {
    max-width: 700px;
    margin: 4rem auto;
    background: rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    padding: 2.5rem;
    box-shadow: 0 4px 20px rgba(0,0,0,0.5);
    backdrop-filter: blur(6px);
  }
  h1 {
    text-align: center;
    color: #00e5ff;
    text-shadow: 0 0 10px #00e5ff;
  }
  h2 {
    color: #ffd166;
    margin-top: 1.5rem;
  }
  p, li {
    line-height: 1.6;
  }
  ul {
    list-style: none;
    padding-left: 0;
  }
  li::before {
    content: "▹ ";
    color: #00e5ff;
  }
  footer {
    text-align: center;
    font-size: 0.85rem;
    color: #ccc;
    margin-top: 2rem;
  }
</style>
</head>
<body>
  <div class="container">
    <h1>Gustave</h1>
    <p><i>“For those who come after. Right?”</i></p>

    <h2>Core Expertise</h2>
    <ul>
      <li>Tactical Combat Planning</li>
      <li>Frontline Leadership & Morale Management</li>
      <li>Heavy Weapon Proficiency</li>
      <li>Risk Assessment & Survival Techniques</li>
    </ul>

    <h2>Profile Summary</h2>
    <p>Gustave is a battle-hardened veteran and the backbone of Expedition 33.  
       With experience from countless missions, he oversees every tactical decision  
       made in the war against the Paintress.</p>
  </div>

  <footer>© 2025 Expedition 33 — For Those Who Come After</footer>
</body>
</html>
EOF

cat << EOF > /etc/nginx/sites-available/default
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;
    index profile_gustave.html;
    server_name gustave33.com;
    location / {
        try_files \$uri \$uri/ =404;
    }
    access_log /tmp/access.log;
    error_log /tmp/error.log;
}
EOF

killall nginx
nginx
```



## Di ESQUIE

```bash
curl -s lune33.com
curl -s sciel33.com
curl -s gustave33.com
```

Node Lune, Sciel, dan Gustave dikonfigurasi menjadi web server menggunakan Nginx. File HTML profil yang berbeda dibuat untuk setiap node dan ditempatkan di /var/www/html. Konfigurasi Nginx diubah agar menyajikan file-file HTML tersebut sebagai halaman utama.

<br>

## Soal 6

> Setelah website berhasil dideploy pada masing-masing node web server dan halaman dapat menampilkan profil yang sesuai,  buatlah custom access log ke file /tmp/access.log di masing-masing node web server menggunakan format log tertentu seperti di bawah:
> - Tanggal dan waktu akses dalam format standar log.
> - Nama node yang sedang diakses.
> - Alamat IP klien yang mengakses website.
> - Metode HTTP dan URI yang diakses oleh klien.
> - Status respons HTTP yang diberikan oleh server.
> - Jumlah byte yang dikirimkan dalam respons.
> - Waktu yang dihabiskan oleh server untuk menangani permintaan.
> - Contoh format log yang sesuai:
>   [01/Oct/2024:11:30:45 +0000] Jarkom Node Lune Access from 192.168.1.15 using method "GET /resep/bayam HTTP/1.1" returned status 200 with 2567 bytes sent in 0.038 seconds

> _After successfully deploying each website and verifying the correct profile page is displayed, create a custom access log in /tmp/access.log on each web server using the following format:_
> _- Date and time of access (standard log format)_
> _- Name of the node being accessed_
> _- IP address of the client accessing the website_
> _- HTTP method and URI accessed by the client_
> _- HTTP response status code_
> _- Number of bytes sent in the response_
> _- Time taken by the server to process the request_
> _- Example Log Format:_
> _[01/Oct/2024:11:30:45 +0000] Jarkom Node Lune Access from 192.168.1.15 using method "GET /resep/bayam HTTP/1.1" returned status 200 with 2567 bytes sent in 0.038 seconds_

**Answer:**

- Screenshot

  `<img width="1600" height="807" alt="no 6 1" src="https://github.com/user-attachments/assets/e7cfe895-2f4a-445e-a7a6-77adaba67d62" />
  <img width="1314" height="296" alt="no 6 2" src="https://github.com/user-attachments/assets/32fff03c-939d-4245-8bb8-48446533ca70" />

`

- Explanation
## Di LUNE, SCIEL, GUSTAVE
```bash
mkdir -p /etc/nginx/conf.d

cat << EOF > /etc/nginx/conf.d/00-custom-log.conf
log_format custom_format '[\$time_local] Jarkom Node \$hostname Access from \$remote_addr using method "\$request" returned status \$status with \$body_bytes_sent bytes sent in \$request_time seconds';
EOF
````




```bash
sed -i 's|access_log /tmp/access.log;|access_log /tmp/access.log custom_format;|' /etc/nginx/sites-available/default
killall nginx && nginx
```



## Di SCIEL

```bash
sed -i 's|access_log /tmp/access.log;|access_log /tmp/access.log custom_format;|' /etc/nginx/sites-available/default
killall nginx && nginx
```



## Di GUSTAVE

```bash
sed -i 's|access_log /tmp/access.log;|access_log /tmp/access.log custom_format;|' /etc/nginx/sites-available/default
killall nginx && nginx
```



## Di ESQUIE

```bash
curl -s lune33.com
```



## Di LUNE

```bash
tail /tmp/access.log
```

Log bawaan Nginx diganti pakai format baru yang lebih detail dan gampang dibaca. Kita bikin format log sendiri yang bisa nampilin nama node-nya, IP yang akses, request-nya apa, dan lama responsnya.

<br>

## Soal 7

> Gustave merupakan web server yang tidak disarankan untuk dilihat oleh publik. Maka dari itu, ubahlah konfigurasi nginx sehingga halaman profil Gustave menjadi hanya bisa di akses melalui port 8080 dan 8888.

> _The Gustave web server should not be publicly accessible.
Modify the Nginx configuration so that Gustave’s profile page can only be accessed through ports 8080 and 8888._

**Answer:**


- Explanation

## Di GUSTAVE
```bash
nano /etc/nginx/sites-available/default
````

Hapus dua baris berikut:

```nginx
listen 80 default_server;
listen [::]:80 default_server;
```

Ganti dengan dua baris ini:

```nginx
listen 8000 default_server;
listen 8888;
```

```bash
killall nginx && nginx
```
Ganti port di Gustave. Biar web Gustave tidak gampang diakses publik, port 80 (port standar web) ditutup. Di settingan Nginx-nya, 'listen 80' dihapus, terus diganti jadi 'listen 8000' sama 'listen 8888'. Jadi cuma bisa diakses lewat port itu.

<br>

## Soal 8

> Untuk mempermudah program ekspedisi, maka node Lune, Sciel, Gustave sepakat untuk membuat halaman informasi dengan konten yang sama. Maka dari itu, buatlah lagi 1 server block di dalam konfigurasi nginx yang akan menyajikan file HTML ini. Namun, mereka ingin menyajikan halaman informasi tersebut di port yang berbeda-beda, yaitu Lune menggunakan port 8000, Sciel menggunakan port 8100, dan Gustave menggunakan port 8200.

> _To simplify coordination for the expedition program, Lune, Sciel, and Gustave agree to create a shared information page with the same content. Add one more server block in each node’s Nginx configuration that serves this HTML file 
Each node should serve the information page on a different port:_
> _- Lune → port 8000_
> _- Sciel → port 8100_
> _- Gustave → port 8200_

**Answer:**

- Screenshot


- Explanation
## Di LUNE, SCIEL, dan GUSTAVE
```bash
cat << EOF > /var/www/html/info.html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Expedition 33 — Mission Brief</title>
<style>
  body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    color: #fff;
    background: linear-gradient(270deg, #0a043c, #4a0c25, #7b1b0c, #f9a826);
    background-size: 800% 800%;
    animation: bgShift 20s ease infinite;
  }
  @keyframes bgShift {
    0% {background-position: 0% 50%;}
    50% {background-position: 100% 50%;}
    100% {background-position: 0% 50%;}
  }
  header {
    text-align: center;
    padding: 3rem 1rem 1rem;
  }
  header h1 {
    font-size: 2.8rem;
    color: #00e5ff;
    letter-spacing: 1px;
    text-shadow: 0 0 10px #00e5ff;
  }
  header p {
    font-size: 1rem;
    color: #b5eaff;
    margin-top: 0.3rem;
  }
  .container {
    max-width: 700px;
    margin: 2rem auto 3rem;
    background: rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    padding: 2rem 2.5rem;
    box-shadow: 0 4px 20px rgba(0,0,0,0.4);
    backdrop-filter: blur(6px);
  }
  h2 {
    color: #00e5ff;
    margin-top: 1.5rem;
  }
  p, li {
    line-height: 1.6;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  li {
    margin: 0.4rem 0;
  }
  strong {
    color: #ffd166;
  }
  .motto {
    text-align: center;
    margin-top: 1.5rem;
    font-style: italic;
    color: #ccc;
    opacity: 0.9;
  }
  footer {
    text-align: center;
    font-size: 0.9rem;
    color: #ddd;
    padding: 1rem;
    background: rgba(0,0,0,0.4);
  }
</style>
</head>
<body>
<header>
  <h1>Expedition 33</h1>
  <p>Mission Log #E33-00</p>
</header>

<div class="container">
  <h2>Mission Brief</h2>
  <p>Expedition 33 is a digital exploration led by <strong>Lune</strong>, <strong>Sciel</strong>, and <strong>Gustave</strong>.    
     Together, they traverse the unknown layers of the Paintress system — seeking order within chaos.</p>

  <h2>Active Nodes</h2>
  <ul>
    <li><strong>Lune</strong> — “When one falls, we continue.”</li>
    <li><strong>Sciel</strong> — “Tomorrow comes.”</li>
    <li><strong>Gustave</strong> — “For those who come after. Right?”</li>
  </ul>

  <h2>Ports</h2>
  <ul>
    <li>Lune: Port <strong>8000</strong></li>
    <li>Sciel: Port <strong>8100</strong></li>
    <li>Gustave: Port <strong>8200</strong></li>
  </ul>

  <div class="motto">
    “Despite the odds, Expedition 33 marches forward.”
  </div>
</div>

<footer>
  © 2025 Expedition 33 — For Those Who Come After
</footer>
</body>
</html>
EOF
````



## Di LUNE

```bash
nano /etc/nginx/sites-available/default
```

Tambahkan server block baru ini di bagian paling bawah file:

```nginx
server {
    listen 8000;
    root /var/www/html;
    index info.html;

    location / {
        try_files $uri $uri/ =404;
    }

    access_log /tmp/access.log custom_format;
    error_log /tmp/error.log;
}
```

```bash
killall nginx && nginx
```



## Di SCIEL

Ganti seluruh isi file `/etc/nginx/sites-available/default`:

```bash
cat << EOF > /etc/nginx/sites-available/default
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;
    index profile_sciel.html;
    server_name sciel33.com;
    location / {
        try_files \$uri \$uri/ =404;
    }
    access_log /tmp/access.log custom_format;
    error_log /tmp/error.log;
}
server {
    listen 8100;
    listen [::]:8100; 
    root /var/www/html;
    index info.html;
    location / {
        try_files \$uri \$uri/ =404;
    }
}
EOF
```

```bash
killall nginx && nginx
```



## Di GUSTAVE

```bash
nano /etc/nginx/sites-available/default
```

Tambahkan server block baru ini di bagian paling bawah file:

```nginx
server {
    listen 8200;
    root /var/www/html;
    index info.html;

    location / {
        try_files $uri $uri/ =404;
    }

    access_log /tmp/access.log custom_format;
    error_log /tmp/error.log;
}
```

```bash
killall nginx && nginx
```


bikin Nginx di tiap server bisa jalanin banyak 'situs'. Jadi satu server Nginx bisa layanin beberapa halaman di port yang beda-beda. Selain nampilin halaman profil (dari soal 5), kita tambah settingan baru ('server block') buat nampilin halaman 'info.html'. Port-nya dibedain: Lune di 8000, Sciel di 8100, dan Gustave di 8200.


<br>

## Soal 9

> Untuk mempermudah akses ke profil tiap anggota ekspedisi, buatlah 1 domain lagi yaitu "expeditioners.com" yang akan mengarah ke Alicia. Lalu, untuk mencegah overload dari salah satu web server, konfigurasikan reverse proxy Alicia agar bisa forward request ke server yang sesuai berdasarkan URL profile yang diminta oleh klien dengan ketentuan sebagai berikut:
> -  Request untuk “expeditioners.com/profil_lune” harus dialihkan ke halaman profil web server Lune.
> -  Request untuk “expeditioners.com/profil_sciel” harus dialihkan ke halaman profil web server Sciel.
> -  Request untuk “expeditioners.com/profil_gustave” harus dialihkan ke halaman profil web server Gustave.
> Jika terdapat request ke URL selain profil yang ditentukan, reverse proxy akan mengalihkan ke halaman informasi pada web server Lune.

> _To make it easier to access each member’s profile, create a new domain “expeditioners.com” that points to Alicia. "
Configure Alicia’s reverse proxy (Nginx) to forward requests to the correct web server based on the requested URL, with the following rules:_
> _- Request URL expeditioners.com/profil_lune, Forward To Lune’s profile page_
> _- Request URL expeditioners.com/profil_sciel, Forward To Sciel’s profile page_
> _- Request URL expeditioners.com/profil_gustave, Forward To Gustave’s profile page_
> _- Any other URL, Forward To Lune’s information page_

**Answer:**

- Screenshot

  `<img width="1600" height="810" alt="no 9 2" src="https://github.com/user-attachments/assets/0993f1c2-1861-49f8-8046-8a24ea3e3917" />
  <img width="1600" height="808" alt="no 9 1" src="https://github.com/user-attachments/assets/359ca06b-72eb-42cf-b031-10ea0e8d2ec9" />
  <img width="1600" height="810" alt="no 9 3" src="https://github.com/user-attachments/assets/6b7e73e1-6b65-48a5-83e1-db61a374cb5e" />
  <img width="1600" height="810" alt="no 9 4" src="https://github.com/user-attachments/assets/7225ab40-fbf9-4d85-a084-74e18a23018e" />
  <img width="1600" height="808" alt="no 9 5" src="https://github.com/user-attachments/assets/af834a69-0f33-48f6-8dde-a3562e5de722" />




`

- Explanation

## Di RENOIR
```bash
nano /etc/bind/named.conf.local
````

Tambahkan blok di akhir file:

```bash
zone "expeditioners.com" {
    type master;
    file "/etc/bind/db.expeditioners.com";
    allow-transfer { 10.47.3.2; }; 
};
```

```bash
nano /etc/bind/db.expeditioners.com
```

Isi file dengan:

```dns
$TTL    604800
@       IN      SOA     renoir.expeditioners.com. admin.expeditioners.com. (
                              1
                         604800
                          86400
                        2419200
                         604800 )
@       IN      NS      renoir.
@       IN      NS      verso.
renoir  IN      A       10.47.3.1
verso   IN      A       10.47.3.2
@       IN      A       10.47.4.1
```

```bash
killall named && named -u bind
```



## Di VERSO

```bash
nano /etc/bind/named.conf.local
```

Tambahkan blok di akhir file:

```bash
zone "expeditioners.com" {
    type slave;
    file "db.expeditioners.com";
    masters { 10.47.3.1; }; 
};
```

```bash
killall named && named -u bind
```



## Di ALICIA

Ganti seluruh isi file `/etc/nginx/sites-available/default`:

```bash
cat << EOF > /etc/nginx/sites-available/default
server {
    listen 80 default_server;
    server_name expeditioners.com;

    location /profil_lune {
        proxy_pass [http://10.47.2.1/](http://10.47.2.1/); 
    }

    location /profil_sciel {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/); 
    }

    location /profil_gustave {
        proxy_pass [http://10.47.2.3:8000/](http://10.47.2.3:8000/); 
    }

    location / {
        proxy_pass [http://10.47.2.1:8000/](http://10.47.2.1:8000/); 
    }
}
EOF
```

```bash
killall nginx
nginx
```



## Di ESQUIE

```bash
curl expeditioners.com/profil_lune   
curl expeditioners.com/profil_sciel  
curl expeditioners.com/profil_gustave 
curl expeditioners.com  
```
Mengkonfigurasi Reverse Proxy di Alicia. Domain baru 'expeditioners.com' diarahkan ke IP Alicia. Nginx di Alicia diatur untuk meneruskan (proxy_pass) permintaan berdasarkan URL: /profil_lune ke Lune, /profil_sciel ke Sciel, dan /profil_gustave ke Gustave (port 8000). Permintaan lain diteruskan ke halaman info Lune (port 8000).

<br>

## Soal 10

> Untuk mendistribusikan traffic halaman informasi, atur Reverse Proxy Alicia agar dapat membagi pekerjaan kepada web server Lune, Sciel, dan Gustave secara optimal menggunakan algoritma Round-robin. Pastikan target pembagian load merupakan halaman informasi, bukan halaman profil masing-masing web server.

> _To distribute traffic for the information page, configure the reverse proxy (Alicia) to use Round-robin load balancing between the three web servers: Lune, Sciel, and Gustave.
Ensure that only the information page is included in the load-balancing configuration - not the profile pages._

**Answer:**

- Screenshot

  `<img width="1600" height="812" alt="no 10" src="https://github.com/user-attachments/assets/2c1300a6-1433-4a14-a10a-3371fc880584" />
  
`

- Explanation


## Di ALICIA
```bash
nano /etc/nginx/sites-available/default
````

Ganti seluruh isi file:

```nginx
    upstream info_pages {
    server 10.47.2.1:8000;  
    server 10.47.2.2:8100; 
    server 10.47.2.3:8200;  
}

server {
    listen 80 default_server;
    server_name expeditioners.com;

    location /profil_lune {
        proxy_pass [http://10.47.2.1/](http://10.47.2.1/); 
    }
    location /profil_sciel {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/); 
    }
    location /profil_gustave {
        proxy_pass [http://10.47.2.3:8000/](http://10.47.2.3:8000/); 
    }

    location / {
        proxy_pass http://info_pages;
        add_header X-Upstream-Server $upstream_addr;
    }
}
```

```bash
killall nginx && nginx
```



## Di ESQUIE

```bash
for (( c=1; c<=6; c++ )); do curl -s -I [http://expeditioners.com/](http://expeditioners.com/) | grep X-Upstream-Server; done
```
Mengimplementasikan Load Balancing. Konfigurasi di Alicia dari soal 9 diubah. Sebuah 'upstream' bernama 'info_pages' dibuat, berisi daftar server info Lune, Sciel, dan Gustave. Permintaan ke lokasi root (/) kini diarahkan ke grup upstream tersebut, sehingga Nginx mendistribusikan traffic ke ketiga server secara bergantian (Round-Robin).



<br>
  
## Problems

## Revisions (if any)
