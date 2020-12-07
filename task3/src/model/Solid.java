package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Solid {
    protected List<Vertex> vertices;
    protected List<Integer> indices;
    protected List<Integer> indicesAxe;
    protected List<Integer> color;

    public Solid(){
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        indicesAxe = new ArrayList<>();
        color = new ArrayList<>();
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public List<Integer> getIndices(){
        return indices;
    }

    public List<Integer> getIndicesAxe(){
        return indicesAxe;
    }

    public List<Integer> getColor(){
        return color;
    }

    public void render(BufferedImage bufferedImage, boolean clip) {
    }
}
