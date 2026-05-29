# === Этап 1: Сборка приложения ===
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Копируем файлы сборщика для кэширования зависимостей
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Загружаем зависимости (если они не менялись, этот шаг пропустится при повторной сборке)
RUN ./mvnw dependency:go-offline

# Копируем исходный код и собираем jar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# === Этап 2: Финальный легковесный образ для запуска ===
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Копируем собранный jar-файл из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Открываем порт, который использует Spring Boot по умолчанию
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]