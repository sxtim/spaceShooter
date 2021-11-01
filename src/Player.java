import javax.swing.*;
import java.awt.*;

public class Player {
    //Fields
    private static Image imageShip = new ImageIcon("Image/playership6.png").getImage();
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
    private int health;

    public Player() {

        health = 3;
        x = (double) GamePanel.WIDTH / 2;
        y = (double) GamePanel.HEIGHT / 1.3;
        dx = 0;
        dy = 0;
        r = 25;
        speed = 5;

        color1 = Color.WHITE;

    }

    //Functions

    //Move player
    public boolean upMove(){
        return up && y > r;
    }
    public boolean downMove(){
        return down && y < GamePanel.HEIGHT - r;
    }public boolean leftMove(){
        return left && x > 0;
    }public boolean rightMove(){
        return right && x < GamePanel.WIDTH - r;
    }

    public void update() {
        if(upMove()){
            dy = - speed;
        }
        if(downMove()){
            dy = speed;
        }
        if(leftMove()){
            dx = -speed;
        }
        if(rightMove()){
            dx = speed;
        }
        if(up && left || up && right || down && left || down && right){

            double angle = Math.toRadians(45);//корректировка угла движения(переводим градусы в радианы)
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }
        y += dy;
        x += dx;
        //Отображение координат
//        System.out.println("X: " + x);
//        System.out.println("Y: " + y);
//        System.out.println("DX: " + dx);
//        System.out.println("DY: " + dy);
        dy = 0;
        dx = 0;


        //Shoot player
        if(isFiring){
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX() - 20, (int) GamePanel.player.getY()));
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX() + 20, (int) GamePanel.player.getY()));
            isFiring = false;
        }

    }
        public void hit(){
        health--;
            System.out.println(health);
        }


    public void draw(Graphics2D g) {//передаем графику и рисуем игрока
        //TODO
//        AffineTransform origForm; //создаем объект класса AffineTransform
//        origForm = g.getTransform();//получаем текущее значение
//        AffineTransform newForm = (AffineTransform)(origForm.clone());//клонируем текущее значение
//        newForm.rotate(angl, x + 29, y + 25);//вертим полученное изображение
//        g.setTransform(newForm);//ставим трансформированное изображение
//        g.drawImage(imageShip, (int) x, (int) y, null);//рисуем картинку
//        g.setTransform(origForm);//возвращаем старое значение

        g.drawImage(imageShip, (int)x - 61/2, (int)y - 42/2, null);
        g.setColor(Color.CYAN);
        g.drawOval((int)(x - r), (int)(y - r), r * 2, r * 2);

        //Player в виде точки
//        g.setColor(color1);
//        g.fillOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
//        g.setStroke(new BasicStroke(3));
//        g.setColor(color1.darker());
//        g.drawOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
//        g.setStroke(new BasicStroke(1));

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


    public Point2D getPos() {
        return new Point2D((int)x, (int)y);
    }
}
