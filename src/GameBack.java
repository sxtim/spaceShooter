import java.awt.*;

public class GameBack {
    //Fields
    private Color color;


    //Constructor
    public GameBack() {
        color = Color.ORANGE;
    }


    //Functions
    public void update() {

    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);//рисуем прямоугольник желтый
    }
}
