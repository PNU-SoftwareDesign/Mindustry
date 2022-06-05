import mindustry.content.*;
import mindustry.type.*;
import org.junit.jupiter.api.*;

class ItemSeqTest{
    final private ItemSeq itemSeq = new ItemSeq();

    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }

    @BeforeEach
    void beforeEach() {
        itemSeq.clear();
        itemSeq.add(new ItemStack(Items.copper, 10));
        itemSeq.add(new ItemStack(Items.coal, 10));
        itemSeq.add(new ItemStack(Items.blastCompound, 15));
        itemSeq.add(new ItemStack(Items.phaseFabric, 6));
        itemSeq.add(new ItemStack(Items.titanium, 7));
        itemSeq.add(new ItemStack(Items.lead, 8));
        itemSeq.add(new ItemStack(Items.metaglass, 9));
    }

    void insertNegative() {
        itemSeq.add(new ItemStack(Items.graphite, -10));
        itemSeq.add(new ItemStack(Items.scrap, -10));
        itemSeq.add(new ItemStack(Items.pyratite, -15));
        itemSeq.add(new ItemStack(Items.sand, -6));
        itemSeq.add(new ItemStack(Items.silicon, -7));
        itemSeq.add(new ItemStack(Items.thorium, -8));
        itemSeq.add(new ItemStack(Items.surgeAlloy, -9));
    }

    @Test
    void testCheckNegative() {
        insertNegative();

        itemSeq.checkNegative();
        itemSeq.forEach((itemStack) -> {
            Assertions.assertTrue(itemStack.amount >= 0);
        });
    }
}