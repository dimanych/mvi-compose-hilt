# My Application

## Описание

Приложение-пример на Android, демонстрирующее подход **Clean Architecture** с реализацией паттерна **MVI** и современными библиотеками.

## Стек технологий

- **Язык**: Kotlin  
- **UI**: Jetpack Compose, Material3  
- **Архитектура**: Clean Architecture, MVI (Model-View-Intent)  
- **Внедрение зависимостей**: Dagger Hilt  
- **Сетевой слой**: Retrofit + Moshi + Coroutines Adapter  
- **Локальное хранилище**: Room + Paging 3  
- **Загрузка изображений**: Landscapist (Glide)  
- **Асинхронность**: Kotlin Coroutines  
- **Тестирование**:  
  - Unit-тесты: JUnit, AndroidX Test
  - UI-тесты: Kaspresso, Kaspresso Compose

## Структура проекта

- `domain` — бизнес-логика и use-case  
- `data` — репозитории, источники данных (remote/local)  
- `presentation` — ViewModel, экраны Compose, MVI-потоки  
- `util` — утилиты, constants, тестовые идентификаторы  
- 
- `androidTest` — UI-тесты (Kaspresso)  


## Запуск тестов
- **UI-тесты (Kaspresso)**:  
  ```bash
  ./gradlew connectedAndroidTest
  ```  
