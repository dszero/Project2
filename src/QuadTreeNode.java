/**
 * This is the quadtree node abstract class
 * and will be extended by leaf, internal, and flywieght classes
 *  
 * @author Shan Ding (dszero); David Thames (davidct)
 *
 */
public abstract class QuadTreeNode 
{
	public boolean insert(Point point)
	{
		return false;
	}
	
	public boolean remove(Point point)
	{
		return false;
	}
	
	public boolean remove(int x, int y)
	{
		return false;
	}
	
	public boolean regionsearch(int x, int y, int w, int h)
	{
		return false;
	}
	
	public static boolean isLeaf(QuadTreeNode node)
	{
		if (node.getClass() == LeafNode.class)
		{
			return true;
		}
		return false;
	}

}
