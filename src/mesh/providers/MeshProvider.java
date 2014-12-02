package mesh.providers;

import mesh.model.Mesh;

/**
 * Created by MM on 2014-12-02.
 */
public class MeshProvider {
    private static Mesh meshInstance;
    public static Mesh getMesh() {
        if(meshInstance == null){
            return null;
        }else{
            return meshInstance;

        }
    }
    public static void init(int w, int h){
        meshInstance = new Mesh(w,h);
    }

    private MeshProvider() {
    }
}
