package dataStructures.classes;

/**
 * The Pair class is a convenient way of storing key-value pairs.
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
    public K key;
    public V value;

    /*
     *
     */
    public Pair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }
    /*
     *
     */
    public K getKey() {
        return key;
    }
    /*
     *
     */
    public void setKey(final K key) {
        this.key = key;
    }
    /*
     *
     */
    public V getValue() {
        return value;
    }
    /*
     *
     */
    public void setValue(final V value) {
        this.value = value;
    }
}
