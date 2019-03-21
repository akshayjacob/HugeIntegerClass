//Akshay Jacob_400113615_2/11/2019_Lab 1&2

public class HugeInteger {
	// instance fields
	private int size = 0; //keeps track of size of stored number
	private SLL head; //initlizes head of linked list
	private boolean sign; //keeps track of sign of stored number

	public HugeInteger() { //initilizes HugeInteger
		size = 0;
		head.next = null;
	}

	public HugeInteger(String val) { //constructor 1, when the number is in string format

		if (val.startsWith("-")) { //checks if number is neg or pos, if neg starts indexing after sign
			sign = true; //sets var to negative to indicate value neg
			
			//sets head
			head = new SLL(Integer.parseInt(val.substring(val.length() - 1, val.length())), null);
			SLL p = head;
			
			size++; //increments size
			
			for (int i = val.length() - 1; i > 1; i--) { //loops through string to store number digit by digit in linked list
				p.next = new SLL(Integer.parseInt(val.substring(i - 1, i)), p.next);
				p = p.next;
				
				size++;
			}
		} else { //runs if number isnt neg
			sign = false; 
			
			//sets head
			head = new SLL(Integer.parseInt(val.substring(val.length() - 1, val.length())), null);
			SLL p = head;
			
			size++;//increments size
			
			for (int i = val.length() - 1; i > 0; i--) { //loops through string to store number digit by digit in linked list
				p.next = new SLL(Integer.parseInt(val.substring(i - 1, i)), p.next);
				p = p.next;
				
				size++;
			}
		}
	}

	public HugeInteger(int n) {//constructor 2, to generate a random number of n length
		//sets head
		head = new SLL((int) (Math.random() * 10), null);
		SLL p = head;
		
		size++; //increments size to track size of integer
		
		for (int i = 0; i < n - 1; i++) { //loops for n times to create an integer of n value in a linked list format
			p.next = new SLL((int) (Math.random() * 10), p.next);
			p = p.next;
			
			size++;
		}
	}
	
	//method to grab digits at pos in linked list
	public int indexDigit(int pos) {
		SLL p = head;

		int currIndex = 0; //counter variable
		
		//loops through list until counter isnt equal to desired pos
		//once counter equals pos then desired position is reached to 
		//grab the desired digit from linked list
		while (currIndex != pos) {
			p = p.next; //goes to next link
			currIndex++; //increments counter
		}

		return p.value;
	}

	
	//Method 1: ADD__________________________________________________________
	public HugeInteger add(HugeInteger h) {
		//init required variables for method
		int tempSum = 0, carry = 0, len = size;
		HugeInteger sum;
		String val = "";

		if (size < h.size) { //checks which digit is bigger and sets len equal h if h is bigger
			len = h.size;
		}

		if (!h.sign && this.sign) { // if a is neg but b is pos (-a + b = b-a)
			sum = h.subtract(this); //subtracts b from a
			
			if (this.compareABS(h) == 1) { //checks to see if than is bigger than h, if so sets sign to neg
				sum.sign = true; 
			}
		} else if (!this.sign && h.sign) { // if a is pos but b is neg (a + -b = a-b)
			h.sign = false; //sets sign of h to pos
			
			sum = this.subtract(h);
			
			if (this.compareABS(h) == -1) {//checks to see if h is bigger than this, if so sets sign to neg
				sum.sign = true;
			}
		} else { // if both numbers are positive (a+b)
			for (int i = 0; i < len; i++) { //loops until all the values in the linked list is added together
				//checks if h is smaller than len since we are adding smaller number to bigger one
				if (i < size && i < h.size) { 
					//sets tempsum equal to the sum of the curr index of this, h and valcarry
					tempSum = this.indexDigit(i) + h.indexDigit(i) + carry; 

					if (tempSum >= 10) {//checks if tempsum is greater than 10. if it is then carries digit
						carry = tempSum / 10; //sets carry to the tens number
						tempSum = tempSum % 10;  //reduces tempsum to only the ones place holder
					} else {
						carry = 0; //carry is 0 if it doesnt have to carry
					}

					val = val + tempSum; //appends tempsum to val

				} else if (i >= size) { //checks if there are still numbers left in the bigger number to add carry (if h is bigger than this)
					tempSum = carry + h.indexDigit(i); //sets tempsum to carry and index of h plus carry

					if (tempSum >= 10) {
						carry = tempSum / 10; //sets carry to the tens number
						tempSum = tempSum % 10; //reduces tempsum to only the ones place holder
					} else {
						carry = 0; //carry is 0 if it doesnt have to carry
					}

					val = val + tempSum; //appends tempsum to val 

				} else if (i >= h.size) { //checks if there are still numbers left in the bigger number to add carry (if this is bigger than h)
					tempSum = carry + this.indexDigit(i); //sets tempsum to carry and index of this plus carry

					if (tempSum >= 10) {
						carry = tempSum / 10; //sets carry to the tens number
						tempSum = tempSum % 10; //reduces tempsum to only the ones place holder
					} else {
						carry = 0; //carry is 0 if it doesnt have to carry
					}

					val = val + tempSum; //appends tempsum to val 

				}
			}

			//checks to see if there is still carry left over after adding all digits in this and h
			if (carry != 0) { 
				val = val + carry; //appends carry to val
			}
			
			//converts string val to a stringbuilder sb since the number in val is currently backwards
			StringBuilder sb = new StringBuilder(val); 

			sum = new HugeInteger(sb.reverse().toString()); //reverses sb to correct order and sets it equal to sum

			if (this.sign && h.sign) { //if both are neg sets sign equal to neg 
				sum.sign = true;
			}
		}

		return sum;
	}
	
