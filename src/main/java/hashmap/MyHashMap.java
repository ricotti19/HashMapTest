package hashmap;

/**
 * A simple hash map using separate chaining with automatic resizing.
 *
 *  - Null keys are NOT allowed (put/get/remove/containsKey throw NullPointerException).
 *  - Null values ARE allowed.
 *  - Initial capacity defaults to 16. Capacity always doubles on resize.
 *  - Resize happens AFTER an insert when size / capacity exceeds the load factor (0.75).
 *
 * Likely bug spots:
 *  1. indexFor(): negative hashCodes must not produce a negative bucket index.
 *  2. put(): overwriting an existing key must replace the value and NOT increase size.
 *  3. remove(): removing the head, middle and tail of a bucket chain are different code paths.
 *  4. resize(): every entry must be rehashed with the NEW capacity; threshold uses > not >=.
 */

public class MyHashMap<K, V> {

    static final int DEFAULT_CAPACITY = 16;
    static final double LOAD_FACTOR = 0.75;

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive: " + initialCapacity);
        }
        this.buckets = (Node<K, V>[]) new Node[initialCapacity];
        this.size = 0;
    }

    /** Maps key to value. Returns the previous value, or null if there was none. */
    public V put(K key, V value) {
        requireNonNullKey(key);
        int index = indexFor(key, buckets.length);

        for (Node<K, V> n = buckets[index]; n != null; n = n.next) {
            if (n.key.equals(key)) {
                V old = n.value;
                n.value = value;
                return old;
            }
        }

        buckets[index] = new Node<>(key, value, buckets[index]);
        size++;

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    public V get(K key) {
        requireNonNullKey(key);
        Node<K, V> n = findNode(key);
        return n == null ? null : n.value;
    }

    public boolean containsKey(K key) {
        requireNonNullKey(key);
        return findNode(key) != null;
    }

    /** Removes the mapping for key. Returns the removed value, or null if absent. */
    public V remove(K key) {
        requireNonNullKey(key);
        int index = indexFor(key, buckets.length);

        Node<K, V> prev = null;
        Node<K, V> curr = buckets[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = curr.next; // removing head of chain
                } else {
                    prev.next = curr.next;      // removing middle or tail
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    /** Current number of buckets (package-private so tests can observe resizing). */
    int capacity() {
        return buckets.length;
    }

    // ---------------------------------------------------------------- internals

    private Node<K, V> findNode(K key) {
        int index = indexFor(key, buckets.length);
        for (Node<K, V> n = buckets[index]; n != null; n = n.next) {
            if (n.key.equals(key)) {
                return n;
            }
        }
        return null;
    }

    /** Math.floorMod keeps the index non-negative even for negative hash codes. */
    static int indexFor(Object key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] old = buckets;
        Node<K, V>[] bigger = (Node<K, V>[]) new Node[old.length * 2];

        for (Node<K, V> head : old) {
            Node<K, V> n = head;
            while (n != null) {
                Node<K, V> next = n.next;
                int index = indexFor(n.key, bigger.length);
                n.next = bigger[index];
                bigger[index] = n;
                n = next;
            }
        }
        buckets = bigger;
    }

    private static void requireNonNullKey(Object key) {
        if (key == null) {
            throw new NullPointerException("null keys are not supported");
        }
    }

    /**
     * Invariant checker used by tests. Verifies:
     *  - the number of nodes across all buckets equals size()
     *  - every node sits in the bucket its hash maps to
     *  - no duplicate keys exist
     *  - the load factor is respected after every operation
     */
    void checkInvariants() {
        int count = 0;
        java.util.Set<K> seen = new java.util.HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            for (Node<K, V> n = buckets[i]; n != null; n = n.next) {
                count++;
                if (indexFor(n.key, buckets.length) != i) {
                    throw new AssertionError("key " + n.key + " in wrong bucket " + i);
                }
                if (!seen.add(n.key)) {
                    throw new AssertionError("duplicate key " + n.key);
                }
            }
        }
        if (count != size) {
            throw new AssertionError("size field " + size + " but found " + count + " nodes");
        }
        if ((double) size / buckets.length > LOAD_FACTOR) {
            throw new AssertionError("load factor exceeded: " + size + "/" + buckets.length);
        }
    }
}
