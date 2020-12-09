package solids;

import model.Solid;
import model.Vertex;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Axis extends Solid {
    public Axis(){
        super();
        vertices.add(new Vertex(0, 0, 0));
        vertices.add(new Vertex(5, 0, 0));
        vertices.add(new Vertex(0, 5, 0));
        vertices.add(new Vertex(0, 0, 5));

        indices.add(0); indices.add(1);
        indices.add(0); indices.add(2);
        indices.add(0); indices.add(3);

        color.add(16711680);
        color.add(65280);
        color.add(255);
    }
}
