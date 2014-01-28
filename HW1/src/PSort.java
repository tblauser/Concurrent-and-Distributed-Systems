import java.util.Arrays;


public class PSort implements Runnable{
	int[] numbs;
	int start;
	int finish;
	
	public PSort(int[] x, int start, int finish){
		this.numbs = x;
		this.start = start;
		this.finish = finish;
	}
	
		public static void parallelSort(int[] A, int begin, int end){
			int center = (begin+end)/2;
			
			if(begin < end){
			
				PSort p1 = new PSort(A, begin, center);
				PSort p2 = new PSort(A, center+1, end);
				
				Thread t1 = new Thread(p1);
				Thread t2 = new Thread(p2);
				
				t1.start();
				t2.start();
				
				try{
					t1.join();
					t2.join();
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				
				merge(A, begin, center+1, end);
			
			}
			
			System.out.println("Array so far is:" + Arrays.toString(A));
			
		}
		
		public static void merge(int[] a, int start, int middle, int finish){
			int buffer[] = new int[a.length];
			
			int smiddle = middle-1;
			int index = start;
			int s = start;
			int m = middle;
			
			while(s <= smiddle && m <= finish){
				if(a[s] <= a[m]){
					buffer[index++] = a[s++];				//left side is less than or equal to right so keep same order
					
				}
				else{
					buffer[index++] = a[m++];				//put right side number where left side number was in the buffer
				
				}
			}
			
			while(s <= smiddle){				//copy leftovers from right or left depending on which finished first
				buffer[index++] = a[s++];
			}
			
			while(m <= finish){
				buffer[index++] = a[m++];
			}
			
			for(int x=start; x <= finish; x++){		//copy new values to a
				a[x] = buffer[x];
			}
			
		
		
		}
		
		public static void main(String args[]){
			int[] test = new int[]{3, 5, 2, 8, 5, 10, 6, 1};
			parallelSort(test, 0, test.length-1);
			
			
		}
		
		public void run(){
			parallelSort(numbs, start, finish);
		}

		
}
