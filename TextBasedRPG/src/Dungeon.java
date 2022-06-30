import java.util.Random;

public class Dungeon {
    public int x;
    public int y;
    Room[][] makeUp;
    String entryDirection;
    Random rnd = new Random();
    String[] directions = {"S", "W"};

    public Dungeon()
    {
        x = rnd.nextInt(20)+1;
        y = rnd.nextInt(20)+1;
        this.entryDirection = directions[rnd.nextInt(directions.length)];
        this.makeUp = new Room[x][y];
        for (int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++){
                this.makeUp[i][j] = new Room();
            }
        }
    }

}
