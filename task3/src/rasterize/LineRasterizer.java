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
        drawLine(line.GetP1().getX(), line.GetP1().getY(), line.GetP2().getX(), line.GetP2().getY());
    }

    public void rasterize(Point p1, Point p2, Color color) {
        setColor(color);
        drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        setColor(color);
        drawLine(x1, y1, x2, y2);
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {

    }
}