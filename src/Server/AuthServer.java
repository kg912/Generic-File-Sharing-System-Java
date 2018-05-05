package Server;

import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class AuthServer implements Runnable {

    private static ServerSocket sSocket;
    private static Socket cSocket = null;
    public String passHash = "";
    public int portNum = 0;
    public String path = "";
   
    public AuthServer(int port, String pass, String path) {
    	this.portNum = port;
    	this.passHash = pass;
    	this.path = path;
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            sSocket = new ServerSocket(portNum);
           	showMessage("Server Started");
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Port already in use. Close additional windows of the application and try again");
            System.exit(1);
        }

        while (true) {
            try {
                cSocket = sSocket.accept();
                showMessage("Accepted Connection: " + cSocket);
                Thread t = new Thread(new AuthConnection(cSocket, this.passHash, this.path));
                t.start();

            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Error in connecting to the server", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}

	private void showMessage(final String message) {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	try {
	            JOptionPane.showMessageDialog(null, message);
	        	} catch(NullPointerException e) {
	        		
	        	}
	        }
	    });
	}
	
	public ServerSocket getServerSocket() {
		return this.sSocket;
	}
	
	public Socket getClientSocket() {
		return this.cSocket;
	}
}