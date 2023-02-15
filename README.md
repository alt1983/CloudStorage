#Приложение Облачное хранилище

##Запуск
Приложение запускается на порту 8080

##Авторизация
POST http://localhost:8080/login

##Вывод списка файлов
GET http://localhost:8080/list

##Загрузка файла
POST http://localhost:8080/file

##Скачивание файла
GET http://localhost:8080/file

##Удаление файла
DELETE http://localhost:8080/file

##Изменение имени файла
PUT http://localhost:8080/file

##Выход
POST http://localhost:8080/logout

##Тестирование
* Реализован набор тестов с использованием Mockito и JUnit
* Реализован набор тестов с использованием Testcontainers
* Для добства тестирования создан Docker-image cloud

##Тестовые пользователи
* USER: user1/user1
* ADMIN: admin1/admin1



