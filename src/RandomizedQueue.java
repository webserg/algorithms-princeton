import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    Item[] array;
    int N = 0;


    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[4];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (array.length == N) {
            resize();
        }
        array[N++] = item;
    }

    private void resize() {
        Item[] arrayNew = (Item[]) new Object[N * 2];
        System.arraycopy(array, 0, arrayNew, 0, N);
        array = arrayNew;
    }

    private void shrink(int idx, Item[] shrinkArray) {
        for (int i = idx; i < N - 1; i++) {
            shrinkArray[i] = shrinkArray[i + 1];
        }
    }

    // delete and return a random item
    public Item dequeue() {
        int randomIdx = StdRandom.uniform(N);
        Item tmp = array[randomIdx];
        shrink(randomIdx, array);
        N--;
        return tmp;
    }

    // return (but do not delete) a random item
    public Item sample() {
        int randomIdx = StdRandom.uniform(N);
        return array[randomIdx];
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] arrayCopy;
        private int counter;

        private ArrayIterator() {
            arrayCopy = (Item[]) new Object[N];
            System.arraycopy(array, 0, arrayCopy, 0, N);
            counter = N;
        }

        @Override
        public boolean hasNext() {
            return counter != 0;
        }

        @Override
        public Item next() {
            int randomIdx = StdRandom.uniform(counter);
            Item tmp = arrayCopy[randomIdx];
            shrink(randomIdx, arrayCopy);
            counter--;
            return tmp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing
    public static void main(String[] args) {
        testIsEmpty();
        testRemoveFirst();
        testSize();
        testIterator();
        testIteratorRandom();
    }

    private static void testIsEmpty() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertVal(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        assertVal(!randomizedQueue.isEmpty());
    }

    private static void testRemoveFirst() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assert randomizedQueue.isEmpty();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        assertVal(randomizedQueue.dequeue() != null);
    }


    private static void testSize() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertVal(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.dequeue();
        assertVal(randomizedQueue.size() == 1);
    }

    private static void testIterator() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertVal(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        int sum = 0;
        for (Integer i : randomizedQueue) {
            sum += i;
        }
        assertVal(sum == 15);
    }

    private static void testIteratorRandom() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertVal(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : randomizedQueue) {
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder.toString());
        assertVal(!"12345".equals(stringBuilder.toString()));
    }

    private static void assertVal(boolean b) {
        if (!b) {
            throw new RuntimeException("test failed");
        }
    }
}
