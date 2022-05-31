package mindustry.content;

import mindustry.content.unitTypes.*;
import mindustry.content.unitTypes.air.*;
import mindustry.content.unitTypes.legs.*;
import mindustry.content.unitTypes.mechanics.*;
import mindustry.content.unitTypes.naval.*;
import mindustry.type.*;

public class UnitTypeFactory{
    public static UnitType generateUnitType(String unitTypeName) {
        UnitType unitType;
        switch (unitTypeName) {
            case Dagger.name:
                unitType = new Dagger();
                break;
            case Mace.name:
                unitType = new Mace();
                break;
            case Fortress.name:
                unitType = new Fortress();
                break;
            case Scepter.name:
                unitType = new Scepter();
                break;
            case Reign.name:
                unitType = new Reign();
                break;
            case Nova.name:
                unitType = new Nova();
                break;
            case Pulsar.name:
                unitType = new Pulsar();
                break;
            case Quasar.name:
                unitType = new Quasar();
                break;
            case Vela.name:
                unitType = new Vela();
                break;
            case Corvus.name:
                unitType = new Corvus();
                break;
            case Crawler.name:
                unitType = new Crawler();
                break;
            case Atrax.name:
                unitType = new Atrax();
                break;
            case Spiroct.name:
                unitType = new Spiroct();
                break;
            case Arkyid.name:
                unitType = new Arkyid();
                break;
            case Toxopid.name:
                unitType = new Toxopid();
                break;
            case Flare.name:
                unitType = new Flare();
                break;
            case Horizon.name:
                unitType = new Horizon();
                break;
            case Zenith.name:
                unitType = new Zenith();
                break;
            case Antumbra.name:
                unitType = new Antumbra();
                break;
            case Eclipse.name:
                unitType = new Eclipse();
                break;
            case Mono.name:
                unitType = new Mono();
                break;
            case Poly.name:
                unitType = new Poly();
                break;
            case Mega.name:
                unitType = new Mega();
                break;
            case Quad.name:
                unitType = new Quad();
                break;
            case Oct.name:
                unitType = new Oct();
                break;
            case Risso.name:
                unitType = new Risso();
                break;
            case Minke.name:
                unitType = new Minke();
                break;
            case Bryde.name:
                unitType = new Bryde();
                break;
            case Sei.name:
                unitType = new Sei();
                break;
            case Omura.name:
                unitType = new Omura();
                break;
            case Retusa.name:
                unitType = new Retusa();
                break;
            case Oxynoe.name:
                unitType = new Oxynoe();
                break;
            case Cyerce.name:
                unitType = new Cyerce();
                break;
            case Aegires.name:
                unitType = new Aegires();
                break;
            case Navanax.name:
                unitType = new Navanax();
                break;
            case Alpha.name:
                unitType = new Alpha();
                break;
            case Beta.name:
                unitType = new Beta();
                break;
            case Gamma.name:
                unitType = new Gamma();
                break;
            case Block.name:
                unitType = new Block();
                break;
            default:
                unitType = null;
                break;
        }
        return unitType;
    }
}
