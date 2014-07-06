import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    public Deque() {
        head = tail = null;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // unit testing
    public static void main(String[] args) {
        testIsEmpty();
        testRemoveFirst();
        testRemoveLast();
        testSize();
    }

    private static void testIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertVal(deque.isEmpty());
        deque.addFirst(1);
        assertVal(!deque.isEmpty());
    }

    private static void testRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        assert deque.isEmpty();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertVal(deque.removeFirst() == 3);
    }

    private static void testRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        assert deque.isEmpty();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        assertVal(deque.removeLast() == 3);
    }

    private static void testSize() {
        Deque<Integer> deque = new Deque<>();
        assertVal(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        assertVal(deque.size() == 3);
    }

    private static void assertVal(boolean b) {
        if (!b) {
            throw new RuntimeException("test failed");
        }
    }

    public void addLast(Item item) {
        Node<Item> node = new Node<>(item);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public void addFirst(Item item) {
        Node<Item> node = new Node<>(item);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

//    public boolean search(Item k) {
//        Node<Item> cur = head;
//        while (cur != null) {
//            if (cur.data == k) return true;
//            cur = cur.next;
//        }
//        return false;
//    }

    public Item removeFirst() {
        Node<Item> tmp = null;
        if (head != null) {
            tmp = head;
            head = head.getNext();
        }
        return tmp != null ? tmp.getData() : null;
    }

    public Item removeLast() {
        Node<Item> tmp = null;
        if (tail != null) {
            tmp = tail;
            Node<Item> cur = head;
            Node<Item> prev = null;
            while (cur != tail) {
                prev = cur;
                cur = cur.getNext();
            }
            tail = prev;
            if (tail != null)
                tail.setNext(null);

        }
        return tmp != null ? tmp.getData() : null;
    }

//    public boolean delete(Item k) {
//        Node<Item> cur = head;
//        Node<Item> prev = null;
//        while (cur != null) {
//            if (cur.data == k) {
//                if (prev == null) {
//                    head = cur.next;
//                } else {
//                    if (cur == tail) {
//                        tail = prev;
//                    }
//                    prev.next = cur.next;
//                }
//                size--;
//                return true;
//            }
//            prev = cur;
//            cur = cur.next;
//        }
//        return false;
//    }

//    private String toString() {
//        Node<Item> n = head;
//        StringBuilder s = new StringBuilder();
//        while (n != null) {
//            s.append(n.toString());
//            n = n.getNext();
//        }
//        return s.toString();
//    }

//    public int getSize() {
//        return size;
//    }

    private class LinkedListIterator implements Iterator<Item> {
        Node<Item> cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<Item> tmp = cur;
            cur = cur.getNext();
            return tmp.getData();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

class Node<Item> {
    private Item data;
    private Node<Item> next;

    Node(Item data) {
        this.setData(data);
    }

//    private String toString() {
//        return getData() + " ";
//    }

    public Item getData() {
        return data;
    }

    public void setData(Item data) {
        this.data = data;
    }

    public Node<Item> getNext() {
        return next;
    }

    public void setNext(Node<Item> next) {
        this.next = next;
    }
}