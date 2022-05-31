package mindustry.content.unitTypes.naval;

import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

import static mindustry.content.UnitTypes.flare;

public class Omura extends UnitType{
    public static final String name = "omura";

    public Omura(){
        super(name);
        health = 22000;
        speed = 0.62f;
        drag = 0.18f;
        hitSize = 58f;
        armor = 16f;
        accel = 0.19f;
        rotateSpeed = 0.9f;
        rotateShooting = false;
        ammoType = new PowerAmmoType(4000);

        float spawnTime = 60f * 15f;

        abilities.add(new UnitSpawnAbility(flare, spawnTime, 19.25f, -31.75f), new UnitSpawnAbility(flare, spawnTime, -19.25f, -31.75f));

        trailLength = 70;
        trailX = 23f;
        trailY = -32f;
        trailScl = 3.5f;

        weapons.add(new Weapon("omura-cannon"){{
            reload = 110f;
            cooldownTime = 90f;
            mirror = false;
            x = 0f;
            y = -3.5f;
            rotateSpeed = 1.4f;
            rotate = true;
            shootY = 23f;
            shake = 6f;
            recoil = 10.5f;
            shadow = 50f;
            shootSound = Sounds.railgun;

            shots = 1;
            ejectEffect = Fx.none;

            bullet = new RailBulletType(){{
                shootEffect = Fx.railShoot;
                length = 500;
                updateEffectSeg = 60f;
                pierceEffect = Fx.railHit;
                updateEffect = Fx.railTrail;
                hitEffect = Fx.massiveExplosion;
                smokeEffect = Fx.shootBig2;
                damage = 1250;
                pierceDamageFactor = 0.5f;
            }};
        }});
    }
}
