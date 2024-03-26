package View;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    
    private final Font buttonsFont = new Font("Arial", Font.PLAIN, 27);
    private final int buttonsSpacing = 25;

    private JPanel buttonsPanel;
    private JLabel header;
    private JButton playButton;
    private JButton musiciansButton;
    private JButton instrumentsButton;
    private JButton settingsButton;
    private JButton exitButton;

    public MainMenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        header = new JLabel();
        header.setFont(new Font("Arial", Font.BOLD, 50));
        header.setAlignmentX(CENTER_ALIGNMENT);

        buttonsPanel = new JPanel(new GridLayout(0, 1, buttonsSpacing, buttonsSpacing));
        buttonsPanel.setMaximumSize(new Dimension(500, 400));

        playButton = new JButton("Сыграть всем оркестром");
        playButton.setFont(buttonsFont);
        playButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.add(playButton);

        musiciansButton = new JButton("Список музыкантов");
        musiciansButton.setFont(buttonsFont);
        musiciansButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.add(musiciansButton);

        instrumentsButton = new JButton("Список инструментов");
        instrumentsButton.setFont(buttonsFont);
        instrumentsButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.add(instrumentsButton);

        settingsButton = new JButton("Настройки");
        settingsButton.setFont(buttonsFont);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.add(settingsButton);

        exitButton = new JButton("Выход");
        exitButton.setFont(buttonsFont);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> { System.exit(0); });
        buttonsPanel.add(exitButton);

        this.add(Box.createVerticalStrut(buttonsSpacing * 2));
        this.add(header);
        this.add(Box.createVerticalStrut(buttonsSpacing * 2));
        this.add(buttonsPanel);
        this.add(Box.createVerticalStrut(buttonsSpacing));
    }
    public void setHeaderOrchestraName(String name){
        header.setText("Оркестр \"" + name + "\"");
    }
    public JButton getPlayButton(){
        return playButton;
    }
    public JButton getInstrumentsButton(){
        return instrumentsButton;
    }
    public JButton getMusiciansButton(){
        return musiciansButton;
    }
    public JButton getSettingsButton(){
        return settingsButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }
}
