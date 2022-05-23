package mindustry.content.unitTypes.naval;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Bryde extends UnitType{
    public static final String name = "bryde";

    public Bryde(){
        super(name);
        health = 910;
        speed = 0.85f;
        accel = 0.2f;
        rotateSpeed = 1.8f;
        drag = 0.17f;
        hitSize = 20f;
        armor = 7f;
        rotateShooting = false;
        ammoType = new ItemAmmoType(Items.graphite);

        trailLength = 22;
        trailX = 7f;
        trailY = -9f;
        trailScl = 1.5f;

        abilities.add(new ShieldRegenFieldAbility(20f, 40f, 60f * 4, 60f));

        weapons.add(new Weapon("large-artillery"){{
            reload = 65f;
            mirror = false;
            x = 0f;
            y = -3.5f;
            rotateSpeed = 1.7f;
            rotate = true;
            shootY = 7f;
            shake = 5f;
            recoil = 4f;
            shadow = 12f;

            shots = 1;
            inaccuracy = 3f;
            ejectEffect = Fx.casing3;
            shootSound = Sounds.artillery;

            bullet = new ArtilleryBulletType(3.2f, 15){{
                trailMult = 0.8f;
                hitEffect = Fx.massiveExplosion;
                knockback = 1.5f;
                lifetime = 84f;
                height = 15.5f;
                width = 15f;
                collidesTiles = false;
                splashDamageRadius = 40f;
                splashDamage = 70f;
                backColor = Pal.missileYellowBack;
                frontColor = Pal.missileYellow;
                trailEffect = Fx.artilleryTrail;
                trailSize = 6f;
                hitShake = 4f;

                shootEffect = Fx.shootBig2;

                status = StatusEffects.blasted;
                statusDuration = 60f;
            }};
        }});

        weapons.add(new Weapon("missiles-mount"){{
            reload = 20f;
            x = 8.5f;
            y = -9f;

            shadow = 6f;

            rotateSpeed = 4f;
            rotate = true;
            shots = 2;
            shotDelay = 3f;
            inaccuracy = 5f;
            velocityRnd = 0.1f;
            shootSound = Sounds.missile;
            ammoType = new ItemAmmoType(Items.thorium);

            ejectEffect = Fx.none;
            bullet = new MissileBulletType(2.7f, 12){{
                width = 8f;
                height = 8f;
                shrinkY = 0f;
                drag = -0.003f;
                homingRange = 60f;
                keepVelocity = false;
                splashDamageRadius = 25f;
                splashDamage = 10f;
                lifetime = 70f;
                trailColor = Color.gray;
                backColor = Pal.bulletYellowBack;
                frontColor = Pal.bulletYellow;
                hitEffect = Fx.blastExplosion;
                despawnEffect = Fx.blastExplosion;
                weaveScale = 8f;
                weaveMag = 1f;
            }};
        }});
    }
}
