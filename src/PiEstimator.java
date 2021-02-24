/**
  * Description: <describe your program here>
  *
  * @author <your name here>
  * 
  * QUESTIONS: how many digits should it estimate? 5! Mine does 4...
  * 
  */

public class PiEstimator {
    // add any desired fields here
	long numPoints;
	int numThreads;

    // constructor taking in the number of sample points, numPoints, 
    // and the number of threads used to compute the estimate
    public PiEstimator (long numPoints, int numThreads) {
	    this.numPoints = numPoints;
	    this.numThreads = numThreads;
    }

    // compute the estimate of pi (improve this description!)
    public double getPiEstimate () {
    	double pi;
    	int numRuns;
    	
    	double[] piValues = new double[numThreads];
    	Thread[] threads = new Thread[numThreads];
    	numRuns = (int) (numPoints / numThreads);
    	
    	for (int i = 0; i < numThreads; i++) {
    	    threads[i] = new Thread(new PiThread(numRuns, piValues, i));
    	}
    	
    	for (Thread t : threads) {
    	    t.start();
    	}
    	
    	for (Thread t : threads) {
    	    try {
    		    t.join();
    	    }
    	    catch (InterruptedException ignored) {
    		    // don't care if t was interrupted
    	    }
    	}
    	
    	double numHits = 0;
    	for (int i = 0; i < numThreads; i++) {
    		numHits += piValues[i];
    	}
    	
    	double p = numHits / numPoints;
    	pi = 4 * p;
    	
	    return pi;
    }
}