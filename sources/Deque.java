import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node head = null, tail = null;

    private class Node {
        Item item;
        Node previous, next;
    }

    // construct an empty deque
    // required time complexity: O(1)
    public Deque() {

    }

    // is the deque empty?
    // required time complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    // required time complexity: O(1)
    public int size() {
        return size;
    }

    // add the item to the front
    // required time complexity: O(1)
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node node = new Node();
        node.item = item;

        if (head == null) tail = node;
        else {
            node.next = head;
            head.previous = node;
        }
        head = node;

        size += 1;
    }

    // add the item to the back
    // required time complexity: O(1)
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node node = new Node();
        node.item = item;

        if (head == null) head = node;
        else {
            node.previous = tail;
            tail.next = node;
        }
        tail = node;

        size += 1;
    }

    // remove and return the item from the front
    // required time complexity: O(1)
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node oldFirst = head;
        head = head.next;
        size -= 1;

        if (head == null) tail = null;
        else head.previous = null;

        return oldFirst.item;
    }

    // remove and return the item from the back
    // required time complexity: O(1)
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node oldLast = tail;
        size -= 1;
        tail = oldLast.previous;

        if (tail == null) head = null;
        else tail.next = null;

        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    // required time complexity: O(1)
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public class DequeIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<Integer>();

        System.out.println("IS EMPTY: " + deck.isEmpty());

        for (int i = 0; i < 10; i++) {
            deck.addFirst(i);
            System.out.println("SIZE: " + deck.size());
            System.out.println("IS EMPTY: " + deck.isEmpty());
        }

        System.out.println("Elements 0-9 added. We should have seen 1 to 10 printed");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println(
                "Finished iterating over the iterator. Elements should appear from 9 to 0.");

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.removeLast());
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 removed. They should appear from 0 to 9.");

        for (int i = 0; i < 10; i++) {
            deck.addLast(i);
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 added.");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println(
                "Finished iterating over the iterator. Elements should appear from 0 to 9");

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.removeFirst());
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 removed. Elements should appear from 0 to 9");
    }
}
