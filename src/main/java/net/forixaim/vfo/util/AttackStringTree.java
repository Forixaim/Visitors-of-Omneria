package net.forixaim.vfo.util;

public class AttackStringTree<T, R> extends Tree<T>
{
	private R predicateObject;

	public AttackStringTree(TreeBuilder<T> builder)
	{
		super(builder);
	}
}
