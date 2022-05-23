package mindustry.content.unitTypes.air;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;

public class Horizon extends UnitType{
    public static final String name = "horizon";

    public Horizon(){
        super(name);
        health = 340;
        speed = 1.65f;
        accel = 0.08f;
        drag = 0.016f;
        flying = true;
        hitSize = 9f;
        targetAir = false;
        engineOffset = 7.8f;
        range = 140f;
        faceTarget = false;
        armor = 3f;
        //do not rush core, attack closest
        playerTargetFlags = new BlockFlag[]{null};
        targetFlags = new BlockFlag[]{BlockFlag.factory, null};
        commandLimit = 5;
        circleTarget = true;
        ammoType = new ItemAmmoType(Items.graphite);

        weapons.add(new Weapon(){{
            minShootVelocity = 0.75f;
            x = 3f;
            shootY = 0f;
            reload = 12f;
            shootCone = 180f;
            ejectEffect = Fx.none;
            inaccuracy = 15f;
            ignoreRotation = true;
            shootSound = Sounds.none;
            bullet = new BombBulletType(27f, 25f){{
                width = 10f;
                height = 14f;
                hitEffect = Fx.flakExplosion;
                shootEffect = Fx.none;
                smokeEffect = Fx.none;

                status = StatusEffects.blasted;
                statusDuration = 60f;
            }};
        }});
    }
}
