package mindustry.content.unitTypes.naval;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class Navanax extends UnitType{
    public static final String name = "navanax";

    public Navanax(){
        super(name);
        health = 20000;
        speed = 0.65f;
        drag = 0.17f;
        hitSize = 58f;
        armor = 16f;
        accel = 0.2f;
        rotateSpeed = 1.1f;
        rotateShooting = false;
        ammoType = new PowerAmmoType(4500);

        trailLength = 70;
        trailX = 23f;
        trailY = -32f;
        trailScl = 3.5f;

        buildSpeed = 3.5f;

        for(float mountY : new float[]{-117/4f, 50/4f}){
            for(float sign : Mathf.signs){
                weapons.add(new Weapon("plasma-laser-mount"){{
                    shadow = 20f;
                    controllable = false;
                    autoTarget = true;
                    mirror = false;
                    shake = 3f;
                    shootY = 7f;
                    rotate = true;
                    x = 84f/4f * sign;
                    y = mountY;

                    targetInterval = 20f;
                    targetSwitchInterval = 35f;

                    rotateSpeed = 3.5f;
                    reload = 170f;
                    recoil = 1f;
                    shootSound = Sounds.beam;
                    continuous = true;
                    cooldownTime = reload;
                    immunities.add(StatusEffects.burning);

                    bullet = new ContinuousLaserBulletType(){{
                        maxRange = 90f;
                        damage = 27f;
                        length = 95f;
                        hitEffect = Fx.hitMeltHeal;
                        drawSize = 200f;
                        lifetime = 155f;
                        shake = 1f;

                        shootEffect = Fx.shootHeal;
                        smokeEffect = Fx.none;
                        width = 4f;
                        largeHit = false;

                        incendChance = 0.03f;
                        incendSpread = 5f;
                        incendAmount = 1;

                        healPercent = 0.4f;
                        collidesTeam = true;

                        colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                    }};
                }});
            }
        }

        weapons.add(new Weapon("emp-cannon-mount"){{
            rotate = true;

            x = 70f/4f;
            y = -26f/4f;

            reload = 65f;
            shake = 3f;
            rotateSpeed = 2f;
            shadow = 30f;
            shootY = 7f;
            recoil = 4f;
            cooldownTime = reload - 10f;
            //TODO better sound
            shootSound = Sounds.laser;

            bullet = new EmpBulletType(){{
                float rad = 100f;

                scaleVelocity = true;
                lightOpacity = 0.7f;
                unitDamageScl = 0.8f;
                healPercent = 20f;
                timeIncrease = 3f;
                timeDuration = 60f * 20f;
                powerDamageScl = 3f;
                damage = 60;
                hitColor = lightColor = Pal.heal;
                lightRadius = 70f;
                clipSize = 250f;
                shootEffect = Fx.hitEmpSpark;
                smokeEffect = Fx.shootBigSmoke2;
                lifetime = 60f;
                sprite = "circle-bullet";
                backColor = Pal.heal;
                frontColor = Color.white;
                width = height = 12f;
                shrinkY = 0f;
                speed = 5f;
                trailLength = 20;
                trailWidth = 6f;
                trailColor = Pal.heal;
                trailInterval = 3f;
                splashDamage = 70f;
                splashDamageRadius = rad;
                hitShake = 4f;
                trailRotation = true;
                status = StatusEffects.electrified;
                hitSound = Sounds.plasmaboom;

                trailEffect = new Effect(16f, e -> {
                    color(Pal.heal);
                    for(int s : Mathf.signs){
                        Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                    }
                });

                hitEffect = new Effect(50f, 100f, e -> {
                    e.scaled(7f, b -> {
                        color(Pal.heal, b.fout());
                        Fill.circle(e.x, e.y, rad);
                    });

                    color(Pal.heal);
                    stroke(e.fout() * 3f);
                    Lines.circle(e.x, e.y, rad);

                    int points = 10;
                    float offset = Mathf.randomSeed(e.id, 360f);
                    for(int i = 0; i < points; i++){
                        float angle = i* 360f / points + offset;
                        //for(int s : Mathf.zeroOne){
                        Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle/* + s*180f*/);
                        //}
                    }

                    Fill.circle(e.x, e.y, 12f * e.fout());
                    color();
                    Fill.circle(e.x, e.y, 6f * e.fout());
                    Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                });
            }};
        }});
    }
}
