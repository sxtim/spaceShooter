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
        speed = 15;
        color = Color.YELLOW;
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
        g.fillOval((int)x + 29,(int)y,r,5 * r);
        //ищем в каком классе переопределяется метод drawOval
//        String name = g.getClass().getName();
//        System.out.println(name);
//        sun.java2d.SunGraphics2D



    }
    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
