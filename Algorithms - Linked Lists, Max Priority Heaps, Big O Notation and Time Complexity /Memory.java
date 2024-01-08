package pnpatel.hw2;

/**
 * COPY this class into your USERID.hw2
 * 
 * Responsible for allocating memory from within a designated block of chars.
 * 
 * Can reallocate memory (and copy existing chars to smaller or larger destination).
 * 
 * Can defragment available by combining neighboring regions together. ONLY possible if the blocks
 * of allocated memory appear in sorted order within the available list (worth five points on this question).
 * 
 * Can alert user that excess memory remains unfree'd
 * 
 * Address ZERO is always invalid.
 */
public class Memory {
	
	/** USE THIS StorageNode CLASS AS IS. */
	class StorageNode {
		int           addr;        // address into storage[] array
		int           numChars;    // how many chars are allocated
		
		StorageNode   next;        // the next StorageNode in linked list.
		
		/** Allocates a new Storage Node. */
		public StorageNode (int addr, int numChars) {
			this.addr = addr;
			this.numChars = numChars;
			this.next = null;
		}
		
		/** Allocates a new Storage Node and makes it head of the linked list, next. */ 
		public StorageNode (int addr, int numChars, StorageNode next) {
			this.addr = addr;
			this.numChars = numChars;
			this.next = next;
		}
		
		/** Helper toString() method. */
		public String toString() {
			return "[" + addr + " @ " + numChars + " = " + (addr+numChars-1) + "]";
		}
	}
	
	/** Storage of char[] that this class manages. */
	final char[] storage;
	
	// YOU CAN ADD FIELDS HERE
	
	StorageNode allocated;
	StorageNode available;
	public int charsAllocated;
	public int charsAvailable;
	public int blocksAllocated;
	public int blocksAvailable;
	
	public Memory(int N) {
		// memory address 0 is not valid, so make array N+1 in size and never use address 0.
		storage = new char[N+1];
		// DO MORE THINGS HERE
		available = new StorageNode(1, N); 
		charsAvailable = N;
		charsAllocated = 0;
		blocksAllocated = 0;
		blocksAvailable = 1;
		
	}
	
	/** 
	 * Make a useful debug() method.
	 * 
	 * You should print information about the AVAILABLE memory chunks and the ALLOCATED memory chunks.
	 * 
	 * This will prove to be quite useful during debugging.
	 */
	public String toString() {
		return "COMPLETE THIS IMPLEMENTATION";
	}
	
	/** 
	 * Report on # of StorageNode in allocated list (used for testing/debugging)
	 */
	public int blocksAllocated() {
		return blocksAllocated;
	}
	
	/** 
	 *  Report on # of StorageNode in available list (used for testing/debugging)
	 */
	public int blocksAvailable() {
		return blocksAvailable;
	}
	
	/** 
	 * Report on memory that was allocated but not free'd.
	 * Performance must be O(1).
	 */
	public int charsAllocated() {
		return charsAllocated;
	}
	
	/** 
	 * Report on available memory remaining to be allocated.
	 * Performance must be O(1).
	 */
	public int charsAvailable() {
		return charsAvailable;
	}
	
	/** 
	 * Return the char at the given address.
	 * Unprotected: can return char for any address of memory.
	  */
	public char getChar(int addr) {
		//validateAllocated(addr); [SORRY I INCLUDED THIS. YOU DON'T NEED TO DO THIS]
		return storage[addr];
	}
	
	/** 
	 * Get char[] at the given address for given number of chars, if valid.
	 * Unprotected: can return char[] for any address of memory.
	 * Awkward that you do not have ability to know IN ADVANCE whether this many
	 * characters are stored there, but a runtime exception will tell you.
	 */
	public char[] getChars(int addr, int numChars) {
		//validateAllocated(addr, numChars);  [SORRY I INCLUDED THIS. YOU DON'T NEED TO DO THIS]
		char[] chars = new char[numChars];
		int k = 0;
		for(int i = addr; i < addr + numChars; i++) {
			chars[k] = storage[i];
			k++;
		}
		return chars;
	}
	
	/** 
	 * Determines if the current address is valid allocation. Throws Runtime Exception if not. 
	 * Performance proportional to number of allocated blocks.
	 */
	void validateAllocated(int addr) {
		// COMPLETE IMPLEMENTATION
		int i = 0;
		StorageNode n = allocated;
		while(n != null) {
			if(addr >= n.addr && addr < n.addr + n.numChars) {
				i = 1;
				break;
			}
			
			n = n.next;
		}
		if(i == 0) {
		throw new RuntimeException("The Address is not a valid allocation");
		}
	}
	
	/** Determines if the current address is valid allocation for the given number of characters. */
	void validateAllocated (int addr, int numChar) throws RuntimeException{
		// COMPLETE IMPLEMENTATION
		int i = 0;
		StorageNode n = allocated;
		while(n != null) {
			if(addr >= n.addr && addr < n.addr + n.numChars && (addr + numChar - 1) <= (n.addr+ n.numChars - 1)) {
				i = 1;
				break;
			}
			
			n = n.next;
		}
		if(i == 0) {
		throw new RuntimeException("The Address is not a valid allocation to store the given number of characters");
		}
	}
	
