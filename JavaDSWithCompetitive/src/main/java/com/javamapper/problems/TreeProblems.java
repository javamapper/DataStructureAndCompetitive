package com.javamapper.problems;

import com.javamapper.entity.TreeNode;

public class TreeProblems {
	static int preIdx = 0;
	/*
	 * 
	 * 
                                                                                               4
                                                                                              /    \
                                                                                            /        \
                                                                                          /            \
                                                                                        /                \
                                                                                      /                    \
                                                                                    /                        \
                                                                              10                                  11
                                                                                \                               /   \
                                                                                  \                           /       \
                                                                                    \                       /           \
                                                                                      \                   /               \
                                                                                        \               /                   \
                                                                                           13     5                             2
                                                                                               \     \
                                                                                                 \     \
                                                                                                   \     \
                                                                                                     \     \
                                                                                                        7     8
                                                                                                           \
                                                                                                             \
                                                                                                               \
                                                                                                                 9
                                                                                                                /    \
                                                                                                              /        \
                                                                                                             12          3
	 */
	int[] preorder = { 4, 10, 13, 7, 9, 12, 3, 11, 5, 8, 2 };
	int[] inorder = { 10, 13, 7, 12, 9, 3, 4, 5, 8, 11, 2 };

	public void buildTreeByOrderedListM1() {
		TreeNode root = method1(0, 0, inorder.length - 1, preorder, inorder);
		method2(0, inorder.length - 1, preorder, inorder);
		TreeNode.drawTree(root);
	}

	public void buildTreeByOrderedListM2() {
		TreeNode root = method2(0, inorder.length - 1, preorder, inorder);
		TreeNode.drawTree(root);
	}
	/**
	 * @param preStart
	 * @param inStart
	 * @param inEnd
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	private static TreeNode method1(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {

		if (preStart > preorder.length - 1 || inStart > inEnd)
			return null;
		TreeNode root = new TreeNode(preorder[preStart]);
		int inIndex = 0;

		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}

		root.left = method1(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		if (root.left != null) {
			root.left.parent = root;
		}
		root.right = method1(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		if (root.right != null) {
			root.right.parent = root;
		}
		return root;
	}

	/**
	 * @param inStart
	 * @param inEnd
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	private static TreeNode method2(int inStart, int inEnd, int[] preorder, int[] inorder) {

		if (preIdx > preorder.length - 1 || inStart > inEnd)
			return null;
		TreeNode root = new TreeNode(preorder[preIdx++]);
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = method2(inStart, inIndex - 1, preorder, inorder);
		if (root.left != null) {
			root.left.parent = root;
		}
		root.right = method2(inIndex + 1, inEnd, preorder, inorder);
		if (root.right != null) {
			root.right.parent = root;
		}
		return root;
	}

}
