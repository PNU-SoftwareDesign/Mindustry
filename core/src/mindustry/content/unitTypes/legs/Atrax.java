package mindustry.content.unitTypes.legs;

import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Atrax extends UnitType{
    public static final String name = "atrax";

    public Atrax(){
        super(name);
        speed = 0.57f;
        drag = 0.4f;
        hitSize = 13f;
        rotateSpeed = 3f;
        targetAir = false;
        health = 600;
        immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.melting);

        legCount = 4;
        legLength = 9f;
        legTrns = 0.6f;
        legMoveSpace = 1.4f;
        hovering = true;
        armor = 3f;
        ammoType = new ItemAmmoType(Items.coal);

        visualElevation = 0.2f;
        groundLayer = Layer.legUnit - 1f;

        weapons.add(new Weapon("eruption"){{
            top = false;
            shootY = 3f;
            reload = 9f;
            ejectEffect = Fx.none;
            recoil = 1f;
            x = 7f;
            shootSound = Sounds.flame;

            bullet = new LiquidBulletType(Liquids.slag){{
                damage = 13;
                speed = 2.5f;
                drag = 0.009f;
                shootEffect = Fx.shootSmall;
                lifetime = 57f;
                collidesAir = false;
            }};
        }});
    }
}