	/** 
	 * Internally allocates given memory if possible and return its starting address.
	 * 
	 * Must ZERO out all memory that is allocated.
	 * @param numChars   number of consecutive char to be allocated
	 */
	public int alloc(int numChars) throws RuntimeException {
		StorageNode n = available;
		//look for available chunks
		if(charsAllocated() == storage.length -1) {
			throw new RuntimeException("Cannot allocate this number of characters, not enough memory");
		}
		int i = 0;
		StorageNode prev = null;
		while(n != null) {
			if(n.numChars >= numChars) {
				i = 1;
				break;
			}
			prev = n;
			n = n.next;
		}
		
		if(i == 0) {
			throw new RuntimeException("Cannot allocate this number of characters, not enough memory");
		}
		int add =n.addr;
		//change available
		if(n.numChars > numChars) {
			n.addr = n.addr + numChars;
			n.numChars = n.numChars - numChars;
		}
		else {
			if(available.next == null) {
				available = null;
				blocksAvailable--; 
			}
			else if(prev == null){
				available = n.next;
				blocksAvailable--;
			}
			else {
			    prev.next = n.next; 
			    blocksAvailable --;
			}
		}
		
		//update allocated
		int address = 0;
		if(charsAllocated() == 0) {
		allocated = new StorageNode(1, numChars);
		address = 1;
	}
		else {
		
			StorageNode m = allocated;
			
			while(m != null) {
				if(m.next == null) {
					StorageNode end = new StorageNode(add, numChars);
					m.next = end;
					address = add;
					break;
				}
				m = m.next;
				
				
			}
		
			
			
		}	
		//zero all values
		for(int k = address; k <= numChars; k++) {
			storage[i] = 0;
		}
		//update fields
			charsAllocated = charsAllocated + numChars;
			charsAvailable = charsAvailable - numChars;
			blocksAllocated ++;
			return address;
	}
	
	/** Reallocate to larger space and copy existing chars there, while free'ing the old memory. */
	public int realloc(int addr, int newSize) {
		int add = 0;
		StorageNode m = allocated;
		while(m != null) {
			if(m.addr == addr) {
				add = 1;
				break;
			}
		}
		if(add == 0) {
			throw new RuntimeException("Address does not exist");
		}
		int address = alloc(newSize);
		StorageNode n = allocated;
		int num = 0;
		while(n != null) {
			if(n.addr == addr) {
				num = n.numChars;
				break;
			}
			n = n.next;
		}
		int i;
		for(i = 1; i < newSize; i++) {
			if(i >= num) {
				
				break;
			}
			
		}
		char[] chars = getChars(addr, i);
		setChars(address, chars);
		free(addr);
				
		return address;
		
	}
	
	/** 
	 * Internally allocates sufficient memory in which to copy the given char[]
	 * array and return the starting address of memory.
	 * @param chars - the characters to be copied into the new memory
	 * @return address of memory that was allocated
	 */
	public int copyAlloc(char[] chars) throws RuntimeException {
		int address = alloc(chars.length);
		setChars(address, chars);
		return address;
		//throw new RuntimeException("COMPLETE IMPLEMENTATION");
	}
	
	/** 
	 * Free the memory currently associated with address and add back to 
	 * available list.
	 * 
	 * if addr is not within a range of allocated memory, then FALSE is returned.
	 */
	public boolean free(int addr) {
		
		//check for address in allocated
		StorageNode n = allocated;
		boolean isThere = false;
		while(n != null) {
			if(n.addr == addr) {
				isThere = true;
				break;
			}
			n = n.next;
		}
		if(!isThere) {
			return false;
		}
		//remove node from allocated
		StorageNode prev = null;
		StorageNode m = allocated;
		while(m != null) {
			if(m.addr == addr) {
				if(prev == null) {
					allocated = m.next;
					break;
				}
				else {
					prev.next = m.next;
					
					break;
				}
			}
			prev = m;
			m = m.next;
		}
		blocksAllocated--;
		charsAllocated = charsAllocated - m.numChars;
		//add node to available
		prev = null;
		if(charsAvailable() == 0) {
			m.next = null;
			available = m;
		}
		else {
		StorageNode b = available;
		while(b != null) {
			if(m.addr < b.addr) {
				if(prev == null) {
					m.next = available;
					available = m;
					break;
				}
				else {
					prev.next = m;
					m.next = b;
					break;
				}
			}
			prev = b;
			b = b.next;
		}
		if(b == null) {
			m.next = null;
			prev.next = m;
		}
		}
		charsAvailable = charsAvailable + m.numChars;
		blocksAvailable++;
		
		//merge nodes in available
		if(available.next != null) {
		prev = null;
		StorageNode a = available;
		prev = a;
		a = a.next;
		while(a != null) {
			if(prev.addr + prev.numChars == a.addr) {
				prev.numChars = prev.numChars + a.numChars;
				prev.next = a.next;
				a = a.next;
				blocksAvailable--;
			}
			else {
				prev = a;
				a = a.next;
			}
			
		}
		}
		
		

		
		
		
		
		return true;
		
	}
	
