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

    public static int YStartBack0;
    public static int YStartBack1_1;
    public static int YStartBack1_2 = -1800;
    public static int YStartBack2_1;
    public static int YStartBack2_2 = -1800;
    public static int YStartBack3_1;
    public static int YStartBack3_2 = -1800;
    public static double speedBack1 = 1;
    public static int speedBack2 = 2;
    public static int speedBack3 = 3;

    Image backGroundImage[] = {imageBack0, imageBack1_1, imageBack1_2, imageBack2_1,  imageBack2_2, imageBack3_1,   imageBack3_2};

    private Color color;


    //Constructor
    public GameBack() {
        color = Color.ORANGE;

    }


    //Functions
    public void update() {
        YStartBack0 = 0;

//        if(Player.up) {
//            YStartBack1_1 += speedBack1;
//            YStartBack1_2 += speedBack1;
//            if (YStartBack1_1 == 1800) {
//                YStartBack1_1 = 0;
//            }
//            if(YStartBack1_2 == 0) {
//                YStartBack1_2 = -1800;
//            }
//        }
//        if(Player.down) {
//
//            YStartBack1_1 -= speedBack1;
//            YStartBack1_2 -= speedBack1;
//            if (YStartBack1_1 == -1800) {
//                YStartBack1_1 = 0;
//            }
//            if (YStartBack1_2 == 1) {
//                YStartBack1_2 = 1800;
//            }
//        }
        YStartBack1_1 += speedBack1;
        YStartBack1_2 += speedBack1;
        YStartBack2_1 += speedBack2;
        YStartBack2_2 += speedBack2;
        YStartBack3_1 += speedBack3;
        YStartBack3_2 += speedBack3;

        //проверка фона 1
        if (YStartBack1_1 == 1800) {
            YStartBack1_2 = 0;
        }
        if(YStartBack2_1 == 0) {
            YStartBack2_2 = -1800;
        }

        //проверка фона 2
        if (YStartBack2_1 == 1800) {
            YStartBack2_1 = 0;
        }
        if(YStartBack2_2 == 0) {
            YStartBack2_2 = -1800;
        }
        //проверка фона 3
        if (YStartBack3_1 == 1800) {
            YStartBack3_1 = 0;
        }
        if(YStartBack3_2 == 0) {
            YStartBack3_2 = -1800;
        }

    }

    public void draw(Graphics2D g) {

        g.drawImage(backGroundImage[0], 0, YStartBack0, null);
        g.drawImage(backGroundImage[1], 0, YStartBack1_1, null);
        g.drawImage(backGroundImage[2], 0, YStartBack1_2, null);
        g.drawImage(backGroundImage[3], 0, YStartBack2_1, null);
        g.drawImage(backGroundImage[4], 0, YStartBack2_2, null);
        g.drawImage(backGroundImage[5], 0, YStartBack3_1, null);
        g.drawImage(backGroundImage[6], 0, YStartBack3_2, null);

//        g.setColor(color);
//        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);//рисуем прямоугольник желтый
    }
}
