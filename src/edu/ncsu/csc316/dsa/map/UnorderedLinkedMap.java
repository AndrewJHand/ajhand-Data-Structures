package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/**
	 * the list of entries
	 */
    private PositionalList<Entry<K, V>> list;
    /**
     * constructor for the unordered linked map that creates a positional list
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key)
    {
    	if(list == null || list.size() == 0) {
    		return null;
    	}
        Position<Entry<K, V>> pos = list.first();
        while(pos != null) {
        	if(pos.getElement().getKey().equals(key)) {
        		return pos;
            }
        	pos = list.after(pos);
        }
        return null;
    }

    @Override
    public V get(K key) {
    	if(list.size() == 0) {
    		return null;
    	}
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null && p.getElement() != null && p.getElement().getValue() != null) {
        	moveToFront(p);
        	return p.getElement().getValue();
        }
        else {
        	return null;
        }
    }
    
    /**
     * Method that moves a given position to the front of the list
     * @param position is the position to move to the front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        list.remove(position);
        list.addFirst(position.getElement());
    }

    /**
     * Put method uses moveToFront to update existing key
     * or create a new map entry for the list
     * @param key is the key to lookup/create/change
     * @param value is the value that should be created/updated
     * @return V the value of the original key if it exists
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
     
        if(p != null && p.getElement() != null) {
        	V val = p.getElement().getValue();
        	p.getElement().setValue(value);
        	moveToFront(p);
        	return val;
        }
        else {
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return null;
        } 
    }
    
    /**
     * method to remove a position with given key
     * @param key is the key to remove
     * @return the value that was removed, or null if it didn't exist
     */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       
       if(p == null) {
    	   return null;
       }
       V value = p.getElement().getValue();
	   list.remove(p);
	   return value;
    }
    /**
     * getter for size
     * @return size of the list
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * Create an iterable over all entries
     * @return collection, the entry set
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    
    
}