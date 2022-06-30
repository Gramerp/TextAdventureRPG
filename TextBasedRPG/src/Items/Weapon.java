package Items;

public class Weapon extends Item
{
    public int attbonus;

    public static String[] nameList = {"Axe", "Sword","Stave","Dagger"};

    public Weapon()
    {
        attbonus = rnd.nextInt(5)+1;
        this.name = nameList[rnd.nextInt(nameList.length)];
    }

}
