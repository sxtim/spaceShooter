import java.awt.event.*;

public class Listeners extends MouseAdapter implements KeyListener, MouseListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();// числовое значение клавиши нажатой на клавиатуре

        if (key == KeyEvent.VK_W)
            Player.up = true;

        if (key == KeyEvent.VK_S)
            Player.down = true;

        if (key == KeyEvent.VK_A)
            Player.left = true;

        if (key == KeyEvent.VK_D)
            Player.right = true;
        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();// числовое значение клавиши нажатой на клавиатуре

        if (key == KeyEvent.VK_W)
            Player.up = false;

        if (key == KeyEvent.VK_S)
            Player.down = false;

        if (key == KeyEvent.VK_A)
            Player.left = false;

        if (key == KeyEvent.VK_D)
            Player.right = false;

        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = false;
        }

    }

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
            Player.isFiring = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
