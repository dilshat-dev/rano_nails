# Используем официальный образ Java 17 с Maven
FROM eclipse-temurin:17-jdk-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем все файлы проекта
COPY . .

# Даем права на выполнение скрипта сборки
RUN chmod +x mvnw

# Собираем приложение, без запуска тестов (можно убрать -DskipTests если хочешь)
RUN ./mvnw clean package -DskipTests

# Запускаем собранный jar
CMD ["java", "-jar", "target/rano-0.0.1-SNAPSHOT.jar"]
