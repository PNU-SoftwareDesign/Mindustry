package mindustry.content.unitTypes.mechanics;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;

public class Vela extends UnitType{
    public static final String name = "vela";

    public Vela(){
        super(name);
        hitSize = 24f;

        rotateSpeed = 1.8f;
        mechFrontSway = 1f;
        buildSpeed = 3f;

        mechStepParticles = true;
        mechStepShake = 0.15f;
        ammoType = new PowerAmmoType(2500);
        drownTimeMultiplier = 4f;

        speed = 0.44f;
        boostMultiplier = 2.4f;
        engineOffset = 12f;
        engineSize = 6f;
        lowAltitude = true;
        riseSpeed = 0.02f;

        health = 8200f;
        armor = 9f;
        canBoost = true;
        landShake = 4f;
        immunities = ObjectSet.with(StatusEffects.burning);

        commandLimit = 8;
        singleTarget = true;

        weapons.add(new Weapon("vela-weapon"){{
            mirror = false;
            top = false;
            shake = 4f;
            shootY = 13f;
            x = y = 0f;

            firstShotDelay = Fx.greenLaserChargeSmall.lifetime - 1f;
            parentizeEffects = true;

            reload = 155f;
            recoil = 0f;
            chargeSound = Sounds.lasercharge2;
            shootSound = Sounds.beam;
            continuous = true;
            cooldownTime = 200f;

            bullet = new ContinuousLaserBulletType(){{
                damage = 35f;
                length = 180f;
                hitEffect = Fx.hitMeltHeal;
                drawSize = 420f;
                lifetime = 160f;
                shake = 1f;
                despawnEffect = Fx.smokeCloud;
                smokeEffect = Fx.none;

                shootEffect = Fx.greenLaserChargeSmall;

                incendChance = 0.1f;
                incendSpread = 5f;
                incendAmount = 1;

                //constant healing
                healPercent = 1f;
                collidesTeam = true;

                colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
            }};

            shootStatus = StatusEffects.slow;
            shootStatusDuration = bullet.lifetime + firstShotDelay;
        }});

        weapons.add(new RepairBeamWeapon("repair-beam-weapon-center-large"){{
            x = 44 / 4f;
            y = -30f / 4f;
            shootY = 6f;
            beamWidth = 0.8f;
            repairSpeed = 1.4f;

            bullet = new BulletType(){{
                maxRange = 120f;
            }};
        }});
    }
}
