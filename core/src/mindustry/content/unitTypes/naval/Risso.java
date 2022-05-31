package mindustry.content.unitTypes.naval;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class Risso extends UnitType{
    public static final String name = "risso";

    public Risso(){
        super(name);
        speed = 1.1f;
        drag = 0.13f;
        hitSize = 10f;
        health = 280;
        accel = 0.4f;
        rotateSpeed = 3.3f;
        trailLength = 20;
        rotateShooting = false;

        armor = 2f;

        weapons.add(new Weapon("mount-weapon"){{
            reload = 13f;
            x = 4f;
            shootY = 4f;
            y = 1.5f;
            rotate = true;
            ejectEffect = Fx.casing1;
            bullet = Bullets.standardCopper;
        }});

        weapons.add(new Weapon("missiles-mount"){{
            mirror = false;
            reload = 25f;
            x = 0f;
            y = -5f;
            rotate = true;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.missile;
            bullet = new MissileBulletType(2.7f, 12, "missile"){{
                keepVelocity = true;
                width = 8f;
                height = 8f;
                shrinkY = 0f;
                drag = -0.003f;
                homingRange = 60f;
                splashDamageRadius = 25f;
                splashDamage = 10f;
                lifetime = 65f;
                trailColor = Color.gray;
                backColor = Pal.bulletYellowBack;
                frontColor = Pal.bulletYellow;
                hitEffect = Fx.blastExplosion;
                despawnEffect = Fx.blastExplosion;
                weaveScale = 8f;
                weaveMag = 2f;
            }};
        }});
    }
}
