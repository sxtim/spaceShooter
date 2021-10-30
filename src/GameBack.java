import javax.swing.*;
import java.awt.*;

public class GameBack {
    //Fields
    private static Image backImage = new ImageIcon("Image/stars.png").getImage();
    private Color color;


    //Constructor
    public GameBack() {
        color = Color.ORANGE;
    }


    //Functions
    public void update() {

    }

    public void draw(Graphics2D g) {

        g.drawImage(backImage, 0,0, null);
//        g.setColor(color);
//        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);//рисуем прямоугольник желтый
    }
}
