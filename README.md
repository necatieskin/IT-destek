# IT Destek - Arıza Takip Sistemi

Bu proje, kurum içi IT arıza taleplerini yönetmek için Spring Boot (Backend) ve Tailwind CSS (Frontend) ile geliştirilmiştir.

## 🚀 Çalıştırma Adımları

1. **Projeyi Açın:** Klasörü IntelliJ IDEA ile açın ve Maven bağımlılıklarının yüklenmesini bekleyin.
2. **Backend'i Başlatın:** `ItDestekApplication.java` dosyasına sağ tıklayıp **Run** seçeneğine basın (Uygulama `http://localhost:8080` adresinde çalışacaktır).
3. **Frontend'i Açın:** `index.html` dosyasına çift tıklayarak tarayıcıda açın. Giriş ekranından rolünüzü seçerek paneli kullanın.

## 🔒 Kimlik Bilgileri (Basic Auth)

* **User (Sadece Listeleme):** `user` / `1234`
* **Admin (Tam Yetki):** `admin` / `admin123`

## 📬 API Endpointleri

* `GET /issues` - Tüm arızaları listeler.
* `GET /issues/{id}` - ID'ye göre arıza getirir.
* `POST /issues` - Yeni arıza ekler.
* `PUT /issues/{id}` - Arızayı günceller.
* `DELETE /issues/{id}` - Arızayı siler.
* `GET /issues/latest` - Son eklenen 5 arızayı listeler.

## 🗄️ Veritabanı (H2 Console)
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Kullanıcı Adı:** `sa` (Şifre boş bırakılacak)
