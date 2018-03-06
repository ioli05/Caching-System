package observerPattern.classes;

import observerPattern.interfaces.CacheListener;

/**
 * The StatsListener collects hit / miss / update stats for a cache.
 *
 * @param <K>
 * @param <V>
 */
public class StatsListener<K, V> implements CacheListener<K, V> {

    private int onHit = 0;
    private int onMiss = 0;
    private int onPut = 0;
    /**
     * Get the number of hits for the cache.
     *
     * @return number of hits
     */

    public int getHits() {
        return onHit;
    }

    /**
     * Get the number of misses for the cache.
     *
     * @return number of misses
     */
    public int getMisses() {
        return onMiss;
    }

    /**
     * Get the number of updates (put operations) for the cache.
     *
     * @return number of updates
     */
    public int getUpdates() {
        return onPut;
    }

    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onHit(java.lang.Object)
     */
    @Override
    public void onHit(final K key) {
            onHit++;
    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onMiss(java.lang.Object)
     */
    @Override
    public void onMiss(final K key) {
            onMiss++;

    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onPut(java.lang.Object, java.lang.Object)
     */
    @Override
    public void onPut(final K key, final V value) {
            onPut++;

    }
}
