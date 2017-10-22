import java.util.Iterator;

import junit.framework.TestCase;

public class DLinkedListTest extends TestCase{
	/**
	 * initialize variables
	 */
	private DLinkedList<String> list;
//	private Point point1;
//	private Point point2;
//	private Point point3;
//	private Point point4;

	/**
	 * setup before testing
	 */
	public void setUp()
	{
		list = new DLinkedList<String>();
//		point1 = new Point("Jim", 4, 6);
//		point2 = new Point("Jack", 6, 9);
//		point3 = new Point("kick", 4, 6);
//		point4 = new Point("Yeah", 12, 3);		
	}
	
	/**
	 * test the add method
	 */
	public void testAdd() {
		assertFalse(list.add((String) null));
		assertTrue(list.add("test"));
	}
	
	/**
	 * test the size method
	 */
	public void testLength() {
		assertEquals(list.size(), 0);
		list.add("test");
		assertEquals(list.size(), 1);
	}
	
	/**
	 *  test the remove method
	 */
	public void testRemove() {
		assertNull(list.remove("test"));
		
		list.add("test");
		assertNull(list.remove("test1"));
		assertNotNull(list.remove("test"));
		assertEquals(list.size(), 0);
		
		list.add("test1");
		list.add("test2");
		list.add("test3");
		assertNotNull(list.remove("test2"));
		assertEquals(list.size(), 2);
		
		list.add("test4");
		assertNotNull(list.remove("test4"));
		assertEquals(list.size(), 2);
		
	}
	
	/**
	 * test the get method
	 */
	public void testGet() {
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		assertEquals(list.get(0), "test1");
		assertEquals(list.get(1), "test2");
		assertNull(list.get(3));
		assertEquals(list.get(2), "test3");
		
	}
	
	/**
	 * test the iterator method
	 */
	public void testIterator() {
		list.add("test1");
		list.add("test2");
		list.add("test3");

		int i = 0;
		for(String item : list) {
			assertEquals(item, list.get(i));
			i++;
		}
		assertEquals(list.size(), i);
	}
	
	/**
	 * test the addAll method
	 */
	public void testAddAll() {
		DLinkedList<String> list1 = new DLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		list1.add("test4");
		list1.add("test5");
		list1.add("test6");
		
		list.addAll(list1);
		
		assertEquals(list.get(0), "test1");
		assertEquals(list.get(1), "test2");
		assertEquals(list.get(2), "test3");
		assertEquals(list.get(3), "test4");
		assertEquals(list.get(4), "test5");
		assertEquals(list.get(5), "test6");
	}
	
	/**
	 * test for adding and removing methods
	 */
//	public void testForAddingAndRemoving()
//	{
//		//three different points
//		assertTrue(list.add(point1));
//		list.add(point2);
//		list.add(point3);
//		assertFalse(list.add(point1));
//		assertFalse(list.add(point4));
//		assertFalse(list.getIdenticalPoints());
//		assertEquals(3, list.getLIstSize());
//		//remove with x = 4, y = 6
//		assertTrue(list.remove(4, 6));
//		assertEquals(2, list.getLIstSize());
//		//remove with name "Jack"
//		assertFalse(list.remove("Kick"));
//		assertTrue(list.remove("Jack"));
//		assertEquals(1, list.getLIstSize());
//		
//		//five identical points 
//		list1.add(point1);
//		list1.add(point1);
//		list1.add(point1);
//		list1.add(point1);
//		list1.add(point1);
//		assertFalse(list1.add(point4));
//		assertTrue(list1.add(point1));
//		assertEquals(6, list1.getLIstSize());
//		assertTrue(list1.getIdenticalPoints());
//		//remove wirh name "Jim"
//		assertTrue(list1.remove("Jim"));
//		assertEquals(0, list1.getLIstSize());
//		
//		//four location-identical and one distinct points
//		list2.add(point1);
//		assertTrue(list2.add(point3));
//		list2.add(point1);
//		assertFalse(list2.add(point2));
//		assertTrue(list2.getIdenticalPoints());
//		list2.add(point3);
//		assertEquals(4, list2.getLIstSize());
//		//remove nodes with x = 4 y = 6 
//		assertFalse(list2.remove(3, 5));
//		assertTrue(list2.remove(4, 6));
//		assertEquals(0, list2.getLIstSize());
//		
//		
//		
//		Node current = list1.head.next();
//		for (int i = 0; i < list1.getLIstSize(); i++)
//		{
//			System.out.println(current.point().getName());
//			current = current.next();
//		}
//		
//		
//	}


	

}
