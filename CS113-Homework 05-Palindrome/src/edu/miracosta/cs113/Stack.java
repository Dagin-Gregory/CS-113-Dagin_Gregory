package edu.miracosta.cs113;

public interface Stack<E>
{
	public boolean empty();
	public E peek();
	public E pop();
	public E push(E object);
}
