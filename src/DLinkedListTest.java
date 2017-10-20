import student.TestCase;

public class DLinkedListTest extends TestCase{
	
	private DLinkedList list;
	private DLinkedList list1;
	private Point point1;
	private Point point2;
	private Point point3;
	private Point point4;
	
	public void setUp()
	{
		list = new DLinkedList();
		list1 = new DLinkedList();
		point1 = new Point("Jim", 4, 6);
		point2 = new Point("Jack", 6, 9);
		point3 = new Point("kick", 4, 6);
		point4 = new Point("Yeah", 12, 3);
		
	}
	
	/**
	 * check add method
	 */
	public void testAdd()
	{
		//assertEquals(list.getLIstSize(), 0);
		//empty list
		assertTrue(list.add(point1));
		assertEquals("(Jim, 4, 6)", list.toString());
		//non-empty list
		assertTrue(list.add(point2));
		assertEquals(2, list.getLIstSize());
		
		assertTrue(list.add(point2));
		assertFalse(list.add(point2));
		
		assertTrue(list1.add(point1));
		assertTrue(list1.add(point2));
		assertTrue(list1.add(point3));
		assertFalse(list1.add(point4));
		assertFalse(list.getIdenticalPoints());
		
		
		
	}

	

}
