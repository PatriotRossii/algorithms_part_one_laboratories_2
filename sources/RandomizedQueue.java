import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int capacity, size;
    private Item[] elements;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        elements = (Item[]) new Object[this.capacity];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        elements[size++] = item;

        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    private void resize(int newCapacity) {
        if (isEmpty()) return;

        capacity = newCapacity;
        Item[] newStorage = (Item[]) new Object[capacity];

        for (int i = 0; i != size; ++i) {
            newStorage[i] = elements[i];
        }
        elements = newStorage;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int randomIndex = StdRandom.uniformInt(size);
        Item element = elements[randomIndex];

        size -= 1;
        elements[randomIndex] = elements[size];
        elements[size] = null;

        if (size <= capacity / 4)
            resize(capacity / 2);

        return element;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        int randomIndex = StdRandom.uniformInt(size);
        return elements[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final RandomizedQueue<Item> copy;

        RandomizedQueueIterator() {
            copy = new RandomizedQueue<Item>();

            for (int i = 0; i != size; ++i) {
                copy.enqueue(elements[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        System.out.println("IS EMPTY: " + queue.isEmpty());

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println("SIZE: " + queue.size());
            System.out.println("IS EMPTY: " + queue.isEmpty());
        }

        System.out.println("Elements 0-9 added. We should have seen 1 to 10 printed");

        for (Integer i : queue) {
            System.out.println(i);
        }

        System.out.println(
                "Finished iterating over the iterator. Elements should appear in random order");

    }
}
