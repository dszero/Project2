/**
 * This is the leaf node class
 * will implement the prquadtree interface
 * 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class LeafNode< T extends Comparable2D<? super T> > implements QuadTreeNode<T>{
	
	private LinkedList<T> items;
	
	/**
	 * Create new flyweight leaf node
	 */
	public LeafNode()
	{
		
	}
	
	/**
	 * Create leaf node containing one object
	 * 
	 * @param point
	 */
	public LeafNode(T obj)
	{
		
	}

	/**
	 * Insert object into leaf node or split into multiple leaves
	 * 
	 * @param obj - object to insert
	 * @return true if inserted, false if it is a duplicate
	 */
	@Override
	public boolean insert(T obj) {

		return false;
	}

	/**
	 * Remove object from leaf node
	 * 
	 * @param obj - object to remove
	 * @return true if removed, false if not found
	 */
	@Override
	public boolean remove(T obj) {

		return false;
	}

	/**
	 * Remove object at given coordinates from leaf node
	 * 
	 * @param x - x coordinate of object
	 * @param y - y coordinate of object
	 */
	@Override
	public boolean remove(int x, int y) {

		return false;
	}

	/**
	 * Find all nodes in region bounded by the given square
	 * 
	 * @param x - upper bound square
	 * @param y - lower bound square
	 * @return a linked list of objects contained in the bounded region
	 */
	@Override
	public boolean regionsearch(int x, int y, int w, int h) {

		return false;
	}

	/**
	 * Find all objects with duplicate locations
	 * 
	 * @return a linked list of coordinates with duplicates
	 */
	@Override
	public boolean duplicates() {

		return false;
	}
	
	/**
	 * Indicate this is a leaf node
	 * 
	 * @return true
	 */
	@Override
	public boolean isLeaf() {
		return true;
	}
	
}
