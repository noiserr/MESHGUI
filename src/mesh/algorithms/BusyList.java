package mesh.algorithms;

import mesh.model.AllocatedTask;
import mesh.model.Mesh;
import mesh.model.Task;
import mesh.providers.MeshProvider;
import mesh.providers.TaskListProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mariusz on 2015-01-03.
 */
public class BusyList {
    //Copy task list from TaskListProvider, IMPORTANT!
    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    private List<AllocatedTask> allocatedTaskList = new ArrayList<AllocatedTask>();
    private Mesh mesh = MeshProvider.getMesh();
    private Random random = new Random();

    private int passedTime = 0;
    private int numberOfFailedAllocations = 0;
    private int frag =0;

    private int[] points = new int[taskList.size()];

    public void run() {
        MeshProvider.getMesh().fillArray();

        makeCandidates();
/*        while (true) {

//           Task temporaryTask = taskList.get(0);
//            tryToAllocate(temporaryTask);
//            findAndRemoveFinished();

            if (taskList.isEmpty()) {
                do {
                    timeLapse();
                    findAndRemoveFinished();
                } while (!allocatedTaskList.isEmpty());


                break;
            }
        }*/
    }

    public void makeCandidates() {
        for(int task_index=0; task_index < taskList.size(); task_index++) {
            for (int y=2; y < mesh.getMeshHeight(); y++) {
                for (int x=4; x < mesh.getMeshWidth(); x++) {
                    if (mesh.gridIsFree(x, y, taskList.get(task_index))) {
                        countPoints(task_index, x, y);
                    }
                }
            }
        }
    }

    public void countPoints(int task, int x, int y)
    {
        System.out.println("Task: " +task+ " (" +x+ "," +y+ ")");
       // System.out.println("Task_size (" +taskList.get(task).getWidth()+ "," +taskList.get(task).getHeight()+ ")");
       // System.out.println("Task_size: " +task+ " (" +x+ "," +y+ ")");
        if (y == 0 || y == mesh.getMeshHeight()-1 ) {
            points[task] = taskList.get(task).getWidth();
        }

    }

    public void tryToAllocate(Task currentTask) {
        int x = random.nextInt(mesh.getMeshWidth());
        int y = random.nextInt(mesh.getMeshHeight());
        taskList.remove(0);
       System.out.println("X: " +x+ " Y: "+y);


        if (mesh.gridIsFree(x, y, currentTask)) {
            mesh.allocateTask(x, y, currentTask);
            timeLapse();
            allocatedTaskList.add(new AllocatedTask(currentTask, y, x));
        } else {
            numberOfFailedAllocations++;
            taskList.add(currentTask);
            timeLapse();
        }
    }

    public void timeLapse() {

        if(!allocatedTaskList.isEmpty()){
            frag += MeshProvider.getMesh().countFreeNodes();
        }

        passedTime++;
        for (AllocatedTask allocatedTask : allocatedTaskList) {
            allocatedTask.timeLapse();

        }

        System.out.println(frag);
        MeshProvider.getMesh().printArray();
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

    public int getNumberOfFailedAllocations() {
        return numberOfFailedAllocations;
    }

    public float getFragmentation(){

        float v = (float) frag/(MeshProvider.getMesh().getMeshWidth() * MeshProvider.getMesh().getMeshHeight() * passedTime);
        return v;

    }
}

