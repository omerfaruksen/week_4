import java.util.Random;

public class Snake extends Obstacle{
    public Snake() {
        super("Yılan", 4, 0, 12, 0);
        Random rnd = new Random();
        int randomDamage= rnd.nextInt(3,6); //Yılanın hasarının random belirlendiği nokta
        setDamage(randomDamage);
    }
}
