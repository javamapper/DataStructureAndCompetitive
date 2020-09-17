package com.javamapper.problems;

import java.util.Arrays;
import java.util.List;

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

	/**
	 * 
	 */
	public void bildBSTTreeRec() {
		List<Integer> treeElements = Arrays.asList(50, 30, 70, 20, 25, 60, 10, 5, 75, 90);
		TreeNode root=null;
		for (Integer ele : treeElements) {
			  root = insertBstTreeRec(root,ele);
		}
		TreeNode.drawTree(root);
	}
	
	/**
	 * 
	 */
	public void bildBSTTreeIter() {
		List<Integer> treeElements = Arrays.asList(50, 30, 70, 20, 25, 60, 10, 5, 75, 90);
		TreeNode root = null;
		for (Integer ele : treeElements) {
			root = insertBstTreeItr(root, ele);
		}
		TreeNode.drawTree(root);
	}
	private TreeNode insertBstTreeRec(TreeNode root, Integer ele) {
		if (root == null) {
			TreeNode node = new TreeNode(ele);
			return node;
		} else if (root.val < ele) {
			root.right = insertBstTreeRec(root.right, ele);
			if (root.right != null) {
				root.right.parent = root;
			}
		} else if (root.val > ele) {
			root.left = insertBstTreeRec(root.left, ele);
			if (root.left != null) {
				root.left.parent = root;
			}
		}
		return root;
	}

	private TreeNode insertBstTreeItr(TreeNode root, Integer ele) {
		TreeNode temp = null;
		TreeNode rootPoint=root;
		if (root == null) {
			TreeNode node = new TreeNode(ele);
			return node;
		}
		while (root != null) {
			temp = root;
			if (root.val < ele) {
				root = root.right;
			} else if (root.val > ele) {
				root = root.left;
			}
		}
		if (temp == null)
			return root;
		TreeNode newNode = new TreeNode(ele);
		if (temp.val > ele) {
			temp.left = newNode;
			newNode.parent=temp;
		} else {
			temp.right = newNode;
			newNode.parent=temp;
		}
		return rootPoint;
	}
}
