package solids;

import model.Solid;
import model.Vertex;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Axis extends Solid {
    public Axis(){
        super();
        vertices.add(new Vertex(0, 0, 0));
        vertices.add(new Vertex(10, 0, 0));
        vertices.add(new Vertex(0, 10, 0));
        vertices.add(new Vertex(0, 0, 10));

        indicesAxe.add(0); indicesAxe.add(1); color.add(Integer.valueOf(255));
        indicesAxe.add(0); indicesAxe.add(2); color.add(Integer.valueOf(65280));
        indicesAxe.add(0); indicesAxe.add(3); color.add(Integer.valueOf(16711680));
    }

    @Override
    public void render(BufferedImage bufferedImage, boolean clip) {
        for (int i = 0; i < indicesAxe.size(); i += 2){
            renderAxes(vertices.get((Integer) indicesAxe.get(i)).getPosition(),
                    vertices.get((Integer) indicesAxe.get(i + 1)).getPosition(),
                    (Integer) color.get(i / 2), clip, bufferedImage);
        }
    }

    private void renderAxes(Point3D pA, Point3D pB, int color, boolean clip, BufferedImage bufferedImage) {
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
        if (color == 255)
            g.drawString("x", (int)b.get().getX(), (int)b.get().getY());
        if (color == 65280)
            g.drawString("y", (int)b.get().getX(), (int)b.get().getY());
        if (color == 16711680)
            g.drawString("z", (int)b.get().getX(), (int)b.get().getY());
    }


    private Vec3D viewPort(Optional<Vec3D> a, BufferedImage bufferedImage) {
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
                .mul(new Vec3D(((w - 1) / 2), ((h - 1) / 2), 1.0D));
    }
    //Todo

}
