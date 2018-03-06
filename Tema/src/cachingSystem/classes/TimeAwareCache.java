package cachingSystem.classes;

import java.sql.Timestamp;
import java.util.HashMap;

import cachingSystem.interfaces.CacheStalePolicy;
import dataStructures.classes.Pair;

/**
 * The TimeAwareCache offers the same functionality as the LRUCache, but also stores a timestamp for
 * each element. The timestamp is updated after each get / put operation for a key. This
 * functionality allows for time based cache stale policies (e.g. removing entries that are older
 * than 1 second).
 */
public class TimeAwareCache<K, V> extends LRUCache<K, V> {

    /* TODO: figure out which methods need to overridden in order to implement the timestamp
    functionality */

    HashMap<K, Timestamp> timeHashMap = new HashMap<K, Timestamp>();


    /**
     * Get the timestamp associated with a key, or null if the key is not stored in the cache.
     *
     * @param key the key
     * @return the timestamp, or null
     */

    @Override
    public void put(final K key, final V value) {

        if (timeHashMap.containsKey(key)) {
            timeHashMap.put(key, new Timestamp(System.currentTimeMillis()));
            super.put(key, value);
            return;
        }

        super.put(key, value);
        timeHashMap.put(key, new Timestamp(System.currentTimeMillis()));

    }

    @Override
    public V get(final K key) {

        if (timeHashMap.containsKey(key)) {
            timeHashMap.put(key, new Timestamp(System.currentTimeMillis()));
            return super.get(key);

        }

        return null;
    }


    @Override
    public int size() {
        // TODO Auto-generated method stub
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return super.isEmpty();
    }

    @Override
    public V remove(K key) {
        timeHashMap.remove(key);
        return super.remove(key);
    }

    @Override
    public void clearAll() {
        timeHashMap.clear();
        super.clearAll();
    }

    @Override
    public Pair<K, V> getEldestEntry() {
        return super.getEldestEntry();
    }

    public Timestamp getTimestampOfKey(K key) {
        if (timeHashMap.containsKey(key)) {
            return timeHashMap.get(key);
        }
        return null;

    }

    /**
     * Set a cache stale policy that should remove all elements older than @millisToExpire
     * milliseconds. This is a convenience method for setting a time based policy for the cache.
     *
     * @param millisToExpire the expiration time, in milliseconds
     */
    public void setExpirePolicy(long millisToExpire) {
        /* TODO: create a stale policy and set it for the TimeAwareCache */

                CacheStalePolicy<K, V> cache = new CacheStalePolicy<K, V>()
        {

            @Override
            public boolean shouldRemoveEldestEntry(Pair<K, V> entry) {
                //Timestamp systemTime = new Timestamp(System.currentTimeMillis());
                Timestamp t = (Timestamp) entry.getValue();
                Timestamp ti = new Timestamp(millisToExpire);
                if (t.compareTo(ti) > 0) {
                    return true;
                }
                return false;

            }

        };
        this.setStalePolicy(cache);
    }

}

