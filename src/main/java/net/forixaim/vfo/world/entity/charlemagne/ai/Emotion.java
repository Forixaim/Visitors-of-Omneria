package net.forixaim.vfo.world.entity.charlemagne.ai;

import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;

public enum Emotion
{
	NEUTRAL,
	SERIOUS;

	public boolean is(Emotion mode)
	{
		return this == mode;
	}
}
