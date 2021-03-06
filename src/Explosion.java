import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Explosion {

    //Fields
    private ArrayList<Image> explosionsBig2Image;
    private double x;
    private double y;
    private int counterImageFrame;

    //Constructor
    public Explosion(double x, double y) {
        explosionsBig2Image = new ArrayList<>();
        explosionsBig2Image.addAll(AnimationImageData.createExplosionBig2());
        this.x = x;
        this.y = y;
    }


    //Functions

    public boolean update() {
        counterImageFrame++;
        if (counterImageFrame >= explosionsBig2Image.size()){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        g.drawImage(explosionsBig2Image.get((counterImageFrame)), (int) x - 124, (int) y - 124, null);
    }
}
