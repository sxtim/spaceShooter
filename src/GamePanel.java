import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //Field
    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    //Constructor
    public GamePanel(){
        super(); //конструктор родителя (JPanel)

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();


    }

}
