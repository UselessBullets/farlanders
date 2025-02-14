package turniplabs.farlanders.mixin;

import net.minecraft.core.Global;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.farlanders.Farlanders;
import turniplabs.farlanders.FarlandersClient;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin extends Entity {
	@Unique
	private int durabilityTimer = 0;

	@Shadow
	public final InventoryPlayer inventory = new InventoryPlayer((EntityPlayer)(Object)this);

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Unique
	public boolean hasNightVision() {
		return inventory.armorInventory[3] != null && inventory.armorInventory[3].itemID == Farlanders.itemArmorGoggles.id;
	}

	@Inject(method = "onLivingUpdate", at = @At("TAIL"))
	private void farlanders_nightVision(CallbackInfo ci) {
		if (!world.isClientSide){
			durabilityTimer++;
			if (hasNightVision()){
				if (durabilityTimer > 100) {
					durabilityTimer = 0;
					inventory.damageArmor(1, 3);
				}
			}
		}
		if (!Global.isServer){
			FarlandersClient.fullbrightStuff(hasNightVision());
		}

	}
}
