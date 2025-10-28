
# Network Configuration Guide

## 1. Setup IP Address

Konfigurasi berikut dilakukan di file `/etc/network/interfaces` atau setara pada masing-masing node.

**Lune:**
```

auto eth0
iface eth0 inet static
address 10.47.2.2
netmask 255.255.0.0
gateway 10.47.2.1

```

**Sciel:**
```

auto eth0
iface eth0 inet static
address 10.47.2.3
netmask 255.255.0.0
gateway 10.47.2.1

```

**Gustave:**
```

auto eth0
iface eth0 inet static
address 10.47.2.4
netmask 255.255.0.0
gateway 10.47.2.1

```

**Renoir:**
```

auto eth0
iface eth0 inet static
address 10.47.3.2
netmask 255.255.0.0
gateway 10.47.3.1

```

**Verso:**
```

auto eth0
iface eth0 inet static
address 10.47.3.3
netmask 255.255.0.0
gateway 10.47.3.1

```

**Alicia:**
```

auto eth0
iface eth0 inet static
address 10.47.4.2
netmask 255.255.0.0
gateway 10.47.4.1

```

**Maelle:**
```

auto eth0
iface eth0 inet static
address 10.47.5.2
netmask 255.255.0.0
gateway 10.47.5.1

```

**Monocco:**
```

auto eth0
iface eth0 inet static
address 10.47.5.3
netmask 255.255.0.0
gateway 10.47.5.1

```

**Esquie:**
```

auto eth0
iface eth0 inet static
address 10.47.5.4
netmask 255.255.0.0
gateway 10.47.5.1

````

---

## 2. Setup DNS Master (Renoir) & Slave (Verso)

### Di Master (Renoir)

Edit `named.conf.local`:
```bash
nano /etc/bind/named.conf.local
````

Tambahkan:

```
zone "lune33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/lune33.com";
};

zone "sciel33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/sciel33.com";
};

zone "gustave33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/gustave33.com";
};
```

Buat direktori dan file zone:

```bash
mkdir /etc/bind/zones
nano /etc/bind/zones/lune33.com
```

Isi `lune33.com`:

```
$TTL 604800
@   IN SOA  lune33.com. root.lune33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       lune33.com.
@   IN A        10.47.2.2
```

```bash
nano /etc/bind/zones/gustave33.com
```

Isi `gustave33.com`:

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       gustave33.com.
@   IN A        10.47.2.4
```

```bash
nano /etc/bind/zones/sciel33.com
```

Isi `sciel33.com`:

```
$TTL 604800
@   IN SOA  sciel33.com. root.sciel33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       sciel33.com.
@   IN A        10.47.2.3
```

### Di Slave (Verso)

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan:

```
zone "lune33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/lune33.com";
};

zone "sciel33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/sciel33.com";
};

zone "gustave33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/gustave33.com";
};
```

### Di Setiap Client

Edit `/etc/resolv.conf`:

```bash
nano /etc/resolv.conf
```

Isi:

```
nameserver 10.47.3.2
nameserver 10.47.3.3
```

Tes DNS dari client:

```bash
ping sciel33.com -c 5
```

-----

## 3\. CNAME & Reverse DNS

### Di Master (Renoir)

Edit file zone `lune33.com`:

```bash
nano /etc/bind/zones/lune33.com
```

Tambahkan baris CNAME (pastikan naikkan Serial jika perlu):

```
$TTL 604800
@   IN SOA  lune33.com. root.lune33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       lune33.com.
@   IN A        10.47.2.2
exp IN CNAME    lune33.com.
```

Edit file zone `sciel33.com`:

```bash
nano /etc/bind/zones/sciel33.com
```

Tambahkan baris CNAME (pastikan naikkan Serial jika perlu):

```
$TTL 604800
@   IN SOA  sciel33.com. root.sciel33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       sciel33.com.
@   IN A        10.47.2.3
exp IN CNAME    sciel33.com.
```

Edit `named.conf.local` untuk Reverse DNS:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona reverse:

```
zone "2.47.10.in-addr.arpa" { # Assuming 10.47.2.x subnet for Gustave
    type master;
    file "/etc/bind/zones/db.10.47.2"; # Adjust filename as needed
};
```

*Catatan: Nama zona reverse dan file perlu disesuaikan dengan subnet IP Gustave (10.47.2.4). Jika menggunakan /16 (10.47.x.x), nama zona adalah `47.10.in-addr.arpa`.*

Buat file zone reverse (contoh untuk 10.47.2.x):

```bash
nano /etc/bind/zones/db.10.47.2 # Adjust filename as needed
```

Isi file reverse untuk Gustave (IP 10.47.2.4):

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@ IN NS  gustave33.com. ; Adjust NS if needed
4 IN PTR  gustave33.com.
```

