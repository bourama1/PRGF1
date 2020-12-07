package fill;

import model.Line;
import model.Polygon;
import rasterize.FilledLineRasterizer;
import rasterize.LineRasterizer;
import rasterize.Raster;

import java.awt.*;
import java.util.ArrayList;

//ScanLine ktery meni take barvu ohraniceni vybarvene oblasti
//Implementovan BubbleSort, jehoz hlavni vyhodou je jednoduchost algoritmu a hlavni nevyhodou mozna slozitost az n^2

public class ScanLine implements Filler {
    private final Raster raster;
    private Polygon poly;
    private final ArrayList<Line> lines = new ArrayList<Line>();
    private int fillColor;
    private Color outlineColor;
    private final LineRasterizer lr;

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
        int yMin = poly.getPoint(0).getY(), yMax=0;
        int size = poly.getPointsSize();
        ArrayList<Integer> intersections = new ArrayList<Integer>();
        for (int i=0; i < size; i++){
            Line line = new Line(
                    poly.getPoint(i),
                    poly.getPoint((i+1) % size),
                    0x0
            );
            if(!line.isHorizontal())
                lines.add(line.setOrientation());

                //hledani yMin a yMax
                if (yMin >= poly.getPoint(i).getY())
                    yMin = poly.getPoint(i).getY();
                if (yMax <= poly.getPoint(i).getY())
                    yMax = poly.getPoint(i).getY();

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

        for (int i = 0; i < size; i++) {
            int x1 = (poly.getPoint(i)).getX();
            int y1 = (poly.getPoint(i)).getY();
            int x2 = (poly.getPoint((i + 1) % size)).getX();
            int y2 = (poly.getPoint((i + 1) % size)).getY();
            this.lr.rasterize(x1, y1, x2, y2, outlineColor);
        }
    }

    private static void bubbleSort(ArrayList<Integer> array) {
        int temp;
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

