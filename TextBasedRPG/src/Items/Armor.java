package Items;

public class Armor extends Item {

    public int defbonus;

    public static String[] nameList = {"Leather Armor", "Plate Mail", "Chain Mail"};

    public Armor()
    {
        defbonus = rnd.nextInt(3)+1;
        this.name = nameList[rnd.nextInt(nameList.length)]+" "+this.defbonus;
        this.iString = "There is "+this.name+" here.";
    }

}
