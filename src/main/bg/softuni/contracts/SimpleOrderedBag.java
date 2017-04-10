package main.bg.softuni.contracts;

import java.util.Collection;

public interface SimpleOrderedBag<T extends Comparable<T>> extends Iterable<T> {

    void add (T element);

    void addAll(Collection<T> elements);

    int size();

    String joiWith(String joiner);

    boolean remove(T element);

    int capacity();

}
