package turniplabs.farlanders;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.material.ArmorMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.useless.dragonfly.helper.ModelHelper;
import turniplabs.farlanders.entity.EntityEyes;
import turniplabs.farlanders.entity.EntityFarlander;
import turniplabs.farlanders.entity.render.ModelEyes;
import turniplabs.farlanders.entity.render.ModelFarlander;
import turniplabs.farlanders.entity.render.RendererEyes;
import turniplabs.farlanders.entity.render.RendererFarlander;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;


public class Farlanders implements GameStartEntrypoint {
    public static final String MOD_ID = "farlanders";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Item itemLens;
	public static Item itemArmorGoggles;
	public static ArmorMaterial materialGoggles;

	@Override
	public void beforeGameStart() {
		materialGoggles  = ArmorHelper.createArmorMaterial(MOD_ID, "goggles", 120, 70.0f, 70.0f, 70.0f, 70.0f);

		itemLens = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/lens")
			.build(new Item("lens", FarlandersConfig.cfg.getInt("Item IDs.farlanderLens")));

		itemArmorGoggles = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/armor_goggles")
			.build(new ItemArmor("armor.helmet.goggles", FarlandersConfig.cfg.getInt("Item IDs.farlanderGoggles"), materialGoggles, 0));

		EntityHelper.createEntity(EntityFarlander.class,FarlandersConfig.cfg.getInt("Farlanders.farlanderID"), "Farlander", () -> new RendererFarlander(ModelHelper.getOrCreateEntityModel(MOD_ID, "entity/farlander.json", ModelFarlander.class)));
		EntityHelper.createEntity(EntityEyes.class, FarlandersConfig.cfg.getInt("Farlanders.eyesID"), "Eyes", () -> new RendererEyes(ModelHelper.getOrCreateEntityModel(MOD_ID, "entity/eyes.json", ModelEyes.class)));

		SoundHelper.addSound(MOD_ID, "whispers.wav");
		SoundHelper.addSound(MOD_ID, "fwoosh.wav");

		LOGGER.info("Farlanders initialized. Stay safe...");
	}

	@Override
	public void afterGameStart() {

	}

}
