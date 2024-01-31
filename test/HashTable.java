package test;

import org.jetbrains.annotations.Nullable;

public interface HashTable {
    void add(int x, int y);

    void delete(int x);

    @Nullable
    Integer search(int x);

    String print();
}
