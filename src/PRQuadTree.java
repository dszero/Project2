
public class PRQuadTree < T extends Comparable2D<? super T> > {
	private QuadTreeNode<T> root;
	private int xMin, xMax, yMin, yMax;

	/**
	 * Create new PR Quadtree with given bounds
	 * 
	 * @param xMin - minimum x coordinate for items
	 * @param xMax - minimum y coordinate for items
	 * @param yMin - maximum x coordinate for items
	 * @param yMax - maximum y coordinate for items
	 */
	public PRQuadTree(int xMin, int xMax, int yMin, int yMax) {
		
	}
	
	/**
	 * Insert new item into tree
	 * 
	 * @param elem - object to insert
	 * @return true if inserted, fakse if not
	 */
	public boolean insert(T elem) {
		return false;
	}
	
	/**
	 * Remove item from tree
	 * 
	 * @param elem - object to remove
	 * @return true if removed, false if not
	 */
	public boolean remove(T elem) {
		return false;
	}
	
	/**
	 * Find item in tree
	 * 
	 * @param elem - object to find
	 * @return object if found, null if not
	 */
	public T find(T elem) {
		return null;
	}
	
	/**
	 * Clear all items from tree
	 */
	public void clear() {
		
	}


}
	