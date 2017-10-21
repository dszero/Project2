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
	
	public void setUp() {
		tree = new PRQuadTree<Point>(0, 0, 1024, 1024);
		
		point1 = new Point("p1", 20, 30);
		
		point2a = new Point("p2a", 50, 640);
		point2b = new Point("p2b", 50, 640);
		point2c = new Point("p2c", 50, 640);
		point2d = new Point("p2d", 50, 640);
		
		point3 = new Point("p3", 200, 60);
		point4 = new Point("p4", 235, 2);
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
		
	}
	
	public void testFind() {
		
	}
	
	public void testClear() {
		
	}
}
