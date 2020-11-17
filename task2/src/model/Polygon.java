package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Point> points = new ArrayList<>();

    public Polygon(){
    }

    public Polygon(List<Point> points){
        this.points = points;
    }

    public int getPointsSize(){
        return points.size();
    }

    public List<Point> getPoints(){
        return points;
    }

    public Point getPoint(int index){
        return points.get(index);
    }

    public void addPoint(Point p){
        points.add(p);
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