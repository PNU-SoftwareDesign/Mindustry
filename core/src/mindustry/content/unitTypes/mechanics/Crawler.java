package mindustry.content.unitTypes.mechanics;

import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Crawler extends UnitType{
    public static final String name = "crawler";

    public Crawler(){
        super(name);
        defaultController = SuicideAI::new;

        speed = 1f;
        hitSize = 8f;
        health = 200;
        mechSideSway = 0.25f;
        range = 40f;
        ammoType = new ItemAmmoType(Items.coal);

        weapons.add(new Weapon(){{
            shootOnDeath = true;
            reload = 24f;
            shootCone = 180f;
            ejectEffect = Fx.none;
            shootSound = Sounds.explosion;
            x = shootY = 0f;
            mirror = false;
            bullet = new BombBulletType(0f, 0f, "clear"){{
                hitEffect = Fx.pulverize;
                lifetime = 10f;
                speed = 1f;
                splashDamageRadius = 55f;
                instantDisappear = true;
                splashDamage = 90f;
                killShooter = true;
                hittable = false;
                collidesAir = true;
            }};
        }});
    }
}
