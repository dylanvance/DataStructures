/*
 * 2-3-4 Tree Data Structure Implementation Program
 * Authored by Dylan Vance
 * Some code provided by Professor Kahanda
 */
package BTree234;

import java.util.Scanner;

class DataItem
{
	public long dData;          // one data item 
	//--------------------------------------------------------------
	public DataItem(long dd)    // constructor
		{ dData = dd; } 
	//--------------------------------------------------------------
	public void displayItem()   // display item, format “/27”
		{ System.out.print("/"+dData); } 
	//--------------------------------------------------------------
	
	public void setData(long in) {
		this.dData = in;
	}
}  // end class DataItem ////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
class Node234
{
	private static final int ORDER = 4; //order of 2-3-4 Tree is 4 (i.e. K=4)
	private int numItems; // # of items in the node
	private Node234 parent;
	private Node234 childArray[] = new Node234[ORDER]; //each node can have up to 4 (=K) child nodes
	private DataItem itemArray[] = new DataItem[ORDER-1]; //each node can contain up to 3 (=K-1) keys
	// -------------------------------------------------------------
	
	public Node234 getChild(int childNum)
	{ return childArray[childNum]; }
	// -------------------------------------------------------------
	
	public void setChild(Node234 child, int index) {
		this.childArray[index] = child;
	}
	
	public Node234 getParent()
	{ return parent; }
	// -------------------------------------------------------------
	
	public boolean isLeaf()
	{ return (childArray[0]==null) ? true : false; }
	// -------------------------------------------------------------
	
	public int getNumItems()
	{ return numItems; }
	// -------------------------------------------------------------
	
	public void incrementNumItems() {
		this.numItems += 1;
	}
	
	public DataItem getItem(int index)   // get DataItem at index
	{ return itemArray[index]; }
	// -------------------------------------------------------------
	
	public void setItem(int index, long input) {
		DataItem data = new DataItem(input);
		this.itemArray[index] = data;
		this.numItems += 1;
	}
	
	public void assignItem(int index1, int index2) {
		if (this.itemArray[index1] == null) {
			setItem(index1, this.itemArray[index2].dData);
		}
		else {
			this.itemArray[index1] = this.itemArray[index2];
		}
	}
	
	public void swapItems(int index1, int index2) {
		DataItem temp;
		temp = this.itemArray[index1];
		this.itemArray[index1] = this.itemArray[index2];
		this.itemArray[index2] = temp;
	}
	
	public void swapInsert(int keyIndex, int movedIndex, long input) {
		DataItem key = new DataItem(input);
		DataItem temp = new DataItem(this.itemArray[keyIndex].dData);
		this.itemArray[keyIndex] = key;
		this.itemArray[movedIndex] = temp;
	}
	
	public boolean isFull()
	{ return (numItems==ORDER-1) ? true : false; }
	// -------------------------------------------------------------
	
	public void displayNode()           // format “/24/56/74/”
	{
		for(int j=0; j<numItems; j++)
		itemArray[j].displayItem();   // “/56” 
		System.out.println("/");         // final “/”
	}
	// -------------------------------------------------------------
}  // end class Node234 ////////////////////////////////////////////////////////////////

class Tree234 {
	
	private Node234 root = new Node234();            // make root node 
	// -------------------------------------------------------------
	
	// search for a key
	public int search(long key)
	{
		//Tree is empty
		if (this.root.getItem(0) == null) {
			return -1;
		}
		
		Node234 current = this.root;
			
		while (current != null) {
			
			if (current.getItem(0).dData == key || 
				current.getItem(1).dData == key ||
				current.getItem(2).dData == key) {
				return 0; //node found
			}
			
			if (key < current.getItem(0).dData) {
				current = current.getChild(0);
			}
			else if (current.getItem(1) == null || key < current.getItem(1).dData) {
				current = current.getChild(1);
			}
			else if (current.getItem(2) == null || key < current.getItem(2).dData) {
				current = current.getChild(2);
			}
			else {
				current = current.getChild(3);
			}
		}
		
		return -1;	//not found --> return -1
		
	} // end search()
	// -------------------------------------------------------------
	
	// insert a DataItem
	public void insert(long dValue)
	{
		
		int i = 0;
		
		//If tree is empty
		if (root.getItem(0) == null) {
			root.setItem(0, dValue);
		}
		
		Node234 current = root;
		
		while (current != null) {
			
			int numItems = current.getNumItems();
			//Checks if value is already in node
			for (i = 0; i < numItems; i++) {
				if (current.getItem(i).dData == dValue)
					return;
			}
			
			//if node is full
			if (current.isFull()) {
				split(current);
				current = this.root;
			}
			//if node is not a leaf
			if (!current.isLeaf()) {
				if (dValue < current.getItem(0).dData) {
					current = current.getChild(0);	//move to left child
				}
				else if (current.getItem(1) == null || dValue < current.getItem(1).dData) {
					current = current.getChild(1);	//move to middle1 child
				}
				else if (current.getItem(2) == null || dValue < current.getItem(2).dData) {
					current = current.getChild(3);	//move to middle2 child
				}
				else {
					current = current.getChild(4);	//move to right child
				}
			}
			else {
				leafInsert(current, dValue);
				return;
			}
		}
		
	}  // end insert()
	// -------------------------------------------------------------
	
