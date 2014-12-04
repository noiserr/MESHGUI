package mesh.model;


import mesh.providers.MeshProvider;

public class Mesh {

    private int meshWidth, meshHeight;

    private int[][] grid;


    public Mesh(int w, int h) {
        System.out.println("New MESH"+w + " x "+h);
        this.meshWidth = w;
        this.meshHeight = h;
        grid = new int[meshHeight][meshWidth];
        fillArray();
    }

    public void fillArray() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = 0;
            }
        }
    }

    public void allocateTask(int startX, int startY, Task allocatingTask) {

        for (int i = startX; i < allocatingTask.getHeight() + startX; i++) {
            for (int j = startY; j < allocatingTask.getWidth() + startY; j++) {
                if ((allocatingTask.getWidth() + startY) > getMeshWidth()) {
                    grid[i][j % (getMeshWidth())] = allocatingTask.getId();
                    System.out.println("j % (getMeshWidth():" + j % getMeshWidth());
                    MeshProvider.getMesh().printArray();
                } else {
                    grid[i][j] = allocatingTask.getId();
                    MeshProvider.getMesh().printArray();
                }
            }
        }
    }

    public void removeTask(int startX, int startY, Task allocatingTask) {
//        System.out.println("Removing task: " + allocatingTask.getId());
//        System.out.println("ATWidth: " + allocatingTask.getWidth() + " ATHeight: " + allocatingTask.getHeight());
        for (int i = startX; i < allocatingTask.getHeight() + startX ; i++) {
            for (int j = startY; j < allocatingTask.getWidth() + startY; j++) {
                if ((allocatingTask.getWidth() + startY) >= getMeshWidth()) {
                    grid[i][j % getMeshWidth()] = 0;
                } else {
                    grid[i][j] = 0;

                }
            }
        }
    }

    //Check if small grid is free
    public boolean gridIsFree(int startX, int startY, Task allocatingTask) {
        int allocator = 0;
        for (int i = startX; i < allocatingTask.getHeight() + startX; i++) {
            for (int j = startY; j < allocatingTask.getWidth() + startY; j++) {
                if((allocatingTask.getHeight() + startX) >= (getMeshWidth())){
                    return false;
                }else if ((allocatingTask.getWidth() + startY) > getMeshWidth()) {
                    allocator += grid[i][j % getMeshWidth()];
                } else {
                    allocator += grid[i][j];

                }
            }
        }

        if (allocator == 0) {
            return true;
        } else
            return false;
    }


    public void printArray() {
        System.out.println("");
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                System.out.print(grid[i][j] + " ");
                //System.out.println();
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }



    public int getMeshWidth() {
        return meshWidth;
    }

    public int getMeshHeight() {
        return meshHeight;
    }

}
