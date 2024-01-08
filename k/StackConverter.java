package pnpatel.hw1;

import algs.days.day03.FixedCapacityStack;

/**
 * COPY this file into your USERID.hw1 package and complete its implementation.
 * 
 * You do not have to modify the main() method.
 */
public class StackConverter {

	public static int[] toArray(FixedCapacityStack<Integer> stack) {
		//throw new RuntimeException("MUST COMPLETE toArray() method in StackConverter.");
		FixedCapacityStack<Integer> temp = new FixedCapacityStack<>(256);
		int counter = 0;
		while(!(stack.isEmpty())) {
			int num = stack.pop();
		
			temp.push(num);
			counter++;
		}
		int arr[] = new int[counter];
		int i = 0;
		while(!(temp.isEmpty())) {
			int number = temp.pop();
			
			stack.push(number);
			arr[i] = number;
			i++;
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(256);
		stack.push(926);
		stack.push(415);
		stack.push(31);
		
		int vals[] = StackConverter.toArray(stack);
		System.out.println("The following output must be [926, 415, 31] :" + java.util.Arrays.toString(vals));

		// note that you can still pop values
		System.out.println("shoud be 31:" + stack.pop());
		System.out.println("shoud be 415:" + stack.pop());
		System.out.println("shoud be 926:" + stack.pop());
	}
}
