package turniplabs.farlanders;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Unique;

public class FarlandersClient {
	private static boolean toggledFullBright = false;
	private static Boolean gameFullBright = null;
	public static void fullbrightStuff(boolean hasNightVision){
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		if (mc != null){
			if (gameFullBright == null){
				gameFullBright = mc.fullbright;
			}
			if (hasNightVision){
				if (!toggledFullBright && mc.fullbright)
					gameFullBright = true;

				if (!toggledFullBright) {
					if (!mc.fullbright) {
						mc.fullbright = true;
						mc.renderGlobal.loadRenderers();
					}
					toggledFullBright = true;
				}

				if (!mc.fullbright) {
					gameFullBright = !gameFullBright;
					mc.fullbright = true;
					mc.renderGlobal.loadRenderers();
				}
			} else {
				if (toggledFullBright) {
					mc.fullbright = gameFullBright;
					toggledFullBright = false;
					mc.renderGlobal.loadRenderers();
				}
			}
		}
	}
}
