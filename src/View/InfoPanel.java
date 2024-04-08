package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Класс для информации об инструментах/музыкантах
public class InfoPanel extends JPanel {
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JPanel headerPanel;
    private JLabel headerLabel;

    private JPanel instrumentTypePanel;
    private JLabel instrumentTypeLabel;
    private JComboBox<String> instrumentTypeChoose;

    private JPanel namePanel;
    private JLabel nameLabel;
    private JComboBox<String> nameChoose;

    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton backButton;
    private JTable infoTable;

    private JButton playSoundButton;
    private JLabel playSoundLabel;

    private ArrayList<MusicInstrument> shownInstruments;
    private ArrayList<Musician> shownMusicians;
    private int currentInstrumentType = -1;
    private Musician currentMusician;
    private MusicInstrument currentInstrument;

    private int tableRows = 12;
    private int tableCols = 2;

    public InfoPanel(boolean isInstrumentsInfo){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        instrumentTypePanel = new JPanel();
        namePanel = new JPanel();
        buttonsPanel = new JPanel();

        headerLabel = new JLabel();
        headerPanel.add(headerLabel);

        instrumentTypeLabel = new JLabel();
        instrumentTypeChoose = new JComboBox<String>();
        instrumentTypePanel.add(instrumentTypeLabel);
        instrumentTypePanel.add(instrumentTypeChoose);

        nameLabel = new JLabel();
        nameChoose = new JComboBox<String>();
        namePanel.add(nameLabel);
        namePanel.add(nameChoose);

        buttonsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension(250, 250));
        addButton = new JButton();
        deleteButton = new JButton();
        backButton = new JButton("Назад");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(backButton);

        if(!isInstrumentsInfo)
            tableRows = 4;
        infoTable = new JTable(tableRows, tableCols);
        infoTable.setFont(new Font("Arial", Font.PLAIN, 17));
        infoTable.setRowHeight(40);
        infoTable.getColumnModel().getColumn(0).setMinWidth(200);
        infoTable.getColumnModel().getColumn(1).setMinWidth(275);
        infoTable.setIntercellSpacing(new Dimension(5, 5));
        infoTable.setShowVerticalLines(false);

        playSoundButton = new JButton("Сыграть на инструменте");
        playSoundButton.addActionListener(e->{ playInstrumentButtonClicked(); });
        playSoundButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        playSoundLabel = new JLabel();
        playSoundLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        playSoundLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        leftPanel.add(headerPanel);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(instrumentTypePanel);
        leftPanel.add(namePanel);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(buttonsPanel);

        rightPanel.add(infoTable);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(playSoundButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(playSoundLabel);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(Box.createHorizontalStrut(25));
        this.add(rightPanel, BorderLayout.EAST);

        if(isInstrumentsInfo)
            initInstrumentsInfoData();
        else
            initMusiciansInfoData();
    }

