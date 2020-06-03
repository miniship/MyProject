package com.moso;

import java.util.*;

public class NLargestSums {
	List findHighestSums(int[][] lists, int n) throws OutOfIndicesCombinationException {
		List<Integer> result = new LinkedList<>();
		Queue<Sum> queue = new PriorityQueue<>((Sum s1, Sum s2) -> s2.getValue() - s1.getValue());
		Set<String> stringIndicesSet = new HashSet<>();

		Arrays.stream(lists).forEach(Arrays::sort);
		queue.add(createFirstSum(lists));

		while (queue.size() > 0) {
			Sum currentSum = queue.poll();
			result.add(currentSum.getValue());
			if (result.size() == n) {
				break;
			}

			int[] nextIndices = generateNextIndices(lists, currentSum.getIndices());
			String stringIndices = Arrays.toString(nextIndices);
			if (stringIndicesSet.add(stringIndices)) {
				Sum nextSum = createSum(lists, nextIndices);
				queue.add(nextSum);
			}
		}
		return result;
	}

	private Sum createFirstSum(int[][] lists) {
		return createSum(lists, new int[lists.length]);
	}

	private Sum createSum(int[][] lists, int[] indices) {
		Sum sum = new Sum();
		sum.setValue(calculateSumValue(lists, indices));
		sum.setIndices(indices);
		return sum;
	}

	private int calculateSumValue(int[][] lists, int[] indices) {
		int value = 0;
		for (int i = 1; i < indices.length; i++) {
			int listNo = i - 1;
			int listIndex = indices[i];
			value += lists[listNo][listIndex];
		}
		return value;
	}

	private int[] generateNextIndices(int[][] lists, int[] currentIndices) throws OutOfIndicesCombinationException {
		for (int i = 0; i < lists.length; i++) {
			int[] currentList = lists[i];
			int currentListIndex = currentIndices[i];
			if (currentListIndex == currentList.length - 1) {
				continue;
			}

			int[] nextIndices = currentIndices.clone();
			nextIndices[i]++;
			return nextIndices;
		}
		throw new OutOfIndicesCombinationException();
	}

	private void quickSort(int[] list, int left, int right) {
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
		quickSort(list, left, j);
		quickSort(list, i, right);
	}
}
