import java.awt.*;

public class Explosion {

    //Fields
    private double x;
    private double y;
    private int r;
    private int maxRadius;

    //Constructor

    public Explosion(double x, double y, int r, int maxRadius) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.maxRadius = maxRadius;
    }


    //Functions

    public boolean update() {
        r++;
        if (r >= maxRadius){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        System.out.println("РИСУЕМ КРУГ");
        g.setColor(new Color(255,255,255, 128));
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

    }
}
