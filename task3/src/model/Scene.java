package model;

import java.util.ArrayList;

public class Scene {
    ArrayList<Solid> solids;
    public Scene() {
        this.solids = new ArrayList<>();
    }

    public ArrayList<Solid> getSolids(){
        return solids;
    }

    public Solid getActiveSolid() {
        for (Solid solid: solids) {
            if (solid.active)
                return solid;
        }
        return null;
    }
}
