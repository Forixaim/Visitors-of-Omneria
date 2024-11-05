package net.forixaim.vfo.util;

import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class Tree<T>
{
	private Node<T> root;

	public Tree(T rootData) {
		root = new Node<T>();
		root.data = rootData;
		root.children = Lists.newArrayList();
	}

	public static Tree<?> newTree(Object root)
	{
		return new Tree<>(root);
	}


	public static class Node<T>
	{
		private T data;
		private Node<T> parent;
		private List<Node<T>> children;
	}
}
