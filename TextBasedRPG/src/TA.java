import Items.Armor;
import Items.Weapon;

import java.util.Random;
import java.util.Scanner;

public class TA {
    public static Creature p1 = new Creature(5);
    static Random rnd = new Random();
    static int row;
    static int col;
    static Dungeon d = new Dungeon();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        type("Input player name");
        p1.name = sc.next();

        type("You, "+ p1.name + " are on a journey to retrieve the Amulet of Yendor");

        //intro();
        type("You enter the dungeon from the " + d.entryDirection + "...\nbut you are magically teleported deeper in the dungeon.");
        row = rnd.nextInt(d.y)+1;
        col = rnd.nextInt(d.x)+1;
        while (true)
        {
            dungeonLoop();
        }
    }
    public static void type(String s) throws InterruptedException {
        for (int i = 0; i < s.length(); i++)
        {
            System.out.print(s.charAt(i));
            Thread.sleep(30);
        }
        System.out.print("\n");
    }
    public static void intro() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < Math.random()*10; i++)
        {
            int x = rnd.nextInt(5)+1;
            switch (x)
            {
                case 1:
                case 2:
                case 3:
                    Creature ent = new Creature();
                    ent.health = rnd.nextInt(10)+1;
                    ent.att = rnd.nextInt(10)+1;
                    ent.def = rnd.nextInt(2)+1;
                    type("A ghostly enemy attacks! Use magic to weaken it before attacking it.");
                    while (p1.health > 0 && ent.health > 0)
                    {
                        type(p1.name+"'s health: "+ p1.health);
                        type("Enemy health: "+ent.health);
                        ent.attack(p1);
                        type("Attack or Weaken?");
                        switch (sc.next())
                        {
                            case "Attack":
                                p1.attack(ent);
                                break;
                            case "Weaken":
                                p1.weaken(ent);
                                break;
                            default:
                                type("Error");
                                break;
                        }
                    }
                    if (p1.health>ent.health)
                    {
                        type("You live.");
                    }
                    else
                    {
                        type("Death");
                        System.exit(7437);
                    }
                    break;
                case 4:
                    type("You come across a chest");
                    Weapon w = new Weapon();
                    type("You found a " +w.name);
                    p1.inventory.add(w);
                    p1.att += w.attbonus;
                    break;
                case 5:
                    type("You found a stand holding some armor.");
                    Armor r = new Armor();
                    type("You found "+r.name);
                    p1.inventory.add(r);
                    p1.def += r.defbonus;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    }

    public static void dungeonLoop() throws InterruptedException
    {
        type("You are in room "+col+", "+row);
        for (int i = 0; i < d.makeUp[col][row].desc.size(); i++) {
            System.out.println(d.makeUp[col][row].desc.get(i));
            Thread.sleep(50);
        }
        System.out.println("What do you want to do?\nM: Move\nF: Fight\nL: Loot");
        String choice = sc.next();
        switch (choice)
        {
            case "M":
                System.out.println("Which way do you want to go?\nN: North\nE: East\nS: South\nW: West");
                String dir = sc.next();
                switch (dir)
                {
                    case "N":
                        row++;
                        break;
                    case "E":
                        col++;
                        break;
                    case "S":
                        row--;
                        break;
                    case "W":
                        col--;
                        break;
                }
                if (row < 0) {
                    if (col <= 0)
                    {
                        type("You leave the dungeon the way you came in. You have abandoned your quest.");
                        System.exit(1053);
                    }
                    else
                    {
                        row = 0; type("There is a wall that way");
                    }
                }
                else if (row > d.y) {
                    row = d.y;
                    type("There is a wall that way");
                }
                if (col < 0) {
                    if (row <= 0)
                    {
                        type("You leave the dungeon the way you came in. You have abandoned your quest.");
                        System.exit(1053);
                    }
                    else {
                        col = 0;
                    } type("There is a wall that way");
                }
                else if (col > d.x) {
                    col = d.x;
                    type("There is a wall that way");
                }
                break;
            case "F":
                System.out.println("Who do you want to fight?");
                for (int i = 0; i < d.makeUp[col][row].enemies.size(); i++) {
                    System.out.println(i+": level "+d.makeUp[col][row].enemies.get(i).level+" "+ d.makeUp[col][row].enemies.get(i).name);
                }
                int fight = sc.nextInt();
                type("You fight the level "+d.makeUp[col][row].enemies.get(fight).level+" "+d.makeUp[col][row].enemies.get(fight).name);
                while (p1.health > 0 && d.makeUp[col][row].enemies.get(fight).health > 0)
                {
                    boolean run = false;
                    boolean defend = false;
                    boolean erun = false;
                    boolean edefend = false;
                    if (defend == true)
                    {
                        p1.def -= p1.level;
                    }
                    if(run == true){
                        type("You ran away");
                        break;
                    }
                    System.out.println(p1.name+"'s health: "+p1.health+"\n"+d.makeUp[col][row].enemies.get(fight).name+"'s health: "+d.makeUp[col][row].enemies.get(fight).health);
                    System.out.println("What do you want to do?\nA: Attack\nB: Magical attack\nC: Defend\nD: Run");
                    String combatchoice = sc.next();
                    switch (combatchoice)
                    {
                        case "A":
                            p1.attack(d.makeUp[col][row].enemies.get(fight));
                            break;
                        case "B":
                            p1.weaken(d.makeUp[col][row].enemies.get(fight));
                            break;
                        case "C":
                            p1.def+=p1.level;
                            type("The "+d.makeUp[col][row].enemies.get(fight).name+" takes a defensive stance!");
                            defend = true;
                            break;
                        case "D":
                            run = true;
                            break;
                    }
                    if (edefend == true)
                    {
                        type("The enemy loses it's defensive stance!");
                        d.makeUp[col][row].enemies.get(fight).def -= d.makeUp[col][row].enemies.get(fight).level;
                    }
                    if(erun == true){
                        type("The enemy ran away to the corner of the room.");
                        break;
                    }
                    int enemychoice = rnd.nextInt(4)+1;
                    switch (enemychoice)
                    {
                        case 1:
                            d.makeUp[col][row].enemies.get(fight).attack(p1);
                            type("You're hurt!");
                            break;
                        case 2:
                            d.makeUp[col][row].enemies.get(fight).weaken(p1);
                            type("You feel weaker");
                            break;
                        case 3:
                            d.makeUp[col][row].enemies.get(fight).def+=d.makeUp[col][row].enemies.get(fight).level;
                            edefend = true;
                            break;
                        case 4:
                            erun = true;
                            break;
                    }

                }
                if (p1.health <= 0)
                {
                    type("You, "+ p1.name+", have died...");
                    System.exit(7437);
                }
                if (d.makeUp[col][row].enemies.get(fight).health <= 0);
                {
                    type("You defeated your foe!");
                    p1.xp += d.makeUp[col][row].enemies.get(fight).level*5;
                    while ((p1.level*5) <= p1.xp) p1.levelup();
                    d.makeUp[col][row].desc.remove(d.makeUp[col][row].enemies.get(fight).cstring);
                    d.makeUp[col][row].enemies.remove(d.makeUp[col][row].enemies.get(fight));
                    break;
                }
        }

    }
}
