import java.awt.*;

public class PowerUp {
    //Fields
    private double x;
    private double y;
    private int r;
    private Color color1;

    private int type;
    // 1 -- +1 life
    // 2 -- +1 power
    // 3 -- +2 power


    //Constructor

    public PowerUp(int type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
        if (type == 1) {
            color1 = Color.PINK;
            r = 10;
        }
        if (type == 2) {
            color1 = Color.YELLOW;
            r = 15;
        }
        if (type == 3) {
            color1 = Color.YELLOW.darker();
            r = 20;
        }


    }


    //Functions
    public boolean update() {
        y += 2;
        return y > GamePanel.HEIGHT + r;

    }


    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillRect((int) x - r, (int) y - r, 2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawRect((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getType() {
        return type;
    }
}
