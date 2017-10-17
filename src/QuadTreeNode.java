/**
 * This is the quadtree node abstract class
 * and will be extended by leaf, internal, and flywieght classes
 *  
 * @author Shan Ding (dszero); David Thames (davidct)
 *
 */
public abstract class QuadTreeNode< T extends Comparable2D<? super T> >
{
	public boolean insert(T obj)
	{
		return false;
	}
	
	public boolean remove(T obj)
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
	
	public boolean duplicates()
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
