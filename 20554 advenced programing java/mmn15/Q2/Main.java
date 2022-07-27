package mmn15Q2;

import java.util.Scanner;

public class Main {
	
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
			
			//initalizetion
			int[] randomArr = randomizArr(n); 
			MyMonitor monitor = new MyMonitor(n);
			
			//print starting array
			System.out.print("Random array: ");
			for (int i:randomArr) {
				System.out.print(i+" ");
			}
			System.out.print("\n");
			
			
			//start m rounds
			for (int r=0;r<m;r++) {
				
				//create array threads  
				Thr myThreads[] = new Thr[n];
				for (int i = 0; i < n; i++) {
				    myThreads[i] = new Thr(randomArr[i], i, monitor, myThreads);
				}
				
				//start threads
				for (int i = 0; i < n; i++) {
					myThreads[i].start();
				}
				
				//wait for  threads for print 
				for (int i = 0; i < n; i++) {
					try {
						myThreads[i].join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				//change the randomArr
				for (int i = 0; i < n; i++) {
					randomArr[i]=myThreads[i].get_value();
				}
				
				//print for debuging
				System.out.print("After "+ (r+1) + " rounds ");
				for (int i=0;i<n;i++) {
					System.out.print(myThreads[i].get_value()+ " ");
				}
				System.out.print("\n");
			}

		}
		
	}
	
	//return random integers array size n 
	private static int[] randomizArr(int n) {
		int[] res = new int[n];
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
	
}
