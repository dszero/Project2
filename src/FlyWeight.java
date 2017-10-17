/**
 * This is the flyweight class, which is single empty leaf node 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class FlyWeight< T extends Comparable2D<? super T> > extends QuadTreeNode<T> {
	@Override
	boolean remove(T obj) {
		return false;
	}
	
	
}
