package cen3024c;

import java.util.Arrays;
import java.util.Random;

/* Module 8 | Concurrency Assignment */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SingleThread st = new SingleThread();
		ParallelThread pt = new ParallelThread();
		
		//start single thread
		st.start();
		
		//try to complete single thread before starting parallel thread
		try {
			st.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//start parallel thread
		pt.start();	
	}
}

class Utility {
	
	public int[] randomNumbers(int size) {
		// TODO Auto-generated method stub
		
		//create array variable to store numbers
		int[] num = new int[size];
		//create new random instance
		Random rand = new Random();
		
		//loop through the size of the array and insert random number between 1-10
		for(int i = 0; i < size; i++) {
			num[i] = rand.nextInt(10) + 1;
		}
		
		return num;
	}
}

class SingleThread extends Thread{
	
	public static int singleThreadSum(int[] arrayNum) {
		
		int sum = 0;
		
		//for loop to add all numbers in array
		for (int i = 0; i < arrayNum.length; i++) {
			sum += arrayNum[i];
		}
		
		return sum;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//array size that holds 2 million
		Utility utility = new Utility();
		int[] randNum = utility.randomNumbers(20000000);
		double time = System.nanoTime(); //begin the time in nanoseconds
		int sts = singleThreadSum(randNum);
		time = System.nanoTime() - time;//end time the nanoseconds took to finish
		
		//Print out singleThread answer
		System.out.println("\nSingle thread sum: " + sts +" \nSingle thread time: "+ time +" nanoseconds" );
	}	
}

class ParallelThread extends Thread{
	
	public static int parallelSum(int[] arrayNum) {
		
		//using Java 8 stream to run parallel
		//source: https://www.baeldung.com/java-when-to-use-parallel-stream
		return (int) Arrays.stream(arrayNum).parallel().asLongStream().sum();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//array size that holds 2 million
		Utility utility = new Utility();
		int[] randNum = utility.randomNumbers(20000000);
		double time = System.nanoTime(); //begin the time in nanoseconds
		int sts = parallelSum(randNum);
		time = System.nanoTime() - time;//end time the nanoseconds took to finish
		
		//Print out singleThread answer
		System.out.println("\nParallel thread sum: " + sts +" \nParallel thread time: "+ time +" nanoseconds" );
	}	
}
