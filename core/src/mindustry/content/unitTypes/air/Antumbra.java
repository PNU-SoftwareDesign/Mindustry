package mindustry.content.unitTypes.air;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;

public class Antumbra extends UnitType{
    public static final String name = "antumbra";

    public Antumbra(){
        super(name);

        speed = 0.8f;
        accel = 0.04f;
        drag = 0.04f;
        rotateSpeed = 1.9f;
        flying = true;
        lowAltitude = true;
        health = 7200;
        armor = 9f;
        engineOffset = 21;
        engineSize = 5.3f;
        hitSize = 46f;
        targetFlags = new BlockFlag[]{BlockFlag.generator, BlockFlag.core, null};
        ammoType = new ItemAmmoType(Items.thorium);

        BulletType missiles = new MissileBulletType(2.7f, 18){{
            width = 8f;
            height = 8f;
            shrinkY = 0f;
            drag = -0.01f;
            splashDamageRadius = 20f;
            splashDamage = 37f;
            ammoMultiplier = 4f;
            lifetime = 50f;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;

            status = StatusEffects.blasted;
            statusDuration = 60f;
        }};

        weapons.add(
            new Weapon("missiles-mount"){{
                y = 8f;
                x = 17f;
                reload = 20f;
                ejectEffect = Fx.casing1;
                rotateSpeed = 8f;
                bullet = missiles;
                shootSound = Sounds.missile;
                rotate = true;
                shadow = 6f;
            }},
            new Weapon("missiles-mount"){{
                y = -8f;
                x = 17f;
                reload = 35;
                rotateSpeed = 8f;
                ejectEffect = Fx.casing1;
                bullet = missiles;
                shootSound = Sounds.missile;
                rotate = true;
                shadow = 6f;
            }},
            new Weapon("large-bullet-mount"){{
                y = 2f;
                x = 10f;
                shootY = 10f;
                reload = 12;
                shake = 1f;
                rotateSpeed = 2f;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shootBig;
                rotate = true;
                shadow = 8f;
                bullet = new BasicBulletType(7f, 55){{
                    width = 12f;
                    height = 18f;
                    lifetime = 25f;
                    shootEffect = Fx.shootBig;
                }};
            }}
        );
    }
}
