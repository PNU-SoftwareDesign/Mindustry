package mindustry.content.unitTypes.air;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;

import static mindustry.Vars.tilePayload;

public class Quad extends UnitType{
    public static final String name = "quad";

    public Quad(){
        super(name);
        armor = 8f;
        health = 6000;
        speed = 1.2f;
        rotateSpeed = 2f;
        accel = 0.05f;
        drag = 0.017f;
        lowAltitude = false;
        flying = true;
        circleTarget = true;
        engineOffset = 12f;
        engineSize = 6f;
        rotateShooting = false;
        hitSize = 36f;
        payloadCapacity = (3 * 3) * tilePayload;
        buildSpeed = 2.5f;
        buildBeamOffset = 23;
        range = 140f;
        targetAir = false;
        targetFlags = new BlockFlag[]{BlockFlag.battery, BlockFlag.factory, null};

        ammoType = new PowerAmmoType(3000);

        weapons.add(
        new Weapon(){{
            x = y = 0f;
            mirror = false;
            reload = 55f;
            minShootVelocity = 0.01f;

            soundPitchMin = 1f;
            shootSound = Sounds.plasmadrop;

            bullet = new BasicBulletType(){{
                sprite = "large-bomb";
                width = height = 120/4f;

                maxRange = 30f;
                ignoreRotation = true;

                backColor = Pal.heal;
                frontColor = Color.white;
                mixColorTo = Color.white;

                hitSound = Sounds.plasmaboom;

                shootCone = 180f;
                ejectEffect = Fx.none;
                hitShake = 4f;

                collidesAir = false;

                lifetime = 70f;

                despawnEffect = Fx.greenBomb;
                hitEffect = Fx.massiveExplosion;
                keepVelocity = false;
                spin = 2f;

                shrinkX = shrinkY = 0.7f;

                speed = 0f;
                collides = false;

                healPercent = 15f;
                splashDamage = 220f;
                splashDamageRadius = 80f;
            }};
        }});
    }
}
