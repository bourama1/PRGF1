package rasterize;

import java.awt.image.BufferedImage;

public interface Raster {

    void clear();

    void setClearColor(int color);

    int getWidth() ;

    int getHeight();

    int getPixel(int x, int y);

    void setPixel(int x, int y, int color) ;

    BufferedImage getImg();
}
