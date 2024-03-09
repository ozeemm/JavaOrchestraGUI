package OrchestraGUI_package;

import javax.swing.*;
import java.awt.*;

// Класс для информации об инструментах/музыкантах
public class InfoGUI extends JPanel {
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
    private JButton infoButton;
    private JButton backButton;

    private JTable infoTable;

    private JButton playSoundButton;

    private JLabel playSoundLabel;

    public InfoGUI(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        instrumentTypePanel = new JPanel();
        namePanel = new JPanel();
        buttonsPanel = new JPanel();

        headerLabel = new JLabel("Музыкальные инструменты");
        headerPanel.add(headerLabel);

        instrumentTypeLabel = new JLabel("Тип инструмента:");
        instrumentTypeChoose = new JComboBox(new String[]{ "Струнные", "Клавишные", "Духовые", "Безнотные" });
        instrumentTypePanel.add(instrumentTypeLabel);
        instrumentTypePanel.add(instrumentTypeChoose);

        nameLabel = new JLabel("Инструмент:");
        nameChoose = new JComboBox(new String[]{ "Акустическая гитара", "Электрогитара", "Старая советская гитара" });
        namePanel.add(nameLabel);
        namePanel.add(nameChoose);

        buttonsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension(250, 250));
        addButton = new JButton("Добавить инструмент");
        addButton.addActionListener(e -> { new AddInstrumentGUI(instrumentTypeChoose.getSelectedIndex()); });
        deleteButton = new JButton("Удалить инструмент");
        infoButton = new JButton("Информация об инструменте");
        //showInstrumentInfoButton.addActionListener(e -> { ShowInstrumentInfoTable(); });
        backButton = new JButton("Назад");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(infoButton);
        buttonsPanel.add(backButton);

        String[][] data = new String[][]{
                {"Название", "Старая советская гитара"},
                {"Акустический", "Да"},
                {"Минимальная нота", "E 3"},
                {"Максимальная нота", "F 6"},
                {"Тип", "Струнный"},
                {"Количество струн", "6"},
                {"Количество ладов", "19"},
                {"Смычок", "Нет"},
                {"Шанс лопания струны", "75.0%"}
        };
        infoTable = new JTable(9, 2);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 2; j++){
                infoTable.setValueAt(data[i][j], i, j);
            }
        }
        infoTable.setFont(new Font("Arial", Font.PLAIN, 18));
        infoTable.setRowHeight(40);
        infoTable.getColumnModel().getColumn(0).setMinWidth(200);
        infoTable.getColumnModel().getColumn(1).setMinWidth(250);
        infoTable.setIntercellSpacing(new Dimension(5, 5));
        infoTable.setShowVerticalLines(false);

        playSoundButton = new JButton("Сыграть на инструменте");
        playSoundButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        playSoundLabel = new JLabel("I'm text =O");
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
    }

    public void setInstrumentTypeChooseValues(String[] values){
        instrumentTypeChoose.removeAllItems();
        for(String val : values){
            instrumentTypeChoose.addItem(val);
        }
    }

    public void setNameChooseValues(String[] values){
        nameChoose.removeAllItems();
        for(String val : values){
            nameChoose.addItem(val);
        }
    }

    public JButton getBackButton(){
        return backButton;
    }
}
