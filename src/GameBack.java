import javax.swing.*;
import java.awt.*;

public class GameBack {
    //Fields
    private static Image imageBack0 = new ImageIcon("Image/background/bkgd_0.png").getImage();
    private static Image imageBack1_1 = new ImageIcon("Image/background/bkgd_1.png").getImage();
    private static Image imageBack1_2 = new ImageIcon("Image/background/bkgd_1.png").getImage();
    private static Image imageBack2_1 = new ImageIcon("Image/background/bkgd_2.png").getImage();
    private static Image imageBack2_2 = new ImageIcon("Image/background/bkgd_2.png").getImage();
    private static Image imageBack3_1 = new ImageIcon("Image/background/bkgd_3_3.png").getImage();
    private static Image imageBack3_2 = new ImageIcon("Image/background/bkgd_3_3.png").getImage();

    private static int YMove0;
    private static int YMove1_1;
    private static int YMove1_2 = -1800;
    private static int YMove2_1;
    private static int YMove2_2 = -1800;
    private static int YMove3_1;
    private static int YMove3_2 = -1800;
    private static double speed1 = 1;
    private static int speed2 = 2;
    private static int speed3 = 3;

    Image backGroundImage[] = {imageBack0, imageBack1_1, imageBack1_2, imageBack2_1,  imageBack2_2, imageBack3_1,   imageBack3_2};

    private Color color;


    //Constructor
    public GameBack() {
        color = Color.ORANGE;
    }


    //Functions
    public void update() {
        YMove0 = 0;
        YMove1_1 += speed1;
        YMove1_2 += speed1;
        YMove2_1 += speed2;
        YMove2_2 += speed2;
        YMove3_1 += speed3;
        YMove3_2 += speed3;

        if (YMove1_1 == 1800) {
            YMove1_1 = 0;
        }
        if(YMove1_2 == 0) {
            YMove1_2 = -1800;
        }

        if (YMove2_1 == 1800) {
            YMove2_1 = 0;
        }
        if(YMove2_2 == 0) {
            YMove2_2 = -1800;
        }

        if (YMove3_1 == 1800) {
            YMove3_1 = 0;
        }
        if(YMove3_2 == 0) {
            YMove3_2 = -1800;
        }

    }

    public void draw(Graphics2D g) {

        g.drawImage(backGroundImage[0], 0,YMove0, null);
        g.drawImage(backGroundImage[1], 0, YMove1_1, null);
        g.drawImage(backGroundImage[2], 0, YMove1_2, null);
        g.drawImage(backGroundImage[3], 0, YMove2_1, null);
        g.drawImage(backGroundImage[4], 0, YMove2_2, null);
        g.drawImage(backGroundImage[5], 0, YMove3_1, null);
        g.drawImage(backGroundImage[6], 0, YMove3_2, null);

//        g.setColor(color);
//        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);//рисуем прямоугольник желтый
    }
}
