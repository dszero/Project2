import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Database will parse the inpur file and receive the commands 
 * and call functions from both BST and Quadtree 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class Database 
{
	private File file;
	private PRQuadTree<Point> quadtree;
	private BST<Point> bst;

	/**
	 * initialize the constructor of the database
	 * @param file will be passed in and parsed
	 */
	public Database(File file, PRQuadTree<Point> quadtree, BST<Point> bst)
	{
		this.file = file;		
		this.quadtree = quadtree;
		this.bst = bst;
	}
	
	/**
	 * parse file and execute all the valid commands in the files
	 * @throws FileNotFoundException if file is not found 
	 */
	public void parse() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine())
		{
			String[] line = parseLine(scanner.nextLine());
			
			//check if the array is null, if 
			if (line != null)
			{
				if (line[0].equals("insert"))//Point Rejected: (r_r, -1, -20)
				{
					String name = line[1];
					int x = Integer.parseInt(line[2]);
					int y = Integer.parseInt(line[3]);
					insertion(name, x, y);
				}
				else if (line[0].equals("duplicates"))
				{
					duplicates();			

				}
				else if (line[0].equals("dump"))
				{
					dump();
					
				}
				else if (line[0].equals("search"))//search by name
				{
					searchByName(line[1]);
				}
				else if (line[0].equals("remove") && line.length == 2)//remove by name
				{
					removeByName(line[1]);				
				}
				else if (line[0].equals("remove") && line.length == 3)
				{
					int x = Integer.parseInt(line[1]);
					int y = Integer.parseInt(line[2]);
					
					removeByCoordinates(x, y);
					
				}
				else //region search
				{
					int x = Integer.parseInt(line[1]);
					int y = Integer.parseInt(line[2]);
					int w = Integer.parseInt(line[3]);
					int h = Integer.parseInt(line[4]);
					regionSearch(x, y, w, h);
					
 				}
			}
		}
		scanner.close();
	}
	



	/**
	 * parse a line. if line is not empty,
	 * return string array contains all the words
	 * of the line
	 * @param line will be sent from scanner
	 * @return string array
	 */
	public String[] parseLine(String line)
	{
		String[] splitted = null;
		if (!line.trim().equals(""))
		{
			splitted = line.trim().split("\\s+");
		}
		return splitted;
	}
	
	/**
	 * insert a point to both bst and quadtree
	 * if it is a valid point object
	 * @param name of the point
	 * @param x coordinate of the point
	 * @param y coordinate of the point
	 */
	public void insertion(String name, int x, int y)
	{
		
		boolean isNumber = Character.isDigit(name.charAt(0));
		String pointInfo = "(" + name + ", " + x + ", " + y +")";
		String result = null;
		
		//check if inserted point has valid coordinates
		//System.out.println(quadtree.getXMax());
		if (!isNumber && x >= 0 && y >= 0 && x <= quadtree.getXMax() 
				&& y <=  quadtree.getYMax())
		{
			Point point = new Point(name, x, y);
			bst.insert(point);
			quadtree.insert(point);
			result = "Point Inserted: " + pointInfo;
		}		
		else//if it is a invalid point
		{
			result = "Point Rejected: " + pointInfo;
		}
		System.out.println(result);
	}
	
	/**
	 * print out a list of current bst 
	 */
	public void dump()
	{
		//bst dump
		System.out.println("BST dump:");
		int size = 0;
		if (!bst.isEmpty())
		{
			Iterator<BST<Point>.NodeInfo> bstIterator = bst.iterator(Order.IN);
			while (bstIterator.hasNext())
			{
				BST<Point>.NodeInfo info = bstIterator.next();
				Point point = info.element;
				int depth = info.depth;
				String dep = "Node has depth " + depth 
								+ ", Value (";
				String pointInfo = point.getName() + ", " 
									+ point.getX() + ", " 
									+ point.getY() + ")";
				System.out.println(dep + pointInfo);
				size++;
			}
		}
		else
		{
			System.out.println("Node has depth 0, Value (null)");
		}
		System.out.println("BST size is: " + size);
		
		//quad tree dump
		System.out.println("QuadTree Dump:");
		String quadtreeDump = quadtree.toString();
		System.out.println(quadtreeDump);
		System.out.println("QuadTree Size: " + (quadtreeDump.
								split("Node").length - 1) + 
							" QuadTree Nodes Printed.");
	
	}
	
	
	/**
	 * remove a point with a specific name
	 * @param name of a point
	 */
	public void removeByName(String name)
	{
		Point point = bst.find(name);
		if (point != null)
		{
			bst.remove(point);
			quadtree.remove(point);//make sure bst and quadtree remove the same point
		}
		else
		{
			System.out.println("Point Rejected: " + name);
		}	
	}
	
	/**
	 * remove a point with specific coordinates 
	 * from both bst and quadtree
	 * @param x coordinate of a point
	 * @param y coordinate of a point
	 */
	public void removeByCoordinates(int x, int y)
	{
		Point point = quadtree.remove(x, y);
		
		if (point != null)
		{
			bst.remove(point);//make sure bst and quadtree remove the same point
			
		}
		else
		{
			System.out.println("Point Rejected: (" 
								+ x + ", " + y + ")");
		}
	}
	
	/**
	 * search point objects in that specific region
	 * @param x coordinate of the region
	 * @param y coordinate of the region
	 * @param w width of the region
	 * @param h height of the region
	 */
	public void regionSearch(int x, int y, int w, int h)
	{
		DLinkedList<Point> list = new DLinkedList<Point>();
		int visitedNode = quadtree.regionsearch(list, x, y, w, h);
		if (w < 0 || h < 0) {
			System.out.println("Invalid Region: (" + x
					+ ", " + y + ", " + w + ", " + h + ")");
			return;
		}
		System.out.println("Points Intersecting Region: (" + x
							+ ", " + y + ", " + w + ", " + h + ")");
		if (list.size() != 0)
		{
			Iterator<Point> iterator = list.iterator();
			while (iterator.hasNext())
			{
				Point point = iterator.next();
				System.out.println("(" + point.getName() + ", " 
									+ point.getX() + ", " 
				                     + point.getY() + ")");  
			}
		}
		System.out.println(visitedNode + " QuadTree Nodes Visited");
	}
	
	
	/**
	 * search point by name
	 * @param name of the points
	 */
	public void searchByName(String name)
	{
		int size = 0;		
		
		Iterator<BST<Point>.NodeInfo> bstIterator = bst.iterator(Order.IN);
		while (bstIterator.hasNext())
		{
			BST<Point>.NodeInfo info = bstIterator.next();
			Point point = info.element;
			if (point.getName().equals(name))
			{
				String pointInfo = "(" + name + ", " + point.getX() + ", "
									+ point.getY() + ")";
				System.out.println("Point Found: " + pointInfo);
				size++;
			}
		}
		
		
		if (size == 0)
		{
			System.out.println("Point Not Found: " + name);
		}
		
	}
	
	/**
	 * check for duplicates, and print out all the duplicates points
	 */
	public void duplicates()
	{
		DLinkedList<Point> list = quadtree.duplicates(); //Duplicate Points:
		System.out.println("Duplicate Points:");
		Iterator<Point> iterator = list.iterator();
		while (iterator.hasNext())
		{
			Point point = iterator.next();
			System.out.println("(" + point.getX() + ", " 
			                     + point.getY() + ")");  
		}	
	}
}















