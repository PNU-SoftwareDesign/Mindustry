package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class Reign extends UnitType{
    public static final String name = "reign";

    public Reign(){
        super(name);
        speed = 0.35f;
        hitSize = 26f;
        rotateSpeed = 1.65f;
        health = 24000;
        armor = 14f;
        mechStepParticles = true;
        mechStepShake = 0.75f;
        drownTimeMultiplier = 6f;
        mechFrontSway = 1.9f;
        mechSideSway = 0.6f;
        ammoType = new ItemAmmoType(Items.thorium);

        weapons.add(
            new Weapon("reign-weapon"){{
                top = false;
                y = 1f;
                x = 21.5f;
                shootY = 11f;
                reload = 9f;
                recoil = 5f;
                shake = 2f;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.bang;

                bullet = new BasicBulletType(13f, 70){{
                    pierce = true;
                    pierceCap = 10;
                    width = 14f;
                    height = 33f;
                    lifetime = 15f;
                    shootEffect = Fx.shootBig;
                    fragVelocityMin = 0.4f;

                    hitEffect = Fx.blastExplosion;
                    splashDamage = 16f;
                    splashDamageRadius = 13f;

                    fragBullets = 3;
                    fragLifeMin = 0f;
                    fragCone = 30f;

                    fragBullet = new BasicBulletType(9f, 18){{
                        width = 10f;
                        height = 10f;
                        pierce = true;
                        pierceBuilding = true;
                        pierceCap = 3;

                        lifetime = 20f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 15f;
                        splashDamageRadius = 10f;
                    }};
                }};
            }}
        );
    }
}
