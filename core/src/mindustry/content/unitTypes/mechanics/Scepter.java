package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Scepter extends UnitType{
    public static final String name = "scepter";

    public Scepter(){
        super(name);
        speed = 0.36f;
        hitSize = 22f;
        rotateSpeed = 2.1f;
        health = 9000;
        armor = 10f;
        mechFrontSway = 1f;
        ammoType = new ItemAmmoType(Items.thorium);

        mechStepParticles = true;
        mechStepShake = 0.15f;
        singleTarget = true;
        drownTimeMultiplier = 4f;

        weapons.add(
            new Weapon("scepter-weapon"){{
                top = false;
                y = 1f;
                x = 16f;
                shootY = 8f;
                reload = 45f;
                recoil = 5f;
                shake = 2f;
                ejectEffect = Fx.casing3;
                shootSound = Sounds.bang;
                shots = 3;
                inaccuracy = 3f;
                shotDelay = 4f;

                bullet = new BasicBulletType(7f, 50){{
                    width = 11f;
                    height = 20f;
                    lifetime = 25f;
                    shootEffect = Fx.shootBig;
                    lightning = 2;
                    lightningLength = 6;
                    lightningColor = Pal.surge;
                    //standard bullet damage is far too much for lightning
                    lightningDamage = 20;
                }};
            }},

            new Weapon("mount-weapon"){{
                reload = 13f;
                x = 8.5f;
                y = 6f;
                rotate = true;
                ejectEffect = Fx.casing1;
                bullet = Bullets.standardCopper;
            }},
            new Weapon("mount-weapon"){{
                reload = 16f;
                x = 8.5f;
                y = -7f;
                rotate = true;
                ejectEffect = Fx.casing1;
                bullet = Bullets.standardCopper;
            }}
        );
    }
}

