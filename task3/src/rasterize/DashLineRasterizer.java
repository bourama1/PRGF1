package rasterize;

public class DashLineRasterizer extends LineRasterizer{

    public DashLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2){
        int x, y;
        int dx, dy;
        int incX, incY;
        int balance;
        int i = 0;

        if (x2 >= x1) {
            dx = x2 - x1;
            incX = 1;
        } else {
            dx = x1 - x2;
            incX = -1;
        }

        if (y2 >= y1) {
            dy = y2 - y1;
            incY = 1;
        } else {
            dy = y1 - y2;
            incY = -1;
        }

        x = x1;
        y = y1;

        if (dx >= dy) {
            dy <<= 1;
            balance = dy - dx;
            dx <<= 1;

            while (x != x2) {
                if (i % 9 > 4) {
                    raster.setPixel(x, y, color.getRGB());
                }
                if (balance >= 0) {
                    y += incY;
                    balance -= dx;
                }
                balance += dy;
                x += incX;
                i++;
            }
            //Pridani posledniho pixelu abych zajistil ze usecka neskonci mezerou
            raster.setPixel(x, y, color.getRGB());
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != y2) {
                if (i % 9 > 4) {
                    raster.setPixel(x, y, color.getRGB());
                }
                if (balance >= 0) {
                    x += incX;
                    balance -= dy;
                }
                balance += dx;
                y += incY;
                i++;
            }
            //Pridani posledniho pixelu abych zajistil ze usecka neskonci mezerou
            raster.setPixel(x, y, color.getRGB());
        }
    }
}
