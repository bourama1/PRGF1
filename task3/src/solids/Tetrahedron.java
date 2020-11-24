package solids;

import model.Solid;
import model.Vertex;

public class Tetrahedron extends Solid {
    public Tetrahedron() {
        super();
        vertices.add(new Vertex(0, 0, 0));
        /*0, 0, 0)
        (1,0,0)
        (0,1,0)
        (0,0,1)*/
        indices.add(0); indices.add(1); // hrana 0-1
        indices.add(0); indices.add(2); // hrana 0-2
        //todo ...
    }
}
