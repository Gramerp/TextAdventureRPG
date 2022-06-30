import Items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Creature {
    int health;
    int maxHealth;
    int level;
    float xp;
    int def;
    int att;
    int magic;
    ArrayList<Item> inventory = new ArrayList();
    Random rnd = new Random(10);
    public String name;

    public int damaged(int damage)
    {
        int damageTaken = damage-this.def;
        if (damageTaken < 0)
        {
            damageTaken = 0;
        }
        this.health -= damageTaken;
        return damageTaken;
    }

    public Creature()
    {

    }

    public Creature(int level)
    {
        this();
        this.level = level;
        this.att = level*rnd.nextInt(3)+1;
        this.def = level*rnd.nextInt(3)+1;
        this.maxHealth = level*rnd.nextInt(3);
        this.health = this.maxHealth;
        this.magic = level*rnd.nextInt(3)+1;
    }

    public int attack(Creature target)
    {
        int damage = this.att*rnd.nextInt(3);
        target.damaged(damage);
        int damageDealt = target.damaged(damage);
        return damageDealt;
    }
    public int weaken(Creature target)
    {
        int weakness = this.magic*rnd.nextInt(3);
        this.att -= weakness;
        this.def -= weakness;
        this.magic -= weakness;
        if (this.att < 0)
        {
            this.att = 0;
        }
        if (this.def < 0)
        {
            this.def = 0;
        }
        if (this.magic < 0)
        {
            this.magic = 0;
        }
        return weakness;
    }
}
