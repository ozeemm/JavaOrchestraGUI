package View;

import Model.OrchestraSettings;

import javax.swing.*;
import java.awt.*;
import Controller.*;

public class SettingsPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel orchestraNamePanel;
    private JPanel noteFormatPanel;
    private JPanel songSoundsDelayPanel;
    private JPanel buttonsPanel;

    private JLabel headerLabel;
    private JLabel orchestraNameLabel;
    private JLabel noteFormatLabel;

    private JLabel songSoundsDelayLabel;

    private JTextField orchestraNameInput;
    private JTextField songSoundsDelayInput;

    private JComboBox<String> noteFormatInput;

    private JButton saveButton;
    private JButton resetButton;
    private JButton backButton;

    private OrchestraSettings orchestraSettings;

    public SettingsPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        orchestraNamePanel = new JPanel();
        noteFormatPanel = new JPanel();
        songSoundsDelayPanel = new JPanel();
        buttonsPanel = new JPanel();

        headerLabel = new JLabel("Настройки");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 35));
        headerPanel.add(headerLabel);

        orchestraNameLabel = new JLabel("Название оркестра:");
        orchestraNameInput = new JTextField(15);
        orchestraNamePanel.add(orchestraNameLabel);
        orchestraNamePanel.add(orchestraNameInput);

        noteFormatLabel = new JLabel("Формат нот:");
        noteFormatInput = new JComboBox(new String[] {"CDEFGAB", "До-Ре-Ми-Фа-Соль-Ля-Си"});
        noteFormatPanel.add(noteFormatLabel);
        noteFormatPanel.add(noteFormatInput);

        songSoundsDelayLabel = new JLabel("Задержка между звуками в песне (в миллисекундах):");
        songSoundsDelayInput = new JTextField(3);
        songSoundsDelayPanel.add(songSoundsDelayLabel);
        songSoundsDelayPanel.add(songSoundsDelayInput);

        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> { saveSettings(); });
        resetButton = new JButton("Сбросить");
        resetButton.addActionListener(e -> { resetSettings(); });
        backButton = new JButton("Назад");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(backButton);

        this.add(headerPanel);
        this.add(orchestraNamePanel);
        this.add(noteFormatPanel);
        this.add(songSoundsDelayPanel);
        this.add(buttonsPanel);
    }

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getSaveButton(){ return saveButton; }

    public void showSettingsValues(OrchestraSettings settings){
        orchestraSettings = settings;
        orchestraNameInput.setText(settings.getName());
        noteFormatInput.setSelectedIndex(settings.getIsRuNotes() ? 1 : 0);
        songSoundsDelayInput.setText(Integer.toString(settings.getSongSoundsDelay()));
    }

    public void resetSettings(){
        showSettingsValues(orchestraSettings);
    }

    public void saveSettings(){
        orchestraSettings = new OrchestraSettings();

        orchestraSettings.setName(orchestraNameInput.getText());
        orchestraSettings.setIsRuNotes(noteFormatInput.getSelectedIndex() == 1);
        orchestraSettings.setSongSoundsDelay(Integer.parseInt(songSoundsDelayInput.getText()));
        Controller.getOrchestra().setSettings(orchestraSettings);
    }
}
