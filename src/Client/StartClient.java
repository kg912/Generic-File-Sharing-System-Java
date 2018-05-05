package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class StartClient {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JTextField ipField = new JTextField();
	private final JLabel lblServerIp = new JLabel("Server IP Address");
	private final JLabel label = new JLabel("Password");
	private final JTextField portNumber = new JTextField();
	private final JButton btnNewButton = new JButton("Login");
	private final JLabel lblGenericFileSharing = new JLabel("Welcome to the Generic File Sharing System");
	private final JLabel lblPleaseLogin = new JLabel("Please Login");
	private final JPasswordField passwordField = new JPasswordField();
	private final JLabel label_1 = new JLabel("Port Number");
	private static String password = "";
	public static Thread myThread = null;
	public static AuthClient client = null;
	public static String ipAdd = "";
	public static int portNum;
	private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	protected static final Exception Exception = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartClient window = new StartClient();
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
	public StartClient() {
		initialize();
	}
	
	

	public static boolean validate(final String ip) {
	    return PATTERN.matcher(ip).matches();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ipField.setBounds(163, 78, 201, 20);
		ipField.setColumns(10);
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 285);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 395, 246);
		
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel.add(ipField);
		lblServerIp.setForeground(Color.WHITE);
		lblServerIp.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		lblServerIp.setBounds(20, 77, 133, 20);
		ipField.setDocument(new JTextFieldLimit(15));
		panel.add(lblServerIp);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		label.setBounds(20, 138, 86, 20);
		
		panel.add(label);
		lblGenericFileSharing.setForeground(Color.WHITE);
		lblGenericFileSharing.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		
		portNumber.setColumns(10);
		portNumber.setBounds(163, 109, 86, 20);
		
		panel.add(portNumber);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ipadd = ipField.getText().trim();
				String port = portNumber.getText().trim();
				String temp = "";
				for(char a: passwordField.getPassword()) {
					if(a != '\u0000') {
						temp += a;
					}
				}
				if(!(temp.isEmpty() || ipadd.isEmpty() || port.isEmpty()) ) {
					try {
						StartClient.password = SHA1.encrypt(temp.trim());
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
					if(validate(ipadd)) {
						StartClient.ipAdd = ipadd;
						StartClient.portNum = Integer.parseInt(port);
						StartClient.client = new AuthClient(ipadd, Integer.parseInt(port), StartClient.password);
						StartClient.myThread = new Thread(StartClient.client);
						StartClient.myThread.start();
						frame.dispose();
						//frame.setVisible(false);
					}
					else {
						throw Exception;
					}
				
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Invalid Input. Please try again.");
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		btnNewButton.setBounds(127, 184, 128, 30);
		portNumber.setDocument(new JTextFieldLimit(4));
		
		panel.add(btnNewButton);
		lblGenericFileSharing.setBounds(10, 0, 398, 40);
		
		panel.add(lblGenericFileSharing);
		lblPleaseLogin.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		lblPleaseLogin.setForeground(Color.WHITE);
		lblPleaseLogin.setBounds(133, 40, 128, 14);
		
		panel.add(lblPleaseLogin);
		passwordField.setBounds(163, 139, 201, 19);
		
		panel.add(passwordField);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		label_1.setBounds(21, 109, 107, 20);
		
		panel.add(label_1);
	}
}
