import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;

public class Player {
    //Fields
    private double x;
    private double y;
    private double dx; //коифициент смещения по диагонали
    private double dy; //коифициент смещения по диагонали
    private int r; // радиус
    private Color color1;
    private Color color2;
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    private int speed;
    public static boolean isFiring;

    public Player() {
        x = (double) GamePanel.WIDTH / 2;
        y = (double) GamePanel.HEIGHT / 2;
        dx = 0;
        dy = 0;
        r = 5;
        speed = 5;

        color1 = Color.WHITE;

    }

    //Functions

    //Move player
    public void update() {
        if(up && y > r){
            dy = - speed;
        }
        if(down && y < GamePanel.HEIGHT - r){
            dy = speed;
        }
        if(left && x > r){
            dx = -speed;
        }
        if(right && x < GamePanel.WIDTH - r){
            dx = speed;
        }
        if(up && left || up && right || down && left || down && right){

            double angle = Math.toRadians(45);//корректировка угла движения(переводим градусы в радианы)
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }
        y += dy;
        x += dx;
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("DX: " + dx);
        System.out.println("DY: " + dy);
        dy = 0;
        dx = 0;
        //Shoot player
        if(isFiring){
            GamePanel.bullets.add(new Bullet());
        }

    }

    public void draw(Graphics2D g) {//передаем графику и рисуем игрока
        g.setColor(color1);
        g.fillOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));

    }

    //Getters

    public int getR() {
        return r;
    }

    public double getX(){
        return x;
    }


    public double getY(){
        return y;
    }

}
