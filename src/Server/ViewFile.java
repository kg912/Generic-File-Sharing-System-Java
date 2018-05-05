package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewFile {

	private JFrame frame;
	private final JLabel infoLabel1 = new JLabel("Properties");
	private final JLabel lblName = new JLabel("Name:");
	private final JLabel label = new JLabel("Size:");
	private final JLabel label_1 = new JLabel("Last Modified:");
	private final JLabel label_2 = new JLabel("Directory:");
	private final JLabel label_3 = new JLabel("File:");
	private final JLabel label_4 = new JLabel("Hidden:");
	private final JLabel nameLabel = new JLabel("Loading...");
	private final JLabel sizeLabel = new JLabel("Loading...");
	private final JLabel lastMod = new JLabel("Loading...");
	private final JLabel dirLabel = new JLabel("Loading...");
	private final JLabel isFileLabel = new JLabel("Loading...");
	private final JLabel isHiddenLabel = new JLabel("Loading...");
	private final JLabel label_10 = new JLabel("Full Path:");
	private final JLabel pathLabel = new JLabel("Loading...");
	private final JButton btnOk = new JButton("OK");
	private String[] fileInfo;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFile window = new ViewFile(args);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewFile(String[] info) {
		this.fileInfo = info;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		infoLabel1.setForeground(Color.WHITE);
		infoLabel1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 22));
		infoLabel1.setBounds(148, 0, 166, 54);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(infoLabel1);
		lblName.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(24, 55, 69, 23);
		
		frame.getContentPane().add(lblName);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label.setBounds(24, 94, 69, 23);
		
		frame.getContentPane().add(label);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label_1.setBounds(24, 137, 113, 23);
		
		frame.getContentPane().add(label_1);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label_2.setBounds(24, 178, 96, 23);
		
		frame.getContentPane().add(label_2);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label_3.setBounds(24, 213, 96, 23);
		
		frame.getContentPane().add(label_3);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label_4.setBounds(24, 247, 96, 23);
		
		frame.getContentPane().add(label_4);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameLabel.setBounds(137, 57, 297, 18);
		
		frame.getContentPane().add(nameLabel);
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sizeLabel.setBounds(137, 96, 307, 18);
		
		frame.getContentPane().add(sizeLabel);
		lastMod.setForeground(Color.WHITE);
		lastMod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lastMod.setBounds(137, 139, 281, 18);
		
		frame.getContentPane().add(lastMod);
		dirLabel.setForeground(Color.WHITE);
		dirLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dirLabel.setBounds(137, 180, 307, 18);
		
		frame.getContentPane().add(dirLabel);
		isFileLabel.setForeground(Color.WHITE);
		isFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		isFileLabel.setBounds(137, 215, 307, 18);
		
		frame.getContentPane().add(isFileLabel);
		isHiddenLabel.setForeground(Color.WHITE);
		isHiddenLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		isHiddenLabel.setBounds(137, 249, 307, 18);
		
		frame.getContentPane().add(isHiddenLabel);
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		label_10.setBounds(24, 288, 96, 23);
		
		frame.getContentPane().add(label_10);
		pathLabel.setForeground(Color.WHITE);
		pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pathLabel.setBounds(137, 290, 307, 18);
		
		frame.getContentPane().add(pathLabel);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnOk.setBounds(178, 334, 89, 23);
		
	
		
		frame.getContentPane().add(btnOk);
		frame.setBounds(100, 100, 450, 408);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		nameLabel.setText(fileInfo[0]);
		sizeLabel.setText(fileInfo[1]);
		lastMod.setText(fileInfo[2]);
		dirLabel.setText(fileInfo[3]);
		isFileLabel.setText(fileInfo[4]);
		isHiddenLabel.setText(fileInfo[5]);
		pathLabel.setText(fileInfo[6]);
	}
}
