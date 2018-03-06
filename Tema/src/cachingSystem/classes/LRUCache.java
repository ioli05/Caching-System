package cachingSystem.classes;

import java.util.HashMap;

import dataStructures.classes.Pair;

/**
 * This cache is very similar to the FIFOCache, but guarantees O(1) complexity for the get, put and
 * remove operations.
 */
public class LRUCache<K, V> extends ObservableCache<K, V> {


    class Node {

        protected Pair<K, V> pair;


        protected Node next, prev;

        Node(final K key, final V value, final Node next,
                final Node prev) {
	    pair = new Pair<K, V>(key, value);
            this.next = next;
            this.prev = prev;

        }

        public V getValue() {

           return pair.getValue();

        }
        public void updateValue(final V value) {
            pair.setValue(value);

        }

    }

    class Dequeue {

        private Node front, rear;
        private int size;

        public boolean isEmpty() {

            return front == null;
        }

        public void clear() {

            front = null;
            rear = null;
            size = 0;

        }

        public void insertAtRear(Node node) {

           
            size++;

            if (rear == null) {
                rear = node;
                front = rear;
                return;
            }

            if (rear != null) {
                rear.next = node;
                node.prev = rear;
                rear = node;
                return;
            }
        }

        public Node deleteNode(Node node){

            if (isEmpty()) {
                return null;
            }

            //1. daca elementul este singurul din coada

            if (node.prev == null && node.next == null) {
                front = null;
                rear = null;
                size--;
                return node;

            }

            //2. daca elementul este la inceput;
            if (node.prev == null && node.next != null) {
                node.next.prev = null;
                front = node.next;
                node.next = null;
                size--;
                return node;

            }

            //3. daca elementul este la coada;
            if (node.prev != null && node.next == null) {
                node.prev.next = null;
                rear = node.prev;
                node.prev = null;
                size--;
                return node;
            }

            //4. daca elementul este in mijloc;

            if (node.prev != null && node.next != null) {

                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.next = null;
                node.prev = null;
                size--;
                return node;

            }
            return null;
        }


    }

    private HashMap<K, Node> map = new HashMap<K, Node>();
    private Dequeue dequeue = new Dequeue();
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#get(java.lang.Object)
     */
    @Override
    public V get(final K key) {

       Node node = map.get(key);
       if (node == null) {
           cacheListener.onMiss(key);
	   return null;
       } else {
	   dequeue.deleteNode(node);
	   dequeue.insertAtRear(node);
           cacheListener.onHit(key);
	   V elem = map.get(key).getValue();
	   return elem;
       }

    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(final K key, final V value) {

        cacheListener.onPut(key, value);
        Node node = map.get(key);
         if (node != null) {
             node.updateValue(value);
             dequeue.deleteNode(node);
             dequeue.insertAtRear(node);
         } else {
             node = new Node(key, value, null, null);
             dequeue.insertAtRear(node);
             map.put(key, node);
        }

        clearStaleEntries();



    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#size()
     */
    @Override
    public int size() {
        return dequeue.size;
    }

    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return dequeue.isEmpty();
    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#remove(java.lang.Object)
     */
    @Override
    public V remove(final K key) {

        Node node = map.get(key);
        V elem = null;
        if (node != null) {
            dequeue.deleteNode(node);
            elem = map.remove(key).getValue();
        }
        return elem;

    }
    /*
     * (non-Javadoc)
     * @see cachingSystem.interfaces.Cache#clearAll()
     */
    @Override
    public void clearAll() {
        dequeue.clear();
        map.clear();
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

        return dequeue.front.pair;

    }
}


