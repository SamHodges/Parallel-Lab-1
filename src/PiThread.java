import java.util.concurrent.ThreadLocalRandom;


public class PiThread implements Runnable {
	int numRuns;
	double[] sharedArray;
	int threadNum;
	int numHits;
	
	public PiThread (int numRuns, double[] sharedArray, int threadNum) {
		this.numRuns = numRuns;
		this.sharedArray = sharedArray;
		this.threadNum = threadNum;
		numHits = 0;
		
	}

	@Override
	public void run() {

		for (int i = 0; i < numRuns; i++) {
			double x, y;
			x = ThreadLocalRandom.current().nextDouble();
			y = ThreadLocalRandom.current().nextDouble();
			
			if ((x * x) + (y * y) <= 1) {
				numHits += 1;
			}
		}
		
		sharedArray[threadNum] = numHits;
		
		
	}
	
}