import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;




public class Deque {
	
	public int capacity;
	final ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private LinkedList<Integer> deque = new LinkedList<Integer>();
	final Iterator<Integer> i = deque.iterator();
	
	public Deque(int capacity){
		this.capacity = capacity;
	}
	
	
	public int getHead(){
	lock.lock();
	
	try{
		while(deque.isEmpty()){
			notEmpty.await();
		}
		return deque.peekFirst();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		lock.unlock();
	}
		
		return 0;
	}
	
	public void insertHead(int val){
		lock.lock();
		
		try{
			while(deque.size() >= capacity){
				notFull.await();
			}
			deque.addFirst(val);
			notEmpty.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
		
	}
	
	public int deleteHead(){
		
		lock.lock();
		int x =0;
		try{
			while(deque.isEmpty()){
				notEmpty.await();
			}
			
			 x = deque.removeFirst();
			notFull.signal();
			
			   
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return x;
	}
	
	public int getTail(){
		lock.lock();
		int x =0;
		try{
			while(deque.isEmpty()){
				notEmpty.await();
			}
			x = deque.peekLast();
			//return deque.peekLast();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		return x;
	}
	
	public void insertTail(int val){
		lock.lock();
		
		try{
			while(deque.size() >= capacity){
				notFull.await();
			}
			deque.add(val);
			notEmpty.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public int deleteTail(){
		lock.lock();
		int x =0;
		try{
			while(deque.isEmpty()){
				notEmpty.await();
			}
			x = deque.removeLast();
			notFull.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return x;
	}
	
	public String toString(){
		String q = "[";
		int y = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
//		while(i.hasNext()){
//			x = i.next();
//			q.concat(" ");
//			q.concat(Integer.toString(x));
//		}
//		q.concat("]");
		for(int x = 0; x < deque.size(); x++){
			y = deque.get(x);
			sb.append(" ");
			sb.append(Integer.toString(y));
		}
		sb.append(" ]");
		q = sb.toString();
		return q;
	}
	
	
}
