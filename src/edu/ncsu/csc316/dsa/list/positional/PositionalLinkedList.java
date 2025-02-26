package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * source is: Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    /**
     * Method to create a new instance of the iterator as an element iterator
     * @return a new element iterator starting at front
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * Method to add a element after a given position using addBetween
     * @param p is the position to add after
     * @param element is the element to add
     * @return the new position
     */
	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node.getNext(), node);
	}

	/**
     * Method to add a element before a given position using addBetween
     * @param p is the position to add before
     * @param element is the element to add
     * @return the new position
     */
	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getPrevious());
	}

	/**
     * Method to add a element at the beginning of the list using addBetween
     * @param element is the element to add
     * @return the new position
     */
	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	/**
     * Method to add a element at the end of the list using addBetween
     * @param element is the element to add
     * @return the new position
     */
	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	/**
	 * Method that accesses the node after a position
	 * @param p is the position to check after
	 * @return the node after, or null if there is no node after
	 */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(node.getNext().getElement() == null) {
			return null;
		}
		return node.getNext();
	}

	/**
	 * Method to access the node before a position
	 * @param p is the position to check before
	 * @return the node before, or null if there is no node
	 */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(node.getPrevious().getElement() == null) {
			return null;
		}
		return node.getPrevious();
	}

	/**
	 * Method to access the first node
	 * @return a node that is the first one in the list, or null if it's empty
	 */
	@Override
	public Position<E> first() {
		if(size == 0) {
			return null;
		}
		return front.getNext();
	}

	/**
	 * Method to check if the list has nodes in it or not
	 * @return true if there are no nodes, or false if there are
	 */
	@Override
	public boolean isEmpty() {
		if(this.size == 0) {
			return true;
		}
		return false;
	}
	/**
	 * Method to access the last node in the list
	 * @return tail, the last node in the list or null if there is none
	 */
	@Override
	public Position<E> last() {
		if(size == 0) {
			return null;
		}
		return tail.getPrevious();
	}

	/**
	 * method to create the iterator as an iterable objext
	 * @return the position iterable iterator
	 */
	@Override
	public Iterable<Position<E>> positions() {
		PositionIterable iterator = new PositionIterable();
		return iterator;
	}

	/**
	 * Method to remove a position
	 * @param p is the position to remove
	 * @return E the element that it is now
	 */
	@Override
	public E remove(Position<E> p) {
		E previous = p.getElement();
		PositionalNode<E> node = validate(p);
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		this.size--;
		return previous;
	}

	/**
	 * Method should set a position to a different element
	 * @param p is the position to change
	 * @param element is the new element it should be
	 * @return E the previous element
	 */
	@Override
	public E set(Position<E> p, E element) {
		E prev = p.getElement();
		PositionalNode<E> node = validate(p);
		node.setElement(element);
		return prev;
	}

	/**
	 * Getter method to access the number of elements in the list
	 * @return size, the size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		Position<E> node = new PositionalNode<E>(element, next, prev);
		if(size == 0) {
			front.setNext(validate(node));
			tail.setPrevious(validate(node));
		}
		else {
			prev.setNext(validate(node));
			next.setPrevious(validate(node));
		}
		this.size++;
        return node;
    }
	
	/**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	/**
	 * Class to create a positional node
	 * @param <E> is the generic type of the node
	 */
	private static class PositionalNode<E> implements Position<E> {

		/**
		 * the element contained in the positional node
		 */
        private E element;
        /**
         * the next node connected to this position
         */
        private PositionalNode<E> next;
        /**
         * the previous node connected to this positional node
         */
        private PositionalNode<E> previous;

        /**
         * Constructor that sets the element to value
         * and the next and previous nodes to null
         * @param value is the value to set element to
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * constructor that calls the three arg constructor to set previous to null
         * sets element to value and next to next
         * @param value is the value to set element to
         * @param next is the node to set next to
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructor takes three parameters and sets element, next, and previous
         * @param value is the value to set element to
         * @param next is the node to set next to
         * @param prev is the node to set previous to
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * method to set the previous node
         * @param prev is the node to set previous to
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Method to access the precious node
         * @return previous, the node previous
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Method to set the next node
         * @param next is the node to set next to
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * method to access the next node
         * @return the next node
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
         * method to access the element of the position
         * @return element, the element of the node
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Method to set the element of the node
         * @param element is the value to set element to
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        
    }
	
	/**
	 * class for positional iterator to use in Element iterator to iterate
	 * through the positional list
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * the current node that the iterator is on
		 */
        private Position<E> current;
        /**
         * Checks if next was called before remove
         */
        private boolean removeOK;

        public PositionIterator() {
            current = front;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
        	PositionalNode<E> node = validate(current);
            return node.getNext().getElement() != null;
        }

        /**
         * Method sets current to the next element if there is one
         * throws NoSuchElementException if there is no next element
         * @return current after setting current to the next node
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public Position<E> next() {
        	PositionalNode<E> node = validate(current);
        	if(hasNext()) {
            	current = node.getNext();
            	removeOK = true;
            	return current;
            }
        	throw new NoSuchElementException();
        }

        /**
         * Method to remove current from the iterator
         * sets removeOK to false until next is called
         * @throws IllegalStateException if next was not previously called before remove
         */
        @Override
        public void remove() {
        	if(removeOK) {
        		PositionalLinkedList.this.remove(current);
        		removeOK = false;
        	}
        	else {
        		throw new IllegalStateException();
        	}
        	
            // You should consider delegating to
            // the outer class's remove(position) method,
            // similar to:
            // PositionalLinkedList.this.remove(position);
        }
    }
	
	/**
	 * inner class that uses positional iterator to iterate through the elements in the list
	 */
	private class ElementIterator implements Iterator<E> {

		/**
		 * is the positional iterator to use
		 */
        private Iterator<Position<E>> it;

        /**
         * constructor creates the iterator as a positional iterator
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /**
         * method checks if there is a next element
         * @return true or false if there is a next element
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * method uses positional iterator next to change the iterator
         * to the next element
         * @return E the element that it now is
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Method uses positional iterator to remove the current element
         * @throws IllegalStateException if next wasn't previously called
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
	/**
	 * wrapper class to allow the iterator to be an iterable object
	 */
	private class PositionIterable implements Iterable<Position<E>> {
        /**
         * Method creates the position iterator as an iterable object
         */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
}