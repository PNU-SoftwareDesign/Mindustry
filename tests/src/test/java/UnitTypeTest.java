import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.type.*;
import org.junit.jupiter.api.*;

class UnitTypeTest {
    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: setStats to render ui
     * Input: none
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
     * Input: none
     * Expected: No Throws Excpetion
     */
    @Test
    void setStateTest() {
        UnitType[] nonAbilityUnits = {
        UnitTypes.mace, UnitTypes.dagger, UnitTypes.crawler, UnitTypes.fortress, UnitTypes.scepter, UnitTypes.reign, UnitTypes.vela,
        UnitTypes.corvus, UnitTypes.atrax,
        UnitTypes.spiroct, UnitTypes.arkyid, UnitTypes.toxopid,
        UnitTypes.flare, UnitTypes.eclipse, UnitTypes.horizon, UnitTypes.zenith, UnitTypes.antumbra,
        UnitTypes.mono, UnitTypes.mega, UnitTypes.quad, UnitTypes.alpha, UnitTypes.beta, UnitTypes.gamma,
        UnitTypes.risso, UnitTypes.minke, UnitTypes.sei, UnitTypes.retusa, UnitTypes.cyerce,
        UnitTypes.navanax, UnitTypes.block,
        UnitTypes.nova, UnitTypes.pulsar, UnitTypes.quasar, UnitTypes.poly, UnitTypes.oct, UnitTypes.bryde, UnitTypes.omura, UnitTypes.oxynoe, UnitTypes.aegires,
        };
        Assertions.assertDoesNotThrow(() -> {
            for(UnitType unitType : nonAbilityUnits){
                unitType.setStats();
            }
        });
    }

    /**
     * Purpose: get requirements list
     * Input: none
     * Expected: There is no zero requirements units.
     */
    @Test
    void getTotalRequirementsTest() {
        UnitType[] unitTypes = {
        UnitTypes.mace, UnitTypes.dagger, UnitTypes.crawler, UnitTypes.fortress, UnitTypes.scepter, UnitTypes.reign, UnitTypes.vela,
        UnitTypes.corvus, UnitTypes.atrax,
        UnitTypes.spiroct, UnitTypes.arkyid, UnitTypes.toxopid,
        UnitTypes.flare, UnitTypes.eclipse, UnitTypes.horizon, UnitTypes.zenith, UnitTypes.antumbra,
        UnitTypes.mono, UnitTypes.mega, UnitTypes.quad,
        UnitTypes.risso, UnitTypes.minke, UnitTypes.sei, UnitTypes.retusa, UnitTypes.cyerce,
        UnitTypes.navanax
        };
        for(UnitType unitType : unitTypes){
            Assertions.assertNotEquals(unitType.getTotalRequirements().length, 0);
        }
    }
}