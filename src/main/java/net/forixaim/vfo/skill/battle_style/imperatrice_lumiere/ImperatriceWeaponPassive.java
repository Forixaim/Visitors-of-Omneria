package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.forixaim.bs_api.BattleArtsAPI;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.battle_style.BattleStyle;
import net.forixaim.efm_ex.skill.ExCapWeaponPassive;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.CharlemagneFlamiereAnims;
import net.forixaim.vfo.capabilities.weapons.OmneriaCategories;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.UUID;

public class ImperatriceWeaponPassive extends ExCapWeaponPassive
{
	private static final UUID EVENT_UUID = UUID.fromString("2b3a8bea-cf7e-4710-ba98-68dc0f9bce30");

	private static final AnimationProvider<AttackAnimation> FORIXAIM_DRAW = () -> (AttackAnimation) CharlemagneFlamiereAnims.TRUE_JOYEUSE_DRAW;

	public ImperatriceWeaponPassive(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		LogUtils.getLogger().debug(container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(container.getExecuter()).toString());
		if (ModList.get().isLoaded(BattleArtsAPI.MOD_ID))
		{
			SkillContainer battleStyleContainer = container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE);
			if (container.getExecuter() instanceof ServerPlayerPatch playerPatch && !battleStyleContainer.isEmpty())
			{
				if (battleStyleContainer.getSkill() instanceof BattleStyle battleStyle)
				{
					if (battleStyle.canModifyAttacks())
					{
						battleStyleContainer.requestExecute(playerPatch, null);
					}
					if (!battleStyle.getWeaponDrawAnimations().isEmpty())
					{
						for (Pair<WeaponCategory, AnimationProvider<StaticAnimation>> animationProviderPair : battleStyle.getWeaponDrawAnimations())
						{
							if (container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == animationProviderPair.getFirst())
							{
								if (animationProviderPair.getFirst() == OmneriaCategories.ORIGIN_JOYEUSE)
								{
									if (container.getExecuter().isLogicalClient() && Minecraft.getInstance().getUser().getUuid().equals("42479ed5a8f04967bfb17500577896a6"))
									{
										if (FORIXAIM_DRAW.get() != null)
										{
											container.getExecuter().playAnimationSynchronized(FORIXAIM_DRAW.get(), 0);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean shouldDeactivateAutomatically(PlayerPatch<?> executor)
	{
		return true;
	}
}
