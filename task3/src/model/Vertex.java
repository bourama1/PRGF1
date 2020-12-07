package model;

import transforms.Mat4;
import transforms.Point3D;

public class Vertex {
    Point3D position;

    public Vertex(double x, double y, double z){
        position = new Point3D(x,y,z);
    }

    public Vertex(Point3D p){
        position = new Point3D(p);
    }

    public Vertex transform(Mat4 matrix) {
        return  new Vertex(position.mul(matrix));
    }

    public Point3D getPosition(){
        return position;
    }
    //color
    //...
}
