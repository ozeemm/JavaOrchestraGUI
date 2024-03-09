package OrchestraGUI_package;

import Orchestra_package.Orchestra;
import Orchestra_package.OrchestraSettings;

import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends JPanel {
    private JPanel headerPanel;
    private JPanel orchestraNamePanel;
    private JPanel noteFormatPanel;
    private JPanel minSongSoundsPanel;
    private JPanel maxSongSoundsPanel;
    private JPanel songSoundsDelayPanel;
    private JPanel buttonsPanel;

    private JLabel headerLabel;
    private JLabel orchestraNameLabel;
    private JLabel noteFormatLabel;

    private JLabel minSongSoundsLabel;
    private JLabel maxSongSoundsLabel;
    private JLabel songSoundsDelayLabel;

    private JTextField orchestraNameInput;
    private JTextField minSongSoundsInput;
    private JTextField maxSongSoundsInput;
    private JTextField songSoundsDelayInput;

    private JComboBox<String> noteFormatInput;

    private JButton saveButton;
    private JButton resetButton;
    private JButton backButton;

    private OrchestraSettings orchestraSettings;

    public SettingsGUI(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        orchestraNamePanel = new JPanel();
        noteFormatPanel = new JPanel();
        minSongSoundsPanel = new JPanel();
        maxSongSoundsPanel = new JPanel();
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

        minSongSoundsLabel = new JLabel("Минимальное количество звуков в песне:");
        minSongSoundsInput = new JTextField(2);
        minSongSoundsPanel.add(minSongSoundsLabel);
        minSongSoundsPanel.add(minSongSoundsInput);

        maxSongSoundsLabel = new JLabel("Максимальное количество звуков в песне:");
        maxSongSoundsInput = new JTextField(2);
        maxSongSoundsPanel.add(maxSongSoundsLabel);
        maxSongSoundsPanel.add(maxSongSoundsInput);

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
        this.add(minSongSoundsPanel);
        this.add(maxSongSoundsPanel);
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
        minSongSoundsInput.setText(Integer.toString(settings.getMinSongSounds()));
        maxSongSoundsInput.setText(Integer.toString(settings.getMaxSongSounds()));
        songSoundsDelayInput.setText(Integer.toString(settings.getSongSoundsDelay()));
    }

    public void resetSettings(){
        showSettingsValues(orchestraSettings);
    }

    public void saveSettings(){
        orchestraSettings = new OrchestraSettings();

        orchestraSettings.setName(orchestraNameInput.getText());
        orchestraSettings.setIsRuNotes(noteFormatInput.getSelectedIndex() == 1);
        orchestraSettings.setMinSongSounds(Integer.parseInt(minSongSoundsInput.getText()));
        orchestraSettings.setMaxSongSounds(Integer.parseInt(maxSongSoundsInput.getText()));
        orchestraSettings.setSongSoundsDelay(Integer.parseInt(songSoundsDelayInput.getText()));
        GUIController.getOrchestra().setSettings(orchestraSettings);
    }
}
