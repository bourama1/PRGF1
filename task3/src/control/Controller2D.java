package control;

import clip.Clipper;
import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Point;
import model.Polygon;
import rasterize.DashLineRasterizer;
import rasterize.FilledLineRasterizer;
import rasterize.PolygonRasterizer;
import rasterize.Raster;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;

    private int x,y;
    private boolean dashedPolygon = false;
    private SeedFill seedFill;
    private SeedFillBorder seedFillBorder;
    private ScanLine scanLine;
    private final Polygon polygon = new Polygon();
    private final Polygon clipPolygon = new Polygon();
    private Polygon outPolygon = new Polygon();
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
        polygonRasterizer = new PolygonRasterizer(new FilledLineRasterizer(raster), new DashLineRasterizer(raster));
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isShiftDown()) {
                    if (SwingUtilities.isMiddleMouseButton(e)) {
                        seedFillBorder.setSeed(e.getX(), e.getY());
                        seedFillBorder.setBorderColor(Color.CYAN);
                        seedFillBorder.fill();
                    }
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    Point p = new Point(e.getX(), e.getY());
                    polygon.addPoint(p);
                    updatePolygon();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    seedFill.setSeed(e.getX(), e.getY());
                    seedFill.fill();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    Point p = new Point(e.getX(), e.getY());
                    clipPolygon.addPoint(p);
                    updatePolygon();
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (polygon.getPointsSize() > 0) {
                        x = e.getX();
                        y = e.getY();
                        dashedPolygon = true;
                        updatePolygon();
                    }
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

        if (dashedPolygon)
            polygonRasterizer.rasterizeDash(polygon, x, y);
        dashedPolygon = false;

        if (clipPolygon.getPointsSize() >= 3) {
            outPolygon = Clipper.clip(polygon, clipPolygon);
            polygonRasterizer.rasterize(outPolygon, Color.WHITE);
            scanLine.setFillColor(Color.WHITE.getRGB());
            scanLine.setOutlineColor(Color.WHITE);
            scanLine.setPoly(outPolygon);
            scanLine.fill();
        }
        if (polygon.getPointsSize() > 0)
            polygonRasterizer.rasterize(polygon, Color.CYAN);
        if (clipPolygon.getPointsSize() > 0)
            polygonRasterizer.rasterize(clipPolygon, Color.RED);
    }

    private void hardClear() {
        panel.clear();
    }

}