package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;

public class PolygonRasterizer {
    private FilledLineRasterizer lr;
    public PolygonRasterizer(FilledLineRasterizer lineRasterizer){
        lr = lineRasterizer;
    }

    public void rasterize(Polygon polygon, Color outlineColor){
        for (int i = 0; i < polygon.points.size(); i++){
            if (i == 0){
                lr.raster.setPixel(polygon.points.get(i).getX(), polygon.points.get(i).getY(), outlineColor.getRGB());
            } else if (i == 1){
                lr.rasterize(polygon.points.get(0), polygon.points.get(i), outlineColor);
            } else if (i != polygon.points.size() - 1){
                lr.rasterize(polygon.points.get(i-1), polygon.points.get(i), outlineColor);
            } else if (i == polygon.points.size() - 1){
                lr.rasterize(polygon.points.get(0), polygon.points.get(i), outlineColor);
                lr.rasterize(polygon.points.get(i-1), polygon.points.get(i), outlineColor);
            }
        }
    }
}
