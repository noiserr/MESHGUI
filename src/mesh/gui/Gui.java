package mesh.gui;

import mesh.comparators.AscendingTaskTimeComparator;
import mesh.model.Mesh;
import mesh.model.TaskGenerator;
import mesh.model.Task;
import mesh.comparators.DescendingTaskTimeComparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by MM on 2014-11-22.
 */
public class Gui extends JFrame {

    private JPanel rootPanel;
    private int minH, maxH, minW, maxW, minT, maxT, taskNum, meshWidth, meshHeight;
    private Mesh mesh;

    private JButton generateButton;
    private JTextArea taskTextArea;
    private JScrollPane taskScrollPane;

    private JButton clickMeButton;
    private JSpinner gridWidthSpinner;
    private JSpinner gridHeightSpinner;

    private JSpinner maxTimeSpinner;
    private JSpinner minTimeSpinner;
    private JSpinner minHeightSpinner;
    private JSpinner maxHeightSpinner;
    private JSpinner minWidthSpinner;
    private JSpinner maxWidthSpinner;
    private JSpinner taskNumberSpinner;
    private JButton ascendingButton;
    private JButton descendingButton;
    private JButton shuffleButton;

    private List<Task> taskList;

    public Gui() {
        super("M*E*S*H");

        //theme();
        setContentPane(rootPanel);
        setSize(new Dimension(960, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ascendingButton.setEnabled(false);
        descendingButton.setEnabled(false);
        shuffleButton.setEnabled(false);
        addActionListeners();




    }

    private void setVariables() {
        minH = (Integer) minHeightSpinner.getValue();
        maxH = (Integer) maxHeightSpinner.getValue();
        minW = (Integer) minWidthSpinner.getValue();
        maxW = (Integer) maxWidthSpinner.getValue();
        minT = (Integer) minTimeSpinner.getValue();
        maxT = (Integer) maxTimeSpinner.getValue();
        taskNum = (Integer) taskNumberSpinner.getValue();
        meshWidth = (Integer) gridWidthSpinner.getValue();
        meshHeight = (Integer) gridHeightSpinner.getValue();


    }

    private void createUIComponents() {


        minTimeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        maxTimeSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        minHeightSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        maxHeightSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        minWidthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        maxWidthSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        gridHeightSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        gridWidthSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        taskNumberSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));

        taskTextArea = new JTextArea();
        taskTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
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

    public void addActionListeners(){
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVariables();
                mesh = new Mesh(meshWidth, meshHeight);
                taskTextArea.setText("");
                if ((maxW < minW) || (maxH < minH) || (maxT < minT)) {
                    taskTextArea.append("***ERROR***\n" +
                            "MAX < MIN?\n" +
                            "****************\n");
                    return;
                } else {

                    ascendingButton.setEnabled(true);
                    descendingButton.setEnabled(true);
                    shuffleButton.setEnabled(true);

                    TaskGenerator generator = new TaskGenerator(minW, minH, minT, maxW, maxH, maxT, taskNum);
                    taskList = generator.gen();

                    refreshTaskWindows();


                }
            }
        });

        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ddframe = new JFrame("xxxxxxxxxx");
                ddframe.setSize(new Dimension(300, 300));
                ddframe.setLocationRelativeTo(null);
                ddframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                ddframe.setVisible(true);
            }
        });

        ascendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(taskList, new AscendingTaskTimeComparator());
                refreshTaskWindows();

            }
        });

        descendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(taskList, new DescendingTaskTimeComparator());
                refreshTaskWindows();
            }
        });

        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //long seed = System.nanoTime();
                Collections.shuffle(taskList);
                refreshTaskWindows();
            }
        });
    }

    public void refreshTaskWindows(){
        taskTextArea.setText("");
        for (Task task : taskList) {
            taskTextArea.append(task.toString() + "\n");
        }
    }
}

