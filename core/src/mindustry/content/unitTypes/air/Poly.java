package mindustry.content.unitTypes.air;

import arc.graphics.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Poly extends UnitType{
    public static final String name = "poly";

    public Poly(){
        super(name);
        defaultController = BuilderAI::new;

        flying = true;
        drag = 0.05f;
        speed = 2.6f;
        rotateSpeed = 15f;
        accel = 0.1f;
        range = 130f;
        health = 400;
        buildSpeed = 0.5f;
        engineOffset = 6.5f;
        hitSize = 9f;
        lowAltitude = true;

        ammoType = new PowerAmmoType(900);

        mineTier = 2;
        mineSpeed = 3.5f;

        abilities.add(new RepairFieldAbility(5f, 60f * 8, 50f));

        weapons.add(new Weapon("heal-weapon-mount"){{
            top = false;
            y = -2.5f;
            x = 3.5f;
            reload = 30f;
            ejectEffect = Fx.none;
            recoil = 2f;
            shootSound = Sounds.missile;
            shots = 1;
            velocityRnd = 0.5f;
            inaccuracy = 15f;
            alternate = true;

            bullet = new MissileBulletType(4f, 12){{
                homingPower = 0.08f;
                weaveMag = 4;
                weaveScale = 4;
                lifetime = 50f;
                keepVelocity = false;
                shootEffect = Fx.shootHeal;
                smokeEffect = Fx.hitLaser;
                hitEffect = despawnEffect = Fx.hitLaser;
                frontColor = Color.white;
                hitSound = Sounds.none;

                healPercent = 5.5f;
                collidesTeam = true;
                backColor = Pal.heal;
                trailColor = Pal.heal;
            }};
        }});
    }
}
