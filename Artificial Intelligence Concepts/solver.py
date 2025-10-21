# solver_auto.py

import random
from collections import Counter

def muat_kamus(nama_file='kamus.txt'):
    try:
        with open(nama_file, 'r') as file:
            daftar_kata = [baris.strip().lower() for baris in file]
        return [kata for kata in daftar_kata if len(kata) == 5]
    except FileNotFoundError:
        print(f"Error: File kamus '{nama_file}' tidak ditemukan.")
        return None

def beri_umpan_balik(tebakan, jawaban):
    """
    Menghasilkan umpan balik otomatis:
    'g' = hijau, 'y' = kuning, 'b' = abu-abu
    """
    feedback = ['b'] * 5
    jawaban_counter = Counter(jawaban)
    
    # Cek hijau dulu
    for i in range(5):
        if tebakan[i] == jawaban[i]:
            feedback[i] = 'g'
            jawaban_counter[tebakan[i]] -= 1
    
    # Cek kuning
    for i in range(5):
        if feedback[i] == 'b' and tebakan[i] in jawaban_counter and jawaban_counter[tebakan[i]] > 0:
            feedback[i] = 'y'
            jawaban_counter[tebakan[i]] -= 1

    return ''.join(feedback)

def saring_kata(daftar_kata, tebakan_terakhir, umpan_balik):
    sisa_kata = daftar_kata[:]
    huruf_hijau = {i: tebakan_terakhir[i] for i, char in enumerate(umpan_balik) if char == 'g'}
    huruf_kuning = {i: tebakan_terakhir[i] for i, char in enumerate(umpan_balik) if char == 'y'}
    huruf_abu = {tebakan_terakhir[i] for i, char in enumerate(umpan_balik) if char == 'b'}
    jumlah_huruf_min = Counter(list(huruf_hijau.values()) + list(huruf_kuning.values()))

    kata_yang_memenuhi = []
    for kata in sisa_kata:
        valid = True
        for pos, huruf in huruf_hijau.items():
            if kata[pos] != huruf:
                valid = False
                break
        if not valid: continue
        for pos, huruf in huruf_kuning.items():
            if kata[pos] == huruf or huruf not in kata:
                valid = False
                break
        if not valid: continue
        for huruf in huruf_abu:
            if huruf not in jumlah_huruf_min and huruf in kata:
                valid = False
                break
        if not valid: continue
        for huruf, jumlah in jumlah_huruf_min.items():
            if kata.count(huruf) < jumlah:
                valid = False
                break
        if not valid: continue
        kata_yang_memenuhi.append(kata)
    return kata_yang_memenuhi

def pilih_tebakan_berikutnya(daftar_kata):
    if not daftar_kata:
        return None
    return random.choice(daftar_kata)

def main():
    daftar_kata = muat_kamus()
    if not daftar_kata:
        return

    print("=== Wordle Solver Otomatis ===")
    jawaban = input("Masukkan kata rahasia (5 huruf): ").lower()
    if len(jawaban) != 5 or jawaban not in daftar_kata:
        print("Kata rahasia tidak valid atau tidak ada di kamus.")
        return

    tebakan = "slate"  # tebakan awal
    for i in range(1, 7):
        print(f"\nPercobaan ke-{i}: Tebakan -> {tebakan.upper()}")
        umpan_balik = beri_umpan_balik(tebakan, jawaban)
        # Tampilkan umpan balik dalam bentuk warna
        tampilan = ''
        for j, c in enumerate(tebakan):
            if umpan_balik[j] == 'g':
                tampilan += f"[{c.upper()}]"  # hijau
            elif umpan_balik[j] == 'y':
                tampilan += f"({c})"  # kuning
            else:
                tampilan += c  # abu-abu
        print("Umpan balik:", tampilan)

        if umpan_balik == 'ggggg':
            print("\nBerhasil! Kata ditemukan 🎉")
            return

        daftar_kata = saring_kata(daftar_kata, tebakan, umpan_balik)
        if not daftar_kata:
            print("Tidak ada kata yang cocok dengan petunjuk.")
            return
        tebakan = pilih_tebakan_berikutnya(daftar_kata)

    print("\nGagal menemukan kata dalam 6 percobaan.")

if __name__ == "__main__":
    main()
