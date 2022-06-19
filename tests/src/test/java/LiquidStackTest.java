import arc.struct.*;
import mindustry.content.*;
import mindustry.type.*;
import org.junit.jupiter.api.*;

import java.util.*;

class LiquidStackTest{
    private final LiquidStack[] liquidStacks = new LiquidStack[]{
        new LiquidStack(Liquids.water, 5),
        new LiquidStack(Liquids.oil, 0),
        new LiquidStack(Liquids.cryofluid, -5),
        new LiquidStack(Liquids.slag, 1),
    };

    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: multiply LiquidStackList by amount
     * Input: LiquidStack[] stacks, float amount
     * Expected: return SUCCESS
     * { LiquidStack(Liquids.water, 5), LiquidStack(Liquids.oil, 0),
     * LiquidStack(Liquids.cryofluid, -5),LiquidStack(Liquids.slag, 1) } , 10 ->
     *  { LiquidStack(Liquids.water, 50), LiquidStack(Liquids.oil, 0),
     * LiquidStack(Liquids.cryofluid, -50),LiquidStack(Liquids.slag, 10) }
     * { LiquidStack(Liquids.water, 5), LiquidStack(Liquids.oil, 0),
     * LiquidStack(Liquids.cryofluid, -5),LiquidStack(Liquids.slag, 1) } , 1 ->
     * { LiquidStack(Liquids.water, 5), LiquidStack(Liquids.oil, 0),
     * LiquidStack(Liquids.cryofluid, -5), LiquidStack(Liquids.slag, 1) }
     */
    @Test
    void multTest() {
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
            Assertions.assertEquals(multedStacks2[i], liquidStacks[i]);
        }
    }

    /**
     * Purpose: Convert list of object to LiquidStackList
     * Input: Object[] items
     * Expected: return SUCCESS
     * {Liquids.water, 5, Liquids.oil, 0, Liquids.cryofluid, -5, Liquids.slag, 1 } ->
     * { LiquidStack(Liquids.water, 5), LiquidStack(Liquids.oil, 0),
     * LiquidStack(Liquids.cryofluid, -5),LiquidStack(Liquids.slag, 1) }
     */
    @Test
    void withTest(){
        Object[] items = new Object[]{
        Liquids.water, 5,
        Liquids.oil, 0,
        Liquids.cryofluid, -5,
        Liquids.slag, 1
        };

        LiquidStack[] withStack = LiquidStack.with(items);
        for (int i = 0; i < liquidStacks.length; ++i) {
            Assertions.assertEquals(withStack[i], liquidStacks[i]);
        }
    }

    /**
     * Purpose: Convert list of object to Seq<LiquidStack>
     * Input: Object[] items
     * Expected: return SUCCESS
     */
    @Test
    void listTest(){
        Object[] items = new Object[]{
        Liquids.water, 5,
        Liquids.oil, 0,
        Liquids.cryofluid, -5,
        Liquids.slag, 1
        };

        Seq<LiquidStack> withStack = LiquidStack.list(items);
        withStack.forEach((liquidStack) -> {
            Assertions.assertEquals(liquidStack, liquidStacks[withStack.indexOf(liquidStack)]);
        });
    }

    /**
     * Purpose: Check same(semantically) LiquidStack
     * Input: Object o
     * Expected: return SUCCESS
     */
    @Test
    void equalsSameObjectTest() {
        LiquidStack liquidStack = new LiquidStack(Liquids.water, 1);
        Assertions.assertEquals(liquidStack, liquidStack);
    }

    /**
     * Purpose: Check same(semantically) LiquidStack
     * Input: Object o
     * Expected: return SUCCESS
     */
    @Test
    void equalsNotSameInstanceTest() {
        LiquidStack liquidStack = new LiquidStack(Liquids.water, 1);
        Object itemStack = new ItemStack(Items.sand, 1);
        Assertions.assertNotEquals(liquidStack, itemStack);
    }

    /**
     * Purpose: Check same(semantically) LiquidStack
     * Input: Object o
     * Expected: return SUCCESS
     */
    @Test
    void equalsSameInstanceTest() {
        Object liquidStack1 = new LiquidStack(Liquids.water, 1);
        Object liquidStack2 = new LiquidStack(Liquids.water, 1);
        Assertions.assertEquals(liquidStack1, liquidStack2);
    }

    /**
     * Purpose: Check same(semantically) LiquidStack
     * Input: Object o
     * Expected: return SUCCESS
     */
    @Test
    void equalsSameInstanceNotSameTest() {
        Object liquidStack1 = new LiquidStack(Liquids.water, 1);
        Object liquidStack2 = new LiquidStack(Liquids.water, 2);
        Assertions.assertNotEquals(liquidStack1, liquidStack2);
    }
}