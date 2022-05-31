package mindustry.content.unitTypes.naval;

import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;

public class Aegires extends UnitType{
    public static final String name = "aegires";

    public Aegires(){
        super(name);
        health = 12000;
        armor = 12f;

        speed = 0.7f;
        drag = 0.17f;
        hitSize = 44f;
        accel = 0.2f;
        rotateSpeed = 1.4f;
        rotateShooting = false;
        ammoType = new PowerAmmoType(3500);
        ammoCapacity = 40;

        //clip size is massive due to energy field
        clipSize = 250f;

        trailLength = 50;
        trailX = 18f;
        trailY = -17f;
        trailScl = 3.2f;

        buildSpeed = 3f;

        abilities.add(new EnergyFieldAbility(35f, 65f, 180f){{
            statusDuration = 60f * 6f;
            maxTargets = 25;
        }});

        for(float mountY : new float[]{-18f, 14}){
            weapons.add(new PointDefenseWeapon("point-defense-mount"){{
                x = 12.5f;
                y = mountY;
                reload = 6f;
                targetInterval = 8f;
                targetSwitchInterval = 8f;

                bullet = new BulletType(){{
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.pointHit;
                    maxRange = 180f;
                    damage = 24f;
                }};
            }});
        }
    }
}
