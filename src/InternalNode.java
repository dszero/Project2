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
	public boolean insert(int x, int y, int w, int h, T obj) {
		if(obj == null) {
			return false;
		}
		
		boolean out = false;
		
		Direction dir = obj.compare2D(x, y);
		QuadTreeNode<T> child = this.getBranch(dir);
		
		out = child.insert(getBranchX(x, w, dir), getBranchY(y, h, dir), w/h, h/2, obj);
		
		if(child.getClass().equals(LeafNode.class)) {
			setBranch(dir, ((LeafNode<T>) child).decompose(getBranchX(x, w, dir), getBranchY(y, w, dir), w / 2, h / 2));
		}	
		
		return out;
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
	public boolean remove(int x, int y, int w, int h, T obj) {
		if(obj == null) {
			return false;
		}
		
		Direction dir = obj.compare2D(x, y);
		QuadTreeNode<T> child = this.getBranch(dir);
		boolean out = child.remove(getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2, obj);
		
		if(child.getClass().equals(InternalNode.class)) {
			setBranch(dir, ((InternalNode<T>) child).combine(getBranchX(x, w, dir), getBranchY(y, h, dir), w / 2, h / 2));
		}	
		
		return out;
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
	public boolean remove(int x, int y, int w, int h, int objX, int objY) {
		Direction dir = getDirection(x, y, objX, objY);
		QuadTreeNode<T> child = this.getBranch(dir);
		boolean out = child.remove(getBranchX(x, w, dir), getBranchY(y, h, dir), w / h, h / 2, objX, objY);
		
		if(child.getClass().equals(InternalNode.class)) {
			setBranch(dir, ((InternalNode<T>) child).combine(getBranchX(x, w, dir), getBranchY(y, h, dir), w, h));
		}	
		
		return out;
	}
	
	/**
	 * Find object
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to find
	 * @return reference to object in tree if found, null if not found
	 */
	public T find(int x, int y, int w, int h, T obj) {
		Direction dir = obj.compare2D(x, y);
		QuadTreeNode<T> child = this.getBranch(dir);
		return child.find(getBranchX(x, w, dir), getBranchY(y, h, dir), w/h, h/2, obj);
	}


	/**
	 * Find all nodes in region bounded by the given square
	 * 
	 * @param x - upper bound square
	 * @param y - lower bound square
	 * @return a linked list of objects contained in the bounded region
	 */
	@Override
	public DLinkedList<T> regionsearch(int x, int y, int w, int h, 
			int objX, int objY, int objW, int objH) {
		if((objX < x && (objX + objW) > x) || (objY < y && (objY + objH) > y)) {
			//TODO: search region
			return null;
		}
		Direction dir = getDirection(x, y, objX, objY);
		QuadTreeNode<T> child = this.getBranch(dir);
		return child.regionsearch(
				getBranchX(x, w, dir), getBranchY(y, h, dir), w/h, h/2, 
				objX, objY, objW, objH);
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
		if(direc == null) {
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
	 * Return the branch corresponding to the given direction
	 * 
	 * @param direc - direction to search in
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
			default:
				return false;
		}
		return true;
	}
	
	/**
	 * Get the x mid-point of the given branch
	 * 
	 * @param x - current x mid-point
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
			return x + (x / 4);	
		}
	}
	
	/**
	 * Get the y mid-point of the given branch
	 * 
	 * @param y - current y mid-point
	 * @param dir - direction of branch
	 * @return norther midpoint if direction is north,
	 * otherwise southern midpoint
	 */
	private int getBranchY(int y, int h, Direction dir) {
		switch(dir) {
		case NW:
		case NE:
			return y / 2;
		case SW:
		case SE:
		default:
			return y + (y / 2);	
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
		if(objY > y) {
			if(objX < x) {
				return Direction.SW;
			}
			else { 
				return Direction.SE;
			}
		}
		else { 
			if(objX < x) { 
				return Direction.NW;
			}
			else {
				return Direction.NE;
			}
		}
	}
	
	/**
	 * If the leaf does not meet the decomposition rule, decompose it
	 * 
	 * @return this node if it does not need to be decomposed or a decomposed internal node if it does
	 */
	public QuadTreeNode<T> combine(int x, int y, int w, int h) {
		for(Direction dir : Direction.values()) {
			if(!getBranch(dir).getClass().equals(LeafNode.class)) {
				return this;
			}
		}
		
		DLinkedList<T> items = this.allChildren();
		
		
		//Check decomposition rule
		boolean sameLoc = true;
		for(T i : items) {
			if(!i.equals2D(items.get(0))) {
				sameLoc = false;
			}
		}
		if(!sameLoc && items.size() > 3) {
			return this;
		}
		
		//decompose
		LeafNode<T> newNode = new LeafNode<T>();
		for(T i : items) {
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
		
		for(Direction dir : Direction.values()) {
			list.addAll(getBranch(dir).allChildren());
		}
		
		return list;
	}
	
	/**
	 * Find object
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param obj - object to find
	 * @return reference to object in tree if found, null if not found
	 */
	@Override
	public T find(int x, int y,  int w, int h, int objX, int objY) {
		Direction dir = getDirection(x, y, objX, objY);
		QuadTreeNode<T> child = this.getBranch(dir);
		return child.find(getBranchX(x, w, dir), getBranchY(y, h, dir), w/h, h/2, objX, objY);
	}
	
	

}