Restart BIND di Renoir (dan Verso jika reverse zone juga di-slave).

### Tes dari Client

```bash
host -t CNAME exp.sciel33.com
host -t PTR 10.47.2.4
```

-----

## 4\. Delegasi Subdomain

### Di Master (Renoir)

Edit file zone `gustave33.com`:

```bash
nano /etc/bind/zones/gustave33.com
```

Tambahkan record NS untuk delegasi (naikkan Serial):

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial --> Naikkan jika ada perubahan
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       gustave33.com.
@   IN A        10.47.2.4
ns1 IN A        10.47.3.3     ; IP Verso as NS for subdomain
expedition    IN NS     ns1
```

*Catatan: Anda perlu record A (glue record) untuk `ns1` jika nameserver (`verso` dalam contoh ini, IP 10.47.3.3) berada di luar zona `gustave33.com`.*

Edit `/etc/bind/named.conf.options`:

```bash
nano /etc/bind/named.conf.options
```

Pastikan `allow-query` ada:

```
options {
        directory "/var/cache/bind";
        //dnssec-validation auto;
        allow-query{any;};
};
```

Restart BIND di Renoir.

### Di Server Delegasi (Verso)

Edit `/etc/bind/named.conf.options`:

```bash
nano /etc/bind/named.conf.options
```

Pastikan `allow-query` ada:

```
options {
        directory "/var/cache/bind";
        //dnssec-validation auto;
        allow-query{any;};
};
```

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona master untuk subdomain:

```
zone "expedition.gustave33.com" {
  type master;
  file "/etc/bind/delegasi/expedition.gustave33.com";
};
```

Buat direktori dan file zone subdomain:

```bash
mkdir /etc/bind/delegasi
nano /etc/bind/delegasi/expedition.gustave33.com
```

Isi file `expedition.gustave33.com`:

```
$TTL 604800
@   IN SOA  expedition.gustave33.com. root.expedition.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       expedition.gustave33.com. ; Or point to ns1.gustave33.com.
@   IN A        10.47.2.4
```

Restart BIND di Verso.

-----

## 5\. Setup Nginx Web Servers

Perintah berikut dijalankan di node **Lune**, **Sciel**, dan **Gustave**. Ganti nama file HTML sesuai node.

```bash
mkdir /var/www/profile
# Contoh untuk Gustave:
# nano /var/www/profile/profile_gustave.html
# (Isi dengan konten HTML profil)
```

Konfigurasi Nginx (buat file baru, misal `profile`):

```bash
cd /etc/nginx/sites-available/
# rm default # Hati-hati jika ada konfigurasi lain
nano profile
```

Isi `profile` (Contoh untuk Gustave):

```
server {
    listen 80;
    root /var/www/profile;
    index profile_gustave.html;
    server_name _; # Atau gunakan domain spesifik
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log;
}
```

Aktifkan site dan restart Nginx:

```bash
cd /etc/nginx/sites-enabled/
# rm default # Hati-hati
ln -s /etc/nginx/sites-available/profile /etc/nginx/sites-enabled/
service nginx restart
```

Ulangi untuk Lune dan Sciel dengan file HTML dan `index` yang sesuai.

Tes koneksi dari client:

```bash
nc -vz 10.47.2.2 80 # Tes Lune
curl -v lune33.com # Tes Lune via domain
```

-----

## 6\. Nginx Custom Log Format

Edit file konfigurasi Nginx utama di **Lune**, **Sciel**, dan **Gustave**:

```bash
nano /etc/nginx/nginx.conf
```

Di dalam blok `http { ... }`, tambahkan `log_format` (ganti nama format per node):
Contoh untuk Lune:

```
http {
    # ...
    log_format lune '[$time_local] Jarkom Node Lune Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Contoh untuk Sciel:

```
http {
    # ...
    log_format sciel '[$time_local] Jarkom Node Sciel Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Contoh untuk Gustave:

```
http {
    # ...
    log_format gustave '[$time_local] Jarkom Node Gustave Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Edit konfigurasi site (`profile`) di masing-masing node:

```bash
cd /etc/nginx/sites-available/
nano profile
```

Ubah baris `access_log` (Contoh untuk Lune):

```
    access_log  /tmp/access.log lune;
```

Ulangi untuk Sciel (`sciel`) dan Gustave (`gustave`).

Restart Nginx di ketiga node:

```bash
service nginx restart
```

Cek log setelah akses dari client:

```bash
cat /tmp/access.log
```

-----

## 7\. Ubah Port Nginx Gustave

Di **Gustave**:

```bash
nano /etc/nginx/sites-available/profile
```

Ubah baris `listen`:

```
server {
    listen 8080; # Ganti dari 80
    listen 8888;
    # ... sisa konfigurasi ...
}
```

Restart Nginx di Gustave:

```bash
service nginx restart
```

-----

## 8\. Tambah Halaman Info & Port Baru

Di **Lune**, **Sciel**, dan **Gustave**:
Buat file info:

```bash
nano /var/www/profile/info.html
```

(Isi dengan konten HTML info)

Edit konfigurasi site (`profile`) di masing-masing node:

```bash
nano /etc/nginx/sites-available/profile
```

**Di Gustave** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 8080 & 8888 ...
}

server {
    listen 8200;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log gustave; # Gunakan format log yang sesuai
}
```

**Di Lune** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 80 ...
}

server {
    listen 8000;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log lune; # Gunakan format log yang sesuai
}
```

**Di Sciel** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 80 ...
}

