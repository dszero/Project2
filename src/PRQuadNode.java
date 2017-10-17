/**
 * This is the interface of the quadtree node
 * will be implemented in leaf, internal and flyweight class
 * @author Shan Ding(dszero) David
 * @version 10.16.2017
 *
 */
public interface PRQuadNode {
	
	/**
	 * This function will be used to check leafnode
	 * @return true if the node is a leafnode,
	 * 			otherwise, return false
	 */
	public boolean isLeaf();
	
	/**
	 * This function will be used to get the 
	 * x coordinate of the node
	 * @return x coordinate of the node
	 */
	public int getX();

	/**
	 * This function will be used to get the 
	 * y coordinate of the node
	 * @return y coordinate of the node
	 */
	public int getY();
	
	

}
