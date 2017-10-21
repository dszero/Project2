
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
		if(elem == null) {
			return false;
		}
		if(root == null) {
			root = new LeafNode(elem);
			return true;
		}
		return root.insert(centerX(), centerY(), elem);
	}
	
	/**
	 * Remove item from tree
	 * 
	 * @param elem - object to remove
	 * @return true if removed, false if not
	 */
	public boolean remove(T elem) {
		if(elem == null || root == null) {
			return false;
		}
		return root.remove(centerX(), centerY(), elem);
	}
	
	/**
	 * Find item in tree
	 * 
	 * @param elem - object to find
	 * @return object if found, null if not
	 */
	public T find(T elem) {
		if(elem == null || root == null) {
			return null;
		}
		return root.find(centerX(), centerY(), elem);
	}
	
	/**
	 * Clear all items from tree
	 */
	public void clear() {
		root = null;
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


}
	