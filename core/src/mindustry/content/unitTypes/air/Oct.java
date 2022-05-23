package mindustry.content.unitTypes.air;

import mindustry.ai.types.*;
import mindustry.entities.abilities.*;
import mindustry.type.*;

import static mindustry.Vars.tilePayload;

public class Oct extends UnitType{
    public static final String name = "oct";

    public Oct(){
        super(name);
        defaultController = DefenderAI::new;

        armor = 16f;
        health = 24000;
        speed = 0.8f;
        rotateSpeed = 1f;
        accel = 0.04f;
        drag = 0.018f;
        flying = true;
        engineOffset = 46f;
        engineSize = 7.8f;
        rotateShooting = false;
        hitSize = 66f;
        payloadCapacity = (5.3f * 5.3f) * tilePayload;
        buildSpeed = 4f;
        drawShields = false;
        commandLimit = 6;
        lowAltitude = true;
        buildBeamOffset = 43;
        ammoCapacity = 1;

        abilities.add(new ForceFieldAbility(140f, 4f, 7000f, 60f * 8), new RepairFieldAbility(130f, 60f * 2, 140f));
    }
}
