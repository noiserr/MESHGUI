package mesh.algorithms;

import mesh.model.AllocatedTask;
import mesh.model.Mesh;
import mesh.model.Task;
import mesh.providers.MeshProvider;
import mesh.providers.TaskListProvider;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by MM on 2014-12-02.
 */
public class RNCAlgorithm {

    //Copy task list from TaskListProvider, IMPORTANT!
    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    private Thread t;
//    private List<Task> taskList;
    private List<AllocatedTask> allocatedTaskList = new ArrayList<AllocatedTask>();
    private Mesh mesh = MeshProvider.getMesh();
    private int[][] grid = mesh.getGrid();

    private Random random = new Random();
    private int passedTime = 0;
    private boolean running = true;

    public void run() {
        MeshProvider.getMesh().fillArray();
//        taskList = TaskListProvider.getTaskList();
//        taskList = new ArrayList<Task>();
//        taskList.add(new Task(1,11,10,1));
//        taskList.add(new Task(2,2,4,1));

//        taskList.add(new Task(2,2,3,4));
//        taskList.add(new Task(3,2,3,4));
////        taskList.add(new Task(4,2,3,4));
//        Task task1 = taskList.get(0);
//        Task task2 = taskList.get(1);
////
//        Task task3 = taskList.get(2);
//
//        tryToAllocate(0,0,task1);
//        tryToAllocate(task1);
//        MeshProvider.getMesh().printArray();
//        tryToAllocate(4,9,task3);
//
//        mesh.printArray();
////
//        System.out.println();
//        mesh.removeTask(allocatedTaskList.get(0).getX(), allocatedTaskList.get(0).getY(), allocatedTaskList.get(0));
//
//        mesh.printArray();






//        AllocatedTask task2 = allocatedTaskList.get(0);

//        System.out.println("NTaskID: " +task1.getId() +" TaskWidth: " + task1.getWidth() + " TaskHeight: " + task1.getHeight() +" TaskTime: " + task1.getTime() );
//        System.out.println("ATaskID: " +task2.getId() +" TaskWidth: " + task2.getWidth() + " TaskHeight: " + task2.getHeight() +" TaskTime: " + task2.getTime() );


        while (true) {

            Task temporaryTask = taskList.get(0);
            tryToAllocate(temporaryTask);
            findAndRemoveFinished();
            if (taskList.isEmpty()) {
                do {
                    timeLapse();
                    findAndRemoveFinished();
                } while (!allocatedTaskList.isEmpty());
                break;
            }
        }
    }


    public void tryToAllocate(Task currentTask) {
        int x = random.nextInt(mesh.getMeshWidth());
        int y = random.nextInt(mesh.getMeshHeight());
//        int x=0; int y=0;
        taskList.remove(0);
        System.out.println("X: " +x+ " Y: "+y);
        if (mesh.gridIsFree(x, y, currentTask)) {


            mesh.allocateTask(x, y, currentTask);

            timeLapse();
            allocatedTaskList.add(new AllocatedTask(currentTask.getId(), currentTask.getWidth(),
                    currentTask.getHeight(), currentTask.getTime(), y, x));
        } else {
            taskList.add(currentTask);
            timeLapse();
        }
    }

    public void timeLapse() {
        passedTime++;
        for (AllocatedTask allocatedTask : allocatedTaskList) {
            allocatedTask.timeLapse();

        }
    }

    public void findAndRemoveFinished() {

        for (int i = allocatedTaskList.size() - 1; i >= 0; i--) {
            if (allocatedTaskList.get(i).getTime() == 0) {
                mesh.removeTask(allocatedTaskList.get(i).getY(), allocatedTaskList.get(i).getX(), allocatedTaskList.get(i));
                allocatedTaskList.remove(allocatedTaskList.get(i));
            }


        }


    }



    public int getPassedTime() {
        return passedTime;
    }
}