import javax.swing.*;
import java.awt.*;

public class Bullet {

    //Fields
    private static Image imgBullet = new ImageIcon("Image/bullets/bullet1.png").getImage();
    private Point2D pos = new Point2D(0, 0);
    private int r;
    private Point2D deltaPos;

    private Color color;


    //Constructor
    public Bullet(int x, int y, double angle){
        this.pos.set(x, y);
        r = 2;
        deltaPos = new Point2D(8, 0).rotate(angle);
        System.out.println("bullet create with angle=" + angle + " actualAngle=" + deltaPos.angle());
        color = Color.YELLOW;
    }

    //Functions
    public void update(){
        pos.add(deltaPos);
    }

    //проверка не улетела ли пуля за экран
    public boolean remove(){
        // TODO proper bounds checking
        return pos.y < 0 || pos.y > GamePanel.HEIGHT || pos.x < 0 || pos.x > GamePanel.WIDTH;
    }


    public void draw(Graphics2D g){
        g.drawImage(imgBullet, (int) pos.x - 10,(int)pos.y, null );

        g.setColor(Color.WHITE);
        Point2D nextPos = pos.copy().add(deltaPos.copy().multiple(10));
        g.drawLine((int) pos.x, (int) pos.y, (int) nextPos.x, (int) nextPos.y);
        g.drawString("angle=" + deltaPos.angle(), (int)pos.x, (int)pos.y);

//        g.setColor(color);
//        g.fillOval((int)x,(int)y,r,5 * r);
        //ищем в каком классе переопределяется метод drawOval
//        String name = g.getClass().getName();
//        System.out.println(name);
//        sun.java2d.SunGraphics2D



    }
    //Getters
    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public int getR() {
        return r;
    }
}
