package model;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    ArrayList<Solid> solids;
    public Scene() {
        this.solids = new ArrayList<>();
    }

    public ArrayList<Solid> getSolids(){
        return solids;
    }

    public void clear() {
        solids.clear();
    }
}
