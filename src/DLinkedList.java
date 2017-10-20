
public class DLinkedList
{
	//node insert and remove based on the point object
	//size
	//inner node class
	/**
	 * initialize the variables
	 */
	private Node head;
	private Node tail;
	private int listSize; //size of the linked list
	private boolean identicalPoints; //true if all the points in the 
											//list have the same location coordinates
	/**
	 * initialize constructor of linked list
	 */
	public DLinkedList()
	{
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
		identicalPoints = true;
		listSize = 0;
	}
//	/**
//	 * check if the linked list is empty
//	 * @return true if list size is 0, 
//	 * 			otherwise, return false
//	 */
//	public boolean isEmpty()
//	{
//		return listSize == 0;
//	}
	
	/**
	 * get the current size of the list
	 * @return listSize
	 */
	public int getLIstSize()
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
	public boolean add(Point point) 
	{
		boolean added = false;
		Node newNode = new Node();
		newNode.point = point;
		
	
		if (listSize >= 3) //identicalPoints is true
		{
			if (identicalPoints == true && checkIdentical(newNode))
			{
				added = true;
			}
		}
		else if (listSize == 0)
		{
			added = true;
		}
		else
		{
			if (!checkIdentical(newNode))
			{
				identicalPoints = false;
			}
			added = true;
		}		
		
		if (added == true)
		{
			addToFront(newNode);
			listSize++;
		}
		
		return added;
			
	}
	
	/**
	 * check if the point in the new node has the same location 
	 * coordinate with the first node in the list
	 * 
	 * @param newNode that needs to be checked with
	 * @return true if the point in the new node has the identical location
	 * 			with the point that is stored in the first node in the list;
	 * 			otherwise, return false.
	 */
	public boolean checkIdentical(Node newNode)
	{
		if (newNode.point.getX() == head.next.point.getX() &&
				newNode.point.getY() == head.next.point.getY())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * add a new node to the front of the list
	 * @param point that needs to be stored in the new node
	 */
	public void addToFront(Node newNode)
	{
		head.next.prev = newNode;
		newNode.next = head.next;
		newNode.prev = head;
		head.next = newNode;
		
	}
	
	/**
	 * check if there are points that have been stored in the nodes
	 * have the same name as the parameter string
	 * if yes, remove them all
	 * @param name of the point 
	 * @return true if removed successfully,
	 * 			otherwise, return false
	 */
	public boolean remove(String name)
	{
		boolean removed = false;
		Node current = head.next;
		while (current != tail)
		{
			if (current.point.getName().equals(name))
			{
				current.prev.next = current.next;
				current.next.prev = current.prev;
			}
			current = current.next;
			removed = true;
			listSize--;
		}
		return removed;
	}
	
	/**
	 * remove nodes in the linked list that have the same x and y coordinates 
	 * as the passed in parameters
	 * if the list size is greater than three, which means every points that 
	 * have been stored in this list all have the same locations coordinates
	 * other wise, iterate the list and find the match point
	 * @param x coordinate of the points that need to be removed
	 * @param y coordinate of the points that need to be removed
	 * @return true if removing successfully; false, if the node cannot be found
	 */
	public boolean remove(int x, int y)
	{
		boolean removed = false;
		//every point in this linked list has the same x and y coordinates
		if (identicalPoints) 
		{
			head.next = tail;
			tail.prev = head;
			removed = true;
			listSize = 0;
		}
		else
		{
			Node current = head.next;
			for (int i = 0; i < listSize; i++)
			{
				if (current.point.getX() == x && current.point.getY() == y)
				{
					current.prev.next = current.next;
					current.next.prev = current.prev;
					removed = true;
					listSize--;
				}
				current = current.next;
			}
		}
		return removed;
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
		private Node prev;//Set the previous and next references of nodes
		public Node next;
		private Point point; //The object that will be stored in the node
	
	}
	
	/**
	 * print out all the points in the list
	 */
	public String toString()
	{
		String str = "";
		Node current = head.next;
		for (int i= 0; i < listSize; i++)
		{
			str = str + "(" + current.point.getName() + ", "
					+ current.point.getX() + ", " + 
					current.point.getY() + ")";
		}
		return str;
	}
	
	


		
		
}
