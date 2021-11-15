import java.awt.*;
import java.util.ArrayList;

public class Shield {

    //Fields
    private  static ArrayList<Image> shieldImage;
    private double x;
    private double y;
    public int counterImageFrame;

    private int hitCooldown;


    //Constructor

    public Shield(double x, double y) {

        shieldImage = new ArrayList<>();
        shieldImage.addAll(AnimationImageData.createShield());
        this.x = x;
        this.y = y;
    }


    //Functions

    public boolean update() {
        counterImageFrame++;
        if (counterImageFrame >= shieldImage.size()){
            counterImageFrame = 0;
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        System.out.println("РИСУЕМ ЩИТ");
        g.drawImage(shieldImage.get((counterImageFrame)),(int) x,(int) y, null);
    }
}
