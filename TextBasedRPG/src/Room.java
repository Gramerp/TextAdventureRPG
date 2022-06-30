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

        if (rnd.nextInt(5)>2)
        {
            for (int i = 0; i < rnd.nextInt(TA.p1.level+1);i++)
            {
                Weapon w = new Weapon();
                loot.add(w);
                desc.add("There is a "+w.name+" here.");
            }
            for (int i = 0; i < rnd.nextInt(TA.p1.level+1);i++)
            {
                Armor r = new Armor();
                loot.add(r);
                desc.add("There is "+r.name+" here.");
            }
        }
        else
        {
            for (int i = 0; i < rnd.nextInt(TA.p1.level+1);i++)
            {
                Creature c = new Creature(rnd.nextInt(TA.p1.level+1));
                enemies.add(c);
                desc.add("There is a "+c.name+" here!");
            }
        }
    }

}
