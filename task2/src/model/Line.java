package model;

import java.awt.*;

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
        if (y1 > y2) {
            return this;
        }
        return new Line(x1, y2, x2, y1, Color.YELLOW.getRGB());
    }
    //TODO

}
