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
        int size = polygon.points.size();
        for (int i = 0; i < size; i++){
            //Pro prvni bod vykreslim pouze pixel
            if (i == 0){
                lr.raster.setPixel(polygon.points.get(i).getX(), polygon.points.get(i).getY(), outlineColor.getRGB());
            } else {
                lr.rasterize(polygon.points.get(i - 1), polygon.points.get(i), outlineColor);
            }
        }
        //Propojeni prvniho a posledniho bodu polygonu
        lr.rasterize(polygon.points.get(0), polygon.points.get(size - 1), outlineColor);
    }
}
