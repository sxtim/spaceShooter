import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class EnemyMeteor {
    private static Image meteorXlImage;
    private static Image meteorLImage;
    private static ArrayList<Image> sparksImages = new ArrayList<>();

    static {
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion1.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion2.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion3.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion4.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion5.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion6.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion7.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion8.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion9.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion10.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion11.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion12.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion13.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion14.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion15.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion16.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion17.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion18.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion19.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion20.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion21.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion22.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion23.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion24.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion25.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion26.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion27.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion28.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion29.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion30.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion31.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion32.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion33.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion34.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion35.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion36.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion37.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion38.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion39.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion40.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion41.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion42.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion43.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion44.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion45.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion46.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion47.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion48.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion49.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion50.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion51.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion52.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion53.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion54.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion55.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion56.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion57.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion58.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion59.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion60.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion61.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion62.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion63.png").getImage());
        sparksImages.add(new ImageIcon("Image/explosions/small/explosion64.png").getImage());

    }

    private Point2D pos = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
    private Point2D velocity = new Point2D(0, 0);
    private double angle = 1;
    private int r;
    Color color;
    private double speed;
    private boolean drawMeteorL;
    private boolean dead;
    private boolean hit;
    private long hitTimer;


    private int health;
    private int type;
    private int rank;
    private int animFrame;
    int hitCooldown;


    //Constructor
    public EnemyMeteor(int type, int rank) {
        System.out.println("meteor created");
        hit = false;
        hitTimer = 0;

        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1):
                color = Color.GREEN;
                switch (rank) {
                    case (1):
//                        System.out.println("изменяется картинка у первого типа");
                        meteorXlImage = new ImageIcon("Image/meteors/meteor-01-xl.png").getImage();
                        pos.x = Math.random() * GamePanel.WIDTH;
                        pos.y = 0;
                        r = 27;
                        speed = 0.2;
                        health = 3;
                        double angle = Math.toRadians(Math.random() * 360);// угол направления шариков от 0 до 360
                        acceleration.x = Math.sin(angle) * speed; //смещение шариков
                        acceleration.y = Math.cos(angle) * speed;
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
                        health = 5;
                        double angle = Math.toRadians(Math.random() * 360);
                        acceleration.x = Math.sin(angle) * speed; //смещение шариков
                        acceleration.y = Math.cos(angle) * speed;


                }
        }
    }

    //Functions
    public boolean isDead() {
        return dead;
    }

    public void hit(boolean ignoreCooldown, Player player) {//при попадании уменьшаем здоровье
        if (!ignoreCooldown && hitCooldown > 0) {
            return;
        }

        health--;
        hitCooldown = 60;

        if (health <= 0) {
            dead = true;
        }
        hit = true;
        hitTimer = System.nanoTime();

        if (player != null) {
            Point2D delta = player.pos.copy().minus(pos);//расстояние между позициями игрока и метеора
            acceleration.set(speed, 0)  // tmp acceleration
                    .rotate(delta.multiple(-1).angle());
            player.velocity.set(delta.multiple(-1));
            return;
        }
    }

    public void update() {
        if (hit) {
            long elapsed = (System.nanoTime() - hitTimer) / 1000000;
            if (elapsed > 1000) {
                hit = false;
                hitTimer = 0;
            }
        }

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
                GamePanel.enemies.add(e);
            }
        }
    }


    public void draw(Graphics2D g) {

        if (drawMeteorL) {
                System.out.println("рисуем тип 2");
                AffineTransform origForm1; //создаем объект класса AffineTransform
                origForm1 = g.getTransform();//получаем текущее значение
                AffineTransform newForm1 = (AffineTransform) (origForm1.clone());//клонируем текущее значение
                newForm1.rotate(angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
                g.setTransform(newForm1);//
                g.drawImage(meteorLImage, (int) pos.x - 20, (int) pos.y - 20, null);
                g.setTransform(origForm1);

        } else {
            if(hit) {

                System.out.println("рисуем тип 1");
                AffineTransform origForm; //создаем объект класса AffineTransform
                origForm = g.getTransform();//получаем текущее значение
                AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
                newForm.rotate(angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
                g.setTransform(newForm);//
                g.drawImage(meteorXlImage, (int) pos.x - 27, (int) pos.y - 27, null);
                g.setTransform(origForm);
                g.drawImage(sparksImages.get((animFrame / 5) % sparksImages.size()), (int) pos.x - 124, (int) pos.y - 124, null);
            }else {
                AffineTransform origForm; //создаем объект класса AffineTransform
                origForm = g.getTransform();//получаем текущее значение
                AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
                newForm.rotate(angle, pos.x, pos.y);//вертим полученное изображение относительно X и Y
                g.setTransform(newForm);//
                g.drawImage(meteorXlImage, (int) pos.x - 27, (int) pos.y - 27, null);
                g.setTransform(origForm);
            }
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
}
