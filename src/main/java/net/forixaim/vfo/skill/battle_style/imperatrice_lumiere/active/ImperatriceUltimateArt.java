package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active;

import com.google.common.collect.Lists;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.active.ultimate_arts.UltimateArt;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.Ultimate;
import net.forixaim.vfo.capabilities.weapons.OmneriaCategories;
import net.forixaim.vfo.registry.ItemRegistry;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.effect.EpicFightMobEffects;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.List;
import java.util.UUID;

public class ImperatriceUltimateArt extends UltimateArt
{
	private static final UUID EVENT_UUID = UUID.fromString("9febc8c6-a0b3-4ee8-8da0-65cccb5a3ca0");
	private static final AnimationProvider<AttackAnimation> TRY_ANIMATION = () -> (AttackAnimation) Ultimate.IMPERATRICE_ULTIMATE_TRY;
	private static final AnimationProvider<AttackAnimation> FLARE_BLADE_CLEAVE = () -> (AttackAnimation) Ultimate.IMPERATRICE_FLARE_BLADE_CLEAVE;
	private static final AnimationProvider<AttackAnimation> FLARIAN_IGNITION = () -> (AttackAnimation) Ultimate.IMPERATRICE_SWORD_FLARIAN_IGNITION;
	private static final List<WeaponCategory> ALLOWED_WEAPONS = Lists.newArrayList(
			CapabilityItem.WeaponCategories.SWORD,
			CapabilityItem.WeaponCategories.GREATSWORD,
			CapabilityItem.WeaponCategories.LONGSWORD,
			CapabilityItem.WeaponCategories.TACHI,
			CapabilityItem.WeaponCategories.UCHIGATANA,
			OmneriaCategories.DERIVED_JOYEUSE,
			OmneriaCategories.ORIGIN_JOYEUSE
	);

	public ImperatriceUltimateArt(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		PlayerEventListener listener = container.getExecuter().getEventListener();
		listener.addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID, event ->
				{
					if (event.getDamageSource().getAnimation() == FLARE_BLADE_CLEAVE.get() || event.getDamageSource().getAnimation() == FLARIAN_IGNITION.get())
					{
						LivingEntityPatch<?> enemyPatch = EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class);
						if (enemyPatch != null)
							enemyPatch.playSound(SoundRegistry.IMPERATRICE_ANTI_CHEESE.get(), 2, 0, 0);
						if (event.getDamageSource().getDamageModifier().getSetter() >= event.getTarget().getHealth() && enemyPatch != null)
						{
							enemyPatch.playSound(SoundRegistry.FATAL_ULTIMATE_HIT.get(), 3, 0,0);
						}
					}
					if (event.getDamageSource().getAnimation() == TRY_ANIMATION.get() && !isConnected(container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE)))
					{
						LivingEntityPatch<?> enemyPatch = EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class);
						if (enemyPatch != null)
						{
							enemyPatch.knockBackEntity(event.getTarget().getPosition(0), 10);
						}
						else
						{
							event.getTarget().knockback(10, 5,0);
						}
						event.getPlayerPatch().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get(), true, event.getPlayerPatch().getOriginal());
					}
					else
					{
						if (event.getPlayerPatch().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE))
							event.getPlayerPatch().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get(), false, event.getPlayerPatch().getOriginal());
					}
				});
		listener.addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, event ->
		{
			if (event.getAnimation() == TRY_ANIMATION.get() && isConnected(container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE)))
			{
				executeOnServer(event.getPlayerPatch(), null);
			}
			else if (event.getAnimation() == TRY_ANIMATION.get() && !isConnected(container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE)))
			{
				event.getPlayerPatch().getOriginal().setInvulnerable(false);
				container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get(), false, event.getPlayerPatch().getOriginal());
			}
			if (event.getAnimation() == FLARE_BLADE_CLEAVE.get())
			{
				container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get(), false, event.getPlayerPatch().getOriginal());
			}
		});
	}

	private boolean weaponCategoryCheck(PlayerPatch<?> playerPatch)
	{
		boolean test = false;
		for (WeaponCategory category : ImperatriceUltimateArt.ALLOWED_WEAPONS)
		{
			if (playerPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == category)
				test = true;
		}
		return test;
	}

	private boolean isConnected(SkillContainer container)
	{
		return container.hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE) && container.getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get());
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		SkillDataManager BattleStyleManager = executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager();
		if (executor.getOriginal().isCreative())
		{
			return this.weaponCategoryCheck(executor) &&
					!BattleStyleManager.getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
					executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE) &&
					executor.getOriginal().onGround();
		}
		return this.weaponCategoryCheck(executor) &&
				executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE) &&
				!BattleStyleManager.getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get()) &&
				BattleStyleManager.getDataValue(DatakeyRegistry.FLARE_BURST.get()) &&
				BattleStyleManager.getDataValue(DatakeyRegistry.HEAT.get()) >= 700 &&
				executor.getOriginal().onGround();

	}

	@Override
	public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args)
	{
		executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get(), true, executor.getOriginal());
		if (executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get()))
		{
			executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get(), false, executor.getOriginal());
			if (executor.getOriginal().getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.ORIGIN_JOYEUSE.get()))
			{
				executor.playAnimationSynchronized(FLARE_BLADE_CLEAVE.get(), 0);
			}
			else
			{
				executor.playAnimationSynchronized(FLARIAN_IGNITION.get(), 0);
			}
		}
		else
		{
			if (!executor.getOriginal().isCreative())
				executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.HEAT.get(), executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()) - 700, executor.getOriginal());
			executor.getOriginal().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 400));

			executor.playSound(SoundRegistry.ULTIMATE_ART_EXECUTE.get(), 0, 0);
			executor.playAnimationSynchronized(TRY_ANIMATION.get(), 0);
		}
	}
}
