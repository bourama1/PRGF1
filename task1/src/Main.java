import model.Point;
import model.Polygon;
import rasterize.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * trida pro kresleni na platno: vyuzita tridy RasterBufferedImage
 * 
 * @author PGRF FIM UHK
 * @version 2020
 */

public class Main {

	private JPanel panel;
	private RasterBufferedImage raster;
	private int x, y;
	private int index;
	private FilledLineRasterizer rasterizer;
	private DashLineRasterizer rasterizerDashed;
	private Polygon polygon = new Polygon();
	private PolygonRasterizer polygonRasterizer;

	public Main(int width, int height) {
		JFrame frame = new JFrame();

		frame.setLayout(new BorderLayout());

		frame.setTitle("UHK FIM PGRF : Matej Boura 1. Uloha " + this.getClass().getName());
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		raster = new RasterBufferedImage(width, height);
		rasterizer = new FilledLineRasterizer(raster);
		rasterizerDashed = new DashLineRasterizer(raster);
		polygonRasterizer = new PolygonRasterizer(new FilledLineRasterizer(raster));

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};
		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					Point p = new Point(e.getX(), e.getY());
					polygon.points.add(p);
					draw();
				} else if (SwingUtilities.isRightMouseButton(e) && polygon.points.size() > 0) {
					Point p = new Point(e.getX(), e.getY());
					polygon.points.set(index, p);
					draw();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					Point p = new Point(e.getX(), e.getY());
					index = polygon.closePoint(p);
					draw();
				}
			}
		});

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e) && polygon.points.size() > 0) {
					draw();
					x = e.getX();
					y = e.getY();
					drawDash();
				} else if (SwingUtilities.isRightMouseButton(e) && polygon.points.size() > 0) {
					draw();
					x = e.getX();
					y = e.getY();
					if (polygon.points.size() > 1) {
						redrawDash();
					}
				}
			}
		});

		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (panel.getWidth()<1 || panel.getHeight()<1)
					return;
				if (panel.getWidth()<=raster.getWidth() && panel.getHeight()<=raster.getHeight()) //no resize if new one is smaller
					return;
				RasterBufferedImage newRaster = new RasterBufferedImage(panel.getWidth(), panel.getHeight());

				newRaster.draw(raster);
				raster = newRaster;
				rasterizer = new FilledLineRasterizer(raster);
				rasterizerDashed = new DashLineRasterizer(raster);
			}
		});

		panel.requestFocus();
		panel.requestFocusInWindow();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_C:
						polygon.points.clear();
						break;
				}
				draw();
				panel.repaint();
			}
		});
	}

	public void draw(){
		clear(0x222222);
		polygonRasterizer.rasterize(polygon);
		panel.repaint();
	}

	public void drawDash(){
		int i = polygon.points.size() - 1;
		rasterizerDashed.rasterize(polygon.points.get(0).getX(), polygon.points.get(0).getY(), x, y, Color.RED);
		rasterizerDashed.rasterize(polygon.points.get(i).getX(), polygon.points.get(i).getY(), x, y, Color.RED);
		panel.repaint();
	}

	public void redrawDash(){
		if (index == polygon.points.size() - 1) {
			rasterizerDashed.rasterize(polygon.points.get(index - 1).getX(), polygon.points.get(index - 1).getY(), x, y, Color.RED);
			rasterizerDashed.rasterize(polygon.points.get(0).getX(), polygon.points.get(0).getY(), x, y, Color.RED);
		} else if (index == 0) {
			int i = polygon.points.size() - 1;
			rasterizerDashed.rasterize(polygon.points.get(i).getX(), polygon.points.get(i).getY(), x, y, Color.RED);
			rasterizerDashed.rasterize(polygon.points.get(index + 1).getX(), polygon.points.get(index + 1).getY(), x, y, Color.RED);
		} else {
			rasterizerDashed.rasterize(polygon.points.get(index - 1).getX(), polygon.points.get(index - 1).getY(), x, y, Color.RED);
			rasterizerDashed.rasterize(polygon.points.get(index + 1).getX(), polygon.points.get(index + 1).getY(), x, y, Color.RED);
		}
		panel.repaint();
	}

	public void clear(int color) {
		raster.setClearColor(color);
		raster.clear();
	}

	public void present(Graphics graphics) {
		raster.repaint(graphics);
	}

	public void start() {
		clear(0xaaaaaa);
		raster.getGraphics().drawString("Use mouse buttons and try resize the window", 5, 15);
		panel.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main(800, 600).start());
	}

}
