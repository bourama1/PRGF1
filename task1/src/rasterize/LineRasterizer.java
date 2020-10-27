package rasterize;

import model.Line;
import model.Point;

import java.awt.*;

public abstract class LineRasterizer {
    Raster raster;
    Color color;

    public LineRasterizer(Raster raster){
        this.raster = raster;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void rasterize(Line line) {
        setColor(line.GetColor());
        drawLine(line.GetP1(), line.GetP2());
    }

    public void rasterize(Point p1, Point p2, Color color) {
        setColor(color);
        drawLine(p1, p2);
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        setColor(color);
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        drawLine(p1, p2);
    }

    protected void drawLine(Point p1, Point p2) {

    }
}
