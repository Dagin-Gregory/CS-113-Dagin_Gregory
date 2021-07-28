package edu.miracosta.cs113;

public class ListStack<E> implements Stack<E> // >:^)
{
	private static class Node<E>
	{
		private E data;
		private Node<E> next;
		
		private Node(E newStudent)
		{
			this.data = newStudent;
			this.next = null;
		}
		private Node(E newStudent, Node<E> nodeRef) 
		{
			this.data = newStudent;
			this.next = nodeRef;
		}
	} //end of Node inner class
	
	private Node<E> head;
	private int size;
	
	public ListStack()
	{
		head = null;
		size = 0;
	}
	
	public boolean empty()
	{
		return head != null && size != 0;
	}
	public E peek()
	{
		return head.data;
	}
	public E pop()
	{
		return removeFirst();
	}
	public E push(E object)
	{
		addFirst(object);
		return object;
	}
	public void addFirst(E item) 
	{
		head = new Node<E>(item, head);
		size++;
	}
	public void addLast(E item)
	{
		add(size, item);
	}
	public E removeFirst() 
	{
		if (head == null && size == 0)
		{
			return null;
		} 
		else 
		{
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.data;
		}
	}
	public void remove(E search)
	{
		Node<E> position = head;
		Node<E> pastPosition = head;
		boolean match = false;
		while(position !=  null && !match)
		{
			E current = (E)position.data;
	
			if(current.equals(search))
			{
				match = true;
			}
			else
			{
				pastPosition = position;
			}
			position = position.next;
		}
		removeAfter(pastPosition);
	}
	
	public String toString()
	{
		Node<E> nodeRef = head;
		StringBuilder result = new StringBuilder();
		while(nodeRef != null)
		{
			result.append(nodeRef.data);
			if(nodeRef.next != null)
			{
				result.append(" ==> ");
			}
			nodeRef = nodeRef.next;
		}
		return result.toString();
	}
	public int getSize()
	{
		return this.size;
	}
	private void add(int index, E item) 
	{
		if (index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0)
		{
			addFirst(item);
		} 
		else 
		{
			Node<E> node = getNode(index-1);
			addAfter(node, item);
		}
	}
	
	private void addAfter(Node<E> node, E item) 
	{
		Node<E> temp = new Node<E>(item, node.next);
		node.next = temp;
		size++;
	}
	
	private E removeAfter(Node<E> node)
	{
		Node<E> temp = node.next;
		if(temp != null)
		{
			node.next = temp.next;
			size--;
			return temp.data;
		}
		else
		{
			return null;
		}
	}
	
	private Node<E> getNode(int index)
	{
		Node<E> node = head;
		int i = 0;
		while(i < index && node != null)
		{
			node = node.next;
			i++;
		}
		return node;
	} 
}