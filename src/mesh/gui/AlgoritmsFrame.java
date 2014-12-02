package mesh.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by MM on 2014-12-02.
 */
public class AlgoritmsFrame extends JFrame {
    private JPanel rootPanel;
    private JButton firstFitButton;

    public AlgoritmsFrame() {
        super("Algorithms");
        setContentPane(rootPanel);
        setSize(new Dimension(400,300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);


    }
}
