package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * 
 * Used Data Structures and Algorithms in Java page 407
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/**
    	 * The key in the map
    	 */
        private K key;
        /**
         * the value of the key in the map
         */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {

    	/**
    	 * the list to iterate
    	 */
        private List<Entry<K, V>> list;

        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

        	/**
        	 * the list to iterate
        	 */
            private Iterator<Entry<K, V>> it;

            public EntryCollectionIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }      

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class KeyIterator implements Iterator<K> {

    	/**
    	 * The iterator of to iterate by keys
    	 */
    	private Iterator<Entry<K, V>> entries;
    	
    	public KeyIterator(){
    		entries = entrySet().iterator();
    	}
		/**
		 * Method to check if there is a next element in the map
		 * @return true or false if there is a next key or not
		 */
    	@Override
		public boolean hasNext() {
			return entries.hasNext( );
		}

    	/**
    	 * go to the next key
    	 * @return the next key
    	 */
		@Override
		public K next() {
			return entries.next( ).getKey( );
		}
        /**
         * remove method isn't supported by this iterator
         * @throws UnsupportedOperationException if it is called
         */
		@Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class ValueIterator implements Iterator<V> {

    	/**
    	 * Iterator to iterate by values
    	 */
    	private Iterator<Entry<K, V>> entries;
    	
    	 
    	public ValueIterator(){
    		entries = entrySet().iterator();
    	}
    	/**
		 * Method to check if there is a next element in the map
		 * @return true or false if there is a next key or not
		 */
		@Override
		public boolean hasNext() {
			 return entries.hasNext( );
		}

		/**
    	 * go to the next value
    	 * @return the next value
    	 */
		@Override
		public V next() {
			return entries.next( ).getValue( );
		}
        
		/**
		 * shouldn't be able to remove
		 * @throws UnsupportedOperationException because you can't remove
		 */
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
    

    private class ValueIterable implements Iterable<V> {

    	/**
    	 * Make new value iterator
    	 */
		@Override
		public Iterator<V> iterator() {
			 return new ValueIterator( );
		}
		
    }

}