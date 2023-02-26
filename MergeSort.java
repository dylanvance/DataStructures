/*
 * 4.9 Programming Assignment: Merge sort
 * Dylan Vance
 * All code was provided other than lines that say "Added By Me"
 */

package MergeSort;

import java.util.Scanner;

public class MergeSort {

		private static int comparisons = 0;
	
	   // Read size and create an array of size ints.
	   // Read size ints, storing them in the array.
	   // Return the array.
	   public static int[] readNums() {
	      Scanner scnr = new Scanner(System.in);
	      int size = scnr.nextInt();                  // Read array size
	      int[] nums = new int[size];                 // Create array
	      for (int j = 0; j < size; ++j) {            // Read the numbers
	         nums[j] = scnr.nextInt();
	      }
	      scnr.close();
	      return nums;                                // Return the array
	   }

	   // Output the numbers in nums, separated by spaces.
	   // No space or newline will be output before the first number or after the last.
	   public static void printNums(int[] nums) {
	      for (int i = 0; i < nums.length; i++) {
	         System.out.print(nums[i]);
	         if (i < nums.length) {
	            System.out.print(" ");
	         }
	      }
	   }

	   public static void merge(int[] numbers, int i, int j, int k) {
	      int mergedSize = k - i + 1;
	      int mergedNumbers[] = new int[mergedSize];
	      int mergePos;
	      int leftPos;
	      int rightPos;

	      mergePos = 0;
	      leftPos = i;
	      rightPos = j + 1;

	      while (leftPos <= j && rightPos <= k) {
	         if (numbers[leftPos] < numbers[rightPos]) {
	            mergedNumbers[mergePos] = numbers[leftPos];
	            ++leftPos;
	            
	            comparisons++;									//Comparisons Incremented //Added By Me
	         }
	         else {
	            mergedNumbers[mergePos] = numbers[rightPos];
	            ++rightPos;
	            
	            comparisons++;									//Comparisons Incremented //Added By Me
	         }
	         ++mergePos;
	      }

	      while (leftPos <= j) {
	         mergedNumbers[mergePos] = numbers[leftPos];
	         ++leftPos;
	         ++mergePos;
	      }

	      while (rightPos <= k) {
	         mergedNumbers[mergePos] = numbers[rightPos];
	         ++rightPos;
	         ++mergePos;
	      }

	      for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
	         numbers[i + mergePos] = mergedNumbers[mergePos];
	      }
	   }

	   public static void mergeSort(int numbers[], int i, int k) {
	      int j;
	      
	      if (i < k) {
	         j = (i + k) / 2;
	         /* Trace output added to code in book */
	         System.out.printf("%d %d | %d %d\n", i, j, j+1, k);
	         
	         mergeSort(numbers, i, j);
	         mergeSort(numbers, j + 1, k);

	         merge(numbers, i, j, k);
	      }
	   }

	   public static void  main(String[] args) {
	      int[] numbers = readNums();

	      System.out.print("unsorted: ");
	      printNums(numbers);
	      System.out.println("\n");

	      mergeSort(numbers, 0, numbers.length - 1);

	      System.out.print("\nsorted:   ");
	      printNums(numbers);
	      
	      System.out.println();										//Added By Me
	      System.out.println("comparisons: " + comparisons);		//Added By Me
	   }
	
}
