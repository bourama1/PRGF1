package clip;

import model.Point;
import model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Clipper {

    public Clipper(){

    }

    public static Polygon clip(Polygon inPoly, Polygon clipPoly){
        int size = clipPoly.getPointsSize();
        List<Point> inPolyPoints = inPoly.getPoints(); //vytvorim si list bodu puvodniho polygonu abych si je neprepsal
        List<Point> newPoints = inPolyPoints;

        for (int i = 0; i < size; i++) {
            newPoints = clipEdge(inPolyPoints, new Edge(i, clipPoly));
            inPolyPoints = newPoints;
        }
        return new Polygon(newPoints);
    }

    private static List<Point> clipEdge(List<Point> points, Edge e) {
        if (points.size() < 2)
            return points;
        List<Point> out = new ArrayList<>();
        out.clear();
        Point v1 = points.get(points.size() - 1);
        for (Point v2 : points) {
            if (e.inside(v2)) {
                if (!e.inside(v1))
                    out.add(e.intersection(v1, v2));
                out.add(v2);
            } else if (e.inside(v1)) {
                out.add(e.intersection(v1, v2));
            }
            v1 = v2;
        }
        return out;
    }
}
