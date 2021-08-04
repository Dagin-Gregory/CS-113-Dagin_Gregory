package edu.miracosta.cs113;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    private Node<E> head = null;   // points to the head of the list
    private Node<E> tail = null;   //points to the tail of the list
    private int size = 0;    // the number of items in the list

    //default constructor
    public DoubleLinkedList() {
        this.head = this.tail = null;
        size = 0;
    }

    public void add(int index, E obj) {
        ListIterator<E> iter = listIterator(index);
        iter.add(obj);
    }

    public void addFirst(E obj) { // Fill Here

    }
    public void addLast(E obj) {

    }

    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        ListIterator<E> iter = listIterator(index);
        return iter.next();
    }
    public E getFirst() {
        return head.data;
    }
    public E getLast() {
        return tail.data;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    public E remove(int index) {
        E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext()) {
            returnValue = iter.next();
            iter.remove();
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        return returnValue;
    }

    public Iterator iterator() {
        return new ListIter(0);
    }
    public ListIterator listIterator() {
        return new ListIter(0);
    }
    public ListIterator listIterator(int index){
        return new ListIter(index);
    }
    public ListIterator listIterator(ListIterator iter) {
        return new ListIter( (ListIter) iter);
    }

    // Inner Classes
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E dataItem) {
            this.data = dataItem;
            this.next = prev = null;
        }

    }

    public class ListIter implements ListIterator<E> {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   //

        public ListIter() {
            this.nextItem = head;
            this.lastItemReturned = null;
            index = 0;
        }

        // constructor for ListIter class
        public ListIter(int i) {
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null;

            // Special case of last item
            if (i == size)  {
                index = size;
                nextItem = null;
            }
            // start at the beginning
            else {
                nextItem = head;
                for (index = 0; index < i; index++)  nextItem = nextItem.next;
            }// end else
        }  // end constructor

        public ListIter(ListIter other) {
            nextItem = other.nextItem;
            index = other.index;
        }

        public boolean hasNext() {
            return nextItem != null;
        }
        
        public boolean hasPrevious() {
        	
        	if(size > 0) {
        		
	        	if((nextItem == null) || nextItem.prev != null && size > 1) {
	
	        		return true;
	        		
	        	}
	        	
        	}
        	
            return false;
            
        }
        
        public int previousIndex() {
            return index-1;
        }
        public int nextIndex() {
            return index;
        }
        public void set(E o)  {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }
            else {
                lastItemReturned.data = o;
                lastItemReturned = null;
            }
        }
        public void remove(){
            // if list is empty or no item was returned
            if (isEmpty() || lastItemReturned == null)
            {
                throw new IllegalStateException();
            }
            else
            {
                // only one node in list
                if (size() == 1)
                {
                    head = null;
                }

                // first node in list
                else if (lastItemReturned.prev == null)
                {
                    head = nextItem;
                    lastItemReturned.next.prev = null;
                }
                // last node in list
                else if (lastItemReturned.next == null)
                {
                    lastItemReturned.prev.next = null;
                }
                else // everything else in between
                {
                    lastItemReturned.prev.next = lastItemReturned.next;
                    lastItemReturned.next.prev = lastItemReturned.prev;
                }
                size--;
                lastItemReturned = null;
            }
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) {
                nextItem = tail;
            }
            else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        public void add(E obj) {
            Node<E> newNode;

            // if list is empty
            if (head == null)
            {
                head = new Node<E>(obj);
                tail = head;
            }
            // if the next item is the head
            else if (nextItem == head)
            {
                newNode = new Node<E>(obj);
                newNode.next = nextItem;    // link new node with next Item
                nextItem.prev = newNode;  // link nextItems's previous reference with new node
                head = newNode;     // assign new node as head
            }
            else if (nextItem == null)
            {
                newNode = new Node<E>(obj);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            else
            {
                newNode = new Node<E>(obj);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }

            size++;
            index++;
            lastItemReturned = null;
        }
    }// end of inner class ListIter


    @Override
    public String toString() {
        // local variables
        ListIter iterator;
        String output;

        iterator = new ListIter();
        output = "[";

        // adds list to string
        while (iterator.hasNext()) {
            output += iterator.next().toString();
            if (iterator.hasNext()) {
                output += ", ";
            }
        }

        return output + "]";
    }
}// end of class DoubleLinkedList