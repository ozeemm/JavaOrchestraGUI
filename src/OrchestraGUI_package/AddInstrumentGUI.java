package OrchestraGUI_package;

import javax.swing.*;
import java.util.ArrayList;

public class AddInstrumentGUI extends JFrame {
    private JPanel panel;
    private int instrumentType;
    private ArrayList<JComponent> inputComponents;
    private void initWindow(){
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        ImageIcon icon = new ImageIcon("./img/icon2.png");
        this.setIconImage(icon.getImage());
    }

    public AddInstrumentGUI(int instrumentType){
        super("Добавление инструмента");
        initWindow();
        this.instrumentType = instrumentType;
        inputComponents = new ArrayList<JComponent>();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        JLabel header = new JLabel();
        header.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(header);

        switch(instrumentType){
            case 0: // Струнные
                header.setText("Добавление струнного инструмента");
                initMusicInstrument();
                initNoteInstrumentInputElements();
                initStringInstrumentInputElements();
                break;
            case 1: // Клавишные
                header.setText("Добавление клавишного инструмента");
                initMusicInstrument();
                initNoteInstrumentInputElements();
                initKeyboardInstrumentInputElements();
                break;
            case 2: // Духовые
                header.setText("Добавление духового инструмента");
                initMusicInstrument();
                initNoteInstrumentInputElements();
                initWindInstrumentInputElements();
                break;
            case 3: // Безнотные
                header.setText("Добавление безнотного инструмента");
                initMusicInstrument();
                initNonNoteInstrumentInputElements(new String[]{ "Бочка", "Малый", "Открытый хэт", "Закрытый хэт", "Том-том" });
                break;
        }

        JButton addButton = new JButton("Добавить");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.addActionListener(e -> { buttn(); });
        JButton closeButton = new JButton("Закрыть");
        closeButton.setAlignmentX(CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> { dispose(); });

        panel.add(addButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(closeButton);

        panel.revalidate();
    }

    private void initMusicInstrument(){
        JPanel namePanel = new JPanel();
        JPanel isAcousticPanel = new JPanel();

        JLabel nameLabel = new JLabel("Название");
        JTextField nameInput = new JTextField(15);
        inputComponents.add(nameInput);
        namePanel.add(nameLabel);
        namePanel.add(nameInput);

        JLabel isAcousticLabel = new JLabel("Акустический ли инструмент");
        JCheckBox isAcousticCheckBox = new JCheckBox();
        inputComponents.add(isAcousticCheckBox);
        isAcousticPanel.add(isAcousticLabel);
        isAcousticPanel.add(isAcousticCheckBox);

        panel.add(namePanel);
        panel.add(isAcousticPanel);
    }
    private void initNoteInstrumentInputElements(){
        JPanel minNotePanel = new JPanel();
        JPanel maxNotePanel = new JPanel();

        JLabel minNoteLabel = new JLabel("Минимальная нота");
        JTextField minNoteInput = new JTextField(5);
        inputComponents.add(minNoteInput);
        minNotePanel.add(minNoteLabel);
        minNotePanel.add(minNoteInput);

        JLabel maxNoteLabel = new JLabel("Максимальная нота");
        JTextField maxNoteInput = new JTextField(5);
        inputComponents.add(maxNoteInput);
        maxNotePanel.add(maxNoteLabel);
        maxNotePanel.add(maxNoteInput);

        panel.add(minNotePanel);
        panel.add(maxNotePanel);
    }
    private void initStringInstrumentInputElements(){
        JPanel stringsPanel = new JPanel();
        JPanel fretsPanel = new JPanel();
        JPanel isBowPanel = new JPanel();
        JPanel chancePanel = new JPanel();

        JLabel stringsLabel = new JLabel("Количество струн");
        JTextField stringsInput = new JTextField(5);
        inputComponents.add(stringsInput);
        stringsPanel.add(stringsLabel);
        stringsPanel.add(stringsInput);

        JLabel fretsLabel = new JLabel("Количество ладов");
        JTextField fretsInput = new JTextField(5);
        inputComponents.add(fretsInput);
        fretsPanel.add(fretsLabel);
        fretsPanel.add(fretsInput);

        JLabel isBowLabel = new JLabel("Используется ли смычок");
        JCheckBox isBowCheckBox = new JCheckBox();
        inputComponents.add(isBowCheckBox);
        isBowPanel.add(isBowLabel);
        isBowPanel.add(isBowCheckBox);

        JLabel chanceLabel = new JLabel("Шанс лопания струны");
        JTextField chanceInput = new JTextField(5);
        inputComponents.add(chanceInput);
        chancePanel.add(chanceLabel);
        chancePanel.add(chanceInput);

        panel.add(stringsPanel);
        panel.add(fretsPanel);
        panel.add(isBowPanel);
        panel.add(chancePanel);
    }
    private void initKeyboardInstrumentInputElements(){
        JPanel keysPanel = new JPanel();

        JLabel keysLabel = new JLabel("Количество клавиш");
        JTextField keysInput = new JTextField(5);
        inputComponents.add(keysInput);
        keysPanel.add(keysLabel);
        keysPanel.add(keysInput);

        panel.add(keysPanel);
    }
    private void initWindInstrumentInputElements(){
        JPanel materialPanel = new JPanel();
        JPanel typePanel = new JPanel();
        JPanel chancePanel = new JPanel();

        JLabel materialLabel = new JLabel("Материал инструмента");
        JTextField materialInput = new JTextField(8);
        inputComponents.add(materialInput);
        materialPanel.add(materialLabel);
        materialPanel.add(materialInput);

        JLabel typeLabel = new JLabel("Тип инструмента");
        JTextField typeInput = new JTextField(8);
        inputComponents.add(typeInput);
        typePanel.add(typeLabel);
        typePanel.add(typeInput);

        JLabel chanceLabel = new JLabel("Шанс того, что музыканту не хватит воздуха");
        JTextField chanceInput = new JTextField(3);
        inputComponents.add(chanceInput);
        chancePanel.add(chanceLabel);
        chancePanel.add(chanceInput);

        panel.add(materialPanel);
        panel.add(typePanel);
        panel.add(chancePanel);
    }
    private void initNonNoteInstrumentInputElements(String[] instruments) {
        JLabel label = new JLabel("Звуки инструмента");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        for(String instrument : instruments){
            JCheckBox checkBox = new JCheckBox(instrument);
            checkBox.setAlignmentX(LEFT_ALIGNMENT);
            inputComponents.add(checkBox);
            panel.add(checkBox);
        }
    }

    private void buttn(){
        System.out.println(inputComponents.size());
    }
}
