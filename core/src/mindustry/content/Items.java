package mindustry.content;

import arc.graphics.*;
import mindustry.ctype.*;
import mindustry.type.*;

public class Items implements ContentList{
    public static Item
    scrap, copper, lead, graphite, coal, titanium, thorium, silicon, plastanium,
    phaseFabric, surgeAlloy, sporePod, sand, blastCompound, pyratite, metaglass;

    @Override
    public void load(){

        copper = new Item.ItemBuilder("copper", Color.valueOf("d99d73"))
                .hardness(1)
                .cost(0.5f)
                .alwaysUnlocked(true)
                .build();

        lead = new Item.ItemBuilder("lead", Color.valueOf("8c7fa9"))
                .hardness(1)
                .cost(0.7f)
                .alwaysUnlocked(true)
                .build();

        metaglass = new Item.ItemBuilder("metaglass", Color.valueOf("ebeef5"))
                .cost(1.5f)
                .build();

        graphite = new Item.ItemBuilder("graphite", Color.valueOf("b2c6d2"))
                .cost(1f)
                .build();

        sand = new Item.ItemBuilder("sand", Color.valueOf("f7cba4"))
                .lowPriority(true)
                .alwaysUnlocked(true)
                .build();

        coal = new Item.ItemBuilder("coal", Color.valueOf("272727"))
                .explosiveness(0.2f)
                .flammability(1f)
                .hardness(2)
                .build();

        titanium = new Item.ItemBuilder("titanium", Color.valueOf("8da1e3"))
                .hardness(3)
                .cost(1f)
                .build();

        thorium = new Item.ItemBuilder("thorium", Color.valueOf("f9a3c7"))
                .hardness(4)
                .cost(1.1f)
                .explosiveness(0.2f)
                .radioactivity(1f)
                .build();

        scrap = new Item.ItemBuilder("scrap", Color.valueOf("777777"))
                .build();

        silicon = new Item.ItemBuilder("silicon", Color.valueOf("53565c"))
                .cost(0.8f)
                .build();

        plastanium = new Item.ItemBuilder("plastanium", Color.valueOf("cbd97f"))
                .flammability(0.1f)
                .explosiveness(0.2f)
                .cost(1.3f)
                .build();

        phaseFabric = new Item.ItemBuilder("phase-fabric", Color.valueOf("f4ba6e"))
                .cost(1.3f)
                .radioactivity(0.6f)
                .build();

        surgeAlloy = new Item.ItemBuilder("surge-alloy", Color.valueOf("f3e979"))
                .cost(1.2f)
                .charge(0.75f)
                .build();

        sporePod = new Item.ItemBuilder("spore-pod", Color.valueOf("7457ce"))
                .flammability(1.15f)
                .build();

        blastCompound = new Item.ItemBuilder("blast-compound", Color.valueOf("ff795e"))
                .flammability(0.4f)
                .explosiveness(1.2f)
                .build();

        pyratite = new Item.ItemBuilder("pyratite", Color.valueOf("ffaa5f"))
                .flammability(1.4f)
                .explosiveness(0.4f)
                .build();
    }
}
