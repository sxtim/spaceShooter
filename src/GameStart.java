import javax.swing.*;

public class GameStart {
    public static void main(String[] args) {
        JFrame startFrame = new JFrame("BubbleShooter");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(new GamePanel());//добавляем панель на фрейм
        startFrame.pack(); // окно занимает столько сколько его компненты
        startFrame.setLocationRelativeTo(null); // устанавливает по центру экрана
        startFrame.setVisible(true);
    }

}
