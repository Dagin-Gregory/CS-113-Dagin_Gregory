package edu.miracosta.cs113;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * ArrayListStack.java - My implementation of a Stack, using an ArrayList. Note: Some methods are implemented from the
 * book.
 *
 * Instance Variables:
 *
 *      - INITIAL_CAPACITY (int): The initial capacity of the array.
 *      - theData (E[]): The underlying array that will hold all elements.
 *      - topOfStack (int): The index of the last element in the stack (-1 if empty).
 *      - capacity (int): The number of elements the Stack can hold.
 *
 * Methods:
 *
 *      - reallocate: Creates a new array with twice the capacity of the original array and copies information from the old array into
 *        a new one.
 *      - Overridden methods will use the same documentation from the super interface.
 *
 */
class ArrayListStack < E > implements StackInterface < E > {

    private static final int INITIAL_CAPACITY = 10;
    private E [ ] theData;
    private int topOfStack = -1, capacity;

    ArrayListStack ( ) {
        capacity = INITIAL_CAPACITY;
        theData = ( E[ ] )  new Object [ INITIAL_CAPACITY ];
    }

    @Override
    public boolean empty ( ) {
        return ( topOfStack < 0 );
    }

    @Override
    public E peek ( ) throws EmptyStackException {
        if ( empty ( ) )
            throw new EmptyStackException ( );

        return theData [ topOfStack ];
    }

    @Override // Method implemented with syntax from book.
    public E pop ( ) throws EmptyStackException {
        if ( empty ( ) )
            throw new EmptyStackException ( );

        return theData [ topOfStack -- ];
    }

    @Override // Method implemented with syntax from book.
    public E push ( E element ) {
        if ( topOfStack + 1 >= capacity )
            reallocate ( );

        try{
	        topOfStack++;
	        theData [ topOfStack ] = element;
	        
        }
        		
        catch(ArrayIndexOutOfBoundsException e) {
        	
        	System.out.println(theData.length - 1);
        	
        }
        return element;
    }

    /**
     * Creates a new array with twice the capacity of the original array and copies information from the old array into
     * a new one. Method implemented with syntax from the book.
     */
    private void reallocate ( ) {
    	capacity *= 2; 
    	
        theData = Arrays.copyOf ( theData, ( capacity ) );
    }
}
