import java.io.File;

import junit.framework.TestCase;

/**
 * This is the junit testcase for database class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017 *
 */

public class DatabaseTest extends TestCase{
	
	
	private Database database;
	private BST<Point> bst;
	private PRQuadTree<Point> quadtree;
	private File file;
	
	/**
	 * set up variables 
	 */
	public void setUp()
	{
		quadtree = new PRQuadTree<Point>(0, 32, 0, 32);
		bst = new BST<Point>();
		database = new Database(file, quadtree, bst);
	}
	
	/**
	 * test parse line function
	 */
	public void testLineSplitting()
	{
		String empty = "                  	";
		String[] emptyArray = database.parseLine(empty);
		assertEquals(emptyArray, null);
		
		String insert = "insert r_r          -1 -20";
		String[] insertArray = database.parseLine(insert);
		assertEquals(insertArray.length, 4);
		assertEquals(insertArray[0], "insert");
		assertEquals(insertArray[1], "r_r");
		assertEquals(insertArray[2], "-1");
		assertEquals(insertArray[3], "-20");
	}
	
	/**
	 * test insertion
	 */
	public void testInsertion()
	{
		
		database.insertion("cool", 4, 5);
		database.insertion("nice", 6, 4);
		database.insertion("cool1", -3, 5);
		database.insertion("cool2", 3, 32);
		database.insertion("cool3", 32, 5);
		database.insertion("nice", 0,  0);
		//database.bstDump();
		assertEquals(bst.getSize(), 5);
		assertEquals(quadtree.getSize(), 5);
		
		database.insertion("cool", 4, 5);
		database.dump();
		assertEquals(bst.getSize(), 6);
		assertEquals(quadtree.getSize(), 6);
		
		//---------------------------------
		database.insertion("nice", 0,  0);
		database.insertion("nice", 33,  33); //false
		database.insertion("nice", 0,  33); //false
		database.insertion("nice", 33,  0); //false
		database.insertion("nice", 0,  32);
		database.insertion("nice", 32,  0);
		database.insertion("nice", 32,  32);
		assertEquals(bst.getSize(), 10);
		assertEquals(quadtree.getSize(), 10);
		//------------------------------------
		database.dump();
		database.insertion("1nice", 0,  0);
		
	}
	
	/**
	 * test remove by name function
	 */
	public void testRemoveByName()
	{
		database.insertion("Jim", 5, 7);
		database.insertion("Joe", 6, 7);
		database.insertion("Kil", 10, 9);
		database.insertion("Jim", 5, 7);
		database.insertion("name", 5, 11);
		System.out.println("//-------------------------------------");
		database.dump();
		System.out.println("//-------------------------------------");
		database.removeByName("Jim");
		database.dump();
		assertEquals(bst.getSize(), 4);
		assertEquals(quadtree.getSize(), 4);
	}
	
	/**
	 * test remove by coordinates
	 */
	public void testRemoveByCoor()
	{
		database.insertion("Jim", 5, 7);
		database.insertion("Joe", 6, 7);
		database.insertion("Kil", 10, 9);
		database.insertion("Ji", 5, 7);
		
		database.removeByCoordinates(5, 7);
		database.dump();
		
		assertEquals(bst.getSize(), 3);
		assertEquals(quadtree.getSize(), 3);
		
	}
	
	/**
	 * test region search method
	 */
	public void testRegionSearch()
	{
		database.insertion("Coke",  0, 0);
		database.insertion("Coke1",  10, 24);
		database.insertion("Coke2",  7, 2);
		database.insertion("Coke3",  1, 0);
		database.insertion("Coke4",  5, 6);
		database.insertion("Coke5",  8, 2);
		
		database.regionSearch(0, 0, 8, 2);
		database.dump();
		assertEquals(bst.getSize(), 6);
		
	}
	
	/**
	 * test search by name
	 */
	public void testSearchByName()
	{
		database.dump();
		database.searchByName("Jim");
		database.insertion("Jim", 5, 7);
		database.insertion("Joe", 6, 7);
		database.insertion("Kil", 10, 9);
		database.insertion("Jim", 10, 9);
		database.insertion("Jim", 5, 7);
		database.searchByName("Jim");
		database.searchByName("pp");
		assertEquals(5, quadtree.getSize());
		
		database.dump();
	}

	public void testDuplicates()
	{
		//database.duplicates();
		
		database.insertion("a", 5, 6); //
		database.insertion("b", 7, 6); //--
		database.insertion("c", 5, 6); //
		database.insertion("d", 9, 6);
		database.insertion("b", 7, 6); //--
		database.insertion("f", 0, 6);		
		database.insertion("g", 1, 6);
		database.insertion("h", 1, 6);
		System.out.println("//-------------------------------------");
		database.dump();
		System.out.println("//-------------------------------------");
		database.duplicates();
		System.out.println("//-------------------------------------");
		database.dump();
		System.out.println("//-------------------------------------");
		assertEquals(bst.getSize(), 8);
		assertEquals(quadtree.getSize(), 8);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
