package mindustry.content.unitTypes.air;

import mindustry.ai.types.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Mono extends UnitType{
    public static final String name = "mono";

    public Mono(){
        super(name);
        defaultController = MinerAI::new;

        flying = true;
        drag = 0.06f;
        accel = 0.12f;
        speed = 1.5f;
        health = 100;
        engineSize = 1.8f;
        engineOffset = 5.7f;
        range = 50f;
        isCounted = false;

        ammoType = new PowerAmmoType(500);

        mineTier = 1;
        mineSpeed = 2.5f;
    }
}
