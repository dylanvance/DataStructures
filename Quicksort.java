/*
 * 4.10 Programming Assignment: Sorting user IDs (Quicksort)
 * Authored by Dylan Vance
 */

package Quicksort;

import java.util.Scanner;
import java.util.ArrayList;


public class Quicksort {
	
	public static int partition(ArrayList<String> userIDs, int low, int high) {
		
		int midpoint = low + (high - low) / 2;
		String pivot = userIDs.get(midpoint);
		String temp = "";
	   
		boolean done = false;
		while (!done) {
			
			while (userIDs.get(low).compareTo(pivot) < 0) {
				low += 1;
			}
		   
			while (userIDs.get(high).compareTo(pivot) > 0) {
				high -= 1;
			}
		   
			if (low >= high) {
				done = true;
			}
			else {
				//Swap
				temp = userIDs.get(low);
				userIDs.add(low, userIDs.get(high));
				userIDs.add(high, temp);
				
				//Update low and high
				low += 1;
				high -= 1;
			}
		}
		
		return high;
	}

	public static void quicksort(ArrayList<String> userIDs, int low, int high) {
		
		int lowEndIndex;
		
		if (low >= high) {
			return;
	    }
		
		lowEndIndex = partition(userIDs, low, high);
		
		quicksort(userIDs, low, lowEndIndex);
		quicksort(userIDs, lowEndIndex + 1, high);

	}

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);

	    ArrayList<String> userIDList = new ArrayList<String>();

	    String userID;

	    userID = scnr.next();
	    while (!userID.equals("-1")) {
	    	userIDList.add(userID);
	        userID = scnr.next();
	    }
	      
	    // Initial call to quicksort 
	    quicksort(userIDList, 0, userIDList.size() - 1);

	    for (int i = 0; i < userIDList.size(); ++i) {
	    	System.out.println(userIDList.get(i));
	    }
	    
	    scnr.close();
	}
}