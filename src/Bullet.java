import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet {

    public static final int TYPE_MY_BULLET = 1;
    public static final int TYPE_ENEMY_BULLET = 2;
    public static final int TYPE_ENEMY_MINE = 3;
    //Fields
    private Image imgBullet;
    public Point2D pos = new Point2D(0, 0);
    public Point2D velocity = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
    private double angle;
    private int timerMine;
    private boolean deadMine;
    private int r;
    private int rMaxExplosion;
    private Point2D deltaPos;
    private double x;
    private double y;
    private double distX;
    private double distY;
    private double dist;
    public int type = TYPE_MY_BULLET;


    private Color color;


    //Constructor
    public Bullet(int type, double x, double y, double angle) {
        timerMine = 600;
        rMaxExplosion = 500;
        this.type = type;
        this.pos.set(x, y);
        int speed = 0;
        r = 0;
        switch (type) {
            case TYPE_MY_BULLET:
                imgBullet = new ImageIcon("Image/bullets/bullet21.png").getImage();
                r = 10;
                speed = 10;
                break;
            case TYPE_ENEMY_BULLET:
                speed = 5;
                r = 10;
                imgBullet = new ImageIcon("Image/bullets/bullet1.png").getImage();
                break;
            case TYPE_ENEMY_MINE:
                System.out.println("MINE");
                speed = 0;
                r = 15;
                imgBullet = new ImageIcon("Image/bullets/mine.gif").getImage();
                break;
        }

        //нач координаты пули - координаты героя
        this.x = pos.x;
        this.y = pos.y;
        distX = GamePanel.mousePos.x - x;// разница по Х от мыши до пули
        distY = y - GamePanel.mousePos.y;// разница по Y от мыши до пули

        dist = (Math.sqrt(distX * distX + distY * distY));//расстояние от мыши до пули


        deltaPos = new Point2D(speed, 0).rotate(angle);
//        System.out.println("bullet create with angle=" + angle + " actualAngle=" + deltaPos.angle());
        color = Color.YELLOW;
    }

    //Functions
    public boolean isDeadMine(){
        return deadMine;
    }


    public void explosionMine() {
        if(type == TYPE_ENEMY_MINE) {
            if(timerMine == 0)
                deadMine = true;
            r ++;
            if (r >= rMaxExplosion) {
                r = 15;
            }
        }
    }



    public void update() {
        timerMine--;
        if(timerMine <= 0) {
            deadMine = true;
            timerMine = 600;
        }
        pos.add(deltaPos);
    }

    public void hit(Player player) {
        Point2D delta = player.pos.copy().minus(pos);//расстояние между позициями игрока и метеора
        acceleration.set(10, 0)  // tmp acceleration
                .rotate(delta.multiple(-1).angle());
        player.velocity.set(delta.multiple(-1));
        return;
    }

    //проверка не улетела ли пуля за экран
    public boolean remove() {
        return pos.y < 0 || pos.y > GamePanel.HEIGHT || pos.x < 0 || pos.x > GamePanel.WIDTH || deadMine;
    }


    public void draw(Graphics2D g) {

//        Point2D nextPos = pos.copy().add(deltaPos.copy().multiple(0)); // not used?
        AffineTransform origForm; //создаем объект класса AffineTransform
        origForm = g.getTransform();//получаем текущее значение
        AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
        if (distX > 0)
            newForm.rotate(Math.acos(distY / (dist)), pos.x, pos.y);//вертим полученное изображение относительно X и Y
        if (distX < 0)
            newForm.rotate(-Math.acos(distY / (dist)), pos.x, pos.y);//вертим полученное изображение относительно X и Y
        g.setTransform(newForm);//ставим трансформированное изображение
        g.drawImage(imgBullet, (int) pos.x - r, (int) pos.y - r, null);//рисуем картинку
        g.setTransform(origForm);//возвращаем старое значение

        g.drawOval((int) pos.x - r / 2, (int) pos.y - r / 2, r, r);

//        g.drawImage(imgBullet, (int) nextPos.x - 4* r, (int) nextPos.y - 3 *r, null);

        //сервисные линии
//        g.setColor(Color.CYAN);
//        g.drawOval((int) (pos.x - r), (int) (pos.y - r), r * 2, r * 2);


//                g.setColor(color);
//        g.fillOval((int)pos.x,(int)pos.y,r,5 * r);


//        Point2D nextPos = pos.copy().add(deltaPos.copy().multiple(10));
//        g.drawImage(imgBullet, (int) nextPos.x, (int) nextPos.y, null);


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
