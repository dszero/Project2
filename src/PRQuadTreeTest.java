import junit.framework.TestCase;

public class PRQuadTreeTest extends TestCase {
	
	private PRQuadTree<Point> tree;
	
	private Point point1;
	
	private Point point2a;
	private Point point2b;
	private Point point2c;
	private Point point2d;
	
	private Point point3;
	private Point point4;
	
	private Point point5;
	
	/**
	 * initialize the tree and points
	 */
	public void setUp() {
		tree = new PRQuadTree<Point>(0, 1024, 0, 1024);
		
		point1 = new Point("p1", 20, 30);
		
		point2a = new Point("p2a", 50, 640);
		point2b = new Point("p2b", 50, 640);
		point2c = new Point("p2c", 50, 640);
		point2d = new Point("p2d", 50, 640);
		
		point3 = new Point("p3", 200, 60);
		point4 = new Point("p4", 235, 2);
		
		point5 = new Point("p5", 25, 29);
	}
	
	/**
	 * test the inesrt method
	 */
	public void testInsert() {
		//insert null (false)
		assertFalse(tree.insert((Point) null));
		
		//insert valid point as head
		assertTrue(tree.insert(point1));
		
		//insert valid point (convert leaf -> internal node)
		assertTrue(tree.insert(point2a));
		
		//insert 3 valid point with same coord (without decomp)
		assertTrue(tree.insert(point2b));
		assertTrue(tree.insert(point2c));
		assertTrue(tree.insert(point2d));
		
		//test duplicates
		assertFalse(tree.insert(point1));
		assertFalse(tree.insert(point2c));

		//insert valid point with diff coord (decomp)
		assertTrue(tree.insert(point3));
		
		//insert valid point (internal node insertion) 
		assertTrue(tree.insert(point4));
	}
	
	/**
	 * test the remove method with a point
	 */
	public void testRemove() {
		tree.insert((Point) null);
		assertNull(tree.remove((Point) null));
		
		tree.insert(point1);
		assertNull(tree.remove(point3));
		assertNotNull(tree.remove(point1));
		tree.insert(point1);
		
		tree.insert(point2a);
		assertNull(tree.remove(point3));
		assertNotNull(tree.remove(point1));
		tree.insert(point1);
		assertNotNull(tree.remove(point2a));
		tree.insert(point2a);
		
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		assertNotNull(tree.remove(point2c));
		tree.insert(point2c);
		
		tree.insert(point1);
		assertNotNull(tree.remove(point1));
		assertNull(tree.remove(point1));
		tree.insert(point2c);
		
		tree.insert(point3);
		tree.insert(point4);
		
		assertNotNull(tree.remove(point3));
		assertNotNull(tree.remove(point4));
	}
	
	/**
	 * test the remove method with x and y coordinates
	 */
	public void testRemoveXY() {
		tree.insert((Point) null);
		assertNull(tree.remove(1000000000, 100000000));
		
		tree.insert(point1);
		assertNull(tree.remove(200, 60));
		assertNotNull(tree.remove(20, 30));
		tree.insert(point1);
		
		tree.insert(point2a);
		assertNull(tree.remove(200, 60));
		assertNotNull(tree.remove(20, 30));
		tree.insert(point1);
		assertNotNull(tree.remove(50, 640));
		tree.insert(point2a);
		
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		assertNotNull(tree.remove(50, 640));
		assertNotNull(tree.remove(50, 640));
		tree.insert(point2c);
		
		tree.insert(point1);
		assertNotNull(tree.remove(20, 30));
		assertNull(tree.remove(20, 30));
		tree.insert(point2c);
		
		tree.insert(point3);
		tree.insert(point4);
		
		assertNotNull(tree.remove(200, 60));
		assertNotNull(tree.remove(235, 2));
	}
	
	/**
	 * test the find method
	 */
	public void testFind() {
		tree.insert((Point) null);
		assertNull(tree.find((Point) null));
		
		tree.insert(point1);
		assertNull(tree.find(point3));
		assertEquals(tree.find(point1), point1);
		tree.insert(point1);
		
		tree.insert(point2a);
		assertNull(tree.find(point3));
		assertEquals(tree.find(point1), point1);
		tree.insert(point1);
		assertEquals(tree.find(point2a), point2a);
		tree.insert(point2a);
		
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		assertEquals(tree.find(point2c), point2c);
		tree.insert(point2c);
		
		tree.insert(point1);
		assertEquals(tree.find(point1), point1);
		assertEquals(tree.find(point1), point1);
		tree.insert(point2c);
		
		tree.insert(point3);
		tree.insert(point4);
		
		assertEquals(tree.find(point3), point3);
		assertEquals(tree.find(point4), point4);
		assertNull(tree.find(point5));
		
		//test after removes
		tree.remove(point1);
		tree.remove(point2a);
		tree.remove(point3);
		assertNull(tree.find(point1));
		assertNull(tree.find(point2a));
		assertEquals(tree.find(point2c), point2c);
		assertEquals(tree.find(point4), point4);
	}
	
	/**
	 * test the clear method
	 */
	public void testClear() {
		tree.insert((Point) null);		
		tree.insert(point1);
		tree.insert(point1);
		tree.insert(point2a);
		tree.insert(point1);
		tree.insert(point2a);
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		tree.insert(point2c);
		tree.insert(point1);
		tree.insert(point2c);
		tree.insert(point3);
		tree.insert(point4);	
		System.out.println(tree.toString());
		
		tree.clear();
		assertNull(tree.find(point1));
		assertNull(tree.find(point2a));
		assertNull(tree.find(point2c));
		assertNull(tree.find(point4));
	}
}
