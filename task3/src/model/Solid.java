package model;

import java.util.ArrayList;
import java.util.List;

public class Solid {
    protected List<Vertex> vertices;
    protected List<Integer> indices;

    public Solid(){
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
    }
}
