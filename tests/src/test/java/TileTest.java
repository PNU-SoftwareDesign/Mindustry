import mindustry.content.Blocks;
import mindustry.world.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TileTest {


    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }
    /*
     * Purpose: check tile create
     * Input: tile's x, y
     * Expected: return SUCCESS
     */

    @Test
    void checkTilePosTest() {
        Tile tile = new Tile(3,4);
        Assertions.assertTrue(tile.pos()>=0);
    }

    /*
     * Purpose: check tile Block fill with Blocks.air
     * Input: tile's x, y
     * Expected: return SUCCESS
     */

    @Test
    void checkTileBlockTest() {
        Tile tile = new Tile(5,5);
        Assertions.assertTrue(tile.block()==Blocks.air);
    }

    /*
     * Purpose: check tile array size
     * Input: tile's x, y
     * Expected: return SUCCESS
     */

    @Test
    void checkTileArrayTest() {
        Tile tile = new Tile(5,5);
        Assertions.assertTrue(tile.array() == 5);
    }

    /*
     * Purpose: check relative between tile or point
     * Input: tile or point(x,y)
     * Expected: return SUCCESS
     */
    @Test
    void checkTileRelativeTest() {
        Tile tile = new Tile(5,5);
        Tile tile1 = new Tile(4,5);
        Tile tile2 = new Tile(5,4);
        Tile tile3 = new Tile(6,5);
        Tile tile4 = new Tile(5,6);
        Assertions.assertTrue(tile.relativeTo(tile1) == 2);
        Assertions.assertTrue(tile.relativeTo(tile2) == 3);
        Assertions.assertTrue(tile.relativeTo(tile3) == 0);
        Assertions.assertTrue(tile.relativeTo(tile4) == 1);

        Assertions.assertTrue(tile.relativeTo(4,5) == 2);
        Assertions.assertTrue(tile.relativeTo(5,4) == 3);
        Assertions.assertTrue(tile.relativeTo(6,5) == 0);
        Assertions.assertTrue(tile.relativeTo(5,6) == 1);
    }


    /*
     * Purpose: check absolute relative between point
     * Input: point(x,y)
     * Expected: return SUCCESS
     */

    @Test
    void checkTileAbsoluteRelativeTest() {
        Tile tile = new Tile(10,10);

        Assertions.assertTrue(tile.absoluteRelativeTo(9,10)==2);
        Assertions.assertTrue(tile.absoluteRelativeTo(10,9)==3);
        Assertions.assertTrue(tile.absoluteRelativeTo(10,11)==1);
        Assertions.assertTrue(tile.absoluteRelativeTo(11,10)==0);

    }


}


