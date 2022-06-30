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
        for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Weapon w = new Weapon();
                loot.add(w);
                desc.add(w.iString);
            }
            for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Armor r = new Armor();
                loot.add(r);
                desc.add(r.iString);
            }
            for (int i = 0; i < Math.random()*TA.p1.level;i++)
            {
                Creature c = new Creature(rnd.nextInt(TA.p1.level)+1);
                enemies.add(c);
                desc.add(c.cstring);
            }
    }

}
