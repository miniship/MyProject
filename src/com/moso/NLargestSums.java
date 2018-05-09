package com.moso;

import java.util.LinkedList;
import java.util.List;

public class NLargestSums {
	List findHighestSums(int[][] lists, int n) {
		// make reductionLists from lists: for each list, remove the highest integer,
		// change item[i] value to item[0] - item[i].
		// Find the n largest sums from lists is the same as find n - 1 smallest sums
		// from reductionLists, but we only have to pick at least one integer.
		// For the lists (highest sum = 21)
		// [5,4,3,2,1]
		// [4,1]
		// [5,0,0]
		// [6,4,2]
		// [1]
		// The corresponding reductionLists is
		// [1,2,3,4]
		// [3]
		// [5,5]
		// [2,4]
		// Chose {1, , , } from the reductionLists, the next sum is 21-1 = 20
		// Chose {1, 3, , 4} from the reductionLists, the next sum is 21-1-3-4 = 13
		
		int[][] reductionLists = makeReductionLists(lists);
		List<Integer> results = new LinkedList<Integer>(); // store results
		results.add(sumFirstItems(lists)); // add the highest sum
		int[] remainSums = new int[] { n - 1 }; // remaining sums we have to find

		for (int i = 0; i <= sumLastItems(reductionLists); i++) {
			if (remainSums[0] <= 0) { // if requirement is reached
				break;
			}
			findSums(reductionLists, remainSums, results, i, 0, 0); // find the next batch of sums
		}
		return results;
	}

	/**
	 * @param reductionLists
	 * @param remainSums
	 * @param results
	 * @param range
	 *            range from this batch to the highest sum
	 * @param currList
	 *            index of the current list where we are working on
	 * @param sumDecrease
	 *            sum of integers which we have chosen (at most one from each list)
	 */
	void findSums(int[][] reductionLists, int[] remainSums, List<Integer> results, int range, int currList, int sumDecrease) {
		if (currList == reductionLists.length) { // out of list to chose
			return;
		}

		for (int i = currList; i < reductionLists.length; i++) {
			for (int number : reductionLists[i]) {
				sumDecrease += number; // pick one integer in the current list

				if (sumDecrease > range) { // the final result will be smaller than the target sum
					sumDecrease -= number; // discard the most recently picked integer
					break; // move to the next list
				}
				if (sumDecrease == range) { // a match if found
					results.add(results.get(0) - range);
					remainSums[0]--;
					if (remainSums[0] <= 0) {
						return;
					}
				}

				findSums(reductionLists, remainSums, results, range, i + 1, sumDecrease); // check the next list
				if (remainSums[0] <= 0) {
					return;
				}
				sumDecrease -= number; // to move on the next integer in the current list
			}
		}
	}

	int[][] makeReductionLists(int[][] lists) {
		List<int[]> result = new LinkedList<int[]>();

		for (int[] list : lists) {
			int len = list.length;
			if (len < 2) {
				continue;
			}

			int[] newList = new int[len - 1];
			for (int i = 1; i < len; i++) {
				newList[i - 1] = list[0] - list[i];
			}
			result.add(newList);
		}
		return result.toArray(new int[result.size()][]);
	}

	int sumFirstItems(int[][] lists) {
		int result = 0;
		for (int[] list : lists) {
			result += list[0];
		}
		return result;
	}

	int sumLastItems(int[][] reductionLists) {
		int result = 0;
		for (int[] list : reductionLists) {
			result += list[list.length - 1];
		}
		return result;
	}
}
