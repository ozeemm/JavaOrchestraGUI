import javax.swing.*;
import java.awt.*;

public class TestGUISettings extends JFrame {
    public TestGUISettings(){
        super("Test GUI application"); // Окно с названием
        setSize(800, 600); // Размер окна
        setLocationByPlatform(true); // Чтобы открылось в удобном месте
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        //settingsPanel.setLayout(new GridLayout(0, 1));
        this.add(settingsPanel, BorderLayout.CENTER);

        // === orchestra panel ===
        JPanel orchestraPanel = new JPanel();
        // label
        JLabel orchestraNameLabel = new JLabel("Название оркестра:");
        // input
        JTextField orchestraNameInput = new JTextField(15);
        orchestraNameInput.setText("Солнышко");
        // panels
        orchestraPanel.add(orchestraNameLabel);
        orchestraPanel.add(orchestraNameInput);
        settingsPanel.add(orchestraPanel);

        // === note format panel ===
        JPanel noteFormatPanel = new JPanel();
        noteFormatPanel.setBackground(Color.pink);
        // label
        JLabel noteFormatLabel = new JLabel("Формат нот:");
        // input
        JComboBox noteFormatInput = new JComboBox(new String[] {"CDEFGAB", "До-Ре-Ми-Фа-Соль-Ля-Си"});
        // panels
        noteFormatPanel.add(noteFormatLabel);
        noteFormatPanel.add(noteFormatInput);
        settingsPanel.add(noteFormatPanel);

        // === min song sounds panel ===
        JPanel minSongSoundsPanel = new JPanel();
        // label
        JLabel minSongSoundsLabel = new JLabel("Минимальное количество звуков в песне:");
        // input
        JTextField minSongSoundsInput = new JTextField(2);
        // panels
        minSongSoundsPanel.add(minSongSoundsLabel);
        minSongSoundsPanel.add(minSongSoundsInput);
        settingsPanel.add(minSongSoundsPanel);

        // === max song sounds panel ===
        JPanel maxSongSoundsPanel = new JPanel();
        // label
        JLabel maxSongSoundsLabel = new JLabel("Максимальное количество звуков в песне:");
        // input
        JTextField maxSongSoundsInput = new JTextField(2);
        // panels
        maxSongSoundsPanel.add(maxSongSoundsLabel);
        maxSongSoundsPanel.add(maxSongSoundsInput);
        settingsPanel.add(maxSongSoundsPanel);

        // === song sounds delay panel ===
        JPanel songSoundsDelayPanel = new JPanel();
        // label
        JLabel songSoundsDelayLabel = new JLabel("Задержка между звуками в песне (в миллисекундах):");
        // input
        JTextField songSoundsDelayInput = new JTextField(4);
        // panels
        songSoundsDelayPanel.add(songSoundsDelayLabel);
        songSoundsDelayPanel.add(songSoundsDelayInput);
        settingsPanel.add(songSoundsDelayPanel);

        // === buttons panel ===
        JPanel buttonsPanel = new JPanel();
        // buttons
        JButton saveButton = new JButton("Сохранить");
        JButton resetButton = new JButton("Сбросить");
        JButton backButton = new JButton("Назад");
        // panels
        buttonsPanel.add(saveButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(backButton);
        settingsPanel.add(buttonsPanel);

        this.revalidate(); // Проверка корректности (Иначе TextField не видно)
    }
}
