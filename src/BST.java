
/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {						
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {									
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }				//Tested

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {										//Tested
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */		
    public boolean contains(Key key) {								//Tested
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }			

    private Value get(Node x, Key key) {							//Tested
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {							
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {					//Tested
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: theta(h)
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {							
    	if (isEmpty()) { return -1; }
    	int height = 0;
    	if (root.N > 1)
    		height = getHeight(root);
    	else
    		return 0;
    	
    	return height - 1;
    	
    }
    
    private int getHeight(Node node) {				//Tested
    	Node leftNode = null;
    	Node rightNode = null;
    	if (node != null) {
    		if (node.left != null) {
    			leftNode = node.left;
    		}
    		if (node.right != null) {
    			rightNode = node.right;
    		}
    			
    		int leftHeight = getHeight(node.left);
        	int rightHeight = getHeight(node.right);
        	
        	if (leftHeight > rightHeight) {
        		return leftHeight + 1;
        	}
        	else {
        		return rightHeight + 1;
        	}
    	}
    	return 0;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {						
      if (isEmpty()) return null;
      if (this.size(root.left) == this.size(root.right)) { return root.key; }
      int medianIndex = (this.size() + 1) / 2;
      Node node = root;
      Node medianNode = null;
      medianNode = getMedianNode(node, medianIndex);    
      return medianNode.key;
    }
    
    private Node getMedianNode(Node node, int targetKey) {
    	//Tested
    	
    	if ((Integer) node.key == targetKey) {
    		return node;
    	}
    	else if ((Integer) node.key > targetKey) {
    		node = getMedianNode(node.left, targetKey);
    		return node;
    	}
    	else if ((Integer) node.key < targetKey) {
    		node = getMedianNode(node.right, targetKey);
    		return node;
    	}	
    	return node;
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() { 				//NEED TO TEST
      if (isEmpty()) return "()";
      Node currentNode = root;
      String toReturn = "";
      toReturn = "(" + printInOrder(currentNode) + ")";
      return toReturn;
    }
    
    private String printInOrder(Node node) {		//NEED TO TEST
    	String values = "";
    	Node leftNode = null;
    	Node rightNode = null;
    	if (node != null) {
    		String leftVal = "";
    		String rightVal = "";
    		if (node.left != null) {
        		leftNode = node.left;
       			leftVal += printInOrder(leftNode);
       		}
    		if (node.right != null) {
       			rightNode = node.right;
        		rightVal += printInOrder(rightNode);
        	} 		
    		values = "(" + leftVal + ")" + node.key + "(" + rightVal + ")";
    	}
    	return values;
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {				
      return assemblePrettyKeys(root, "");
    }
    
    private String assemblePrettyKeys(Node node, String vals) {			//Tested
    	String leftVals = "";
    	String rightVals = "";
    
    	if (node == null) {
    		return vals + "-null\n";
    	}
    	if (node != null) {
    		leftVals = vals + " " + "|";
    		rightVals = vals + "  ";
    		return vals + "-" + node.key.toString() + "\n" + assemblePrettyKeys(node.left, leftVals) + assemblePrettyKeys(node.right, rightVals);
    	}
    	return vals;
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {									//Tested
    	
    	root = deleteNode(root, key);
    }
    
    
    private Node deleteNode(Node node, Key keyToDelete) {			
    	if (node == null) { 
    		return null; 
    	}
    	if ((Integer) node.key > (Integer) keyToDelete) {
    		node.left = deleteNode(node.left, keyToDelete);
    	}
    	else if ((Integer) node.key < (Integer) keyToDelete) {
    		node.right = deleteNode(node.right, keyToDelete);
    	}
    	else {
    		if (node.right == null) {
    			return node.left;
    		}
    		if (node.left == null) {
    			return node.right;
    		}
    		Node newNode = node;
    		node = max(node.left);
    		node.left = deleteMax(newNode.left);
    		node.right = newNode.right;		
    	}
    	node.N = size(node.left) + size(node.right) + 1;
    	return node;
    }
    
    private Node max(Node node) {				
    	if (node.right == null) {
    		return node;
    	}
    	return max(node.right);
    }
    
    private Node deleteMax(Node node) {			
    	if (node.right == null) { return node.left; }
    	node.right = deleteMax(node.right);
    	node.N = size(node.left) + size(node.right) + 1;
    	return node;
    }
}