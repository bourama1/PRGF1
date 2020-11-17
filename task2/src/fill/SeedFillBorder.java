package fill;


import rasterize.Raster;

import java.awt.*;

public class SeedFillBorder implements Filler {
    private final Raster raster;
    private int rasterWidth, rasterHeight;
    private int seedX,seedY;
    private PatternFill pattern = new PatternFillCircle();
    private Color borderColor;

    public SeedFillBorder(Raster raster) {
        this.raster = raster;
        this.rasterWidth = raster.getWidth();
        this.rasterHeight = raster.getWidth();
    }

    public void setSeed(int x, int y){
        seedX = x;
        seedY = y;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public void fill() {
        seedFill(seedX,seedY,Color.RED, borderColor);
    }

    //Rekurzivne udelany seedFill ktery vyplnuje barvou a hlida si barvu hranice plus vlastni barvu, aby nebarvil jiz obarvene

    private void seedFill(int seedX, int seedY, Color fillColor, Color borderColor){
        if(raster.getPixel(seedX,seedY) != fillColor.getRGB() && raster.getPixel(seedX,seedY) != borderColor.getRGB()
                && rasterWidth > seedX && rasterHeight > seedY
                && seedX > 0 && seedY > 0)
        {
            raster.setPixel(seedX, seedY, fillColor.getRGB());
            seedFill(seedX+1,seedY,fillColor,borderColor);
            seedFill(seedX-1,seedY,fillColor,borderColor);
            seedFill(seedX,seedY+1,fillColor,borderColor);
            seedFill(seedX,seedY-1,fillColor,borderColor);
        }
    }
}
