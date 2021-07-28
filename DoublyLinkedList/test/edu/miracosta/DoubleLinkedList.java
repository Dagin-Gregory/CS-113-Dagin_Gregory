package edu.miracosta;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class DoubleLinkedList<E> implements List<E>
{
    // Local variables
    private Node head;
    private Node tail;
    private int size;

    /**
     * Default constructor sets head and tail to null while size gets set to 0.
     */
    public DoubleLinkedList()
    {
        this.setAll(null, null, 0);
    }


    private void setAll(Node head, Node tail, int size)
    {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new DoubleListIterator();
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new DoubleListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
    	if(index < this.size) {
    		return new DoubleListIterator(index);
    	}
    	return new DoubleListIterator(0);
    }

    @Override
    public boolean add(E e)
    {

        listIterator(this.size).add(e);
        return true;
    }

    @Override
    public void add(int index, E element)
    {
        listIterator(index).add(element);
    }

    @Override
    public void clear()
    {
        this.head = null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        else
        {
            DoubleLinkedList other = (DoubleLinkedList) o;
            return this.toString() == other.toString();
        }
    }

    @Override
    public boolean contains(Object o)
    {
        // special case list is empty
        if (this.size() == 0)
        {
            return false;
        }

        ListIterator iterator = this.listIterator();

        while (iterator.hasNext())
        {
            Object local = iterator.next();
            if (local.equals(o))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index)
    {
        checkOutOfBounds(index);
        return listIterator(index).next();
    }

    @Override
    public int indexOf(Object o)
    {
        int location;

        if (size != 0)
        {
            location = 0;

            ListIterator iterator = this.listIterator();

            while (iterator.hasNext())
            {
                Object local = iterator.next();
                if (local.equals(o))
                {
                    return location;
                }

                location++;
            }
        }

        return -1;
    }


    @Override
    public int lastIndexOf(Object o)
    {
        int location;

        if (size != 0)
        {
            location = this.size() - 1;

            ListIterator iterator = this.listIterator(this.size());

            while (iterator.hasPrevious())
            {
                Object local = iterator.previous();
                if (local.equals(o))
                {
                    return location;
                }
                location--;
            }
        }

        return -1;

    }

    @Override
    public boolean isEmpty()
    {
        return (head == null);
    }

    @Override
    public E remove(int index)
    {
        E elementToReturn;

        checkOutOfBounds(index);
        ListIterator<E> iterator = listIterator(index);
        elementToReturn = iterator.next();
        iterator.remove();

        return (E) elementToReturn; 
    }

    @Override
    public boolean remove(Object other)
    {
        // local variables
        Object local;
        ListIterator iterator;

        if (this.size() == 0)
        {
            return false;
        }
        
        else
        {
            iterator = listIterator();

            while (iterator.hasNext())
            {
                local = iterator.next();

                if (local.equals(other))
                {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public E set(int index, E element)
    {
        // local variables
        E elementToReturn;
        ListIterator iterator;

        // checking out of bounds
        checkOutOfBounds(index);

        // set iterator at desired index
        iterator = listIterator(index);

        // store element to return before replacing it
        elementToReturn = (E) iterator.next();

        // replace the element at index given
        iterator.set(element);

        // return the element we replaced
        return elementToReturn;
    }

    @Override
    public int size()
    {
        return this.size;
    }


    @Override
    public String toString()
    {
        // local variables
        DoubleListIterator iterator;
        String listContent;

        // initialize
        iterator = new DoubleListIterator();
        listContent = "[";

        // add list contents to string
        while (iterator.hasNext())
        {
            listContent += iterator.next().toString();
            if (iterator.hasNext())
            {
                listContent += ", ";
            }
        }

        return listContent + "]";
    }

 
    public String indexedToString()
    {
        String listContent, index;
        Node currentPosition;

        listContent = "Size: " + this.size + "\n";

        currentPosition = this.head;

        for (int i = 0; i < this.size; i++)
        {

            index = String.format("[%d]", i);
            listContent += String.format("%s %s", index, currentPosition.data);

            // Add comma
            if (i != this.size - 1)
            {
                listContent += ", ";
            }
            currentPosition = currentPosition.next;
        }

        return listContent;
    }



    private void checkOutOfBounds(int index)
    {
        if (index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }



    @Override
    public Object[] toArray()
    {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return null;
    }

    private class DoubleListIterator implements ListIterator<E>
    {
        // Local variables
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index;


        public DoubleListIterator()
        {
            this.nextItem = head;
            this.lastItemReturned = null;
            index = 0;
        }

        public DoubleListIterator(int i)
        {
            // out of bounds check
            if (i < 0 || i > size)
            {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }

            lastItemReturned = null;  // No item returned yet.

            // special case, last item
            if (i == size)
            {
                index = size;
                nextItem = null;
            }
            else // start at beginning
            {
                nextItem = head;
                for (index = 0; index < i; index++)
                {
                    nextItem = nextItem.next;
                }
            }
        }

        @Override
        public void add(E e)
        {
            Node<E> newNode;


            // if list is empty
            if (head == null)
            {
                head = new Node<E>(e);
                tail = head;
            }
            // if the next item is the head
            else if (nextItem == head)
            {
                newNode = new Node<E>(e);
                newNode.next = nextItem;    // link new node with next Item
                nextItem.previous = newNode;  // link nextItems's previous reference with new node
                head = newNode;     // assign new node as head
            }
            else if (nextItem == null)
            {
                newNode = new Node<E>(e);
                tail.next = newNode;
                newNode.previous = tail;
                tail = newNode;
            }
            else
            {
                newNode = new Node<E>(e);
                newNode.previous = nextItem.previous;
                nextItem.previous.next = newNode;
                newNode.next = nextItem;
                nextItem.previous = newNode;
            }

            size++;
            index++;
            lastItemReturned = null;
        }

        @Override
        public boolean hasNext()
        {
            // if nextItem == null returns false
            // else nextItem != null returns true
            return nextItem != null;
        }

        @Override
        public boolean hasPrevious()
        {
            // return true if nextItem is null and list is not empty
            // or nextItem's previous is not null
            return (nextItem == null && size != 0)
                    || nextItem.previous != null;
        }

        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        @Override
        public int nextIndex()
        {
            return index;
        }

        @Override
        public E previous()
        {
            if (!hasPrevious())
            {
                throw new NoSuchElementException();
            }
            if (nextItem == null)
            {
                nextItem = tail;
            }
            else
            {
                nextItem = nextItem.previous;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }


        @Override
        public int previousIndex()
        {
            return index - 1;
        }

        @Override
        public void remove()
        {
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
                else if (lastItemReturned.previous == null)
                {
                    head = nextItem;
                    lastItemReturned.next.previous = null;
                }
                // last node in list
                else if (lastItemReturned.next == null)
                {
                    lastItemReturned.previous.next = null;
                }
                else // everything else in between
                {
                    lastItemReturned.previous.next = lastItemReturned.next;
                    lastItemReturned.next.previous = lastItemReturned.previous;
                }
                size--;
                lastItemReturned = null;
            }
        }

        @Override
        public void set(E e)
        {
            if (lastItemReturned == null)
            {
                throw new IllegalStateException();
            }
            else
            {
                lastItemReturned.data = e;
                lastItemReturned = null;
            }
        }
    }


    private static class Node<E>
    {
        // class variables
        private E data;
        private Node<E> next;
        private Node<E> previous;


        public Node()
        {
            this.setAll(null, null, null);
        }


        public Node(E dataItem)
        {
            this.setAll(dataItem, null, null);
        }


        public Node(E newData, Node<E> nextNode, Node<E> previousNode)
        {
            this.setAll(newData, nextNode, previousNode);
        }


        public void setAll(E dataItem, Node nextNode, Node previousNode)
        {
            this.data = dataItem;
            this.next = nextNode;
            this.previous = previousNode;
        }
    }
}