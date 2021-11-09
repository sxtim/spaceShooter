import java.awt.*;
import java.util.ArrayList;

public class ExplosionHit {

    //Fields
    private ArrayList<Image> explosionsSmallImage;
    private double x;
    private double y;
    private int counterImageFrame;
    private int hitCooldown;


    //Constructor

    public ExplosionHit(double x, double y) {
        explosionsSmallImage = new ArrayList<>();
        explosionsSmallImage.addAll(AnimationImageData.createExplosionSmall());
        this.x = x;
        this.y = y;
    }


    //Functions

    public boolean update() {
        counterImageFrame++;
        if (counterImageFrame >= explosionsSmallImage.size()){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        System.out.println("РИСУЕМ МАЛЕНЬКИЙ ВЗРЫВ");
        g.drawImage(explosionsSmallImage.get((counterImageFrame)), (int) x - 124, (int) y - 124, null);
    }
}
