package pnpatel.hw3;

import edu.princeton.cs.algs4.Queue;

/**
 * MINIMAL BST that just stores integers as the key and values are eliminated.
 * Note that duplicate values can exist (i.e., this is not a symbol table).
 * 
 * COPY this file into your USERID.hw3 package and complete the final four methods 
 * in this class.
 */
public class BST {
	// root of the tree
	Node root;               
	
	// Use Node class as is without any change.
	class Node {
		int    key;          // SIMPLIFIED to just use int
		Node   left, right;  // left and right subtrees

		public Node(int key) {
			this.key = key;
		}
		
		public String toString() { return "[" + key + "]"; }
	}

	/** Check if BST is empty. */
	public boolean isEmpty() { return root == null; }

	/** Determine if key is contained. */ 
	public boolean contains(int key) { 
		return contains(root, key);
	}
	
	/** Recursive helper method for contains. */
	boolean contains(Node parent, int key) {
		if (parent == null) return false;
		
		if (key < parent.key) {
			return contains(parent.left, key);
		} else if (key > parent.key) {
			return contains(parent.right, key);
		} else {
			return true; // found it!
		}
	}
	
	/** Invoke add on parent, should it exist. */
	public void add(int key) { 
		root = add(root, key);
	}

	/** Recursive helper method for add. */
	Node add(Node parent, int key) {
		if (parent == null) {
			return new Node(key);
		}
		
		if (key <= parent.key) {
			parent.left = add(parent.left, key);
		} else if (key > parent.key) {
			parent.right = add(parent.right, key); 
		} 
		
		return parent;
	}
	
	// AFTER THIS POINT YOU CAN ADD CODE....
	// ----------------------------------------------------------------------------------------------------

	/** Return a new BST that is a structural copy of this current BST. */
	public BST copy() {
		BST copy = new BST();
		
		copyhelp(copy, this.root);
		return copy;
		//throw new RuntimeException("Jerry Maguire...");
	}
	
	public void copyhelp(BST copy, Node parent) {
		
		if(parent == null) {
			return;
		}
		
		copy.add(parent.key);
		copyhelp(copy, parent.left);
		copyhelp(copy, parent.right);
		
		
	}
	
	
	/** Return the count of nodes in the BST whose key is even. */
		public int countIfEven() {
			if(this.root == null) {
				return 0;
			}
			int counter = 0;
			return count(counter, this.root);
		// do some work here, but you also need a helper method....
		//throw new RuntimeException("Jerry Maguire...");
	}
		
		public int count(int count, Node n)
		{
			
			if (n == null) {
				return count;
			}
			
			count = count(count, n.left);
			if(n.key % 2 == 0) {
				count++;
			}
			count = count(count, n.right);
			
			return count;
		}
	
	/** Return a Queue<Integer> containing the depths for all nodes in the BST. */
	public Queue<Integer> nodeDepths() {
		Queue<Integer> q = new Queue<>();
		if(this.root == null) {
			return q;
		}
		int count = 0;
		depths(this.root, q, count);
		// do something here, but you also need a helper method....
		return q;
	}
	
	public void depths(Node parent, Queue<Integer> depths, int counter) {
		
		if(parent == null) {
			return;
		}
		
		depths.enqueue(counter);
		counter++;
		depths(parent.left, depths, counter);
		depths(parent.right, depths, counter);
	}
	
	/** Remove all leaf nodes that are odd. */
	public void removeLeafIfOdd() {
		if(this.root == null) {
			return;
		}
		this.root = removeLeafOdd(this.root);
		// do some work here, but you also need a helper method....
	}
	
	
	public Node removeLeafOdd(Node parent) {
		
		if(parent.left == null && parent.right == null && !(parent.key % 2 == 0)) {
			return null;
		}
		else if(parent.left == null && parent.right == null && (parent.key % 2 == 0)) {
			return parent;
		}
		
		
		if(!(parent.left == null)) {
		parent.left = removeLeafOdd(parent.left);
		}
		if(!(parent.right == null)) {
		parent.right = removeLeafOdd(parent.right);
		}
		return parent;
	}
}
