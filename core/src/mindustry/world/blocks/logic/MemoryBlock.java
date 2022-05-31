package mindustry.world.blocks.logic;

import arc.util.io.*;
import mindustry.content.Items;
import mindustry.gen.*;
import mindustry.type.Category;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.with;

public class MemoryBlock extends Block{
    public static final String memoryCell = "memory-cell";
    public static final String memoryBank = "memory-bank";
    public int memoryCapacity = 32;

    public MemoryBlock(String name){
        super(name);
        if(name == memoryCell) {
            requirements(Category.logic, with(Items.graphite, 30, Items.silicon, 30));
            memoryCapacity = 64;
        }
        if(name == memoryBank) {
            requirements(Category.logic, with(Items.graphite, 80, Items.silicon, 80, Items.phaseFabric, 30));
            memoryCapacity = 512;
            size = 2;
        }
        destructible = true;
        solid = true;
        group = BlockGroup.logic;
        drawDisabled = false;
        envEnabled = Env.any;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.memoryCapacity, memoryCapacity, StatUnit.none);
    }

    public class MemoryBuild extends Building{
        public double[] memory = new double[memoryCapacity];

        //massive byte size means picking up causes sync issues
        @Override
        public boolean canPickup(){
            return false;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            write.i(memory.length);
            for(double v : memory){
                write.d(v);
            }
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            int amount = read.i();
            for(int i = 0; i < amount; i++){
                double val = read.d();
                if(i < memory.length) memory[i] = val;
            }
        }
    }
}
