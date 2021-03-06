/**
 * The PRQuadtree class 
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 *
 * @param <T> generic type
 */
public class PRQuadTree < T extends Comparable2D<? super T> > {
	private QuadTreeNode<T> root;
	private int xMin, xMax, yMin, yMax;
	private int quadtreeSize;

	/**
	 * Create new PR Quadtree with given bounds
	 * 
	 * @param xMin - minimum x coordinate for items
	 * @param xMax - maximum x coordinate for items
	 * @param yMin - minimum y coordinate for items
	 * @param yMax - maximum y coordinate for items
	 */
	public PRQuadTree(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
		root = new LeafNode<T>();
		quadtreeSize = 0;
	}
	
	/**
	 * get the x boundary
	 * @return xMax
	 */
	public int getXMax()
	{
		return xMax;
	}
	
	/**
	 * get the y boundary
	 * @return yMax
	 */
	public int getYMax()
	{
		return yMax;
	}
	
	/**
	 * Insert new item into tree
	 * 
	 * @param elem - object to insert
	 * @return true if inserted, false if not
	 */
	public boolean insert(T elem) {
		if (elem == null) {
			return false;
		}
		boolean out = root.insert(centerX(), centerY(), xMax - xMin, yMax - yMin, elem);
		if (root.getClass().equals(LeafNode.class)) {
			root = ((LeafNode<T>) root).decompose(centerX(), centerY(), xMax - xMin, yMax - yMin);
		}
		quadtreeSize++;
		return out;
	}
	
	/**
	 * Remove item from tree
	 * 
	 * @param elem - object to remove
	 * @return true if removed, false if not
	 */
	public T remove(T elem) {
		if (elem == null) {
			return null;
		}
		T removed = root.remove(centerX(), centerY(), xMax - xMin, yMax - yMin, elem);
		
		if (removed != null) {
			quadtreeSize--;
			if (root.getClass().equals(InternalNode.class)) {
				root =  ((InternalNode<T>) root).combine(centerX(), centerY(), xMax - xMin, yMax - yMin);
			}	
		}
		return removed;
	}
	
	/**
	 * Remove object as given x, y coordinates
	 * 
	 * @param objX - x coordinate of obj to remove
	 * @param objY - y coordinate of obj to remove
	 * @return true if removed false if not
	 */
	public T remove(int objX, int objY) {
		if (xMin > objX  || objX > xMax || yMin > objY  || objY > yMax) {
			return null;
		}
		//System.out.println("not null");
		T removed = root.remove(centerX(), centerY(), xMax - xMin, yMax - yMin, objX, objY);
		
		if (removed != null) {
			quadtreeSize--;
			if (root.getClass().equals(InternalNode.class)) {
				root =  ((InternalNode<T>) root).combine(centerX(), centerY(), xMax - xMin, yMax - yMin);
			}	
		}
		return removed;
	}
	
	/**
	 * Search for all nodes within the given region in the quadtree
	 * 
	 * @param objX - upper bound of square
	 * @param objY - lower bound of square
	 * @param objW - width of region
	 * @param objH - height of region
	 * @return
	 */
	public int regionsearch(DLinkedList<T> results, int objX, int objY, int objW, int objH) {
		return root.regionsearch(results, centerX(), centerY(), xMax - xMin, yMax - yMin, objX, objY, objW, objH);
	}
	
	/**
	 * Clear all items from tree
	 */
	public void clear() {
		root = new LeafNode<T>();
		quadtreeSize = 0;
	}
	
	/**
	 * get the size of quadtree
	 * @return quadtreeSize
	 */
	public int getSize()
	{
		return quadtreeSize;
	}
	
	/**
	 * Find the center x coordinate in the bounds of this tree
	 * 
	 * @return x coordinate of center
	 */
	private int centerX() {
		return xMin + ((xMax - xMin) / 2);
	}
	
	/**
	 * Find the center y coordinate in the bounds of this tree
	 * 
	 * @return y coordinate of center
	 */
	private int centerY() {
		return yMin + ((yMax - yMin) / 2);
	}
	
	/**
	 * Dump QuadTree
	 */
	public String toString() {
		String string = root.toString(centerX(), centerY(), 
				xMax - xMin, yMax - yMin, 0);
		string = string.substring(0, string.length() - 1);
		return string;
	}
	
	/**
	 * Get dulpicate objects
	 * 
	 * @return linked list of objects which have other duplicate positions
	 */
	public DLinkedList<T> duplicates() {
		return root.duplicates();
	}


}
	