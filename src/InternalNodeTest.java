import junit.framework.TestCase;

public class InternalNodeTest extends TestCase {
	
	InternalNode<Point> node;
	Point pNull;
	Point pMid;
	Point pNW;
	Point pNE;
	Point pSW;
	Point pSE;
	
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
		
	}
	
	/**
	 * Test the insert method
	 */
	public void testInsert() {
		assertFalse(node.insert(511, 511, pNull));
		assertTrue(node.insert(511, 511, pMid));
		assertFalse(node.insert(511, 511, pMid));
	}
	
	/**
	 * Test the remove method by object
	 */
	public void testRemoveObj() {
		//test remove on empty node
		assertFalse(node.remove(511, 511, pNull));
		assertFalse(node.remove(511, 511, pNE));
		
		//test remove on failed insert
		assertFalse(node.insert(511, 511, pNull));
		assertFalse(node.remove(511, 511, pNull));
		
		//Test remove on node
		assertTrue(node.insert(511, 511, pMid));
		assertTrue(node.remove(511, 511, pMid));
		
		//Test insert after remove
		assertTrue(node.insert(511, 511, pMid));
		
		//Test double insert
		assertFalse(node.insert(511, 511, pMid));
		assertTrue(node.remove(511, 511, pMid));
		assertFalse(node.remove(511, 511, pMid));
	}
	
	/**
	 * Test the insert method by coordinates
	 */
	public void testRemoveXY() {
		//Test remove on empty node
		assertFalse(node.remove(511, 511, 2000, 2000));
		assertFalse(node.remove(511, 511, 510, 520));
		
		//Test remove on node
		assertTrue(node.insert(511, 511, pMid));
		assertTrue(node.remove(511, 511, 511, 511));
		
		//Test insert after remove
		assertTrue(node.insert(511, 511, pMid));
		
		//Test double insert
		assertFalse(node.insert(511, 511, pMid));
		assertTrue(node.remove(511, 511, 511, 511));
		assertFalse(node.remove(511, 511, 511, 511));
	}
	
	/**
	 * Test the regionsearch method
	 */
	public void testRegionsearch() {
		
	}

	/**
	 * Test the duplicates method
	 */
	public void testDuplicates() {
		
	}
	
	/**
	 * Test the isLeaf method
	 */
	public void testIsLeaf() {
		
	}
}