	//Method 2:Subtract ___________________________________________________
	//this is the subtract method specified in document
	//it works in collarbation to my own method bigSubtractSmall
	public HugeInteger subtract(HugeInteger h) {
		//init required vars for method
		String val = "";
		HugeInteger diff = null;

		if (this.sign && !h.sign) { // if a is neg but b is pos (-a - b = -(a+b))
			this.sign = false;
			val = this.add(h).toString(); //adds this and h
			diff = new HugeInteger(val);
			diff.sign = true; //sets neg to result
			this.sign = true;
		} else if (!this.sign && h.sign) { // if a is pos but b is not (a - -b = a+b)
			h.sign = false;
			val = this.add(h).toString(); //adds this and h
			diff = new HugeInteger(val);
			h.sign = true;
		} else if (this.sign && h.sign) { // if a & b is neg (-a - -b = b - a)
			if (this.compareABS(h) == 1) {// if abs compare says h is bigger
				val = this.bigSubtractSmall(h, this); //subtracts this from h
				diff = new HugeInteger(val); //result
				diff.sign = false;
			} else if (this.compareABS(h) == -1) { // if abs compare says this is bigger
				val = this.bigSubtractSmall(this, h); //subtracts h from this
				diff = new HugeInteger(val); //result
				diff.sign = true;
			} else {
				val = this.bigSubtractSmall(this, h); //subtracts this from h
				diff = new HugeInteger(val);
			}

		} else { // if a & b is pos (a - b)
			if (this.compareABS(h) == -1) {// if abs compare says h is bigger
				val = this.bigSubtractSmall(h, this); //subtracts h from this
				diff = new HugeInteger(val); //result
				diff.sign = true;
			} else if (this.compareABS(h) == 1) { // if abs compare says this is bigger
				val = this.bigSubtractSmall(this, h); //subtracts this from h
				diff = new HugeInteger(val); //result
				diff.sign = false;
			} else {
				val = this.bigSubtractSmall(this, h); //subtracts this from h
				diff = new HugeInteger(val); //result
			}
		}

		return diff;
	}
	
