package net.forixaim.vfo.world.entity.charlemagne.ai;

import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;

public class CharlemagneBrain
{
	private Charlemagne target;
	private CharlemagneMode mode;

	public CharlemagneBrain(final Charlemagne target)
	{
		this.target = target;
		this.mode = CharlemagneMode.FRIENDLY;
	}

	public CharlemagneMode getMode()
	{
		return mode;
	}

	public void receiveTickFire()
	{

	}
}
