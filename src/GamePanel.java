import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 800;
    public static int HEIGHT = 800;
    private Thread thread;
    private BufferedImage image; //переменная нашего холста на котором будем рисловать
    private Graphics2D g; //переменная кисточка
    public GameBack backGround;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;

    private int FPS;
    private double millisPerFrame;//миллисеккунд чтобы получить фпс
    private long timerFPS;
    private int sleepTime;

    //Constructor
    public GamePanel() {
        super(); //конструктор родителя (JPanel)

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());//добавляем в конструктор панели слушателя клавиатуры
        addMouseListener(new Listeners());//добавляем в конструктор панели слушателя мыши
    }

    //Functions
    public void startThread() {
        thread = new Thread(this);//объект thread с переопределенным run()
        thread.start();// старт потока
    }

    @Override
    public void run() {
        FPS = 60;
        millisPerFrame =  1000 / FPS; //сколько миллисекунд на отрисовку 1 кадра
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//способ обработки цвета холста
        g = (Graphics2D) image.getGraphics(); //привязываем к кисточке холст; g наследник Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // сглаживание

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        wave = new Wave();

        backGround = new GameBack();//инициализируем задний фон
        player = new Player();// инициализируем плеера

        while (true) {// TODO States
            //инициализация таймера ФПС
            timerFPS = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();


            //таймеры чтобы не зависимо от длительности цикла всегда получалось 60 фпс
            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if(millisPerFrame > timerFPS) {
                sleepTime = (int) (millisPerFrame - timerFPS);
            } else sleepTime = 1;
                try {
                Thread.sleep(sleepTime); //TODO FPS
                    System.out.println(FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                timerFPS = 0;
                sleepTime = 1;
        }
    }

    public void gameUpdate() { // обновляет состояния
        //BackGround update
        backGround.update();
        //Player update
        player.update();
        //Bullets update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) { //если пуля выходит за прелеы
                bullets.remove(i);//удаляем пулю из списка
//                i--;//переводим счетчик назад
            }
        }
        //Enemies update
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        //Bullets-enemies collides
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();//получаем координаты врага по икс
            double ey = e.getY();//получаем координаты по Y

            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();///получаем координаты по икс
                double by = b.getY();//получаем координаты по Y

                double dx = ex - bx;//difference (разница медну врагом и пулей)
                double dy = ey - by;//difference (разница медну врагом и пулей)

                double dist = Math.sqrt(dx * dx + dy * dy);//Дистанция

                if ((int) dist <= e.getR() + b.getR()) {//если дистанция между врагом и пулей меньше
                    e.hit();                          //чем сумма радиусов врага и пули, то есть попадание
                    bullets.remove(j);                  //удаляем пулю и выходим из цикла
                    boolean remove = e.remove();//проверяем врага, если health =< 0, то удаляем
                    if (remove) {                 //если
                        enemies.remove(i);
                        i--;
                    }
                    break;
                }
            }
        }
        //Player-enemy collides
        for (int i = 0; i < enemies.size(); i++) {//цикл по врагам
            Enemy e = enemies.get(i);
            double eX = e.getX();
            double eY = e.getY();

            double pX = player.getX();//координаты плеера
            double pY = player.getY();

            double dX = eX - pX;//difference (разница медну врагом и пулей)
            double dY = eY - pY;//difference (разница медну врагом и пулей)

            double distance = Math.sqrt(dX * dX + dY * dY); //Дистанция

            if ((int) distance <= e.getR() + player.getR()) {
                e.hit();
                player.hit();
                boolean remove = e.remove();//проверяем врага, если health =< 0, то удаляем
                if (remove) {
                    enemies.remove(i);
                    i--;
                }
            }
        }
        //Wave update
        wave.update();
    }

    public void gameRender() { // обновляет картинку
        //BackGround draw
        backGround.draw(g);
        //Player draw
        player.draw(g);
        //Bullets draw
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        //Draw enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        //Draw wave
        if(wave.showWave())//если надо показывать то пишем на экране текст
        wave.draw(g);
    }

    private void gameDraw() { // передаем изображение в нашу компоненту
        Graphics g2 = this.getGraphics(); // выводим на JPanel нарисованные элементы
        g2.drawImage(image, 0, 0, null);
        g2.dispose(); // убрать сборщиком мусора переменную g2, которую мы уже нарисовали
    }
}
