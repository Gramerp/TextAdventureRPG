import java.util.Random;

public class Armor extends Item {

    public int defbonus;

    public static String[] nameList = {"Leather Armor", "Plate Mail", "Chain Mail"};
    Random rnd = new Random(System.currentTimeMillis()+Room.clocker);
    public Armor()
    {
        this.defbonus = rnd.nextInt(3)+1;
        this.name = nameList[rnd.nextInt(nameList.length)]+" "+this.defbonus;
        this.iString = "There is "+this.name+" here.";
    }

}
