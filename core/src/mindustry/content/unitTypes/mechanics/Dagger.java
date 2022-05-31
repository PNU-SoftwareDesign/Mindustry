package mindustry.content.unitTypes.mechanics;

import mindustry.content.*;
import mindustry.type.*;

public class Dagger extends UnitType{
    public static final String name = "dagger";
    public Dagger(){
        super(name);
        speed = 0.5f;
        hitSize = 8f;
        health = 150;
        weapons.add(new Weapon("large-weapon"){{
            reload = 13f;
            x = 4f;
            y = 2f;
            top = false;
            ejectEffect = Fx.casing1;
            bullet = Bullets.standardCopper;
        }});
    }
}
