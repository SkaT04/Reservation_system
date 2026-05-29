Reservation System — система бронирования
RESTful веб-приложение на Java 21 + Spring Boot(Web, Data JPA). 
Реализована классическая многослойная архитектура: контроллеры для обработки HTTP-запросов, сервисный слой с бизнес-логикой, работа с PostgreSQL 15 через JPA/Hibernate. 
Настроен глобальный обработчик ошибок для единого формата ответов API. 
Система и PostrgreSQL, будучи контейнерами, упакованы в Docker Compose для быстрого и удобного развёртывания.

Для запуска:
1) git clone [https://github.com/SkaT04/Reservation_system.git](https://github.com/SkaT04/Reservation_system.git)
cd Reservation_system

2) docker compose up --build
