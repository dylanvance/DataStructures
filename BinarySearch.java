/*
 * Implementation of binary search through looping and recursion
 * Authored by Dylan Vance
 */
package binarySearch;

public class BinarySearch {
	private static int low = 0;
	private static int high = 0;
	private static int mid = 0;
	
	
	public static int binarySearch(int numbersArray[], int key) {
		high = numbersArray.length - 1;				//Set high to last index
		
		while (high >= low) {
			mid = (low + high) / 2;					//Find the midpoint
			
			if (numbersArray[mid] < key) {
				low = mid + 1;						//Search right of midpoint
			}
			else if (numbersArray[mid] > key) {
				high = mid - 1;						//Search left of midpoint
			}
		}
		
		return -1;									//Not found
	}
	
	public static int recursiveBinarySearch(int rNumbersArray[], int rLow, int rHigh, int rKey) {
		
		if (rLow > rHigh) { return -1; }
		
		mid = (rLow + rHigh) / 2;
		
		if (rNumbersArray[mid] < rKey) {
			return recursiveBinarySearch(rNumbersArray, mid + 1, rHigh, rKey);
		}
		else if(rNumbersArray[mid] > rKey) {
			return recursiveBinarySearch(rNumbersArray, rLow, mid - 1, rKey);
		}
		
		return mid;
	} 
	
	public static void main (String[] args) {
		
		System.out.println("Hello World!");
		
	}
}
