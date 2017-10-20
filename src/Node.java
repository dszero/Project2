
/**
 * this is an inner node class for doubly linked list which gives the references
 * of the list
 */
public class Node {
	/**
	 * initialize node class variables
	 */
	private Node prev;// Set the previous and next references of nodes
	private Node next;
	private Point point; // The object that will be stored in the node

	/**
	 * initialize construction for head and tail
	 */
	public Node() {
		next = null;
		prev = null;
		point = null;
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
	public void setPoint(Point point1) {
		point = point1;
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
	public Point point() {
		return point;
	}

}