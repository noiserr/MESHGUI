package mesh.model;

/**
 * Created by MM on 2014-12-03.
 */
public class AllocatedTask extends Task {
    private int x, y;

    public AllocatedTask(Task task, int x, int y) {

        super(task.getId(), task.getWidth(),task.getHeight(), task.getTime());
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {

        return String.format("ID:%2d [%2dx%2d] t=%-3d x=%-3d y=%-3d", id, width, height, time, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
