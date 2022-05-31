package mindustry.type;

import arc.graphics.*;
import arc.struct.*;
import mindustry.ctype.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class Item extends UnlockableContent{
    public Color color;

    /** how explosive this item is. */
    public float explosiveness = 0f;
    /** flammability above 0.3 makes this eligible for item burners. */
    public float flammability = 0f;
    /** how radioactive this item is. */
    public float radioactivity;
    /** how electrically potent this item is. */
    public float charge = 0f;
    /** drill hardness of the item */
    public int hardness = 0;
    /**
     * base material cost of this item, used for calculating place times
     * 1 cost = 1 tick added to build time
     */
    public float cost = 1f;
    /** if true, this item is of lowest priority to drills. */
    public boolean lowPriority;

    public Item(String name, Color color){
        super(name);
        this.color = color;
    }

    public Item(String name){
        this(name, new Color(Color.black));
    }

    @Override
    public void setStats(){
        stats.addPercent(Stat.explosiveness, explosiveness);
        stats.addPercent(Stat.flammability, flammability);
        stats.addPercent(Stat.radioactivity, radioactivity);
        stats.addPercent(Stat.charge, charge);
    }

    @Override
    public String toString(){
        return localizedName;
    }

    @Override
    public ContentType getContentType(){
        return ContentType.item;
    }

    /** Allocates a new array containing all items that generate ores. */
    public static Seq<Item> getAllOres(){
        return content.blocks().select(b -> b instanceof OreBlock).map(b -> b.itemDrop);
    }

    public static class ItemBuilder {
        private Color color;
        private float explosiveness = 0f;
        private float flammability = 0f;
        private float radioactivity;
        private float charge = 0f;
        private int hardness = 0;
        private float cost = 1f;
        private boolean lowPriority;
        private String name;
        private boolean alwaysUnlocked = false;

        public ItemBuilder(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public ItemBuilder hardness(int hardness) {
            this.hardness = hardness;
            return this;
        }

        public ItemBuilder cost(float cost) {
            this.cost = cost;
            return this;
        }

        public ItemBuilder alwaysUnlocked(boolean alwaysUnlocked) {
            this.alwaysUnlocked = alwaysUnlocked;
            return this;
        }

        public ItemBuilder lowPriority(boolean lowPriority) {
            this.lowPriority = lowPriority;
            return this;
        }

        public ItemBuilder flammability(float flammability) {
            this.flammability = flammability;
            return this;
        }

        public ItemBuilder radioactivity(float radioactivity) {
            this.radioactivity = radioactivity;
            return this;
        }

        public ItemBuilder charge(float charge) {
            this.radioactivity = radioactivity;
            return this;
        }

        public ItemBuilder explosiveness(float explosiveness) {
            this.explosiveness = explosiveness;
            return this;
        }

        public Item build() {
            Item item = new Item(this.name, this.color);
            item.explosiveness = this.explosiveness;
            item.flammability = this.flammability;
            item.radioactivity = this.radioactivity;
            item.charge = this.charge;
            item.alwaysUnlocked = this.alwaysUnlocked;
            item.hardness = this.hardness;
            item.cost = this.cost;
            item.lowPriority = this.lowPriority;
            return item;
        }
    }
}
