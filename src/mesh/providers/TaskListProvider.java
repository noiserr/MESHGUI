package mesh.providers;

import mesh.model.Mesh;
import mesh.model.Task;

import java.util.List;

/**
 * Created by MM on 2014-12-02.
 */
public class TaskListProvider {
    private static List<Task> taskListInstance;
    public static List<Task> getTaskList() {
        if(taskListInstance == null){
            return null;
        }else{
            return taskListInstance;

        }
    }
    public static void init(List<Task> taskList){
        taskListInstance = taskList;
    }

    private TaskListProvider() {
    }
}
