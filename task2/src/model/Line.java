package model;

public class Line {

    int  x1, x2, y1, y2;
    //private final int color;

    public Line(int x1, int y1, int x2, int y2, int color) {
        //TODO
    }

    public Line(Point p1, Point p2, int color) {
        //TODO
    }

    public boolean isHorizontal() {
        return y1 == y2;
    }

    public Line setOrientation() {
        return this;
    }

    public boolean isIntersection(int y) {
        return (y>=y1) && y<=y2;
    }

    public int getIntersection(int y) {
        return 0; //x=ky+q;
    }

    //TODO

}