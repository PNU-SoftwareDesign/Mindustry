package mindustry.content;

import mindustry.annotations.Annotations.*;
import mindustry.content.unitTypes.*;
import mindustry.content.unitTypes.air.*;
import mindustry.content.unitTypes.legs.*;
import mindustry.content.unitTypes.mechanics.*;
import mindustry.content.unitTypes.naval.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;

public class UnitTypes implements ContentList{
    //region definitions

    //mech
    public static @EntityDef({Unitc.class, Mechc.class}) UnitType mace, dagger, crawler, fortress, scepter, reign, vela;

    //mech, legacy
    public static @EntityDef(value = {Unitc.class, Mechc.class}, legacy = true) UnitType nova, pulsar, quasar;

    //legs
    public static @EntityDef({Unitc.class, Legsc.class}) UnitType corvus, atrax;

    //legs, legacy
    public static @EntityDef(value = {Unitc.class, Legsc.class}, legacy = true) UnitType spiroct, arkyid, toxopid;

    //air
    public static @EntityDef({Unitc.class}) UnitType flare, eclipse, horizon, zenith, antumbra;

    //air, legacy
    public static @EntityDef(value = {Unitc.class}, legacy = true) UnitType mono;

    //air, legacy
    public static @EntityDef(value = {Unitc.class}, legacy = true) UnitType poly;

    //air + payload
    public static @EntityDef({Unitc.class, Payloadc.class}) UnitType mega;

    //air + payload, legacy
    public static @EntityDef(value = {Unitc.class, Payloadc.class}, legacy = true) UnitType quad;

    //air + payload + legacy (different branch)
    public static @EntityDef(value = {Unitc.class, Payloadc.class}, legacy = true) UnitType oct;

    //air, legacy
    public static @EntityDef(value = {Unitc.class}, legacy = true) UnitType alpha, beta, gamma;

    //naval
    public static @EntityDef({Unitc.class, WaterMovec.class}) UnitType risso, minke, bryde, sei, omura, retusa, oxynoe, cyerce, aegires, navanax;

    //special block unit type
    public static @EntityDef({Unitc.class, BlockUnitc.class}) UnitType block;

    //endregion

    @Override
    public void load(){
        //region ground attack
        dagger = UnitTypeFactory.generateUnitType(Dagger.name);
        mace = UnitTypeFactory.generateUnitType(Mace.name);
        fortress = UnitTypeFactory.generateUnitType(Fortress.name);
        scepter = UnitTypeFactory.generateUnitType(Scepter.name);
        reign = UnitTypeFactory.generateUnitType(Reign.name);
        //end region
        //region ground suport
        nova = UnitTypeFactory.generateUnitType(Nova.name);
        pulsar = UnitTypeFactory.generateUnitType(Pulsar.name);
        quasar = UnitTypeFactory.generateUnitType(Quasar.name);
        vela = UnitTypeFactory.generateUnitType(Vela.name);
        corvus = UnitTypeFactory.generateUnitType(Corvus.name);
        //endregion
        //region ground legs
        crawler = UnitTypeFactory.generateUnitType(Crawler.name);
        atrax = UnitTypeFactory.generateUnitType(Atrax.name);
        spiroct = UnitTypeFactory.generateUnitType(Spiroct.name);
        arkyid = UnitTypeFactory.generateUnitType(Arkyid.name);
        toxopid = UnitTypeFactory.generateUnitType(Toxopid.name);
        //endregion
        //region air attack
        flare = UnitTypeFactory.generateUnitType(Flare.name);
        horizon = UnitTypeFactory.generateUnitType(Horizon.name);
        zenith = UnitTypeFactory.generateUnitType(Zenith.name);
        antumbra = UnitTypeFactory.generateUnitType(Antumbra.name);
        eclipse = UnitTypeFactory.generateUnitType(Eclipse.name);
        //endregion
        //region air support
        mono = UnitTypeFactory.generateUnitType(Mono.name);
        poly = UnitTypeFactory.generateUnitType(Poly.name);
        mega = UnitTypeFactory.generateUnitType(Mega.name);
        quad = UnitTypeFactory.generateUnitType(Quad.name);
        oct = UnitTypeFactory.generateUnitType(Oct.name);
        //endregion
        //region naval attack
        risso = UnitTypeFactory.generateUnitType(Risso.name);
        minke = UnitTypeFactory.generateUnitType(Minke.name);
        bryde = UnitTypeFactory.generateUnitType(Bryde.name);
        sei = UnitTypeFactory.generateUnitType(Sei.name);
        omura = UnitTypeFactory.generateUnitType(Omura.name);
        //endregion
        //region naval support
        retusa = UnitTypeFactory.generateUnitType(Retusa.name);
        oxynoe = UnitTypeFactory.generateUnitType(Oxynoe.name);
        cyerce = UnitTypeFactory.generateUnitType(Cyerce.name);
        aegires = UnitTypeFactory.generateUnitType(Aegires.name);
        navanax = UnitTypeFactory.generateUnitType(Navanax.name);
        //endregion
        //region core
        alpha = UnitTypeFactory.generateUnitType(Alpha.name);
        beta = UnitTypeFactory.generateUnitType(Beta.name);
        gamma = UnitTypeFactory.generateUnitType(Gamma.name);
        //endregion
        //region internal
        block = UnitTypeFactory.generateUnitType(Block.name);
        //endregion
    }
}
