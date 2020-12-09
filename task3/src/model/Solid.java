package model;

import transforms.Mat4;
import transforms.Mat4Identity;
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
    protected Mat4 model;
    protected boolean active;

    public Solid(){
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        color = new ArrayList<>();
        model = new Mat4Identity();
        active = false;
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

    public Mat4 getModel(){
        return model;}

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }
}
