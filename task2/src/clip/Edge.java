package clip;

import model.Polygon;

public class Edge {
    private class Vertex{
        double x,y;

        Vertex(Vertex v){
            this(v.x, v.y);
        }

        Vertex(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    Vertex v1,v2;
    Edge(int index, Polygon polygon){
        v1 = new Vertex(polygon.points.get(index).x, polygon.points.get(index).y);
        v2 = new Vertex(polygon.points.get(index+1).x, polygon.points.get(index+1).y);
    }

    void test(){
        Vertex a, b;
        a = new Vertex(1,2);
        b = new Vertex(a);
        a.x = 3;

    }
}

