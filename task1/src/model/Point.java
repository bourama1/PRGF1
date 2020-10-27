package model;

public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getY(){return y;}
    public int getX(){return x;}

    double distanceTo(Point p){
        return Math.sqrt((p.getY() - this.y) * (p.getY() - this.y) + (p.getX() - this.x) * (p.getX() - this.x));
    }
}
