package mindustry.editor;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static mindustry.Vars.*;

public class MapRenderer implements Disposable{
    private static final int chunkSize = 64;
    private IndexedRenderer chunk;
    private IntSet updates = new IntSet();
    private IntSet delayedUpdates = new IntSet();
    private TextureRegion clearEditor;
    private int width, height;

    public void resize(int width, int height){
        updates.clear();
        delayedUpdates.clear();
        if(chunk != null){
            chunk.dispose();
        }

        final int numRows = (int)Math.ceil((float)height / chunkSize);
        final int numCols = (int)Math.ceil((float)width / chunkSize);
        chunk = new IndexedRenderer(numRows, numCols, chunkSize * chunkSize * 2);

        this.width = width;
        this.height = height;
        updateAll();
    }

    public void draw(float tx, float ty, float tw, float th){
        Draw.flush();
        clearEditor = Core.atlas.find("clear-editor");

        updates.each(i -> render(i % width, i / width));
        updates.clear();

        updates.addAll(delayedUpdates);
        delayedUpdates.clear();

        //????
        if(chunk == null){
            return;
        }

        var texture = Core.atlas.find("clear-editor").texture;

        for(int x = 0; x < chunk.getNumChunkCol(); x++){
            for(int y = 0; y < chunk.getNumChunkRow(); y++){
                chunk.getTransformMatrix(x, y).setToTranslation(tx, ty).scale(tw / (width * tilesize), th / (height * tilesize));
                chunk.setProjectionMatrix(x, y, Draw.proj());
                chunk.render(x, y, texture);
            }
        }
    }

    public void updatePoint(int x, int y){
        updates.add(x + y * width);
    }

    public void updateAll(){
        clearEditor = Core.atlas.find("clear-editor");
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                render(x, y);
            }
        }
    }

    private void render(int wx, int wy){
        int x = wx / chunkSize, y = wy / chunkSize;
        Tile tile = editor.tiles().getn(wx, wy);

        Team team = tile.team();
        Block floor = tile.floor();
        Block wall = tile.block();

        TextureRegion region;

        int idxWall = (wx % chunkSize) + (wy % chunkSize) * chunkSize;
        int idxDecal = (wx % chunkSize) + (wy % chunkSize) * chunkSize + chunkSize * chunkSize;
        boolean center = tile.isCenter();

        if(wall != Blocks.air && wall.synthetic()){
            region = !wall.editorIcon().found() || !center ? clearEditor : wall.editorIcon();

            float width = region.width * Draw.scl, height = region.height * Draw.scl;

            chunk.draw(x, y, idxWall, region,
            wx * tilesize + wall.offset + (tilesize - width) / 2f,
            wy * tilesize + wall.offset + (tilesize - height) / 2f,
            width, height,
            tile.build == null || !wall.rotate ? 0 : tile.build.rotdeg());
        }else{
            region = floor.editorVariantRegions()[Mathf.randomSeed(idxWall, 0, floor.editorVariantRegions().length - 1)];

            chunk.draw(x, y, idxWall, region, wx * tilesize, wy * tilesize, 8, 8);
        }

        float offsetX = -(wall.size / 3) * tilesize, offsetY = -(wall.size / 3) * tilesize;

        if((wall.update || wall.destructible) && center){
            chunk.setColor(team.color);
            region = Core.atlas.find("block-border-editor");
        }else if(!wall.synthetic() && wall != Blocks.air && center){
            region = !wall.editorIcon().found() ?
                clearEditor : wall.variants > 0 ?
                wall.editorVariantRegions()[Mathf.randomSeed(idxWall, 0, wall.editorVariantRegions().length - 1)] :
                wall.editorIcon();

            if(wall == Blocks.cliff){
                chunk.setColor(Tmp.c1.set(floor.mapColor).mul(1.6f));
                region = ((Cliff)Blocks.cliff).editorCliffs[tile.data & 0xff];
            }

            offsetX = tilesize / 2f - region.width / 2f * Draw.scl;
            offsetY = tilesize / 2f - region.height / 2f * Draw.scl;
        }else if(wall == Blocks.air && !tile.overlay().isAir()){
            region = tile.overlay().editorVariantRegions()[Mathf.randomSeed(idxWall, 0, tile.overlay().editorVariantRegions().length - 1)];
        }else{
            region = clearEditor;
        }

        float width = region.width * Draw.scl, height = region.height * Draw.scl;
        if(!wall.synthetic() && wall != Blocks.air && !wall.isMultiblock()){
            offsetX = 0;
            offsetY = 0;
            width = tilesize;
            height = tilesize;
        }

        chunk.draw(x, y, idxDecal, region, wx * tilesize + offsetX, wy * tilesize + offsetY, width, height);
        chunk.setColor(Color.white);
    }

    @Override
    public void dispose(){
        if(chunk != null){
            chunk.dispose();
        }
    }
}
