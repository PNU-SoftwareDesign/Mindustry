package mindustry.content.unitTypes.naval;

import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class Oxynoe extends UnitType{
    public static final String name = "oxynoe";

    public Oxynoe(){
        super(name);
        health = 560;
        speed = 0.83f;
        drag = 0.14f;
        hitSize = 14f;
        armor = 4f;
        accel = 0.4f;
        rotateSpeed = 4f;
        rotateShooting = false;

        trailLength = 22;
        trailX = 5.5f;
        trailY = -4f;
        trailScl = 1.9f;
        ammoType = new ItemAmmoType(Items.coal);

        abilities.add(new StatusFieldAbility(StatusEffects.overclock, 60f * 6, 60f * 6f, 60f));

        buildSpeed = 2f;

        weapons.add(new Weapon("plasma-mount-weapon"){{

            reload = 5f;
            x = 4.5f;
            y = 6.5f;
            rotate = true;
            rotateSpeed = 5f;
            inaccuracy = 10f;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.flame;
            shootCone = 30f;

            bullet = new BulletType(3.4f, 23f){{
                healPercent = 1.5f;
                collidesTeam = true;
                ammoMultiplier = 3f;
                hitSize = 7f;
                lifetime = 18f;
                pierce = true;
                collidesAir = false;
                statusDuration = 60f * 4;
                hitEffect = Fx.hitFlamePlasma;
                ejectEffect = Fx.none;
                despawnEffect = Fx.none;
                status = StatusEffects.burning;
                keepVelocity = false;
                hittable = false;
                shootEffect = new Effect(32f, 80f, e -> {
                    color(Color.white, Pal.heal, Color.gray, e.fin());

                    randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.5f);
                        Drawf.light(e.x + x, e.y + y, 16f * e.fout(), Pal.heal, 0.6f);
                    });
                });
            }};
        }});

        weapons.add(new PointDefenseWeapon("point-defense-mount"){{
            mirror = false;
            x = 0f;
            y = 1f;
            reload = 9f;
            targetInterval = 10f;
            targetSwitchInterval = 15f;

            bullet = new BulletType(){{
                shootEffect = Fx.sparkShoot;
                hitEffect = Fx.pointHit;
                maxRange = 100f;
                damage = 17f;
            }};
        }});

    }
}
