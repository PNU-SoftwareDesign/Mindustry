package mindustry.content.unitTypes.mechanics;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Nova extends UnitType{
    public static final String name = "nova";

    public Nova(){
        super(name);
        canBoost = true;
        boostMultiplier = 1.5f;
        speed = 0.55f;
        hitSize = 8f;
        health = 120f;
        buildSpeed = 0.8f;
        armor = 1f;
        commandLimit = 8;

        abilities.add(new RepairFieldAbility(10f, 60f * 4, 60f));
        ammoType = new PowerAmmoType(1000);

        weapons.add(new Weapon("heal-weapon"){{
            top = false;
            shootY = 2f;
            reload = 24f;
            x = 4.5f;
            alternate = false;
            ejectEffect = Fx.none;
            recoil = 2f;
            shootSound = Sounds.lasershoot;

            bullet = new LaserBoltBulletType(5.2f, 13){{
                lifetime = 30f;
                healPercent = 5f;
                collidesTeam = true;
                backColor = Pal.heal;
                frontColor = Color.white;
            }};
        }});
    }
}
