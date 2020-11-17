package fill;

import rasterize.Raster;

public class SeedFill implements Filler {
    private final Raster raster;
    private int rasterWidth, rasterHeight;
    private int seedX,seedY;
    private PatternFill pattern = new PatternFillCross();

    public SeedFill(Raster raster) {
        this.raster = raster;
        this.rasterWidth = raster.getWidth();
        this.rasterHeight = raster.getWidth();
    }

    public void setSeed(int x, int y){
        seedX = x;
        seedY = y;
    }

    @Override
    public void fill() {
        seedFill(seedX,seedY,0xff0000, raster.getPixel(seedX,seedY));
    }

    //Rekurzivne udelany seedFill ktery vyplnuje podle patternu a hlida si barvu pozadi

    private void seedFill(int seedX, int seedY, int fillColor, int backgroundColor){
        if(raster.getPixel(seedX,seedY) == backgroundColor
                && rasterWidth > seedX && rasterHeight > seedY
                && seedX > 0 && seedY > 0)
        {
            raster.setPixel(seedX, seedY, pattern.paint(seedX,seedY));
            seedFill(seedX+1,seedY,fillColor,backgroundColor);
            seedFill(seedX-1,seedY,fillColor,backgroundColor);
            seedFill(seedX,seedY+1,fillColor,backgroundColor);
            seedFill(seedX,seedY-1,fillColor,backgroundColor);
        }
    }
}

