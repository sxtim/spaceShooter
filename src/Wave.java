import java.awt.*;

public class Wave {
    //Fields
    private int waveNumber;
    private int waveMultiplier;//множитель врагов
    private String waveText;


    private long waveTimer;//Timers
    private long waveDelay;//Timers
    private long waveTimerDiff;//Timers


    //Constructor
    public Wave() {
        waveText = "WAVE: ";
        waveNumber = 1;
        waveMultiplier = 7;

        waveTimer = 0;
        waveDelay = 1000; //orig 5000
        waveTimerDiff = 0;
    }


    //Functions
    public void createEnemies() {
        int enemyCount = waveNumber * waveMultiplier;
        if (waveNumber < 2) {//до 4 волны создаем определенный алгоритм
            while(enemyCount > 0) {//создаем врагов
                int rank = 1;
                int type = Math.random() > 0.3 ? Enemy.TYPE_METEOR : Enemy.TYPE_FIGTHER;
//                int type = Enemy.TYPE_FIGTHER;
                GamePanel.enemies.add(new Enemy(type, rank));

                System.out.println(" create meteor ");

                enemyCount -= type * rank;
            }
        }
        waveNumber++;
    }


    public void update() {//необходимо узнать нужно лди запускать волну
        if (GamePanel.enemies.size() == 0 && waveTimer == 0)//если список врагов пуст и таймер волны равен 0
            waveTimer = System.nanoTime();//устанавливаем таймер волны в соответствии с текущем временем

        if (waveTimer > 0) {//если таймер запущен
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000; //приводим к миллисикундам
            waveTimer = System.nanoTime();
        }
        //после каждой волны таймер будет пропускать 5 сек и снова создавать врагов
        if (waveTimerDiff > waveDelay) {
            createEnemies();
            //обнуляем таймеры
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }

    public boolean showWave(){//показываем до тех пор пока не обнулился таймер
        return waveTimer != 0;
    }


    public void draw(Graphics2D g) {
        double divider = waveDelay / 180D;//рисуем с эффектом
        double alpha = waveTimerDiff / divider;//рисуем с эффектом
        alpha = 255 * Math.sin(Math.toRadians(alpha));//рисуем с эффектом
        if(alpha < 0) alpha = 0;//корректируем alpha
        if(alpha > 255) alpha = 255;//корректируем alpha
        g.setFont(AddFont.createFontSpaceHorizon(50));
        g.setColor(new Color(200,52,200,(int) alpha));//рисуем с эффектом
        String s = waveText + waveNumber;
        long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth(); //получаем длину текста в пикселях

        g.drawString(s, GamePanel.WIDTH / 2 - (int) length / 2, GamePanel.HEIGHT / 2);
    }


}
