
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class MenuDemo extends JFrame {
	private static final long serialVersionUID = 2785806306580796922L;

	// Text fields for Number 1, Number 2, and Result
	private JTextField a, b, c, d, A, B, C, D, h, H, diag1, diag2, jtfVolume;

	// Button "Volume",
	private JButton jbtVolume;

	// Menu items "Volume" and "Close"
	private JMenuItem jmiVolume, jmiClose;

	public MenuDemo() throws IOException {
		// Create menu bar
		JMenuBar jmb = new JMenuBar();

		// Set menu bar to the applet
		setJMenuBar(jmb);

		// Add menu "Operation" to menu bar
		JMenu operationMenu = new JMenu("Operation");
		operationMenu.setMnemonic('O');
		jmb.add(operationMenu);

		// Add menu "Exit" to menu bar
		JMenu exitMenu = new JMenu("Exit");
		exitMenu.setMnemonic('E');
		jmb.add(exitMenu);

		// Add menu items with mnemonics to menu "Operation" and "Exit"
		operationMenu.add(jmiVolume = new JMenuItem("Volume", 'V'));

		exitMenu.add(jmiClose = new JMenuItem("Close", 'C'));

		// Set keyboard accelerators
		jmiVolume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

		// Panel p3 to hold text
		JPanel p31 = new JPanel(new FlowLayout());
		p31.add(new JLabel("Laturile a, b, c si d sunt ale bazei de sus. "));
		p31.add(new JLabel("Laturile A, B, C si D sunt ale bazei de jos."));

		// Panel p11 to hold text fields and labels
		JPanel p11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p11.add(new JLabel("Latura a"));
		p11.add(a = new JTextField(5));
		p11.add(new JLabel("Latura b"));
		p11.add(b = new JTextField(5));
		p11.add(new JLabel("Latura c"));
		p11.add(c = new JTextField(5));
		p11.add(new JLabel("Latura d"));
		p11.add(d = new JTextField(5));
		p11.add(new JLabel("Latura A"));
		p11.add(A = new JTextField(5));
		p11.add(new JLabel("Latura B"));
		p11.add(B = new JTextField(5));
		p11.add(new JLabel("Latura C"));
		p11.add(C = new JTextField(5));
		p11.add(new JLabel("Latura D"));
		p11.add(D = new JTextField(5));
		p11.add(new JLabel("Inaltime piramida"));
		p11.add(h = new JTextField(5));
		p11.add(new JLabel("Inaltime trunchi"));
		p11.add(H = new JTextField(5));
		p11.add(new JLabel("Diagonala de jos(baza mare)"));
		p11.add(diag1 = new JTextField(5));
		p11.add(new JLabel("Diagonala de sus(baza mica)"));
		p11.add(diag2 = new JTextField(5));
		p11.add(new JLabel("Volume"));
		p11.add(jtfVolume = new JTextField(15));
		jtfVolume.setEditable(false);

		Image image1 = null, image2 = null;
		try {
			URL url1 = new URL("http://i66.tinypic.com/inbuyb.png");
			URL url2 = new URL("http://i66.tinypic.com/2core3c.png");
			image1 = ImageIO.read(url1);
			image2 = ImageIO.read(url2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		p11.add(new JLabel(new ImageIcon(image1)));
		p11.add(new JLabel(new ImageIcon(image2)));

		// Panel p2 to hold buttons
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(jbtVolume = new JButton("Calculate Volume"));

		// Add panels to the frame
		setLayout(new BorderLayout());
		add(p11, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		add(p31, BorderLayout.NORTH);

		// Register listeners
		jbtVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate('v');
			}
		});

		jmiVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate('v');
			}
		});

		jmiClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuDemo.this.dispose();
			}
		});
	}

	/** Calculate and show the result in jtfVolume */
	private void calculate(char operator) throws NullPointerException {

		// Obtain dimensions
		double latA = (Double.parseDouble(A.getText().trim()));
		double latB = (Double.parseDouble(B.getText().trim()));
		double latC = (Double.parseDouble(C.getText().trim()));
		double latD = (Double.parseDouble(D.getText().trim()));
		double lat_a = (Double.parseDouble(a.getText().trim()));
		double lat_b = (Double.parseDouble(b.getText().trim()));
		double lat_c = (Double.parseDouble(c.getText().trim()));
		double lat_d = (Double.parseDouble(d.getText().trim()));
		double heightTrunchi = (Double.parseDouble(H.getText().trim()));
		double heightPyramid = (Double.parseDouble(h.getText().trim()));
		double diagJos = (Double.parseDouble(diag1.getText().trim()));
		double diagSus = (Double.parseDouble(diag2.getText().trim()));

		double alphaJos, gamaJos, alphaSus, gamaSus;

		alphaSus = (double) Math.acos((lat_a * lat_a + lat_d * lat_d - diagSus * diagSus) / (2 * lat_a * lat_d));
		gamaSus = (double) Math.acos((lat_b * lat_b + lat_c * lat_c - diagSus * diagSus) / (2 * lat_b * lat_c));

		alphaJos = (double) Math.acos((latA * latA + latD * latD - diagJos * diagJos) / (2 * latA * latD));
		gamaJos = (double) Math.acos((latB * latB + latC * latC - diagJos * diagJos) / (2 * latB * latC));

		double semiperimeterJos, semiperimeterSus;

		semiperimeterSus = (double) (lat_a + lat_b + lat_c + lat_d) / 2;
		semiperimeterJos = (double) (latA + latB + latC + latD) / 2;

		double ariaJos, ariaSus;

		ariaSus = (double) Math.sqrt((semiperimeterSus - lat_a) * (semiperimeterSus - lat_b)
				* (semiperimeterSus - lat_c) * (semiperimeterSus - lat_d)
				- lat_a * lat_b * lat_c * lat_d * Math.pow(Math.cos((double) (alphaSus + gamaSus) / 2), 2));

		ariaJos = (double) Math.sqrt((semiperimeterJos - latA) * (semiperimeterJos - latB) * (semiperimeterJos - latC)
				* (semiperimeterJos - latD)
				- latA * latB * latC * latD * Math.pow(Math.cos((double) (alphaJos + gamaJos) / 2), 2));

		double volumeTrunchi = 0, volumePyramid = 0, totalVolume = 0;

		// Perform selected operation
		switch (operator) {
		// Volume
		case 'v':
			volumeTrunchi = (double) ((heightTrunchi * (ariaJos + (double) Math.sqrt(ariaJos * ariaSus) + ariaSus)) * 1
					/ 3);
			volumePyramid = (double) (ariaSus * heightPyramid) * 1 / 3;
			totalVolume = volumeTrunchi + volumePyramid;
			break;
		}

		// Set result in jtfResult
		jtfVolume.setText(String.valueOf(totalVolume));
	}

}