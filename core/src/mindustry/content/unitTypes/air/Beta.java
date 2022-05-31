package mindustry.content.unitTypes.air;

import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.type.*;

public class Beta extends UnitType{
    public static final String name = "beta";

    public Beta(){
        super(name);
        defaultController = BuilderAI::new;
        isCounted = false;

        flying = true;
        mineSpeed = 7f;
        mineTier = 1;
        buildSpeed = 0.75f;
        drag = 0.05f;
        speed = 3.3f;
        rotateSpeed = 17f;
        accel = 0.1f;
        itemCapacity = 50;
        health = 170f;
        engineOffset = 6f;
        hitSize = 9f;
        rotateShooting = false;
        lowAltitude = true;
        commandLimit = 4;

        weapons.add(new Weapon("small-mount-weapon"){{
            top = false;
            reload = 20f;
            x = 3f;
            y = 0.5f;
            rotate = true;
            shots = 2;
            shotDelay = 4f;
            spacing = 0f;
            ejectEffect = Fx.casing1;

            bullet = new BasicBulletType(3f, 11){{
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
