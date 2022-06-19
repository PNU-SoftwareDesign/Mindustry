import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import org.junit.jupiter.api.*;

class LiquidTest{

    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: set Stats to rendering table ui
     * Input: none
     * Expected: return SUCCESS
     * oil.stats.toMap().get(Stat.explosiveness.category) -> NotNull
     * oil.stats.toMap().get(Stat.flammability.category) -> NotNull
     * oil.stats.toMap().get(Stat.temperature.category) -> NotNull
     * oil.stats.toMap().get(Stat.heatCapacity.category) -> NotNull
     * oil.stats.toMap().get(Stat.viscosity.category) -> NotNull
     *
     * oil.stats.toMap().get(Stat.explosiveness.category).getNull(Stat.explosiveness) -> NotNull
     * oil.stats.toMap().get(Stat.flammability.category).getNull(Stat.flammability) -> NotNull
     * oil.stats.toMap().get(Stat.temperature.category).getNull(Stat.temperature) -> NotNull
     * oil.stats.toMap().get(Stat.heatCapacity.category).getNull(Stat.heatCapacity) -> NotNull
     * oil.stats.toMap().get(Stat.viscosity.category).getNull(Stat.viscosity) -> NotNull
     */
    @Test
    void setStatsTest() {
        Liquid oil = Liquids.oil;
        oil.setStats();

        Assertions.assertNotNull(oil.stats.toMap().get(Stat.explosiveness.category));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.flammability.category));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.temperature.category));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.heatCapacity.category));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.viscosity.category));

        Assertions.assertNotNull(oil.stats.toMap().get(Stat.explosiveness.category).getNull(Stat.explosiveness));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.flammability.category).getNull(Stat.flammability));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.temperature.category).getNull(Stat.temperature));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.heatCapacity.category).getNull(Stat.heatCapacity));
        Assertions.assertNotNull(oil.stats.toMap().get(Stat.viscosity.category).getNull(Stat.viscosity));
    }

    /**
     * Purpose: check Liquid can extinguish fire
     * Input: (flammability, temperature)
     * Expected: return SUCCESS
     * (0.1f, 0.5f) -> False
     * (0.09999f, 0.5f) -> True
     */
    @Test
    void canExtinguishTest() {
        Liquid testLiquid = new Liquid("testLiquid");
        testLiquid.flammability = 0.1f;
        testLiquid.temperature = 0.5f;
        Assertions.assertFalse(testLiquid.canExtinguish());

        testLiquid.flammability = 0.09999f;
        testLiquid.temperature = 0.5f;
        Assertions.assertTrue(testLiquid.canExtinguish());
    }
}