package mindustry.editor;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;

public class WaveGraph extends Table{
    public Seq<SpawnGroup> groups = new Seq<>();
    public int from = 0, to = 20;
    private int[][] values;
    private OrderedSet<UnitType> used = new OrderedSet<>();
    private Table colors;
    private ObjectSet<UnitType> hidden = new ObjectSet<>();

    private ObjectSet<GraphMode> gModes = new ObjectSet<>();
    private GraphMode cMode;


    public WaveGraph(){

        gModes.add(new Counts("counts"));
        gModes.add(new Totals("totals"));
        gModes.add(new Health("health"));

        cMode = gModes.first();

        background(Tex.pane);
        rect((x, y, width, height) -> {
            Lines.stroke(Scl.scl(3f));

            GlyphLayout lay = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
            Font font = Fonts.outline;

            lay.setText(font, "1");

            int maxY = cMode.getMaxY();

            float offsetX = Scl.scl(lay.width * (maxY + "").length() * 2);
            float offsetY = Scl.scl(22f) + lay.height + Scl.scl(5f);

            float graphX = x + offsetX, graphY = y + offsetY, graphW = width - offsetX, graphH = height - offsetY;
            float spacing = graphW / (values.length - 1);

            cMode.drawLine(graphX, graphY, graphH, spacing);

            //how many numbers can fit here
            float totalMarks = Mathf.clamp(maxY, 1, 10);

            int markSpace = Math.max(1, Mathf.ceil(maxY / totalMarks));

            Draw.color(Color.lightGray);
            Draw.alpha(0.1f);

            for(int i = 0; i < maxY; i += markSpace){
                float cx = graphX;
                float cy = graphY + i * graphH / maxY;

                Lines.line(cx, cy, cx + graphW, cy);

                lay.setText(font, "" + i);

                font.draw("" + i, cx, cy + lay.height / 2f, Align.right);
            }
            Draw.alpha(1f);

            float len = Scl.scl(4f);
            font.setColor(Color.lightGray);

            for(int i = 0; i < values.length; i++){
                float cx = graphX + graphW / (values.length - 1) * i;
                float cy = y + lay.height;

                Lines.line(cx, cy, cx, cy + len);
                if(i == values.length / 2){
                    font.draw("" + (i + from + 1), cx, cy - Scl.scl(2f), Align.center);
                }
            }
            font.setColor(Color.white);

            Pools.free(lay);

            Draw.reset();
        }).pad(4).padBottom(10).grow();

        row();

        table(t -> colors = t).growX();

        row();

        table(t -> {
            t.left();
            ButtonGroup<Button> group = new ButtonGroup<>();

            for(GraphMode mode : gModes){
                t.button("@wavemode." + mode.getName(), Styles.fullTogglet, () -> {
                    cMode = mode;
                }).group(group).height(35f).update(b -> b.setChecked(mode.getName().equals(cMode.getName()))).width(130f);
            }
        }).growX();
    }

    public void rebuild(){
        values = new int[to - from + 1][Vars.content.units().size];
        used.clear();

        for(int i = from; i <= to; i++){
            for(SpawnGroup spawn : groups){
                int spawned = spawn.getSpawned(i);
                values[i - from][spawn.type.id] += spawned;
                if(spawned > 0){
                    used.add(spawn.type);
                }
            }
        }
        gModes.each(GraphMode::setMax);

        ObjectSet<UnitType> usedCopy = new ObjectSet<>(used);

        colors.clear();
        colors.left();
        colors.button("@waves.units.hide", Styles.cleart, () -> {
            if(hidden.size == usedCopy.size){
                hidden.clear();
            }else{
                hidden.addAll(usedCopy);
            }

            used.clear();
            used.addAll(usedCopy);
            for(UnitType o : hidden) used.remove(o);
        }).update(b -> b.setText(hidden.size == usedCopy.size ? "@waves.units.show" : "@waves.units.hide")).height(32f).width(130f);
        colors.pane(t -> {
            t.left();
            for(UnitType type : used){
                t.button(b -> {
                    Color tcolor = color(type).cpy();
                    b.image().size(32f).update(i -> i.setColor(b.isChecked() ? Tmp.c1.set(tcolor).mul(0.5f) : tcolor)).get().act(1);
                    b.image(type.uiIcon).size(32f).padRight(20).update(i -> i.setColor(b.isChecked() ? Color.gray : Color.white)).get().act(1);
                    b.margin(0f);
                }, Styles.fullTogglet, () -> {
                    if(!hidden.add(type)){
                        hidden.remove(type);
                    }

                    used.clear();
                    used.addAll(usedCopy);
                    for(UnitType o : hidden) used.remove(o);
                }).update(b -> b.setChecked(hidden.contains(type)));
            }
        }).scrollY(false);

        for(UnitType type : hidden){
            used.remove(type);
        }
    }

