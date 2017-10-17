/**
 * 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class InternalNode< T extends Comparable2D<? super T> > extends QuadTreeNode<T> {
	
	
	QuadTreeNode<T> NW;
	QuadTreeNode<T> NE;
	QuadTreeNode<T> SW;
	QuadTreeNode<T> SE;
	
	public InternalNode() {
		NW = new FlyWeight<T>();
		NE = new FlyWeight<T>();
		SW = new FlyWeight<T>();
		SE = new FlyWeight<T>();
	};

		

	public boolean insert(Point point) {
		return false;	
	}
	
	

}
