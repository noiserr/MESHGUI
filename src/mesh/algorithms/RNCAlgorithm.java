package mesh.algorithms;

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
//                               = TaskListProvider.getTaskList();
    private List<Task> taskList;
    private List<Task> allocatedTaskList = new ArrayList<Task>();
    private Mesh mesh = MeshProvider.getMesh();
    private int[][] grid = mesh.getGrid();

    private Random random = new Random();


    public void run() {
//        taskList = new ArrayList<Task>();
        taskList = TaskListProvider.getTaskList();
        //taskList.add(new Task(1,4,2,1));
        //taskList.add(new Task(1,3,4,1));
        //taskList.add(new Task(9,3,2,1));

        Task temporaryTask = taskList.get(0);
        for (Task task : taskList) {
            System.out.println(task);
        }
//        taskList.remove(0);

//        int x = random.nextInt(mesh.getMeshWidth() + 1);
//        int y = random.nextInt(mesh.getMeshHeight() + 1);
//        tryToAlloacte(0,0, temporaryTask);
        mesh.allocateTask(0,0,temporaryTask);
        mesh.printArray();
        System.out.println();
        temporaryTask = taskList.get(1);
        mesh.allocateTask(1,1,temporaryTask);
        mesh.printArray();






    }

    public boolean gridIsFree(int x, int y, Task currentTask){
        int allocator =0;
        for (int i = x; i < currentTask.getHeight(); i++) {
            for (int j = y; j < currentTask.getWidth(); j++) {
                allocator += grid[i][j];
            }
        }

        if(allocator ==0){
            //System.out.println(allocator);
            return true;
        }else
            return false;
    }

    public void tryToAlloacte(int x, int y, Task currentTask){
        if(gridIsFree(x,y,currentTask)){
            mesh.allocateTask(0, 0, currentTask);
        }
        else{

        }
    }
}