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
	
	Point a;
	Point a1;
	Point a2;
	Point a3;
	Point b;
	Point c;
	Point d;
	Point e;
	Point f;
	Point g;
	Point h;
	
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
		
		a = new Point("a", 10, 600);
		a1 = new Point("a1", 100, 600);
		a2 = new Point("a2", 100, 600);
		a3 = new Point("a3", 100, 600);
		b = new Point("b", 400, 600);
		c = new Point("c", 10, 900);
		d = new Point("d", 400, 900);
		e = new Point("e", 10, 300);
		f = new Point("f", 400, 300);
		g = new Point("g", 600, 300);
		h = new Point("h", 900, 300);
		
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
	public void testRegionsearch()
	{
		Point point1 = new Point("search1", 0, 0);
		Point point2 = new Point("search2", 40, 0);
		Point point3 = new Point("search3", 0, 40);
	
		assertTrue(node.insert(0, 0, 40, 40, point1));
		assertTrue(node.insert(0, 0, 40, 40, point2));
		assertTrue(node.insert(0, 0, 40, 40, point3));		
		
		DLinkedList<Point> list = new DLinkedList<Point>();
		int visited = node.regionsearch(list, 0, 0, 40, 40, 0, 0, 20, 20);
		assertEquals(list.size(), 1);
		assertEquals(visited, 5);
		
		//----------------------------------------------
		assertNotNull(node.remove(0, 0, 40, 40, point1));
		assertNotNull(node.remove(0, 0, 40, 40, point2));
		assertNotNull(node.remove(0, 0, 40, 40, point3));
		
		Point point4 = new Point("search1", 21, 0);
		Point point5 = new Point("search2", 20, 0);
		Point point6 = new Point("search3", 0, 20);
	
		assertTrue(node.insert(0, 0, 40, 40, point4));
		assertTrue(node.insert(0, 0, 40, 40, point5));
		assertTrue(node.insert(0, 0, 40, 40, point6));	
		
		DLinkedList<Point> list1 = new DLinkedList<Point>();
		int visited1 = node.regionsearch(list1, 0, 0, 40, 40, 0, 0, 20, 20);
		assertEquals(list1.size(), 2);
		assertEquals(visited1, 5);
		
		//----------------------------------------------
		assertNotNull(node.remove(0, 0, 40, 40, point4));
		assertNotNull(node.remove(0, 0, 40, 40, point5));
		assertNotNull(node.remove(0, 0, 40, 40, point6));
		
		Point point7 = new Point("search1", 0, 21);
		Point point8 = new Point("search2", 20, 20);
		Point point9 = new Point("search3", 21, 21);
	
		assertTrue(node.insert(0, 0, 40, 40, point7));
		assertTrue(node.insert(0, 0, 40, 40, point8));
		assertTrue(node.insert(0, 0, 40, 40, point9));	
		
		DLinkedList<Point> list2 = new DLinkedList<Point>();
		int visited2 = node.regionsearch(list2, 0, 0, 40, 40, 0, 0, 20, 20);
		assertEquals(list2.size(), 1);
		assertEquals(visited2, 5);
				
		
		
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
		
		DLinkedList<Point> list3 = new DLinkedList<Point>();
		assertEquals(node.regionsearch(list3, 511, 511, 1000, 1000, -2, -2, 700, 1100), 17);
		assertEquals(list3.size(), 13);

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
