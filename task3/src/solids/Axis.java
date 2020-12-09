package solids;

import model.Solid;
import model.Vertex;

public class Axis extends Solid {
    public Axis(){
        super();

        vertices.add(new Vertex(0, 0, 0));
        vertices.add(new Vertex(3, 0, 0));
        vertices.add(new Vertex(0, 3, 0));
        vertices.add(new Vertex(0, 0, 3));

        indices.add(0); indices.add(1);
        indices.add(0); indices.add(2);
        indices.add(0); indices.add(3);

        color.add(16711680);
        color.add(65280);
        color.add(255);
    }
}
