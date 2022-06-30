import Items.Armor;
import Items.Item;
import Items.Weapon;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.Iterator;

public class Room {
    Random rnd = new Random();
    ArrayList<String> desc;
    ArrayList<Item> loot;
    ArrayList<Creature> enemies;

    public Room()
    {
        desc = new ArrayList<>();
        loot = new ArrayList<>();
        enemies = new ArrayList<>();
        desc.add("You are here");
        for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Weapon w = new Weapon();
                loot.add(w);
                String wstring = "There is a "+w.name+" here.";
                desc.add(wstring);
            }
            for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Armor r = new Armor();
                loot.add(r);
                String rstring = "There is "+r.name+" here.";
                desc.add(rstring);
            }
            for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Creature c = new Creature(rnd.nextInt(TA.p1.level));
                enemies.add(c);
                desc.add(c.cstring);
            }
    }

}
