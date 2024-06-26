package turniplabs.farlanders.entity.crafting;

import net.minecraft.core.item.Item;
import turniplabs.farlanders.Farlanders;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class FarlandersRecipes implements RecipeEntrypoint {

	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shaped(Farlanders.MOD_ID)
			.setShape(
				"111",
				"1 1",
				"2 2")
			.addInput('1', Item.ingotGold)
			.addInput('2', Farlanders.itemLens)
			.create("goggles", Farlanders.itemArmorGoggles.getDefaultStack());
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(Farlanders.MOD_ID);
	}
}
