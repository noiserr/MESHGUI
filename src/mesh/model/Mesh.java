package mesh.model;

/**
 * Created by MM on 2014-11-22.
 */
public class Mesh {

    private int meshWidth, meshHeight;

    int[][] grid;


    public Mesh(int w, int h) {
        this.meshWidth = w;
        this.meshHeight = h;

        grid = new int[meshWidth][meshHeight];
        fillArray();



    }

    public void fillArray(){
        for (int i = 0; i < meshWidth; i++) {
            for (int j = 0; j < meshHeight; j++) {
                grid[i][j]=0;
                System.out.print(grid[i][j]+ " ");
            }
            System.out.print("\n");
        }
    }

}
