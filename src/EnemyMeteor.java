import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
import java.util.List;

public class EnemyMeteor {

    //Fields
    Image asteroidXL = new ImageIcon ("Image/meteors/meteor-01-xl.png").getImage();
    Image asteroidL = new ImageIcon ("Image/meteors/meteor-01-l.png").getImage();



    private static List<Image> enemyImages = new ArrayList<>();

        static {
        enemyImages.add(new ImageIcon("Image/enemy/figther1.png").getImage());
    }

    private double x;
    private double y;
    private double dx;//сдвиг при движении
    private double dy;
    private Point2D pos = new Point2D(0, 0);
    private double angle = 1;
    private int r;
    Color color;
    private double speed;


    private int health;
    private int type;
    private int rank;
    private int animFrame;
     int hitCooldown;


    //Constructor
    public EnemyMeteor(int type, int rank) {


        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1):
                color = Color.GREEN;
                switch (rank) {
                    case (1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 25;

                        speed = 2;
                        health = 10;
                        double angle = Math.toRadians(Math.random() * 360);// угол направления шариков от 0 до 360
                        dx = Math.sin(angle) * speed; //смещение шариков
                        dy = Math.cos(angle) * speed;
                }
        }
    }

    //Functions
    public boolean remove() {
        return health <= 0;
    }

    public void update() {
        x += dx;
        y += dy;
        //проверка выхода за пределы поля
        //еесли враг вышел за пределы поля, то возвращаем его
        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;
        if (y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;

        animFrame++;
        if (hitCooldown > 0) {
            hitCooldown--;
        }
    }

    public void hit(boolean ignoreCooldown) {//при попадании уменьшаем здоровье
        if (!ignoreCooldown && hitCooldown > 0) {

            return;
        }
        hitCooldown = 60;
        health--;
        System.out.println(health);
    }


    public void draw(Graphics2D g) {
//        g.drawImage(asteroidXL, (int) (x - 54/2),(int) (y - 54/2),null);

// The required drawing location
        int drawLocationX = 300;
        int drawLocationY = 300;

// Rotation information

        double rotationRequired = Math.toRadians (45);
        double locationX = 54 / 2f;
        double locationY = 54 / 2f;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//        g.drawImage(asteroidXL, tx, ImageObserver);
// Drawing the rotated image at the required drawing locations



//        AffineTransform origForm; //создаем объект класса AffineTransform
//        origForm = g.getTransform();//получаем текущее значение
//        AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
//        newForm.rotate(angle + Math.PI / 2, x, y);//вертим полученное изображение относительно X и Y
//        g.setTransform(newForm);//ставим трансформированное изображение
//        g.drawImage(asteroidXL, (int) x - 54 / 2, (int) y - 54 / 2, null);//рисуем картинку
//        g.setTransform(origForm);//возвращаем старое значение

//       if (dx < 0) g.drawImage(asteroidXL, (int) (x - 50/2),(int) (y - 40/2),null);//разные типы
//       if (dx > 0) g.drawImage(asteroidL, (int) (x - 50/2),(int) (y - 40/2),null);

//        g.drawImage(enemyImages.get((animFrame / 30) % enemyImages.size()), (int) x, (int) y, null);//анимация
        g.setColor(Color.CYAN);
        g.drawOval((int)(x - r), (int)(y - r), r * 2 , r * 2 );
//        g.setColor(color);
//        g.fillOval((int)x - r, (int)y -r, 2 * r, 2 * r); //рисуем с середины экрана
//        g.setStroke(new BasicStroke(3));
//        g.setColor(color.darker());
//        g.drawOval((int)x - r, (int)y -r, 2 * r, 2 * r);
//        g.setStroke(new BasicStroke(1));
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
