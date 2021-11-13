import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet {

    public static final int TYPE_MY_BULLET = 1;
    public static final int TYPE_ENEMY_BULLET = 2;
    //Fields
    private Image imgBullet;
    private Point2D pos = new Point2D(0, 0);
    private double angle;
    private int r;
    private Point2D deltaPos;
    private double x;
    private double y;
    private double distX;
    private double distY;
    private double dist;
    public int type = TYPE_MY_BULLET;


    private Color color;


    //Constructor
    public Bullet(int type, double x, double y, double angle) {
        this.type = type;

        switch (type) {
            case TYPE_MY_BULLET:
                imgBullet = new ImageIcon("Image/bullets/bullet4.png").getImage();
                break;
            case TYPE_ENEMY_BULLET:
                imgBullet = new ImageIcon("Image/bullets/bullet1.png").getImage();
                break;
        }
        this.pos.set(x, y);
        //нач координаты пули - координаты героя
        x = pos.x;
        y = pos.y;
        distX = GamePanel.mousePos.x - x;// разница по Х от мыши до пули
        distY = y - GamePanel.mousePos.y;// разница по Y от мыши до пули

        dist = (Math.sqrt(distX * distX + distY * distY));//расстояние от мыши до пули

        r = 2;
        deltaPos = new Point2D(10, 0).rotate(angle);
//        System.out.println("bullet create with angle=" + angle + " actualAngle=" + deltaPos.angle());
        color = Color.YELLOW;
    }

    //Functions
    public void update() {
        pos.add(deltaPos);

    }

    //проверка не улетела ли пуля за экран
    public boolean remove() {
        return pos.y < 0 || pos.y > GamePanel.HEIGHT || pos.x < 0 || pos.x > GamePanel.WIDTH;
    }


    public void draw(Graphics2D g){
        Point2D nextPos = pos.copy().add(deltaPos.copy().multiple(0));
        AffineTransform origForm; //создаем объект класса AffineTransform
        origForm = g.getTransform();//получаем текущее значение
        AffineTransform newForm = (AffineTransform) (origForm.clone());//клонируем текущее значение
      if(distX > 0)  newForm.rotate(Math.acos(distY/(dist)), pos.x, pos.y);//вертим полученное изображение относительно X и Y
      if(distX < 0)  newForm.rotate(-Math.acos(distY/(dist)), pos.x, pos.y);//вертим полученное изображение относительно X и Y
        g.setTransform(newForm);//ставим трансформированное изображение
        g.setTransform(origForm);//возвращаем старое значение
        g.drawImage(imgBullet, (int) nextPos.x - 4* r, (int) nextPos.y - 3 *r, null);


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
