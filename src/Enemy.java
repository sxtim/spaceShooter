import java.awt.*;

public class Enemy {

    //Fields
    private double x;
    private double y;
    private double dx;//сдвиг при движении
    private double dy;
    private int r;
    Color color;
    private double speed;

    private int health;
    private int type;
    private int rank;

    //Constructor
    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        switch (type) {
            case (1): color = Color.GREEN;
                switch (rank) {
                    case (1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 7;

                        speed = 2;
                        health = 10;
                        double angle = Math.toRadians(Math.random() * 360);// угол направления шариков от 0 до 360
                        dx = Math.sin(angle) * speed; //смещение шариков
                        dy = Math.cos(angle) * speed;
                }
        }
    }
    //Functions
    public boolean remove(){
       return health <= 0;
    }

    public void update() {
    x += dx;
    y += dy;
    //проверка выхода за пределы поля
    //еесли враг вышел за пределы поля, то возвращаем его
    if(x < 0 && dx < 0) dx = -dx;
    if(x > GamePanel.WIDTH && dx > 0) dx = -dx;
    if(y < 0 && dy < 0) dy = -dy;
    if(y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }

    public void hit(){//при попадание именьшаем здоровье
        health--;
        System.out.println(health);
    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x - r, (int)y -r, 2 * r, 2 * r); //рисуем с середины экрана
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int)x - r, (int)y -r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
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
