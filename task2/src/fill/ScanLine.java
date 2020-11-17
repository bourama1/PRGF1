package fill;

import model.Line;
import model.Polygon;
import rasterize.FilledLineRasterizer;
import rasterize.LineRasterizer;
import rasterize.Raster;

import java.awt.*;
import java.util.*;

//ScanLine ktery meni take barvu ohraniceni vybarvene oblasti
//Implementovan BubbleSort, jehoz hlavni vyhodou je jednoduchost algoritmu a hlavni nevyhodou mozna slozitost az n^2

public class ScanLine implements Filler {
    private final Raster raster;
    private Polygon poly;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private int fillColor;
    private Color outlineColor;
    private LineRasterizer lr;

    public ScanLine(Raster raster) {
        this.raster = raster;
        this.lr  = new FilledLineRasterizer(raster);
    }

    public void setPoly(Polygon poly) {
        lines.clear();
        this.poly = poly;
    }

    public void setFillColor(int color){
        fillColor = color;
    }

    public void setOutlineColor(Color color){
        outlineColor = color;
    }

    @Override
    public void fill() {
        process();
    }

    private void process(){
        int yMin = poly.points.get(0).getY(), yMax=0;
        ArrayList<Integer> intersections = new ArrayList<Integer>();
        for (int i=0; i<poly.points.size(); i++){
            Line line = new Line(
                    poly.points.get(i),
                    poly.points.get((i+1)%poly.points.size()),
                    0x0
            );
            if(!line.isHorizontal())
                lines.add(line.setOrientation());

                //hledani yMin a yMax
                if (yMin >= poly.points.get(i).getY())
                    yMin = poly.points.get(i).getY();
                if (yMax <= poly.points.get(i).getY())
                    yMax = poly.points.get(i).getY();

        }

        for(int y = yMax; y > yMin; y--) {
            intersections.clear();
            for (Line line:lines) {
                if (line.isIntersection(y)){
                    int x = line.getIntersection(y);
                    intersections.add(x);
                }
            }

            bubbleSort(intersections);

            int k = intersections.size() - 1;
            while (k > 0) {
                for (int x = intersections.get(k - 1); x <= intersections.get(k); x++) {
                    raster.setPixel(x, y, fillColor);
                }
                k -= 2;
            }
        }

        for (int i = 0; i < poly.points.size(); i++) {
            int x1 = (poly.points.get(i)).getX();
            int y1 = (poly.points.get(i)).getY();
            int x2 = (poly.points.get((i + 1) % poly.points.size())).getX();
            int y2 = (poly.points.get((i + 1) % poly.points.size())).getY();
            this.lr.rasterize(x1, y1, x2, y2, outlineColor);
        }
    }

    private static void bubbleSort(ArrayList<Integer> array) {
        int temp = 0;
        int size = array.size();
        for(int i=0; i < size; i++){
            for(int j=1; j < (size-i); j++){
                if(array.get(j - 1) > array.get(j)){
                    temp = array.get(j - 1);
                    array.set(j - 1, array.get(j));
                    array.set(j, temp);
                }
            }
        }
    }
}

