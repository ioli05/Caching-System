package cachingSystem.classes;

import dataStructures.classes.Pair;

/**
 * Class that adapts the FIFOCache class to the ObservableCache abstract class.
 */
public class ObservableFIFOCache<K, V> extends ObservableCache<K, V> {

    public FIFOCache<K, V> cache = new FIFOCache<K, V>();
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#get(java.lang.Object)
     */
    @Override
    public V get(final K key) {

        V element =  cache.get(key);

        if (element == null) {
            cacheListener.onMiss(key);
        } else {
            cacheListener.onHit(key);
        }

        return element;


    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(final K key, final V value) {

        cache.put(key, value);
	cacheListener.onPut(key, value);
        clearStaleEntries();
	
        

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
        return cache == null;
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
       cache.clearAll();

    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#getEldestEntry()
     */
    @Override
    public Pair<K, V> getEldestEntry() {
        return cache.getEldestEntry();

    }

}
