package model;

public class Line {
    private final Point p1;
    private final Point p2;
    private final int color;

    public Line(Point p1, Point p2, int color) {
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
        this.color = color;
    }

    public Point GetP1(){return p1;}

    public Point GetP2(){return p2;}

    public int GetColor(){return color;}

    public boolean isHorizontal() {
        return p1.getY() == p2.getY();
    }

    public Line setOrientation() {
        if (p1.getY() <= p2.getY())
            return this;
        return new Line(p2, p1, color);
    }

    public boolean isIntersection(int y) {
        return (y >= p1.getY()) && ( y < p2.getY());
    }

    public int getIntersection(int y) {
        float k = getK();
        float q = getQ(k);
        return Math.round(k * y + q);
    }

    private float getK() {
        return (float)(p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
    }

    private float getQ(float k) {
        return p1.getX() - k * p1.getY();
    }
}