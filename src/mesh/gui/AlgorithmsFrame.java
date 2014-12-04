package mesh.gui;

import mesh.algorithms.RNCAlgorithm;
import mesh.comparators.AscendingTaskAreaComparator;
import mesh.comparators.AscendingTaskTimeComparator;
import mesh.comparators.DescendingTaskAreaComparator;
import mesh.comparators.DescendingTaskTimeComparator;
import mesh.model.Task;
import mesh.providers.TaskListProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MM on 2014-12-02.
 */
public class AlgorithmsFrame extends JFrame {

    private JPanel rootPanel;
    private JButton firstFitButton;
    private JButton wsbaButton;
    private JButton busyListButton;
    private JButton rncButton;
    private JTextArea taskTextArea;
    private JButton ascendingButton;
    private JButton descendingButton;
    private JButton shuffleButton;
    private JRadioButton timeRadioButton;
    private JRadioButton areaRadioButton;
    private JTextArea outputTextArea;
    private JButton panicButton;

    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    RNCAlgorithm rncAlgorithm;


    public AlgorithmsFrame() {
        super("Algorithms");
        refreshTaskWindow();
        addActionListeners();
        setContentPane(rootPanel);
        setSize(new Dimension(550, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);


    }

    public void addActionListeners() {
        firstFitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        busyListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        wsbaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        rncButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rncAlgorithm = new RNCAlgorithm();
                long startTime = System.nanoTime();
                rncAlgorithm.run();
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1000000;
                outputTextArea.append("\nPik of time: " + rncAlgorithm.getPassedTime());
                outputTextArea.append("\nActual computing time: " + duration + "ms");
            }
        });

        ascendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (timeRadioButton.isSelected()) {
                    Collections.sort(taskList, new AscendingTaskTimeComparator());

                } else if (areaRadioButton.isSelected()) {
                    Collections.sort(taskList, new AscendingTaskAreaComparator());
                }
                refreshTaskWindow();

            }
        });

        descendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeRadioButton.isSelected()) {
                    Collections.sort(taskList, new DescendingTaskTimeComparator());
                } else if (areaRadioButton.isSelected()) {
                    Collections.sort(taskList, new DescendingTaskAreaComparator());
                }
                refreshTaskWindow();
            }
        });

        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Collections.shuffle(taskList);
                refreshTaskWindow();
            }
        });

        panicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


    }

    public void refreshTaskWindow() {
        taskTextArea.setText("");
        for (Task task : taskList) {
            taskTextArea.append(task.toString() + "\n");
        }
    }

    private void createUIComponents() {
        taskTextArea = new JTextArea();

        taskTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

    }
}
