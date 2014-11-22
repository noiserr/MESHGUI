package mesh.model;

/**
 * Created by MM on 2014-11-22.
 */
public class Task {
    private int id;
    private int height;
    private int width;
    private int time;

    public Task(int id, int w, int h, int t) {
        this.id = id;
        this.height = h;
        this.width = w;
        this.time = t;
    }

    @Override
    public String toString() {


        String format = "ID:%-3d [%-1dx%-1d] t=%-3d";
        String someLine;
        someLine = String.format(format, id, width, height, time);
        return someLine;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTime() {
        return time;
    }
}
