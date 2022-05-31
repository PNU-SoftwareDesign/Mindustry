package mindustry.content.unitTypes.legs;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Toxopid extends UnitType{
    public static final String name = "toxopid";

    public Toxopid(){
        super(name);
        drag = 0.1f;
        speed = 0.5f;
        hitSize = 26f;
        health = 22000;
        armor = 13f;
        lightRadius = 140f;

        rotateSpeed = 1.9f;
        drownTimeMultiplier = 3f;

        legCount = 8;
        legMoveSpace = 0.8f;
        legPairOffset = 3;
        legLength = 75f;
        legExtension = -20;
        legBaseOffset = 8f;
        landShake = 1f;
        legLengthScl = 0.93f;
        rippleScale = 3f;
        legSpeed = 0.19f;
        ammoType = new ItemAmmoType(Items.graphite, 8);
        buildSpeed = 1f;

        legSplashDamage = 80;
        legSplashRange = 60;

        hovering = true;
        visualElevation = 0.95f;
        groundLayer = Layer.legUnit;

        weapons.add(
        new Weapon("large-purple-mount"){{
            y = -5f;
            x = 11f;
            shootY = 7f;
            reload = 30;
            shake = 4f;
            rotateSpeed = 2f;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.shootBig;
            rotate = true;
            shadow = 12f;
            recoil = 3f;
            shots = 2;
            spacing = 17f;

            bullet = new ShrapnelBulletType(){{
                length = 90f;
                damage = 110f;
                width = 25f;
                serrationLenScl = 7f;
                serrationSpaceOffset = 60f;
                serrationFadeOffset = 0f;
                serrations = 10;
                serrationWidth = 6f;
                fromColor = Pal.sapBullet;
                toColor = Pal.sapBulletBack;
                shootEffect = smokeEffect = Fx.sparkShoot;
            }};
        }});

        weapons.add(new Weapon("toxopid-cannon"){{
            y = -14f;
            x = 0f;
            shootY = 22f;
            mirror = false;
            reload = 210;
            shake = 10f;
            recoil = 10f;
            rotateSpeed = 1f;
            ejectEffect = Fx.casing3;
            shootSound = Sounds.artillery;
            rotate = true;
            shadow = 30f;

            bullet = new ArtilleryBulletType(3f, 50){{
                hitEffect = Fx.sapExplosion;
                knockback = 0.8f;
                lifetime = 80f;
                width = height = 25f;
                collidesTiles = collides = true;
                ammoMultiplier = 4f;
                splashDamageRadius = 80f;
                splashDamage = 75f;
                backColor = Pal.sapBulletBack;
                frontColor = lightningColor = Pal.sapBullet;
                lightning = 5;
                lightningLength = 20;
                smokeEffect = Fx.shootBigSmoke2;
                hitShake = 10f;
                lightRadius = 40f;
                lightColor = Pal.sap;
                lightOpacity = 0.6f;

                status = StatusEffects.sapped;
                statusDuration = 60f * 10;

                fragLifeMin = 0.3f;
                fragBullets = 9;

                fragBullet = new ArtilleryBulletType(2.3f, 30){{
                    hitEffect = Fx.sapExplosion;
                    knockback = 0.8f;
                    lifetime = 90f;
                    width = height = 20f;
                    collidesTiles = false;
                    splashDamageRadius = 70f;
                    splashDamage = 40f;
                    backColor = Pal.sapBulletBack;
                    frontColor = lightningColor = Pal.sapBullet;
                    lightning = 2;
                    lightningLength = 5;
                    smokeEffect = Fx.shootBigSmoke2;
                    hitShake = 5f;
                    lightRadius = 30f;
                    lightColor = Pal.sap;
                    lightOpacity = 0.5f;

                    status = StatusEffects.sapped;
                    statusDuration = 60f * 10;
                }};
            }};
        }});
    }
}
