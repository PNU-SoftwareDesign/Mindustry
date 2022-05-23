package mindustry.content.unitTypes.air;

import arc.graphics.*;
import mindustry.ai.types.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

import static mindustry.Vars.tilePayload;

public class Mega extends UnitType{
    public static final String name = "mega";

    public Mega(){
        super(name);
        defaultController = RepairAI::new;

        mineTier = 3;
        mineSpeed = 4f;
        health = 460;
        armor = 3f;
        speed = 2.5f;
        accel = 0.06f;
        drag = 0.017f;
        lowAltitude = true;
        flying = true;
        engineOffset = 10.5f;
        rotateShooting = false;
        hitSize = 16.05f;
        engineSize = 3f;
        payloadCapacity = (2 * 2) * tilePayload;
        buildSpeed = 2.6f;
        isCounted = false;

        ammoType = new PowerAmmoType(1100);

        weapons.add(
        new Weapon("heal-weapon-mount"){{
            shootSound = Sounds.lasershoot;
            reload = 24f;
            x = 8f;
            y = -6f;
            rotate = true;
            bullet = new LaserBoltBulletType(5.2f, 10){{
                lifetime = 35f;
                healPercent = 5.5f;
                collidesTeam = true;
                backColor = Pal.heal;
                frontColor = Color.white;
            }};
        }},
        new Weapon("heal-weapon-mount"){{
            shootSound = Sounds.lasershoot;
            reload = 15f;
            x = 4f;
            y = 5f;
            rotate = true;
            bullet = new LaserBoltBulletType(5.2f, 8){{
                lifetime = 35f;
                healPercent = 3f;
                collidesTeam = true;
                backColor = Pal.heal;
                frontColor = Color.white;
            }};
        }});
    }
}
