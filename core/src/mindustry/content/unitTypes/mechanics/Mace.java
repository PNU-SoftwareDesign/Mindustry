package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Mace extends UnitType{
    public static final String name = "mace";
    public Mace(){
        super(name);
        speed = 0.45f;
        hitSize = 10f;
        health = 540;
        armor = 4f;
        ammoType = new ItemAmmoType(Items.coal);

        immunities.add(StatusEffects.burning);

        weapons.add(new Weapon("flamethrower"){{
            top = false;
            shootSound = Sounds.flame;
            shootY = 2f;
            reload = 11f;
            recoil = 1f;
            ejectEffect = Fx.none;
            bullet = new BulletType(4.1f, 35f){{
                ammoMultiplier = 3f;
                hitSize = 7f;
                lifetime = 13f;
                pierce = true;
                statusDuration = 60f * 4;
                shootEffect = Fx.shootSmallFlame;
                hitEffect = Fx.hitFlameSmall;
                despawnEffect = Fx.none;
                status = StatusEffects.burning;
                keepVelocity = false;
                hittable = false;
            }};
        }});
    }
}
