/**
 * This is the point object class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class Point implements Comparable2D<Point> {
	/**
	 * initialize the variables
	 */
	private String name;
	private int x;
	private int y;
	/**
	 * initialize the point constructor
	 * @param name of the point object
	 * @param newX coordinate of  the point object
	 * @param newY coordinate of the point object
	 */
	public Point(String name, int newX, int newY)
	{
		this.name = name;
		this.x = newX;
		this.y = newY;
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
	
	public boolean equals(Object o) {
		if (o != null && o instanceof Point) {
			Point oP = (Point) o;
			return name == oP.getName() && this.equals2D(oP);
		}
		return false;
	}

	@Override
	public int compareTo(Point o) {
		return name.compareTo(o.getName());
	}

	/**
	 * Find the direction of this node from a given point
	 * 
	 * @param o - point to use as src
	 * @return Direction of this node from o
	 */
	@Override
	public Direction compare2D(Point o) {
		if (o.compareY(y) < 0) {
			if (o.compareX(x) > 0) {
				return Direction.SW;
			}
			else { 
				return Direction.SE;
			}
		}
		if (o.compareY(y) > 0) { 
			if (o.compareX(x) > 0) { 
				return Direction.NW;
			}
			else {
				return Direction.NE;
			}
		}
		if (o.compareX(x) > 0) {
			return Direction.SW;
		}
		return Direction.NE;
	}

	/**
	 * Compare the x coordinate of this to the given x
	 * 
	 * @param otherX - x to compare to
	 * @return this x coordinate - otherX
	 */
	@Override
	public int compareX(int otherX) {
		return x - otherX;
	}

	/**
	 * Compare the y coordinate of this to the given y
	 * 
	 * @param otherY - y to compare to
	 * @return this y coordinate - otherY
	 */
	@Override
	public int compareY(int otherY) {
		return y - otherY;
	}
	
	/**
	 * Find the direction of this node from a given point
	 * 
	 * @param otherX - x coordinate to use as src
	 * @param otherY - y coordinate to use as src
	 * @return Direction of this node from (otherX, otherY)
	 */
	@Override
	public Direction compare2D(int otherX, int otherY) {
		if (y > otherY) {
			if (x < otherX) {
				return Direction.SW;
			}
			return Direction.SE;
		}
		if (y < otherY) { 
			if (x < otherX) { 
				return Direction.NW;
			}
			return Direction.NE;
		}
		if (x < otherX) {
			return Direction.SW;
		}
		return Direction.NE;
	}

	/**
	 * determin if this location is equal to the given objects location
	 * 
	 * @param o - obj to compare to
	 * @return true if at the same location, false if not
	 */
	@Override
	public boolean equals2D(Point o) {
		return o.compareX(x) == 0 && o.compareY(y) == 0;
	}
	
	/**
	 * get a string representation of this point
	 * 
	 * @return (name, x, y)
	 */
	public String toString() {
		return "(" + name + ", " + x + ", " + y + ")";
	}

}










