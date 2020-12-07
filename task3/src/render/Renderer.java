package render;

import model.Scene;
import model.Solid;
import model.Vertex;
import rasterize.LineRasterizer;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Renderer {
    LineRasterizer lineRasterizer;
    BufferedImage bufferedImage;
    private Mat4 model;
    private Mat4 view;
    private Mat4 projection;
    private boolean clip;

    public Renderer(LineRasterizer lineRasterizer, BufferedImage bufferedImage){
        this.lineRasterizer = lineRasterizer;
        this.bufferedImage = bufferedImage;
        clip = false;
    }

    public void render(Scene scene){
        //axis
        Point3D a = new Point3D(scene.getSolids().get(0).getVertices().get(0).getPosition());
        Point3D b = new Point3D(scene.getSolids().get(0).getVertices().get(1).getPosition());
        Point3D c = new Point3D(scene.getSolids().get(0).getVertices().get(2).getPosition());
        Point3D d = new Point3D(scene.getSolids().get(0).getVertices().get(3).getPosition());
        renderAxes(a, b, Color.BLUE.getRGB());
        renderAxes(a, c, Color.RED.getRGB());
        renderAxes(a, d, Color.YELLOW.getRGB());

        //tetra

      //todo pro vsechna Solids
    }

    public void render(Solid solid){
        Vertex a = new Vertex(0,0,0);

        Vertex a2 = a.transform(model).transform(view).transform(projection); //n*(3*12+3*16)
        // + : n*3*12 = 36
        // * : n*3*16 = 48

        Mat4 m = model.mul(view).mul(projection); //(A*B*C = A*(B*C) = (A*B)*C
        Mat4 m2 = model.mul(view.mul(projection));
        Mat4 m3 = (model.mul(view)).mul(projection);

        a2 = a.transform(m); //n*(12+16)+2*48 +2*64
        // + : n*12 + 2*48 = 108
        // * : n*16 + 2*64 = 144

        /* Mat4*Mat4
            + : 3*4*4 = 48
            * : 4*4*4 = 64
           Poin3D*Mat4 - transform
           + : 3*4 = 12
           * : 4*4 = 16
        */
        //modelovaci transformace
        //pohledova transformace
        //projekcni (transformace zobrazovaciho objemu)
        //Model - View - Projection
        //orezani - fast clip
        //dehomogenizace
        //projekce
        //viewport transformace
        //vykresleni pomoci linerasterizeru
    }


    private void renderAxes(Point3D pA, Point3D pB, int color) {
        if (Math.min(pA.getW(), pB.getW()) < 0.0D)
            return;
        Optional<Vec3D> a = pA.dehomog();
        Optional<Vec3D> b = pB.dehomog();
        if (this.clip && (Math.min(a.get().getX(), b.get().getX()) < -1.0D || Math.max(a.get().getX(), b.get().getX()) > 1.0D ||
                Math.min(a.get().getY(), b.get().getY()) < -1.0D || Math.max(a.get().getY(), b.get().getY()) > 1.0D ||
                Math.min(a.get().getZ(), b.get().getZ()) < 0.0D || Math.max(a.get().getZ(), b.get().getZ()) > 1.0D))
            return;
        a = Optional.ofNullable(viewPort(a));
        b = Optional.ofNullable(viewPort(b));
        Graphics g = this.bufferedImage.getGraphics();
        g.setColor(new Color(color));
        g.drawLine((int)a.get().getX(), (int)a.get().getY(), (int)b.get().getX(), (int)b.get().getY());
        if (color == 255)
            g.drawString("x", (int)b.get().getX(), (int)b.get().getY());
        if (color == 65280)
            g.drawString("y", (int)b.get().getX(), (int)b.get().getY());
        if (color == 16711680)
            g.drawString("z", (int)b.get().getX(), (int)b.get().getY());
    }


    private Vec3D viewPort(Optional<Vec3D> a) {
        int w = this.bufferedImage.getWidth();
        int h = this.bufferedImage.getHeight();
        int px = 0;
        int py = 0;
        switch (1) {
            case 1:
                px = 0;
                py = 0;
                w = this.bufferedImage.getWidth() / 2;
                h = this.bufferedImage.getHeight() / 2;
                break;
            case 2:
                px = 2;
                py = 0;
                w = this.bufferedImage.getWidth() / 2;
                h = this.bufferedImage.getHeight() / 2;
                break;
            case 3:
                px = 0;
                py = 2;
                w = this.bufferedImage.getWidth() / 2;
                h = this.bufferedImage.getHeight() / 2;
                break;
            case 4:
                px = 2;
                py = 2;
                w = this.bufferedImage.getWidth() / 2;
                h = this.bufferedImage.getHeight() / 2;
                break;
        }
        return a.get().mul(new Vec3D(1.0D, -1.0D, 1.0D)).add(new Vec3D((1 + px), (1 + py), 0.0D))
                .mul(new Vec3D(((w - 1) / 2), ((h - 1) / 2), 1.0D));
    }
}
