package com.javamapper.entity;

import java.util.LinkedList;

public class TreeNode {
	static int currDrawLevel = -1;
	static int currSpaceCount = -1;
	static final int H_SPREAD = 3;
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public int depth = 0;
	public int level = 0;
	public int drawPos = 0;

	public TreeNode(int x) {
		val = x;
	}

	public static void drawTree(TreeNode root) {

		System.out.println("\n\nLevel order traversal of tree:");
		int depth = depth(root);
		setLevels(root, 0);

		int depthChildCount[] = new int[depth + 1];

		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root.left);
		q.add(root.right);

		// draw root first
		root.drawPos = (int) Math.pow(2, depth - 1) * H_SPREAD;
		currDrawLevel = root.level;
		currSpaceCount = root.drawPos;
		System.out.print(getSpace(root.drawPos) + root.val);

		while (!q.isEmpty()) {
			TreeNode ele = q.pollFirst();
			drawElement(ele, depthChildCount, depth, q);
			if (ele == null)
				continue;
			q.add(ele.left);
			q.add(ele.right);
		}
		System.out.println();
	}

	static void drawElement(TreeNode ele, int depthChildCount[], int depth, LinkedList<TreeNode> q) {
		if (ele == null)
			return;

		if (ele.level != currDrawLevel) {
			currDrawLevel = ele.level;
			currSpaceCount = 0;
			System.out.println();
			for (int i = 0; i < (depth - ele.level + 1); i++) {
				int drawn = 0;
				if (ele.parent.left != null) {
					drawn = ele.parent.drawPos - 2 * i - 2;
					System.out.print(getSpace(drawn) + "/");
				}
				if (ele.parent.right != null) {
					int drawn2 = ele.parent.drawPos + 2 * i + 2;
					System.out.print(getSpace(drawn2 - drawn) + "\\");
					drawn = drawn2;
				}

				TreeNode doneParent = ele.parent;
				for (TreeNode sibling : q) {
					if (sibling == null)
						continue;
					if (sibling.parent == doneParent)
						continue;
					doneParent = sibling.parent;
					if (sibling.parent.left != null) {
						int drawn2 = sibling.parent.drawPos - 2 * i - 2;
						System.out.print(getSpace(drawn2 - drawn - 1) + "/");
						drawn = drawn2;
					}

					if (sibling.parent.right != null) {
						int drawn2 = sibling.parent.drawPos + 2 * i + 2;
						System.out.print(getSpace(drawn2 - drawn - 1) + "\\");
						drawn = drawn2;
					}
				}
				System.out.println();
			}
		}
		int offset = 0;
		int numDigits = (int) Math.ceil(Math.log10(ele.val));
		if (ele.parent.left == ele) {
			ele.drawPos = ele.parent.drawPos - H_SPREAD * (depth - currDrawLevel + 1);
			// offset = 2;
			offset += numDigits / 2;
		} else {
			ele.drawPos = ele.parent.drawPos + H_SPREAD * (depth - currDrawLevel + 1);
			// offset = -2;
			offset -= numDigits;
		}

		System.out.print(getSpace(ele.drawPos - currSpaceCount + offset) + ele.val);
		currSpaceCount = ele.drawPos + numDigits / 2;
	}

	public static int depth(TreeNode n) {
		if (n == null)
			return 0;
		n.depth = 1 + Math.max(depth(n.left), depth(n.right));
		return n.depth;
	}

	public static int countNodes(TreeNode n) {
		if (n == null)
			return 0;
		return 1 + countNodes(n.left) + countNodes(n.right);
	}

	static void setLevels(TreeNode r, int level) {
		if (r == null)
			return;
		r.level = level;
		setLevels(r.left, level + 1);
		setLevels(r.right, level + 1);
	}

	static String getSpace(int i) {
		String s = "";
		while (i-- > 0)
			s += " ";
		return s;
	}
}
