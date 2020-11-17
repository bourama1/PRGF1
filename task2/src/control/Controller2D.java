package control;

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
    private PolygonRasterizer polygonRasterizer;

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
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    hardClear();
                    Point p = new Point(e.getX(), e.getY());
                    polygon.points.add(p);
                    polygonRasterizer.rasterize(polygon, Color.CYAN);
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    seedFill.setSeed(e.getX(),e.getY());
                    seedFill.fill();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    scanLine.setFillColor(Color.YELLOW.getRGB());
                    scanLine.setOutlineColor(Color.GREEN);
                    scanLine.setPoly(polygon);
                    scanLine.fill();
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
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //mazani datovych typu a vycisteni platna
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    x = 0;
                    y = 0;
                    polygon.points.clear();
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

    private void update() {
//        panel.clear();
        //TODO

    }

    private void hardClear() {
        panel.clear();
    }

}