import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TA {
    public static Creature p1 = new Creature(1);
    static Random rnd = new Random(System.currentTimeMillis());
    static int row;
    static int col;
    static int zLevel = 0;
    static Dungeon d = new Dungeon();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        type("Input player name");
        p1.name = sc.next();

        type("You, "+ p1.name + " are on a journey to retrieve the Amulet of Yendor");

        type("Your stats are:\nHP: "+p1.health+"\nATT: "+p1.att+"\nDEF: "+p1.def+"\nMAG: "+p1.magic);

        type("You enter the dungeon from the " + d.entryDirection + "...\nbut you are magically teleported deeper in the dungeon.");
        row = rnd.nextInt(d.y)+1;
        col = rnd.nextInt(d.x)+1;
        while (zLevel < 6)
        {
            dungeonLoop();
        }
        finale();
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
        Room cr = d.makeUp[col][row];
        for (int i = 0; i < cr.desc.size(); i++) {
            System.out.println(cr.desc.get(i));
            Thread.sleep(50);
        }
        System.out.println("What do you want to do?\nM: Move\nF: Fight\nL: Loot\nE: Equip/Unequip/Use an item\nD: Drop an item.");
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
                    if (col >= d.x)
                    {
                        type("You fall into a new portion of the dungeon...");
                        d.reset();
                        row = rnd.nextInt(d.y)+1;
                        col = rnd.nextInt(d.x)+1;
                        break;
                    }
                    else
                    {
                        row = d.y;
                        type("There is a wall that way");
                    }

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
                    if ( row >= d.y)
                    {
                        d.reset();
                        col = rnd.nextInt(d.x)+1;
                        row = rnd.nextInt(d.y)+1;
                        break;
                    }
                    else
                    {
                        col = d.x;
                        type("There is a wall that way");
                    }
                }
                break;
            case "F":
                if (cr.enemies.size() == 0)
                {
                    type("There is no one there...");
                    break;
                }
                type("Who do you want to fight?");
                for (int i = 0; i < cr.enemies.size(); i++) {
                    System.out.println(i+": level "+d.makeUp[col][row].enemies.get(i).level+" "+ d.makeUp[col][row].enemies.get(i).name);
                }
                int fight = sc.nextInt();
                type("You fight the level "+cr.enemies.get(fight).level+" "+cr.enemies.get(fight).name);
                while (p1.health > 0 && cr.enemies.get(fight).health > 0)
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
                if (cr.enemies.get(fight).health <= 0);
                {
                    type("You defeated your foe!");
                    p1.xp += cr.enemies.get(fight).level*5;
                    type("You gained "+cr.enemies.get(fight).level*5+" xp.");
                    p1.levelup();
                    for (int i = 0; i < cr.enemies.get(fight).inventory.size(); i++)
                    {
                        cr.enemies.get(fight).inventory.get(i).equipped = false;
                        p1.inventory.add(cr.enemies.get(fight).inventory.get(i));
                        type("You got a "+cr.enemies.get(fight).inventory.get(i).name+".");
                        cr.enemies.get(fight).inventory.remove(cr.enemies.get(fight).inventory.get(i));
                    }
                    cr.desc.remove(cr.enemies.get(fight).cstring);
                    cr.enemies.remove(cr.enemies.get(fight));
                    break;
                }
            case "L":
                if (cr.loot.size() == 0)
                {
                    type("There is nothing to grab");
                    break;
                }
                type("What do you want to pick up?");
                for (int i = 0; i < cr.loot.size(); i++) {
                    System.out.println(i+": "+d.makeUp[col][row].loot.get(i).name);
                }
                int grab = sc.nextInt();
                type("You grab the "+ cr.loot.get(grab).name+".");
                p1.get(cr.loot.get(grab));
                cr.desc.remove(cr.loot.get(grab).iString);
                cr.loot.remove(grab);
                break;

            case "E":
                if (p1.inventory.size() <= 0)
                {
                    type("You have nothing.");
                    break;
                }
                type("What do you want to equip unequip or use?");
                for (int i = 0; i < p1.inventory.size(); i++)
                {
                    System.out.print(i+": "+p1.inventory.get(i).name);
                    if (p1.inventory.get(i).equipped == true)
                    {
                        System.out.print(" (Equipped)");
                    }
                    System.out.print("\n");
                }
                int itemchoice = sc.nextInt();
                if (p1.inventory.get(itemchoice).equipped)
                {
                    type("You unequip the "+p1.inventory.get(itemchoice).name+".");
                    if (p1.inventory.get(itemchoice) instanceof Weapon)
                    {
                        p1.att -= ((Weapon) p1.inventory.get(itemchoice)).attbonus;
                        p1.wequipped = false;
                    }
                    else if (p1.inventory.get(itemchoice) instanceof Armor)
                    {
                        p1.def -= ((Armor) p1.inventory.get(itemchoice)).defbonus;
                        p1.requipped = false;
                    }
                    p1.inventory.get(itemchoice).equipped = false;
                    break;
                }
                else
                {
                    if (p1.inventory.get(itemchoice) instanceof Weapon)
                    {
                        if (p1.wequipped)
                        {
                            type("Unequip that weapon first.");
                            break;
                        }
                        else
                        {
                            type("You equipped the "+p1.inventory.get(itemchoice).name);
                            p1.att += (((Weapon) p1.inventory.get(itemchoice)).attbonus);
                            p1.inventory.get(itemchoice).equipped = true;
                            p1.wequipped = true;
                            break;
                        }
                    }
                    else if (p1.inventory.get(itemchoice) instanceof Armor)
                    {
                        if (p1.requipped)
                        {
                            type("Take off that armor first.");
                            break;
                        }
                        else
                        {
                            type("You equipped the "+p1.inventory.get(itemchoice).name);
                            p1.def += ((Armor) p1.inventory.get(itemchoice)).defbonus;
                            p1.inventory.get(itemchoice).equipped = true;
                            p1.requipped = true;
                            break;
                        }
                    }
                    else if (p1.inventory.get(itemchoice) instanceof Consumable)
                    {
                        if (p1.inventory.get(itemchoice) instanceof Healpot)
                        {
                            p1.health += ((Healpot) p1.inventory.get(itemchoice)).healbonus;
                            type("You are healed by "+ ((Healpot) p1.inventory.get(itemchoice)).healbonus);
                        }
                        p1.inventory.remove(p1.inventory.get(itemchoice));
                        break;
                    }
                }
                break;
            case "D":
                if (p1.inventory.size() <= 0)
                {
                    type("You have nothing...");
                    break;
                }
                type("What do you want to drop?");
                for (int i = 0; i < p1.inventory.size(); i++)
                {
                    System.out.println(i+": "+p1.inventory.get(i).name);
                    if (p1.inventory.get(i).equipped)
                    {
                        System.out.println(" (Equipped)");
                    }
                }
                int dropchoice = sc.nextInt();
                if (p1.inventory.get(dropchoice).equipped)
                {
                    p1.inventory.get(dropchoice).equipped = false;
                    if (p1.inventory.get(dropchoice) instanceof Weapon)
                    {
                        p1.wequipped = false;
                    }
                    else if (p1.inventory.get(dropchoice) instanceof Armor)
                    {
                        p1.requipped = false;
                    }
                }
                type("You dropped the "+p1.inventory.get(dropchoice).name+".");
                cr.loot.add(p1.inventory.get(dropchoice));
                cr.desc.add(p1.inventory.get(dropchoice).iString);
                p1.inventory.remove(p1.inventory.get(dropchoice));
                break;
        }

    }

    public static void finale() throws InterruptedException {
        Room finalroom = new Room();
        Item yendorAmulet = new Item();
        yendorAmulet.iString = "The Amulet of Yendor is here! GET IT.";
        yendorAmulet.name = "Amulet of Yendor";
        finalroom.desc.add(yendorAmulet.iString);
        finalroom.loot.add(yendorAmulet);
        finalroom.enemies.removeAll(finalroom.enemies);
        type("Well. This is it. You made it to the amulet...");
        Thread.sleep(500);
        type("Do what you must now... but don't take the amulet until you are ready...");
        boolean ready = false;
        while (!ready)
        {

            System.out.println("F: Fight\nL: Loot\nE: Equip/Unequip/Use an item\nD: Drop an item.");
            String choice = sc.next();
            switch (choice)
            {
                case "F":
                    if (finalroom.enemies.size() == 0)
                    {
                        type("There is no one there...");
                        break;
                    }
                    type("Who do you want to fight?");
                    for (int i = 0; i < d.makeUp[col][row].enemies.size(); i++) {
                        System.out.println(i+": level "+d.makeUp[col][row].enemies.get(i).level+" "+ d.makeUp[col][row].enemies.get(i).name);
                    }
                    int fight = sc.nextInt();
                    type("You fight the level "+finalroom.enemies.get(fight).level+" "+finalroom.enemies.get(fight).name);
                    while (p1.health > 0 && finalroom.enemies.get(fight).health > 0)
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
                    if (finalroom.enemies.get(fight).health <= 0);
                {
                    type("You defeated your foe!");
                    p1.xp += d.makeUp[col][row].enemies.get(fight).level*5;
                    type("You gained "+d.makeUp[col][row].enemies.get(fight).level*5+" xp.");
                    p1.levelup();
                    d.makeUp[col][row].desc.remove(d.makeUp[col][row].enemies.get(fight).cstring);
                    d.makeUp[col][row].enemies.remove(d.makeUp[col][row].enemies.get(fight));
                    break;
                }
                case "L":
                    if (finalroom.loot.size() == 0)
                    {
                        type("There is nothing to grab");
                        break;
                    }
                    type("What do you want to pick up?");
                    for (int i = 0; i < finalroom.loot.size(); i++) {
                        System.out.println(i+": "+finalroom.loot.get(i).name);
                    }
                    int grab = sc.nextInt();
                    if (finalroom.loot.get(grab) == yendorAmulet)
                    {
                        type("You took the Amulet of Yendor...\nA great foe awaits you now...");
                        ready = true;
                        break;
                    }
                    type("You grab the "+ finalroom.loot.get(grab).name+".");
                    p1.get(finalroom.loot.get(grab));
                    finalroom.desc.remove(finalroom.loot.get(grab).iString);
                    finalroom.loot.remove(grab);
                    break;

                case "E":
                    if (p1.inventory.size() <= 0)
                    {
                        type("You have nothing.");
                        break;
                    }
                    type("What do you want to equip unequip or use?");
                    for (int i = 0; i < p1.inventory.size(); i++)
                    {
                        System.out.print(i+": "+p1.inventory.get(i).name);
                        if (p1.inventory.get(i).equipped == true)
                        {
                            System.out.print(" (Equipped)");
                        }
                        System.out.print("\n");
                    }
                    int itemchoice = sc.nextInt();
                    if (p1.inventory.get(itemchoice).equipped)
                    {
                        type("You unequip the "+p1.inventory.get(itemchoice).name+".");
                        if (p1.inventory.get(itemchoice) instanceof Weapon)
                        {
                            p1.att -= ((Weapon) p1.inventory.get(itemchoice)).attbonus;
                            p1.wequipped = false;
                        }
                        else if (p1.inventory.get(itemchoice) instanceof Armor)
                        {
                            p1.def -= ((Armor) p1.inventory.get(itemchoice)).defbonus;
                            p1.requipped = false;
                        }
                        p1.inventory.get(itemchoice).equipped = false;
                        break;
                    }
                    else
                    {
                        if (p1.inventory.get(itemchoice) instanceof Weapon)
                        {
                            if (p1.wequipped)
                            {
                                type("Unequip that weapon first.");
                                break;
                            }
                            else
                            {
                                type("You equipped the "+p1.inventory.get(itemchoice).name);
                                p1.att += (((Weapon) p1.inventory.get(itemchoice)).attbonus);
                                p1.inventory.get(itemchoice).equipped = true;
                                p1.wequipped = true;
                                break;
                            }
                        }
                        else if (p1.inventory.get(itemchoice) instanceof Armor)
                        {
                            if (p1.requipped)
                            {
                                type("Take off that armor first.");
                                break;
                            }
                            else
                            {
                                type("You equipped the "+p1.inventory.get(itemchoice).name);
                                p1.def += ((Armor) p1.inventory.get(itemchoice)).defbonus;
                                p1.inventory.get(itemchoice).equipped = true;
                                p1.requipped = true;
                                break;
                            }
                        }
                    }
                    break;
                case "D":
                    if (p1.inventory.size() <= 0)
                    {
                        type("You have nothing...");
                        break;
                    }
                    type("What do you want to drop?");
                    for (int i = 0; i < p1.inventory.size(); i++)
                    {
                        System.out.println(i+": "+p1.inventory.get(i).name);
                        if (p1.inventory.get(i).equipped)
                        {
                            System.out.println(" (Equipped)");
                        }
                    }
                    int dropchoice = sc.nextInt();
                    if (p1.inventory.get(dropchoice).equipped)
                    {
                        p1.inventory.get(dropchoice).equipped = false;
                        if (p1.inventory.get(dropchoice) instanceof Weapon)
                        {
                            p1.wequipped = false;
                        }
                        else if (p1.inventory.get(dropchoice) instanceof Armor)
                        {
                            p1.requipped = false;
                        }
                    }
                    type("You dropped the "+p1.inventory.get(dropchoice).name+".");
                    finalroom.loot.add(p1.inventory.get(dropchoice));
                    finalroom.desc.add(p1.inventory.get(dropchoice).iString);
                    p1.inventory.remove(p1.inventory.get(dropchoice));
                    break;
            }
        }
    }

}
