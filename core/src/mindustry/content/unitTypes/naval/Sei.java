package mindustry.content.unitTypes.naval;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Sei extends UnitType{
    public static final String name = "sei";

    public Sei(){
        super(name);
        health = 11000;
        armor = 12f;

        speed = 0.73f;
        drag = 0.17f;
        hitSize = 39f;
        accel = 0.2f;
        rotateSpeed = 1.3f;
        rotateShooting = false;
        ammoType = new ItemAmmoType(Items.thorium);

        trailLength = 50;
        trailX = 18f;
        trailY = -21f;
        trailScl = 3f;

        weapons.add(new Weapon("sei-launcher"){{

            x = 0f;
            y = 0f;
            rotate = true;
            rotateSpeed = 4f;
            mirror = false;

            shadow = 20f;

            shootY = 2f;
            recoil = 4f;
            reload = 45f;
            shots = 6;
            spacing = 10f;
            velocityRnd = 0.4f;
            inaccuracy = 7f;
            ejectEffect = Fx.none;
            shake = 3f;
            shootSound = Sounds.missile;
            xRand = 8f;
            shotDelay = 1f;

            bullet = new MissileBulletType(4.2f, 42){{
                homingPower = 0.12f;
                width = 8f;
                height = 8f;
                shrinkX = shrinkY = 0f;
                drag = -0.003f;
                homingRange = 80f;
                keepVelocity = false;
                splashDamageRadius = 35f;
                splashDamage = 45f;
                lifetime = 62f;
                trailColor = Pal.bulletYellowBack;
                backColor = Pal.bulletYellowBack;
                frontColor = Pal.bulletYellow;
                hitEffect = Fx.blastExplosion;
                despawnEffect = Fx.blastExplosion;
                weaveScale = 8f;
                weaveMag = 2f;
            }};
        }});

        weapons.add(new Weapon("large-bullet-mount"){{
            reload = 60f;
            cooldownTime = 90f;
            x = 70f/4f;
            y = -66f/4f;
            rotateSpeed = 4f;
            rotate = true;
            shootY = 7f;
            shake = 2f;
            recoil = 3f;
            shadow = 12f;
            ejectEffect = Fx.casing3;
            shootSound = Sounds.shootBig;

            shots = 3;
            shotDelay = 4f;
            inaccuracy = 1f;
            bullet = new BasicBulletType(7f, 57){{
                width = 13f;
                height = 19f;
                shootEffect = Fx.shootBig;
                lifetime = 35f;
            }};
        }});
    }
}
