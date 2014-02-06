//Author: Trey Blauser and Shashank Desai
//Date: 1/27/2014
//Description: A multithreaded mergesort implementation
//NOTE: there was no requirement for returning the completed array so I have left the method with
//the exact same parameters as the code on the assignment sheet. There is a print function to see
//what the array looks like during the duration of the program



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
			
			if(begin != end){
			
				PSort p1 = new PSort(A, begin, center);
				PSort p2 = new PSort(A, center+1, end);
				
				Thread t1 = new Thread(p1);				//create 2 new child threads using p1 and p2 in order											
				Thread t2 = new Thread(p2);				//to solve the two subarrays created
				
				t1.start();
				t2.start();
				
				try{
					t1.join();
					t2.join();
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				
				merge(A, begin, center+1, end);			//parent thread merges after the two child threads are finished
			
			}
			
			//System.out.println("Array so far is:" + Arrays.toString(A));
			
		}
		
		public static void merge(int[] a, int start, int middle, int finish){ //typical merging method
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
			int[] converted = new int[args.length];
			for(int i =0; i<args.length; i++){
				converted[i] = Integer.parseInt(args[i]);
			}
			
			
			parallelSort(converted, 0, converted.length-1);
			
			
		}
		
		public void run(){
			parallelSort(numbs, start, finish);
		}

		
}
