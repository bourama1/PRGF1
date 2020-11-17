package control;

import clip.Clipper;
import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Point;
import model.Polygon;
import rasterize.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller2D implements Controller {

    private final Panel panel;

    private int x,y;
    private LineRasterizerGraphics rasterizer;
    private SeedFill seedFill;
    private SeedFillBorder seedFillBorder;
    private ScanLine scanLine;
    private Polygon polygon = new Polygon();
    private Polygon clipPolygon = new Polygon();
    private Polygon outPolygon = new Polygon();
    private PolygonRasterizer polygonRasterizer;
    private Clipper polygonClip;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        seedFill = new SeedFill(raster);
        seedFillBorder = new SeedFillBorder(raster);
        scanLine = new ScanLine(raster);
        rasterizer = new LineRasterizerGraphics(raster);
        rasterizer.setColor(0x00ff00);
        polygonRasterizer = new PolygonRasterizer(new FilledLineRasterizer(raster));
        polygonClip = new Clipper();
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    Point p = new Point(e.getX(), e.getY());
                    polygon.points.add(p);
                    updatePolygon();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    if (SwingUtilities.isMiddleMouseButton(e)) {
                        seedFill.setSeed(e.getX(), e.getY());
                        seedFill.fill();
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    Point p = new Point(e.getX(), e.getY());
                    clipPolygon.points.add(p);
                    updatePolygon();
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //mazani datovych typu a vycisteni platna
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    x = 0;
                    y = 0;
                    polygon.clear();
                    clipPolygon.clear();
                    outPolygon.clear();
                    hardClear();
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void updatePolygon() {
        hardClear();
        if (clipPolygon.points.size() >= 3)
            outPolygon = Clipper.clip(polygon, clipPolygon);
        if (polygon.points.size() > 0)
            polygonRasterizer.rasterize(polygon, Color.CYAN);
        if (clipPolygon.points.size() > 0)
            polygonRasterizer.rasterize(clipPolygon, Color.RED);
        if (outPolygon.points.size() > 0) {
            polygonRasterizer.rasterize(outPolygon, Color.WHITE);
            scanLine.setFillColor(Color.WHITE.getRGB());
            scanLine.setOutlineColor(Color.WHITE);
            scanLine.setPoly(outPolygon);
            scanLine.fill();
        }
    }

    private void hardClear() {
        panel.clear();
    }

}