import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 1800;
    public static int HEIGHT = 980;

    public static Point2D mousePos = new Point2D(0, 0);//координаты мыши
    public static boolean leftMouse;

    private Thread thread;
    private BufferedImage image; //переменная нашего холста на котором будем рисловать
    private Graphics2D g; //переменная кисточка
    public GameBack backGround;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<EnemyMeteor> enemies;
    public static Wave wave;
    public static Menu menu;

    public static enum STATES {
        MENU,
        PLAY
    }

    public void customCursor(){

        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("Image/crosshairs/crosshair1.png");
        Point p = new Point(11,11);
        Cursor c = t.createCustomCursor(img, p, "Image/crosshairs/crosshair1.png");
        setCursor(c);
    }

    public static STATES state = STATES.MENU;//по умолчанию хотели бы попасть в меню

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
        addMouseMotionListener(new Listeners());//добавляем слушателя движения мыши
    }

    //Functions
    public void startThread() {
        thread = new Thread(this);//объект thread с переопределенным run()
        thread.start();// старт потока
    }

    @Override
    public void run() {
        FPS = 120;
        millisPerFrame = (float) 1000 / FPS; //сколько миллисекунд на отрисовку 1 кадра
        sleepTime = 0;
        leftMouse = false;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//способ обработки цвета холста
        g = (Graphics2D) image.getGraphics(); //привязываем к кисточке холст; g наследник Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // сглаживание

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        wave = new Wave();
        menu = new Menu();




        player = new Player();// инициализируем плеера
        backGround = new GameBack(player);//инициализируем задний фон

        while (true) {// TODO States
            // инициализация таймера ФПС
            timerFPS = System.nanoTime();

            if (state.equals(STATES.MENU)) {//если мы в состоянии меню
                backGround.update();
                backGround.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
                System.out.println("mouse=" + mousePos);
            }
            if (state.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

//            //инициализация таймера ФПС
//            timerFPS = System.nanoTime();

//            gameUpdate();
//            gameRender();
//            gameDraw();


            //таймеры чтобы не зависимо от длительности цикла всегда получалось 60 фпс
            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millisPerFrame > timerFPS) {
                sleepTime = (int) (millisPerFrame - timerFPS);
            } else sleepTime = 0;
            try {
                Thread.sleep(sleepTime); //TODO FPS
//                    System.out.println(FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 0;
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
        for (EnemyMeteor enemyMeteor : enemies) {
            enemyMeteor.update();
        }
        //Bullets-enemies collides
        for (int i = 0; i < enemies.size(); i++) {
            EnemyMeteor e = enemies.get(i);
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
                    System.out.println("register hit of meteor");//чем сумма радиусов врага и пули, то есть попадание
                    e.hit(true, null);
                    bullets.remove(j);                  //удаляем пулю и выходим из цикла
                    boolean remove = e.isDead();//проверяем врага, если health =< 0, то удаляем
                    if (remove) {
                        enemies.remove(i);
                        i--;
                        e.explode();
                    }
                    break;
                }
            }
        }
        //Player-enemy collides
        for (int i = 0; i < enemies.size(); i++) {//цикл по врагам
            EnemyMeteor e = enemies.get(i);
            double eX = e.getX();
            double eY = e.getY();

            double pX = player.getX();//координаты плеера
            double pY = player.getY();

            double dX = eX - pX;//difference (разница медну врагом и пулей)
            double dY = eY - pY;//difference (разница медну врагом и пулей)

            double distance = Math.sqrt(dX * dX + dY * dY); //Дистанция

            if ((int) distance <= e.getR() + player.getR()) {
                if (e.hitCooldown == 0) {
                    player.hit();
                }
                e.hit(false, player);
                boolean remove = e.isDead();//проверяем врага, если health =< 0, то удаляем
                if (remove) {
                    enemies.remove(i);
                    i--;
                    e.explode();
                }
            }
        }
        //Wave update
        wave.update();
    }

    public void gameRender() { // обновляет картинку
        customCursor();
        //BackGround draw
        backGround.draw(g);
        //Player draw
        player.draw(g);
        //Bullets draw
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        //Draw enemies
        for (EnemyMeteor enemyMeteor : enemies) {
            enemyMeteor.draw(g);
        }
        //Draw wave
        if (wave.showWave())//если надо показывать то пишем на экране текст
            wave.draw(g);
    }

    private void gameDraw() { // передаем изображение в нашу компоненту
        Graphics g2 = this.getGraphics(); // выводим на JPanel нарисованные элементы
        g2.drawImage(image, 0, 0, null);
        g2.dispose(); // убрать сборщиком мусора переменную g2, которую мы уже нарисовали
    }
}
