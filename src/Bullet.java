import javax.swing.*;
import java.awt.*;

public class Bullet {

    //Fields
    private static Image imgBullet = new ImageIcon("Image/bullets/bullet1.png").getImage();
    private double x;
    private double y;
    private int r;
    private int speed;

    private Color color;


    //Constructor
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        r = 2;
        speed = 8;
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
        g.drawImage(imgBullet, (int)x - 10,(int)y, null );
//        g.setColor(color);
//        g.fillOval((int)x,(int)y,r,5 * r);
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
