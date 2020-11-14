package fill;

import model.Line;
import model.Polygon;

import java.util.List;

public class ScanLine implements Filler {
    private Polygon poly;
    private List<Line> lines;
    private int fillColor;
    private int outlineColor;

    @Override
    public void fill() {

    }

    /*private void process(){
        int yMin=0, yMax=0;
        List<Integer> intersections;
        for (int i=0; i<poly.points.size(); i++){
            Line line = new Line(
                    poly.points.get(i),
                    poly.points.get((i+1)%poly.points.size()),
                    0x0
            );
            if(!line.isHorizontal())
                lines.add(line.setOrientation());
            //TODO najdi yMax, yMin
        }

        for(int y = yMax; y>yMin; y--){
            for (Line line:lines) {
                if (line.isIntersection(y)){
                    int x = line.getIntersection(y);
                    intersections.add(x);
                }
            }
            //TODO implemntuj sort podle x
            for (int i=0; i<intersections.size()-1; i+=2){
                //TODO vypln podle fillColor
            }

        }

    }*/
}

