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

public class Cyerce extends UnitType{
    public static final String name = "cyerce";

    public Cyerce(){
        super(name);
        health = 870;
        speed = 0.86f;
        accel = 0.22f;
        rotateSpeed = 2.6f;
        drag = 0.16f;
        hitSize = 20f;
        armor = 6f;
        rotateShooting = false;
        ammoType = new ItemAmmoType(Items.graphite);

        trailLength = 23;
        trailX = 9f;
        trailY = -9f;
        trailScl = 2f;

        buildSpeed = 2f;

        weapons.add(new RepairBeamWeapon("repair-beam-weapon-center"){{
            x = 11f;
            y = -10f;
            shootY = 6f;
            beamWidth = 0.8f;
            repairSpeed = 0.7f;

            bullet = new BulletType(){{
                maxRange = 130f;
            }};
        }});

        weapons.add(new Weapon("plasma-missile-mount"){{
            reload = 60f;
            x = 9f;
            y = 3f;

            shadow = 5f;

            rotateSpeed = 4f;
            rotate = true;
            inaccuracy = 1f;
            velocityRnd = 0.1f;
            shootSound = Sounds.missile;

            ejectEffect = Fx.none;
            bullet = new FlakBulletType(2.5f, 25){{
                sprite = "missile-large";
                //for targeting
                collidesGround = collidesAir = true;
                explodeRange = 40f;
                width = height = 12f;
                shrinkY = 0f;
                drag = -0.003f;
                homingRange = 60f;
                keepVelocity = false;
                lightRadius = 60f;
                lightOpacity = 0.7f;
                lightColor = Pal.heal;

                splashDamageRadius = 30f;
                splashDamage = 25f;

                lifetime = 80f;
                backColor = Pal.heal;
                frontColor = Color.white;

                hitEffect = new ExplosionEffect(){{
                    lifetime = 28f;
                    waveStroke = 6f;
                    waveLife = 10f;
                    waveRadBase = 7f;
                    waveColor = Pal.heal;
                    waveRad = 30f;
                    smokes = 6;
                    smokeColor = Color.white;
                    sparkColor = Pal.heal;
                    sparks = 6;
                    sparkRad = 35f;
                    sparkStroke = 1.5f;
                    sparkLen = 4f;
                }};

                weaveScale = 8f;
                weaveMag = 1f;

                trailColor = Pal.heal;
                trailWidth = 4.5f;
                trailLength = 29;

                fragBullets = 7;
                fragVelocityMin = 0.3f;

                fragBullet = new MissileBulletType(3.9f, 11){{
                    homingPower = 0.2f;
                    weaveMag = 4;
                    weaveScale = 4;
                    lifetime = 60f;
                    keepVelocity = false;
                    shootEffect = Fx.shootHeal;
                    smokeEffect = Fx.hitLaser;
                    splashDamage = 13f;
                    splashDamageRadius = 20f;
                    frontColor = Color.white;
                    hitSound = Sounds.none;

                    lightColor = Pal.heal;
                    lightRadius = 40f;
                    lightOpacity = 0.7f;

                    trailColor = Pal.heal;
                    trailWidth = 2.5f;
                    trailLength = 20;
                    trailChance = -1f;

                    healPercent = 2.8f;
                    collidesTeam = true;
                    backColor = Pal.heal;

                    despawnEffect = Fx.none;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 20f;
                        waveStroke = 2f;
                        waveColor = Pal.heal;
                        waveRad = 12f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparkColor = Pal.heal;
                        sparks = 9;
                        sparkRad = 35f;
                        sparkLen = 4f;
                        sparkStroke = 1.5f;
                    }};
                }};
            }};
        }});
    }
}
