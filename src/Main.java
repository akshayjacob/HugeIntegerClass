import java.util.Timer;

//Akshay Jacob_400113615_2/11/2019_Lab 1&2


public class Main {

	public static void main(String[] args) {
		HugeInteger a = new HugeInteger("10000");
		HugeInteger b = new HugeInteger("-300");
		HugeInteger c = new HugeInteger("-9876543201");
		HugeInteger d = new HugeInteger("852789");
		HugeInteger e = new HugeInteger("0");
		HugeInteger f = new HugeInteger("1000");
		
		HugeInteger i = new HugeInteger("-23");
		HugeInteger j = new HugeInteger("-23");
		HugeInteger k = new HugeInteger("5210");
		HugeInteger l = new HugeInteger("-23");
		
		HugeInteger w = new HugeInteger("-98765");
		HugeInteger x = new HugeInteger("134");
		HugeInteger y = new HugeInteger("101");
		HugeInteger z = new HugeInteger("-12");
		
		HugeInteger o = new HugeInteger("1000000000");
		HugeInteger p = new HugeInteger("100000");
		
//		long startTime = System.nanoTime();
//		
//		System.out.println(o.multiply(p));		
//		
//		long endTime   = System.nanoTime();
//		
//		long totalTime = endTime - startTime;
//		System.out.println(totalTime);
		
//		Testing Add000
//		System.out.println("Testing Add_____________________________");
//		System.out.println("a + b = " + a.add(b)); //123333332211 right
//		System.out.println("b + b = " + b.add(b)); //-246913578 right
//		System.out.println("d + b = " + d.add(b)); //-122604000
//		System.out.println("d + c = " + d.add(c)); //9877395990 right
//		System.out.println("c + d = " + c.add(d)); //9877395990 right
//////		
////		//Testing Subtract
//		System.out.println("Testing Subtract________________________");
//		System.out.println("a - b = " + a.subtract(b)); //123580245789 correct
//		System.out.println("b - a = " + b.subtract(a));
//		System.out.println("d - b = " + d.subtract(b));
//		System.out.println("d - c = " + a.subtract(d));
//		System.out.println("e - f = " + e.subtract(f));
////		
////		//Testing Multiply
//		System.out.println("Testing Multiply________________________");
//		System.out.println("i * k = " + i.multiply(k)); 
//		System.out.println("j * k = " + j.multiply(k));
//		System.out.println("k * i = " + k.multiply(i));
//		System.out.println("i * l = " + i.multiply(l));
////		
//////		//Testing Divide
//		System.out.println("Testing Divide__________________________");
//		System.out.println("j / k = " + j.divide(k));
//		System.out.println("i / l = " + i.divide(l));
//		System.out.println("x / y = " + x.divide(y));
//		System.out.println("w / z = " + w.divide(z));
////		
//////		//Testing Compare
//		System.out.println("Testing CompareTo_________________________");
//		System.out.println("j comp k = " + j.compareTo(k));
//		System.out.println("i comp l = " + i.compareTo(l));
//		System.out.println("x comp y = " + x.compareTo(y));
//		System.out.println("w comp z = " + w.compareTo(z));
		
		HugeIntTiming test = new HugeIntTiming();
		
		System.out.println("__________Testing Add__________");
		test.timeAdd();
		System.out.println("__________Testing Subtract_____");
		test.timeSubtract();
		System.out.println("__________Testing Multiply_____");
//		test.timeMultiply();
		System.out.println("__________Testing Divide_______");
//		test.timeDivide();
		System.out.println("__________Testing Compare______");
		test.timeCompare();
		
//		System.out.println(1000%10);
		
		
		//System.out.println(a.toString());
	}

}
