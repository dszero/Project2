/**
 * The internal node class of the quadtree
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
	}

	/**
	 * Insert object into children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param obj - object to insert
	 * @return true if inserted, false if duplicate
	 */
	@Override
	public boolean insert(int x, int y, int w, int h, T obj) {
		if (obj == null) {
			return false;
		}
	 
		boolean out = false;
		
		Direction dir = obj.compare2D(x, y);
		QuadTreeNode<T> child = this.getBranch(dir);
		
		out = child.insert(getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2, obj);
		
		if (child.getClass().equals(LeafNode.class)) {
			setBranch(dir, ((LeafNode<T>) child).decompose(getBranchX(x, w, dir), getBranchY(y, w, dir), w / 2, h / 2));
		}	
		
		return out;
	}


	/**
	 * Remove object from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param obj - object to remove
	 * @return removed object or null
	 */
	@Override
	public T remove(int x, int y, int w, int h, T obj) {
		if (obj == null) {
			return null;
		}
		
		Direction dir = obj.compare2D(x, y);
		QuadTreeNode<T> child = this.getBranch(dir);
		T out = child.remove(getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2, obj);
		
		if (child.getClass().equals(InternalNode.class)) {
			setBranch(dir, ((InternalNode<T>) child).combine(getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2));
		}	
		
		return out;
	}

	/**
	 * Remove object at given coordinates from children leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param objX - x coordinate of object
	 * @param objY - y coordinate of object
	 * @return true if removed, false if not found
	 */
	@Override
	public T remove(int x, int y, int w, int h, int objX, int objY) {
		Direction dir = getDirection(x, y, objX, objY);
		QuadTreeNode<T> child = this.getBranch(dir);
		T out = child.remove(getBranchX(x, w, dir), getBranchY(y, h, dir), w / h, h / 2, objX, objY);
		
		if (child.getClass().equals(InternalNode.class)) {
			setBranch(dir, ((InternalNode<T>) child).combine(getBranchX(x, w, dir), getBranchY(y, h, dir), w, h));
		}	
		
		return out;
	}

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
	@Override
	public int regionsearch(DLinkedList<T> results, int x, int y, int w, int h, 
			int objX, int objY, int objW, int objH) {
		//if region outside of node
		if ((x - (w / 2)) > objX + objW || 
				(x + (w / 2)) < objX || 
				(y - (h / 2)) > objY + objH  ||
				(y + (h / 2)) < objY) {
			return 0;
		}
		int visited = 1;
		for (Direction dir : Direction.values()) {
			visited += getBranch(dir).regionsearch(results, 
					getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2, 
					objX, objY, objW, objH);
		}
		return visited;
	}


	/**
	 * Find all objects with duplicate locations
	 * 
	 * @return a linked list of coordinates with duplicates
	 */
	@Override
	public DLinkedList<T> duplicates() {
		DLinkedList<T> list = new DLinkedList<T>();
		
		list.addAll(this.NW.duplicates());
		list.addAll(this.NE.duplicates());
		list.addAll(this.SW.duplicates());
		list.addAll(this.SE.duplicates());
		
		return list;
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
	private QuadTreeNode<T> getBranch(Direction direc) {
		if (direc == null) {
			return null;
		}
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
	 * Set the branch corresponding to the given direction to the given node
	 * 
	 * @param direc - direction to search in
	 * @param obj - node to set branch to
	 * @return branch corresponding to direction
	 */
	private boolean setBranch(Direction direc, QuadTreeNode<T> obj) {
		switch(direc){
			case NW:
				this.NW = obj;
				break;
			case NE:
				this.NE = obj;
				break;
			case SE:
				this.SE = obj;
				break;
			case SW:
				this.SW = obj;
				break;
		}
		return true;
	}
	
	/**
	 * Get the x mid-point of the given branch
	 * 
	 * @param x - current x mid-point
	 * @param w - current width
	 * @param dir - direction of branch
	 * @return eastern midpoint if direction is east,
	 * otherwise western midpoint
	 */
	private int getBranchX(int x, int w, Direction dir) {
		switch(dir) {
		case NW:
		case SW:
			return x - (w / 4);
		case NE:
		case SE:
		default:
			return x + (w / 4);	
		}
	}
	
	/**
	 * Get the y mid-point of the given branch
	 * 
	 * @param y - current y mid-point
	 * @param h - current height
	 * @param dir - direction of branch
	 * @return norther midpoint if direction is north,
	 * otherwise southern midpoint
	 */
	private int getBranchY(int y, int h, Direction dir) {
		switch(dir) {
		case NW:
		case NE:
			return y - (h / 4);
		case SW:
		case SE:
		default:
			return y + (h / 4);	
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
	private Direction getDirection(int x, int y, int objX, int objY) {
		if (objY > y) {
			if (objX < x) {
				return Direction.SW;
			}
			return Direction.SE;
		}
		if (objY < y) { 
			if (objX < x) { 
				return Direction.NW;
			}
			return Direction.NE;
		}
		if (objX < x) {
			return Direction.SW;
		}
		return Direction.NE;
	}
	
	/**
	 * If the leaf does not meet the decomposition rule, combine it
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @return new child node, either combined or not
	 */
	public QuadTreeNode<T> combine(int x, int y, int w, int h) {
		for (Direction dir : Direction.values()) {
			if (!getBranch(dir).getClass().equals(LeafNode.class)) {
				return this;
			}
		}
		
		DLinkedList<T> items = this.allChildren();
		
		
		//Check decomposition rule
		boolean sameLoc = true;
		for (T i : items) {
			if (!i.equals2D(items.get(0))) {
				sameLoc = false;
			}
		}
		if (!sameLoc && items.size() > 3) {
			return this;
		}
		
		//decompose
		LeafNode<T> newNode = new LeafNode<T>();
		for (T i : items) {
			newNode.insert(x, y, w, h, i);
		}
		return newNode;
		
	}
	
	/**
	 * Get all child items of this region
	 * 
	 * @return linked list of items
	 */
	@Override
	public DLinkedList<T> allChildren() {
		DLinkedList<T> list = new DLinkedList<T>();
		
		for (Direction dir : Direction.values()) {
			list.addAll(getBranch(dir).allChildren());
		}
		
		return list;
	}
	
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
	@Override
	public String toString(int x, int y, int w, int h, int d) {
		String tabs = "";
		for (int i = 0; i < d; i++) {
			tabs += "  ";
		}
		
		return tabs + "Node at " + (x - w/2) + ", " + (y - h/2) + ", " + w + ": Internal\n" +
				NW.toString(getBranchX(x, w, Direction.NW)	, getBranchY(y, h, Direction.NW), w / 2, h / 2, d + 1) +
				NE.toString(getBranchX(x, w, Direction.NE), getBranchY(y, h, Direction.NE), w / 2, h / 2, d + 1) + 
				SW.toString(getBranchX(x, w, Direction.SW), getBranchY(y, h, Direction.SW), w / 2, h / 2, d + 1) +
				SE.toString(getBranchX(x, w, Direction.SE), getBranchY(y, h, Direction.SE), w / 2, h / 2, d + 1);
	}
}
