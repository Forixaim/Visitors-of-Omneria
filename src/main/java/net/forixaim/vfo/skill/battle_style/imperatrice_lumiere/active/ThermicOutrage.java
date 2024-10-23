package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active;

import net.forixaim.bs_api.battle_arts_skills.active.burst_arts.BurstArt;
import yesman.epicfight.skill.Skill;

/**
 * Skill Description: Outrage bar increases with damage taken, decreases with damage dealt. When going into Outrage, all attacks ignore guard, dodge, and any form of invincibility as well as setting the opponent on fire. You lose outrage passively but also lose outrage when landing hits. You lose more outrage when landing higher damage hits. You gain outrage regardless if you are in the state or not. (70% efficiency while skill is active)
 */
public class ThermicOutrage extends BurstArt
{
	public ThermicOutrage(Builder<? extends Skill> builder)
	{
		super(builder);
	}
}
