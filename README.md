# İzin Modülü

Swagger: http://localhost:8080/swagger-ui/index.html
##Projenin ve gerekli bağımlılıkların indirilmesi
```
* git clone https://github.com/umutatagun/spring-izin-servisi.git
* cd izin-modulu
* mvn install
```

### Veritabanını docker ile kullanmak için
```
docker-compose up
```

### Local MySQL ile bağlanmak için

* MySql user ve password bilgilerini .env dosyası içersinde belirtmelisiniz.


### Projeyi Ayağa Kaldırmak İçin
Eğer local veritabanı bağlantısı kullanıyorsanız
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Docker ile veritabanı bağlantısı kullanıyorsanız
```
mvn spring-boot:run
```

## Uygulamanın Kullanımı

###Postman Collection'ını dahil etme
* izin_modulu_postman_collection.json isimli dosyayı postman'e import ediniz.
  * https://testfully.io/blog/import-from-postman/
* Collection içersinde varolan bütün servisleri test edebilmemiz için gerekli endpointler hazır olarak gelecektir.

### Çalışan Yaratma
* Postman içersinde Employee/CreateEmployee servisinden gerekli isteği göndererek çalışan oluşturma işlemini tamamlayabiliriz.
* Çalışan yaratmak için herhangi bir login işlemine gerek yoktur.
* Bu işlem sonrasında login objesi içersinde gönderdiğimiz bilgiler ile authentication ve authorization işlemlerini gerçekleştireceğiz.

### Authentication & Authorization
* Username ve password bilgilerini postman Auth/Login servisine istek olarak göndermeliyiz.
* Buradan dönen token, postman scriptiyle otomatik olarak environments'a tanımlanacaktır. (detay için Auth/Login servisinde tests alanını inceleyebilirsiniz)

### Çalışan ve İzin kontrol servisleri
* Çalışan ve izin isteklerinin hepsini veya id bazlı aramasını yapabileceğimiz servisleri postmande bulabilirsiniz.
* İzin onayı, reddi gibi istekler sadece manager rolüne sahip çalışanlar tarafından yapılabilir, manager rolüne sahip olmayan bir çalışanın bu endpointe erişim hakkı yoktur. Hata ile karşılaşacaktır.

### Yıllık izinlerin yenilenmesi
* İzinler, çalışanların tecrübelerine göre verilmiştir.
* Her yıl 30 Aralık'ta izin süreleri güncellenir. (Cron job olarak SchedulePermissions.class'a eklendi)
* Manuel test yapılabilmesi için, Postman Test/RefleshPermissions servisi tetiklenmelidir.
* Servis tetiklendiği zaman her yıl 30 aralıkta çalışacak cron job bir kerelik çalıştırılır ve çalışanlara izinleri verilir.

 
###Dil ayarı için
* Default dil ayarı Türkçedir. 
* Postman headerlarında Accept-Language alanı en_EN olarak güncellenirse hata mesajları ingilizce olarak dönecektir.












