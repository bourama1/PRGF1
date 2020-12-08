package model;

import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solid {
    protected List<Vertex> vertices;
    protected List<Integer> indices;
    protected List<Integer> color;

    public Solid(){
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        color = new ArrayList<>();
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public List<Integer> getIndices(){
        return indices;
    }

    public List<Integer> getColor(){
        return color;
    }
}
