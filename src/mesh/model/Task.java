package mesh.model;

/**
 * Created by MM on 2014-11-22.
 */
public class Task {
    protected int id;
    protected int height;
    protected int width;
    protected int time;

    public Task(int id, int w, int h, int t) {
        this.id = id;
        this.height = h;
        this.width = w;
        this.time = t;
    }

    public void timeLapse(){
        time--;
    }

    @Override
    public String toString() {

        return String.format("ID:%2d [%2dx%2d] t=%-3d", id, height, width, time);
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
