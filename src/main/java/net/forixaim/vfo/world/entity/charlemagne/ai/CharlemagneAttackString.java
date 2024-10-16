package net.forixaim.vfo.world.entity.charlemagne.ai;

import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.minecraft.util.RandomSource;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class CharlemagneAttackString
{
	public final List<List<CharlemagneAttack>> Attacks = Lists.newArrayList();
	private final CharlemagnePatch attacker;
	public boolean connected;
	public boolean firing;
	public int position;

	@SafeVarargs
	public CharlemagneAttackString(CharlemagnePatch attacker, List<CharlemagneAttack>... attacks)
	{
		this.Attacks.addAll(List.of(attacks));
		this.attacker = attacker;
		this.connected = false;
		this.firing = false;
		this.position = 0;
	}

	public void fire()
	{
		RandomSource source = attacker.getOriginal().getRandom();
		if (position >= Attacks.size())
			return;
		List<CharlemagneAttack> Buffer1 = Attacks.get(position);
		CharlemagneAttack BufferAnim = null;
		if (Buffer1.size() > 1)
		{
			BufferAnim = Buffer1.get(source.nextInt(0, Buffer1.size()));
		}
		else
		{
			BufferAnim = Buffer1.get(0);
		}
		if (BufferAnim != null)
		{
			if (BufferAnim.getSuspectConfidence() > 0.94 || connected)
				BufferAnim.Fire(attacker);
			else if (BufferAnim.getSuspectConfidence() > 0.82)
				BufferAnim.Feint(attacker);
			firing = true;
		}
	}

	public void reset()
	{
		position = 0;
		firing = false;
	}
}
