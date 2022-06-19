import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import org.junit.jupiter.api.*;

class PlanetTest{
    static private Planet planet1;
    static private Planet planet2;

    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
        planet1 = new Planet("planet1", null, 10f, 5);
        planet2 = new Planet("planet2", planet1, 10f);
    }

    /**
     * Purpose: get last sector of planet
     * Input: none
     * Expected: return SUCCESS
     * planet.getLastSector().id == 0
     */
    @Test
    void getLastSectorEmptyTest() {
        Assertions.assertEquals(planet1.getLastSector().id, 0);
    }

    /**
     * Purpose: get last sector of planet
     * Input: none
     * Expected: return SUCCESS
     * planet.getLastSector() == null
     */
    @Test
    void getLastSectorNullTest() {
        Assertions.assertNull(planet2.getLastSector());
    }

    /**
     * Purpose: calculate current rotation value when tidalLocked
     * Input: none
     * Expected: return SUCCESS
     * planet2.getRotation() == -26.08532f
     */
    @Test
    void getRotationLockTest() {
        planet2.tidalLock = true;
        Assertions.assertEquals(planet2.getRotation(), -26.08532f);
    }

    /**
     * Purpose: calculate current rotation value when tidalUnLocked
     * Input: none
     * Expected: return SUCCESS
     * planet2.getRotation() == 61.520863f
     */
    @Test
    void getRotationUnLockTest() {
        planet2.tidalLock = false;
        Assertions.assertEquals(planet2.getRotation(), 61.520863f);
    }
}