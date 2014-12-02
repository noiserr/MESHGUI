package mesh.comparators;

import mesh.model.Task;

import java.util.Comparator;

/**
 * Created by MM on 2014-11-23.
 */
public class AscendingTaskTimeComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        return task1.getTime() - task2.getTime();
    }
}
