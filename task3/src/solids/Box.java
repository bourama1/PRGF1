package solids;

import model.Solid;
import model.Vertex;
import transforms.Mat4;

import java.awt.*;

public class Box extends Solid {
    public Box(Mat4 model){
        super();

        vertices.add(new Vertex(1, 1, -1));
        vertices.add(new Vertex(1, -1, -1));
        vertices.add(new Vertex(-1, -1, -1));
        vertices.add(new Vertex(-1, 1, -1));
        vertices.add(new Vertex(1, 1, 1));
        vertices.add(new Vertex(1, -1, 1));
        vertices.add(new Vertex(-1, -1, 1));
        vertices.add(new Vertex(-1, 1, 1));

        indices.add(0); indices.add(1);
        indices.add(0); indices.add(3);
        indices.add(0); indices.add(4);
        indices.add(1); indices.add(2);
        indices.add(1); indices.add(5);
        indices.add(2); indices.add(3);
        indices.add(2); indices.add(6);
        indices.add(3); indices.add(7);
        indices.add(4); indices.add(5);
        indices.add(4); indices.add(7);
        indices.add(5); indices.add(6);
        indices.add(6); indices.add(7);

        color.add(Color.PINK.getRGB());

        this.model = model;
    }
}
