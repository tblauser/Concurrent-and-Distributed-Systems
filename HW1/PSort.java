import java.util.Arrays;


public class PSort implements Runnable{
	int[] numbs;
	public PSort(int[] x){
		this.numbs = x;
	}
		public static void parallelSort(int[] A, int begin, int end){
			int[] first_half = new int[A.length/2];
			int[] second_half = new int[A.length/2];
			int center = (begin+end)/2;
			
			if(begin == end){
			
				PSort p1 = new PSort(A);
				PSort p2 = new PSort(A);
				
				Thread t1 = new Thread(p1);
				Thread t2 = new Thread(p2);
				
				
			
			}
			
		}
		
		public void main(int args[]){
			parallelSort(args[], 0, args.length);
			
			
		}
		
		public void run(){
			parallelSort(numbs, 0, numbs.length);
		}

		
}
