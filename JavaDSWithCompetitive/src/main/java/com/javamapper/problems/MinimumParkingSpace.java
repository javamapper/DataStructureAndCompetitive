package com.javamapper.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MinimumParkingSpace {
	/*
	 * 4 
	 * 5,10 0,20 25,40 35,45 
	 * 5 
	 * 5,10 0,20 31,35 11,35 21,30
	 */

	public void problem1() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wr = new PrintWriter(System.out);
		int n = 5;// Integer.parseInt(br.readLine().trim());

		int[][] parkingStartEndTimeList = new int[n][2];
		String[] parkingStartEndTimes = "5,10 0,20 31,35 11,35 21,30".split(" ");// br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			String[] parkingStartEndTime = parkingStartEndTimes[i].split(",");
			for (int j = 0; j < parkingStartEndTime.length; j++) {
				parkingStartEndTimeList[i][j] = Integer.parseInt(parkingStartEndTime[j]);
			}
		}

		int out = minParkingSpaces(parkingStartEndTimeList);
		System.out.println(out);

		wr.close();
		br.close();
	}

	static class Pair<T1, T2> {
		T1 item1;
		T2 item2;

		Pair(T1 t1, T2 t2) {
			this.item1 = t1;
			this.item2 = t2;
		}

		@Override
		public String toString() {
			return "[" + item1 + ", " + item2 + "]";
		}

	}

	public static void sortPairObjects(List<Pair<Integer, Integer>> inputData) {
		inputData.sort((pair1, pair2) -> {
			if (pair1.item1.equals(pair2.item1)) {
				return 0;
			} else if (pair1.item1 > pair2.item1) {
				return 1;
			} else {
				return -1;
			}
		});
	}

	static int minParkingSpaces(int[][] parkingStartEndTimes) {
		List<Pair<Integer, Integer>> inputData = new LinkedList<>();
		Map<Integer, List<Pair<Integer, Integer>>> parkingSlot = new LinkedHashMap<>();
		Random random = new Random();

		for (int i = 0; i < parkingStartEndTimes.length; i++) {
			inputData.add(new Pair<Integer, Integer>(parkingStartEndTimes[i][0], parkingStartEndTimes[i][1]));
		}
		System.out.println(inputData);
		sortPairObjects(inputData);
		// System.out.println(inputData);
		for (Pair<Integer, Integer> pair : inputData) {
			boolean isNew = true;
			for (Entry<Integer, List<Pair<Integer, Integer>>> entry : parkingSlot.entrySet()) {
				List<Pair<Integer, Integer>> valueList = entry.getValue();
				if (valueList.isEmpty()) {
					valueList.add(pair);
				} else if (pair.item1 >= valueList.get(valueList.size() - 1).item2) {
					valueList.add(pair);
					isNew = false;
					break;
				}
			}
			if (isNew) {
				LinkedList<Pair<Integer, Integer>> newList = new LinkedList<Pair<Integer, Integer>>();
				newList.add(pair);
				parkingSlot.put(random.nextInt(10), newList);

			}
		}
		for (Entry<Integer, List<Pair<Integer, Integer>>> entry : parkingSlot.entrySet()) {
			System.out.println(entry);
		}
		return parkingSlot.size();
	}
}