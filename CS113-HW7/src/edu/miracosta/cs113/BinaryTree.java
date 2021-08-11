package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

/**
 * BinaryTree.java - My implementation of a binary tree class. BinaryTree has an inner class, Node, that represents
 * the individual nodes of the binary tree.
 *
 * Instance Variables:
 *      -root (Node<E>): The base of the binary tree.
 *
 * Methods:
 *      -Constructors:
 *          -No-argument constructor: Sets the root equal to null.
 *          -Partial constructor: Takes a Node argument and sets the root of the new BinaryTree object equal to the
 *           node argument.
 *          -Full constructor: Takes a Node argument, and two binary tree arguments. Sets the root of the new
 *           BinaryTree object equal to the Node argument, and sets root.left equal to the first BinaryTree argumnet,
 *           and root.right equal to the second BinaryTree argument.
 *      - getLeftSubtree: Returns the subtree starting from .left of the calling node.
 *      - getRightSubtree: Returns the subtree starting from the .right of the calling node.
 *      - getData: Returns the data stored in the calling node.
 *      - isLeaf: Returns true if the calling node is a leaf node (has no sub-nodes).
 *      - toString: Returns a String representation of the BinaryTree.
 *      - preOrderTraverse (Node<E>, int, StringBuilder): Traverses the node in pre-order traversal (starting from the
 *        root down the left sub-tree, then down the right subtree) and returns a String representation of said traversal.
 *      -readBinaryTree (Scanner): Returns a copy of the calling BinaryTree.
 */
class BinaryTree < E > implements Serializable {

    protected static class Node < E > implements Serializable {

        protected E data;
        protected Node<E> left, right;

        Node ( E data ) {
            this.data = data;
        }

        @Override
        public String toString ( ) {
            return data.toString () ;
        }
    }

    protected Node< E > root;

    /**
     * Constructs an empty binary tree.
     */
    BinaryTree ( ) {
        root = null;
    }

    /**
     * Constructs a binary tree with the given node as the root.
     *
     * @param root The root for the new binary tree.
     */
    protected BinaryTree ( Node < E > root ) {
        this.root = root;
    }

    /**
     * Constructs a binary tree with the given data at the root and the two given subtrees.
     *
     * @param data The data the root will hold.
     * @param leftTree The left node the root will reference.
     * @param rightTree The right node the root will reference.
     */
    BinaryTree ( E data, BinaryTree < E > leftTree, BinaryTree < E > rightTree ) {
        root = new Node < > ( data );

        root.left = ( leftTree == null ) ? null : leftTree.root;
        root.right = ( rightTree == null ) ? null : rightTree.root;
    }

    /**
     * Returns the left subtree.
     *
     * @return The left subtree.
     */
    BinaryTree < E > getLeftSubtree ( ) {
        return ( root != null && root.left != null ) ? new BinaryTree < > ( root.left ) : null;
    }

    /**
     * Returns the right subtree.
     *
     * @return The right subtree.
     */
    BinaryTree < E > getRightSubtree ( ) {
        return ( root != null && root.right != null ) ? new BinaryTree < > ( root.right ) : null;
    }

    /**
     * Returns the data in the root.
     *
     * @return The data within the root.
     */
    E getData ( ) {
        return root.data;
    }

    /**
     * Determines if the calling node is a left.
     *
     * @return Returns true if the calling node is a lead, false if not.
     */
    boolean isLeaf ( ) {
        return ( root.left == null && root.right == null );
    }

    @Override
    public String toString ( ) {
        StringBuilder sb = new StringBuilder ( );
        preOrderTraverse ( root, 1, sb );
        return sb.toString ( );
    }

    /**
     * Performs a pre-order traversal of the subtree whose root is node.
     *
     * @param node The starting node.
     * @param depth The level of the tree.
     * @param sb The StringBuilder object that appends the nodes.
     */
    
    private String repeat(String strToRepeat, int timesToRepeat) {
    	
    	if(timesToRepeat == 0) {
    		
    		return("");
    		
    	}
    	
    	if(timesToRepeat < 0) {
    	
    		return("");
    		
    	}
    	
    	return(strToRepeat + repeat(strToRepeat, timesToRepeat - 1));
    	
    }
    
    private void preOrderTraverse ( Node < E > node, int depth, StringBuilder sb ) {
    	
        sb.append ( repeat ( " ", Math.max ( 0, depth - 1 ) ) );

        if ( node == null )
            sb.append ( "null\n" );
        else {
            sb.append ( node.toString ( ) + "\n" );
            preOrderTraverse ( node.left, depth + 1, sb );
            preOrderTraverse ( node.right, depth + 1, sb );
        }
    }

    /**
     * Constructs a binary tree by reading its data using Scanner scan.
     *
     * @param scan The Scanner object.
     * @return The appended binary tree.
     */
    static BinaryTree < String > readBinaryTree ( Scanner scan ) {
        String data = scan.next ( );

        if ( data.equals ( "null" ) )
            return null;
        else {
            BinaryTree < String > leftTree = readBinaryTree ( scan );
            BinaryTree < String > rightTree = readBinaryTree ( scan );
            return new BinaryTree < > ( data, leftTree, rightTree );
        }
    }
}
