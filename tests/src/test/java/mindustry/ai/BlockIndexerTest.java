package mindustry.ai;

import arc.*;
import arc.backend.headless.*;
import arc.files.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.core.GameState.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.maps.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.net.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import org.junit.jupiter.api.*;

import static mindustry.Vars.*;
import static org.junit.jupiter.api.Assertions.*;

class BlockIndexerTest{
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
                        BlockIndexerTest.testDataFolder.deleteDirectory();
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

        world.loadMap(testMap);
        state.set(State.playing);
    }

    /**
     * Purpose: check adding a tile in BlockIndex properly
     * Input:
     * 1. Tile(oreCopper)
     * 2. Tile(air)
     * Expected:
     * 1. does indexer have copper
     * 2. does indexer have block added
     */
    @Test
    void addIndexTest(){
        Tile ore = world.rawTile(5,0);
        ore.setBlock(Blocks.oreCopper, Team.sharded);
        ore.setOverlayID(Blocks.oreCopper.id);
        indexer.addIndex(ore);

        assertTrue(indexer.hasOre(Items.copper));

        Tile air = world.rawTile(5,1);
        air.setBlock(Blocks.air, Team.sharded);
        air.setOverlayID(Blocks.oreCoal.id);

        indexer.addIndex(air);

        assertTrue(indexer.isBlockPresent(air.block()));
    }

    /**
     * Purpose: check whether BlockIndexer.removeIndex() removes Tile properly
     * Input:
     * 1. Tile(core)
     * 2. Tile(battery, damaged)
     * Expected:
     * 1. Total unitCap of the team should be subtracted
     * 2. battery should be not damaged because it removed.
     */
    @Test
    void removeIndexTest(){
        Tile core = world.rawTile(5,1);
        core.setBlock(Blocks.coreShard, Team.sharded);
        core.setOverlay(Blocks.air);
        state.teams.updateTeamStats();

        indexer.addIndex(core);
        int expectedUnitCap = core.team().data().unitCap;
        indexer.removeIndex(core);
        assertEquals(expectedUnitCap - core.block().unitCapModifier, core.team().data().unitCap);

        Tile damagedBattery = world.rawTile(20,20);
        damagedBattery.setBlock(Blocks.battery, Team.blue);
        damagedBattery.setOverlay(Blocks.air);

        damagedBattery.build.damage(10f);
        assertTrue(damagedBattery.build.wasDamaged);
        indexer.removeIndex(damagedBattery);
        assertFalse(damagedBattery.build.wasDamaged);
    }

    /**
     * Purpose: check whether getEnemy() get enemy Tiles properly
     * Input:
     * 1. Tile(battery), doesn't update team stats
     * 2. Tile(battery), team stats updated
     * Expected:
     * 1. return of getEnemy() is not Null
     * and first item of the return seq is to be battery.
     * 2. same as Expected 1
     */
    @Test
    void getEnemyTest(){
        Tile enemyBattery = world.rawTile(20,20);
        enemyBattery.setBlock(Blocks.battery, Team.blue);
        enemyBattery.setOverlay(Blocks.air);

        Seq<Tile> seq = indexer.getEnemy(Team.sharded, BlockFlag.battery);
        assertNotNull(seq);
        Tile enemy = seq.get(0);
        assertNotNull(enemy);
        assertEquals(Blocks.battery.id, enemy.blockID());

        state.teams.updateTeamStats();
        Seq<Tile> seq2 = indexer.getEnemy(Team.sharded, BlockFlag.battery);
        assertNotNull(seq2);
        Tile enemy2 = seq.get(0);
        assertNotNull(enemy2);
        assertEquals(Blocks.battery.id, enemy2.blockID());
    }
}