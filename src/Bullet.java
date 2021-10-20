import java.awt.*;

public class Bullet {

    //Fields
    private double x;
    private double y;
    private int r;
    private int speed;

    private Color color;


    //Constructor
    public Bullet(){
        x = (int)GamePanel.player.getX();
        y = (int)GamePanel.player.getY();
        r = 2;
        speed = 10;
        color = Color.WHITE;
    }

    //Functions
    public void update(){
        y -= speed;
    }

    //проверка не улетела ли пуля за экран
    public boolean remove(){
        return y < 0;
    }


    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int)x,(int)y,r,2 * r);



    }



}
