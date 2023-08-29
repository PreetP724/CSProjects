package pnpatel.hw1;

import algs.days.day03.FixedCapacityStack;

/**
 * COPY this class into your USERID.hw1 package and complete the implementation of reverseRepresentation.
 */
public class DigitRepresentation {

	static FixedCapacityStack<Integer> reverseRepresentation(int n, int b) {
		int counter = 0;
		int temp = n;
		while(temp > 0) {
			counter++;
			temp = temp / b;
			
		}
		
		FixedCapacityStack<Integer> hold = new FixedCapacityStack<>(counter);
		int digit = 0;
		
		while (n > 0) {
			digit = (n % b);
			
			hold.push(digit);
			n = n /b;
		}
		
		return hold;
		
	}

	public static void main(String args[]) {
		System.out.println("b       21 in base b");
		System.out.println("--------------------");
		int N = 21;
		for (int b = 2; b <= 10; b++) {
			System.out.print(b + "\t");
			FixedCapacityStack<Integer> numbers = reverseRepresentation(N,b);
			while(!(numbers.isEmpty())) {
				System.out.print(numbers.pop());
			}
			System.out.println("");
		}
		
	}
}
