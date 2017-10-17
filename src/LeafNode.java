/**
 * This is the leaf node class
 * will implement the prquadtree interface
 * 
 * @author Shan Ding (dszero) David
 * @version 10.16.2017
 *
 */
public class LeafNode implements PRQuadNode{
	
	/**
	 * initialize the variables
	 */
	private Point point;
	
	public LeafNode(Point point)
	{
		this.point = point;
	}
	
	/**
	 * override the interface function
	 * since this is the leafnode class
	 * this function should return true
	 */
	@Override
	public boolean isLeaf() {
		return true;
	}
	/**
	 * override the interface function
	 * get the x coordinate of the point that has been 
	 * stored in the leaf node
	 */
	@Override
	public int getX()
	{
		return point.getX();
	}
	
	/**
	 * override the interface function
	 * get the y coordinate of the point that has been 
	 * stored in the leaf node
	 */
	@Override
	public int getY()
	{
		return point.getY();
	}

}