	//Subtract Function_________________________________________________
	//this function takes in two hughe integer values, big and small
	//size of values must be as specified(big first, small second)
	//subtracts smaller from bigger
	//Ex              10000000000 bigger(top)
	//				 -       1234 smaller(bottom)
	//				-------------
	//						  ans
	public String bigSubtractSmall(HugeInteger big, HugeInteger small) {
		//init required vars for method
		int lenTop = big.size, lenBot = small.size, borrow = 0, temp = 0, counter = 0;
		String val = "";
		StringBuilder top = new StringBuilder(big.toString());
		StringBuilder bot = new StringBuilder(small.toString());
		StringBuilder sb = new StringBuilder(val);
		
		//loops while there are numbers in the bottom to subtract from
		while (lenBot > 0) {
			//grabs first digit of both top and bottom
			int tempTop = Integer.parseInt(top.substring(lenTop - 1, lenTop));
			int tempBot = Integer.parseInt(bot.substring(lenBot - 1, lenBot));

			if (tempTop - tempBot < 0) { //checks to see if function needs to borrow from next digit
				tempTop = tempTop + 10; //adds 10 to current value as a "borrow"

				for (int i = lenTop; i > 0; i--) {
					counter++; //counter var to keep track of what place in the top num youre borrowing from
					
					if (Integer.parseInt(top.substring(i - 2, i - 1)) != 0) { //once a sutible number is found breaks loop
						break;
					}
				}

				//goes to "borrowed" value and decremnets it by 1
				borrow = Integer.parseInt(top.substring(lenTop - counter - 1, lenTop - counter)) - 1;
				top.replace(lenTop - counter - 1, lenTop - counter, "" + borrow);
				
				//goes through all the previous numbers and if they were 0 they are changed to 9
				for (int i = counter; i >= 1; i--) {
					top.replace(lenTop - i, lenTop - i + 1, "" + 9);
				}

				temp = tempTop - tempBot; //subtracts digits in place
				
				sb.append(temp); //appends the calc value to sb
				
				//decrements len of top and bot for forloop
				lenBot--;
				lenTop--;

			} else { //if borrow isnt necessary does regular subtraction between digits in place
				temp = tempTop - tempBot;//subtracts digits in place
				
				sb.append(temp);
				
				lenBot--;
				lenTop--;
			}
		}

		if (lenTop > 0) {
			while (lenTop > 0) {
				int tempA = Integer.parseInt(top.substring(lenTop - 1, lenTop));
				sb.append(tempA);
				lenTop--;
			}

		}

		val = sb.reverse().toString();
		// System.out.println("Val: " + val);
		if (val.startsWith("0")) {
			// sb.reverse();
			sb.deleteCharAt(0);
			val = sb.toString();
		}

		return val;
	}
	
	//Method 3: Multiply______________________________________________
	//This method essentially adds this for h times
	//not efficient but works
	public HugeInteger multiply(HugeInteger h) {
		//int vars for method
		String big = "", small = "";
		
		//checks what number is bigger so run time is less
		if (this.size > h.size) {
			big = this.printABS();
			small = h.printABS();
		} else {
			big = h.printABS();
			small = this.printABS();
		}
		
		HugeInteger val = new HugeInteger(big);
		HugeInteger temp = new HugeInteger("1");
		
		//quick check to see if any numbers are 0 so 0 doesnt have to be added numerous times
		if (big.equals("0") || small.equals("0")) {
			val = new HugeInteger("0");
			return val;
		}
		
		//loops to add bigger number for smaller number times
		while (!temp.printABS().equals(small)) {
			val = val.add(new HugeInteger(big));
			temp = temp.add(new HugeInteger("1"));
		}
		
		//checks if signs are different to set ans pos or neg
		if (this.sign != h.sign) {
			val.sign = true;
		}

		return val;
	}
	
