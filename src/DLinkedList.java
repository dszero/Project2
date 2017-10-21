import java.util.Iterator;

public class DLinkedList<T> implements Iterable<T>
{
	//node insert and remove based on the point object
	//size
	//inner node class
	/**
	 * initialize the variables
	 */
	Node head;
	private Node tail;
	private int listSize; //size of the linked list
	private boolean identicalPoints; //true if all the points in the 
											//list have the same location coordinates
	/**
	 * initialize constructor of linked list
	 */
	public DLinkedList()
	{
		//head = new Node();
		//tail = new Node();
		//head.setNext(tail);
		//tail.setPrev(head);
		//identicalPoints = true;
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
	 * add a new node into the linked list 
	 * First three nodes will be added into the list
	 * no matter they have the same x, y coordinates 
	 * but if more nodes require to be added, the 
	 * identicalPoints boolean should be checked before adding
	 * if the points in the list have the same location coordinates 
	 * with the point which is waiting to be added, then add it into 
	 * the list, otherwise, reject to add the new point into list.
	 * 
	 * @param point that requires to be added into the list
	 * @return true if the add successfully, otherwise, return false;
	 */
//	public boolean add(T obj) 
//	{
//		boolean added = false;
//		
//		if (listSize >= 3) //identicalPoints is true
//		{
//			if (identicalPoints == true && checkIdentical(point))
//			{
//				added = true;
//			}
//		}
//		else if (listSize == 0)
//		{
//			added = true;
//		}
//		else
//		{
//			if (!checkIdentical(point))
//			{
//				identicalPoints = false;
//			}
//			added = true;
//		}		
//		
//		if (added == true)
//		{
//			addToFront(point);
//			listSize++;
//		}
//		return added;
	
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
	
//	/**
//	 * check if the point in the new node has the same location 
//	 * coordinate with the first node in the list
//	 * 
//	 * @param newNode that needs to be checked with
//	 * @return true if the point in the new node has the identical location
//	 * 			with the point that is stored in the first node in the list;
//	 * 			otherwise, return false.
//	 */
//	public boolean checkIdentical(Point point)
//	{
//		if (point.getX() == head.next().point().getX() &&
//				point.getY() == head.next().point().getY())
//		{
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * add a new node to the front of the list
	 * @param point that needs to be stored in the new node
	 */
//	public Node addToFront(Point point)
//	{
//		Node newNode = new Node();
//		newNode.setPoint(point);
//		newNode.setPrev(head);
//		newNode.setNext(head.next());
//		head.next().setPrev(newNode);
//		head.setNext(newNode);
//		return newNode;
//	}
//	
	/**
	 * check if there are points that have been stored in the nodes
	 * have the same name as the parameter string
	 * if yes, remove them all
	 * @param name of the point 
	 * @return true if removed successfully,
	 * 			otherwise, return false
	 */
//	public boolean remove(String name)
//	{
//		boolean removed = false;
//		Node current = head.next();
//		while (current != tail)
//		{
//			if (current.data().getName().equals(name))
//			{
//				//System.out.println(current.point().getName());
//				current.prev().setNext(current.next());
//				current.next().setPrev(current.prev());
//				removed = true;
//				listSize--;
//			}
//			current = current.next();			
//		}
//		return removed;
//	}
	
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
	
//	/**
//	 * remove nodes in the linked list that have the same x and y coordinates 
//	 * as the passed in parameters
//	 * if the list size is greater than three, which means every points that 
//	 * have been stored in this list all have the same locations coordinates
//	 * other wise, iterate the list and find the match point
//	 * @param x coordinate of the points that need to be removed
//	 * @param y coordinate of the points that need to be removed
//	 * @return true if removing successfully; false, if the node cannot be found
//	 */
//	public boolean remove(int x, int y)
//	{
//		boolean removed = false;
//		Point tempPoint = new Point("Test", x, y);
//		//every point in this linked list has the same x and y coordinates
//		if (identicalPoints && checkIdentical(tempPoint)) 
//		{
//			head.setNext(tail);
//			tail.setPrev(head);
//			removed = true;
//			listSize = 0;
//		}
//		else
//		{
//			Node current = head.next();
//			for (int i = 0; i < listSize; i++)
//			{
//				if (current.point().getX() == x && current.point().getY() == y)
//				{
//					current.prev().setNext(current.next());
//					current.next().setPrev(current.prev());
//					removed = true;
//					listSize--;
//				}
//				current = current.next();
//			}
//		}
//		return removed;
//	}

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
	 * print out all the points in the list
	 */
//	public String toString()
//	{
//		String str = "";
//		Node current = head.next;
//		for (int i= 0; i < listSize; i++)
//		{
//			str = str + "(" + current.point.getName() + ", "
//					+ current.point.getX() + ", " + 
//					current.point.getY() + ")";
//		}
//		return str;
//	}
	
	
	
	
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
