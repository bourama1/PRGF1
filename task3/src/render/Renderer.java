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
        scene.getSolids().get(0).render(bufferedImage, false);

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



}
