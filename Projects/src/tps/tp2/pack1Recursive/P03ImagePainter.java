package tps.tp2.pack1Recursive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 */
public class P03ImagePainter {

	private String imagePath = "images/butterfly.jpg";

	private JLabel imageLabel;

	private BufferedImage image = null;

	// variar o threshold e ver o efeito
	private int threshold = 4;

	private int imageHeight;

	private int imageWidth;

	private JLabel colorLabel;

	private Color INITIALCOLOR = Color.green;

	private Color currentColor = INITIALCOLOR;

	private JFrame frame;

	boolean transform = true;

	/**
	 * 
	 */
	static int getRGBBlue(int rgb) {
		return (rgb) & 0xFF;
	}

	/**
	 * 
	 */
	static int getRGBGreen(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	/**
	 * 
	 */
	static int getRGBRed(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	/**
	 * 
	 */
	static int setRGBBlue(int rgb, int blue) {
		return (rgb & 0xFFFFFF00) | (blue & 0xFF);
	}

	/**
	 * 
	 */
	static int setRGBGreen(int rgb, int green) {
		return (rgb & 0xFFFF00FF) | ((green & 0xFF) << 8);
	}

	/**
	 * 
	 */
	static int setRGBRed(int rgb, int red) {
		return ((red & 0xFF) << 16) | rgb & 0xFF00FFFF;
	}

	/**
	 * 
	 */
	static int clearAlphaChannel(int rgb) {
		return rgb & 0x00FFFFFF;
	}

	/**
	 * 
	 */
	private void setNewImage(URL imageURL) {
		try {
			image = ImageIO.read(imageURL);
			setImage(image);
		} catch (IOException e) {
			System.out.println("Error reading file -> " + imageURL);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private void updateImage() {
		setImage(image);
	}

	/**
	 * 
	 */
	private void setImage(BufferedImage image) {
		this.image = image;
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		imageLabel.setIcon(new ImageIcon(image));
	}

	/**
	 * 
	 */
	public void drawSquare(int x, int y, Color paintColor) {
		// desprezar o canal Alpha (transpar�ncia) em todas as cores utilizadas
		int paintColorRGB = clearAlphaChannel(paintColor.getRGB());

//		image.setRGB(x - 1, y - 1, paintColorRGB);
//		image.setRGB(x + 0, y - 1, paintColorRGB);
//		image.setRGB(x + 1, y - 1, paintColorRGB);
//		image.setRGB(x - 1, y + 0, paintColorRGB);
		image.setRGB(x + 0, y + 0, paintColorRGB);
//		image.setRGB(x + 1, y + 0, paintColorRGB);
//		image.setRGB(x - 1, y + 1, paintColorRGB);
//		image.setRGB(x + 0, y + 1, paintColorRGB);
//		image.setRGB(x + 1, y + 1, paintColorRGB);
	}

	/**
	 * 
	 */
	public void transformImage(int x, int y, Color paintColor) {
		// desprezar o canal Alpha (transpar�ncia) em todas as cores utilizadas
		int paintColorRGB = clearAlphaChannel(paintColor.getRGB());

		transformPoint(x, y, getPixelColor(x, y), paintColorRGB);
	}

	/**
	 * Calcula o valor medio RGB de um pixel numa coordenada
	 * @param x Valor das absissas
	 * @param y Valos das ordenadas
	 * @return Valor medio RGB
	 */
	private int getPixelColor(int x, int y) {
		int pixelColor = 0;
		int count = 0;

		// Loop around the centered pixel
		for (int w = x - 1; w <= x + 1; w++) {
			for (int h = y - 1; h <= y + 1; h++) {
				// Don't go out the image frame
				if (w < 0 || w > this.image.getWidth()) {
					continue;
				}
				if (h < 0 || h > this.image.getHeight()) {
					continue;
				}

				count++;
				pixelColor += clearAlphaChannel(this.image.getRGB(w, h));
			}
		}

		return pixelColor / count;
	}

	/**
	 * Transforma uma regiao de uma determinada cor para outra
	 * @param x Valor das absissas
	 * @param y Valos das ordenadas
	 * @param refColorRGB Cor de referencia a ser substituida
	 * @param paintColorRGB Cor de substituicao
	 */
	private void transformPoint(int x, int y, int refColorRGB, int paintColorRGB) {
		int pixel_rgb = clearAlphaChannel(refColorRGB);
		int avg_rgb = clearAlphaChannel(this.getPixelColor(x, y));
		// Threshold comparison
		if (Math.abs(pixel_rgb - avg_rgb) <= this.threshold) {
			return;
		}

		drawSquare(x, y, new Color(paintColorRGB));

		for (int w = x - 1; w <= x + 1; w++) {
			for (int h = y - 1; h <= y + 1; h++) {
				if (w == x && h == y) {
					continue;
				}
				if (w < 0 || w > this.image.getWidth()) {
					continue;
				}
				if (h < 0 || h > this.image.getHeight()) {
					continue;
				}

				this.transformPoint(w, h, avg_rgb, paintColorRGB);
			}
		}
	}

	/**
	 * 
	 */
	public void init() throws IOException {
		final URL imageURL = P03ImagePainter.class.getResource(imagePath);

		frame = new JFrame("...: My painter 0.1 :...");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel pcentral = new JPanel(new CenterLayout());

		imageLabel = new JLabel((ImageIcon) null, JLabel.CENTER);
		setNewImage(imageURL);
		pcentral.add(imageLabel);
		frame.add(pcentral, BorderLayout.CENTER);

		imageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				// System.out.println("label W and H: " + imageLabel.getWidth()
				// + " " + imageLabel.getHeight());
				// System.out.println("Image W and H: " + image.getWidth() + " "
				// + image.getHeight());
				// System.out.println("Image xi and yi: " + imageLabel. + " "
				// + image.getHeight());
				try {
					// System.out.println("Original clicked Point: " + p.x + " "
					// + p.y);
					// adjust clicked point
					int deltaWidth = (imageLabel.getWidth() - imageWidth) / 2;
					int deltaHeight = (imageLabel.getHeight() - imageHeight) / 2;

					int x = p.x - deltaWidth;
					int y = p.y - deltaHeight;
					System.out.println("Image clicked Point: " + x + " " + y);
					if (x < 0 || y < 0) {
						System.out.println("Click point is out of image. Will be ignored...");
						return;
					}

					if (transform)
						transformImage(x, y, colorLabel.getBackground());
					else
						drawSquare(x, y, colorLabel.getBackground());
					updateImage();
				} catch (StackOverflowError error) {
					System.out.println("stack overflow: " + " Area too big for a recursive algorithm...");
				}
			}
		});

		// Buttons panel -----------------------------------------
		JPanel buttonsPanel = new JPanel();
		frame.add(buttonsPanel, BorderLayout.SOUTH);

		// Draw panel -----------------------------------------
		JPanel buttonsImagePanel = new JPanel();
		buttonsImagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
		buttonsPanel.add(buttonsImagePanel);

		// Select image button -----------------------------
		JButton buttonSelectImage = new JButton("Select image");
		buttonSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("src/tps/tp2/pack1Recursive/images");
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.println("Selected file -> " + file);
					URL imageURL;
					try {
						imageURL = new URL("file", null, file.toString());
						setNewImage(imageURL);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		buttonsImagePanel.add(buttonSelectImage);

		// write image button -----------------------------
		JButton buttonwriteImage = new JButton("Save image");
		buttonwriteImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(image, "png", new File("outputImage.png"));
					System.out.println("Image saved...");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonsImagePanel.add(buttonwriteImage);

		// Draw panel -----------------------------------------
		JPanel buttonsDrawPanel = new JPanel();
		buttonsDrawPanel.setBorder(BorderFactory.createTitledBorder("Draw"));
		buttonsPanel.add(buttonsDrawPanel);

		// color label and button -----------------------------
		colorLabel = new JLabel("        ");
		colorLabel.setOpaque(true);
		colorLabel.setBackground(currentColor);
		colorLabel.setPreferredSize(new Dimension(30, buttonSelectImage.getPreferredSize().height));
		colorLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		colorLabel.setToolTipText("Click me to change active color");
		colorLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(frame, "Choose Background Color", currentColor);
				if (newColor != null) {
					currentColor = newColor;
					colorLabel.setBackground(currentColor);
				}
			}
		});
		buttonsDrawPanel.add(colorLabel);

		// Sensitivity label and spinner -----------------------------
		buttonsDrawPanel.add(new JLabel("Sensitivity: "));
		SpinnerNumberModel spNumModel = new SpinnerNumberModel(threshold, 1, 100, 1);
		JSpinner spSensitivity = new JSpinner(spNumModel);
		spNumModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				threshold = spNumModel.getNumber().intValue();
			}
		});
		buttonsDrawPanel.add(spSensitivity);

		// Transform paint button -----------------------------
		JButton buttonTransformOrPaint = new JButton(transform ? "Transform" : "Paint");
		buttonTransformOrPaint.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				transform = !transform;
				((JButton) e.getSource()).setText(transform ? "Transform" : "Paint");
			}
		});
		buttonsDrawPanel.add(buttonTransformOrPaint);

		// put frame visible -----------
		frame.setVisible(true);
	}

	/**
	 * M�todo main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					P03ImagePainter bmapTrans = new P03ImagePainter();
					bmapTrans.init();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
