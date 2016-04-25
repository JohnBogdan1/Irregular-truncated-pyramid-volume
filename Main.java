import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException {
		JFrame frame = new MenuDemo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Volume of an irregular truncated pyramid");
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
