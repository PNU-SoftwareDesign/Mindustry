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
     * Input:
     * Expected: Throws NullPointerException
     */
    @Test
    void noLocalizedNameNullTest() {
        UnitType testUnit = new UnitType("testUnit"){{
            abilities.add(new EnergyFieldAbility(35f, 65f, 180f){{
                statusDuration = 60f * 6f;
                maxTargets = 25;
            }});
        }};
        Assertions.assertThrows(NullPointerException.class, () -> {
            testUnit.setStats();
        });
    }
}