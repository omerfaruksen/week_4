import java.util.Random;

public class Snake extends Obstacle{
    public Snake() {
        super("Yılan", 4, 0, 12, 0);
        Random rnd = new Random();
        int damageRnd= rnd.nextInt(3,6); //yılan sayısı randm belirlendi.
        setDamage(damageRnd);
    }
}
