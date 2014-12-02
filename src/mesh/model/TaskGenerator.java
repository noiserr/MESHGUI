package mesh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MM on 2014-11-22.
 */
public class TaskGenerator {
    private int minW, minH, minT, maxW, maxH, maxT, taskNum;

    Random r;
    List taskList;



    public TaskGenerator(int minW, int minH, int minT, int maxW, int maxH, int maxT, int taskNum) {
        taskList = new ArrayList<Task>();
        this.minW = minW;
        this.minH = minH;
        this.minT = minT;
        this.maxW = maxW;
        this.maxH = maxH;
        this.maxT = maxT;
        this.taskNum = taskNum;
        r = new Random();

    }

    public List<Task> gen(){

        for(int i =0 ; i < taskNum; i++){
            int width = r.nextInt(maxW-minW+1)+minW;
            int height = r.nextInt(maxH-minH+1)+minH;
            int time = r.nextInt(maxT-minT+1)+minT;
            taskList.add(new Task(i+1,width,height,time));

        }
        return taskList;
//        for (Object o : taskList) {
//            System.out.println(o);
//        }
    }
}

