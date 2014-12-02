package mesh.gui;

import mesh.algorithms.RNCAlgorithm;
import mesh.model.Mesh;
import mesh.model.Task;
import mesh.providers.MeshProvider;
import mesh.providers.TaskListProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * Created by MM on 2014-12-02.
 */
public class AlgoritmsFrame extends JFrame {

    private JPanel rootPanel;
    private JButton firstFitButton;
    private JButton wsbaButton;
    private JButton busyListButton;
    private JButton rncButton;
    private JTextArea resultTextArea;

    private Mesh mesh;
    private List<Task> taskList;


    public AlgoritmsFrame(Mesh mesh, List<Task> taskList) {
        super("Algorithms");
        //this.mesh = mesh;
        this.taskList = taskList;
        //mesh.printArray();
        addActionListeners();
        setContentPane(rootPanel);
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        resultTextArea.append("MESH Width: " + MeshProvider.getMesh().getMeshWidth() + " Height: " + MeshProvider.getMesh().getMeshHeight() + "\n");
        resultTextArea.append("Task list size: "+TaskListProvider.getTaskList().size());

    }

    public void addActionListeners(){
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
                RNCAlgorithm rncAlgorithm = new RNCAlgorithm();
                rncAlgorithm.run();
            }
        });


    }
}
