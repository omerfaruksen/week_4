import java.util.Random;

public class Snake extends Obstacle{
    public Snake() {
        super("Yılan", 4, 0, 12, 0);
    }
    @Override
    public int getDamage(){
        Random randomDamage= new Random();
        return randomDamage.nextInt(3,7);
    }
}
