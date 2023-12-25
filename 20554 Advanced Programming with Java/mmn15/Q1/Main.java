import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	private static LinkedList<Integer> nList =  new LinkedList<Integer>();

	public static void main(String[] args) {
		
		

		boolean isRun = true;
		int n = 0, m = 0;
		while(isRun) {
			try {
				System.out.println("Hello, plaese insert the first number represent n");
				n = scanInt();
				System.out.println("Plaese insert secon number represent m");
				m = scanInt();
				isRun = false;
			}
			catch(Exception  e){
				System.out.println("Please insert valid integer");
			}
		}
		
		//randomize integers array size n 
		System.out.println("n: " + n + " m: "+m);
		Integer[] randomArr = randomizArr(n);
		Collections.addAll(nList, randomArr); 
		for (int i:nList) {
			System.out.println(i);
		}
		
		//start threads handling
		ExecutorService executor = Executors.newFixedThreadPool(m);
		while (nList.size()>=2) {
			try{
				
				executor.execute(new SummerT());                
				
			}catch(Exception err){
				err.printStackTrace();
			}
		}


		//wait and end threads
		awaitTerminationAfterShutdown(executor);

		//print result 
		System.out.println("sum list: " + nList.getFirst());
	}


	//return random integers array size n 
	private static Integer[] randomizArr(int n) {
		Integer[] res = new Integer[n];
		for (int i=0; i<n; i++) {
			res[i] = (int)(Math.random()*100);
		}
		return res;
	}

	//scan int input from the user
	public static int scanInt(){

		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt(); 
//		scan.close();
		return x;
	}

	//summarize two numbers and append the result to the and of linked list 
	public synchronized static void sum() {

		System.out.println("nList.size() in sum OUT: "+ nList.size());
		if (nList.size()>=2) {
			System.out.println("nList.size() in sum IIINNN: "+ nList.size());
			int temp1 = nList.poll();
			int temp2 = nList.poll();
			int res = temp1+temp2;
			nList.addLast(res);
		}
		return;
	}
	
	
	//await for thread pool to finish and then shut it down
	public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
	    threadPool.shutdown();
	    try {
	        if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
	            threadPool.shutdownNow();
	        }
	    } catch (InterruptedException ex) {
	        threadPool.shutdownNow();
	        Thread.currentThread().interrupt();
	    }
	}

}
