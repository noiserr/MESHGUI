package mesh.model;


import mesh.providers.MeshProvider;

public class Mesh {

    private int meshWidth, meshHeight;

    private int[][] grid;


    public Mesh(int w, int h) {
        System.out.println("New MESH" + w + " x " + h);
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

        for (int i = startY; i < allocatingTask.getHeight() + startY; i++) {
            for (int j = startX; j < allocatingTask.getWidth() + startX; j++) {
                if ((allocatingTask.getWidth() + startX) > getMeshWidth()) {
                    grid[i][j % (getMeshWidth())] = allocatingTask.getId();
//                    System.out.println("j % (getMeshWidth():" + j % getMeshWidth());
//                    MeshProvider.getMesh().printArray();
                } else {
                    grid[i][j] = allocatingTask.getId();
//                    MeshProvider.getMesh().printArray();
                }
            }
        }
    }

    public void removeTask(int startX, int startY, Task allocatingTask) {
        for (int i = startY; i < allocatingTask.getHeight() + startY; i++) {
            for (int j = startX; j < allocatingTask.getWidth() + startX; j++) {
                if ((allocatingTask.getWidth() + startX) >= getMeshWidth()) {
                    grid[i][j % getMeshWidth()] = 0;
                } else {
                    grid[i][j] = 0;

                }
            }
        }
    }

    public void drawTask(int startX, int startY, Task allocatingTask) {
        for (int i = startY; i < allocatingTask.getHeight() + startY; i++) {
            for (int j = startX; j < allocatingTask.getWidth() + startX; j++) {
                if ((allocatingTask.getWidth() + startX) >= getMeshWidth()) {
                    grid[i][j % getMeshWidth()] = 9;
                } else {
                    grid[i][j] = 9;

                }
            }
        }
    }

    //Check if small grid is free
    public boolean gridIsFree(int startX, int startY, Task allocatingTask) {
        int allocator = 0;
        for (int i = startY; i < allocatingTask.getHeight() + startY; i++) {
            for (int j = startX; j < allocatingTask.getWidth() + startX; j++) {
                if ((allocatingTask.getHeight() + startY) > (getMeshHeight())) {
                    return false;
                } else if ((allocatingTask.getWidth() + startX) > getMeshWidth()) {
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

    public int countFreeNodes() {

        int numberOfFreeNodes =0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if(grid[row][col] == 0){
                    numberOfFreeNodes++;
                }

            }
        }
        return numberOfFreeNodes;
    }


    public int getMeshWidth() {
        return meshWidth;
    }

    public int getMeshHeight() {
        return meshHeight;
    }

}
