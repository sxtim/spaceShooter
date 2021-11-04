//For future
public class Point2D {
    public double x;
    public double y;

    public Point2D(double x, double y) {

        this.x = x;
        this.y = y;
    }

    public Point2D minus(Point2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Point2D multiple(double mul) {
        this.x *= mul;
        this.y *= mul;
        return this;
    }


    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Point2D copy() {
        return new Point2D(x, y);
    }

    public Point2D add(double dx, double dy) {
        x += dx;
        y += dy;
        return this;
    }

    public Point2D add(Point2D other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Point2D rotate(double angle){
        double x = (this.x * Math.cos(angle) - this.y * Math.sin(angle));
        double y = (this.x * Math.sin(angle) + this.y * Math.cos(angle));
        this.x = x;
        this.y = y;
        return this;
    }
}
