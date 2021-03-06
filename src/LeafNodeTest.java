import junit.framework.TestCase;
/**
 * The junit test class for leaf node class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 *
 */
public class LeafNodeTest extends TestCase 
{
	private LeafNode<Point> node;
	private Point pNull;
	private Point pMid;
	//private Point pNW;
	private Point pNE;
	//private Point pSW;
	//private Point pSE;
	private Point pDup1;
	private Point pDup2;
	private Point pDup3;
	
	/**
	 * Set up the internal node and test points
	 */
	public void setUp() {
		node = new LeafNode<Point>();
		pNull = null;
		pMid = new Point("Mid", 511, 511);
		//pNW = new Point("NW", 500, 520);
		pNE = new Point("NE", 520, 520);
		//pSW = new Point("SW", 500, 500);
		//pSE = new Point("SE", 520, 500);
		
		pDup1 = new Point("Dup1", 510, 510);
		pDup2 = new Point("Dup2", 510, 510);
		pDup3 = new Point("Dup3", 510, 510);
		
	}
	
	/**
	 * Test the insert method
	 */
	public void testInsert() {
		assertFalse(node.insert(511, 511, 1000, 1000, pNull));
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
	}
	
	/**
	 * Test the remove method by object
	 */
	public void testRemoveObj() {
		//test remove on empty node
		assertNull(node.remove(511, 511, 1000, 1000, pNull));
		assertNull(node.remove(511, 511, 1000, 1000, pNE));
		
		//test remove on failed insert
		assertFalse(node.insert(511, 511, 1000, 1000, pNull));
		assertNull(node.remove(511, 511, 1000, 1000, pNull));
		
		//Test remove on node
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, pMid));
		
		//Test insert after remove
		assertNotNull(node.insert(511, 511, 1000, 1000, pMid));
		
		//Test double insert
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, pMid));
	}
	
	/**
	 * Test the insert method by coordinates
	 */
	public void testRemoveXY() {
		//Test remove on empty node
		assertNull(node.remove(511, 511, 1000, 1000, 2000, 2000));
		assertNull(node.remove(511, 511, 1000, 1000, 510, 520));
		
		//Test remove on node
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, 511, 511));
		
		//Test insert after remove
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		
		//Test double insert
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, 511, 511));
		assertNotNull(node.remove(511, 511, 1000, 1000, 511, 511));
	}
	


	/**
	 * Test the duplicates method
	 */
	public void testDuplicates() {
		node.insert(511, 511, 1000, 1000, pDup1);
		assertEquals(node.duplicates().size(), 0);
		node.insert(511, 511, 1000, 1000, pDup2);
		assertEquals(node.duplicates().size(), 1);
		node.insert(511, 511, 1000, 1000, pDup3);
		assertEquals(node.duplicates().size(), 1);
	}
	
}
