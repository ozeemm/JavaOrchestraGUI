package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddInstrumentFrame extends JFrame {
    private JPanel panel;
    private int instrumentType;
    private ArrayList<JComponent> inputComponents;
    private void initWindow(){
        setSize(475, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        ImageIcon icon = new ImageIcon("./img/icon2.png");
        this.setIconImage(icon.getImage());
    }

    public AddInstrumentFrame(int instrumentType){
        super("Добавление");
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
                initNonNoteInstrumentInputElements();
                break;
            case 4: // Музыкант
                header.setText("Добавление музыканта");
                initMusicianInputElements();
                break;
        }

        JButton addButton = new JButton("Добавить");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.addActionListener(e -> { AddButtonClicked(); });
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
    private void initNonNoteInstrumentInputElements() {
        JLabel label = new JLabel("Звуки инструмента");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        JPanel soundsPanel = new JPanel(new GridLayout(7, 3));
        for(String instrument : NonNoteSound.getPossibleSounds()){
            JCheckBox checkBox = new JCheckBox(instrument);
            //checkBox.setAlignmentX(LEFT_ALIGNMENT);
            inputComponents.add(checkBox);
            //panel.add(checkBox);
            soundsPanel.add(checkBox);
        }
        panel.add(soundsPanel);
    }
    private void initMusicianInputElements(){
        JPanel namePanel = new JPanel();
        JPanel agePanel = new JPanel();
        JPanel joiningYearPanel = new JPanel();
        JPanel instrumentPanel = new JPanel();

        JLabel nameLabel = new JLabel("Имя:");
        JTextField nameInput = new JTextField(10);
        inputComponents.add(nameInput);
        namePanel.add(nameLabel);
        namePanel.add(nameInput);

        JLabel ageLabel = new JLabel("Возраст:");
        JTextField ageInput = new JTextField(4);
        inputComponents.add(ageInput);
        agePanel.add(ageLabel);
        agePanel.add(ageInput);

        JLabel joiningYearLabel = new JLabel("Год вступления:");
        JTextField joiningYearInput = new JTextField(4);
        inputComponents.add(joiningYearInput);
        joiningYearPanel.add(joiningYearLabel);
        joiningYearPanel.add(joiningYearInput);

        JLabel instrumentLabel = new JLabel("Инструмент:");
        JComboBox<String> instrumentInput = new JComboBox<String>();
        for(MusicInstrument instrument : Controller.getRepository().getInstruments()){
            instrumentInput.addItem(instrument.getName());
        }
        inputComponents.add(instrumentInput);
        instrumentPanel.add(instrumentLabel);
        instrumentPanel.add(instrumentInput);

        panel.add(namePanel);
        panel.add(agePanel);
        panel.add(joiningYearPanel);
        panel.add(instrumentPanel);
    }

    private void AddButtonClicked(){
        if(instrumentType >= 0 && instrumentType <= 2){
            JTextField nameInput = (JTextField)inputComponents.get(0);
            JCheckBox isAcousticInput = (JCheckBox)inputComponents.get(1);
            JTextField minNoteInput = (JTextField) inputComponents.get(2);
            Note minNote = new Note(minNoteInput.getText().split(" ")[0], Integer.parseInt(minNoteInput.getText().split(" ")[1]));
            JTextField maxNoteInput = (JTextField) inputComponents.get(3);
            Note maxNote = new Note(maxNoteInput.getText().split(" ")[0], Integer.parseInt(maxNoteInput.getText().split(" ")[1]));
            switch (instrumentType){
                case 0:
                    StringedInstrument stringedInstrument = new StringedInstrument();
                    stringedInstrument.setName(nameInput.getText());
                    stringedInstrument.setIsAcoustic(isAcousticInput.isSelected());
                    stringedInstrument.setNoteRange(minNote, maxNote);
                    JTextField fretsInput = (JTextField)inputComponents.get(4);
                    JTextField stringsInput = (JTextField)inputComponents.get(5);
                    JCheckBox isBowInput = (JCheckBox) inputComponents.get(6);
                    JTextField stringBreakChanceInput = (JTextField)inputComponents.get(7);
                    stringedInstrument.setFrets(Integer.parseInt(fretsInput.getText()));
                    stringedInstrument.setStrings(Integer.parseInt(stringsInput.getText()));
                    stringedInstrument.setIsBow(isBowInput.isSelected());
                    stringedInstrument.setStringBreakChance(Float.parseFloat(stringBreakChanceInput.getText()));
                    Controller.getRepository().addInstrument(stringedInstrument);
                    break;
                case 1:
                    KeyboardInstrument keyboardInstrument = new KeyboardInstrument();
                    keyboardInstrument.setName(nameInput.getText());
                    keyboardInstrument.setIsAcoustic(isAcousticInput.isSelected());
                    keyboardInstrument.setNoteRange(minNote, maxNote);
                    JTextField keysInput = (JTextField)inputComponents.get(4);
                    keyboardInstrument.setKeys(Integer.parseInt(keysInput.getText()));
                    Controller.getRepository().addInstrument(keyboardInstrument);
                    break;
                case 2:
                    WindInstrument windInstrument = new WindInstrument();
                    windInstrument.setName(nameInput.getText());
                    windInstrument.setIsAcoustic(isAcousticInput.isSelected());
                    windInstrument.setNoteRange(minNote, maxNote);
                    JTextField materialInput = (JTextField)inputComponents.get(4);
                    JTextField typeInput = (JTextField)inputComponents.get(5);
                    JTextField chanceInput = (JTextField)inputComponents.get(6);
                    windInstrument.setMaterial(materialInput.getText());
                    windInstrument.setType(typeInput.getText());
                    windInstrument.setNotEnoughBreathChance(Float.parseFloat(chanceInput.getText()));
                    Controller.getRepository().addInstrument(windInstrument);
                    break;
            }
        }
        else{
            switch (instrumentType){
                case 3:
                    JTextField nameInput = (JTextField)inputComponents.get(0);
                    JCheckBox isAcousticInput = (JCheckBox)inputComponents.get(1);
                    NonNoteInstrument instrument = new NonNoteInstrument();
                    instrument.setName(nameInput.getText());
                    instrument.setIsAcoustic(isAcousticInput.isSelected());
                    for(int i = 0; i < NonNoteSound.getPossibleSounds().size(); i++){
                        JCheckBox soundInput = (JCheckBox)inputComponents.get(i + 2);
                        if(soundInput.isSelected())
                            instrument.addSound(new NonNoteSound(NonNoteSound.getPossibleSounds().get(i)));
                    }
                    Controller.getRepository().addInstrument(instrument);
                    break;

                case 4:
                    Musician musician = new Musician();
                    JTextField musicianNameInput = (JTextField)inputComponents.get(0);
                    JTextField musicianAgeInput = (JTextField)inputComponents.get(1);
                    JTextField musicianJoiningYearInput = (JTextField)inputComponents.get(2);
                    JComboBox<String> musicianInstrumentInput = (JComboBox<String>)inputComponents.get(3);
                    musician.setName(musicianNameInput.getText());
                    musician.setAge(Integer.parseInt(musicianAgeInput.getText()));
                    musician.setJoiningOrchestraYear(Integer.parseInt(musicianJoiningYearInput.getText()));
                    musician.setInstrument(Controller.getRepository().getInstruments().get(musicianInstrumentInput.getSelectedIndex()));
                    Controller.getRepository().addMusician(musician);
                    break;
            }
        }
        dispose();
    }
}
