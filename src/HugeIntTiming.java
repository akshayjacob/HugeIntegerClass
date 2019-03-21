
public class HugeIntTiming {
	public HugeIntTiming() {
		
	}
	
	public void timeAdd() {
		HugeInteger huge1, huge2, huge3;
		
		long startTime, endTime;
		
		double runTime = 0.0;
		int maxRun = 500;
		
		for(int numInts = 0; numInts <= 100; numInts++) {
			huge1 = new HugeInteger(numInts);
			huge2 = new HugeInteger(numInts);
			
			startTime = System.currentTimeMillis();
			
			for(int numRun = 0; numRun < maxRun; numRun++) {
				huge3 = huge1.add(huge2);
			}
			
			endTime = System.currentTimeMillis();
			
			runTime += (double)(endTime-startTime)/(double)(maxRun);
			
			if(numInts%10 == 0) {
				System.out.println("Testing Add: n: " + numInts + " time: " + runTime);
			}
		}
		
		runTime = runTime/(double)100;
	}
	
	public void timeSubtract() {
		HugeInteger huge1, huge2, huge3;
		
		long startTime, endTime;
		
		double runTime = 0.0;
		int maxRun = 500;
		
		for(int numInts = 0; numInts < 100; numInts++) {
			huge1 = new HugeInteger(numInts);
			huge2 = new HugeInteger(numInts);
			
			startTime = System.currentTimeMillis();
			
			for(int numRun = 0; numRun < maxRun; numRun++) {
				huge3 = huge1.add(huge2);
				huge3 = huge1.add(huge2);
				huge3 = huge1.add(huge2);
				huge3 = huge1.add(huge2);
				huge3 = huge1.add(huge2);
			}
			
			endTime = System.currentTimeMillis();
			
			runTime += (double)(endTime-startTime)/(double)(maxRun);
			
			if(numInts%10 == 0) {
				System.out.println("Testing Subtract: n: " + numInts + " time: " + runTime);
			}
		}
		
		runTime = runTime/(double)100;
		
	}
	
	public void timeMultiply() {
		HugeInteger huge1, huge2, huge3;
		
		long startTime, endTime;
		
		double runTime = 0.0;
		int maxRun = 500;
		
		for(int numInts = 0; numInts < 4; numInts++) {
			huge1 = new HugeInteger(numInts);
			huge2 = new HugeInteger(numInts);
			
			startTime = System.currentTimeMillis();
			
			for(int numRun = 0; numRun < maxRun; numRun++) {
				huge3 = huge1.multiply(huge2);
			}
			
			endTime = System.currentTimeMillis();
			
			runTime += (double)(endTime-startTime)/(double)(maxRun);
			
//			if(numInts%10 == 0) {
				System.out.println("Testing Multiply: n: " + numInts + " time: " + runTime);
//			}
		}
		
		runTime = runTime/(double)100;
		
	}
	
	public void timeDivide() {
		HugeInteger huge1, huge2, huge3;
		
		long startTime, endTime;
		
		double runTime = 0.0;
		int maxRun = 500;
		
		for(int numInts = 0; numInts < 4; numInts++) {
			huge1 = new HugeInteger(numInts);
			huge2 = new HugeInteger(numInts);
			
			startTime = System.currentTimeMillis();
			
			for(int numRun = 0; numRun < maxRun; numRun++) {
				huge3 = huge1.multiply(huge2);
			}
			
			endTime = System.currentTimeMillis();
			
			runTime += (double)(endTime-startTime)/(double)(maxRun);
			
//			if(numInts%10 == 0) {
				System.out.println("Testing Divide: n: " + numInts + " time: " + runTime);
//			}
		}
		
		runTime = runTime/(double)100;
		
	}
	
	public void timeCompare() {
		HugeInteger huge1, huge2;
		int huge3;
		
		long startTime, endTime;
		
		double runTime = 0.0;
		int maxRun = 500;
		
		for(int numInts = 0; numInts < 100; numInts++) {
			huge1 = new HugeInteger(numInts);
			huge2 = new HugeInteger(numInts);
			
			startTime = System.currentTimeMillis();
			
			for(int numRun = 0; numRun < maxRun; numRun++) {
				huge3 = huge1.compareTo(huge2);
			}
			
			endTime = System.currentTimeMillis();
			
			runTime += (double)(endTime-startTime)/(double)(maxRun);
			
			if(numInts%10 == 0) {
				System.out.println("Testing Compare: n: " + numInts + " time: " + runTime);
			}
		}
		
		runTime = runTime/(double)100;
		
	}
}
