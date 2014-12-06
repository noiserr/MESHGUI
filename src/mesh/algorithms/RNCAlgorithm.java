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
 * Created by MM on 2014-12-02.
 */
public class RNCAlgorithm {

    //Copy task list from TaskListProvider, IMPORTANT!
    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    private List<AllocatedTask> allocatedTaskList = new ArrayList<AllocatedTask>();
    private Mesh mesh = MeshProvider.getMesh();
    private Random random = new Random();

    private int passedTime = 0;
    private int numberOfFailedAllocations = 0;

    public void run() {
        MeshProvider.getMesh().fillArray();


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

    public int getNumberOfFailedAllocations() {
        return numberOfFailedAllocations;
    }
}