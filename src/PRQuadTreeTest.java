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
	
	public void testRemove() {
		tree.insert((Point) null);
		assertFalse(tree.remove((Point) null));
		
		tree.insert(point1);
		assertFalse(tree.remove(point3));
		assertTrue(tree.remove(point1));
		tree.insert(point1);
		
		tree.insert(point2a);
		assertFalse(tree.remove(point3));
		assertTrue(tree.remove(point1));
		tree.insert(point1);
		assertTrue(tree.remove(point2a));
		tree.insert(point2a);
		
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		assertTrue(tree.remove(point2c));
		tree.insert(point2c);
		
		tree.insert(point1);
		assertTrue(tree.remove(point1));
		assertFalse(tree.remove(point1));
		tree.insert(point2c);
		
		tree.insert(point3);
		tree.insert(point4);
		
		assertTrue(tree.remove(point3));
		assertTrue(tree.remove(point4));
	}
	
	public void testRemoveXY() {
		tree.insert((Point) null);
		assertFalse(tree.remove(1000000000, 100000000));
		
		tree.insert(point1);
		assertFalse(tree.remove(200, 60));
		assertTrue(tree.remove(20, 30));
		tree.insert(point1);
		
		tree.insert(point2a);
		assertFalse(tree.remove(200, 60));
		assertTrue(tree.remove(20, 30));
		tree.insert(point1);
		assertTrue(tree.remove(50, 640));
		tree.insert(point2a);
		
		tree.insert(point2b);
		tree.insert(point2c);
		tree.insert(point2d);
		assertTrue(tree.remove(50, 640));
		assertTrue(tree.remove(50, 640));
		tree.insert(point2c);
		
		tree.insert(point1);
		assertTrue(tree.remove(20, 30));
		assertFalse(tree.remove(20, 30));
		tree.insert(point2c);
		
		tree.insert(point3);
		tree.insert(point4);
		
		assertTrue(tree.remove(200, 60));
		assertTrue(tree.remove(235, 2));
	}
	
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
		
		tree.clear();
		assertNull(tree.find(point1));
		assertNull(tree.find(point2a));
		assertNull(tree.find(point2c));
		assertNull(tree.find(point4));
	}
}
