import java.util.ArrayList;
import java.util.Random;

public class Room {
    public static int clocker = 0;
    ArrayList<String> desc;
    ArrayList<Item> loot;
    ArrayList<Creature> enemies;

    public Room()
    {
        desc = new ArrayList<>();
        loot = new ArrayList<>();
        enemies = new ArrayList<>();
        for (int i = 0; i < Math.random()*((TA.p1.level/2)+1);i++)
            {
                Random rnd = new Random(System.nanoTime()+clocker);
                Weapon w = new Weapon();
                loot.add(w);
                desc.add(w.iString);
                clocker *= System.currentTimeMillis();
            }
            for (int i = 0; i < Math.random()*((TA.p1.level/2)+1);i++)
            {
                Random rnd = new Random(System.nanoTime()+clocker);
                Armor r = new Armor();
                loot.add(r);
                desc.add(r.iString);
                clocker *= System.currentTimeMillis();
            }
            for (int i = 0; i < Math.random()*((TA.p1.level/2)+1);i++)
            {
                Random rnd = new Random(System.nanoTime()+clocker);
                Healpot h = new Healpot();
                loot.add(h);
                desc.add(h.iString);
                clocker *= System.currentTimeMillis();
            }
            for (int i = 0; i < Math.random()*(TA.p1.level);i++)
            {
                Random rnd = new Random(System.nanoTime()+clocker);
                Creature c = new Creature(rnd.nextInt(TA.p1.level)+1);
                enemies.add(c);
                desc.add(c.cstring);
                clocker *= System.currentTimeMillis();
            }
    }

}