    private void initInstrumentsInfoData(){
        instrumentTypeChoose.addItemListener(e -> { onInstrumentTypeChange(); });
        nameChoose.addItemListener(e -> { onInstrumentChange(); });
        addButton.setText("Добавить инструмент");
        addButton.addActionListener(e -> { addInstrumentClicked(); });
        deleteButton.setText("Удалить инструмент");
        deleteButton.addActionListener(e -> { deleteInstrumentClicked(); });

        headerLabel.setText("Музыкальные инструменты");
        instrumentTypeLabel.setText("Тип инструмента:");
        setInstrumentTypeChooseValues(new String[]{ "Струнные", "Клавишные", "Духовые", "Безнотные" });
        nameLabel.setText("Инструмент:");

    }
    public void onInstrumentsInfoOpened(){
        clearPlaySoundLabel();
        onInstrumentTypeChange();
    }
    public void onInstrumentTypeChange(){
        currentInstrumentType = instrumentTypeChoose.getSelectedIndex();
        shownInstruments = new ArrayList<MusicInstrument>();

        for(MusicInstrument instrument : Controller.getRepository().getInstruments()){
            switch (currentInstrumentType){
                case 0: // Струнные
                    if(instrument instanceof StringedInstrument){
                        shownInstruments.add(instrument);
                    }
                    break;
                case 1: // Клавишные
                    if(instrument instanceof KeyboardInstrument){
                        shownInstruments.add(instrument);
                    }
                    break;
                case 2: // Духовые
                    if(instrument instanceof WindInstrument){
                        shownInstruments.add(instrument);
                    }
                    break;
                case 3: // Безнотные
                    if(instrument instanceof NonNoteInstrument){
                        shownInstruments.add(instrument);
                    }
                    break;
            }
        }

        nameChoose.removeAllItems();
        for(MusicInstrument instrument : shownInstruments){
            nameChoose.addItem(instrument.getName());
        }

        clearPlaySoundLabel();
    }
    public void onInstrumentChange() {
        if(nameChoose.getSelectedIndex() != -1)
            currentInstrument = shownInstruments.get(nameChoose.getSelectedIndex());

        ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
        int row = 0;
        tableData.add(new ArrayList<String>());
        tableData.get(row).add("Название");
        tableData.get(row).add(currentInstrument.getName());
        row++;
        tableData.add(new ArrayList<String>());
        tableData.get(row).add("Акустический");
        tableData.get(row).add(currentInstrument.getIsAcoustic() ? "Да" : "Нет");
        row++;
        if(currentInstrument instanceof NoteInstrument){
            NoteInstrument noteInstrument = (NoteInstrument) currentInstrument;
            boolean isRuNotes = Controller.getRepository().getSettings().getIsRuNotes();
            tableData.add(new ArrayList<String>());
            tableData.get(row).add("Минимальная нота");
            tableData.get(row).add(isRuNotes ? noteInstrument.getMinNote().toRuString() : noteInstrument.getMinNote().toString());
            row++;
            tableData.add(new ArrayList<String>());
            tableData.get(row).add("Максимальная нота");
            tableData.get(row).add(isRuNotes ? noteInstrument.getMaxNote().toRuString() : noteInstrument.getMaxNote().toString());
            row++;
        }

        switch (currentInstrumentType){
            case 0: // Струнные
                StringedInstrument stringedInstrument;
                try { stringedInstrument = (StringedInstrument) currentInstrument; }
                catch (ClassCastException e){ break; }

                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Тип");
                tableData.get(row).add("Струнный");
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Количество струн");
                tableData.get(row).add(Integer.toString(stringedInstrument.getStrings()));
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Количество ладов");
                tableData.get(row).add(Integer.toString(stringedInstrument.getFrets()));
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Смычок");
                tableData.get(row).add(stringedInstrument.getIsBow() ? "Да" : "Нет");
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Шанс лопания струны");
                tableData.get(row).add(stringedInstrument.getStringBreakChance() * 100 + "%");
                row++;
                break;
            case 1: // Клавишные
                KeyboardInstrument keyboardInstrument;
                try{ keyboardInstrument = (KeyboardInstrument)currentInstrument; }
                catch (ClassCastException e) { break; }

                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Тип");
                tableData.get(row).add("Клавишный");
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Количество клавиш");
                tableData.get(row).add(Integer.toString(keyboardInstrument.getKeys()));
                row++;
                break;
            case 2: // Духовые
                WindInstrument windInstrument;
                try{ windInstrument = (WindInstrument)currentInstrument; }
                catch (ClassCastException e) { break; }

                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Тип");
                tableData.get(row).add("Духовой");
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Материал");
                tableData.get(row).add(windInstrument.getMaterial());
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Духовой тип");
                tableData.get(row).add(windInstrument.getType());
                row++;
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Шанс нехватки воздуха");
                tableData.get(row).add(windInstrument.getNotEnoughBreathChance() * 100 + "%");
                row++;
                break;
            case 3: // Безнотные
                NonNoteInstrument nonNoteInstrument;
                try{ nonNoteInstrument = (NonNoteInstrument)currentInstrument; }
                catch (ClassCastException e) { break; }

                //StringBuilder sounds = new StringBuilder();
                tableData.add(new ArrayList<String>());
                tableData.get(row).add("Тип");
                tableData.get(row).add("Безнотный");
                row++;

                for(int i = 0; i < nonNoteInstrument.getSounds().size(); i++){
                    tableData.add(new ArrayList<String>());
                    tableData.get(row).add(i == 0 ? "Звуки" : "");
                    tableData.get(row).add(nonNoteInstrument.getSounds().get(i).toString());
                    row++;
                }
                break;
        }
        for(int i = row; i < tableRows; i++){
            tableData.add(new ArrayList<String>());
            tableData.get(i).add("");
            tableData.get(i).add("");
        }

        for(int i = 0; i < tableRows; i++) {
            for (int j = 0; j < tableCols; j++) {
                infoTable.setValueAt(tableData.get(i).get(j), i, j);
            }
        }
        clearPlaySoundLabel();
    }
    private void addInstrumentClicked(){
        AddInstrumentFrame gui = new AddInstrumentFrame(instrumentTypeChoose.getSelectedIndex());
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                onInstrumentTypeChange();
            }
        });
    }
    private void deleteInstrumentClicked(){
        Controller.getRepository().deleteInstrument(currentInstrument);
        onInstrumentTypeChange();
    }

    public void initMusiciansInfoData(){
        instrumentTypeChoose.addItemListener(e -> { onMusiciansInstrumentTypeChange(); });
        nameChoose.addItemListener(e -> { onMusicianChange(); });
        addButton.setText("Добавить музыканта");
        addButton.addActionListener(e -> { addMusicianClicked(); });
        deleteButton.setText("Удалить музыканта");
        deleteButton.addActionListener(e -> { deleteMusicianClicked(); });

        headerLabel.setText("Музыканты");
        instrumentTypeLabel.setText("Тип инструмента:");
        setInstrumentTypeChooseValues(new String[]{ "Все инструменты", "Струнные", "Клавишные", "Духовые", "Безнотные" });
        nameLabel.setText("Музыкант:");
    }
    public void onMusiciansInfoOpened() {
        clearPlaySoundLabel();
        onMusiciansInstrumentTypeChange();
    }
    public void onMusiciansInstrumentTypeChange(){
        currentInstrumentType = instrumentTypeChoose.getSelectedIndex();
        shownMusicians = new ArrayList<Musician>();

        for(Musician musician : Controller.getRepository().getMusicians()){
            switch(currentInstrumentType){
                case 0: // Все
                    shownMusicians.add(musician);
                    break;
                case 1: // Струнные
                    if(musician.getInstrument() instanceof StringedInstrument)
                        shownMusicians.add(musician);
                    break;
                case 2: // Клавишные
                    if(musician.getInstrument() instanceof KeyboardInstrument)
                        shownMusicians.add(musician);
                    break;
                case 3: // Духовые
                    if(musician.getInstrument() instanceof WindInstrument)
                        shownMusicians.add(musician);
                    break;
                case 4: // Безнотные
                    if(musician.getInstrument() instanceof NonNoteInstrument)
                        shownMusicians.add(musician);
                    break;
            }
        }

        nameChoose.removeAllItems();
        for(Musician musician : shownMusicians){
            nameChoose.addItem(musician.getName() + " | " + musician.getInstrument().getName());
        }

        clearPlaySoundLabel();
    }
    public void onMusicianChange(){
        if(nameChoose.getSelectedIndex() != -1) {
            currentMusician = shownMusicians.get(nameChoose.getSelectedIndex());
            currentInstrument = currentMusician.getInstrument();
        }

        String[][] tableData = new String[][]{
                {"Имя", currentMusician.getName()},
                {"Возраст", String.valueOf(currentMusician.getAge())},
                {"Год вступления", String.valueOf(currentMusician.getJoiningOrchestraYear())},
                {"Инструмент", currentMusician.getInstrument().getName()}
        };

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 2; j++){
                infoTable.setValueAt(tableData[i][j], i, j);
            }
        }

        clearPlaySoundLabel();
    }
    private void addMusicianClicked(){
        AddInstrumentFrame gui = new AddInstrumentFrame(4);
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                onMusiciansInstrumentTypeChange();
            }
        });
    }
    private void deleteMusicianClicked(){
        Controller.getRepository().deleteMusician(currentMusician);
        onMusiciansInstrumentTypeChange();
    }

    public void playInstrumentButtonClicked(){
        String sound = currentInstrument.orchestraPlay(Controller.getRepository().getSettings().getIsRuNotes());
        if(sound != null){
            if(currentInstrument instanceof NoteInstrument)
                playSoundLabel.setText("Сыграна нота " + sound + " октавы");
            else
                playSoundLabel.setText("Сыгран звук: " + sound);
        }
        else{ playSoundLabel.setText("Не удалось сыграть на инструменте"); }
    }
    private void clearPlaySoundLabel(){
        if(playSoundLabel != null)
            playSoundLabel.setText("");
    }

    public void setInstrumentTypeChooseValues(String[] values){
        instrumentTypeChoose.removeAllItems();
        for(String val : values){
            instrumentTypeChoose.addItem(val);
        }
    }
    public JButton getBackButton(){
        return backButton;
    }
}
