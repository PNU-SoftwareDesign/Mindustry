package mindustry.world.modules;

import arc.util.io.*;
import mindustry.gen.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

public class ConsumeModule extends BlockModule{
    private boolean valid, optionalValid;
    private final Building entity;

    public ConsumeModule(Building entity){
        this.entity = entity;
    }

    public BlockStatus status(){
        if(entity.enabledControlTime > 0 && !entity.enabled){
            return BlockStatus.logicDisable;
        }

        if(!entity.shouldConsume()){
            return BlockStatus.noOutput;
        }

        if(!valid || !entity.productionValid()){
            return BlockStatus.noInput;
        }

        return BlockStatus.active;
    }

    public void update(){
        valid = optionalValid = true;
        //everything is valid when cheating
        if(entity.cheating())   return;

        boolean prevValid = valid();
        boolean doConsume = entity.shouldConsume() && entity.productionValid();

        for(Consume cons : entity.block.consumes.all()){
            if(cons.isOptional()) continue;

            if(doConsume && cons.isUpdate() && prevValid && cons.valid(entity)){
                cons.update(entity);
            }

            valid &= cons.valid(entity);
        }

        for(Consume cons : entity.block.consumes.optionals()){
            if(doConsume && cons.isUpdate() && prevValid && cons.valid(entity)){
                cons.update(entity);
            }

            optionalValid &= cons.valid(entity);
        }
    }

    public void trigger(){
        for(Consume cons : entity.block.consumes.all()){
            cons.trigger(entity);
        }
    }

    public boolean valid(){
        return valid && entity.shouldConsume() && entity.enabled;
    }

    public boolean canConsume(){
        return valid && entity.enabled;
    }

    public boolean optionalValid(){
        return valid() && optionalValid && entity.enabled;
    }

    @Override
    public void write(Writes write){
        write.bool(valid);
    }

    @Override
    public void read(Reads read){
        valid = read.bool();
    }
}
