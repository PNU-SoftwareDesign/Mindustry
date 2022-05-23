package mindustry.content.unitTypes.legs;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Corvus extends UnitType{
    public static final String name = "corvus";

    public Corvus(){
        super(name);
        hitSize = 29f;
        health = 18000f;
        armor = 9f;
        landShake = 1.5f;
        rotateSpeed = 1.5f;
        drownTimeMultiplier = 6f;

        commandLimit = 8;

        legCount = 4;
        legLength = 14f;
        legBaseOffset = 11f;
        legMoveSpace = 1.5f;
        legTrns = 0.58f;
        hovering = true;
        visualElevation = 0.2f;
        ammoType = new PowerAmmoType(4000);
        groundLayer = Layer.legUnit;

        speed = 0.3f;

        drawShields = false;

        weapons.add(new Weapon("corvus-weapon"){{
            shootSound = Sounds.laserblast;
            chargeSound = Sounds.lasercharge;
            soundPitchMin = 1f;
            top = false;
            mirror = false;
            shake = 14f;
            shootY = 5f;
            x = y = 0;
            reload = 350f;
            recoil = 0f;

            cooldownTime = 350f;

            shootStatusDuration = 60f * 2f;
            shootStatus = StatusEffects.unmoving;
            firstShotDelay = Fx.greenLaserCharge.lifetime;
            parentizeEffects = true;

            bullet = new LaserBulletType(){{
                length = 460f;
                damage = 560f;
                width = 75f;

                lifetime = 65f;

                lightningSpacing = 35f;
                lightningLength = 5;
                lightningDelay = 1.1f;
                lightningLengthRand = 15;
                lightningDamage = 50;
                lightningAngleRand = 40f;
                largeHit = true;
                lightColor = lightningColor = Pal.heal;

                shootEffect = Fx.greenLaserCharge;

                healPercent = 25f;
                collidesTeam = true;

                sideAngle = 15f;
                sideWidth = 0f;
                sideLength = 0f;
                colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
            }};
        }});
    }
}
