package mindustry.content.unitTypes.naval;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Minke extends UnitType{
    public static final String name = "minke";

    public Minke(){
        super(name);
        health = 600;
        speed = 0.9f;
        drag = 0.15f;
        hitSize = 13f;
        armor = 4f;
        accel = 0.3f;
        rotateSpeed = 2.6f;
        rotateShooting = false;
        ammoType = new ItemAmmoType(Items.graphite);

        trailLength = 20;
        trailX = 5.5f;
        trailY = -4f;
        trailScl = 1.9f;

        weapons.add(new Weapon("mount-weapon"){{
            reload = 10f;
            x = 5f;
            y = 3.5f;
            rotate = true;
            rotateSpeed = 5f;
            inaccuracy = 8f;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.shoot;
            bullet = Bullets.flakLead;
        }});

        weapons.add(new Weapon("artillery-mount"){{
            reload = 30f;
            x = 5f;
            y = -5f;
            rotate = true;
            inaccuracy = 2f;
            rotateSpeed = 2f;
            shake = 1.5f;
            ejectEffect = Fx.casing2;
            shootSound = Sounds.bang;
            bullet = new ArtilleryBulletType(3f, 20, "shell"){{
                hitEffect = Fx.flakExplosion;
                knockback = 0.8f;
                lifetime = 80f;
                width = height = 11f;
                collidesTiles = false;
                splashDamageRadius = 30f * 0.75f;
                splashDamage = 40f;
            }};
        }});
    }
}
