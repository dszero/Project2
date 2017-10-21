import java.util.LinkedList;

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
	public boolean insert(int x, int y, T obj) {
		items.append();
		//TODO: Check for duplicates
		return true;
	}

	/**
	 * Remove object from leaf node
	 * 
	 * @param obj - object to remove
	 * @return true if removed, false if not found
	 */
	@Override
	public boolean remove(T obj) {
		int i = items.find(obj);
		if(i == -1) {
			return false;
		}
		items.remove(i);
		return true;
	}

	/**
	 * Remove object at given coordinates from leaf node
	 * 
	 * @param x - x coordinate of object
	 * @param y - y coordinate of object
	 */
	@Override
	public boolean remove(int x, int y) {
		boolean removed = false;
		//TODO: Iterate through linked list, remove node when found 
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
	public boolean regionsearch(int x, int y, int objX, int objY, int objW, int objH) {

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
	
	/**
	 * If the leaf does not meet the decomposition rule, decompose it
	 * 
	 * @return this node if it does not need to be decomposed or a decomposed internal node if it does
	 */
	public QuadTreeNode decompose(int x, int y) {
		//Check decomposition rule
		if(items.length() < 4) {
			return this;
		}
		boolean sameLoc = true;
		for(T i : items) {
			if(i.compareTo(items.get(0)) != 0) {
				sameLoc = false;
			}
		}
		if(sameLoc) {
			return this;
		}
		
		//decompose
		InternalNode<T> newNode = new InternalNode<T>();
		for(T i : items) {
			newNode.insert(x, y, i);
		}
		return newNode;
		
	}
}
