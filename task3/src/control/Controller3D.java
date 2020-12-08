package control;

import model.Scene;
import rasterize.FilledLineRasterizer;
import rasterize.Raster;
import render.Renderer;
import solids.Axis;
import solids.Box;
import solids.Tetrahedron;
import transforms.*;
import view.Panel;

import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private int x, y, h, w;
    private float angle;
    private Scene scene;
    private Renderer renderer;
    private FilledLineRasterizer lineRasterizer;
    private Camera camera = new Camera()
            .withPosition(new Vec3D(-5, 0 ,0))
            .withAzimuth(0)
            .withZenith(0);

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        update();
    }

    public void initObjects(Raster raster) {
        lineRasterizer = new FilledLineRasterizer(raster);
        scene = new Scene();
        renderer = new Renderer(lineRasterizer, raster.getImg(), raster);
        h = raster.getHeight();
        w = raster.getWidth();
    }

    public void update(){
        panel.clear();
        Mat4 model = new Mat4RotXYZ(angle * Math.PI/10, Math.PI/5, Math.PI/7);
        renderer.setProjection(new Mat4PerspRH(Math.PI/2, h/(double)w, 0.1, 10));
        renderer.setView(camera.getViewMatrix());

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

            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    update();
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
}