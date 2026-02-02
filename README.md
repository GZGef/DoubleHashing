# DoubleHashing

[![Лицензия](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Версия Java](https://img.shields.io/badge/Java-11+-green.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

Java-реализация хеш-таблиц с различными стратегиями разрешения коллизий: **двойное хеширование**, **линейное пробирование** и **цепочки**.

## Обзор

Этот проект предоставляет три различные реализации структуры данных хеш-таблицы, каждая использует разную стратегию разрешения коллизий:

1. **Двойное хеширование** - Использует две хеш-функции для определения последовательности проб
2. **Линейное пробирование** - Использует простое линейное пробирование с шагом 1
3. **Цепочки** - Использует связные списки для обработки коллизий

## Возможности

- Обобщенная реализация хеш-таблицы с автоматическим изменением размера
- Три различные стратегии разрешения коллизий
- Комплексная Javadoc документация
- Включены модульные тесты
- Поддержка Maven
- Кроссплатформенная совместимость

## Структура проекта

```
doublehashing/
├── pom.xml                          # Конфигурация сборки Maven
├── README.md                        # Документация проекта
├── CONTRIBUTING.md                  # Руководство по внесению вклада
├── CODE_OF_CONDUCT.md              # Стандарты поведения сообщества
├── LICENSE                          # Лицензия Apache 2.0
├── .gitignore                       # Комплексные шаблоны игнорирования
├── src/
│   ├── main/java/com/github/gzgef/doublehashing/
│   │   ├── Entry.java               # Интерфейс ключ-значение
│   │   ├── IHashTable.java          # Интерфейс хеш-таблицы
│   │   ├── Map.java                 # Абстрактный базовый класс
│   │   ├── HashMap.java             # Реализация двойного хеширования
│   │   └── Main.java                # Демо-приложение
│   └── test/java/com/github/gzgef/doublehashing/
│       ├── HashTable.java           # Тестовый интерфейс
│       ├── HashTableNode.java       # Тестовый класс узла
│       ├── DeletedNode.java         # Маркер удаленного узла
│       ├── ChainHashTable.java      # Реализация цепочек
│       ├── OpenAddressHashTableLP.java  # Реализация линейного пробирования
│       ├── OpenAddressHashTableDH.java  # Реализация двойного хеширования
│       └── Main.java                # Тестовый запуск
```

## Сборка проекта

### Предварительные требования

- Java 11 или выше
- Maven 3.6 или выше

### Команды сборки

```bash
# Очистка и компиляция
mvn clean compile

# Запуск тестов
mvn test

# Упаковка проекта
mvn package

# Установка в локальный репозиторий
mvn install
```

### Запуск демо

```bash
# Запуск основного демо-приложения
mvn exec:java -Dexec.mainClass="com.github.gzgef.doublehashing.Main"
```

## Примеры использования

### Использование обобщенного HashMap (Двойное хеширование)

```java
import com.github.gzgef.doublehashing.HashMap;

public class Example {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        
        // Вставка ключ-значение
        hashMap.put(1, "один");
        hashMap.put(2, "два");
        hashMap.put(3, "три");
        
        // Получение значений
        String value = hashMap.get(2);  // Возвращает "два"
        
        // Удаление записей
        hashMap.remove(1);
        
        // Получение размера
        int size = hashMap.getSize();
        
        // Проверка на пустоту
        boolean isEmpty = hashMap.isEmpty();
    }
}
```

### Использование тестовых реализаций

Тестовые реализации (ChainHashTable, OpenAddressHashTableLP, OpenAddressHashTableDH) предназначены для тестирования и сравнения:

```java
import com.github.gzgef.doublehashing.ChainHashTable;
import com.github.gzgef.doublehashing.OpenAddressHashTableLP;
import com.github.gzgef.doublehashing.OpenAddressHashTableDH;

public class TestExample {
    public static void main(String[] args) {
        // Создание экземпляров различных реализаций хеш-таблиц
        ChainHashTable chainTable = new ChainHashTable();
        OpenAddressHashTableLP lpTable = new OpenAddressHashTableLP();
        OpenAddressHashTableDH dhTable = new OpenAddressHashTableDH();
        
        // Добавление записей
        chainTable.add(1, 100);
        lpTable.add(1, 100);
        dhTable.add(1, 100);
        
        // Поиск записей
        Integer value = chainTable.search(1);  // Возвращает 100
        
        // Поиск минимального и максимального ключей
        Integer min = chainTable.min();
        Integer max = chainTable.max();
        
        // Вывод содержимого таблицы
        String representation = chainTable.print();
    }
}
```

## Стратегии разрешения коллизий

### Двойное хеширование

Двойное хеширование использует две хеш-функции для определения последовательности проб:

- **Первичный хеш**: `hash1(key) = key % capacity`
- **Вторичный хеш**: `hash2(key) = (key * HASH_PARAM) % (capacity - 1)`

Последовательность проб: `hash1(key)`, `hash1(key) + hash2(key)`, `hash1(key) + 2*hash2(key)`, ...

### Линейное пробирование

Линейное пробирование использует одну хеш-функцию с шагом 1:

- **Хеш-функция**: `hash(key) = (key * HASH_PARAM) % capacity`
- **Последовательность проб**: `hash(key)`, `hash(key) + 1`, `hash(key) + 2`, ...

### Цепочки

Цепочки используют связные списки для обработки коллизий:

- **Хеш-функция**: `hash(key) = key % capacity`
- **Разрешение коллизий**: Хранение нескольких записей в одном bucket с использованием связных списков

## Характеристики производительности

| Операция | Двойное хеширование | Линейное пробирование | Цепочки |
|----------|---------------------|----------------------|---------|
| Вставка  | O(1) в среднем      | O(1) в среднем       | O(1) в среднем |
| Поиск    | O(1) в среднем      | O(1) в среднем       | O(1) в среднем |
| Удаление | O(1) в среднем      | O(1) в среднем       | O(1) в среднем |
| Худший случай | O(n)           | O(n)                 | O(n)     |

**Примечание**: Производительность зависит от коэффициента загрузки и качества хеш-функции. Реализации используют коэффициент загрузки 0.75 для автоматического изменения размера.

## Конфигурация

### Хеш-параметры

- **Двойное хеширование**: `HASH_PARAM = 47`
- **Линейное пробирование**: `HASH_PARAM = 37`
- **Начальная емкость**: 8
- **Порог коэффициента загрузки**: 0.75

### Стратегия изменения размера

Когда коэффициент загрузки превышает 0.75, хеш-таблица автоматически удваивает свою емкость и повторно хеширует все записи.

## Тестирование

Проект включает комплексные тестовые реализации, которые можно использовать для бенчмаркинга и сравнения:

```bash
# Запуск всех тестов
mvn test

# Запуск конкретного теста
mvn test -Dtest=OpenAddressHashTableDHTest
```

## Внесение вклада

Взносы приветствуются! Пожалуйста, следуйте этим рекомендациям:

1. Форкните репозиторий
2. Создайте ветку функции (`git checkout -b feature/amazing-feature`)
3. Зафиксируйте изменения (`git commit -m 'Add amazing feature'`)
4. Отправьте в ветку (`git push origin feature/amazing-feature`)
5. Откройте Pull Request

Пожалуйста, прочитайте [CONTRIBUTING.md](CONTRIBUTING.md) для получения подробной информации о нашем кодексе поведения и процессе отправки pull request.

## Лицензия

Этот проект лицензирован по лицензии Apache 2.0 - см. файл [LICENSE](LICENSE) для получения подробной информации.

## Авторы

- **Roman Beskrovnyi** - [romankh3](https://github.com/romankh3)

## Благодарности

- Java Collections Framework для вдохновения
- Университетские курсы алгоритмов для концепций хеш-таблиц
- Сообщество с открытым исходным кодом за лучшие практики

## История версий

- **1.0.0** - Первоначальный выпуск с тремя реализациями хеш-таблиц

## Поддержка

Для получения поддержки, пожалуйста, откройте issue на [GitHub repository](https://github.com/GZGef/DoubleHashing/issues).

## Связанные проекты

- [Java Collections Framework](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html)
- [Apache Commons Collections](https://commons.apache.org/proper/commons-collections/)