
/**
 * This is the leaf node class of the quad tree
 * 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class LeafNode< T extends Comparable2D<? super T> > implements QuadTreeNode<T>{
	
	private DLinkedList<T> items;
	
	/**
	 * Create new flyweight leaf node
	 */
	public LeafNode()
	{
		items = new DLinkedList<T>();
	}
	
	/**
	 * Create leaf node containing one object
	 * 
	 * @param point
	 */
	public LeafNode(T obj)
	{
		items = new DLinkedList<T>();
		items.add(obj);
	}

	/**
	 * Insert object into leaf node or split into multiple leaves
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param obj - object to insert
	 * @return true if inserted, false if it is a duplicate
	 */
	@Override
	public boolean insert(int x, int y,  int w, int h, T obj) {
		if(obj == null) {
			return false;
		}
		
		for(T item : items) {
			if(item.equals(obj)) {
				return false;
			}
		}
		items.add(obj);
		return true;
	}

	/**
	 * Remove object from leaf node
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @param obj - object to remove
	 * @return removed object or null
	 */
	@Override
	public T remove(int x, int y,  int w, int h, T obj) {
		if(obj == null) {
			return null;
		}
		
		return items.remove(obj);
	}

	/**
	 * Remove object at given coordinates from leaf node
	 * 
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 */
	@Override
	public T remove(int x, int y,  int w, int h, int xObj, int yObj) {
		T objToRemove = null;
		
		for(T item : items) {
			if(item.compareX(xObj) == 0 && item.compareY(yObj) == 0) {
				objToRemove = item;
			}
		}
		
		return items.remove(objToRemove);
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
	public int regionsearch(DLinkedList<T> results, int x, int y,  int w, int h, int objX, int objY, int objW, int objH) {
		//if region outside of node
		if((x - (w / 2)) > objX + objW || 
				(x + (w / 2)) < objX || 
				(y - (h / 2)) > objY + objH  ||
				(y + (h / 2)) < objY) {
			return 0;
		}
		//if region contains node
		if((x - (w / 2)) >= objX && 
				(x + (w / 2)) <= objX + objW && 
				(y - (h / 2)) >= objY && 
				(y + (h / 2)) <= objY + objH) {
			results.addAll(items);
			return 1;
		}
		//if region intersects node
		for(T item : items) {
			if(item.compareX(objX) >= 0 && 
					item.compareX(objX + objW) <= 0 && 
					item.compareY(objY) >= 0 && 
					item.compareY(objY + objH) <= 0) {
				results.add(item);
			}
		}
		return 1;
	}

	/**
	 * Find all objects with duplicate locations
	 * 
	 * @return a linked list of coordinates with duplicates
	 */
	@Override
	public DLinkedList<T> duplicates() {
		DLinkedList<T> dup = new DLinkedList<T>();
		if(items.size() < 4) {
			T found = null; 
			for(T item1 : items) {
				int dupCount = 0;
				for(T item2 : items) {
					if(item1.equals2D(item2)) {
						dupCount++;
					}
				}
				if(dupCount > 1 && found == null) {
					found = item1;
				}
			}
			dup.add(found);
			return dup;
			
		}
		else {
			dup.add(items.get(0));
		}
		return dup;
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
	 * @param x - current x position of node
	 * @param y - current y position of node
	 * @param w - current width of  node
	 * @param h - current height of  node
	 * @return this node if it does not need to be decomposed or a decomposed internal node if it does
	 */
	public QuadTreeNode<T> decompose(int x, int y, int w, int h) {
		//Check decomposition rule
		if(items.size() < 4) {
			return this;
		}
		boolean sameLoc = true;
		for(T i : items) {
			if(!i.equals2D(items.get(0))) {
				sameLoc = false;
			}
		}
		if(sameLoc) {
			return this;
		}
		
		//decompose
		InternalNode<T> newNode = new InternalNode<T>();
		for(T i : items) {
			newNode.insert(x, y, w, h, i);
		}
		return newNode;
	}

	/**
	 * Return all children nodes
	 * 
	 * @return linked list of nodes
	 */
	@Override
	public DLinkedList<T> allChildren() {
		return items;
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
		for(int i = 0; i < d; i++) {
			tabs += "  ";
		}
		if(items.size() == 0) {
			return tabs + "Node at " + (x - w/2) + ", " + (y - h/2) + ", " + w + ": Empty\n";
		}
		
		String itemDump = tabs + "Node at " + x + ", " + y + ", " + w + ":\n";
		tabs += "  ";
		for(T item : items) {
			itemDump += tabs + item.toString() + "\n";
		}
		
		return itemDump;
	}
}
