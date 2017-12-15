import java.util.Iterator;


// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Conor CLery, Student NO. 16320175
 *  @version 2/11/17 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	public int size;


	/**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
        
       
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    
    public DoublyLinkedList() 				
    {
      head = null;
      tail = null;
      size = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >The conditional check of the head and the tail is constant as the List object contains a reference to
     *  both the head and the tail of the list.
     * >The return true statement and the return false statement are constant costs as they return a primitive 
     *  boolean type.
     */
    public boolean isEmpty()				
    {
    	if (head == null)						//O(1)
    		return true;						//O(1)
    	return false;							//O(1)		| TOTAL = O(1)
    }



    
    /**
     * Gets the maximum readable index of the Doubly Linked List.
     * @return highest index of list.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  >The worst case of this algorithm will always be reached due to its for loop. The code will be 
     *   executed in the for loop N times where N is the size of the Doubly Linked List.
     *   
     */
    




    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param T : the T to push on the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >No line in this function has a worst case cost greater than O(1). Therefore the worst case asymptotic
     * run time cost of this algorithm is O(1).
     */
    public void push(T T)
    {
    	DLLNode toAdd;											//O(1)
    	if (this.isEmpty()) {									//O(1)
    		toAdd = new DLLNode(T, null, null);				//O(1)
    		this.head = toAdd;									//O(1)
    		this.tail = toAdd;									//O(1)
    	}
    	else {
    		DLLNode oldTail = this.tail;						//O(1)
    		toAdd = new DLLNode(T, oldTail, null);			//O(1)
    		oldTail.next = toAdd;								//O(1)
    		this.tail = toAdd;									//O(1)		| TOTAL=O(1)
    	}
    	size++;
    }

    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param T : the T to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >Justification for this can be found in the push() function as this method reuses that code due to the fact that
     *  in my implementation, they are the exact same actions being performed.
     */
    public void enqueue(T T)
    {
    	this.push(T);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest T inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * > All costs again here are constant, even with the methods called. Therefore the total asymptotic running time of
     *  this algorithm is O(1)
     *   
     */
    public T dequeue()                          
    {
		T data = null;									//O(1)
    	if (!this.isEmpty()) {							//O(1)
    		if (this.head == this.tail) {				//O(1)
    			data = this.head.data;					//O(1)
    			this.head = null;						//O(1)
    			this.tail = null;						//O(1)
    		}
    		else {
    			DLLNode toDequeue = this.head;			//O(1)
    			data = toDequeue.data;					//O(1)
    			DLLNode newHead = toDequeue.next;		//O(1)
    			newHead.prev = null;					//O(1)
    			this.head = newHead;					//O(1)
    			toDequeue.next = null;					//O(1)
    			toDequeue.prev = null;					//O(1)
    			toDequeue = null;						//O(1)
    		}
    	}
    	size--;
    	return data;									//O(1)		| TOTAL=O(1)
    }



	public Iterator<T> iterator()  { return new DoublyLinkedListIterator(); }

	private class DoublyLinkedListIterator implements Iterator<T> {
		private DLLNode current      = head;  // the node that is returned by next()
		// reset to null upon intervening remove() or add()
		private int index = 0;

		public boolean hasNext()      { return index < size; }

		public T next() {
			T item = current.data;
			current = current.next;
			index++;
			return item;
		}

		public  void remove() {}

	}




}

