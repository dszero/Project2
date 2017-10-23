import junit.framework.TestCase;

public class BSTTest extends TestCase {
	
	private BST<Point> bst;

	public void setUp() 
	{
		bst = new BST<Point>();
	}
	
	/**
	 * test for find point obj
	 */
	public void testFindPoint()
	{
		Point point = new Point("name", 1, 4);
		Point point1 = new Point("apple", 4, 5);
		Point point2 = new Point("zoo", 3, 7);
		assertNull(bst.find(point));
		bst.insert(point);
		bst.insert(point2);
		bst.insert(point1);
		assertNotNull(bst.find(point2));
		assertNotNull(bst.find(point1));
	}

}
