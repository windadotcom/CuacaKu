# 🌤️ Cuacaku

Aplikasi cuaca Android sederhana yang dibuat dengan **Jetpack Compose**. Cukup ketik nama kota, lalu lihat kondisi cuaca saat ini, prakiraan per jam, dan prakiraan 7 hari ke depan. Data diambil dari [Open-Meteo](https://open-meteo.com/), sebuah API cuaca gratis yang tidak membutuhkan API key.

## ✨ Fitur

- 🔍 **Cari kota** mana pun di dunia lewat kolom pencarian
- ⚡ **Tombol kota cepat** (Surakarta, Jakarta, Bandung, Surabaya, dan lainnya)
- 🎨 **Latar gradasi dinamis** yang berubah sesuai kondisi cuaca dan waktu (cerah, berawan, hujan, badai, malam)
- 🌈 **Ikon cuaca besar yang beranimasi** (melayang pelan)
- ⏱️ **Prakiraan per jam** untuk 24 jam ke depan
- 📅 **Prakiraan 7 hari** lengkap dengan suhu tertinggi, terendah, dan peluang hujan
- 📊 **Detail cuaca:** kelembapan, kecepatan angin, peluang hujan, indeks UV, serta waktu matahari terbit dan terbenam
- 🚀 **Cuaca otomatis tampil** saat aplikasi dibuka

## 🛠️ Teknologi

| Komponen | Keterangan |
|---|---|
| Bahasa | Kotlin |
| UI | Jetpack Compose + Material 3 (tanpa XML) |
| Jaringan | Retrofit + Gson |
| Arsitektur | ViewModel + state Compose (`mutableStateOf`) |
| Asinkron | Kotlin Coroutines |
| API | [Open-Meteo Forecast](https://open-meteo.com/en/docs) dan [Geocoding](https://open-meteo.com/en/docs/geocoding-api) |

## 📁 Struktur Proyek

```
app/src/main/java/com/winda/cuacaku/
├── MainActivity.kt        # Titik masuk aplikasi
├── Models.kt              # Data class untuk respons API
├── WeatherApi.kt          # Interface Retrofit + instance
├── WeatherViewModel.kt    # Logika dan state layar
├── WeatherScreen.kt       # Seluruh tampilan Jetpack Compose
└── ui/theme/              # Warna, tipografi, dan tema
```

## 🔄 Cara Kerja

1. Pengguna mengetik nama kota, lalu aplikasi memanggil **Geocoding API** untuk mendapatkan koordinat (latitude dan longitude).
2. Koordinat dipakai untuk memanggil **Forecast API** yang mengembalikan cuaca saat ini, data per jam, dan data harian.
3. `WeatherViewModel` menyimpan hasilnya di `uiState`, dan Compose otomatis menggambar ulang tampilan saat state berubah.

## 🚀 Cara Menjalankan

**Prasyarat**

- [Android Studio](https://developer.android.com/studio) versi terbaru
- Koneksi internet (untuk mengunduh dependensi Gradle dan memanggil API)
- Emulator Android atau HP dengan USB debugging aktif

**Langkah**

1. Clone repositori ini:
   ```bash
   git clone https://github.com/windadotcom/CuacaKu.git
   ```
2. Buka folder proyek di Android Studio.
3. Tunggu proses **Gradle sync** selesai (sync pertama bisa memakan waktu cukup lama).
4. Pilih emulator atau perangkat, lalu klik tombol **Run ▶**.

Tidak perlu API key atau konfigurasi tambahan.

## 📸 Tangkapan Layar

> Tambahkan tangkapan layar aplikasi di sini. Simpan gambarnya di folder `screenshots/`, lalu tampilkan dengan:
>
> `![Tampilan utama](screenshots/home.png)`

## 🔮 Rencana Pengembangan

- [ ] Lokasi otomatis dengan GPS
- [ ] Simpan kota favorit
- [ ] Pilihan satuan °C / °F
- [ ] Mode terang dan gelap manual

## 🙏 Kredit

- Data cuaca oleh [Open-Meteo.com](https://open-meteo.com/) (lisensi [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) untuk penggunaan non-komersial)

## 👩‍💻 Pembuat

**Winda Azzhra** ([@windadotcom](https://github.com/windadotcom))

Proyek ini dibuat sebagai latihan belajar pengembangan aplikasi Android dengan Jetpack Compose.
