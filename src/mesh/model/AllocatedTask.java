package mesh.model;

/**
 * Created by MM on 2014-12-03.
 */
public class AllocatedTask extends Task {
    private int x, y;

    public AllocatedTask(int id, int w, int h, int t, int x, int y) {
        super(id, w, h, t);
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {


        String format = "ID:%2d [%2dx%2d] t=%-3d x=%-3d y=%-3d";

        String someLine;
        someLine = String.format(format, id, width, height, time, x, y);
        return someLine;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
