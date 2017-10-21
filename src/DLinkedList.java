import java.util.Iterator;

public class DLinkedList<T> implements Iterable<T>
{
	//node insert and remove based on the point object
	//size
	//inner node class
	/**
	 * initialize the variables
	 */
	private int listSize; //size of the linked list
	private Node head, tail;
	private boolean identicalPoints; //true if all the points in the 
											//list have the same location coordinates
	/**
	 * initialize constructor of linked list
	 */
	public DLinkedList()
	{
		
		listSize = 0;
	}

	
	/**
	 * get the current size of the list
	 * @return listSize
	 */
	public int size()
	{
		return listSize;
	}
	
	/**
	 * get the boolean identicalPoints
	 * @return identicalPoints
	 */
	public boolean getIdenticalPoints()
	{
		return identicalPoints;
	}
	

	/**
	 * Add new non-null object to end of list
	 * 
	 * @param obj - obj to add
	 * @return true if added, false if not
	 */
	public boolean add(T obj) {
		if (obj == null) {
			return false;
		}
		
		Node newNode = new Node(obj);
		newNode.setData(obj);

		if(head == null) {
			head = newNode;
			tail = newNode;
			listSize++;
			return true;
		}
		
		tail.setNext(newNode);
		newNode.setPrev(tail);
		tail = newNode;
		listSize++;
		return true;			
	}

	
	/**
	 * Remove object at given index
	 * 
	 * @param i - index of object
	 * @return true if removed, false if not
	 */
	public boolean remove(T obj) {
		Node currNode = head;
		boolean found = false;
		
		int i = 0;
		while(!found && i < listSize) {
			if(currNode == null) {
				return false;
			}
			if(currNode.data().equals(obj)) {
				found = true;
			}
			if(!found) {
				currNode = currNode.next();
			}
			i++;
		}
		if(!found) {
			return false;
		}
		
		if(listSize == 1) {
			head = null;
			tail = null;
		}
		if(currNode.prev() != null) {
			currNode.prev().setNext(currNode.next());
		}
		else {
			head = currNode.next();
		}
		if(currNode.next() != null) {
			currNode.next().setPrev(currNode.prev());
		}
		else {
			tail = currNode.prev();
		}
		
		listSize--;
		return true;
	}
	

	/**
	 * Get the value at the specified index
	 * 
	 * @param index - index of value
	 * @return object contained at index
	 */
	public T get(int index) {
		Node currNode = head;
		for(int i = 0; i < index; i++) {
			currNode = currNode.next();
			if(currNode == null) {
				return null;
			}
		}
		return currNode.data();
	}

	@Override
	public Iterator<T> iterator() {
		return new DLLIterator(head);
	}
	
	
	/**
	 * Add all items from another linked list
	 * 
	 * @param newList - linked list to add from
	 * @return true if added, false if not
	 */
	public boolean addAll(DLinkedList<T> newList) {
		if(newList == null) {
			return false;
		}
		for(T item : newList) {
			this.add(item);
		}
		return true;
	}

	

	
	
	
	/**
	 * this is an inner node class for doubly linked list which gives the references
	 * of the list
	 */
	private class Node {
		/**
		 * initialize node class variables
		 */
		private Node prev;// Set the previous and next references of nodes
		private Node next;
		private T data; // The object that will be stored in the node

		/**
		 * initialize construction for head and tail
		 */
		public Node() {
			next = null;
			prev = null;
			data = null;
		}
		
		/**
		 * initialize construction for head and tail
		 */
		public Node(T obj) {
			next = null;
			prev = null;
			data = obj;
		}

		// /**
		// * initialize constructor
		// * @param next node
		// * @param prev node
		// * @param point that is stored in the node
		// */
		// public Node(Node next, Node prev, Point point)
		// {
		// this.next = next;
		// this.prev = prev;
		// this.point = point;
		// }

		/**
		 * set the next node
		 * 
		 * @param node
		 *            set as next
		 */
		public void setNext(Node node) {
			next = node;
		}

		/**
		 * set the previous node
		 * 
		 * @param node
		 *            set as previous
		 */
		public void setPrev(Node node) {
			prev = node;
		}

		/**
		 * set the point of the node
		 * 
		 * @param point1
		 *            needs to be set
		 */
		public void setData(T obj) {
			data = obj;
		}

		/**
		 * get the next node
		 * 
		 * @return next
		 */
		public Node next() {
			return next;
		}

		/**
		 * get the previous node
		 * 
		 * @return prev
		 */
		public Node prev() {
			return prev;
		}

		/**
		 * get the point object
		 * 
		 * @return point
		 */
		public T data() {
			return data;
		}

	}
	
	private class DLLIterator implements Iterator<T> {
		
		private Node node;
		
		public DLLIterator(Node newNode) {
			node = newNode;
		}
		
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			T data = node.data();
			node = node.next();
			return data;
		}
		
	}
	
	
	
	
	


		
		
}