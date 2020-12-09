package control;

import model.Scene;
import model.Solid;
import rasterize.FilledLineRasterizer;
import rasterize.Raster;
import render.Renderer;
import solids.Axis;
import solids.Box;
import solids.Curve;
import solids.Tetrahedron;
import transforms.*;
import view.Panel;

import javax.swing.*;
import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private int x, y, h, w, n;
    private boolean per = false;
    private Scene scene;
    private Renderer renderer;
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
        FilledLineRasterizer lineRasterizer = new FilledLineRasterizer(raster);
        renderer = new Renderer(lineRasterizer, raster.getImg(), raster);
        Mat4 model = new Mat4Identity();
        renderer.setModel(model);
        scene = new Scene();
        scene.getSolids().add(new Axis());
        scene.getSolids().add(new Tetrahedron(model));
        scene.getSolids().add(new Box(model));
        scene.getSolids().add(new Curve(model));
        h = raster.getHeight();
        w = raster.getWidth();
        n = 1;
    }

    public void update() {
        panel.clear();
        setProjection();
        renderer.setView(camera.getViewMatrix());
        setActive();
        renderer.render(scene);
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    x = e.getX();
                    y = e.getY();
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)){
                    camera = camera.addAzimuth((Math.PI * (x - e.getX()) / w));
                    camera = camera.addZenith((Math.PI * (e.getY() - y) / h));
                    x = e.getX();
                    y = e.getY();
                }
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //Camera
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    camera = camera.forward(0.1);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    camera = camera.backward(0.1);
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    camera = camera.left(0.1);
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    camera = camera.right(0.1);
                }
                //Rotations
                if (e.getKeyCode() == KeyEvent.VK_Y) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4RotY(0.1).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4RotX(0.1).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4RotZ(0.1).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                //Scale
                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Scale(1.1).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Scale(0.9).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                //Translations
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(-0.5,0,0).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(0.5,0,0).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(0,-0.5,0).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(0,0.5,0).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(0,0,-0.5).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    Mat4 m = scene.getActiveSolid().getModel();
                    m = new Mat4Transl(0,0,0.5).mul(m);
                    scene.getActiveSolid().setModel(m);
                }
                //Change of active solid
                if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    n = (n + 1) % (scene.getSolids().size() - 1) + 1;
                }
                //Projection
                if(e.getKeyCode() == KeyEvent.VK_P) {
                    per = !per;
                }
                update();
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
                update();
            }
        });
    }

    private void setActive() {
        for (Solid solid: scene.getSolids()) {
            solid.setActive(scene.getSolids().indexOf(solid) == n);
        }
    }

    private void setProjection() {
        if (per)
            renderer.setProjection(new Mat4OrthoRH((w / 50.0), (h / 50.0), 0.1, 10));
        else
            renderer.setProjection(new Mat4PerspRH(Math.PI/2, h/(double)w, 0.1, 10));
    }
}