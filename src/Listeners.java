import java.awt.event.*;

public class Listeners extends MouseAdapter implements KeyListener, MouseListener,  MouseMotionListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        onKeyPress(true, e);

    }

    public void onKeyPress(boolean pressed, KeyEvent e) {
        int key = e.getKeyCode();
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
        if (key == KeyEvent.VK_ESCAPE) {
            GamePanel.state = GamePanel.STATES.MENU;
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        onKeyPress(false, e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("кнопка нажата ");
        System.out.println("=========================");

    }

    public void mouseMoved(MouseEvent e) {//если двигаем
        //получаем координаты мыши
        GamePanel.mousePos.set(e.getX(), e.getY());
    }


    @Override
    public void mouseDragged(MouseEvent e){//если что то тащим
        //получаем координаты мыши
        GamePanel.mousePos.set(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e){
        Player.isFiring = true;
        GamePanel.leftMouse = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GamePanel.leftMouse = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
