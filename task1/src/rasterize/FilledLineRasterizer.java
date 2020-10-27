package rasterize;

import java.awt.*;
import model.Point;

public class FilledLineRasterizer extends LineRasterizer {

    public FilledLineRasterizer(Raster raster) {
        super(raster);
    }

    //Pouzit Bresenhamův algoritmus
    //Hlavni vyhodou je pouziti pouze celociselne aritmetiky a operaci nenarocnych pro standardni pocitace. Tudiz je to jeden z nejrychlejsich algoritmu.
    //Nevyhodou je nezvladani antialiasingu a nedokonala presnost. Presnost je ale presto vyssi nez u trivialniho DDA algoritmu.
    //Vykresluje pouze usecky v rozmezi 0º az 45º, takze musime pouzit transformaci usecky zalozenou na rozdilu dx a dy

    @Override
    public void drawLine(Point p1, Point p2){
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        int x, y;
        int dx, dy;
        int incX, incY;
        int balance;

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
                raster.setPixel(x, y, color.getRGB());
                if (balance >= 0) {
                    y += incY;
                    balance -= dx;
                }
                balance += dy;
                x += incX;
            }
            raster.setPixel(x, y, color.getRGB());
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != y2) {
                raster.setPixel(x, y, color.getRGB());
                if (balance >= 0) {
                    x += incX;
                    balance -= dy;
                }
                balance += dx;
                y += incY;
            }
            raster.setPixel(x, y, color.getRGB());
        }
    }
}
