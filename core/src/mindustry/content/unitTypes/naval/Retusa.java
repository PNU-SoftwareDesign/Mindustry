package mindustry.content.unitTypes.naval;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;

public class Retusa extends UnitType{
    public static final String name = "retusa";

    public Retusa(){
        super(name);
        speed = 0.9f;
        targetAir = false;
        drag = 0.14f;
        hitSize = 11f;
        health = 270;
        accel = 0.4f;
        rotateSpeed = 5f;
        trailLength = 20;
        trailX = 5f;
        trailScl = 1.3f;
        rotateShooting = false;
        range = 100f;
        ammoType = new PowerAmmoType(900);

        armor = 3f;

        buildSpeed = 1.5f;

        weapons.add(new RepairBeamWeapon("repair-beam-weapon-center"){{
            x = 0f;
            y = -5.5f;
            shootY = 6f;
            beamWidth = 0.8f;
            mirror = false;
            repairSpeed = 0.75f;

            bullet = new BulletType(){{
                maxRange = 120f;
            }};
        }});

        weapons.add(new Weapon(){{
            mirror = false;
            rotate = true;
            reload = 90f;
            shots = 3;
            shotDelay = 7f;
            x = y = shootX = shootY = 0f;
            shootSound = Sounds.mineDeploy;
            rotateSpeed = 180f;

            bullet = new BasicBulletType(){{
                sprite = "mine-bullet";
                width = height = 8f;
                layer = Layer.scorch;
                shootEffect = smokeEffect = Fx.none;

                maxRange = 50f;
                ignoreRotation = true;
                healPercent = 4f;

                backColor = Pal.heal;
                frontColor = Color.white;
                mixColorTo = Color.white;

                hitSound = Sounds.plasmaboom;

                ejectEffect = Fx.none;
                hitSize = 22f;

                collidesAir = false;

                lifetime = 87f;

                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.greenCloud);
                keepVelocity = false;

                shrinkX = shrinkY = 0f;

                inaccuracy = 2f;
                weaveMag = 5f;
                weaveScale = 4f;
                speed = 0.7f;
                drag = -0.017f;
                homingPower = 0.05f;
                collideFloor = true;
                trailColor = Pal.heal;
                trailWidth = 3f;
                trailLength = 8;

                splashDamage = 33f;
                splashDamageRadius = 32f;
            }};
        }});
    }
}
