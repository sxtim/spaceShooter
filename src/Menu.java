import java.awt.*;

public class Menu {
    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color;
    private String text;


    //Constructor
    public Menu() {
        buttonWidth = 240;
        buttonHeight = 120;
        color = Color.BLUE;
        text = "PLAY";
    }


    //Functions


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));//толщина контуров кнопки в 3 пикселя
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));//возвращаем толщину контцров кнопки

        g.setColor(color);
        g.setFont(new Font("Consolas", Font.BOLD, 60));
        long length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();//определяем длинну надписи, чтоб разместить ее по центру
        g.drawString(text, (int) (GamePanel.WIDTH / 2 - length / 2), (int) GamePanel.HEIGHT / 2 + buttonHeight / 4);

    }


}
