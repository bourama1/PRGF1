package clip;

import model.Polygon;
import model.Point;

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

    Vertex p1,p2;
    Edge(int index, Polygon polygon){
        if (index == polygon.points.size() - 1) {
            p1 = new Vertex(polygon.points.get(index).getX(), polygon.points.get(index).getY());
            p2 = new Vertex(polygon.points.get(0).getX(), polygon.points.get(0).getY());
        } else {
            p1 = new Vertex(polygon.points.get(index).getX(), polygon.points.get(index).getY());
            p2 = new Vertex(polygon.points.get(index + 1).getX(), polygon.points.get(index + 1).getY());
        }
    }

    public boolean inside(Point p) {
        Point v1 = new Point(this.p2.x - this.p1.x, this.p2.y - this.p1.y);
        Point n1 = new Point(-v1.getY(), v1.getX());
        Point v2 = new Point(p.getX() - this.p1.x, p.getY() - this.p1.y);
        return (n1.getX() * v2.getX() + n1.getY() * v2.getY() < 0.0D);
    }

    public double distance(Point p) {
        return Math.abs(((this.p2.y - this.p1.y) * p.getX() - (this.p2.x - this.p1.x) * p.getY() + this.p2.x * this.p1.y - this.p2.y * this.p1.x) /
                Math.sqrt((this.p2.y - this.p1.y) * (this.p2.y - this.p1.y) + (this.p2.x - this.p1.x) * (this.p2.x - this.p1.x)));
    }

    public Point intersection(Point v1, Point v2) {
        double px = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (this.p1.x - this.p2.x) - (this.p1.x * this.p2.y - this.p1.y * this.p2.x) * (v1.getX() - v2.getX())) / ((
                v1.getX() - v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x - this.p2.x) * (v1.getY() - v2.getY()));
        double py = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x * this.p2.y - this.p1.y * this.p2.x) * (v1.getY() - v2.getY())) / ((
                v1.getX() - v2.getX()) * (this.p1.y - this.p2.y) - (this.p1.x - this.p2.x) * (v1.getY() - v2.getY()));
        return new Point(px, py);
    }
}

