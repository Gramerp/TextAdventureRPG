import java.util.Random;

public class Dungeon {
    public int x;
    public int y;
    Room[][] makeUp;
    String entryDirection;
    Random rnd = new Random(System.currentTimeMillis());
    String[] directions = {"S", "W"};

    public void reset()
    {
        x = rnd.nextInt(20)+1;
        y = rnd.nextInt(20)+1;
        this.entryDirection = directions[rnd.nextInt(directions.length)];
        this.makeUp = new Room[x+1][y+1];
        for (int i = 0; i < x+1; i++) {
            for(int j = 0; j < y+1; j++){
                this.makeUp[i][j] = new Room();
            }
        }
        TA.zLevel++;
    }

    public Dungeon()
    {
        x = rnd.nextInt(20)+1;
        y = rnd.nextInt(20)+1;
        this.entryDirection = directions[rnd.nextInt(directions.length)];
        this.makeUp = new Room[x+1][y+1];
        for (int i = 0; i < x+1; i++) {
            for(int j = 0; j < y+1; j++){
                this.makeUp[i][j] = new Room();
            }
        }
    }

}
