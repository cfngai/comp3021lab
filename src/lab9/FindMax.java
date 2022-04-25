package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */


public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		//new FindMax().printMax();
		
		FindMaxTask fmt1 = new FindMax().new FindMaxTask(0, 29);
		Thread t1 = new Thread(fmt1);
		t1.start();
		FindMaxTask fmt2 = new FindMax().new FindMaxTask(30, 59);
		Thread t2 = new Thread(fmt2);
		t2.start();
		FindMaxTask fmt3 = new FindMax().new FindMaxTask(60, 89);
		Thread t3 = new Thread(fmt3);
		t3.start();
		
		try {
			t1.join(); t2.join(); t3.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		int t1_output = fmt1.getOutput();
		int t2_output = fmt2.getOutput();
		int t3_output = fmt3.getOutput();
		System.out.println("T1 result " + t1_output);
		System.out.println("T2 result " + t2_output);
		System.out.println("T3 result " + t3_output);
		System.out.println("the max value is " + Math.max(Math.max(t1_output, t2_output), t3_output));
	}

	public void printMax() {
		// this is a single threaded version
		int max = findMax(0, array.length - 1);
		System.out.println("the max value is " + max);
	}

	public class FindMaxTask implements Runnable {
		private int begin;
		private int end;
		private int output;
		
		public FindMaxTask(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		
		public int getOutput() {
			return output;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			output = findMax(begin, end);
		}
		
	}
	
	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