    Color color(UnitType type){
        return Tmp.c1.fromHsv(type.id / (float)Vars.content.units().size * 360f, 0.7f, 1f);
    }

    int nextStep(float value){
        int order = 1;
        while(order < value){
            if(order * 2 > value){
                return order * 2;
            }
            if(order * 5 > value){
                return order * 5;
            }
            if(order * 10 > value){
                return order * 10;
            }
            order *= 10;
        }
        return order;
    }

    public abstract class GraphMode{
        protected float max = 1f;
        private final String name;
        public GraphMode(String name)  { this.name = name; }
        abstract public void drawLine(float graphX, float graphY, float graphH, float spacing);
        public int getMaxY() { return nextStep((int)max); }
        public String getName() { return name; }

        final public void setMax(){
            max = 1f;
            for(int i = from; i <= to; i++){
                max = Math.max(sumSpawnedValue(i), max);
            }
        }
        abstract protected float sumSpawnedValue(int index);
    }

    public class Counts extends GraphMode{
        public Counts(String name){
            super(name);
        }
        @Override
        public void drawLine(float graphX, float graphY, float graphH, float spacing){
            int maxY = getMaxY();

            for(UnitType type : used.orderedItems()){
                Lines.beginLine();

                Draw.color(color(type));
                Draw.alpha(parentAlpha);

                for(int i = 0; i < values.length; i++){
                    float cx = graphX + i * spacing;
                    float cy = graphY + values[i][type.id] * graphH / maxY;
                    Lines.linePoint(cx, cy);
                }

                Lines.endLine();
            }
        }
        @Override
        protected float sumSpawnedValue(int index){
            for(SpawnGroup spawn : groups){
                max = Math.max((int)max, values[index - from][spawn.type.id]);
            }
            return max;
        }
    }
    public class Totals extends GraphMode{
        public Totals(String name){
            super(name);
        }
        @Override
        public void drawLine(float graphX, float graphY, float graphH, float spacing){
            int maxY = getMaxY();
            Lines.beginLine();

            Draw.color(Pal.accent);
            for(int i = 0; i < values.length; i++){
                int sum = 0;
                for(UnitType type : used.orderedItems()){
                    sum += values[i][type.id];
                }

                float cx = graphX + i * spacing;
                float cy = graphY + sum * graphH / maxY;
                Lines.linePoint(cx, cy);
            }

            Lines.endLine();
        }

        @Override
        public float sumSpawnedValue(int index){
            int sum = 0;
            for(SpawnGroup spawn : groups){
                sum += spawn.getSpawned(index);
            }
            return (float)sum;
        }
    }

    public class Health extends GraphMode{
        public Health(String name){
            super(name);
        }
        @Override
        public void drawLine(float graphX, float graphY, float graphH, float spacing){
            int maxY = getMaxY();
            Lines.beginLine();

            Draw.color(Pal.health);
            for(int i = 0; i < values.length; i++){
                float sum = 0;
                for(UnitType type : used.orderedItems()){
                    sum += (type.health) * values[i][type.id];
                }

                float cx = graphX + i * spacing, cy = graphY + sum * graphH / maxY;
                Lines.linePoint(cx, cy);
            }

            Lines.endLine();
        }

        @Override
        public float sumSpawnedValue(int index){
            float healthSum = 0f;
            for(SpawnGroup spawn : groups){
                healthSum += spawn.getSpawned(index) * (spawn.type.health);
            }
            return healthSum;
        }
    }
}
