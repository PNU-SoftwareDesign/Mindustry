import arc.func.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import org.junit.jupiter.api.*;

class UnitTypeTest {
    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: setStats to render ui
     * Input:
     * Expected: Throws NullPointerException
     */
    @Test
    void noLocalizedNameNullTest() {
        UnitType testUnit = new UnitType("testUnit1"){{
            abilities.add(new EnergyFieldAbility(35f, 65f, 180f){{
                statusDuration = 60f * 6f;
                maxTargets = 25;
            }});
        }};
        Assertions.assertThrows(NullPointerException.class, () -> {
            testUnit.setStats();
        });
    }

    /**
     * Purpose: setStats to render ui
     * Input:
     * Expected: No Throws NullPointerException
     */
    @Test
    void noAbilitySetStateTest() {
        UnitType[] nonAbilityUnits = {
        UnitTypes.mace, UnitTypes.dagger, UnitTypes.crawler, UnitTypes.fortress, UnitTypes.scepter, UnitTypes.reign, UnitTypes.vela,
        UnitTypes.corvus, UnitTypes.atrax,
        UnitTypes.spiroct, UnitTypes.arkyid, UnitTypes.toxopid,
        UnitTypes.flare, UnitTypes.eclipse, UnitTypes.horizon, UnitTypes.zenith, UnitTypes.antumbra,
        UnitTypes.mono, UnitTypes.mega, UnitTypes.quad, UnitTypes.alpha, UnitTypes.beta, UnitTypes.gamma,
        UnitTypes.risso, UnitTypes.minke, UnitTypes.sei, UnitTypes.retusa, UnitTypes.cyerce,
        UnitTypes.navanax, UnitTypes.block
        };
        Assertions.assertDoesNotThrow(() -> {
            for(UnitType unitType : nonAbilityUnits){
                unitType.setStats();
            }
        });
    }
}