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
 * Created by Mariusz on 2015-01-06.
 */
public class FirstFit {
    //Copy task list from TaskListProvider, IMPORTANT!
    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    private List<AllocatedTask> allocatedTaskList = new ArrayList<AllocatedTask>();
    private Mesh mesh = MeshProvider.getMesh();
    private Random random = new Random();

    private int passedTime = 0;
    private int numberOfFailedAllocations = 0;
    private int frag =0;
    private int[] candidates = new int[3];
    private int flag = 0;

    public void run() {
        MeshProvider.getMesh().fillArray();
        while (true) {

           calculateFit();
            makeFit(candidates[1], candidates[2], candidates[0]);

            for (int i=0; i < 3; i++) {
                candidates[i] = 0;
            }

            findAndRemoveFinished();
            flag = 0;

            if (taskList.isEmpty()) {
                do {
                    timeLapse();
                    findAndRemoveFinished();
                } while (!allocatedTaskList.isEmpty());


                break;
            }
        }
    }

    public void makeFit(int x, int y, int task_index) {

        if (mesh.gridIsFree(x, y, taskList.get(task_index))) {
            mesh.allocateTask(x, y, taskList.get(task_index));
            timeLapse();
            allocatedTaskList.add(new AllocatedTask(taskList.get(task_index), y, x));
            taskList.remove(task_index);
        } else {

            timeLapse();
        }
    }

    public void calculateFit() {
        for(int task_index=0; task_index < taskList.size(); task_index++) {
            if (flag == 1) break;
            for (int y=0; y < mesh.getMeshHeight(); y++) {
                if (flag == 1) break;
                for (int x = 0; x < mesh.getMeshWidth(); x++) {
                    if (mesh.gridIsFree(x, y, taskList.get(task_index))) {

                        candidates[0] = task_index;
                        candidates[1] = x;
                        candidates[2] = y;
                        flag = 1;
                        break;
                    }
                }
            }
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

//        System.out.println(frag);
//        MeshProvider.getMesh().printArray();
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
