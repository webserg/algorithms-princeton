import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<K> implements Iterable<K> {
    private Node<K> head;
    private Node<K> tail;
    private int size = 0;

    public Deque() {
        head = tail = null;
    }

    // return an iterator over items in order from front to end
    public Iterator<K> iterator() {
        return new LinkedListIterator();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return getSize() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return getSize();
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

    public void addLast(K k) {
        Node<K> node = new Node<>(k);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(K k) {
        Node<K> node = new Node<>(k);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

//    public boolean search(K k) {
//        Node<K> cur = head;
//        while (cur != null) {
//            if (cur.data == k) return true;
//            cur = cur.next;
//        }
//        return false;
//    }

    public K removeFirst() {
        Node<K> tmp = null;
        if (head != null) {
            tmp = head;
            head = head.next;
        }
        return tmp != null ? tmp.data : null;
    }

    public K removeLast() {
        Node<K> tmp = null;
        if (tail != null) {
            tmp = tail;
            Node<K> cur = head;
            Node<K> prev = null;
            while (cur != tail) {
                prev = cur;
                cur = cur.next;
            }
            tail = prev;
            if (tail != null)
                tail.next = null;

        }
        return tmp != null ? tmp.data : null;
    }

//    public boolean delete(K k) {
//        Node<K> cur = head;
//        Node<K> prev = null;
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

    public String toString() {
        Node<K> n = head;
        StringBuilder s = new StringBuilder();
        while (n != null) {
            s.append(n.toString());
            n = n.next;
        }
        return s.toString();
    }

    public int getSize() {
        return size;
    }

    class LinkedListIterator implements Iterator<K> {
        Node<K> cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<K> tmp = cur;
            cur = cur.next;
            return tmp.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

class Node<K> {
    K data;
    Node<K> next;

    Node(K data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + " ";
    }
}