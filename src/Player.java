import java.awt.*;

public class Player {
    //Fields
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


    public Player() {
        x = (double) GamePanel.WIDTH / 2;
        y = (double) GamePanel.HEIGHT / 2;
        dx = 0;
        dy = 0;
        r = 5;
        color1 = Color.WHITE;
        speed = 5;

    }

    //Functions
    public void update() {
        if(up && y > r){
            dy = - speed;
        }
        if(down && y < GamePanel.HEIGHT - r){
            dy = speed;
        }
        if(left && x > r){
            dx = -speed;
        }
        if(right && x < GamePanel.HEIGHT - r){
            dx = speed;
        }
        if(up && left || up && right || down && left || down && right){
            dy = dy * Math.sin(45);
            dx = dx * Math.cos(45);
        }
        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

    }

    public void draw(Graphics2D g) {//передаем графику и рисуем игрока
        g.setColor(color1);
        g.fillOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));

    }


}
