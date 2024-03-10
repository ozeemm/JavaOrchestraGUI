package OrchestraGUI_package;

import javax.swing.*;
import java.awt.*;

public class MainMenuGUI extends JPanel {
    
    private final Font buttonsFont = new Font("Arial", Font.PLAIN, 27);
    private final int buttonsSpacing = 25;

    private JLabel header;
    private JButton playButton;
    private JButton musiciansButton;
    private JButton instrumentsButton;
    private JButton settingsButton;
    private JButton exitButton;
    public MainMenuGUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        header = new JLabel();
        header.setFont(new Font("Arial", Font.BOLD, 50));
        header.setAlignmentX(CENTER_ALIGNMENT);

        playButton = new JButton("Сыграть всем оркестром");
        playButton.setFont(buttonsFont);
        playButton.setAlignmentX(CENTER_ALIGNMENT);

        musiciansButton = new JButton("Список музыкантов");
        musiciansButton.setFont(buttonsFont);
        musiciansButton.setAlignmentX(CENTER_ALIGNMENT);

        instrumentsButton = new JButton("Список инструментов");
        instrumentsButton.setFont(buttonsFont);
        instrumentsButton.setAlignmentX(CENTER_ALIGNMENT);

        settingsButton = new JButton("Настройки");
        settingsButton.setFont(buttonsFont);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);

        exitButton = new JButton("Выход");
        exitButton.setFont(buttonsFont);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> { System.exit(0); });

        this.add(Box.createVerticalStrut(buttonsSpacing * 2));
        this.add(header);
        this.add(Box.createVerticalStrut(buttonsSpacing * 2));
        this.add(playButton);
        this.add(Box.createVerticalStrut(buttonsSpacing));
        this.add(musiciansButton);
        this.add(Box.createVerticalStrut(buttonsSpacing));
        this.add(instrumentsButton);
        this.add(Box.createVerticalStrut(buttonsSpacing));
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(buttonsSpacing));
        this.add(exitButton);
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
