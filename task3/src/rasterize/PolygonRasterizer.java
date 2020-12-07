package rasterize;

import model.Polygon;

import java.awt.*;

public class PolygonRasterizer {
    private final FilledLineRasterizer lr;
    private final DashLineRasterizer rasterizerDashed;
    public PolygonRasterizer(FilledLineRasterizer lineRasterizer, DashLineRasterizer rasterizerDashed){
        lr = lineRasterizer;
        this.rasterizerDashed = rasterizerDashed;
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

    public void rasterizeDash(Polygon polygon, int x, int y) {
        int i = polygon.getPointsSize() - 1;
        rasterizerDashed.rasterize(polygon.getPoint(0).getX(), polygon.getPoint(0).getY(), x, y, Color.RED);
        rasterizerDashed.rasterize(polygon.getPoint(i).getX(), polygon.getPoint(i).getY(), x, y, Color.RED);
    }
}
