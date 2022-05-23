package mindustry.content.unitTypes;

import mindustry.type.*;

public class Block extends UnitType{
    public static final String name = "block";

    public Block(){
        super(name);
        speed = 0f;
        hitSize = 0f;
        health = 1;
        rotateSpeed = 360f;
        itemCapacity = 0;
        commandLimit = 0;
    }
    @Override
    public boolean isHidden(){
        return true;
    }
}
