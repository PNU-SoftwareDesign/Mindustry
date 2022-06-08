import mindustry.world.Tile;
import mindustry.world.Tiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static mindustry.Vars.world;

class TilesTest {


    @BeforeAll
    static void beforeAll() {
        ApplicationTests.launchApplication();
    }


    /*
     * Purpose: check point in boundary
     * Input: point(x,y)
     * Expected: return SUCCESS
     */

    @Test
    void checkInBoundTest() {
        Tiles tiles = new Tiles(5,5);

        System.out.println(tiles.in(4,4));
        Assertions.assertTrue(tiles.in(4,4) == true);
        Assertions.assertTrue(tiles.in(6,6) == false);
        Assertions.assertTrue(tiles.in(4,5) == false);
        Assertions.assertTrue(tiles.in(5,4) == false);
        Assertions.assertTrue(tiles.in(5,5) == false);


    }

    /*
     * Purpose: check fill() and Iterator
     * Input: Tiles with fill()
     * Expected: return SUCCESS
     */

    @Test
    void checkIteratorTile() {
        int width = 5;
        int height = 5;
        Tiles tiles = new Tiles(width,height);
        tiles.fill();

        Iterator<Tile> it = tiles.iterator();

        int i = 0;

        while(it.hasNext()){

            Assertions.assertTrue(it.next().array() == (i%width) + (i/width) * world.tiles.width   );
            i += 1;
        }
    }



}
