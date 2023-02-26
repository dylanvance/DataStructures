/*
 * Binary Search Tree Data Structure Implementation
 * Authored by Dylan Vance
 * Some code provided by Professor Kahanda
 */

package BST;

import java.util.*;		//for Stack class
	
class Node {
	public int iData;              //key 
	public Node leftChild;         // this node's left child
	public Node rightChild;        // this node's right child
	
	public void display() {            // display ourself
		System.out.print("{");
		System.out.print(iData);
		System.out.print("} ");
	}
		
}	//end class Node

class Tree {
	private Node root;             // first node of tree
		
	public Tree() { root = null; } // constructor. no nodes in tree yet
		
	public Node search(int key) {
		Node current = this.root;
		
		while (current.iData != key) {
			if (key < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
		   
			if (current == null)
				return null;
		}
		
		return current;
	}
		
	public void insert(int id) {
			
		Node insertNode = new Node();
		insertNode.iData = id;
			
		if (this.root == null) {
			this.root = insertNode;
		}
		else {
			Node currentNode = this.root;
			while (currentNode != null) {
					
				if (insertNode.iData < currentNode.iData) {
					if (currentNode.leftChild == null) {
						currentNode.leftChild = insertNode;
						currentNode = null;
					}
					else {
						currentNode = currentNode.leftChild;
					}
				}
				else {
					if (currentNode.rightChild == null) {
						currentNode.rightChild = insertNode;
						currentNode = null;
					}
					else {
						currentNode = currentNode.rightChild;
					}
				}		
			}		
		}	
	}  // end insert()
		
	public boolean remove(int key) { 	// delete node with given key (assumes non-empty list)
			
		Node parent = null;
		Node current = this.root;
			
		while (current != null) {
				
			if (current.iData == key) { 		//Node Found
					
				if (current.leftChild == null && current.rightChild == null) { // Remove leaf
            		if (parent == null) { this.root = null; } // Node is root
            			
            		else if (parent.leftChild == current) { parent.leftChild = null; }
            			
            		else { parent.rightChild = null; }
         		}
         		else if (current.rightChild == null) {                // Remove node with only left child
            		if (parent == null) { this.root = current.leftChild; } // Node is root
            			
            		else if (parent.leftChild == current) { parent.leftChild = current.leftChild; }
            			
            		else { parent.rightChild = current.leftChild; }
         		}
         		else if (current.leftChild == null) {                // Remove node with only right child
            		if (parent == null) { this.root = current.rightChild; }// Node is root
            			
            		else if (parent.leftChild == current) { parent.leftChild = current.rightChild; }
               			
            		else { parent.rightChild = current.rightChild; }
               	}
               	else {                                      		// Remove node with two children
            		// Find successor (leftmost child of right subtree)
            		Node successor = current.rightChild;
            		while (successor.leftChild != null) {
               			successor = successor.leftChild;
            		}
            		//int successorData = successor.iData;
            		remove(successor.iData);     // Remove successor
            		current.iData = successor.iData;
         		}
         		return true; // Node found and removed
					
			}
			else if (current.iData < key) {		//Move to Right Child
				parent = current;
				current = current.rightChild;
			}
			else {								//Move to Left Child
				parent = current;
				current = current.leftChild;
			}
		}
			
		return false;                                // success
	}  // end delete()
		
	
		
	public void traverse(int traverseType) {
		switch(traverseType) {
			case 1: System.out.print("Preorder traversal: ");
				preOrder(root);break;
			case 2: System.out.print("Inorder traversal:  ");
				inOrder(root); break;
			case 3: System.out.print("Postorder traversal: ");
				postOrder(root); break;
		}
		System.out.println(); 
	}
		
	private void preOrder(Node localRoot) {
		if(localRoot != null) {
			System.out.print(localRoot.iData + " "); 
			preOrder(localRoot.leftChild); 
			preOrder(localRoot.rightChild);
		}
	}
		
	private void inOrder(Node localRoot) {
		if(localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + "  "); 
			inOrder(localRoot.rightChild);
		}
	}
		
	private void postOrder(Node localRoot) {
		if(localRoot != null) {
			postOrder(localRoot.leftChild); 
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " "); 
		}
	}
		
	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>(); 
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false; System.out.println("......................................................");
			while(isRowEmpty==false) {
				Stack<Node> localStack = new Stack<Node>(); isRowEmpty = true;
				for(int j=0; j<nBlanks; j++) System.out.print(" ");
				while(globalStack.isEmpty()==false) {
					Node temp = globalStack.pop(); 
					if(temp != null) {
						System.out.print(temp.iData); localStack.push(temp.leftChild); localStack.push(temp.rightChild);
						if(temp.leftChild != null ||temp.rightChild != null) 
							isRowEmpty = false;
					} 
					else {
						System.out.print("--"); 
						localStack.push(null); localStack.push(null); 
					}
					for(int j=0; j<nBlanks*2-2; j++)
						System.out.print(' ');
				}  // end while globalStack not empty
				System.out.println();
				nBlanks /= 2;
				while(localStack.isEmpty()==false)
					globalStack.push( localStack.pop() ); 	
			}  // end while isRowEmpty is false 
			System.out.println("......................................................");
	}  // end displayTree()
		
}	//end class Tree

class TreeApp {

	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		String task="";
		int data = -1;
		boolean wasRemoved=false;	
		Tree tree = new Tree();
		
		String line = scnr.nextLine();
		while (!line.equals("-1")) { 
	    	
			task = line.split(" ")[0];
			data = Integer.parseInt(line.split(" ")[1]); 
	    	
			if (task.equals("i")) { //insert
				tree.insert(data); 
				System.out.println("Inserting "+data+": complete."); 
				tree.traverse(2); 
			} 
			else if (task.equals("s")) { //search
				Node node = tree.search(data);
				if (node==null) System.out.println("Searching for "+data+": not found.");
				else System.out.println("Searching for "+data+": found.");
			}
			else if (task.equals("r")) { //remove
				wasRemoved = tree.remove(data);
				if(wasRemoved) {
					System.out.println("Removing "+data+": complete.");
				}
				else System.out.println("Removing "+data+": not found.");
					tree.traverse(2); 
			}
			else if (task.equals("t")) { //traverse
				tree.traverse(data);
			}
	    	
			line = scnr.nextLine();
		}
		tree.displayTree();
		scnr.close();
	}	
}	//end class TreeApp