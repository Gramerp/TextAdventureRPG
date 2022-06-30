import Items.Armor;
import Items.Weapon;

import java.util.Random;
import java.util.Scanner;

public class TA {
    public static Creature p1 = new Creature();
    static Random rnd = new Random();
    static int row;
    static int col;
    static Dungeon d = new Dungeon();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        type("Input player name");
        p1.name = sc.next();
        p1.health = 50;
        p1.att = rnd.nextInt(18);
        p1.def = rnd.nextInt(2);
        p1.magic = rnd.nextInt(2);
        p1.level = 1;

        type("You, "+ p1.name + " are on a journey to retrieve the Amulet of Yendor");

        intro();
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
        for (String str:
             d.makeUp[col][row].desc) {
            type(str);
        }
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
    }
}
