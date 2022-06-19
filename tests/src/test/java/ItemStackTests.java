import mindustry.content.Items;
import mindustry.type.ItemStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemStackTests {

    @BeforeAll
    static void setUp() {
        ApplicationTests.launchApplication();
    }

    /**
     * Purpose: check setter works correctly in all branch cases
     * Input: set (Items.sand, 2)
     * Expected:
     *  (Items.sand, 2) = (Items.sand, 2)
     */
    @Test
    void set() {
        ItemStack itemStack = new ItemStack();

        itemStack.set(Items.sand, 2);
        assertEquals(itemStack.item, Items.sand);
        assertEquals(itemStack.amount, 2);
    }

    /**
     * Purpose: check copied instance's contents are equal with original one
     * Input: copy
     * Expected:
     *  itemStack.copy() = itemStack
     */
    @Test
    void copy() {
        ItemStack itemStack = new ItemStack(Items.sand, 1);
        ItemStack copied = itemStack.copy();

        assertTrue(itemStack.equals(copied));
    }

    /**
     * Purpose: check equal method works correctly
     * Input: equals (Items.sand, 0), (Items.sand, 1) (Items.copper, 1)
     * Expected:
     *  (Items.sand, 0).equals(Items.sand, 0) = True
     *  (Items.sand, 0).equals(Items.sand, 1) = False
     *  (Items.sand, 0).equals(Items.copper, 0) = False
     *  (Items.sand, 0).equals(Items.copper, 1) = False
     */
    @Test
    void testEquals() {
        ItemStack itemStack0 = new ItemStack(Items.copper, 0);
        ItemStack itemStack1 = new ItemStack(Items.copper, 0);
        ItemStack itemStack2 = new ItemStack(Items.copper, 1);
        ItemStack itemStack3 = new ItemStack(Items.sand, 0);
        ItemStack itemStack4 = new ItemStack(Items.sand, 1);

        assertTrue(itemStack0.equals(itemStack1));
        assertFalse(itemStack0.equals(itemStack2));
        assertFalse(itemStack0.equals(itemStack3));
        assertFalse(itemStack0.equals(itemStack4));
    }

    /**
     * Purpose: check equal method works correctly
     * Input: equals (Items.sand, 0), (Items.sand, 1) (Items.copper, 1)
     * Expected:
     *  (Items.sand, 0).equals(Items.sand, 0) = True
     *  (Items.sand, 0).equals(Items.sand, 1) = False
     *  (Items.sand, 0).equals(Items.copper, 0) = False
     *  (Items.sand, 0).equals(Items.copper, 1) = False
     */
    @Test
    void testEquals1() {
        ItemStack itemStack0 = new ItemStack(Items.copper, 0);
        ItemStack itemStack1 = new ItemStack(Items.copper, 0);
        ItemStack itemStack2 = new ItemStack(Items.copper, 1);
        ItemStack itemStack3 = new ItemStack(Items.sand, 0);
        ItemStack itemStack4 = new ItemStack(Items.sand, 1);

        assertTrue(itemStack0.equals((Object)itemStack0));
        assertTrue(itemStack0.equals((Object)itemStack1));
        assertFalse(itemStack0.equals((Object)itemStack2));
        assertFalse(itemStack0.equals((Object)itemStack3));
        assertFalse(itemStack0.equals((Object)itemStack4));
    }

    /**
     * Purpose: check display string printed as expected
     * Input: toString
     * Expected:
     *  (Items.sand, 10).toString() = "ItemStack{item=sand, amount=10}"
     */
    @Test
    void testToString() {
        ItemStack itemStack = new ItemStack(Items.sand, 10);
        assertEquals(itemStack.toString(), "ItemStack{item=sand, amount=10}");
    }
}