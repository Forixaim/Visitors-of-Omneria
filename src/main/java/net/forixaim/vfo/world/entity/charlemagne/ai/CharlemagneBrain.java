package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.mojang.logging.LogUtils;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.minecraft.world.entity.animal.IronGolem;

public class CharlemagneBrain
{
	private Charlemagne target;
	public CharlemagnePatch patch;
	private CharlemagneMode mode;

	private int tick = 0;
	private int seconds = 0;

	public CharlemagneBrain(final Charlemagne target, final CharlemagnePatch patch)
	{
		this.target = target;
		this.mode = CharlemagneMode.FRIENDLY;
		this.patch = patch;
	}

	public CharlemagneMode getMode()
	{
		return mode;
	}

	public void receiveTickFire()
	{
		tick++;
		if (mode.is(CharlemagneMode.DEFENSE))
			defenseTick();
		if (mode.is(CharlemagneMode.FRIENDLY))
			friendlyTick();
		if (mode.is(CharlemagneMode.DUELING))
			battleTick();
		if (tick >= 20)
		{
			tick = 0;
			seconds++;
			if (!target.level().isClientSide)
				LogUtils.getLogger().debug("a second has passed;");
		}
		if (seconds >= 60)
		{
			seconds = 0;
		}
	}

	private void battleTick()
	{

	}

	private void friendlyTick()
	{

	}

	private void defenseTick()
	{

	}
}
