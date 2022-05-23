package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Fortress extends UnitType{
    public static final String name = "fortress";
    public Fortress(){
        super(name);
        speed = 0.43f;
        hitSize = 13f;
        rotateSpeed = 3f;
        targetAir = false;
        health = 900;
        armor = 9f;
        mechFrontSway = 0.55f;
        ammoType = new ItemAmmoType(Items.graphite);

        weapons.add(new Weapon("artillery"){{
            top = false;
            y = 1f;
            x = 9f;
            reload = 60f;
            recoil = 4f;
            shake = 2f;
            ejectEffect = Fx.casing2;
            shootSound = Sounds.artillery;
            bullet = new ArtilleryBulletType(2f, 20, "shell"){{
                hitEffect = Fx.blastExplosion;
                knockback = 0.8f;
                lifetime = 120f;
                width = height = 14f;
                collides = true;
                collidesTiles = true;
                splashDamageRadius = 35f;
                splashDamage = 80f;
                backColor = Pal.bulletYellowBack;
                frontColor = Pal.bulletYellow;
            }};
        }});
    }
}
