import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 400;
    public static int HEIGHT = 400;
    Thread thread = new Thread(this);//вызывает @Override метод run

    //Constructor
    public GamePanel(){
        super(); //конструктор родителя (JPanel)

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        thread.start();// старт потока


    }
    //Functions
    @Override
    public void run() {
        while(true){// TODO States
            gameUpdate();
            gameRender();

            try {
                thread.sleep(33); //TODO FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void gameUpdate(){ // обновляет состояния

    }

    public void gameRender(){ // обновляет картинку

    }
}
