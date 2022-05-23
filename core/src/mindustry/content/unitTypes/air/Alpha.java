package mindustry.content.unitTypes.air;

import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.type.*;

public class Alpha extends UnitType{
    public static final String name = "alpha";

    public Alpha(){
        super(name);
        defaultController = BuilderAI::new;
        isCounted = false;

        lowAltitude = true;
        flying = true;
        mineSpeed = 6.5f;
        mineTier = 1;
        buildSpeed = 0.5f;
        drag = 0.05f;
        speed = 3f;
        rotateSpeed = 15f;
        accel = 0.1f;
        itemCapacity = 30;
        health = 150f;
        engineOffset = 6f;
        hitSize = 8f;
        commandLimit = 3;
        alwaysUnlocked = true;

        weapons.add(new Weapon("small-basic-weapon"){{
            reload = 17f;
            x = 2.75f;
            y = 1f;
            top = false;
            ejectEffect = Fx.casing1;

            bullet = new BasicBulletType(2.5f, 11){{
                width = 7f;
                height = 9f;
                lifetime = 60f;
                shootEffect = Fx.shootSmall;
                smokeEffect = Fx.shootSmallSmoke;
                buildingDamageMultiplier = 0.01f;
            }};
        }});
    }
}
