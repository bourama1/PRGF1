package model;

import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solid {
    protected List<Vertex> vertices;
    protected List<Integer> indices;
    protected List<Integer> color;

    public Solid(){
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        color = new ArrayList<>();
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public List<Integer> getIndices(){
        return indices;
    }

    public List<Integer> getColor(){
        return color;
    }

    public void render(BufferedImage bufferedImage, boolean clip) {
        for (int i = 0; i < indices.size(); i += 2){
            renderLine(vertices.get(indices.get(i)).getPosition(),
                    vertices.get(indices.get(i + 1)).getPosition(),
                    Color.CYAN.getRGB(), clip, bufferedImage);
        }
    }

    protected void renderLine(Point3D pA, Point3D pB, int color, boolean clip, BufferedImage bufferedImage) {
        int size = 3;
        if (Math.min(pA.getW(), pB.getW()) < 0.0D)
            return;
        Optional<Vec3D> a = pA.dehomog();
        Optional<Vec3D> b = pB.dehomog();
        if (clip && (Math.min(a.get().getX(), b.get().getX()) < -1.0D || Math.max(a.get().getX(), b.get().getX()) > 1.0D ||
                Math.min(a.get().getY(), b.get().getY()) < -1.0D || Math.max(a.get().getY(), b.get().getY()) > 1.0D ||
                Math.min(a.get().getZ(), b.get().getZ()) < 0.0D || Math.max(a.get().getZ(), b.get().getZ()) > 1.0D))
            return;
        a = Optional.ofNullable(viewPort(a, bufferedImage));
        b = Optional.ofNullable(viewPort(b, bufferedImage));
        Graphics g = bufferedImage.getGraphics();
        g.setColor(new Color(color));
        g.drawLine((int)a.get().getX(), (int)a.get().getY(), (int)b.get().getX(), (int)b.get().getY());
        g.drawOval((int)a.get().getX() - size, (int)a.get().getY() - size, 2 * size, 2 * size);
        g.drawOval((int)b.get().getX() - size, (int)b.get().getY() - size, 2 * size, 2 * size);
    }

    protected Vec3D viewPort(Optional<Vec3D> a, BufferedImage bufferedImage) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int px = 0;
        int py = 0;
        switch (1) {
            case 1:
                px = 0;
                py = 0;
                w = bufferedImage.getWidth() / 2;
                h = bufferedImage.getHeight() / 2;
                break;
            case 2:
                px = 2;
                py = 0;
                w = bufferedImage.getWidth() / 2;
                h = bufferedImage.getHeight() / 2;
                break;
            case 3:
                px = 0;
                py = 2;
                w = bufferedImage.getWidth() / 2;
                h = bufferedImage.getHeight() / 2;
                break;
            case 4:
                px = 2;
                py = 2;
                w = bufferedImage.getWidth() / 2;
                h = bufferedImage.getHeight() / 2;
                break;
        }
        return a.get().mul(new Vec3D(1.0D, -1.0D, 1.0D)).add(new Vec3D((1 + px), (1 + py), 0.0D))
                .mul(new Vec3D(((w - 1) / 2.0D), ((h - 1) / 2.0D), 1.0D));
    }
}
