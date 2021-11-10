import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {
    private static final int MAX_SPEED = 13;
    //Fields
    private static Image imageShip = new ImageIcon("Image/player/playership8.png").getImage();
    public Point2D pos = new Point2D(0, 0);
    public Point2D velocity = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
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
    private int lives;

    public Player() {

        lives = 3;
        pos.set(GamePanel.WIDTH / 2.0, GamePanel.HEIGHT / 1.3);
        r = 30;
        speed = 5; //не используется?

        color1 = Color.WHITE;

    }

    //Functions

    //Move player
    public boolean upMove() {
        return up;
    }

    public boolean downMove() {
        return down;
    }

    public boolean leftMove() {
        return left;
    }

    public boolean rightMove() {
        return right;
    }

    public void update() {

        angle = GamePanel.mousePos.copy().minus(pos).angle();

        acceleration.set(0, 0);

      //  System.out.println("движение вверх " + upMove());
      //  System.out.println("кнопка вверх нажата " + up);
        if (upMove()) {
            acceleration.add(Point2D.UP);
        }
        if (downMove()) {
            acceleration.add(Point2D.DOWN);
        }
        if (leftMove()) {
            acceleration.add(Point2D.LEFT);
        }
        if (rightMove()) {
            acceleration.add(Point2D.RIGHT);
        }
        if (acceleration.length() > 0.1) {
            acceleration.length(0.5);
        }


//        acceleration.rotate(angle + (Math.PI / 4) * 2); //поворот векторов тяги на угол корабля текущий

        //Shoot player
        if (isFiring) {
            acceleration.minus(new Point2D(1, 0).rotate(angle));
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle));
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle + 0.1));
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle - 0.1));
            GamePanel.bullets.add(new Bullet((int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle - 0.1));

            isFiring = false;
        }

        velocity.multiple(0.99); // затухание скорости
        velocity.add(acceleration);
        velocity.clamp(3);
        pos.add(velocity);


        if (pos.y < -r) {
            pos.y = GamePanel.HEIGHT;
        } else if (pos.y > GamePanel.HEIGHT) {
            pos.y = -r;
        } else if (pos.x < -r) {
            pos.x = GamePanel.WIDTH;
        } else if (pos.x > GamePanel.WIDTH) {
            pos.x = -r;
        }



    }

    public void hit() {
        lives--;
        System.out.println("========== LIVES " + lives + " LIVES =============");
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
        g.drawImage(imageShip, (int) pos.x - 60 / 2, (int) pos.y - 54 / 2, null);//рисуем картинку
        g.setTransform(origForm);//возвращаем старое значение

        for(int i = 0; i < lives; i++)//рисуем жизни player
            g.drawImage(imageShip, 10 + (70 * i), 10, null);

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

    public int playerGetLives(){
        return lives;
    }

}
