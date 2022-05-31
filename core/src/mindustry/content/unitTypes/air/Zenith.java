package mindustry.content.unitTypes.air;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;

public class Zenith extends UnitType{
    public static final String name = "zenith";

    public Zenith(){
        super(name);
        health = 700;
        speed = 1.7f;
        accel = 0.04f;
        drag = 0.016f;
        flying = true;
        range = 140f;
        hitSize = 20f;
        lowAltitude = true;
        forceMultiTarget = true;
        armor = 5f;

        targetFlags = new BlockFlag[]{BlockFlag.launchPad, BlockFlag.storage, BlockFlag.battery, null};
        engineOffset = 12f;
        engineSize = 3f;
        ammoType = new ItemAmmoType(Items.graphite);

        weapons.add(new Weapon("zenith-missiles"){{
            reload = 40f;
            x = 7f;
            rotate = true;
            shake = 1f;
            shots = 2;
            inaccuracy = 5f;
            velocityRnd = 0.2f;
            shootSound = Sounds.missile;

            bullet = new MissileBulletType(3f, 14){{
                width = 8f;
                height = 8f;
                shrinkY = 0f;
                drag = -0.003f;
                homingRange = 60f;
                keepVelocity = false;
                splashDamageRadius = 25f;
                splashDamage = 15f;
                lifetime = 50f;
                trailColor = Pal.unitBack;
                backColor = Pal.unitBack;
                frontColor = Pal.unitFront;
                hitEffect = Fx.blastExplosion;
                despawnEffect = Fx.blastExplosion;
                weaveScale = 6f;
                weaveMag = 1f;
            }};
        }});
    }
}