server {
    listen 8100;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log sciel; # Gunakan format log yang sesuai
}
```

Restart Nginx di **Lune**, **Sciel**, dan **Gustave**:

```bash
service nginx restart
```

Tes dari client:

```bash
curl -v 10.47.2.2:8000 # Tes Info Lune
curl -v 10.47.2.3:8100 # Tes Info Sciel
curl -v 10.47.2.4:8200 # Tes Info Gustave
```

-----

## 9\. Reverse Proxy (Alicia) - Profil & Fallback

### Di DNS Master (Renoir)

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona `expeditioners.com`:

```
zone "expeditioners.com" {
  type master;
  # Tambahkan allow-transfer jika Verso adalah slave untuk zona ini
  file "/etc/bind/zones/expeditioners.com";
};
```

Buat file zone:

```bash
nano /etc/bind/zones/expeditioners.com
```

Isi `expeditioners.com`:

```
$TTL 604800
@   IN SOA  expeditioners.com. root.expeditioners.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       expeditioners.com. ; Atau NS server Anda
@   IN A        10.47.4.2          ; IP Alicia
```

Restart BIND di Renoir (dan Verso jika slave).

### Di Alicia (Reverse Proxy)

Konfigurasi Nginx (buat file baru, misal `expeditioners`):

```bash
cd /etc/nginx/sites-available/
# rm default # Hati-hati
nano expeditioners
```

Isi `expeditioners`:

```
server {
    listen 80;
    server_name expeditioners.com; # Ganti '_' dengan nama domain

    location /profil_lune/ {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/); # URL Profil Lune (port 80)
    }
    location /profil_sciel/ {
        proxy_pass [http://10.47.2.3/](http://10.47.2.3/); # URL Profil Sciel (port 80)
    }
    location /profil_gustave/ {
        proxy_pass [http://10.47.2.4:8080/](http://10.47.2.4:8080/); # URL Profil Gustave (port 8080)
    }
    location / {
        proxy_pass [http://10.47.2.2:8000/](http://10.47.2.2:8000/); # Fallback ke Info Lune (port 8000)
    }
}
```

Aktifkan site dan restart Nginx:

```bash
cd /etc/nginx/sites-enabled/
# rm default # Hati-hati
ln -s /etc/nginx/sites-available/expeditioners /etc/nginx/sites-enabled/
service nginx restart
```

Tes dari client:

```bash
curl -v [expeditioners.com/profil_lune](https://expeditioners.com/profil_lune)
curl -v [expeditioners.com/profil_sciel](https://expeditioners.com/profil_sciel)
curl -v [expeditioners.com/profil_gustave](https://expeditioners.com/profil_gustave)
curl -v [expeditioners.com/halaman_acak](https://expeditioners.com/halaman_acak) # Tes fallback
```

-----Okay, here are your notes formatted into a Markdown README structure for better readability and copy-pasting.

```markdown
# Network Configuration Guide

## 1. Setup IP Address

Konfigurasi berikut dilakukan di file `/etc/network/interfaces` atau setara pada masing-masing node.

**Lune:**
```

auto eth0
iface eth0 inet static
address 10.47.2.2
netmask 255.255.0.0
gateway 10.47.2.1

```

**Sciel:**
```

auto eth0
iface eth0 inet static
address 10.47.2.3
netmask 255.255.0.0
gateway 10.47.2.1

```

**Gustave:**
```

auto eth0
iface eth0 inet static
address 10.47.2.4
netmask 255.255.0.0
gateway 10.47.2.1

```

**Renoir:**
```

auto eth0
iface eth0 inet static
address 10.47.3.2
netmask 255.255.0.0
gateway 10.47.3.1

```

**Verso:**
```

auto eth0
iface eth0 inet static
address 10.47.3.3
netmask 255.255.0.0
gateway 10.47.3.1

```

**Alicia:**
```

auto eth0
iface eth0 inet static
address 10.47.4.2
netmask 255.255.0.0
gateway 10.47.4.1

```

**Maelle:**
```

auto eth0
iface eth0 inet static
address 10.47.5.2
netmask 255.255.0.0
gateway 10.47.5.1

```

**Monocco:**
```

auto eth0
iface eth0 inet static
address 10.47.5.3
netmask 255.255.0.0
gateway 10.47.5.1

```

**Esquie:**
```

auto eth0
iface eth0 inet static
address 10.47.5.4
netmask 255.255.0.0
gateway 10.47.5.1

````

---

## 2. Setup DNS Master (Renoir) & Slave (Verso)

### Di Master (Renoir)

Edit `named.conf.local`:
```bash
nano /etc/bind/named.conf.local
````

Tambahkan:

```
zone "lune33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/lune33.com";
};

zone "sciel33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/sciel33.com";
};

zone "gustave33.com" {
  type master;
  notify yes;
  also-notify { 10.47.3.3; };
  allow-transfer { 10.47.3.3; };
  file "/etc/bind/zones/gustave33.com";
};
```

Buat direktori dan file zone:

```bash
mkdir /etc/bind/zones
nano /etc/bind/zones/lune33.com
```

Isi `lune33.com`:

```
$TTL 604800
@   IN SOA  lune33.com. root.lune33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       lune33.com.
@   IN A        10.47.2.2
```

```bash
nano /etc/bind/zones/gustave33.com
```

Isi `gustave33.com`:

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       gustave33.com.
@   IN A        10.47.2.4
```

```bash
nano /etc/bind/zones/sciel33.com
```

Isi `sciel33.com`:

```
$TTL 604800
@   IN SOA  sciel33.com. root.sciel33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       sciel33.com.
@   IN A        10.47.2.3
```

### Di Slave (Verso)

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan:

```
zone "lune33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/lune33.com";
};

zone "sciel33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/sciel33.com";
};

zone "gustave33.com" {
    type slave;
    masters { 10.47.3.2; };
    file "/var/lib/bind/gustave33.com";
};
```

### Di Setiap Client

Edit `/etc/resolv.conf`:

```bash
nano /etc/resolv.conf
```

Isi:

```
nameserver 10.47.3.2
nameserver 10.47.3.3
```

Tes DNS dari client:

```bash
ping sciel33.com -c 5
```

-----

## 3\. CNAME & Reverse DNS

### Di Master (Renoir)

Edit file zone `lune33.com`:

```bash
nano /etc/bind/zones/lune33.com
```

Tambahkan baris CNAME (pastikan naikkan Serial jika perlu):

```
$TTL 604800
@   IN SOA  lune33.com. root.lune33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       lune33.com.
@   IN A        10.47.2.2
exp IN CNAME    lune33.com.
```

Edit file zone `sciel33.com`:

```bash
nano /etc/bind/zones/sciel33.com
```

Tambahkan baris CNAME (pastikan naikkan Serial jika perlu):

```
$TTL 604800
@   IN SOA  sciel33.com. root.sciel33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       sciel33.com.
@   IN A        10.47.2.3
exp IN CNAME    sciel33.com.
```

Edit `named.conf.local` untuk Reverse DNS:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona reverse:

```
zone "2.47.10.in-addr.arpa" { # Assuming 10.47.2.x subnet for Gustave
    type master;
    file "/etc/bind/zones/db.10.47.2"; # Adjust filename as needed
};
```

*Catatan: Nama zona reverse dan file perlu disesuaikan dengan subnet IP Gustave (10.47.2.4). Jika menggunakan /16 (10.47.x.x), nama zona adalah `47.10.in-addr.arpa`.*

Buat file zone reverse (contoh untuk 10.47.2.x):

```bash
nano /etc/bind/zones/db.10.47.2 # Adjust filename as needed
```

Isi file reverse untuk Gustave (IP 10.47.2.4):

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@ IN NS  gustave33.com. ; Adjust NS if needed
4 IN PTR  gustave33.com.
```

Restart BIND di Renoir (dan Verso jika reverse zone juga di-slave).

### Tes dari Client

```bash
host -t CNAME exp.sciel33.com
host -t PTR 10.47.2.4
```

-----

## 4\. Delegasi Subdomain

### Di Master (Renoir)

Edit file zone `gustave33.com`:

```bash
nano /etc/bind/zones/gustave33.com
```

Tambahkan record NS untuk delegasi (naikkan Serial):

```
$TTL 604800
@   IN SOA  gustave33.com. root.gustave33.com. (
        2025102401 ; serial --> Naikkan jika ada perubahan
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       gustave33.com.
@   IN A        10.47.2.4
ns1 IN A        10.47.3.3     ; IP Verso as NS for subdomain
expedition    IN NS     ns1
```

*Catatan: Anda perlu record A (glue record) untuk `ns1` jika nameserver (`verso` dalam contoh ini, IP 10.47.3.3) berada di luar zona `gustave33.com`.*

Edit `/etc/bind/named.conf.options`:

```bash
nano /etc/bind/named.conf.options
```

Pastikan `allow-query` ada:

```
options {
        directory "/var/cache/bind";
        //dnssec-validation auto;
        allow-query{any;};
};
```

Restart BIND di Renoir.

### Di Server Delegasi (Verso)

Edit `/etc/bind/named.conf.options`:

```bash
nano /etc/bind/named.conf.options
```

Pastikan `allow-query` ada:

```
options {
        directory "/var/cache/bind";
        //dnssec-validation auto;
        allow-query{any;};
};
```

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona master untuk subdomain:

```
zone "expedition.gustave33.com" {
  type master;
  file "/etc/bind/delegasi/expedition.gustave33.com";
};
```

Buat direktori dan file zone subdomain:

```bash
mkdir /etc/bind/delegasi
nano /etc/bind/delegasi/expedition.gustave33.com
```

Isi file `expedition.gustave33.com`:

```
$TTL 604800
@   IN SOA  expedition.gustave33.com. root.expedition.gustave33.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       expedition.gustave33.com. ; Or point to ns1.gustave33.com.
@   IN A        10.47.2.4
```

Restart BIND di Verso.

-----

## 5\. Setup Nginx Web Servers

Perintah berikut dijalankan di node **Lune**, **Sciel**, dan **Gustave**. Ganti nama file HTML sesuai node.

```bash
mkdir /var/www/profile
# Contoh untuk Gustave:
# nano /var/www/profile/profile_gustave.html
# (Isi dengan konten HTML profil)
```

Konfigurasi Nginx (buat file baru, misal `profile`):

```bash
cd /etc/nginx/sites-available/
# rm default # Hati-hati jika ada konfigurasi lain
nano profile
```

Isi `profile` (Contoh untuk Gustave):

```
server {
    listen 80;
    root /var/www/profile;
    index profile_gustave.html;
    server_name _; # Atau gunakan domain spesifik
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log;
}
```

Aktifkan site dan restart Nginx:

```bash
cd /etc/nginx/sites-enabled/
# rm default # Hati-hati
ln -s /etc/nginx/sites-available/profile /etc/nginx/sites-enabled/
service nginx restart
```

Ulangi untuk Lune dan Sciel dengan file HTML dan `index` yang sesuai.

Tes koneksi dari client:

```bash
nc -vz 10.47.2.2 80 # Tes Lune
curl -v lune33.com # Tes Lune via domain
```

-----

## 6\. Nginx Custom Log Format

Edit file konfigurasi Nginx utama di **Lune**, **Sciel**, dan **Gustave**:

```bash
nano /etc/nginx/nginx.conf
```

Di dalam blok `http { ... }`, tambahkan `log_format` (ganti nama format per node):
Contoh untuk Lune:

```
http {
    # ...
    log_format lune '[$time_local] Jarkom Node Lune Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Contoh untuk Sciel:

```
http {
    # ...
    log_format sciel '[$time_local] Jarkom Node Sciel Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Contoh untuk Gustave:

```
http {
    # ...
    log_format gustave '[$time_local] Jarkom Node Gustave Access from $remote_addr using method "$request" returned status $status with $body_bytes_sent bytes sent in $request_time seconds';
    # ...
}
```

Edit konfigurasi site (`profile`) di masing-masing node:

```bash
cd /etc/nginx/sites-available/
nano profile
```

Ubah baris `access_log` (Contoh untuk Lune):

```
    access_log  /tmp/access.log lune;
```

Ulangi untuk Sciel (`sciel`) dan Gustave (`gustave`).

Restart Nginx di ketiga node:

```bash
service nginx restart
```

Cek log setelah akses dari client:

```bash
cat /tmp/access.log
```

-----

## 7\. Ubah Port Nginx Gustave

Di **Gustave**:

```bash
nano /etc/nginx/sites-available/profile
```

Ubah baris `listen`:

```
server {
    listen 8080; # Ganti dari 80
    listen 8888;
    # ... sisa konfigurasi ...
}
```

Restart Nginx di Gustave:

```bash
service nginx restart
```

-----

## 8\. Tambah Halaman Info & Port Baru

Di **Lune**, **Sciel**, dan **Gustave**:
Buat file info:

```bash
nano /var/www/profile/info.html
```

(Isi dengan konten HTML info)

Edit konfigurasi site (`profile`) di masing-masing node:

```bash
nano /etc/nginx/sites-available/profile
```

**Di Gustave** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 8080 & 8888 ...
}

server {
    listen 8200;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log gustave; # Gunakan format log yang sesuai
}
```

**Di Lune** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 80 ...
}

server {
    listen 8000;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log lune; # Gunakan format log yang sesuai
}
```

**Di Sciel** (Tambahkan server block baru):

```
server {
    # ... block server profil di port 80 ...
}

server {
    listen 8100;
    root /var/www/profile;
    index info.html;
    server_name _;
    location / {
            try_files $uri $uri/ =404;
    }
    location ~ /\.ht {
        deny all;
    }
    error_log   /tmp/error.log;
    access_log  /tmp/access.log sciel; # Gunakan format log yang sesuai
}
```

Restart Nginx di **Lune**, **Sciel**, dan **Gustave**:

```bash
service nginx restart
```

Tes dari client:

```bash
curl -v 10.47.2.2:8000 # Tes Info Lune
curl -v 10.47.2.3:8100 # Tes Info Sciel
curl -v 10.47.2.4:8200 # Tes Info Gustave
```

-----

## 9\. Reverse Proxy (Alicia) - Profil & Fallback

### Di DNS Master (Renoir)

Edit `named.conf.local`:

```bash
nano /etc/bind/named.conf.local
```

Tambahkan zona `expeditioners.com`:

```
zone "expeditioners.com" {
  type master;
  # Tambahkan allow-transfer jika Verso adalah slave untuk zona ini
  file "/etc/bind/zones/expeditioners.com";
};
```

Buat file zone:

```bash
nano /etc/bind/zones/expeditioners.com
```

Isi `expeditioners.com`:

```
$TTL 604800
@   IN SOA  expeditioners.com. root.expeditioners.com. (
        2025102401 ; serial
        3600       ; refresh
        600        ; retry
        604800     ; expire
        86400 )    ; minimum
;
@   IN NS       expeditioners.com. ; Atau NS server Anda
@   IN A        10.47.4.2          ; IP Alicia
```

Restart BIND di Renoir (dan Verso jika slave).

### Di Alicia (Reverse Proxy)

Konfigurasi Nginx (buat file baru, misal `expeditioners`):

```bash
cd /etc/nginx/sites-available/
# rm default # Hati-hati
nano expeditioners
```

Isi `expeditioners`:

```
server {
    listen 80;
    server_name expeditioners.com; # Ganti '_' dengan nama domain

    location /profil_lune/ {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/); # URL Profil Lune (port 80)
    }
    location /profil_sciel/ {
        proxy_pass [http://10.47.2.3/](http://10.47.2.3/); # URL Profil Sciel (port 80)
    }
    location /profil_gustave/ {
        proxy_pass [http://10.47.2.4:8080/](http://10.47.2.4:8080/); # URL Profil Gustave (port 8080)
    }
    location / {
        proxy_pass [http://10.47.2.2:8000/](http://10.47.2.2:8000/); # Fallback ke Info Lune (port 8000)
    }
}
```

Aktifkan site dan restart Nginx:

```bash
cd /etc/nginx/sites-enabled/
# rm default # Hati-hati
ln -s /etc/nginx/sites-available/expeditioners /etc/nginx/sites-enabled/
service nginx restart
```

Tes dari client:

```bash
curl -v [expeditioners.com/profil_lune](https://expeditioners.com/profil_lune)
curl -v [expeditioners.com/profil_sciel](https://expeditioners.com/profil_sciel)
curl -v [expeditioners.com/profil_gustave](https://expeditioners.com/profil_gustave)
curl -v [expeditioners.com/halaman_acak](https://expeditioners.com/halaman_acak) # Tes fallback
```

-----

## 10\. Load Balancing (Alicia) - Halaman Info

Di **Alicia**:
Edit konfigurasi Nginx (`expeditioners`):

```bash
nano /etc/nginx/sites-available/expeditioners
```

Tambahkan `upstream` dan ubah `location /`:

```
upstream static {
    server 10.47.2.2:8000;  # Info Lune
    server 10.47.2.3:8100;  # Info Sciel
    server 10.47.2.4:8200;  # Info Gustave
}

server {
    listen 80;
    server_name expeditioners.com; # Ganti '_' jika perlu

    location /profil_lune/ {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/);
    }
    location /profil_sciel/ {
        proxy_pass [http://10.47.2.3/](http://10.47.2.3/);
    }
    location /profil_gustave/ {
        proxy_pass [http://10.47.2.4:8080/](http://10.47.2.4:8080/); # Pastikan port Gustave benar
    }
    # Tambahkan location baru untuk info jika fallback tidak boleh ke load balancer
    # location /info/ {
    #    proxy_pass http://static/;
    #    add_header X-Upstream $upstream_addr;
    # }
    location / { # Location ini sekarang jadi load balancer
        proxy_pass http://static/;
        add_header X-Upstream $upstream_addr;
    }
}
```

*Catatan: Konfigurasi di atas menjadikan fallback (`/`) sebagai load balancer untuk halaman info. Jika Anda ingin fallback tetap ke Info Lune dan load balancer hanya untuk path tertentu (misal `/info/`), Anda perlu menyesuaikan `location /` dan menambahkan `location /info/`.*

Restart Nginx di Alicia:

```bash
service nginx restart
```

Tes load balancing dari client:

```bash
for i in $(seq 1 12); do curl -s -I --resolve expeditioners.com:80:10.47.4.2 [http://expeditioners.com/](http://expeditioners.com/) | grep -i X-Upstream; done
```

*Catatan: Perintah `--resolve` mungkin tidak diperlukan jika DNS sudah benar.*

```
```

## 10\. Load Balancing (Alicia) - Halaman Info

Di **Alicia**:
Edit konfigurasi Nginx (`expeditioners`):

```bash
nano /etc/nginx/sites-available/expeditioners
```

Tambahkan `upstream` dan ubah `location /`:

```
upstream static {
    server 10.47.2.2:8000;  # Info Lune
    server 10.47.2.3:8100;  # Info Sciel
    server 10.47.2.4:8200;  # Info Gustave
}

server {
    listen 80;
    server_name expeditioners.com; # Ganti '_' jika perlu

    location /profil_lune/ {
        proxy_pass [http://10.47.2.2/](http://10.47.2.2/);
    }
    location /profil_sciel/ {
        proxy_pass [http://10.47.2.3/](http://10.47.2.3/);
    }
    location /profil_gustave/ {
        proxy_pass [http://10.47.2.4:8080/](http://10.47.2.4:8080/); # Pastikan port Gustave benar
    }
    # Tambahkan location baru untuk info jika fallback tidak boleh ke load balancer
    # location /info/ {
    #    proxy_pass http://static/;
    #    add_header X-Upstream $upstream_addr;
    # }
    location / { # Location ini sekarang jadi load balancer
        proxy_pass http://static/;
        add_header X-Upstream $upstream_addr;
    }
}
```

*Catatan: Konfigurasi di atas menjadikan fallback (`/`) sebagai load balancer untuk halaman info. Jika Anda ingin fallback tetap ke Info Lune dan load balancer hanya untuk path tertentu (misal `/info/`), Anda perlu menyesuaikan `location /` dan menambahkan `location /info/`.*

Restart Nginx di Alicia:

```bash
service nginx restart
```

Tes load balancing dari client:

```bash
for i in $(seq 1 12); do curl -s -I --resolve expeditioners.com:80:10.47.4.2 [http://expeditioners.com/](http://expeditioners.com/) | grep -i X-Upstream; done
```

*Catatan: Perintah `--resolve` mungkin tidak diperlukan jika DNS sudah benar.*

```
```