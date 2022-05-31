package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Pulsar extends UnitType{
    public static final String name = "pulsar";

    public Pulsar(){
        super(name);
        canBoost = true;
        boostMultiplier = 1.6f;
        speed = 0.7f;
        hitSize = 11f;
        health = 320f;
        buildSpeed = 0.9f;
        armor = 4f;
        riseSpeed = 0.07f;

        mineTier = 2;
        mineSpeed = 5f;
        commandLimit = 9;

        abilities.add(new ShieldRegenFieldAbility(20f, 40f, 60f * 5, 60f));
        ammoType = new PowerAmmoType(1300);

        weapons.add(new Weapon("heal-shotgun-weapon"){{
            top = false;
            x = 5f;
            shake = 2.2f;
            y = 0.5f;
            shootY = 2.5f;

            reload = 36f;
            shots = 3;
            inaccuracy = 35;
            shotDelay = 0.5f;
            spacing = 0f;
            ejectEffect = Fx.none;
            recoil = 2.5f;
            shootSound = Sounds.spark;

            bullet = new LightningBulletType(){{
                lightningColor = hitColor = Pal.heal;
                damage = 14f;
                lightningLength = 7;
                lightningLengthRand = 7;
                shootEffect = Fx.shootHeal;
                //Does not actually do anything; Just here to make stats work
                healPercent = 2f;

                lightningType = new BulletType(0.0001f, 0f){{
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    healPercent = 2f;
                    collidesTeam = true;
                }};
            }};
        }});
    }
}
