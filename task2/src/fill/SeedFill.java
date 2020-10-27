package fill;

import rasterize.Raster;

public class SeedFill implements Filler {
    private final Raster raster;
    private int seedX,seedY;

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    public void setSeed(int x, int y){
        seedX = x;
        seedY = y;
    }

    @Override
    public void fill() {
        seedFill(seedX,seedY,0xffff00, raster.getPixel(seedX,seedY));
    }

    private void seedFill(int seedX, int seedY, int fillColor, int backgroundColor){
        if(raster.getPixel(seedX,seedY) == backgroundColor) //jsme uvnitr
        {
            raster.setPixel(seedX, seedY, fillColor);
            seedFill(seedX+1,seedY,fillColor,backgroundColor);
            seedFill(seedX-1,seedY,fillColor,backgroundColor);
            seedFill(seedX,seedY+1,fillColor,backgroundColor);
            seedFill(seedX,seedY-1,fillColor,backgroundColor);

        }

    }
}