	public void leafInsert(Node234 node, long key) {
		switch (node.getNumItems()) {
			case 0:									//No items [Null, Null, Null]
				node.setItem(0, key);				//key goes into 0
				break;
			case 1:									//1 item [0, Null, Null]
				if (key < node.getItem(0).dData) {
					node.swapInsert(0, 1, key);		//key goes into 0
					node.incrementNumItems();
				}
				else {
					node.setItem(1, key);			//key goes into 1
				}
				break;
			case 2:									//2 items [0, 1, Null]
				if (key < node.getItem(0).dData) {
					node.swapInsert(0, 1, key);		//key goes into 0
					node.incrementNumItems();
				}
				else if (key < node.getItem(1).dData) {
					node.swapInsert(1, 2, key);		//key goes into 1
					node.incrementNumItems();
				}
				else {
					node.setItem(2, key);			//key goes into 2
				}
				break;
			default:
				System.out.println("big error");
		}	
	}
	
	public void split(Node234 node) {
		//If node is not full no split required
		if (!node.isFull()) {
			return;
		}
		
		Node234 splitLeft = new Node234();
		splitLeft.setItem(0, node.getItem(0).dData);
		
		Node234 splitRight = new Node234();
		splitRight.setItem(0, node.getItem(2).dData);
		
		if (node.getParent() != null) {
			insertKeyWithChildren(node.getParent(), node.getItem(1).dData, splitLeft, splitRight);
		}
		else {
			Node234 newRoot = new Node234();
			newRoot.setItem(0, node.getItem(1).dData);
		
			this.root = newRoot;
		
			newRoot.setChild(splitLeft, 0);
			newRoot.setChild(splitRight, 1);
		}
		
		return;
	}
	
	public void insertKeyWithChildren(Node234 parent, long key, Node234 leftChild, Node234 rightChild) {
		if (key < parent.getItem(0).dData) {
			parent.assignItem(2, 1);
			parent.assignItem(1, 0);
			parent.setItem(0, key);
			
			parent.setChild(parent.getChild(2), 3);
			parent.setChild(parent.getChild(1), 2);
			parent.setChild(rightChild, 1);
			parent.setChild(leftChild, 0);
		}
		else if (parent.getItem(1) == null || key < parent.getItem(1).dData) {
			parent.assignItem(2, 1);
			parent.setItem(1, key);
			
			parent.setChild(parent.getChild(2), 3);
			parent.setChild(rightChild, 2);
			parent.setChild(leftChild, 1);
		}
		else {
			parent.setItem(2, key);
			
			parent.setChild(rightChild, 3);
			parent.setChild(leftChild, 2);
		}
	}
	
	public void displayTree()
	{
		recDisplayTree(root, 0, 0); 
		System.out.println("......................................................"); 
	}
	// -------------------------------------------------------------
	
	private void recDisplayTree(Node234 thisNode, int level, int childNumber)
	{
		System.out.print("level="+level+" child="+childNumber+" "); 
		thisNode.displayNode();               // display this node
		
		// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for(int j=0; j<numItems+1; j++)
		{
			Node234 nextNode = thisNode.getChild(j); 
			if(nextNode != null)
				recDisplayTree(nextNode, level+1, j); 
			else
				return;
		}
	}  // end recDisplayTree()
	// -------------------------------------------------------------
}  // end class Tree234 ////////////////////////////////////////////////////////////////
	
		
////////////////////////////////////////////////////////////////
public class Tree234App{

	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		String task="";
		long data = -1;	
		Tree234 tree = new Tree234();

		String line = scnr.nextLine();
		while (!line.equals("-1")) { 

			task = line.split(" ")[0];
			data = Long.parseLong(line.split(" ")[1]); 

			if (task.equals("i")) { //insert
				tree.insert(data); 
				System.out.println("Inserting "+data+": complete."); 
				tree.displayTree(); 
			} 
			else if (task.equals("s")) { //search
				int found = tree.search(data);
				if (found==-1) System.out.println("Searching for "+data+": not found.");
				else System.out.println("Searching for "+data+": found.");
				tree.displayTree();
			}
			else
				System.out.print("Invalid entry\n");  

			line = scnr.nextLine();
		}
		System.out.println("--- Final Tree ---");
		tree.displayTree();

	}
}