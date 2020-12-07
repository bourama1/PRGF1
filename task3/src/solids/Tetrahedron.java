package solids;

import model.Solid;
import model.Vertex;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Tetrahedron extends Solid {
    public Tetrahedron() {
        super();
        vertices.add(new Vertex(0, 0, 0));
        vertices.add(new Vertex(1, 0, 0));
        vertices.add(new Vertex(0, 1, 0));
        vertices.add(new Vertex(0, 0, 1));

        indices.add(0); indices.add(1);
        indices.add(0); indices.add(2);
        indices.add(0); indices.add(3);
        indices.add(1); indices.add(2);
        indices.add(1); indices.add(3);
        indices.add(2); indices.add(3);
    }
}
