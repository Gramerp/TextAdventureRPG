public class Weapon extends Item
{
    public int attbonus;

    public static String[] nameList = {"Axe", "Sword","Stave","Dagger"};

    public Weapon()
    {
        this.attbonus = rnd.nextInt(5)+1;
        this.name = nameList[rnd.nextInt(nameList.length)]+" "+this.attbonus;
        this.iString = "There is a "+this.name+" here.";
    }

}
