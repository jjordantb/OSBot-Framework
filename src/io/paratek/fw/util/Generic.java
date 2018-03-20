package io.paratek.fw.util;

import io.paratek.fw.ParaScript;
import org.osbot.rs07.api.map.Position;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class Generic {

    public static boolean staminaEnabled(final ParaScript ctx) {
        return (ctx.configs.get(638) << 11) != 0;
    }

    public static Position getDestination(ParaScript ctx) {
        int destX = ctx.client.accessor.getDestinationX();
        int destY = ctx.client.accessor.getDestinationY();
        return new Position(
                destX == 0 ? -1 : destX + ctx.client.accessor.getMapBaseX(),
                destY == 0 ? -1 : destY + ctx.client.accessor.getMapBaseY(),
                ctx.client.accessor.getPlane()
        );
    }

}
