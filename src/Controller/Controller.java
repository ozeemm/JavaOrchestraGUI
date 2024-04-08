package Controller;

import View.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {
    private MainFrame mainFrame;
    private static Repository repository;

    public static Repository getRepository(){ return repository; }
    public void Start(){
        repository = new Repository();
        repository.initDataFromDb();

        mainFrame = new MainFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                repository.closeDbWorker();
            }
        });
        mainFrame.getMainMenuPanel().getInstrumentsButton().addActionListener(e -> { openInstrumentsInfo(); });
        mainFrame.getMainMenuPanel().getMusiciansButton().addActionListener(e -> { openMusiciansInfo(); });
        mainFrame.getMainMenuPanel().getSettingsButton().addActionListener(e -> { openSettings(); });

        mainFrame.getSettingsPanel().getBackButton().addActionListener(e -> {closeSettings();});

        mainFrame.getInstrumentsInfoPanel().getBackButton().addActionListener(e -> { closeInstrumentsInfo(); });

        mainFrame.getMusiciansInfoPanel().getBackButton().addActionListener(e -> { closeMusiciansInfo(); });
    }

    private void switchPanels(JFrame frame, JPanel panelToOpen, JPanel panelToClose){
        frame.remove(panelToClose);
        frame.add(panelToOpen);
        frame.revalidate();
        frame.repaint();
    }
    public void openSettings(){
        switchPanels(mainFrame, mainFrame.getSettingsPanel(), mainFrame.getMainMenuPanel());
        mainFrame.getSettingsPanel().showSettingsValues(repository.getSettings());
    }
    public void closeSettings(){
        switchPanels(mainFrame, mainFrame.getMainMenuPanel(), mainFrame.getSettingsPanel());
        mainFrame.getMainMenuPanel().setHeaderOrchestraName(repository.getSettings().getName());
    }
    public void openInstrumentsInfo() {
        switchPanels(mainFrame, mainFrame.getInstrumentsInfoPanel(), mainFrame.getMainMenuPanel());
        mainFrame.getInstrumentsInfoPanel().onInstrumentsInfoOpened();
    }
    public void closeInstrumentsInfo(){ switchPanels(mainFrame, mainFrame.getMainMenuPanel(), mainFrame.getInstrumentsInfoPanel()); }
    public void openMusiciansInfo(){
        switchPanels(mainFrame, mainFrame.getMusiciansInfoPanel(), mainFrame.getMainMenuPanel());
        mainFrame.getMusiciansInfoPanel().onMusiciansInfoOpened();
    }
    public void closeMusiciansInfo(){
        switchPanels(mainFrame, mainFrame.getMainMenuPanel(), mainFrame.getMusiciansInfoPanel());
    }
}
