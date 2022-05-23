package mindustry.content.unitTypes.air;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.meta.*;

public class Flare extends UnitType{
    public static final String name = "flare";

    public Flare(){
        super(name);
        speed = 3f;
        accel = 0.08f;
        drag = 0.01f;
        flying = true;
        health = 75;
        engineOffset = 5.5f;
        range = 140f;
        targetAir = false;
        //as default AI, flares are not very useful in core rushes, they attack nothing in the way
        playerTargetFlags = new BlockFlag[]{null};
        targetFlags = new BlockFlag[]{BlockFlag.generator, null};
        commandLimit = 4;
        circleTarget = true;
        hitSize = 7;

        weapons.add(new Weapon(){{
            y = 0f;
            x = 2f;
            reload = 13f;
            ejectEffect = Fx.casing1;
            bullet = new BasicBulletType(2.5f, 9){{
                width = 7f;
                height = 9f;
                lifetime = 45f;
                shootEffect = Fx.shootSmall;
                smokeEffect = Fx.shootSmallSmoke;
                ammoMultiplier = 2;
            }};
            shootSound = Sounds.pew;
        }});
    }
}
