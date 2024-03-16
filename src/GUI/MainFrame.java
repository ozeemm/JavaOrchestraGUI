package GUI;

import Logic.*;

import javax.swing.*;
import javax.swing.ImageIcon; // Чтобы добавить иконку
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private static Orchestra orchestra;
    public static void setOrchestra(Orchestra orch){ orchestra = orch; }
    public static Orchestra getOrchestra(){ return orchestra; }
    public MainFrame(){
        super("Pocket Orchestra"); // Окно с названием
        setSize(950, 750); // Размер окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        ImageIcon icon = new ImageIcon("./img/icon2.png");
        this.setIconImage(icon.getImage()); // Иконка окна
    }
}
