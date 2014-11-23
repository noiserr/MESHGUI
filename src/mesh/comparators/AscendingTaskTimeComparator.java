package mesh.comparators;

import mesh.model.Task;

import java.util.Comparator;

/**
 * Created by MM on 2014-11-23.
 */
public class AscendingTaskTimeComparator implements Comparator<Task> {
    public int compare(Task chair1, Task chair2) {
        return chair1.getTime() - chair2.getTime();
    }
}
