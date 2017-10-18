/**
 * 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class InternalNode< T extends Comparable2D<? super T> > implements QuadTreeNode<T> {
	QuadTreeNode<T> NW;
	QuadTreeNode<T> NE;
	QuadTreeNode<T> SW;
	QuadTreeNode<T> SE;
	
	/**
	 * Create a new empty internal node with flyweights
	 */
	public InternalNode() {
		NW = new LeafNode<T>();
		NE = new LeafNode<T>();
		SW = new LeafNode<T>();
		SE = new LeafNode<T>();
	};

	/**
	 * Insert object into children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to insert
	 * @return true if inserted, false if duplicate
	 */
	@Override
	public boolean insert(int x, int y, T obj) {

		return false;
	}


	/**
	 * Remove object from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to remove
	 * @return true if removed, false if not found
	 */
	@Override
	public boolean remove(int x, int y, T obj) {
		
		return false;
	}

	/**
	 * Remove object at given coordinates from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param x - x coordinate of object
	 * @param y - y coordinate of object
	 * @return true if removed, false if not found
	 */
	@Override
	public boolean remove(int x, int y, int objX, int objY) {

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
	public boolean regionsearch(int objX, int objY, int objW, int objH) {
		
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
	 * Indicate this is not a leaf node
	 * 
	 * @return false
	 */
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	/**
	 * Return the branch corresponding to the given direction
	 * 
	 * @param direc - direction to search in
	 * @return branch corresponding to direction
	 */
	public QuadTreeNode<T> getBranch(Direction direc) {
		switch(direc){
			case NW:
				return NW;
			case NE:
				return NE;
			case SE:
				return SE;
			case SW:
				return SW;
			default:
				return null;
		}
	}
	
	/**
	 * Get the quadrant object coordinates are located in relative to the source coordinates
	 * 
	 * @param x - source x position 
	 * @param y - source y position
	 * @param objX - x position of object
	 * @param objY - y position of object
	 * @return direction of object from source
	 */
	public static Direction getDirection(int x, int y, int objX, int objY) {
		if(x < objX) {
			if(y < objY) {
				return Direction.SW;
			}
			else { 
				return Direction.SE;
			}
		}
		else { 
			if(y < objY) { 
				return Direction.NW;
			}
			else {
				return Direction.NE;
			}
		}
	}
	
	

}
