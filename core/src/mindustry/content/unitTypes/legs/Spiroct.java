package mindustry.content.unitTypes.legs;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Spiroct extends UnitType{
    public static final String name = "spiroct";

    public Spiroct(){
        super(name);
        speed = 0.52f;
        drag = 0.4f;
        hitSize = 15f;
        rotateSpeed = 3f;
        health = 940;
        immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.melting);
        legCount = 6;
        legLength = 13f;
        legTrns = 0.8f;
        legMoveSpace = 1.4f;
        legBaseOffset = 2f;
        hovering = true;
        armor = 5f;
        ammoType = new PowerAmmoType(1000);

        buildSpeed = 0.75f;

        visualElevation = 0.3f;
        groundLayer = Layer.legUnit;

        weapons.add(new Weapon("spiroct-weapon"){{
            shootY = 4f;
            reload = 14f;
            ejectEffect = Fx.none;
            recoil = 2f;
            rotate = true;
            shootSound = Sounds.sap;

            x = 8.5f;
            y = -1.5f;

            bullet = new SapBulletType(){{
                sapStrength = 0.5f;
                length = 75f;
                damage = 23;
                shootEffect = Fx.shootSmall;
                hitColor = color = Color.valueOf("bf92f9");
                despawnEffect = Fx.none;
                width = 0.54f;
                lifetime = 35f;
                knockback = -1.24f;
            }};
        }});

        weapons.add(new Weapon("mount-purple-weapon"){{
            reload = 18f;
            rotate = true;
            x = 4f;
            y = 3f;
            shootSound = Sounds.sap;

            bullet = new SapBulletType(){{
                sapStrength = 0.8f;
                length = 40f;
                damage = 18;
                shootEffect = Fx.shootSmall;
                hitColor = color = Color.valueOf("bf92f9");
                despawnEffect = Fx.none;
                width = 0.4f;
                lifetime = 25f;
                knockback = -0.65f;
            }};
        }});
    }
}
