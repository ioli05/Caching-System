package observerPattern.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import observerPattern.interfaces.CacheListener;

/**
 * The KeyStatsListener collects key-level stats for cache operations.
 *
 * @param <K>
 * @param <V>
 */
public class KeyStatsListener<K, V> implements CacheListener<K, V> {


    private Map<K, Integer> mapOnHit = new HashMap<K, Integer>();
    private Map<K, Integer> mapOnMiss = new HashMap<K, Integer>();
    private Map<K, Integer> mapOnPut = new HashMap<K, Integer>();

    /**
     * Get the number of hits for a key.
     *
     * @param key the key
     * @return number of hits
     */
    public int getKeyHits(final K key) {

       if(mapOnHit.containsKey(key)) {
           return mapOnHit.get(key);
       }
        return 0;
    }

    /**
     * Get the number of misses for a key.
     *
     * @param key the key
     * @return number of misses
     */
    public int getKeyMisses(final K key) {

        if(mapOnMiss.containsKey(key)) {
            return mapOnMiss.get(key);
        }
        return 0;
    }

    /**
     * Get the number of updates for a key.
     *
     * @param key the key
     * @return number of updates
     */
    public int getKeyUpdates(final K key) {

        if(mapOnPut.containsKey(key)) {
            return mapOnPut.get(key);
        }
        return 0;
    }

    class SortComparator implements Comparator<Entry<K, Integer>> {

    @Override
    public int compare(Entry<K, Integer> arg0, Entry<K, Integer> arg1) {
        if (arg0.getValue() > arg1.getValue()) {
            return -1;
        }
        if (arg0.getValue() < arg1.getValue()) {
            return 1;
        }
        return 0;
    }
  }

    /**
     * Get the @top most hit keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> createTop(final Map<K, Integer> hashmap, final int top) {

        List<Entry<K, Integer>> unsortedList = new ArrayList<Entry<K, Integer>>(hashmap.entrySet());


        Collections.sort(unsortedList, new SortComparator());

        List<K> sortedList = new ArrayList<K>();

        for (int i = 0; i < top; i++) {
            sortedList.add(unsortedList.get(i).getKey());
        }
        return sortedList;

    }



    public List<K> getTopHitKeys(final int top) {

        return createTop(mapOnHit, top);

    }

    /**
     * Get the @top most missed keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopMissedKeys(final int top) {

        return createTop(mapOnMiss, top);
    }

    /**
     * Get the @top most updated keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopUpdatedKeys(final int top) {

        return createTop(mapOnPut, top);
    }

    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onHit(java.lang.Object)
     */
    @Override
    public void onHit(final K key) {

            if (mapOnHit.containsKey(key)) {

                mapOnHit.put(key, mapOnHit.get(key).intValue() + 1);
                return;
            }

        mapOnHit.put(key, 1);

    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onMiss(java.lang.Object)
     */
    @Override
    public void onMiss(final K key) {

        if (mapOnMiss.containsKey(key)) {

            mapOnMiss.put(key, mapOnMiss.get(key).intValue() + 1);
            return;
        }

    mapOnMiss.put(key, 1);

    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onPut(java.lang.Object, java.lang.Object)
     */
    @Override
    public void onPut(final K key, final V value) {

        if (mapOnPut.containsKey(key)) {

            mapOnPut.put(key, mapOnPut.get(key).intValue() + 1);
            return;
        }

    mapOnPut.put(key, 1);

    }
}

