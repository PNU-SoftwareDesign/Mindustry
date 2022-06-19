package mindustry.editor;

import arc.*;
import arc.backend.headless.*;
import arc.files.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.core.GameState.*;
import mindustry.ctype.*;
import mindustry.editor.DrawOperation.*;
import mindustry.game.*;
import mindustry.maps.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.net.*;
import mindustry.world.*;
import org.junit.jupiter.api.*;

import static mindustry.Vars.*;
import static org.junit.jupiter.api.Assertions.*;

class DrawOperationTest{
    private DrawOperation drawOperation;
    static Map testMap;
    static boolean initialized;
    //core/assets
    static final Fi testDataFolder = new Fi("../../tests/build/test_data");

    @BeforeAll
    public static void launchApplication(){
        launchApplication(true);
    }

    public static void launchApplication(boolean clear){
        //only gets called once
        if(initialized) return;
        initialized = true;

        try{
            boolean[] begins = {false};
            Throwable[] exceptionThrown = {null};
            Log.useColors = false;

            ApplicationCore core = new ApplicationCore(){
                @Override
                public void setup(){
                    //clear older data
                    if(clear){
                        DrawOperationTest.testDataFolder.deleteDirectory();
                    }

                    Core.settings.setDataDirectory(testDataFolder);
                    headless = true;
                    net = new Net(null);
                    tree = new FileTree();
                    Vars.init();
                    world = new World(){
                        @Override
                        public float getDarkness(int x, int y){
                            //for world borders
                            return 0;
                        }
                    };
                    content.createBaseContent();
                    mods.loadScripts();
                    content.createModContent();

                    add(logic = new Logic());
                    add(netServer = new NetServer());

                    content.init();

                    mods.eachClass(Mod::init);

                    if(mods.hasContentErrors()){
                        for(LoadedMod mod : mods.list()){
                            if(mod.hasContentErrors()){
                                for(Content cont : mod.erroredContent){
                                    throw new RuntimeException("error in file: " + cont.minfo.sourceFile.path(), cont.minfo.baseError);
                                }
                            }
                        }
                    }

                }

                @Override
                public void init(){
                    super.init();
                    begins[0] = true;
                    testMap = maps.loadInternalMap("groundZero");
                    Thread.currentThread().interrupt();
                }
            };

            new HeadlessApplication(core, throwable -> exceptionThrown[0] = throwable);

            while(!begins[0]){
                if(exceptionThrown[0] != null){
                    fail(exceptionThrown[0]);
                }
                Thread.sleep(10);
            }

            Block block = content.getByName(ContentType.block, "build2");
            assertEquals("build2", block == null ? null : block.name, "2x2 construct block doesn't exist?");
        }catch(Throwable r){
            fail(r);
        }
    }

    @BeforeEach
    void resetWorld(){
        Time.setDeltaProvider(() -> 1f);
        logic.reset();
        state.set(State.menu);
        drawOperation = new DrawOperation();
    }

    @AfterEach
    void tearDown(){
        drawOperation = null;
    }

    /**
     * Purpose: check that getTile() get TileID properly
     * Input:
     * 1. Tile(metalFloor), OpType.floor
     * 2. Tile(switchBlock), OpType.block
     * 3. Tile(conveyor, not rotated), OpType.rotation
     * 4. Tile(conveyor, rotated), OpType.rotation
     * 5. Tile(grass), OpType.overlay
     * 6. Tile(coreShred), OpType.team
     * Expected:
     * 1. floor.floorID()
     * 2. block.blockID()
     * 3. 0 (not rotate)
     * 4. 1 (rotate)
     * 5. overlay.overlayID()
     * 6. team.getTeamID()
     */
    @Test
    void getTileTest(){
        world.loadMap(testMap);
        state.set(State.playing);

        Tile floor = world.rawTile(5, 0);
        floor.setBlock(Blocks.metalFloor, Team.sharded);
        assertEquals(floor.floorID(), drawOperation.getTile(floor, (byte)OpType.floor.ordinal()));

        Tile block = world.rawTile(5,1);
        block.setBlock(Blocks.switchBlock, Team.sharded);
        assertEquals(block.blockID(), drawOperation.getTile(block, (byte)OpType.block.ordinal()));

        Tile canRotate1 = world.rawTile(5,2);
        canRotate1.setBlock(Blocks.conveyor, Team.sharded);
        assertEquals(0, drawOperation.getTile(canRotate1, (byte)OpType.rotation.ordinal()));

        Tile canRotate2 = world.rawTile(5,3);
        canRotate2.setBlock(Blocks.conveyor, Team.sharded, 1);
        assertEquals(1, drawOperation.getTile(canRotate2, (byte)OpType.rotation.ordinal()));

        Tile overlay = world.rawTile(5,4);
        overlay.setBlock(Blocks.grass, Team.sharded);
        assertEquals(overlay.overlayID(), drawOperation.getTile(overlay, (byte)OpType.overlay.ordinal()));

        Tile team = world.rawTile(5,5);
        team.setBlock(Blocks.coreShard, Team.sharded);
        assertEquals(team.getTeamID(), drawOperation.getTile(team, (byte)OpType.team.ordinal()));

    }
    /**
     * Purpose: check that getTile() throws Exception for illegal type parameter
     * Input: Tile(any Tile), type which is out of bound
     * Expected: throw IllegalArgumentException
     */
    @Test
    void getTileExceptionTest(){
        world.loadMap(testMap);
        state.set(State.playing);

        Tile exceptionTile = world.rawTile(5,0);
        byte outOfBound = (byte)OpType.values().length;
        assertThrows(IllegalArgumentException.class, () -> drawOperation.getTile(exceptionTile, outOfBound));
    }

}