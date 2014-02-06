import java.util.LinkedList;


public class FairReadWriteLock {
	
	int numReaders = 0;
	int numWriters = 0;
	int reqWrite = 0;
	int reqRead = 0;
	int index = 0;
	
	LinkedList<Reader_Writer> rw = new LinkedList<Reader_Writer>();

	public synchronized void beginWrite() {
		reqWrite++;
		index++;
		Reader_Writer w = new Reader_Writer(index, "writer");
		rw.add(w);
		while(rw.peek().idx < w.idx){			//writer waits for all threads ahead of it to finish before writing
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
		reqWrite--;
		numWriters++;
		
	}

	public synchronized void endWrite() {
		numWriters--;
		rw.remove();
		notify();
		
	}

	public synchronized void beginRead() {
		
		Reader_Writer r = new Reader_Writer(index, "reader");
		rw.add(r);
		if((r.idx - rw.peek().idx) != 1 || rw.peek().type.equals("writer")){  //if thread right before is a reader then go ahead and read
		while(rw.peek().idx < r.idx){
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		}
		numReaders++;
	
	}

	public synchronized void endRead() {
		numReaders--;
		rw.remove();
		notify();
		
	}

}
