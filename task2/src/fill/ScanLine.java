package fill;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;

public class ScanLine implements Filler {
    private Polygon poly;
    private List<Line> lines;

    @Override
    public void fill() {

    }

    private void process() {
        for (int i = 0; i < poly.points.size(); i++) {
            if (i + 1 <= poly.points.size()) {
                //Point x = poly.points.get(i).x;
                int y1 = poly.points.get(i).y;
                int x2 = poly.points.get(i + 1).x;
                int y2 = poly.points.get(i + 1).y;
                //Line line = new Line(x1, y1, x2, y2, Color.YELLOW.getRGB());
            } else {
                int x1 = poly.points.get(i).x;
                int y1 = poly.points.get(i).y;
                int x2 = poly.points.get(0).x;
                int y2 = poly.points.get(0).y;
                Line line = new Line(x1, y1, x2, y2, Color.YELLOW.getRGB());
            }
        }
    }
}
