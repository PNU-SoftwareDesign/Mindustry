package mindustry.content.unitTypes.mechanics;

import arc.graphics.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Quasar extends UnitType{
    public static final String name = "quasar";

    public Quasar(){
        super(name);
        mineTier = 3;
        boostMultiplier = 2f;
        health = 640f;
        buildSpeed = 1.7f;
        canBoost = true;
        armor = 9f;
        landShake = 2f;
        riseSpeed = 0.05f;

        commandLimit = 10;
        mechFrontSway = 0.55f;
        ammoType = new PowerAmmoType(1500);

        speed = 0.4f;
        hitSize = 13f;

        mineSpeed = 6f;
        drawShields = false;

        abilities.add(new ForceFieldAbility(60f, 0.3f, 400f, 60f * 6));

        weapons.add(new Weapon("beam-weapon"){{
            top = false;
            shake = 2f;
            shootY = 4f;
            x = 6.5f;
            reload = 55f;
            recoil = 4f;
            shootSound = Sounds.laser;

            bullet = new LaserBulletType(){{
                damage = 45f;
                recoil = 1f;
                sideAngle = 45f;
                sideWidth = 1f;
                sideLength = 70f;
                healPercent = 10f;
                collidesTeam = true;
                length = 135f;
                colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
            }};
        }});
    }
}
