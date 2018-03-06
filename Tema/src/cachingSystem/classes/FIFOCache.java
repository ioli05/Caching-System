package cachingSystem.classes;

import java.util.LinkedHashMap;
import java.util.Map;

import cachingSystem.interfaces.Cache;
import dataStructures.classes.Pair;

/**
 * The FIFOCache class should be considered a blackbox. All you need is its API!
 */
public class FIFOCache<K, V> implements Cache<K, V> {

    private LinkedHashMap<K, V> cache;

    public FIFOCache() {
        cache = new LinkedHashMap<>();
    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#get(java.lang.Object)
     */
    @Override
    public V get(final K key) {
        return cache.get(key);
    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(final K key, final V value) {
        cache.put(key, value);
    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#size()
     */
    @Override
    public int size() {
        return cache.size();
    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#remove(java.lang.Object)
     */
    @Override
    public V remove(final K key) {
        return cache.remove(key);
    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#clearAll()
     */
    @Override
    public void clearAll() {
        cache.clear();
    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#getEldestEntry()
     */
    @Override
    public Pair<K, V> getEldestEntry() {
        if (isEmpty()) {
            return null;
        }

        Map.Entry<K, V> eldest = cache.entrySet().iterator().next();

        return new Pair<K, V>(eldest.getKey(), eldest.getValue());
    }
}
