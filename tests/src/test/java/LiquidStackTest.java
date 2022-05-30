import mindustry.content.*;
import mindustry.type.*;
import org.junit.jupiter.api.*;

import java.util.*;

class LiquidStackTest{
    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: multiply LiquidStackList by amount
     * Input: LiquidStack[] stacks, float amount
     * Expected: return SUCCESS
     * { new LiquidStack(Liquids.water, 5), new LiquidStack(Liquids.oil, 0),
     * new LiquidStack(Liquids.cryofluid, -5),new LiquidStack(Liquids.slag, 1) } , 10 ->
     *  { new LiquidStack(Liquids.water, 50), new LiquidStack(Liquids.oil, 0),
     * new LiquidStack(Liquids.cryofluid, -50),new LiquidStack(Liquids.slag, 10) }
     * { new LiquidStack(Liquids.water, 5), new LiquidStack(Liquids.oil, 0),
     * new LiquidStack(Liquids.cryofluid, -5),new LiquidStack(Liquids.slag, 1) } , 1 ->
     * { new LiquidStack(Liquids.water, 5), new LiquidStack(Liquids.oil, 0),
     * new LiquidStack(Liquids.cryofluid, -5),new LiquidStack(Liquids.slag, 1) }
     */
    @Test
    void multTest() {
        LiquidStack[] liquidStacks = new LiquidStack[]{
            new LiquidStack(Liquids.water, 5),
            new LiquidStack(Liquids.oil, 0),
            new LiquidStack(Liquids.cryofluid, -5),
            new LiquidStack(Liquids.slag, 1),
        };

        LiquidStack[] multedStacks1 = LiquidStack.mult(liquidStacks, 10);
        Assertions.assertEquals(multedStacks1.length, liquidStacks.length);
        Assertions.assertEquals(multedStacks1[0].amount, 50);
        Assertions.assertEquals(multedStacks1[1].amount, 0);
        Assertions.assertEquals(multedStacks1[2].amount, -50);
        Assertions.assertEquals(multedStacks1[3].amount, 10);

        liquidStacks[0] = liquidStacks[0].set(Liquids.water, 5);
        liquidStacks[1] = liquidStacks[1].set(Liquids.oil, 0);
        liquidStacks[2] = liquidStacks[2].set(Liquids.cryofluid, -5);
        liquidStacks[3] = liquidStacks[3].set(Liquids.slag, 1);

        LiquidStack[] multedStacks2 = LiquidStack.mult(liquidStacks, 1);
        Assertions.assertEquals(multedStacks2.length, liquidStacks.length);
        for (int i = 0; i < liquidStacks.length; ++i) {
            Assertions.assertTrue(liquidStacks[i].equals(multedStacks2[i]));
        }
    }
}