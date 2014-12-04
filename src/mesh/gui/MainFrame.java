package mesh.gui;

import mesh.comparators.AscendingTaskAreaComparator;
import mesh.comparators.AscendingTaskTimeComparator;
import mesh.comparators.DescendingTaskAreaComparator;
import mesh.model.Mesh;
import mesh.model.TaskGenerator;
import mesh.model.Task;
import mesh.comparators.DescendingTaskTimeComparator;
import mesh.providers.MeshProvider;
import mesh.providers.TaskListProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by MM on 2014-11-22.
 */
public class MainFrame extends JFrame {

    private JPanel rootPanel;
    private int minH, maxH, minW, maxW, minT, maxT, taskNum, meshWidth, meshHeight;
    private Mesh mesh;

    private JButton generateButton;
    private JTextArea taskTextArea;
    private JScrollPane taskScrollPane;

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
    private JRadioButton timeRadioButton;
    private JRadioButton areaRadioButton;
    private JButton runTestsButton;
    private JButton gridUpdateButton;
    private JButton meshGeneratorButton;

    private List<Task> taskList;

    public MainFrame() {
        super("M*E*S*H");
        runTestsButton.setEnabled(false);
        //theme();
        setContentPane(rootPanel);
        setSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
//        setLocation(300,200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
//        ascendingButton.setEnabled(false);
//        descendingButton.setEnabled(false);
//        shuffleButton.setEnabled(false);
        addActionListeners();


        gridWidthSpinner.addComponentListener(new ComponentAdapter() {
        });
    }

    private void setVariables(String arg) {
        if(arg.equals("grid")){
            meshWidth = (Integer) gridWidthSpinner.getValue();
            meshHeight = (Integer) gridHeightSpinner.getValue();
        }else{
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



    }

    private void createUIComponents() {


        minTimeSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 99, 1));
        maxTimeSpinner = new JSpinner(new SpinnerNumberModel(7, 1, 99, 1));
        minHeightSpinner = new JSpinner(new SpinnerNumberModel(6, 1, 99, 1));
        maxHeightSpinner = new JSpinner(new SpinnerNumberModel(6, 1, 99, 1));
        minWidthSpinner = new JSpinner(new SpinnerNumberModel(11, 1, 99, 1));
        maxWidthSpinner = new JSpinner(new SpinnerNumberModel(11, 1, 99, 1));
        gridHeightSpinner = new JSpinner(new SpinnerNumberModel(11, 1, 99, 1));
        gridWidthSpinner = new JSpinner(new SpinnerNumberModel(11, 1, 99, 1));
        taskNumberSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));

        taskTextArea = new JTextArea();
        taskTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        taskTextArea.setEditable(false);

    }

//    public void theme() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            System.out.println(UIManager.getSystemLookAndFeelClassName());
//            SwingUtilities.updateComponentTreeUI(this);
//
//
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public void addActionListeners(){

        gridUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVariables("grid");
                MeshProvider.init(meshWidth, meshHeight);

            }
        });


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVariables("all");

                MeshProvider.init(meshWidth, meshHeight);
                System.out.println("Mesh width: " + MeshProvider.getMesh().getMeshWidth()+ " MeshHeight: " + MeshProvider.getMesh().getMeshHeight());
                taskTextArea.setText("");

                if ((maxW < minW) || (maxH < minH) || (maxT < minT)) {
                    taskTextArea.append("*****ERROR*****\n" +
                                        "*  MAX < MIN  *\n" +
                                        "***************\n");
                    return;
                } else {

//                    ascendingButton.setEnabled(true);
//                    descendingButton.setEnabled(true);
//                    shuffleButton.setEnabled(true);

                    TaskGenerator generator = new TaskGenerator(minW, minH, minT, maxW, maxH, maxT, taskNum);
                    taskList = generator.gen();
                    TaskListProvider.init(taskList);
                    refreshTaskWindows();


                }
                runTestsButton.setEnabled(true);
            }
        });

        runTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                final AlgoritmsFrame algoritmsFrame = new AlgoritmsFrame(mesh, taskList);
                algoritmsFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we)
                    {
                        String ObjButtons[] = {"Yes","No"};
                        int PromptResult = JOptionPane.showOptionDialog(null,"All your will be erased, are you sure?","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                        if(PromptResult==JOptionPane.YES_OPTION)
                        {
                            setVisible(true);
                            //System.exit(0);
                            algoritmsFrame.dispose();
//                            taskTextArea.setText("");
//                            runTestsButton.setEnabled(false);

                        }
                    }
                });
            }
        });

//        ascendingButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                if(timeRadioButton.isSelected()){
//                    Collections.sort(taskList, new AscendingTaskTimeComparator());
//
//                }else if(areaRadioButton.isSelected()){
//                    Collections.sort(taskList, new AscendingTaskAreaComparator());
//                }
//                refreshTaskWindow();
//
//            }
//        });
//
//        descendingButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(timeRadioButton.isSelected()) {
//                    Collections.sort(taskList, new DescendingTaskTimeComparator());
//                }else if(areaRadioButton.isSelected()){
//                    Collections.sort(taskList, new DescendingTaskAreaComparator());
//                }
//                refreshTaskWindow();
//            }
//        });
//
//        shuffleButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //long seed = System.nanoTime();
//
//                Collections.shuffle(taskList);
//                refreshTaskWindow();
//            }
//        });
    }

    public void refreshTaskWindows(){
        taskTextArea.setText("");
        for (Task task : taskList) {
            taskTextArea.append(task.toString() + "\n");
        }
    }
}

