import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {
    //Fields
    private static Image imageShip = new ImageIcon("Image/player/playership8.png").getImage();
    private Image imageShield = new ImageIcon("Image/player/XDZT.gif").getImage();
    public Point2D pos = new Point2D(0, 0);
    public Point2D velocity = new Point2D(0, 0);
    private Point2D acceleration = new Point2D(0, 0);
    private int r; // радиус
    private double angle;//угол поворота
    private int speed;
    private Color color;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;


    public boolean recovering;
    private long recoveryTimer;
    public static boolean isFiring;
    private int lives;
    private int score;

    private int powerLevel;
    private int power;
    private int[] requiredPower = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    };

    public Player() {
        color = Color.RED;
        lives = 10;
        pos.set(GamePanel.WIDTH / 2.0, GamePanel.HEIGHT / 1.3);
        r = 34;
        speed = 5; //не используется?

        recovering = false;
        recoveryTimer = 0;
        score = 0;
    }

    //=====Functions======
    public boolean isRecovering() {
        return recovering;
    }

    public void gainLife() {
        lives++;
    }

    public void increasePower(int i) {
        power += i;
        if(powerLevel == 9){
            if (power > requiredPower[powerLevel]) {
                power = requiredPower[powerLevel];
            }
            return;
        }
        if (power >= requiredPower[powerLevel]) {
            power -= requiredPower[powerLevel];
            powerLevel++;
        }
    }

    public void loseLife() {
        lives--;
        // состояние восстановления - true
        recovering = true;
        //Таймер состояния восстановления инициализируем текущим временем
        recoveryTimer = System.nanoTime();
    }

    public void addScore(int i) {
        score += i;
    }

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
            if (powerLevel < 2) {
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle));
            } else if (powerLevel < 4) {
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle + 0.05));
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle - 0.05));
            } else {
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle + 0.1));
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle));
                GamePanel.bullets.add(new Bullet(Bullet.TYPE_MY_BULLET, (int) GamePanel.player.getX(), (int) GamePanel.player.getY(), angle - 0.1));

            }

            isFiring = false;
        }
        // пройденное время. разница между текущим временем и инициализированным таймером
        long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
        // сли отрезок времени (пройденное время между двумя запущенными таймерами) больше, то устанавливаем
        if (elapsed > 10000) {//recovery = false и recoveryTimer не запущен;
            recovering = false;
            recoveryTimer = 0;
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


    public void draw(Graphics2D g) {//передаем графику и рисуем игрока
        //TODO

            //Сервисные линии
//        g.setColor(Color.WHITE);
//        g.drawLine((int) pos.x, (int) pos.y, (int) GamePanel.mousePos.x, (int) GamePanel.mousePos.y);

//        g.drawString("angle=" + angle, (int)pos.x, (int)pos.y);
        if (recovering) {
            AffineTransform origForm; //создаем объект класса AffineTransform
            origForm = g.getTransform();//получаем текущее значение
            AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
            newForm.rotate(angle + Math.PI / 2, pos.x, pos.y);//вертим полученное изображение относительно X и Y
            g.setTransform(newForm);//ставим трансформированное изображение
            g.drawImage(imageShip, (int) pos.x - 60 / 2, (int) pos.y - 54 / 2, null);//рисуем картинку
            g.drawImage(imageShield, (int) pos.x - 90 / 2, (int) pos.y - 90 / 2 + 3, null);//рисуем картинку
            g.setTransform(origForm);//возвращаем старое значение


//            g.setColor(Color.CYAN);
//            g.drawOval((int) (pos.x - r), (int) (pos.y - r), r * 2, r * 2);




        } else {
            AffineTransform origForm; //создаем объект класса AffineTransform
            origForm = g.getTransform();//получаем текущее значение
            AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
            newForm.rotate(angle + Math.PI / 2, pos.x, pos.y);//вертим полученное изображение относительно X и Y
            g.setTransform(newForm);//ставим трансформированное изображение
            g.drawImage(imageShip, (int) pos.x - 60 / 2, (int) pos.y - 54 / 2, null);//рисуем картинку
            g.setTransform(origForm);//возвращаем старое значение
        }
        //Draw player power
        g.setColor(Color.ORANGE);
        g.fillRect(20, 100, getPower() * 20, 20);
        g.setColor(Color.ORANGE.darker());
        g.setStroke(new BasicStroke(4));
        for (int i = 0; i < getRequiredPower(); i++) {
            g.drawRect(20 + 20 * i, 100, 20, 20);
        }

        //Draw player lives
        for (int i = 0; i < lives; i++)
            g.drawImage(imageShip, 10 + (70 * i), 10, null);

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

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getPower() {
        return power;
    }

    public int getRequiredPower() {
        return requiredPower[powerLevel];
    }


}
