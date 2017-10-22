import junit.framework.TestCase;

/**
 * The test class for internal node
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 */
public class InternalNodeTest extends TestCase {
	/**
	 * initialize
	 */
	InternalNode<Point> node;
	Point pNull;
	Point pMid;
	Point pNW;
	Point pNE;
	Point pSW;
	Point pSE;
	Point pDup1;
	Point pDup2;
	Point pDup3;
	
	/**
	 * Set up the internal node and test points
	 */
	public void setUp() {
		node = new InternalNode<Point>();
		pNull = null;
		pMid = new Point("Mid", 511, 511);
		pNW = new Point("NW", 500, 520);
		pNE = new Point("NE", 520, 520);
		pSW = new Point("SW", 500, 500);
		pSE = new Point("SE", 520, 500);
		
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
		assertFalse(node.insert(511, 511, 1000, 1000, pMid));
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
		assertTrue(node.insert(511, 511, 1000, 1000, pMid));
		
		//Test double insert
		assertFalse(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, pMid));
		assertNull(node.remove(511, 511, 1000, 1000, pMid));
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
		assertFalse(node.insert(511, 511, 1000, 1000, pMid));
		assertNotNull(node.remove(511, 511, 1000, 1000, 511, 511));
		assertNull(node.remove(511, 511, 1000, 1000, 511, 511));
	}
	
	/**
	 * Test the regionsearch method
	 */
	public void testRegionsearch() {
		
		Point a = new Point("a", 10, 600);
		Point a1 = new Point("a1", 100, 600);
		Point a2 = new Point("a2", 100, 600);
		Point a3 = new Point("a3", 100, 600);
		Point b = new Point("b", 400, 600);
		Point c = new Point("c", 10, 900);
		Point d = new Point("d", 400, 900);
		Point e = new Point("e", 10, 300);
		Point f = new Point("f", 400, 300);
		Point g = new Point("g", 600, 300);
		Point h = new Point("h", 900, 300);
		
		node.insert(511, 511, 1000, 1000, a);
		node.insert(511, 511, 1000, 1000, a1);
		node.insert(511, 511, 1000, 1000, a2);
		node.insert(511, 511, 1000, 1000, a3);
		node.insert(511, 511, 1000, 1000, b);
		node.insert(511, 511, 1000, 1000, c);
		node.insert(511, 511, 1000, 1000, d);
		node.insert(511, 511, 1000, 1000, e);
		node.insert(511, 511, 1000, 1000, f);
		node.insert(511, 511, 1000, 1000, g);
		node.insert(511, 511, 1000, 1000, h);
		
		DLinkedList<Point> list = new DLinkedList<Point>();
		assertEquals(node.regionsearch(list, 511, 511, 1000, 1000, -2, -2, 700, 1100), 13);
		assertEquals(list.size(), 10);
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
	
	/**
	 * Test the isLeaf method
	 */
	public void testIsLeaf() {
		
	}
	
	/**
	 * Test toString method
	 */
	public void testToString() {
		Point a = new Point("a", 10, 600);
		Point a1 = new Point("a1", 100, 600);
		Point a2 = new Point("a2", 100, 600);
		Point a3 = new Point("a3", 100, 600);
		Point b = new Point("b", 400, 600);
		Point c = new Point("c", 10, 900);
		Point d = new Point("d", 400, 900);
		Point e = new Point("e", 10, 300);
		Point f = new Point("f", 400, 300);
		Point g = new Point("g", 600, 300);
		Point h = new Point("h", 900, 300);
		
		node.insert(511, 511, 1000, 1000, a);
		node.insert(511, 511, 1000, 1000, a1);
		node.insert(511, 511, 1000, 1000, a2);
		node.insert(511, 511, 1000, 1000, a3);
		node.insert(511, 511, 1000, 1000, b);
		node.insert(511, 511, 1000, 1000, c);
		node.insert(511, 511, 1000, 1000, d);
		node.insert(511, 511, 1000, 1000, e);
		node.insert(511, 511, 1000, 1000, f);
		node.insert(511, 511, 1000, 1000, g);
		node.insert(511, 511, 1000, 1000, h);
		
		System.out.println(node.toString(511, 511, 1000, 1000, 0));
	}
}
