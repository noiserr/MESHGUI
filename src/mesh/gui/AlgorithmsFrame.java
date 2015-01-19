package mesh.gui;

import mesh.algorithms.BusyList;
import mesh.algorithms.FirstFit;
import mesh.algorithms.RNCAlgorithm;
import mesh.comparators.AscendingTaskAreaComparator;
import mesh.comparators.AscendingTaskTimeComparator;
import mesh.comparators.DescendingTaskAreaComparator;
import mesh.comparators.DescendingTaskTimeComparator;
import mesh.model.Mesh;
import mesh.model.Task;
import mesh.providers.MeshProvider;
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
    private JButton busyListButton;
    private JButton rncButton;
    private JTextArea taskTextArea;
    private JButton ascendingButton;
    private JButton descendingButton;
    private JButton shuffleButton;
    private JRadioButton timeRadioButton;
    private JRadioButton areaRadioButton;
    private JTextArea outputTextArea;
//    private JButton panicButton;
    private JLabel meshWLabel;
    private JLabel taskLabel;

    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    RNCAlgorithm rncAlgorithm;
    BusyList BusyListAlgorithm;
    FirstFit FirstFitAlgorithm;


    public AlgorithmsFrame() {
        super("Algorithms");
        refreshTaskWindow();
        meshWLabel.setText("Mesh [" + MeshProvider.getMesh().getMeshWidth() +"x" + MeshProvider.getMesh().getMeshHeight()+"]");
//        meshHLabel.setText("Mesh H: " + MeshProvider.getMesh().getMeshHeight());
        taskLabel.setText("Tasks: " + TaskListProvider.getTaskList().size());
        addActionListeners();
        setContentPane(rootPanel);
        ImageIcon img = new ImageIcon("src/resources/miro.png");
        setIconImage(img.getImage());
        setSize(new Dimension(550, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);


    }

    public void addActionListeners() {
        firstFitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.append("------------");
                outputTextArea.append("FirstFit Algorithm");
                outputTextArea.append("------------");
                FirstFitAlgorithm = new FirstFit();
                long startTime = System.nanoTime();
                FirstFitAlgorithm.run();
                long endTime = System.nanoTime();
                float duration = (float) ((endTime - startTime) / 1000000.0);
//                System.out.println(endTime - startTime);
                outputTextArea.append("\nTime passed: " + FirstFitAlgorithm.getPassedTime());
                outputTextArea.append("\nFailed allocations: "+ FirstFitAlgorithm.getNumberOfFailedAllocations());
                outputTextArea.append("\nComputing time: " + duration + "ms");
                outputTextArea.append("\nFragmentation: " + FirstFitAlgorithm.getFragmentation());
                outputTextArea.append("\n--------------");
                outputTextArea.append("---------------");
                outputTextArea.append("------------------\n");
            }
        });


        busyListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.append("------------");
                outputTextArea.append("BusyList Algorithm");
                outputTextArea.append("----------");
                BusyListAlgorithm = new BusyList();
                long startTime = System.nanoTime();
                BusyListAlgorithm.run();
                long endTime = System.nanoTime();
                float duration = (float) ((endTime - startTime) / 1000000.0);
//                System.out.println(endTime - startTime);
                outputTextArea.append("\nTime passed: " + BusyListAlgorithm.getPassedTime());
                outputTextArea.append("\nFailed allocations: "+ BusyListAlgorithm.getNumberOfFailedAllocations());
                outputTextArea.append("\nComputing time: " + duration + "ms");
                outputTextArea.append("\nFragmentation: " + BusyListAlgorithm.getFragmentation());
                outputTextArea.append("\n--------------");
                outputTextArea.append("---------------");
                outputTextArea.append("------------------\n");
            }
        });


//        wsbaButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        rncButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.append("--------------");
                outputTextArea.append("RNC Algorithm");
                outputTextArea.append("--------------");
                rncAlgorithm = new RNCAlgorithm();
                long startTime = System.nanoTime();
                rncAlgorithm.run();
                long endTime = System.nanoTime();
                float duration = (float) ((endTime - startTime) / 1000000.00);
//                System.out.println(endTime - startTime);
                outputTextArea.append("\nTime passed: " + rncAlgorithm.getPassedTime());
                outputTextArea.append("\nFailed allocations: "+ rncAlgorithm.getNumberOfFailedAllocations());
                outputTextArea.append("\nComputing time: " + duration + "ms");
                outputTextArea.append("\nFragmentation: " + rncAlgorithm.getFragmentation());
                outputTextArea.append("\n--------------");
                outputTextArea.append("----------------");
                outputTextArea.append("-----------------\n");
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

//        panicButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            }
//        });


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
        outputTextArea = new JTextArea();
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//        meshWLabel = new JLabel("Mesh W: " + MeshProvider.getMesh().getMeshWidth());

    }
}
