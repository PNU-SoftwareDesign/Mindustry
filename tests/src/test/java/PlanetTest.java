import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import org.junit.jupiter.api.*;

class PlanetTest{
    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: get last sector of planet
     * Input: none
     * Expected: return SUCCESS
     * planet.getLastSector().id == 0
     */
    @Test
    void getLastSectorEmptyTest() {
        Planet planet = new Planet("planet1", null, 10f, 5);
        Assertions.assertEquals(planet.getLastSector().id, 0);
    }

    /**
     * Purpose: get last sector of planet
     * Input: none
     * Expected: return SUCCESS
     * planet.getLastSector() == null
     */
    @Test
    void getLastSectorNullTest() {
        Planet planet = new Planet("planet2", null, 10f);
        Assertions.assertNull(planet.getLastSector());
    }
}