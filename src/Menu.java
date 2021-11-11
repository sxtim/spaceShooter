import java.awt.*;

public class Menu {
    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color;
    private String text;
    private int transp; //прозрачность


    //Constructor
    public Menu() {
        buttonWidth = 240;
        buttonHeight = 120;
        color = Color.WHITE;
        text = "PLAY";
    }


    //Functions
    public void update() {//по левому краю
        if (GamePanel.mousePos.x > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                //по правому краю
                GamePanel.mousePos.x < GamePanel.WIDTH / 2 + buttonWidth / 2 &&
                //по верхнему краю
                GamePanel.mousePos.y > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                //по нижнему краю
                GamePanel.mousePos.y < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
                transp = 60;
                if(GamePanel.leftMouse){
                    GamePanel.state = GamePanel.STATES.PLAY;
                    GamePanel.leftMouse = false;
                }
        }else{
            transp = 0;
        }

    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));//толщина контуров кнопки в 3 пикселя
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);

        g.setColor(new Color(255,255,255,transp));

        g.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);

        g.setStroke(new BasicStroke(1));//возвращаем толщину контцров кнопки

        g.setColor(color);
        g.setFont(AddFont.createFontSpaceHorizon(40));

//        g.setFont(new Font("Consolas", Font.BOLD, 60));
        long length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();//определяем длинну надписи, чтоб разместить ее по центру
        g.drawString(text, (int) (GamePanel.WIDTH / 2 - length / 2), (int) GamePanel.HEIGHT / 2 + buttonHeight / 5);

    }


}
