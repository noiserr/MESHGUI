package mesh.gui;

import mesh.model.Generator;
import mesh.model.Task;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by MM on 2014-11-22.
 */
public class Gui extends JFrame {

    private JPanel rootPanel;
    private JFormattedTextField minWidthText;
    private JFormattedTextField maxWidthText;
    private JFormattedTextField maxTimeText;
    private JFormattedTextField maxHeightText;
    private JFormattedTextField minTimeText;
    private JFormattedTextField minHeightText;
    private JFormattedTextField taskNumberText;
    private int minH, maxH, minW, maxW, minT, maxT, taskNum;

    private JButton generateButton;
    private JTextArea taskTextArea;
    private JScrollPane taskScrollPane;

    public Gui() {
        super();
        theme();
        setContentPane(rootPanel);
        setSize(new Dimension(960, 700));
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVariables();
                taskTextArea.setText("");
                if ((maxW < minW) || (maxH < minH) || (maxT < minT)) {
                    System.out.println("Something wrong");
                    return;
                } else {
                    System.out.println("minW: " + minW + " maxW: " + maxW + "\n" +
                                    "minH: " + minH + " maxH: " + maxH + "\n" +
                                    "minT: " + minT + " maxW: " + maxT + "\n" +
                                    "num: " + taskNum);
                    Generator generator = new Generator(minW, minH, minT, maxW, maxH, maxT, taskNum);

                    for (Task task : generator.gen()) {
                        taskTextArea.append(task.toString() + "\n");
                    }
                }
            }
        });
    }

    private void setVariables() {
        minH = new Integer(minHeightText.getText());
        maxH = new Integer(maxHeightText.getText());
        minW = new Integer(minWidthText.getText());
        maxW = new Integer(maxWidthText.getText());
        minT = new Integer(minTimeText.getText());
        maxT = new Integer(maxTimeText.getText());
        taskNum = new Integer(taskNumberText.getText());

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMaximumIntegerDigits(1000);
        NumberFormatter nf = new NumberFormatter(f);
        nf.setAllowsInvalid(true);
        minWidthText = new JFormattedTextField(nf);
        maxWidthText = new JFormattedTextField(nf);
        maxTimeText = new JFormattedTextField(nf);
        minTimeText = new JFormattedTextField(nf);
        minHeightText = new JFormattedTextField(nf);
        maxHeightText = new JFormattedTextField(nf);
        taskNumberText = new JFormattedTextField(nf);
        taskTextArea = new JTextArea();
        taskTextArea.setEditable(false);

    }

    public void theme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
