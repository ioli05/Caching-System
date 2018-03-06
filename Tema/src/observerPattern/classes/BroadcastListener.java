package observerPattern.classes;

import java.util.ArrayList;
import java.util.Iterator;

import observerPattern.interfaces.CacheListener;

/**
 * The BroadcastListener broadcasts cache events to other listeners that have been added to it.
 */
public class BroadcastListener<K, V> implements CacheListener<K, V> {

    /**
     * Add a listener to the broadcast list.
     *
     * @param listener the listener
     */

    private ArrayList<CacheListener<K, V>> list = new ArrayList<CacheListener<K, V>>();

    public final void addListener(final CacheListener<K, V> listener) {

        list.add(listener);

    }

    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onHit(java.lang.Object)
     */
    @Override
    public void onHit(final K key) {
        // TODO Auto-generated method stub
        Iterator<CacheListener<K, V>> it = list.iterator();

        while (it.hasNext()) {

            CacheListener<K, V> listener = it.next();
            listener.onHit(key);
        }

    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onMiss(java.lang.Object)
     */
    @Override
    public void onMiss(final K key) {
        // TODO Auto-generated method stub
        Iterator<CacheListener<K, V>> it = list.iterator();

        while (it.hasNext()) {

            CacheListener<K, V> listener = it.next();
            listener.onMiss(key);
        }

    }
    /*
     * (non-Javadoc)
     * @see observerPattern.interfaces.CacheListener#onPut(java.lang.Object, java.lang.Object)
     */
    @Override
    public void onPut(final K key, final V value) {
        // TODO Auto-generated method stub

        Iterator<CacheListener<K, V>> it = list.iterator();

        while (it.hasNext()) {

            CacheListener<K, V> listener = it.next();
            listener.onPut(key, value);
        }

    }
}
