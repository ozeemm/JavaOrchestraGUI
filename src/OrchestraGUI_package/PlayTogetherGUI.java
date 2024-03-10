package OrchestraGUI_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayTogetherGUI extends JFrame {
    private JLabel playingLabel;
    private void initWindow(){
        setSize(650, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        ImageIcon icon = new ImageIcon("./img/icon2.png");
        this.setIconImage(icon.getImage());
    }
    public PlayTogetherGUI(){
        super("Сыграть всем оркестром");
        initWindow();
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        ImageIcon orchestraImg = new ImageIcon("./img/orchestra_playing.gif");
        JLabel imgLabel = new JLabel(orchestraImg);
        imgLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(imgLabel);

        playingLabel = new JLabel();
        playingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        playingLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(playingLabel);

        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(e -> { this.dispose(); });
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));
        this.add(closeButton);

        orchestraPlay();
    }

    private void orchestraPlay(){
        Timer timer = new Timer(GUIController.getOrchestra().getSettings().getSongSoundsDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playingLabel.setText(GUIController.getOrchestra().playSound());
            }
        });

        timer.start();
    }
}
