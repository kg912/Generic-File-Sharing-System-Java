package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.swing.JPasswordField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartServer {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JLabel lblWelcomeToThe = new JLabel("Select a directory ");
	private final JLabel label = new JLabel("Generic Filesharing System \r\nServer");
	private final JButton selectFiles = new JButton("Browse");
	private final JPasswordField passwordField = new JPasswordField();
	private final JLabel label_1 = new JLabel("Set a password");
	private final JLabel infoDir = new JLabel("Directory:");
	public static JLabel updateDir = new JLabel("Not Selected Yet");
	private final JButton setPass = new JButton("Set");
	private static JButton btnStartServer = new JButton("Start Server");
	private final JLabel lblPasswordShouldBe = new JLabel("Password should be 8 or more characters long");
	private static String password = "";
	private final JButton btnReset = new JButton("Reset");
	private static File directory = null;
	private static String absDir = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartServer window = new StartServer();
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
	public StartServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 513, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 507, 282);
		
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		lblWelcomeToThe.setForeground(Color.WHITE);
		lblWelcomeToThe.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));
		lblWelcomeToThe.setBounds(21, 140, 174, 40);
		
		panel.add(lblWelcomeToThe);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		label.setBounds(67, 11, 373, 51);
		selectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] a = new String[2];
				//frame.setVisible(false);
				File dir;
				dir = Selector.main(a);
				if(dir != null) {
					StartServer.directory = dir;
					StartServer.absDir = dir.getAbsolutePath();
					StartServer.updateDir.setText(StartServer.absDir);
					btnStartServer.setEnabled(true);
					btnReset.setEnabled(true);
				}
			}
		});
		selectFiles.setEnabled(false);
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerView.main(StartServer.directory, StartServer.password);
					frame.setVisible(false);
					} catch(Exception x) {
						
					}
			}
		});
		btnStartServer.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartServer.setEnabled(false);
				selectFiles.setEnabled(false);
				passwordField.setEnabled(true);
				setPass.setEnabled(true);
				passwordField.setText("");
				updateDir.setText("No Directory Selected");
				StartServer.password = "";
				StartServer.directory = null;
				btnReset.setEnabled(false);
			}
		});
		
		btnReset.setEnabled(false);
		panel.add(label);
		setPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = "";
				for(char a: passwordField.getPassword()) {
					if(a != '\u0000') {
						temp += a;
					}
				}
				if(!(temp.isEmpty() || temp.length() < 8) ) {
					try {
						StartServer.password = SHA1.encrypt(temp.trim());
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					passwordField.setEnabled(false);
					selectFiles.setEnabled(true);
					setPass.setEnabled(false);
				}
			}
		});
		setPass.setBounds(362, 83, 65, 23);
		
		panel.add(setPass);
		selectFiles.setBounds(239, 146, 132, 31);
		
		panel.add(selectFiles);
		passwordField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				
			}
		});
		passwordField.setBounds(203, 84, 149, 20);
		
		panel.add(passwordField);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));
		label_1.setBounds(21, 73, 212, 40);
		
		panel.add(label_1);
		infoDir.setForeground(Color.WHITE);
		infoDir.setBounds(92, 201, 107, 20);
		
		panel.add(infoDir);
		updateDir.setForeground(Color.WHITE);
		updateDir.setBounds(151, 204, 250, 14);
		
		panel.add(updateDir);
		btnStartServer.setBounds(345, 238, 125, 23);
		
		panel.add(btnStartServer);
		lblPasswordShouldBe.setForeground(Color.WHITE);
		lblPasswordShouldBe.setBounds(203, 102, 267, 23);
		
		panel.add(lblPasswordShouldBe);
		btnReset.setBounds(209, 238, 112, 23);
		
		panel.add(btnReset);
	}

}
