import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {
    //Fields
    private static Image imageShip = new ImageIcon("Image/player/playership7.png").getImage();
    private Point2D pos = new Point2D(0, 0);
    private double dx; //коифициент смещения по диагонали
    private double dy; //коифициент смещения по диагонали
    private int r; // радиус
    private double angle;//угол поворота

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
        pos.set(GamePanel.WIDTH / 2.0, GamePanel.HEIGHT / 1.3);
        dx = 0;
        dy = 0;
        r = 35;
        speed = 5;

        color1 = Color.WHITE;

    }

    //Functions

    //Move player
    public boolean upMove() {
        return up && pos.y > r;
    }

    public boolean downMove() {
        return down && pos.y < GamePanel.HEIGHT - r;
    }

    public boolean leftMove() {
        return left && pos.x > 0;
    }

    public boolean rightMove() {
        return right && pos.x < GamePanel.WIDTH - r;
    }

    public void update() {

        angle = GamePanel.mousePos.copy().minus(pos).angle();

        if (upMove()) {
            dy = -speed;
        }
        if (downMove()) {
            dy = speed;
        }
        if (leftMove()) {
            dx = -speed;
        }
        if (rightMove()) {
            dx = speed;
        }
        if (up && left || up && right || down && left || down && right) {

            double angle = Math.toRadians(45);//корректировка угла движения(переводим градусы в радианы)
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }
        pos.add(dx, dy);
        //Отображение координат
//        System.out.println("X: " + x);
//        System.out.println("Y: " + y);
//        System.out.println("DX: " + dx);
//        System.out.println("DY: " + dy);
        dy = 0;
        dx = 0;


        //Shoot player
        if (isFiring) {
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX(), (int) GamePanel.player.getY(), GamePanel.player.angle));
            isFiring = false;
        }
    }

    public void hit() {
        health--;
        System.out.println(health);
    }


    public void draw(Graphics2D g) {//передаем графику и рисуем игрока
        //TODO
        g.setColor(Color.WHITE);
        g.drawLine((int) pos.x, (int) pos.y, (int) GamePanel.mousePos.x, (int) GamePanel.mousePos.y);
//        g.drawString("angle=" + angle, (int)pos.x, (int)pos.y);

        AffineTransform origForm; //создаем объект класса AffineTransform
        origForm = g.getTransform();//получаем текущее значение
        AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
        newForm.rotate(angle + Math.PI / 2, pos.x, pos.y);//вертим полученное изображение относительно X и Y
        g.setTransform(newForm);//ставим трансформированное изображение
        g.drawImage(imageShip, (int) pos.x - 70 / 2, (int) pos.y - 72 / 2, null);//рисуем картинку
        g.setTransform(origForm);//возвращаем старое значение

//        g.drawImage(imageShip, (int) pos.x - 70 / 2, (int) pos.y - 72 / 2, null);
        g.setColor(Color.CYAN);
        g.drawOval((int) (pos.x - r), (int) (pos.y - r), r * 2, r * 2);

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

    public double getX() {
        return pos.x;
    }


    public double getY() {
        return pos.y;
    }


    public Point2D getPos() {
        return pos;
    }
}
