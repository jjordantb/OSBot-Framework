package io.paratek.scripts.ParaOrbs.air;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public interface AirOps {

    Area ORB_AREA = new Area(3080, 3578, 3095, 3563);
    Area BANK_AREA = new Area(3083, 3500, 3104, 3485);

    Position[] toTrapDoor = {
            new Position(3093, 3490, 0),
            new Position(3093, 3482, 0),
            new Position(3094, 3472, 0),
            new Position(3094, 3472, 0)
    };

    Position[] dungeonPath = {
            new Position(3096, 9868, 0),
            new Position(3096, 9878, 0),
            new Position(3096, 9888, 0),
            new Position(3096, 9898, 0),
            new Position(3097, 9907, 0),
            new Position(3107, 9908, 0),
            new Position(3115, 9909, 0),
            new Position(3125, 9910, 0),
            new Position(3131, 9911, 0),
            new Position(3132, 9921, 0),
            new Position(3132, 9921, 0),
            new Position(3132, 9931, 0),
            new Position(3132, 9941, 0),
            new Position(3133, 9945, 0),
            new Position(3124, 9950, 0),
            new Position(3120, 9953, 0),
            new Position(3110, 9954, 0),
            new Position(3106, 9954, 0),
            new Position(3097, 9958, 0),
            new Position(3094, 9960, 0),
            new Position(3088, 9968, 0),
            new Position(3088, 9969, 0)
    };

    String OBY = "Obelisk of Air";

}
