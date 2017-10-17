/**
 * This is the point object class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class Point {
	/**
	 * initialize the variables
	 */
	private String name;
	private int x;
	private int y;
	/**
	 * initialize the point constructor
	 * @param name of the point object
	 * @param x coordinate of  the point object
	 * @param y coordinate of the point object
	 */
	public Point(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * get the name of the point
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * get the x coordinate of the point
	 * @return x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * get the y coordinate of the point
	 * @return y
	 */
	public int getY()
	{
		return y;
	}

}










