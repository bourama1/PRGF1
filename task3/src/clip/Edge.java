package clip;

import model.Point;
import model.Polygon;

public class Edge {
    private final Vertex p1;
    private final Vertex p2;
    Edge(int index, Polygon polygon){
        //Kontrola prekroceni hranice, v tomhle pripade vezmu prvni/nulty bod misto dalsiho
        if (index == polygon.getPointsSize() - 1) {
            p1 = new Vertex(polygon.getPoint(index).getX(), polygon.getPoint(index).getY());
            p2 = new Vertex(polygon.getPoint(0).getX(), polygon.getPoint(0).getY());
        } else {
            p1 = new Vertex(polygon.getPoint(index).getX(), polygon.getPoint(index).getY());
            p2 = new Vertex(polygon.getPoint(index + 1).getX(), polygon.getPoint(index + 1).getY());
        }
    }

    public boolean inside(Point p) {
        Point v1 = new Point(this.p2.x - this.p1.x, this.p2.y - this.p1.y);
        Point n1 = new Point(-v1.getY(), v1.getX());
        Point v2 = new Point(p.getX() - this.p1.x, p.getY() - this.p1.y);
        return (n1.getX() * v2.getX() + n1.getY() * v2.getY() < 0.0D);
    }

    public Point intersection(Point v1, Point v2) {
        double px = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (this.p1.x - this.p2.x) - (this.p1.x * this.p2.y - this.p1.y * this.p2.x) * (v1.getX() - v2.getX())) / ((
                v1.getX() - v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x - this.p2.x) * (v1.getY() - v2.getY()));
        double py = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x * this.p2.y - this.p1.y * this.p2.x) * (v1.getY() - v2.getY())) / ((
                v1.getX() - v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x - this.p2.x) * (v1.getY() - v2.getY()));
        return new Point(px, py);
    }

    private static class Vertex{
        private final double x;
        private final double y;

        Vertex(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

}

