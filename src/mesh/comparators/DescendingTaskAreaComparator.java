package mesh.comparators;

import mesh.model.Task;

import java.util.Comparator;

/**
 * Created by MM on 2014-12-02.
 */
public class DescendingTaskAreaComparator implements Comparator <Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return (task2.getHeight() * task2.getWidth()) - (task1.getHeight() * task1.getWidth());

    }
}
