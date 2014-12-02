package mesh.model;


public class Mesh {

    private int meshWidth, meshHeight;

    private int[][] grid;


    public Mesh(int w, int h) {
        this.meshWidth = w;
        this.meshHeight = h;

        grid = new int[meshHeight][meshWidth];
        fillArray();
   }

    public void fillArray(){
        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                grid[row][col] = 0;
            }
        }
    }

    public void allocateTask(int startX, int startY, Task allocatingTask){
        for (int i = startX; i < allocatingTask.getWidth()+startX ; i++) {
            for (int j = startY; j < allocatingTask.getHeight()+startY; j++) {
                grid[i][j]=allocatingTask.getId();
            }
        }
    }

    public void printArray(){
        for(int i = 0; i < grid.length; i++) {

            for(int j = 0; j < grid[i].length; j++) {

                System.out.print(grid[i][j] + " ");
                //System.out.println();
            }
            System.out.println();
        }
    }

    public int getMeshWidth() {
        return meshWidth;
    }

    public int getMeshHeight() {
        return meshHeight;
    }

    public int[][] getGrid() {
        return grid;
    }
}
