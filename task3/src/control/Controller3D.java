package control;

import clip.Clipper;
import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Point;
import model.Polygon;
import model.Scene;
import rasterize.DashLineRasterizer;
import rasterize.FilledLineRasterizer;
import rasterize.PolygonRasterizer;
import rasterize.Raster;
import solids.*;
import view.Panel;
import render.Renderer;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private int x,y, n = 0;
    private boolean dashedPolygon = false;
    private SeedFill seedFill;
    private SeedFillBorder seedFillBorder;
    private ScanLine scanLine;
    private final Polygon polygon = new Polygon();
    private final Polygon clipPolygon = new Polygon();
    private Polygon outPolygon = new Polygon();
    private PolygonRasterizer polygonRasterizer;
    private Scene scene;
    private Renderer renderer;
    private FilledLineRasterizer lineRasterizer;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        draw();
    }

    public void initObjects(Raster raster) {
        lineRasterizer = new FilledLineRasterizer(raster);
        scene = new Scene();
        renderer = new Renderer(lineRasterizer, raster.getImg());
        seedFill = new SeedFill(raster);
        seedFillBorder = new SeedFillBorder(raster);
        scanLine = new ScanLine(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer, new DashLineRasterizer(raster));
    }

    public void draw(){
        scene.getSolids().add(new Axis());
        scene.getSolids().add(new Tetrahedron());
        scene.getSolids().add(new Box());
        renderer.render(scene);
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
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    reset();
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

    private void reset() {
        hardClear();
        scene.clear();
        draw();
    }
}