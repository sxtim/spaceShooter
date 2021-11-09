import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Explosion {

    //Fields
    private ArrayList<Image> explosionsBigImage;
    private double x;
    private double y;
    private int counterImageFrame;

    //Constructor
    public Explosion(double x, double y) {
        explosionsBigImage = new ArrayList<>();
        explosionsBigImage.addAll(AnimationImageData.createExplosionBig());
        this.x = x;
        this.y = y;
    }


    //Functions

    public boolean update() {
        counterImageFrame++;
        if (counterImageFrame >= explosionsBigImage.size()){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        g.drawImage(explosionsBigImage.get((counterImageFrame)), (int) x - 124, (int) y - 124, null);
    }
}
