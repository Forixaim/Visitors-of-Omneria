package net.forixaim.vfo.world.entity.charlemagne;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.CharlemagneFlamiereAnims;
import net.forixaim.vfo.capabilities.weapons.OmneriaCategories;
import net.forixaim.vfo.events.advanced_bosses.DamageDealtEvent;
import net.forixaim.vfo.world.entity.charlemagne.ai.CharlemagneAttackString;
import net.forixaim.vfo.world.entity.charlemagne.ai.CharlemagneBrain;
import net.forixaim.vfo.world.entity.patches.FriendlyHumanoidNPCPatch;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.MobCombatBehaviors;
import yesman.epicfight.world.capabilities.entitypatch.Faction;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class CharlemagnePatch extends FriendlyHumanoidNPCPatch<Charlemagne>
{
	private static final UUID CloseGapUUID = UUID.fromString("eb18c5eb-19bf-4a71-9398-b49d9b510217");
	private static final List<CharlemagneAttackString> bossAttackString = Lists.newArrayList();

	public CharlemagneBrain brain;
	public CharlemagnePatch()
	{
		super(Faction.NEUTRAL);
	}

	@Override
	public boolean isLastAttackSuccess()
	{
		return super.isLastAttackSuccess();
	}

	public void fireDamageDealtEvent(DamageDealtEvent event)
	{
		//Give all control to the internal brain class.
		brain.onReceiveAttackConnection(event);
	}

	public void fireAttackAnimEndEvent()
	{
		brain.onAttackAnimationEnd();
	}

	@Override
	public void onConstructed(Charlemagne entityIn)
	{
		entityIn.patch = this;
		super.onConstructed(entityIn);
		brain = new CharlemagneBrain(entityIn, this);
	}

	@Override
	protected void setWeaponMotions()
	{
		this.weaponLivingMotions = Maps.newHashMap();
		this.weaponLivingMotions.put(CapabilityItem.WeaponCategories.GREATSWORD, ImmutableMap.of(
				CapabilityItem.Styles.TWO_HAND, Set.of(
						Pair.of(LivingMotions.WALK, Animations.BIPED_WALK_TWOHAND),
						Pair.of(LivingMotions.CHASE, Animations.BIPED_WALK_TWOHAND)
				)
		));
		this.weaponLivingMotions.put(OmneriaCategories.ORIGIN_JOYEUSE, ImmutableMap.of(
				CapabilityItem.Styles.COMMON, Set.of(
						Pair.of(LivingMotions.WALK, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_WALK),
						Pair.of(LivingMotions.IDLE, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_IDLE),
						Pair.of(LivingMotions.CHASE, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_WALK)
				)
		));

		this.weaponAttackMotions = Maps.newHashMap();
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.AXE, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_ONEHAND_TOOLS));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.HOE, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_ONEHAND_TOOLS));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.PICKAXE, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_ONEHAND_TOOLS));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.SHOVEL, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_ONEHAND_TOOLS));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.SWORD, ImmutableMap.of(CapabilityItem.Styles.ONE_HAND, MobCombatBehaviors.HUMANOID_ONEHAND_TOOLS, CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_DUAL_SWORD));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.GREATSWORD, ImmutableMap.of(CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_GREATSWORD));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.UCHIGATANA, ImmutableMap.of(CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_KATANA));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.LONGSWORD, ImmutableMap.of(CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_LONGSWORD));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.TACHI, ImmutableMap.of(CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_TACHI));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.SPEAR, ImmutableMap.of(CapabilityItem.Styles.ONE_HAND, MobCombatBehaviors.HUMANOID_SPEAR_ONEHAND, CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_SPEAR_TWOHAND));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.FIST, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_FIST));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.DAGGER, ImmutableMap.of(CapabilityItem.Styles.ONE_HAND, MobCombatBehaviors.HUMANOID_ONEHAND_DAGGER, CapabilityItem.Styles.TWO_HAND, MobCombatBehaviors.HUMANOID_TWOHAND_DAGGER));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.RANGED, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_FIST));
		this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.TRIDENT, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.HUMANOID_SPEAR_ONEHAND));
	}

	@Override
	public void initAnimator(Animator animator)
	{
		animator.addLivingAnimation(LivingMotions.IDLE, Animations.BIPED_IDLE);
		animator.addLivingAnimation(LivingMotions.WALK, Animations.BIPED_IDLE);
	}

	@Override
	public void updateMotion(boolean b)
	{
		super.updateMotion(b);
	}

	@Override
	public void tick(LivingEvent.LivingTickEvent event)
	{
		super.tick(event);
		if (brain != null)
			brain.receiveTickFire();
		if (this.currentLivingMotion == LivingMotions.CHASE)
		{
			Objects.requireNonNull(this.original.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(new AttributeModifier(CloseGapUUID, "chase", 3.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		else
		{
			if (Objects.requireNonNull(this.original.getAttribute(Attributes.MOVEMENT_SPEED)).getModifier(CloseGapUUID) != null)
				Objects.requireNonNull(this.original.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(CloseGapUUID);
		}
	}
}
