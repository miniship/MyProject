package com.moso;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NLargestSumsTest {
	private int[][] lists;
	private int maxN;
	private List allSums;

	public NLargestSumsTest(int[][] lists, int maxN, List allSums) {
		this.lists = lists;
		this.maxN = maxN;
		this.allSums = allSums;
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(NLargestSumsTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("All tests passed: " + result.wasSuccessful());
	}
	
	@Test
	public void test() {		
		NLargestSums nLargestSums = new NLargestSums();
//		nLargestSums.findHighestSums(lists, maxN + 1).stream().forEach(s -> System.out.print(s + ", "));
		assertEquals(allSums, nLargestSums.findHighestSums(lists, maxN + 1));
		assertEquals(allSums, nLargestSums.findHighestSums(lists, maxN + 2));

		for (int i = 1; i <= maxN; i++) {
			assertEquals(allSums.stream().limit(i).collect(Collectors.toList()), nLargestSums.findHighestSums(lists, i));
		}
	}
	
	@Parameters
	public static Collection data() {
		List<Object[]> data = new LinkedList<Object[]>();
		data.add(new Object[] { new int[][] { { 1 } }, 1, Arrays.asList(1) });
		
		data.add(new Object[] { new int[][] { { 3, 2, 1 } }, 3, Arrays.asList(3, 2, 1) });
		data.add(new Object[] { new int[][] { { 3, 3, 1 } }, 3, Arrays.asList(3, 3, 1) });
		data.add(new Object[] { new int[][] { { 3, 1, 1 } }, 3, Arrays.asList(3, 1, 1) });
		data.add(new Object[] { new int[][] { { 4, 4, 1, 1 } }, 4, Arrays.asList(4, 4, 1, 1) });
		data.add(new Object[] { new int[][] { { 3, 3, 3 } }, 3, Arrays.asList(3, 3, 3) });
		
		data.add(new Object[] { new int[][] { { 4, 3, 2, 1 }, { 3, 2, 1 } }, 12, Arrays.asList(7, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 2) });
		data.add(new Object[] { new int[][] { { 4, 3, 2, 1 }, { 3, 3, 1 } }, 12, Arrays.asList(7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 3, 2) });
		data.add(new Object[] { new int[][] { { 4, 3, 2, 1 }, { 3, 1, 1 } }, 12, Arrays.asList(7, 6, 5, 5, 5, 4, 4, 4, 3, 3, 2, 2) });
		data.add(new Object[] { new int[][] { { 4, 3, 2, 1 }, { 5, 5, 2, 2 } }, 16, Arrays.asList(9, 9, 8, 8, 7, 7, 6, 6, 6, 6, 5, 5, 4, 4, 3, 3) });
		data.add(new Object[] { new int[][] { { 4, 3, 2, 1 }, { 3, 3, 3 } }, 12, Arrays.asList(7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4) });
		
		data.add(new Object[] { new int[][] { { 3, 3, 1 }, { 4, 4, 4, 1 } }, 12, Arrays.asList(7, 7, 7, 7, 7, 7, 5, 5, 5, 4, 4, 2) });	
		data.add(new Object[] { new int[][] { { 3, 3, 1 }, { 4, 3, 3, 3 } }, 12, Arrays.asList(7, 7, 6, 6, 6, 6, 6, 6, 5, 4, 4, 4) });
		data.add(new Object[] { new int[][] { { 3, 3, 1 }, { 5, 5, 2, 2 } }, 12, Arrays.asList(8, 8, 8, 8, 6, 6, 5, 5, 5, 5, 3, 3) });
		data.add(new Object[] { new int[][] { { 3, 3, 1 }, { 5, 5, 5, 5 } }, 12, Arrays.asList(8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6) });
		
		data.add(new Object[] { new int[][] { { 4, 3, 3 }, { 5, 4, 4, 4 } }, 12, Arrays.asList(9, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7) });
		data.add(new Object[] { new int[][] { { 4, 3, 3 }, { 5, 5, 4, 4 } }, 12, Arrays.asList(9, 9, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7) });
		data.add(new Object[] { new int[][] { { 4, 3, 3 }, { 5, 5, 5, 5 } }, 12, Arrays.asList(9, 9, 9, 9, 8, 8, 8, 8, 8, 8, 8, 8) });
		
		data.add(new Object[] { new int[][] { { 4, 4, 3, 3 }, { 5, 5, 4, 4 } }, 16, Arrays.asList(9, 9, 9, 9, 8, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7) });
		data.add(new Object[] { new int[][] { { 4, 4, 3, 3 }, { 5, 5, 5 } }, 12, Arrays.asList(9, 9, 9, 9, 9, 9, 8, 8, 8, 8, 8, 8) });
		
		data.add(new Object[] { new int[][] { { 3, 3, 3 }, { 4, 4, 4, 4 } }, 12, Arrays.asList(7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7) });
		
		data.add(new Object[] { new int[][] { { 5, 4, 3, 2, 1 }, { 4, 4, 2 }, { 4, 3, 3 }, { 4, 4, 2, 2 }, { 3, 3 } }, 360, Arrays.asList(20, 20, 20, 20, 20, 20, 20, 20, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11) });
		data.add(new Object[] { new int[][] { { 4, 5, 1, 2, 3 }, { 4, 1 }, { 0, 0, 5 }, { 4, 6, 2 }, { 1 } }, 90, Arrays.asList(21, 20, 19, 19, 18, 18, 18, 17, 17, 17, 17, 16, 16, 16, 16, 16, 16, 15, 15, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 9, 9, 9, 9, 9, 9, 9, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 5, 5) });
		data.add(new Object[] { new int[][] { { 5, 4, 3, 2 }, { 4, 1 }, { 5, 0 }, { 6, 4, 2 }, { 1 } }, 48, Arrays.asList(21, 20, 19, 19, 18, 18, 18, 17, 17, 17, 16, 16, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 11, 11, 10, 10, 10, 9, 9, 9, 8, 8, 7, 6) });
		data.add(new Object[] { new int[][] { { 3, 2, 1 }, { 5, 4 }, { 4, 3 } }, 12, Arrays.asList(12, 11, 11, 11, 10, 10, 10, 10, 9, 9, 9, 8) });
		
		data.add(new Object[] { new int[][] { { 1, 2 }, { 2, 2 }, { 2, 2 } }, 8, Arrays.asList(6, 6, 6, 6, 5, 5, 5, 5) });
		return data;
	}

	private List findAllSums(int[][] lists) {
		List result = new ArrayList<Integer>();
		findAllSums(lists, result, 0, 0);
		Collections.sort(result, Collections.reverseOrder());
		return result;
	}

	private void findAllSums(int[][] lists, List result, int currList, int sum) {
		if (currList == lists.length) {
			result.add(sum);
			return;
		}
		for (int number : lists[currList]) {
			sum += number;
			findAllSums(lists, result, currList + 1, sum);
			sum -= number;
		}
	}
}
