package model;

import transforms.Mat4;
import transforms.Point3D;

public class Vertex {
    Point3D position;

    public Vertex(double x, double y, double z){
        position = new Point3D(x,y,z);
    }

    public Vertex(Point3D p){
        //this(p.getX(), p.getY(), p.getY());
        position = p;
        //position = new Point3D(p);

    }

    public Vertex transform(Mat4 matrix) {
        //Point3D p = position.mul(matrix);
        //return new Vertex(p.getX(), p.getY(), p.getZ());
        return  new Vertex(position.mul(matrix));
    }
    //color
    //...
}
