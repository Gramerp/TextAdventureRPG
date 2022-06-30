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
    String cstring;
    ArrayList<Item> inventory = new ArrayList();
    Random rnd = new Random(10);
    public String name;

    public int damaged(int damage)
    {
        int damageTaken = damage-this.def;
        if (damageTaken < 0)
        {
            damageTaken = 1;
        }
        this.health -= damageTaken;
        return damageTaken;
    }

    public void levelup() throws InterruptedException {
            this.level++;
            this.att += this.level/(this.level/2);
            this.def += this.level/(this.level/2);
            this.maxHealth += this.level/(this.level/2);
            this.health = this.maxHealth;
            this.magic += this.level/(this.level/2);
            TA.type("Welcome to level"+this.level);
    }

    public Creature()
    {

    }

    String[] nameList = {"Goblin", "Bat", "Imp"};

    public Creature(int level)
    {
        this();
        this.name = nameList[rnd.nextInt(nameList.length)];
        this.level = level;
        this.att = level*rnd.nextInt(3)+1;
        this.def = level*rnd.nextInt(3)+1;
        this.maxHealth = level*5;
        this.health = this.maxHealth;
        this.magic = level*rnd.nextInt(3)+1;
        this.cstring = "There is a level "+this.level+" "+this.name+" here!";
    }

    public int attack(Creature target) throws InterruptedException {
        int damage = this.att*rnd.nextInt(3)+1;
        target.damaged(damage);
        int damageDealt = target.damaged(damage);
        TA.type(this.name+" attacks "+target.name+" for "+damage+" damage.");
        return damageDealt;
    }
    public int weaken(Creature target) throws InterruptedException {
        int weakness = this.magic*(int)Math.random()*this.level;
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
        TA.type(this.name + " weakens "+target.name+" by "+weakness);
        return weakness;
    }
}
