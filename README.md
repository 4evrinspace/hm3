Поставьте 4 пожалуйста, данный проект был честно написан (но гпт приходилос часто использовать т.к я плохо в джаве разбираюсь)


# Cистема анализа текста


-  Анализирует текст (слова, параграфы, символы)
-  Ищет плагиат 
-  Есть облака слов
-  И хранение файлов

## Запуск

Сначала нужно запустить PostgreSQL в Docker:
```bash
docker run -d \
    --name postgres-db \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=filedb \
    -p 5433:5432 \
    postgres:latest
```

Затем запускаем приложение:
```bash
mvn spring-boot:run
```

## Технологии

- Все что было в рамках курса + то, что гпт сказал для генерации облаков слов 

## Тесты
Для запуска тестов используйте:
```bash
mvn test
```
Покрытие тестами более 70% (вроде )

Для тестов 