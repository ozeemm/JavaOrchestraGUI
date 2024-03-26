package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import javax.swing.ImageIcon; // Чтобы добавить иконку
import java.awt.*;

public class MainFrame extends JFrame {
    private MainMenuPanel mainMenuPanel;
    private SettingsPanel settingsPanel;
    private InfoPanel instrumentsInfoPanel;
    private InfoPanel musiciansInfoPanel;

    public MainMenuPanel getMainMenuPanel(){ return mainMenuPanel; }
    public SettingsPanel getSettingsPanel(){ return settingsPanel; }
    public InfoPanel getInstrumentsInfoPanel(){ return instrumentsInfoPanel; }
    public InfoPanel getMusiciansInfoPanel(){ return musiciansInfoPanel; }

    public MainFrame(){
        super("Pocket Orchestra"); // Окно с названием
        setSize(950, 750); // Размер окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        ImageIcon icon = new ImageIcon("./img/icon2.png");
        this.setIconImage(icon.getImage()); // Иконка окна

        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.setHeaderOrchestraName(Controller.getOrchestra().getSettings().getName());
        mainMenuPanel.getPlayButton().addActionListener(e -> { new PlayTogetherFrame(); });
        settingsPanel = new SettingsPanel();
        instrumentsInfoPanel = new InfoPanel(true);
        musiciansInfoPanel = new InfoPanel(false);

        this.add(mainMenuPanel);
        this.revalidate();
        this.repaint();
    }
}
