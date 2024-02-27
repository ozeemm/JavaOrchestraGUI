import javax.swing.*;
// import javax.swing.ImageIcon; Чтобы добавить иконку
import java.awt.*;

public class TestGUI extends JFrame {
    private JPanel mainMenuPanel;
    private JPanel settingsPanel;

    public TestGUI(){
        super("Pocket Orchestra"); // Окно с названием
        initWindow();
        initMainMenu();
        initSettingsMenu();

        this.add(mainMenuPanel);
        this.revalidate();
    }

    private void initWindow() {
        setSize(800, 600); // Размер окна
        setResizable(false); // Неизменяемый размер окна
        setLocationByPlatform(true); // Чтобы открылось в удобном месте
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
    }

    private void initMainMenu(){
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(Color.ORANGE);
        //mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.setLayout(new GridLayout(6, 1, 5, 15)); // Рядов, колонок, расстояние между объектами
        //this.add(mainMenuPanel, BorderLayout.CENTER);

        JLabel header = new JLabel();
        header.setText("Оркестр \"Название\"");
        header.setFont(new Font("Arial", Font.BOLD, 50));
        mainMenuPanel.add(header);

        JButton playButton = new JButton("Сыграть всем оркестром");
        playButton.setFont(new Font("Arial", Font.PLAIN, 30));
        mainMenuPanel.add(playButton);

        JButton musiciansButton = new JButton("Список музыкантов");
        musiciansButton.setFont(new Font("Arial", Font.PLAIN, 30));
        mainMenuPanel.add(musiciansButton);

        JButton instrumentsButton = new JButton("Список инструментов");
        instrumentsButton.setFont(new Font("Arial", Font.PLAIN, 30));
        mainMenuPanel.add(instrumentsButton);

        JButton settingsButton = new JButton("Настройки");
        settingsButton.setFont(new Font("Arial", Font.PLAIN, 30));
        settingsButton.addActionListener(e -> {switchPanels(settingsPanel, mainMenuPanel);});
        mainMenuPanel.add(settingsButton);

        JButton exitButton = new JButton("Выход");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        exitButton.addActionListener(e -> { System.exit(0); }); // Функция кнопки
        mainMenuPanel.add(exitButton);
    }

    private void initSettingsMenu(){
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        //settingsPanel.setLayout(new GridLayout(0, 1));
        //this.add(settingsPanel, BorderLayout.CENTER);
        //settingsPanel.setVisible(false);

        // === header panel ===
        JPanel headerPanel = new JPanel();
        // label
        JLabel headerLabel = new JLabel("Настройки");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 35));
        // panels
        headerPanel.add(headerLabel);
        settingsPanel.add(headerPanel);

        // === orchestra panel ===
        JPanel orchestraNamePanel = new JPanel();
        // label
        JLabel orchestraNameLabel = new JLabel("Название оркестра:");
        // input
        JTextField orchestraNameInput = new JTextField(15);
        orchestraNameInput.setText("Солнышко");
        // panels
        orchestraNamePanel.add(orchestraNameLabel);
        orchestraNamePanel.add(orchestraNameInput);
        settingsPanel.add(orchestraNamePanel);

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
        JTextField songSoundsDelayInput = new JTextField(3);
        // panels
        songSoundsDelayPanel.add(songSoundsDelayLabel);
        songSoundsDelayPanel.add(songSoundsDelayInput);
        settingsPanel.add(songSoundsDelayPanel);

        // === buttons panel ===
        JPanel buttonsPanel = new JPanel();
        // buttons
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            System.out.println(orchestraNameInput.getText());
            System.out.println(noteFormatInput.getSelectedIndex());
            System.out.println(minSongSoundsInput.getText());
            System.out.println(maxSongSoundsInput.getText());
            System.out.println(songSoundsDelayInput.getText());
        });
        JButton resetButton = new JButton("Сбросить");
        resetButton.addActionListener(e -> {
            orchestraNameInput.setText("");
            noteFormatInput.setSelectedIndex(0);
            minSongSoundsInput.setText("");
            maxSongSoundsInput.setText("");
            songSoundsDelayInput.setText("");
        });
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> { switchPanels(mainMenuPanel, settingsPanel); });
        // panels
        buttonsPanel.add(saveButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(backButton);
        settingsPanel.add(buttonsPanel);
    }

    private void switchPanels(JPanel panelToOpen, JPanel panelToClose){
        this.remove(panelToClose);
        this.add(panelToOpen);

        this.revalidate();
        this.repaint();
    }
}
