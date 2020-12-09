package solids;

import model.Solid;
import model.Vertex;
import transforms.Mat4;

import java.awt.*;

public class Tetrahedron extends Solid {
    public Tetrahedron(Mat4 model) {
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

        color.add(Color.MAGENTA.getRGB());

        this.model = model;
    }
}
