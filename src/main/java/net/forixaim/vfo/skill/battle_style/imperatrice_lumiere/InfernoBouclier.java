package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.entity.eventlistener.HurtEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents Inferno Bulwark
 */
public class InfernoBouclier extends GuardSkill
{
	private static final int PARRY_WINDOW = 4;
	private static final UUID EVENT_UUID = UUID.fromString("c5547250-4aa6-44c4-a01e-cf4bd4f8e93b");

	public static Builder createInfernoBouclier()
	{
		return GuardSkill.createGuardBuilder().setCategory(SkillCategories.GUARD)
				.addGuardMotion(CapabilityItem.WeaponCategories.LONGSWORD, (itemCapability, playerPatch) ->
				{
					if (itemCapability.getStyle(playerPatch) == LumiereStyles.IMPERATRICE_SWORD || itemCapability.getStyle(playerPatch) == LumiereStyles.FORIXAIM_SWORD)
					{
						return LumiereSwordAnims.IMPERATRICE_GUARD;
					}
					return null;
				})
				.addGuardBreakMotion(CapabilityItem.WeaponCategories.LONGSWORD, (itemCapability, playerPatch) ->
				{
					if (itemCapability.getStyle(playerPatch) == LumiereStyles.IMPERATRICE_SWORD || itemCapability.getStyle(playerPatch) == LumiereStyles.FORIXAIM_SWORD)
					{
						return LumiereSwordAnims.IMPERATRICE_GUARD;
					}
					return null;
				});
	}
	public InfernoBouclier(Builder builder)
	{
		super(builder);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		super.onInitiate(container);
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SERVER_ITEM_USE_EVENT, EVENT_UUID, (event) -> {
			CapabilityItem itemCapability = event.getPlayerPatch().getHoldingItemCapability(InteractionHand.MAIN_HAND);

			if (this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapability, BlockType.GUARD) && this.isExecutableState(event.getPlayerPatch())) {
				event.getPlayerPatch().getOriginal().startUsingItem(InteractionHand.MAIN_HAND);
			}
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SERVER_ITEM_STOP_EVENT, EVENT_UUID, event -> {
			int lastActive = container.getDataManager().getDataValue(SkillDataKeys.LAST_ACTIVE.get());

			if (event.getPlayerPatch().getOriginal().tickCount - lastActive > PARRY_WINDOW * 2) {
				container.getDataManager().setData(SkillDataKeys.LAST_ACTIVE.get(), event.getPlayerPatch().getOriginal().tickCount);
			}
		});
	}

	@Override
	public void guard(SkillContainer container, CapabilityItem itemCapability, HurtEvent.Pre event, float knockback, float impact, boolean advanced)
	{
		if (this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapability, BlockType.ADVANCED_GUARD)) {
			DamageSource damageSource = event.getDamageSource();

			if (this.isBlockableSource(damageSource, true)) {
				ServerPlayer serverPlayer = event.getPlayerPatch().getOriginal();
				boolean successParrying = serverPlayer.tickCount - container.getDataManager().getDataValue(SkillDataKeys.LAST_ACTIVE.get()) < PARRY_WINDOW;
				float penalty = container.getDataManager().getDataValue(SkillDataKeys.PENALTY.get());
				event.getPlayerPatch().playSound(EpicFightSounds.CLASH.get(), -0.05F, 0.1F);
				EpicFightParticles.HIT_BLUNT.get().spawnParticleWithArgument(((ServerLevel)serverPlayer.level()), HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, serverPlayer, damageSource.getDirectEntity());

				if (successParrying) {
					event.setParried(true);
					penalty = 0f;
					knockback *= 0.4F;
					container.getDataManager().setData(SkillDataKeys.LAST_ACTIVE.get(), 0);
				} else {
					penalty += this.getPenalizer(itemCapability);
					container.getDataManager().setDataSync(SkillDataKeys.PENALTY.get(), penalty, serverPlayer);
				}

				if (damageSource.getDirectEntity() instanceof LivingEntity livingentity) {
					knockback += EnchantmentHelper.getKnockbackBonus(livingentity) * 0.1F;
				}

				event.getPlayerPatch().knockBackEntity(Objects.requireNonNull(damageSource.getDirectEntity()).position(), knockback);
				float consumeAmount = penalty * impact;
				boolean canAfford = event.getPlayerPatch().consumeForSkill(this, Resource.STAMINA, consumeAmount);

				BlockType blockType = successParrying ? BlockType.ADVANCED_GUARD : (canAfford ? BlockType.GUARD : BlockType.GUARD_BREAK);
				StaticAnimation animation = this.getGuardMotion(event.getPlayerPatch(), itemCapability, blockType);

				if (animation != null) {
					event.getPlayerPatch().playAnimationSynchronized(animation, 0);
				}

				if (blockType == BlockType.GUARD_BREAK) {
					event.getPlayerPatch().playSound(EpicFightSounds.NEUTRALIZE_MOBS.get(), 3.0F, 0.0F, 0.1F);
				}

				this.dealEvent(event.getPlayerPatch(), event, advanced);

				return;
			}
		}

		super.guard(container, itemCapability, event, knockback, impact, false);
	}

	@Override
	protected boolean isBlockableSource(DamageSource damageSource, boolean advanced) {
		return (damageSource.is(DamageTypeTags.IS_PROJECTILE) && advanced) || super.isBlockableSource(damageSource, false);
	}

	@Nullable
	protected StaticAnimation getGuardMotion(PlayerPatch<?> playerpatch, CapabilityItem itemCapability, BlockType blockType) {
		StaticAnimation animation = itemCapability.getGuardMotion(this, blockType, playerpatch);

		if (animation != null) {
			return animation;
		}

		if (blockType == BlockType.ADVANCED_GUARD) {
			StaticAnimation[] motions = (StaticAnimation[])this.getGuradMotionMap(blockType).getOrDefault(itemCapability.getWeaponCategory(), (a, b) -> null).apply(itemCapability, playerpatch);

			if (motions != null) {
				SkillDataManager dataManager = playerpatch.getSkill(this).getDataManager();
				int motionCounter = dataManager.getDataValue(SkillDataKeys.PARRY_MOTION_COUNTER.get());
				dataManager.setDataF(SkillDataKeys.PARRY_MOTION_COUNTER.get(), (v) -> v + 1);
				motionCounter %= motions.length;

				return motions[motionCounter];
			}
		}

		return super.getGuardMotion(playerpatch, itemCapability, blockType);
	}

	@Override
	protected boolean isAdvancedGuard() {
		return true;
	}

	@Override
	public List<WeaponCategory> getAvailableWeaponCategories() {
		return List.copyOf(this.advancedGuardMotions.keySet());
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		return executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE);
	}
}
