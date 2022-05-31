package mindustry.world.blocks.logic;

import mindustry.world.Block;

public class LogicBlockFactory {
    public static Block generateLogicBlock(String LogicBlockName) {
        Block block;
        switch (LogicBlockName) {
            case MessageBlock.name:
                block = new MessageBlock(LogicBlockName);
                break;
            case SwitchBlock.name:
                block = new SwitchBlock(LogicBlockName);
                break;
            case MemoryBlock.memoryBank:
            case MemoryBlock.memoryCell:
                block = new MemoryBlock(LogicBlockName);
                break;
            default:
                block = null;
        }
        return block;
    }
}
