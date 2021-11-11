import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class EnemyMeteor {
    private static Image meteorXlImage;
    private static Image meteorLImage;

    private Point2D pos = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
    private Point2D velocity = new Point2D(0, 0);
    private double angle;
    private int r;
    private double speed;
    private boolean drawMeteorL;


    private boolean ready;
    private boolean dead;
    private int health;
    private int type;
    private int rank;


    int hitCooldown;


    //Constructor
    public EnemyMeteor(int type, int rank) {
        System.out.println("meteor created");

        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1):
                switch (rank) {
                    //Default enemyMeteor
                    case (1):
//                        System.out.println("изменяется картинка у первого типа");
                        meteorXlImage = new ImageIcon("Image/meteors/meteor-01-xl.png").getImage();
                        pos.x = Math.random() * GamePanel.WIDTH;
                        pos.y = 0;
                        r = 27;
                        speed = 0.2;
                        health = 2;
                        double angle = Math.toRadians(Math.random() * 360);// угол направления шариков от 0 до 360
                        acceleration.x = Math.sin(angle) * speed; //смещение шариков
                        acceleration.y = Math.cos(angle) * speed;
                        ready = false;
                        dead = false;
                }
                switch (rank) {
                    case (2):
                        drawMeteorL = true;
//                        System.out.println("создаем второй тип метеора");
                        meteorLImage = new ImageIcon("Image/meteors/meteor-01-l.png").getImage();
                        pos.x = Math.random() * GamePanel.WIDTH;
                        pos.y = 0;
                        r = 20;
                        speed = 0.3;
                        health = 1;
                        double angle = Math.toRadians(Math.random() * 360);
                        acceleration.x = Math.sin(angle) * speed; //смещение шариков
                        acceleration.y = Math.cos(angle) * speed;
                        ready = false;
                        dead = false;
                }
        }
    }

    //Functions
    public boolean isDead() {
        return dead;
    }


    public void hit(boolean ignoreCooldown, Player player) {//при попадании уменьшаем здоровье
        if (!ignoreCooldown && hitCooldown > 0) {
            Point2D delta = player.pos.copy().minus(pos);//расстояние между позициями игрока и метеора
            acceleration.set(speed, 0)  // tmp acceleration
                    .rotate(delta.multiple(-1).angle());
            player.velocity.set(delta.multiple(-1));
            return;
        }
        hitCooldown = 60;

        health--;
        if (health <= 0) {
            dead = true;
        }

            // Отскок
//        if (player != null) {
//            Point2D delta = player.pos.copy().minus(pos);//расстояние между позициями игрока и метеора
//            acceleration.set(speed, 0)  // tmp acceleration
//                    .rotate(delta.multiple(-1).angle());
//            player.velocity.set(delta.multiple(-1));
//            return;
//        }
    }

    public void update() {
        velocity.multiple(0.9);
        velocity.add(acceleration);
        velocity.clamp(3);
        pos.add(velocity);

        if (hitCooldown > 0) {
            hitCooldown--;
        }

        //проверка выхода за пределы поля
        //еесли враг вышел за пределы поля, то возвращаем его
        if (pos.x < 0 && acceleration.x < 0) acceleration.x = -acceleration.x;
        if (pos.x > GamePanel.WIDTH && acceleration.x > 0) acceleration.x = -acceleration.x;
        if (pos.y < 0 && acceleration.y < 0) acceleration.y = -acceleration.y;
        if (pos.y > GamePanel.HEIGHT && acceleration.y > 0) acceleration.y = -acceleration.y;




//        System.out.println(hitCooldown);
        angle += Math.PI / 360;
    }

    public void explode() {
        if (rank == 1) {
            int amount = 0;
            if (type == 1) {
                amount = 3;
            }
            for (int i = 0; i < amount; i++) {
                EnemyMeteor e = new EnemyMeteor(1, 2);
                e.pos.x = this.pos.x;
                e.pos.y = this.pos.y;
                GamePanel.enemyMeteors.add(e);
            }
        }
    }


    public void draw(Graphics2D g) {

        if (drawMeteorL) {
//                System.out.println("рисуем тип 2");
            AffineTransform origForm1; //создаем объект класса AffineTransform
            origForm1 = g.getTransform();//получаем текущее значение
            AffineTransform newForm1 = (AffineTransform) (origForm1.clone());//клонируем текущее значение
            newForm1.rotate(angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
            g.setTransform(newForm1);//
            g.drawImage(meteorLImage, (int) pos.x - 20, (int) pos.y - 20, null);
            g.setTransform(origForm1);

        } else {


//                System.out.println("рисуем тип 1");
            AffineTransform origForm; //создаем объект класса AffineTransform
            origForm = g.getTransform();//получаем текущее значение
            AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
            newForm.rotate(-angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
            g.setTransform(newForm);//
            g.drawImage(meteorXlImage, (int) pos.x - 27, (int) pos.y - 27, null);
            g.setTransform(origForm);

        }
    }
//        g.drawImage(enemyImages.get(0), (int) pos.x - 27, (int) pos.y - 27, null);//анимация
    //  g.setColor(Color.CYAN);
    // g.drawOval((int)(x - r), (int)(y - r), r * 2 , r * 2 );
//        g.drawImage(sparksImages.get((animFrame) % sparksImages.size()), (int) pos.x - 32, (int) pos.y - 30, null);//анимация

//        g.drawImage(enemyImages.get((animFrame / 30) % enemyImages.size()), (int) pos.x, (int) pos.y - 30, null);


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
    public int getHeath(){
        return health;
    }
    public int getType() {
        return type;
    }
    public int getRank(){
        return rank;
    }
}
