package render;

import model.Scene;
import model.Solid;
import model.Vertex;
import rasterize.LineRasterizer;
import rasterize.Raster;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    LineRasterizer lineRasterizer;
    Raster raster;
    BufferedImage bufferedImage;
    private Mat4 model = new Mat4Identity();
    private Mat4 view = new Mat4Identity();
    private Mat4 projection = new Mat4Identity();

    public Renderer(LineRasterizer lineRasterizer, BufferedImage bufferedImage, Raster raster){
        this.lineRasterizer = lineRasterizer;
        this.raster = raster;
        this.bufferedImage = bufferedImage;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public void render(Scene scene){
        for (Solid solid: scene.getSolids()) {
            render(solid);
        }
    }

    public void render(Solid solid){
        int size = solid.getIndices().size();
        boolean axis = false;
        if(size == 6)
            axis = true;

        model = solid.getModel();
        Mat4 m = model.mul(view).mul(projection);
        List<Vertex> transformedVertices = new ArrayList<Vertex>();

        for (Vertex v: solid.getVertices()) {
            transformedVertices.add(v.transform(m));
        }

        for (int i = 0; i < size; i += 2){
            renderLine(transformedVertices.get(solid.getIndices().get(i)).getPosition(),
                    transformedVertices.get(solid.getIndices().get(i + 1)).getPosition(),
                    solid.getColor().get(i / 2 % solid.getColor().size()),
                    bufferedImage, axis, solid.getActive());
        }



        //modelovaci transformace
        //pohledova transformace
        //projekcni (transformace zobrazovaciho objemu)
        //Model - View - Projection
        //orezani - fast clip
        //projekce
    }

    protected void renderLine(Point3D pA, Point3D pB, int color, BufferedImage bufferedImage, boolean axis, boolean active) {
        if (Math.min(pA.getW(), pB.getW()) < 0.0D)
            return;

        //Dehomogenizace
        Vec3D va = new Vec3D();
        if(pA.dehomog().isPresent())
            va = pA.dehomog().get();
        Vec3D vb = new Vec3D();
        if(pB.dehomog().isPresent())
            vb = pB.dehomog().get();

        //Clip
        if ((Math.min(va.getX(), vb.getX()) < -1.0D || Math.max(va.getX(), vb.getX()) > 1.0D ||
             Math.min(va.getY(), vb.getY()) < -1.0D || Math.max(va.getY(), vb.getY()) > 1.0D ||
             Math.min(va.getZ(), vb.getZ()) < 0.0D || Math.max(va.getZ(), vb.getZ()) > 1.0D))
            return;

        //ViewPort
        int x1 = (int) ((va.getX() + 1) * (raster.getWidth() - 1 ) / 2);
        int y1 = (int) ((1 - va.getY()) * (raster.getHeight() - 1 ) / 2);
        int x2 = (int) ((vb.getX() + 1) * (raster.getWidth() - 1 ) / 2);
        int y2 = (int) ((1 - vb.getY()) * (raster.getHeight() - 1 ) / 2);

        if (active)
            color = Color.YELLOW.getRGB();
        Graphics g = bufferedImage.getGraphics();
        g.setColor(new Color(color));
        lineRasterizer.rasterize(x1, y1, x2, y2, new Color(color));
        if (axis){
            if (color == 16711680)
                g.drawString("x", x2, y2);
            if (color == 65280)
                g.drawString("y", x2, y2);
            if (color == 255)
                g.drawString("z", x2, y2);
        }
    }
}
