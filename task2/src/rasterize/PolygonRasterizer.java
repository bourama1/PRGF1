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
        int size = polygon.getPointsSize();
        for (int i = 0; i < size; i++){
            //Pro prvni bod vykreslim pouze pixel
            if (i == 0){
                lr.raster.setPixel(polygon.getPoint(i).getX(), polygon.getPoint(i).getY(), outlineColor.getRGB());
            } else {
                lr.rasterize(polygon.getPoint(i - 1), polygon.getPoint(i), outlineColor);
            }
        }
        //Propojeni prvniho a posledniho bodu polygonu
        lr.rasterize(polygon.getPoint(0), polygon.getPoint(size - 1), outlineColor);
    }
}
