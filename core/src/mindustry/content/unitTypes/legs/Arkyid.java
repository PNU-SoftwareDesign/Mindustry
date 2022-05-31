package mindustry.content.unitTypes.legs;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Arkyid extends UnitType{
    public static final String name = "arkyid";

    public Arkyid(){
        super(name);
        drag = 0.1f;
        speed = 0.62f;
        hitSize = 23f;
        health = 8000;
        armor = 6f;

        rotateSpeed = 2.7f;

        legCount = 6;
        legMoveSpace = 1f;
        legPairOffset = 3;
        legLength = 30f;
        legExtension = -15;
        legBaseOffset = 10f;
        landShake = 1f;
        legLengthScl = 0.96f;
        rippleScale = 2f;
        legSpeed = 0.2f;
        ammoType = new PowerAmmoType(2000);
        buildSpeed = 1f;

        legSplashDamage = 32;
        legSplashRange = 30;
        drownTimeMultiplier = 2f;

        hovering = true;
        visualElevation = 0.65f;
        groundLayer = Layer.legUnit;

        BulletType sapper = new SapBulletType(){{
            sapStrength = 0.85f;
            length = 55f;
            damage = 40;
            shootEffect = Fx.shootSmall;
            hitColor = color = Color.valueOf("bf92f9");
            despawnEffect = Fx.none;
            width = 0.55f;
            lifetime = 30f;
            knockback = -1f;
        }};

        weapons.add(
        new Weapon("spiroct-weapon"){{
            reload = 9f;
            x = 4f;
            y = 8f;
            rotate = true;
            bullet = sapper;
            shootSound = Sounds.sap;
        }},
        new Weapon("spiroct-weapon"){{
            reload = 14f;
            x = 9f;
            y = 6f;
            rotate = true;
            bullet = sapper;
            shootSound = Sounds.sap;
        }},
        new Weapon("spiroct-weapon"){{
            reload = 22f;
            x = 14f;
            y = 0f;
            rotate = true;
            bullet = sapper;
            shootSound = Sounds.sap;
        }},
        new Weapon("large-purple-mount"){{
            y = -7f;
            x = 9f;
            shootY = 7f;
            reload = 45;
            shake = 3f;
            rotateSpeed = 2f;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.artillery;
            rotate = true;
            shadow = 8f;
            recoil = 3f;

            bullet = new ArtilleryBulletType(2f, 12){{
                hitEffect = Fx.sapExplosion;
                knockback = 0.8f;
                lifetime = 70f;
                width = height = 19f;
                collidesTiles = true;
                ammoMultiplier = 4f;
                splashDamageRadius = 70f;
                splashDamage = 65f;
                backColor = Pal.sapBulletBack;
                frontColor = lightningColor = Pal.sapBullet;
                lightning = 3;
                lightningLength = 10;
                smokeEffect = Fx.shootBigSmoke2;
                shake = 5f;

                status = StatusEffects.sapped;
                statusDuration = 60f * 10;
            }};
        }});
    }
}
