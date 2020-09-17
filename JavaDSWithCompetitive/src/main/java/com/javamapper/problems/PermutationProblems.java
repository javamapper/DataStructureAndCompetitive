package com.javamapper.problems;

import java.util.ArrayList;
import java.util.List;

public class PermutationProblems {
	String str = "Anil";

	public void recursionApproach() {
		permutationsRec(str, "");
		System.out.println("--- EOM ---");
	}

	public void iterationApproach() {
		permutationsIter(str);
		System.out.println("--- EOM ---");
	}

	/**
	 * @param input
	 * @param result
	 */
	private void permutationsRec(String input, String result) {
		if (input.isEmpty()) {
			System.out.println(result);
		}
		for (int i = 0; i < input.length(); i++) {
			String newResult = result + input.charAt(i);
			permutationsRec(dropIndexChar(i, input), newResult);
		}
	}

	/**
	 * @param index
	 * @param input
	 * @return
	 */
	private String dropIndexChar(int index, String input) {
		return input.substring(0, index) + input.substring(index + 1);
	}

	/**
	 * @param str
	 */
	public static void permutationsIter(String str) {
		List<String> suffleList = new ArrayList<>();

		suffleList.add(String.valueOf(str.charAt(0)));

		for (int includeCharIndex = 1; includeCharIndex < str.length(); includeCharIndex++) {
			for (int reEvalIndex = suffleList.size() - 1; reEvalIndex >= 0; reEvalIndex--) {
				String reEvaluateMe = suffleList.remove(reEvalIndex);
				for (int k = 0; k <= reEvaluateMe.length(); k++) {
					suffleList.add(
							reEvaluateMe.substring(0, k) + str.charAt(includeCharIndex) + reEvaluateMe.substring(k));
				}
			}
		}
		suffleList.stream().forEach(System.out::println);
	}

}
