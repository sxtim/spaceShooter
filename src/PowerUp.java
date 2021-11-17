import javax.swing.*;
import java.awt.*;

public class PowerUp {
    //Fields
    public static final int TYPE_POWERUP = 1;
    public static final int TYPE_LIVE = 2;
    public static final int TYPE_TIME_DOWN = 3;
    private Image image;
    private double x;
    private double y;
    private int r;


    private int type;
    // 1 -- +1 power
    // 2 -- +1 life
    // 3 -- +2 power


    //Constructor

    public PowerUp(int type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
        r = 20;

        switch (type) {
            case TYPE_POWERUP: {
                image = new ImageIcon("Image/icon/iconpowerup.png").getImage();
            }
        }

//        if (type == 1) {
//            color1 = Color.PINK;
//            r = 10;
//        }
//        if (type == 2) {
//            image = new ImageIcon("Image/iconpowerup.png").getImage();
//            r = 18;
//        }
//        if (type == 3) {
//            color1 = Color.YELLOW.darker();
//            r = 20;
//        }


    }


    //Functions
    public boolean update() {
        y += 2;
        return y > GamePanel.HEIGHT + r;
    }


    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x - r, (int) y - r, null);


//        g.fillRect((int) x - r, (int) y - r, 2 * r, 2 * r);
//
//        g.setStroke(new BasicStroke(3));
//        g.setColor(color1.darker());
//        g.drawRect((int) x - r, (int) y - r, 2 * r, 2 * r);
//        g.setStroke(new BasicStroke(3));
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getType() {
        return type;
    }
}
