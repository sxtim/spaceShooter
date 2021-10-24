import java.awt.event.*;

public class Listeners extends MouseAdapter implements KeyListener, MouseListener {

    @Override
    public void keyPressed(KeyEvent e) {
        onKeyPress(true, e);   
    }

    public void onKeyPress(boolean pressed, KeyEvent e) {
        if (key == KeyEvent.VK_W)
            Player.up = pressed;

        if (key == KeyEvent.VK_S)
            Player.down = pressed;

        if (key == KeyEvent.VK_A)
            Player.left = pressed;

        if (key == KeyEvent.VK_D)
            Player.right = pressed;

        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = pressed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        onKeyPress(false, e);
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
