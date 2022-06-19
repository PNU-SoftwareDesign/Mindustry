import arc.graphics.Color;
import mindustry.game.Team;
import org.junit.jupiter.api.*;

import static mindustry.game.Team.derelict;
import static mindustry.game.Team.purple;
import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void noPaletteTeamTest(){
        Team test = derelict;

        assertEquals(test.id,0);
        assertEquals(test.name,"derelict");
        assertEquals(test.color, Color.valueOf("4d4e58"));

        assertFalse(test.hasPalette);
    }

    @Test
    public void havePaletteTeamTest(){
        Team test = purple;

        assertEquals(test.id,4);
        assertEquals(test.name,"purple");
        assertEquals(test.color, Color.valueOf("995bb0"));

        assertTrue(test.hasPalette);
    }
}
