package GUI;

import Logic.*;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    private Orchestra orchestra;
    private MainFrame mainFrame;
    private MainMenuPanel mainMenuPanel;
    private SettingsPanel settingsPanel;
    private InfoPanel instrumentsInfoPanel;
    private InfoPanel musiciansInfoPanel;

    private void initTestData(){
        orchestra = new Orchestra();

        // Нотные инструменты
        StringedInstrument guitar = new StringedInstrument();
        guitar.setName("Акустическая гитара");
        guitar.setIsAcoustic(true);
        guitar.setNoteRange(new Note("E", 3), new Note("D", 6));
        guitar.setFrets(22);
        guitar.setStrings(6);
        guitar.setIsBow(false);
        guitar.setStringBreakChance(0.01f);
        orchestra.addInstrument(guitar);

        StringedInstrument elGuitar = new StringedInstrument();
        elGuitar.setName("Электрогитара");
        elGuitar.setIsAcoustic(false);
        elGuitar.setNoteRange(new Note("E", 3), new Note("D", 6));
        elGuitar.setFrets(22);
        elGuitar.setStrings(6);
        elGuitar.setIsBow(false);
        elGuitar.setStringBreakChance(0.01f);
        orchestra.addInstrument(elGuitar);

        StringedInstrument oldGuitar = new StringedInstrument();
        oldGuitar.setName("Старая советская гитара");
        oldGuitar.setIsAcoustic(true);
        oldGuitar.setNoteRange(new Note("E", 3), new Note("F", 6));
        oldGuitar.setFrets(19);
        oldGuitar.setStrings(6);
        oldGuitar.setIsBow(false);
        oldGuitar.setStringBreakChance(0.75f);
        orchestra.addInstrument(oldGuitar);

        KeyboardInstrument royal = new KeyboardInstrument();
        royal.setName("Рояль");
        royal.setIsAcoustic(true);
        royal.setNoteRange(new Note("A", 0), new Note("C", 8));
        royal.setKeys(88);
        orchestra.addInstrument(royal);

        WindInstrument flute = new WindInstrument();
        flute.setName("Флейта");
        flute.setIsAcoustic(true);
        flute.setNoteRange(new Note("C", 3), new Note("D", 6));
        flute.setMaterial("Деревянный");
        flute.setType("Лабиальный");
        flute.setNotEnoughBreathChance(0.02f);
        orchestra.addInstrument(flute);

        WindInstrument saxofone = new WindInstrument();
        saxofone.setName("Саксофон");
        saxofone.setIsAcoustic(true);
        saxofone.setNoteRange(new Note("Db", 2), new Note("Ab", 4));
        saxofone.setMaterial("Медный");
        saxofone.setType("Тростевой");
        saxofone.setNotEnoughBreathChance(0.05f);
        orchestra.addInstrument(saxofone);

        WindInstrument trumpet = new WindInstrument();
        trumpet.setName("Труба");
        trumpet.setIsAcoustic(true);
        trumpet.setNoteRange(new Note("F#", 2), new Note("С", 5));
        trumpet.setMaterial("Медный");
        trumpet.setType("Амбушюрный");
        trumpet.setNotEnoughBreathChance(0.075f);
        orchestra.addInstrument(trumpet);

        // Звуки
        NonNoteSound.addPossibleSound("Бас-бочка");
        NonNoteSound.addPossibleSound("Малый барабан");
        NonNoteSound.addPossibleSound("Подвесный том");
        NonNoteSound.addPossibleSound("Напольный том");
        NonNoteSound.addPossibleSound("Закрытый хай-хэт");
        NonNoteSound.addPossibleSound("Открытый хай-хэт");
        NonNoteSound.addPossibleSound("Крэш");
        NonNoteSound.addPossibleSound("Райд");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №1");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №2");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №3");

        ArrayList<String> possibleSounds = NonNoteSound.getPossibleSounds();

        // Безнотные инструменты
        NonNoteInstrument drumSet = new NonNoteInstrument();
        drumSet.setName("Барабанная установка №1");
        drumSet.setIsAcoustic(true);
        for(int i = 0; i < 8; i++){
            drumSet.addSound(new NonNoteSound(possibleSounds.get(i)));
        }
        orchestra.addInstrument(drumSet);

        NonNoteInstrument conductorStick = new NonNoteInstrument();
        conductorStick.setName("Дирижёрская палочка");
        conductorStick.setIsAcoustic(true);
        for(int i = 8; i < 11; i++){
            conductorStick.addSound(new NonNoteSound(possibleSounds.get(i)));
        }
        orchestra.addInstrument(conductorStick);

        // Музыканты
        Musician musician = new Musician();
        musician.setName("Иванов Иван");
        musician.setAge(27);
        musician.setJoiningOrchestraYear(2020);
        musician.setInstrument(orchestra.getInstruments().get(1)); // Электрогитара
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Александров Александр");
        musician.setAge(26);
        musician.setJoiningOrchestraYear(2020);
        musician.setInstrument(orchestra.getInstruments().get(7)); // Барабанная установка №1
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Сергеев Сергей");
        musician.setAge(47);
        musician.setJoiningOrchestraYear(2014);
        musician.setInstrument(orchestra.getInstruments().get(8)); // Дирижёрская палочка
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Трубов Трубач");
        musician.setAge(37);
        musician.setJoiningOrchestraYear(2016);
        musician.setInstrument(orchestra.getInstruments().get(6)); // Труба
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Трубов Трубач младший");
        musician.setAge(13);
        musician.setJoiningOrchestraYear(2018);
        musician.setInstrument(orchestra.getInstruments().get(6)); // Труба
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Саксофонов Саксофонист");
        musician.setAge(34);
        musician.setJoiningOrchestraYear(2017);
        musician.setInstrument(orchestra.getInstruments().get(5)); // Саксофон
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Петров Пётр");
        musician.setAge(19);
        musician.setJoiningOrchestraYear(2021);
        musician.setInstrument(orchestra.getInstruments().get(3)); // Рояль
        orchestra.addMusician(musician);
    }
    public void Start(){
        initTestData();
        
        mainFrame = new MainFrame();
        mainFrame.setOrchestra(orchestra);

        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.setHeaderOrchestraName(orchestra.getSettings().getName());
        mainMenuPanel.getPlayButton().addActionListener(e -> { new PlayTogetherFrame(); });
        mainMenuPanel.getInstrumentsButton().addActionListener(e -> { openInstrumentsInfo(); });
        mainMenuPanel.getMusiciansButton().addActionListener(e -> { openMusiciansInfo(); });
        mainMenuPanel.getSettingsButton().addActionListener(e -> { openSettings(); });

        settingsPanel = new SettingsPanel();
        settingsPanel.getBackButton().addActionListener(e -> {closeSettings();});

        instrumentsInfoPanel = new InfoPanel(true);
        instrumentsInfoPanel.getBackButton().addActionListener(e -> { closeInstrumentsInfo(); });

        musiciansInfoPanel = new InfoPanel(false);
        musiciansInfoPanel.getBackButton().addActionListener(e -> { closeMusiciansInfo(); });

        mainFrame.add(mainMenuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void switchPanels(JFrame frame, JPanel panelToOpen, JPanel panelToClose){
        frame.remove(panelToClose);
        frame.add(panelToOpen);
        frame.revalidate();
        frame.repaint();
    }
    public void openSettings(){
        switchPanels(mainFrame, settingsPanel, mainMenuPanel);
        settingsPanel.showSettingsValues(orchestra.getSettings());
    }
    public void closeSettings(){
        switchPanels(mainFrame, mainMenuPanel, settingsPanel);
        mainMenuPanel.setHeaderOrchestraName(orchestra.getSettings().getName());
    }
    public void openInstrumentsInfo() {
        switchPanels(mainFrame, instrumentsInfoPanel, mainMenuPanel);
        instrumentsInfoPanel.onInstrumentsInfoOpened();
    }
    public void closeInstrumentsInfo(){ switchPanels(mainFrame, mainMenuPanel, instrumentsInfoPanel); }
    public void openMusiciansInfo(){
        switchPanels(mainFrame, musiciansInfoPanel, mainMenuPanel);
        musiciansInfoPanel.onMusiciansInfoOpened();
    }
    public void closeMusiciansInfo(){
        switchPanels(mainFrame, mainMenuPanel, musiciansInfoPanel);
    }
}