	/** 
	 * Set char, but only if it is properly contained as an allocated segment of memory. 
	 * Performance proportional to number of allocated blocks.
	 * @exception if the addr is not within address of memory that was formerly allocated.
	 */
	public void setChar(int addr, char value) throws RuntimeException {
		validateAllocated(addr);
		storage[addr] = value;
		//throw new RuntimeException("COMPLETE IMPLEMENTATION");
	}
	
	/** 
	 * Set consecutive char values starting with addr to contain the char values passed in, but only if 
	 * the full range is properly contained as an allocated segment of memory. 
	 * Performance proportional to number of allocated blocks.
	 * @exception if the addr is not within address of memory that was formerly allocated.
	 */
	public void setChars(int addr, char[] values) throws RuntimeException {
		validateAllocated(addr, values.length);
		int k = 0;
		for(int i = addr; i < addr + values.length; i++) {
			storage[i] = values[k];
			k++;
		}
		//throw new RuntimeException("COMPLETE IMPLEMENTATION");
	}
	
	// ================================================================================================================
	// ======================================== EVERYTHING ELSE BELOW REMAINS AS IS ===================================
	// ================================================================================================================
	
	/** 
	 * Sets int, but only if it is properly contained as an allocated segment of memory. 
	 * Performance proportional to number of allocated blocks.
	 * USE AS IS.
	 * @exception if the addr is not within address of memory that was formerly allocated with sufficient space
	 */
	public void setInt(int addr, int value) throws RuntimeException {
		validateAllocated(addr, 4);
		setChar(addr,   (char)((value & 0xff000000) >> 24));
		setChar(addr+1, (char)((value & 0xff0000) >> 16));
		setChar(addr+2, (char)((value & 0xff00) >> 8));
		setChar(addr+3, (char)(value & 0xff));
	}
	
	/** 
	 * Return the 4-chars at the given address as an encoded integer.
	 * Performance proportional to number of allocated blocks.
	 * USE AS IS.
	 */
	public int getInt(int addr) {
		validateAllocated(addr, 4);
		return (getChar(addr) << 24) + (getChar(addr+1) << 16) + (getChar(addr+2) << 8) + getChar(addr+3);
	}
	
	/**
	 * Allocate new memory large enough to contain room for an array of numbers and copy
	 * numbers[] into the memory, returning the address of the first char.
	 * 
	 * USE AS IS.
	 * 
	 * Because int values are 32-bits, this means that the total number of char allocated
	 * will be 4*numbers.length
	 * 
	 * @param numbers   The int[] values to be copied into the newly allocated storage.
	 */
	public int copyAlloc(int[] numbers) {
		int addr = alloc(4*numbers.length);
		for (int i = 0; i < numbers.length; i++) {
			setInt(addr+4*i, numbers[i]);
		}
		
		return addr;
	}
	
	/**
	 * Return the string which is constructed from the sequence of char from addr
	 * for len characters.
	 * USE AS IS.
	 */
	public String createString(int addr, int len) {
		return String.valueOf(storage, addr, addr+len-1);
	}
	
	/**
	 * Return those allocated nodes whose allocated char[] matches the pattern of char[] passed in.
	 * ONLY COMPLETE FOR BONUS
	 * @param pattern
	 */
	public java.util.Iterator<StorageNode> match(char[] pattern) {
		throw new RuntimeException("BONUS IMPLEMENTATION");
	}
	
	/** This sample program recreates the linked list example from Q2 on the homework. */
	public static void main(String[] args) {
		Memory mem = new Memory(32);
		
		 mem.alloc(2);
		  // don't use address in this small example...
		int first  = mem.alloc(8);
		
					mem.alloc(3);
		int third  = mem.alloc(8);
		
		             mem.alloc(3);
		int second = mem.alloc(8);
		
		

		mem.setInt(first, 178);   // first node stores 178
		mem.setInt(second, 992);  // second node stores 992
		mem.setInt(third, 194);   // third node stores 194
		
		mem.setInt(first+4, second);    // have next pointer for first to point to second
		mem.setInt(second+4, third);    // have next pointer for second to point to third
		mem.setInt(third+4, 0);         // have next pointer for third to be 0 (END OF LIST)
		
		// How to loop through list?
		System.out.println("Numbers should print in order from 178 -> 992 -> 194");
		int addr = first;
		while (addr != 0) {
			int value = mem.getInt(addr);    // get value of node pointed to by addr.
			System.out.println(value);
			
			addr = mem.getInt(addr+4);       // advance to next one in the list
		}
		
		System.out.println("Allocated bytes should be 32: " + mem.charsAllocated());
		System.out.println("Available bytes should be 0: " + mem.charsAvailable());
		
		mem.free(second);
		mem.free(first);
		mem.free(third);
	}
}