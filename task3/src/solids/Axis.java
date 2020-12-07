package solids;

import model.Solid;
import model.Vertex;

public class Axis extends Solid {
    public Axis(){
        super();
        vertices.add(new Vertex(0, 0, 0));
        vertices.add(new Vertex(10, 0, 0));
        vertices.add(new Vertex(0, 10, 0));
        vertices.add(new Vertex(0, 0, 10));
        indices.add(0); indices.add(1);
        indices.add(0); indices.add(2);
        indices.add(0); indices.add(3);
    }
    //Todo

}
