package com.javamapper.problems;

public class ProblemLcs {
	String str1 = "generation-gap";
	String str2 = "tiger-lap";

	public void recursionApproach() {
		System.out.println(lcsRecu(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length()));
		System.out.println("--- EOM ---");
	}

	public void iterationApproach() {
		System.out.println(lcsMemorize(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length()));
		System.out.println("--- EOM ---");
	}
	

	/**
	 * Recursive method (str1, str2, m-> length of str1, n-> length of str2)
	 * Bottom-Up approach [need to think problem in mXn matrix]
	 * From Bottom-Right corner we start (m-1,n-1)
	 * 		IF both are same THEN 
	 * 			collect diagonal up value(if not available calculate it, obviously not available) + 1 
	 * 		    call self (str1,str2, m-1,n-1); Why not m-2? because, if we see method call then it make clear we are already calling with +1 index
	 * 		ELSE
	 * 			return MAX(left adjacent column value(m,n-1),top adjacent row value(m-1,n))
	 * 		END IF
	 * @param st1
	 * @param st2
	 * @param m
	 * @param n
	 * @return
	 */
	public int lcsRecu(char[] st1, char[] st2, int m, int n) {
		int p1, p2, p0;
		if (m == 0 || n == 0) {
			return 0;
		}
		if (st1[m - 1] == st2[n - 1]) {
			//System.out.println(" --> (m-1,n-1)=>(" + (m - 1) + "," + (n - 1) + ")");
			p0 = lcsRecu(st1, st2, m - 1, n - 1);
			//System.out.println(" <-- (m-1,n-1)=>(" + (m - 1) + "," + (n - 1) + ")" + p0);
			return 1 + p0;
		} else {
			//System.out.println(" --> (m,n-1)=>(" + m + "," + (n - 1) + ")  (m-1,n)=>(" + (m - 1) + "," + n + ")");
			p1 = lcsRecu(st1, st2, m, n - 1);
			p2 = lcsRecu(st1, st2, m - 1, n);
			//System.out.println(" <-- (m,n-1)=>(" + m + "," + (n - 1) + ")" + p1 + "  (m-1,n)=>(" + (m - 1) + "," + n + ")" + p2);
			return Integer.max(p1, p2);
		}
	}
	
	/**
	 * @param st1
	 * @param st2
	 * @param m
	 * @param n
	 * @return
	 */
	public int lcsMemorize(char[] st1, char[] st2, int m, int n) {
		int[][] L = new int[m + 1][n + 1];
		System.out.println("------------Analysis - Action--------------------");
		for (int i = -1; i < m + 1; i++) {
			for (int j = -1; j < n + 1; j++) {
				if (i == -1 || j == -1) {
					if (i == -1) {
						if (j <= 0 || j > st2.length) {
							System.out.print("  \t ");
						} else {
							System.out.print(st2[j - 1] + "  \t");
						}
					} else {
						if (!(i <= 0 || i > st1.length)) {
							System.out.print(st1[i-1] + "   ");
						}
					}
				} else {
					if(i==0 && j==0) {
						System.out.print("    ");
					}
					System.out.print("("+i+","+j+")\t");
				}
			}
			System.out.println();
		}
		System.out.println("------------Actual Program- Action--------------------");
 
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				} else if (st1[i - 1] == st2[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
				} else {
					L[i][j] = Integer.max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		System.out.println();
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				System.out.print(L[i][j] + "   ");
			}
			System.out.println();
		}
		return L[m][n];
	}
}
