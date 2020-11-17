package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    public List<Point> points = new ArrayList<>();

    public Polygon(){
    }

    public int closePoint(Point p){
        int index = 0;
        double dist, min = 1000;
        for (Point x : this.points){
            dist = p.distanceTo(x);
            if (dist <= min) {
                min = dist;
                index = this.points.indexOf(x);
            }
        }
        return index;
    }

    public void clear() {
        points.clear();
    }
}