import junit.framework.TestCase;

public class PRQuadTreeTest extends TestCase {
	
	PRQuadTree<Point> tree;
	
	public void setUp() {
		tree = new PRQuadTree<Point>(0, 0, 1024, 1024);
	}
	
	public void testInsert() {
		//insert null (false)
		
		//insert valid point as head
		
		//insert valid point (convert leaf -> internal node)
		
		//insert valid point with same coord (without decomp)
		//insert valid point with same coord (without decomp)
		//insert valid point with same coord (without decomp)
		
		//insert valid point with diff coord (decomp)
		
		//insert valid point (internal node insertion) 
	}
	
	public void testRemove() {
		
	}
	
	public void testFind() {
		
	}
	
	public void testClear() {
		
	}
}
