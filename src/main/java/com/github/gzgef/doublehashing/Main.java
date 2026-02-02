package com.github.gzgef.doublehashing;

/**
 * Главный класс, демонстрирующий использование реализации HashMap.
 * Этот класс предоставляет простой пример использования хеш-таблицы.
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();

        System.out.println("Хеш-таблица пуста? " + hashMap.isEmpty());

        hashMap.put(1, "один");
        hashMap.put(2, "два");
        hashMap.put(3, "три");
        hashMap.put(4, "четыре");
        hashMap.put(5, "пять");
        hashMap.put(5, "шесть"); // Это обновит значение для ключа 5

        System.out.println("Содержимое хеш-таблицы: " + hashMap);

        hashMap.remove(1);
        hashMap.remove(3);

        System.out.println("Хеш-таблица после удалений: " + hashMap);
        System.out.println("Значение для ключа 2: " + hashMap.get(2));
        System.out.println("Текущий размер: " + hashMap.getSize());
    }
}
