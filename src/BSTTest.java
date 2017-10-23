import junit.framework.TestCase;
/**
 * This is the junit testcase for bst class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017 *
 */


public class BSTTest extends TestCase {
	/**
	 * initialize variable
	 */
	private BST<Point> bst;

	/**
	 * set up before testing
	 */
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
		assertFalse(bst.remove(point));
		bst.insert(point);
		bst.insert(point2);
		bst.insert(point1);
		assertNotNull(bst.find(point2));
		assertNotNull(bst.find(point1));
		assertTrue(bst.remove(point1));
		assertTrue(bst.remove(point2));
		
	}
	
	/**
	 * test for find point by name
	 */
	public void testFindPointByName()
	{
		Point point = new Point("name", 1, 4);
		Point point1 = new Point("apple", 4, 5);
		Point point2 = new Point("zoo", 3, 7);
		assertNull(bst.find("name"));
		bst.insert(point);
		bst.insert(point2);
		bst.insert(point1);
		assertNotNull(bst.find("apple"));
		assertNotNull(bst.find("zoo"));
	}

}
