import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 400;
    public static int HEIGHT = 400;
    private Thread thread;
    private BufferedImage image; //переменная нашего холста на котором будем рисловать
    private Graphics2D g; //переменная кисточка
    private GameBack backGround;


    //Constructor
    public GamePanel() {
        super(); //конструктор родителя (JPanel)

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    //Functions
    public void startThread() {
        thread = new Thread(this);//объект thread с переопределенным run()
        thread.start();// старт потока
    }

    @Override
    public void run() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//способ обработки цвета холста
        g = (Graphics2D) image.getGraphics(); //привязываем к кисточке холст; g наследник Graphics2D
        backGround = new GameBack();//инициализируем задний фон

        while (true) {// TODO States
            gameUpdate();
            gameRender();
            gameDraw();

            try {
                thread.sleep(33); //TODO FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void gameUpdate() { // обновляет состояния
        //BackGround update
        backGround.update();

    }

    public void gameRender() { // обновляет картинку
        //BackGround update
        backGround.draw(g);
    }

    private void gameDraw() { // передаем изображение в нашу компоненту
        Graphics g2 = this.getGraphics(); // выводим на JPanel нарисованные элементы
        g2.drawImage(image, 0, 0, null);
        g2.dispose(); // убрать сборщиком мусора переменную g2, которую мы уже нарисовали
    }
}
