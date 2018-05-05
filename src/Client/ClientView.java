package Client;

import javax.swing.*;

import java.awt.Color;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.ServerSocket;
import java.net.Socket;


public class ClientView {
    private static ServerSocket sSocket;
    private static Socket cSocket = null;
    private static final JPanel panel = new JPanel();
  /**
 * @throws IOException 
 * @wbp.parser.entryPoint
   */
  public static void main(String[][] model, String ip, int port, AuthClient obj) throws IOException {
    // Figure out what directory to display;
  

    // Create a TableModel object to represent the contents of the directory
     String[] column = new String[] {
                "name", "size", "last modified", "directory?", "File?", "Hidden?", "Filepath"
              };
    // Create a JTable and tell it to display our model
    final JTable table = new JTable(model, column);
    
    // Display it all in a scrolling window and make the window appear
    JFrame frame = new JFrame("Generic Filesharing System Server");
    panel.setBackground(Color.DARK_GRAY);
    frame.setBackground(Color.DARK_GRAY);
    frame.setResizable(false);
    panel.setBounds(0, 0, 644, 330);
    
    frame.getContentPane().add(panel);
    frame.getContentPane().setLayout(null);
    panel.setLayout(null);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(10, 11, 624, 220);
    panel.add(scrollPane);
    
    JLabel infoLabel = new JLabel("IP Address of Server");
    infoLabel.setForeground(Color.WHITE);
    infoLabel.setBounds(20, 242, 143, 14);
    panel.add(infoLabel);
    
    JLabel infoLabel2 = new JLabel("Port Number:");
    infoLabel2.setForeground(Color.WHITE);
    infoLabel2.setBounds(20, 284, 143, 14);
    panel.add(infoLabel2);
    
    JLabel ipLabel = new JLabel("waiting...");
    ipLabel.setForeground(Color.WHITE);
    ipLabel.setBounds(221, 242, 91, 14);
    panel.add(ipLabel);
    
    JLabel portLabel = new JLabel("waiting...");
    portLabel.setForeground(Color.WHITE);
    portLabel.setBounds(221, 284, 91, 14);
    panel.add(portLabel);
    
    JButton closeServer = new JButton("Exit");
    closeServer.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    });
    closeServer.setBounds(432, 280, 129, 23);
    panel.add(closeServer);
    
    frame.setSize(650, 357);
    frame.setVisible(true);
    ipLabel.setText(ip);
    portLabel.setText(port + "");
    table.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent evnt) {
            if (evnt.getClickCount() == 1) {
                String[] fileInfo = new String[7];
                for(int i = 0; i < fileInfo.length; i++) {
                    fileInfo[i] = table.getValueAt(table.getSelectedRow(), i).toString();
                }
                ViewClientFile.main(fileInfo);
             }
         }
    });
  }
}


