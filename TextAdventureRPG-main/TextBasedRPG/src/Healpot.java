import java.util.Random;

public class Healpot extends Consumable {
    int healbonus;
    public static String[] nameList = {"Life Elixir", "Ointment", "Medicine"};
    Random rnd = new Random(System.currentTimeMillis());

    public Healpot()
    {
        this.healbonus = rnd.nextInt(5)+1;
        this.name = nameList[rnd.nextInt(nameList.length)]+" "+this.healbonus;
        this.iString = "There is a "+this.name+" here.";
    }
}
