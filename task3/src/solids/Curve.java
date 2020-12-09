package solids;

import model.Solid;
import model.Vertex;
import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class Curve extends Solid{
    public Curve(Mat4 model) {
        super();

        Cubic cubic = new Cubic(Cubic.BEZIER,
                new Point3D(1, 1, -1),
                new Point3D(-1, -1, 0),
                new Point3D(1, 1, 0),
                new Point3D(-1, -1, 1));

        getVertices().add(new Vertex(cubic.compute(0)));
        getVertices().add(new Vertex(cubic.compute(0.1)));
        getVertices().add(new Vertex(cubic.compute(0.2)));
        getVertices().add(new Vertex(cubic.compute(0.3)));
        getVertices().add(new Vertex(cubic.compute(0.4)));
        getVertices().add(new Vertex(cubic.compute(0.5)));
        getVertices().add(new Vertex(cubic.compute(0.6)));
        getVertices().add(new Vertex(cubic.compute(0.7)));
        getVertices().add(new Vertex(cubic.compute(0.8)));
        getVertices().add(new Vertex(cubic.compute(0.9)));
        getVertices().add(new Vertex(cubic.compute(1)));

        getIndices().add(0); getIndices().add(1);
        getIndices().add(1); getIndices().add(2);
        getIndices().add(2); getIndices().add(3);
        getIndices().add(3); getIndices().add(4);
        getIndices().add(4); getIndices().add(5);
        getIndices().add(5); getIndices().add(6);
        getIndices().add(6); getIndices().add(7);
        getIndices().add(7); getIndices().add(8);
        getIndices().add(8); getIndices().add(9);
        getIndices().add(9); getIndices().add(10);

        this.model = model;
    }
}
