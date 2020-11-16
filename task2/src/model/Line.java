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
        return this;
    }

    public boolean isIntersection(int y) {
        return (y >= p1.getY()) && ( y <= p2.getY());
    }

    public int getIntersection(int y) {
        return 0; //x=ky+q;
    }

    //TODO

}