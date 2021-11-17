import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 1800;
    public static int HEIGHT = 980;
    public static STATES state = STATES.MENU;//по умолчанию хотели бы попасть в меню

    private int FPS;
    private double millisPerFrame;//миллисеккунд чтобы получить фпс
    private long timerFPS;
    private int sleepTime;

    public static Point2D mousePos = new Point2D(0, 0);//координаты мыши
    public static boolean leftMouse;

    private Thread thread;
    private BufferedImage image; //переменная нашего холста на котором будем рисовать
    private Graphics2D g; //переменная кисточка
    public GameBack backGround;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static ArrayList<Explosion> explosions;
    public static ArrayList<ExplosionHit> explosionHits;
    public static ArrayList<ExplosionBigSize> explosionBigSizes;
    public static ArrayList<PowerUp> powerUps;
    public static Wave wave;
    public static Menu menu;

    public static enum STATES {
        MENU,
        PLAY
    }

    public void customCursor() {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("Image/crosshairs/crosshair1.png");
        Point p = new Point(11, 11);
        Cursor c = t.createCustomCursor(img, p, "Image/crosshairs/crosshair1.png");
        setCursor(c);
    }


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
        explosions = new ArrayList<>();
        explosionHits = new ArrayList<>();
        explosionBigSizes = new ArrayList<>();
        powerUps = new ArrayList<>();
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

            }
            if (state.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

            //таймеры чтобы не зависимо от длительности цикла всегда получалось 60 фпс
            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millisPerFrame > timerFPS) {
                sleepTime = (int) (millisPerFrame - timerFPS);
            } else sleepTime = 0;
            try {
                Thread.sleep(sleepTime); //TODO FPS

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
            Bullet b = bullets.get(i);
            b.update();
            if(b.isDeadMine()){
                explosionBigSizes.add(new ExplosionBigSize(b.getX(), b.getY()));
            }
            boolean remove = bullets.get(i).remove();
            if (remove) { //если пуля выходит за прелеы
                bullets.remove(i);//удаляем пулю из списка
                i--;//переводим счетчик назад
            }
        }
        //Enemies update
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();
        }

        //PowerUp update
        for (int i = 0; i < powerUps.size(); i++) {
            boolean remove = powerUps.get(i).update();
            if (remove) {
                powerUps.remove(i);
                i--;
            }
        }

        //Bullets-enemies collision
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();//получаем координаты врага по икс
            double ey = e.getY();//получаем координаты по Y

            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                //Bullet - player collision
                if (b.type == Bullet.TYPE_ENEMY_BULLET || b.type == Bullet.TYPE_ENEMY_MINE) {
                    Bullet bulletTypeEnemy = bullets.get(j);

                    double bulletTypeEnemyX = b.getX();///получаем координаты по икс
                    double bulletTypeEnemyY = b.getY();//получаем координаты по Y

                    double distX = player.getX() - bulletTypeEnemyX;//difference (разница между врагом и пулей)
                    double distY = player.getY() - bulletTypeEnemyY;//difference (разница между врагом и пулей)

                    double dist = Math.sqrt(distX * distX + distY * distY);//Дистанция
                    if((int) dist <= player.getR() + bulletTypeEnemy.getR() || dist <= player.getR() + bulletTypeEnemy.getRMineExplosion()){
                        if(b.type == Bullet.TYPE_ENEMY_MINE) {
                            explosionBigSizes.add(new ExplosionBigSize(b.getX(), b.getY()));
                        }

                        explosionHits.add(new ExplosionHit(player.getX(), player.getY()));
                        bullets.remove(j);

                        //Отскок
                        bulletTypeEnemy.hit(player);

                        if(!player.isRecovering()){
                            System.out.println("Hit PLAYER");
                            player.loseLife();
                        }
                    }
                    continue; //TODO HANDLE
                }
                double bx = b.getX();///получаем координаты по икс
                double by = b.getY();//получаем координаты по Y

                double dx = ex - bx;//difference (разница между врагом и пулей)
                double dy = ey - by;//difference (разница между врагом и пулей)

                double dist = Math.sqrt(dx * dx + dy * dy);//Дистанция
                //Registration hit of meteor
                if ((int) dist <= e.getR() + b.getR()) {//если дистанция между врагом и пулей меньше
//                    System.out.println("register hit of meteor");
                        // чем сумма радиусов врага и пули, то есть попадание
                    explosionHits.add(new ExplosionHit(e.getX(), e.getY()));
                    e.hit(true, null);
                    bullets.remove(j); //удаляем пулю
                    //Check dead enemies
                    if (e.isDead()) {
                        //Chance for powerUp
                        double rand = Math.random();
                        System.out.println(rand);
                        if (rand < 0.001) powerUps.add(new PowerUp(1, e.getX(), e.getY()));
                        else if (rand < 0.020) powerUps.add(new PowerUp(1, e.getX(), e.getY()));
                        else if (rand < 0.120) powerUps.add(new PowerUp(1, e.getX(), e.getY()));
                        else powerUps.add(new PowerUp(1, e.getX(), e.getY()));

                        player.addScore(e.getType() + e.getRank());
                        enemies.remove(i);
                        i--;
                        e.explode();//деление метеоритов
                        explosions.add(new Explosion(e.getX(), e.getY()));
                    }
                    break;
                }
            }
        }
        //Player-enemy collision
        for (int i = 0; i < enemies.size(); i++) {//цикл по врагам
            Enemy e = enemies.get(i);
            double eX = e.getX();
            double eY = e.getY();

            double pX = player.getX();//координаты плеера
            double pY = player.getY();

            double dX = eX - pX;//difference (разница между плеером и врагом)
            double dY = eY - pY;

            double distance = Math.sqrt(dX * dX + dY * dY); //Дистанция

            //Check collision player and meteor
            if ((int) distance <= e.getR() + player.getR()) {
                    System.out.println("register hit of meteor");

                //отнимаем жизнь у метеора
                e.hit(false, player);
                //если плеер не в состоянии восстановления
                //отнимаем жизнь у плеера и создаем объект explosionHit
                explosionHits.add(new ExplosionHit(e.getX(), e.getY()));
                if (!player.isRecovering()) {
                    player.loseLife();

                }

                //Check dead meteor
                if (e.isDead()) {
                    //Chance for powerUp
                    double rand = Math.random();
                    if (rand < 0.001) powerUps.add(new PowerUp(1, e.getX(), e.getY()));
                    else if (rand < 0.020) powerUps.add(new PowerUp(3, e.getX(), e.getY()));
                    else if (rand < 0.120) powerUps.add(new PowerUp(2, e.getX(), e.getY()));
                    else powerUps.add(new PowerUp(1, e.getX(), e.getY()));
                    //Add score
                    player.addScore(e.getType() + e.getRank());
                    //Remove dead meteor
                    enemies.remove(i);
                    i--;
                    //разделяем метеор
                    e.explode();
                    explosions.add(new Explosion(e.getX(), e.getY()));
                }
            }
        }
        // Player-PowerUp collision
        double playerX = player.getX();
        double playerY = player.getY();
        double playerR = player.getR();
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            double powerUpX = powerUp.getX();
            double powerUpY = powerUp.getY();
            double powerUpR = powerUp.getR();
            double dx = playerX - powerUpX;
            double dy = playerY - powerUpY;
            double dist = Math.sqrt(dx * dx + dy * dy);
            // collected PowerUp
            if (dist < playerR + powerUpR) {
                int type = powerUp.getType();
                if (type == 1) {
                    player.increasePower(1);
                }
                if (type == 2) {
                    player.increasePower(1);
                }
                if (type == 3) {
                    player.increasePower(2);
                }


                powerUps.remove(i);
                i--;
            }


        }


        //Wave update
        wave.update();
        //Explosion update
        for (int i = 0; i < explosions.size(); i++) {
            boolean remove = explosions.get(i).update();
            if (remove) {
                explosions.remove(i);
                i--;
            }
        }
        //ExplosionSmall Update
        for (int i = 0; i < explosionHits.size(); i++) {
            boolean remove = explosionHits.get(i).update();
            if (remove) {
                explosionHits.remove(i);
                i--;
            }
        }
        //Explosion Big size update
        for (int i = 0; i < explosionBigSizes.size(); i++) {
            boolean remove = explosionBigSizes.get(i).update();
            if (remove) {
                explosionBigSizes.remove(i);
                i--;
            }
        }
    }

    public void gameRender() { // обновляет картинку
        //Custom cursor draw
        customCursor();
        //BackGround draw
        backGround.draw(g);

        //Player draw
        player.draw(g);
        //Player score draw
        g.setFont(AddFont.createFontSpaceHorizon(16));
        g.setColor(new Color(5,223,254,255));
        g.drawString("Score: " + player.getScore(), 1600, 30);
        //Bullets draw
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        //Bullets counter draw
        g.setFont(new Font("Consolas", Font.PLAIN, 18));
        g.setColor(new Color(5,223,254,255));
        g.drawString("Bullets counter = " + bullets.size(), 1560, 900);
        //Draw Meteor enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        //Meteors counter
        g.setFont(new Font("Consolas", Font.PLAIN, 18));
        g.setColor(new Color(5,223,254,255));
        g.drawString("Meteors counter = " + enemies.size(), 1560, 920);
//                    //Meteor health counter
//                    for (Enemy enemyMeteor : enemies) {
//                        g.setFont(new Font("Consolas", Font.PLAIN, 18));
//                        g.drawString("Health of meteor = " + enemyMeteor.getHeath(), 1560, 940);
//                    }

        //Draw wave
        if (wave.showWave())//если надо показывать то пишем на экране текст
            wave.draw(g);

        //PowerUp draw
        for (int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).draw(g);
        }
        //Explosion draw
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).draw(g);
        }
        //SmallExplosion draw
        for (int i = 0; i < explosionHits.size(); i++) {
            explosionHits.get(i).draw(g);
        }
        //Explosion big size draw
        for (int i = 0; i < explosionBigSizes.size(); i++) {
            explosionBigSizes.get(i).draw(g);
        }

    }

    private void gameDraw() { // передаем изображение в нашу компоненту
        Graphics g2 = this.getGraphics(); // выводим на JPanel нарисованные элементы
        g2.drawImage(image, 0, 0, null);
        g2.dispose(); // убрать сборщиком мусора переменную g2, которую мы уже нарисовали
    }
}
