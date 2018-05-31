package com.moso;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class NLargestSums {
	List findHighestSums(int[][] lists, int n) {
		// sort each list using quick sort
		for (int[] list : lists) {
			sort(list, 0, list.length - 1);
		}
		List result = new LinkedList<Integer>();
		
		// create a priority queue to store the sum combinations along with the indices
		// of elements from lists which make up the sum.
		Queue<int[]> queue = new PriorityQueue<int[]>((int[] t1, int[] t2) -> t2[0] - t1[0]); // decrease order by sum
		Set<String> indicesSet = new HashSet<String>(); // to avoid duplicate combinations

		int numOfList = lists.length;
		int[] firstItem = new int[numOfList + 1];
		makeSum(lists, firstItem); // the sum is stored at index 0
		queue.add(firstItem);

		while (queue.size() > 0) {
			int[] currentItem = queue.poll();
			result.add(currentItem[0]);
			if (result.size() == n) { // if target is reached
				break;
			}

			for (int i = 0; i < numOfList; i++) {
				if (currentItem[i + 1] == lists[i].length - 1) { // index out bound
					continue;
				}

				int[] nextItem = currentItem.clone();
				nextItem[i + 1]++; // increase each index in the current indices to find a new combination
				String indices = Arrays.toString(Arrays.copyOfRange(nextItem, 1, numOfList + 1));

				if (!indicesSet.contains(indices)) { // if the new combination is not used yet
					makeSum(lists, nextItem);
					queue.add(nextItem);
					indicesSet.add(indices);
				}
			}
		}
		return result;
	}

	void sort(int[] list, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = list[left];
		int i = left;
		int j = right;

		while (i <= j) {
			while (list[i] > pivot) {
				i++;
			}
			while (list[j] < pivot) {
				j--;
			}
			if (i <= j) {
				int t = list[i];
				list[i++] = list[j];
				list[j--] = t;
			}
		}
		sort(list, left, j);
		sort(list, i, right);
	}

	void makeSum(int[][] lists, int[] item) {
		int temp = 0;
		for (int i = 1; i < item.length; i++) {
			temp += lists[i - 1][item[i]];
		}
		item[0] = temp;
	}
}
