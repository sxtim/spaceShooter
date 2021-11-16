import java.awt.*;
import java.util.ArrayList;

public class ExplosionBigSize {
    //Fields
    private ArrayList<Image> explosionsBigSize;
    private double x;
    private double y;
    private int counterImageFrame;
    private int hitCooldown;


    //Constructor

    public ExplosionBigSize(double x, double y) {
        explosionsBigSize = new ArrayList<>();
        explosionsBigSize.addAll(AnimationImageData.createExplosionBigSize());
        this.x = x;
        this.y = y;
    }


    //Functions

    public boolean update() {
        counterImageFrame++;
        if (counterImageFrame >= explosionsBigSize.size()){
            counterImageFrame = 0;
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        System.out.println("РИСУЕМ ОГРОМНЫЙ ВЗРЫВ");
        g.drawImage(explosionsBigSize.get((counterImageFrame)), (int) x - 124, (int) y - 124, null);
    }
}
