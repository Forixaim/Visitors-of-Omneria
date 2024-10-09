package net.forixaim.vfo.world.entity.charlemagne;

public enum CharlemagneMode
{
	FRIENDLY,
	DUELING,
	DEFENSE;

	public boolean is(CharlemagneMode mode)
	{
		return this == mode;
	}
}
