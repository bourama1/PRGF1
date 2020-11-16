package control;

import fill.SeedFill;
import fill.SeedFillBorder;
import rasterize.*;
import view.Panel;

import javax.swing.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;

    private int x,y;
    private LineRasterizerGraphics rasterizer;
    private SeedFillBorder seedFill;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        seedFill = new SeedFillBorder(raster);
        rasterizer = new LineRasterizerGraphics(raster);
        rasterizer.setColor(0x00ff00);
    }

    //*******************************************************************************************************************
    private class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isControlDown()) return;

            if (e.isShiftDown()) {
                //TODO
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                rasterizer.drawLine(x,y,e.getX(),e.getY());
                x = e.getX();
                y = e.getY();
            } else if (SwingUtilities.isMiddleMouseButton(e)) {
                //TODO
            } else if (SwingUtilities.isRightMouseButton(e)) {
                seedFill.setSeed(e.getX(),e.getY());
                seedFill.fill();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    //*******************************************************************************************************************

    private  class MyAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isControlDown()) return;

            if (e.isShiftDown()) {
                //TODO
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                rasterizer.drawLine(x,y,e.getX(),e.getY());
                x = e.getX();
                y = e.getY();
            } else if (SwingUtilities.isMiddleMouseButton(e)) {
                //TODO
            } else if (SwingUtilities.isRightMouseButton(e)) {
                seedFill.setSeed(e.getX(),e.getY());
                seedFill.fill();
            }
        }
    }
    //*******************************************************************************************************************

    @Override
    public void initListeners(Panel panel) {
        //*******************************************************************************************************************
        MyMouseListener myMouseListener = new MyMouseListener();
        panel.addMouseListener(myMouseListener);
        //*******************************************************************************************************************
        MyAdapter myAdapter = new MyAdapter();
        panel.addMouseListener(myAdapter);
        //*******************************************************************************************************************
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    rasterizer.drawLine(x,y,e.getX(),e.getY());
                    x = e.getX();
                    y = e.getY();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    seedFill.setSeed(e.getX(),e.getY());
                    seedFill.fill();
                }
            }
        };
        panel.addMouseListener(mouseAdapter);
        //*******************************************************************************************************************
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    rasterizer.drawLine(x,y,e.getX(),e.getY());
                    x = e.getX();
                    y = e.getY();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    seedFill.setSeed(e.getX(),e.getY());
                    seedFill.fill();
                }
            }
        });
        //*******************************************************************************************************************
    /*panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    rasterizer.drawLine(x,y,e.getX(),e.getY());
                    x = e.getX();
                    y = e.getY();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    seedFill.setSeed(e.getX(),e.getY());
                    seedFill.fill();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        //TODO
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }
        });*/

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
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    //TODO
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