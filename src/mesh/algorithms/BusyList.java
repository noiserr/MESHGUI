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
public class BusyList {
    //Copy task list from TaskListProvider, IMPORTANT!
    private List<Task> taskList = new ArrayList<Task>(TaskListProvider.getTaskList());
    private List<AllocatedTask> allocatedTaskList = new ArrayList<AllocatedTask>();
    private Mesh mesh = MeshProvider.getMesh();
    private Random random = new Random();

    private int passedTime = 0;
    private int numberOfFailedAllocations = 0;
    private int frag =0;
    private int[] candidates = new int[4];


    public void run() {
        MeshProvider.getMesh().fillArray();

        while (true) {
            makeCandidates();
            for (int i=0; i < 4; i++) {
                System.out.println("Candidates[" +i+ "] = " +candidates[i]);
            }
            if (mesh.gridIsFree(candidates[2], candidates[3], taskList.get(candidates[0]))) {
                mesh.allocateTask(candidates[2], candidates[3], taskList.get(candidates[0]));
                timeLapse();
                allocatedTaskList.add(new AllocatedTask(taskList.get(candidates[0]), candidates[3], candidates[2]));
                taskList.remove(candidates[0]);

            } else {
                timeLapse();
            }

            for (int i=0; i < 4; i++) {
                candidates[i] = 0;
            }

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

    public void makeCandidates() {
        for(int task_index=0; task_index < taskList.size(); task_index++) {
            for (int y=0; y < mesh.getMeshHeight(); y++) {
                for (int x=0; x < mesh.getMeshWidth(); x++) {
                    if (mesh.gridIsFree(x, y, taskList.get(task_index))) {
                        int pts = countPointsBorders(task_index, y);
                        pts += mesh.countPoints(x, y, task_index, taskList.get(task_index));
                        //System.out.println("Task: " +task_index+ " (" +x+ "," +y+ ")");
                        //System.out.println("Task_points: " +pts);

                        if (candidates[1] < pts){
                            candidates[0] = task_index;
                            candidates[1] = pts;
                            candidates[2] = x;
                            candidates[3] = y;
                        }
                    }
                }
            }
        }
    }

    public int countPointsBorders(int task, int y) {
        int points;
        //System.out.println("Task: " +task+ " (" +x+ "," +y+ ")");
        //System.out.println("Task_size (" +taskList.get(task).getWidth()+ "," +taskList.get(task).getHeight()+ ")");
        //System.out.println("Mesh: " +(mesh.getMeshHeight()-1));
        if (mesh.getMeshHeight() == taskList.get(task).getHeight()) {
        points = 2*taskList.get(task).getWidth();
        }
        else if (y == 0 || y == mesh.getMeshHeight()-taskList.get(task).getHeight()) {
        points = taskList.get(task).getWidth();
        }
        else {
            points = 0;
        }
        return points;
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

