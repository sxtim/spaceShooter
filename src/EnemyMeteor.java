import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class EnemyMeteor {

    private static List<Image> enemyImages = new ArrayList<>();

    static {
        enemyImages.add(new ImageIcon("Image/meteors/meteor-01-xl.png").getImage());
    }

    private Point2D pos = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
    private Point2D velocity = new Point2D(0, 0);
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
        System.out.println("meteor created");

        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1):
                color = Color.GREEN;
                switch (rank) {
                    case (1):
                        pos.x = Math.random() * GamePanel.WIDTH;
                        pos.y = 0;
                        r = 25;

                        speed = 1;
                        health = 10;
                        double angle = Math.toRadians(Math.random() * 360);// угол направления шариков от 0 до 360
                        acceleration.x = Math.sin(angle) * speed; //смещение шариков
                        acceleration.y = Math.cos(angle) * speed;
                }
        }
    }

    //Functions
    public boolean remove() {
        return health <= 0;
    }

    public void update() {
        velocity.multiple(0.9);

        velocity.add(acceleration);

        velocity.clamp(3);


        pos.add(velocity);
        //проверка выхода за пределы поля
        //еесли враг вышел за пределы поля, то возвращаем его
        if (pos.x < 0 && acceleration.x < 0) acceleration.x = -acceleration.x;
        if (pos.x > GamePanel.WIDTH && acceleration.x > 0) acceleration.x = -acceleration.x;
        if (pos.y < 0 && acceleration.y < 0) acceleration.y = -acceleration.y;
        if (pos.y > GamePanel.HEIGHT && acceleration.y > 0) acceleration.y = -acceleration.y;

        animFrame++;
        if (hitCooldown > 0) {
            hitCooldown--;
        }
        angle += Math.PI / 360;
    }

    public void hit(boolean ignoreCooldown, Player player) {//при попадании уменьшаем здоровье
        if (!ignoreCooldown && hitCooldown > 0) {

            return;
        }

        hitCooldown = 60;
        health--;

        if (player != null) {

            Point2D delta = player.pos.copy().minus(pos);//расстояние между позициями игрока и метеора

            acceleration.set(speed, 0)  // tmp acceleration
                    .rotate(delta.multiple(-1).angle());

            player.velocity.set(delta.multiple(-1));

            return;
        }
    }


    public void draw(Graphics2D g) {

        AffineTransform origForm; //создаем объект класса AffineTransform
        origForm = g.getTransform();//получаем текущее значение
        AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
        newForm.rotate(angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
        g.setTransform(newForm);//

        g.drawImage(enemyImages.get(0), (int) pos.x - 27, (int) pos.y - 27, null);//анимация
      //  g.setColor(Color.CYAN);
       // g.drawOval((int)(x - r), (int)(y - r), r * 2 , r * 2 );

        g.setTransform(origForm);
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
