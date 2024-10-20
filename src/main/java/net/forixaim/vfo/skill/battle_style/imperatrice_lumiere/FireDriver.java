package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import net.forixaim.bs_api.AnimationHelpers;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordSmashAttacks;
import net.forixaim.vfo.registry.ItemRegistry;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.Objects;
import java.util.UUID;

public class FireDriver extends WeaponInnateSkill
{

	private static final UUID EVENT_UUID = UUID.fromString("9ed5a11f-c7b2-4679-99db-0a4c8de2f5a3");
	private static final AnimationProvider<AttackAnimation> NEUTRAL_SMASH = () -> (AttackAnimation) LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FIRE_DRIVER;
	private static final AnimationProvider<AttackAnimation> DOWN_SMASH =  () -> (AttackAnimation) LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FLARE_ERUPTION;
	private static final AnimationProvider<AttackAnimation> NEUTRAL_AERIAL_SMASH = () -> (AttackAnimation) LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FLAMING_ATMOSPHERE;
	private static final AnimationProvider<AttackAnimation> SOLAR_FLARE = () -> (AttackAnimation) LumiereSwordSmashAttacks.IMPERATRICE_SWORD_SOLAR_FLARE;

	public FireDriver(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	public static Builder createBlazeStinger()
	{
		return (new Builder().setCategory(SkillCategories.WEAPON_INNATE).setResource(Resource.STAMINA));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public FriendlyByteBuf gatherArguments(LocalPlayerPatch executer, ControllEngine controllEngine)
	{
		return ArgumentGatherers.UniversalDirectionalInput(executer, controllEngine);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Object getExecutionPacket(LocalPlayerPatch executer, FriendlyByteBuf args)
	{
		return ArgumentGatherers.DirectionalExecutionPacket(executer, args, this);
	}

	@Override
	public void onInitiate(SkillContainer container) {
		super.onInitiate(container);

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, event ->
		{
			if (event.getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FLAMING_ATMOSPHERE || event.getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FIRE_DRIVER || event.getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FLARE_ERUPTION ||  event.getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_SOLAR_FLARE)
				container.getDataManager().setDataSync(DatakeyRegistry.CHARGE_EXECUTING.get(), false, event.getPlayerPatch().getOriginal());
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.HURT_EVENT_PRE, EVENT_UUID, event ->
		{
			if (container.getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get()))
			{
				container.getDataManager().setDataSync(DatakeyRegistry.CHARGE_EXECUTING.get(), false, event.getPlayerPatch().getOriginal());
			}
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID, (event) -> {
			if (event.getDamageSource().getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FLARE_ERUPTION)
			{
				if (EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class) != null)
				{
					EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class).knockBackEntity(event.getTarget().getPosition(0), 1000);
				}
			}

			if (event.getDamageSource().getAnimation() == LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FIRE_DRIVER) {
				ValueModifier damageModifier = ValueModifier.empty();
				this.getProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, this.properties.get(0)).ifPresent(damageModifier::merge);
				damageModifier.merge(ValueModifier.multiplier(0.8F));
				if (container.getExecuter().getOriginal().getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.ORIGIN_JOYEUSE.get()))
				{
					event.getTarget().setRemainingFireTicks(event.getTarget().getRemainingFireTicks() + 20);
				}
				if (event.getTarget().hasEffect(MobEffects.DAMAGE_RESISTANCE) || event.getTarget().hasEffect(MobEffects.FIRE_RESISTANCE))
				{
					if (event.getTarget().hasEffect(MobEffects.DAMAGE_RESISTANCE))
					{
						int duration = Objects.requireNonNull(event.getTarget().getEffect(MobEffects.DAMAGE_RESISTANCE)).getDuration();
						int potency = Objects.requireNonNull(event.getTarget().getEffect(MobEffects.DAMAGE_RESISTANCE)).getAmplifier() - 1;
						event.getTarget().removeEffect(MobEffects.DAMAGE_RESISTANCE);
						if (potency >= 0)
						{
							event.getTarget().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, potency));
						}
					}
					event.getDamageSource().addRuntimeTag(DamageTypeTags.BYPASSES_ENCHANTMENTS);
					event.getDamageSource().addRuntimeTag(DamageTypeTags.BYPASSES_ARMOR);
					event.getTarget().removeEffect(MobEffects.FIRE_RESISTANCE);
					event.getPlayerPatch().playSound(SoundRegistry.CRITICAL_HIT.get(), 0, 0);
				}
			}
		});
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		if (executor.isLogicalClient())
			return super.canExecute(executor);
		ItemStack itemstack = executor.getOriginal().getItemInHand(InteractionHand.MAIN_HAND);
		if (AnimationHelpers.isInAir(executor))
		{
			return EpicFightCapabilities.getItemStackCapability(itemstack).getInnateSkill(executor, itemstack) == this && executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
					!executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
					!executor.getSkill(this).getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get()) && !executor.getSkill(this).getDataManager().getDataValue(DatakeyRegistry.CHARGE_AERIAL.get());
		}
		return EpicFightCapabilities.getItemStackCapability(itemstack).getInnateSkill(executor, itemstack) == this && executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
				!executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
				!executor.getSkill(this).getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get());
	}

	@Override
	public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args)
	{
		int fw = args.readInt();
		int sw = args.readInt();

		executor.getSkill(this).getDataManager().setDataSync(DatakeyRegistry.CHARGE_EXECUTING.get(), true, executor.getOriginal());

		if (executor.getSkill(SkillSlots.BASIC_ATTACK).hasSkill(OmneriaSkills.FLARE_BLITZ) && executor.getSkill(SkillSlots.BASIC_ATTACK).getDataManager().hasData(DatakeyRegistry.BLAZE_COMBO.get()))
		{
			executor.getSkill(SkillSlots.BASIC_ATTACK).getDataManager().setData(DatakeyRegistry.BLAZE_COMBO.get(), 0);
		}
		if (AnimationHelpers.isInAir(executor))
		{
			executor.getSkill(this).getDataManager().setDataSync(DatakeyRegistry.CHARGE_AERIAL.get(), true, executor.getOriginal());
			executor.playAnimationSynchronized(NEUTRAL_AERIAL_SMASH.get(), 0);
		}
		else
		{
			if (fw == 1)
			{
				executor.playAnimationSynchronized(SOLAR_FLARE.get(), 0);
			}
			else if (executor.getOriginal().isShiftKeyDown())
			{
				executor.playAnimationSynchronized(DOWN_SMASH.get(), 0);
			}
			else
			{
				executor.playAnimationSynchronized(NEUTRAL_SMASH.get(), 0.0F);
			}
		}

		super.executeOnServer(executor, args);
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		PlayerEventListener eventListener = container.getExecuter().getEventListener();
		eventListener.removeListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
		eventListener.removeListener(PlayerEventListener.EventType.HURT_EVENT_PRE, EVENT_UUID);
		eventListener.removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID);
	}

	@Override
	public void updateContainer(SkillContainer container)
	{
		if (!AnimationHelpers.isInAir(container.getExecuter()) && container.getDataManager().getDataValue(DatakeyRegistry.CHARGE_AERIAL.get()))
		{
			if (!container.getExecuter().isLogicalClient())
			{
				container.getDataManager().setDataSync(DatakeyRegistry.CHARGE_AERIAL.get(), false, (ServerPlayer) container.getExecuter().getOriginal());
			}
		}
	}
}
