package mesh.model;


import mesh.providers.MeshProvider;

public class Mesh {

    private int meshWidth, meshHeight;

    private int[][] grid;


    public Mesh(int w, int h) {
        System.out.println("New MESH" + w + " x " + h);
        this.meshWidth = w;
        this.meshHeight = h;
        grid = new int[meshWidth][meshHeight];
//        grid = new int[meshHeight][meshWidth];

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
                    grid[j % (getMeshWidth())][i] = allocatingTask.getId();
//                    System.out.println("j % (getMeshWidth():" + j % getMeshWidth());
//                    MeshProvider.getMesh().printArray();
                } else {
                    grid[j][i] = allocatingTask.getId();
//                    MeshProvider.getMesh().printArray();
                }
            }
        }
    }

    public void removeTask(int startX, int startY, Task allocatingTask) {
        for (int i = startY; i < allocatingTask.getHeight() + startY; i++) {
            for (int j = startX; j < allocatingTask.getWidth() + startX; j++) {
                if ((allocatingTask.getWidth() + startX) >= getMeshWidth()) {
                    grid[j % getMeshWidth()][i] = 0;
                } else {
                    grid[j][i] = 0;

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
                    allocator += grid[j % getMeshWidth()][i];
                } else {
                    allocator += grid[j][i];

                }
            }
        }

        if (allocator == 0) {
            return true;
        } else
            return false;
    }

    public void countPoints(int task, int x, int y) {
        int counter =0;
        for (int i=0; y < getMeshHeight(); i++) {
            for (int j = 0; x < getMeshWidth(); j++) {

            }
        }
    }

    public void printArray() {
        System.out.println("");
        for (int i = 0; i < meshHeight; i++) {
            for (int j = 0; j < meshWidth; j++) {
//                System.out.println("grid[i].length: " + grid[i].length);
//                System.out.println("grid.length"+grid.length);
//                System.out.println("j: " + j + " i: " + i);
                System.out.print(grid[j][i] + " ");
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
