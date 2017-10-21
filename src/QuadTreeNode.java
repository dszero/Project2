/**
 * This is the quadtree node abstract class
 * and will be extended by leaf, internal, and flywieght classes
 *  
 * @author Shan Ding (dszero); David Thames (davidct)
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
	public boolean insert(int x, int y, T obj);
	
	/**
	 * Remove object from QuadTree
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to remove
	 * @return true if removed, false if not found
	 */
	public boolean remove(int x, int y, T obj);
	
	/**
	 * Remove object at given coordinates from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param x - x coordinate of object
	 * @param y - y coordinate of object
	 * @return true if removed, false if not found
	 */
	public boolean remove(int x, int y, int objX, int objY);
	
	/**
	 * Find all nodes in region bounded by the given square
	 * 
	 * @param x - upper bound square
	 * @param y - lower bound square
	 * @return a linked list of objects contained in the bounded region
	 */
	public DLinkedList<T> regionsearch(int x, int y, int objX, int objY, int objW, int objH);
	
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

}
