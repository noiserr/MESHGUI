package mesh.model;


import mesh.algorithms.BusyList;
import mesh.providers.MeshProvider;

public class Mesh {

    private int meshWidth, meshHeight;

    private int[][] grid;


    public Mesh(int w, int h) {
        System.out.println("New MESH" + w + " x " + h);
        this.meshWidth = w;
        this.meshHeight = h;
        grid = new int[meshWidth][meshHeight];

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
                    //System.out.println("grid (" +j+ "," +i+ ")");
                } else {
                    allocator += grid[j][i];
                    //System.out.println("grid (" +j+ "," +i+ ")");
                }
            }
        }

        if (allocator == 0) {
            return true;
        } else
            return false;
    }

    public int countPoints(int x, int y, int task_number,  Task counting_task) {
        int points =0;

        int [][] help_grid = new int [getMeshWidth()+1][getMeshHeight()+2];

        for (int row = 0; row < help_grid.length; row++) {
            for (int col = 0; col < help_grid[row].length; col++) {
                help_grid[row][col] = 0;
            }
        }

        for (int i=1; i < getMeshHeight()+1; i++) {
            for (int j = 1; j < getMeshWidth()+1; j++) {
                help_grid[j][i] = grid[j-1][i-1];
            }
        }

        for (int i = 1; i < getMeshHeight()+1; i++) {
            help_grid[0][i] = help_grid[getMeshWidth()][i];
        }

        int stopX;
        int stopY;
        if (counting_task.getHeight() == getMeshHeight()) {
             stopY = counting_task.getHeight() + 1;
        } else {
             stopY = counting_task.getHeight() + 2;
        }

        if (counting_task.getWidth() == getMeshWidth()){
            stopX = counting_task.getWidth() + 1;
        } else if(x + counting_task.getWidth() == getMeshWidth()) {
            stopX = counting_task.getWidth() + 3;
        }else {
            stopX = counting_task.getWidth() + 2;
        }

        for (int i = y; i < stopY; i++) {
            for (int j = x; j < stopX; j++) {
                if (help_grid[i][j] != (task_number + 1) && help_grid[i][j] != 0) {
                    points += 1;
                }
            }
        }
/*
        System.out.println("");
        for (int i = 0; i < getMeshHeight()+2; i++) {

            for (int j = 0; j < getMeshWidth()+1; j++) {

                System.out.print(help_grid[j][i] + " ");
                //System.out.println();
            }
            System.out.println();
        }
        System.out.println("------------------------");
*/
        return points;
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
