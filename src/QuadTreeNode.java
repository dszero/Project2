/**
 * This is the quadtree node abstract class
 * and will be extended by leaf, internal, and flywieght classes
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 *
 */
public interface QuadTreeNode< T extends Comparable2D<? super T> >
{
	/**
	 * Insert object into QuadTree
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to insert
	 * @return true if inserted, false if duplicate
	 */
	public boolean insert(int x, int y, int w, int h, T obj);
	
	/**
	 * Remove object from QuadTree
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to remove
	 * @return removed object or null
	 */
	public T remove(int x, int y, int w, int h, T obj);
	
	/**
	 * Remove object at given coordinates from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param objX - x coordinate of object
	 * @param objY - y coordinate of object
	 * @return true if removed, false if not found
	 */
	public T remove(int x, int y, int w, int h, int objX, int objY);
	
	/**
	 * Find all nodes in region bounded by the given square
	 * 
	 * @param results - linked list to insert items contained in the region
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param objX - upper bound of square
	 * @param objY - lower bound of square
	 * @param objW - width of region
	 * @param objH - height of region
	 * @return number of nodes visited
	 */
	public int regionsearch(DLinkedList<T> results,int x, int y, int w, int h, int objX, int objY, int objW, int objH);
	
	/**
	 * Find all objects with duplicate locations
	 * 
	 * @return a linked list of coordinates with duplicates
	 */
	public DLinkedList<T> duplicates();
	
	/**
	 * Determine if object is leaf node
	 * 
	 * @return true if leaf, false if internal
	 */
	public boolean isLeaf();
	
	/**
	 * get all children items
	 * 
	 * @return all objects contained under this node as a linked list
	 */
	public DLinkedList<T> allChildren();
	
	/**
	 * Dump nodes
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param d - depth
	 * @return String displaying dump of all nodes
	 */
	public String toString(int x, int y, int w, int h, int d);

}
