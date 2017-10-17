import java.util.ListIterator;

public class DLinkedList<Point> implements Iterable<Point>{
	//node insert and remove based on the point object
	//size
	//inner node class
	/**
	 * initialize the variables
	 */
	private Node head, tail; 
	private int listSize; //size of the linked list
	
	public DLinkedList()
	{
		head.next = tail;
		tail.prev = head;
		listSize = 0;
	}
	/**
	 * check if the linked list is empty
	 * @return true if list size is 0, 
	 * 			otherwise, return false
	 */
	public boolean isEmpty()
	{
		return listSize == 0;
	}
	
	/**
	 * get the current size of the list
	 * @return listSize
	 */
	public int getLIstSize()
	{
		return listSize;
	}
	@Override
	public ListIterator<Point> iterator() {
		return new DListIterator();
	}
	/**
	 * this is an inner node class for doubly linked list
	 * which gives the references of the list
	 */
	private class Node
	{
		/**
		 * initialize node class variables
		 */
		private Node prev, next;//Set the previous and next references of nodes
		private Point point; //The object that will be stored in the node
	}
	
	private class DListIterator implements ListIterator<Point>
	{
		/**
		 * initialize the variables 
		 */
		private int count;
		private Node current;
		
		public DListIterator()
		{
			count = 0;
			current = head;
		}

		@Override
		public void add(Point arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() {
			if (current.next != tail)
			{
				return true;
			}
			return false;
		}

		@Override
		public boolean hasPrevious() {
			if (current.prev != head)
			{
				return true;
			}
			return false;
		}

		@Override
		public Point next() {
			if (hasNext())
			{
				return current.next;
			}
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Point previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(Point arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	

}
