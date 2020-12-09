package model;

import javax.swing.*;
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

    public Solid getActiveSolid() {
        for (Solid solid: solids) {
            if (solid.active)
                return solid;
        }
        return null;
    }
}