	//Method 4: Division________________________________________________
	//this method like multiply subtracts for h times for integer division
	public HugeInteger divide(HugeInteger h) {
		//init required vars
		String divedent = this.printABS(), divsor = h.printABS();
		HugeInteger val = new HugeInteger(divedent);
		HugeInteger temp = new HugeInteger(h.printABS());

		HugeInteger ans = new HugeInteger("0");
		HugeInteger one = new HugeInteger("1");

		if (divsor.equals("0")) { // if h is zero cannot divide
			System.out.println("Division Error: Cannot divide by 0");
		} else if (divedent.equals("0")) { // if this is zero
			ans = new HugeInteger("0");
		} else if (this.compareABS(h) == -1) {
			ans = new HugeInteger("0");
		} else if (divedent.equals(divsor)) {
			ans = new HugeInteger("1");
		} else if (divsor.equals("1")) {
			ans = new HugeInteger(this.toString());
		} else {
			while (val.compareTo(temp) >= 0) {
				val = new HugeInteger(val.subtract(temp).toString()); //subtracts h from this
				ans = ans.add(one); //adds one to ans cuase still subtractable 
			}
		}
		
		
		//if the signs are diff then the ans must be neg
		if (this.sign != h.sign) {
			ans.sign = true;
		}

		return ans;

	}
	
	//Method 5___________________________________________________________________
	//compares this to h
	public int compareTo(HugeInteger h) {
		int compare = 0;
		
		
		if (this.sign && !h.sign) { // if this is pos but h is neg 
			compare = 1;
		} else if (!this.sign && h.sign) { // if this is neg but h is pos 
			compare = -1;
		} else if (this.size < h.size) { // if this is a smaller number than h
			compare = -1;
		} else if (this.size > h.size) { // if this is a bigger num than h
			compare = 1;
		} else if (this.sign && h.sign) { // if both are neg
			for (int i = size - 1; i > 0; i--) { // compare digit by digit
				if (this.indexDigit(i) > h.indexDigit(i)) {
					compare = -1;
					break;
				} else if (this.indexDigit(i) < h.indexDigit(i)) {
					compare = 1;
					break;
				}
			}
		} else { // both pos and equal
			for (int i = size - 1; i > 0; i--) { // compare digit by digit
				if (this.indexDigit(i) > h.indexDigit(i)) {
					compare = 1;
					break;
				} else if (this.indexDigit(i) < h.indexDigit(i)) {
					compare = -1;
					break;
				}
			}
		}

		return compare;
	}

	public int compareABS(HugeInteger h) {
		int compare = 0;

		if (this.size < h.size) { // if this is a smaller number than h
			compare = -1;
		} else if (this.size > h.size) {
			compare = 1;
		} else {
			for (int i = size - 1; i > 0; i--) { // compare digit by digit
				if (this.indexDigit(i) > h.indexDigit(i)) {
					compare = 1;
					break;
				} else if (this.indexDigit(i) < h.indexDigit(i)) {
					compare = -1;
					break;
				}
			}
		}

		return compare;
	}

	public String toString() {
		SLL p = head;
		int temp = size - 1;
		int[] a = new int[size];
		String print = "";

		if (sign) {
			if (p != null) {
				a[temp] = p.value;
				for (p = p.next; p != null; p = p.next) {
					temp--;
					a[temp] = p.value;
				}

			}

			print = print + "-";

			for (int i = 0; i < size; i++) {
				print = print + a[i];
			}

			return print;
		} else {
			if (p != null) {
				a[temp] = p.value;
				for (p = p.next; p != null; p = p.next) {
					temp--;
					a[temp] = p.value;
				}

			}

			for (int i = 0; i < size; i++) {
				print = print + a[i];
			}

			return print;
		}
	}

	public String printABS() {
		SLL p = head;
		int temp = size - 1;
		int[] a = new int[size];
		String print = "";

		if (p != null) {
			a[temp] = p.value;
			for (p = p.next; p != null; p = p.next) {
				temp--;
				a[temp] = p.value;
			}

		}

		for (int i = 0; i < size; i++) {
			print = print + a[i];
		}

		return print;

	}
}
