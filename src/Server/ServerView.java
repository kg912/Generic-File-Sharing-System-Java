package Server;

import javax.swing.*;


import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.ServerSocket;
import java.net.Socket;


public class ServerView {
    private static ServerSocket sSocket;
    private static Socket cSocket = null;
    private static final JPanel panel = new JPanel();
    private static final JLabel lblPortNumber = new JLabel("Port Number:");
    private static final JLabel portLabel = new JLabel("waiting...");
    public static FileTableModel sendModel = null;
    public static String[][] myModel = null;
  /**
 * @throws IOException 
 * @wbp.parser.entryPoint
   */
    
    public static String[][] getData(File dir) {
        String[] filenames = dir.list(); 
        File[] files = new File[filenames.length];
        String[][] data = new String[filenames.length][7];
        for(int i = 0; i < filenames.length; i++) {
            files[i] = new File(dir.getAbsolutePath() + '\\' +  filenames[i]);
        }
        
        for(int j = 0; j < files.length; j++) {
            data[j][0] = filenames[j];
            data[j][1] = new Long(files[j].length()) + "";
            data[j][2] = new Date(files[j].lastModified()) + "";
            data[j][3] = files[j].isDirectory() + "";
            data[j][4] = files[j].isFile() + "";
            data[j][5] = files[j].isHidden() + "";
            data[j][6] = new String(files[j].getAbsolutePath());
        }
        
        return data;
    
    }
    
    
  public static void main(File dir, String pass) throws IOException {
    // Figure out what directory to display;
  

    // Create a TableModel object to represent the contents of the directory
    FileTableModel model = new FileTableModel(dir);
    ServerView.sendModel = model;
    // Create a JTable and tell it to display our model
    final JTable table = new JTable(model);
    
    // Display it all in a scrolling window and make the window appear
    JFrame frame = new JFrame("Generic Filesharing System Server");
    panel.setBackground(Color.DARK_GRAY);
    frame.setBackground(Color.DARK_GRAY);
    frame.setResizable(false);
    panel.setBounds(0, 0, 665, 310);
    
    frame.getContentPane().add(panel);
    frame.getContentPane().setLayout(null);
    panel.setLayout(null);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(10, 11, 645, 220);
    panel.add(scrollPane);
    
    JLabel infoLabel = new JLabel("Server Running");
    infoLabel.setForeground(Color.WHITE);
    infoLabel.setBounds(20, 247, 104, 14);
    panel.add(infoLabel);
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to exit? this will shut down the server", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                  
                } else if (response == JOptionPane.YES_OPTION) {
                  System.exit(0);
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    
                }
                  
        }
    });

    JButton closeServer = new JButton("Exit");
    closeServer.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to exit? this will shut down the server", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                  
                } else if (response == JOptionPane.YES_OPTION) {
                  System.exit(0);
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    
                }
        }
    });
    closeServer.setBounds(538, 243, 104, 23);
    panel.add(closeServer);
    lblPortNumber.setForeground(Color.WHITE);
    lblPortNumber.setBounds(20, 272, 76, 14);
    
    panel.add(lblPortNumber);
    portLabel.setForeground(Color.WHITE);
    portLabel.setBounds(105, 272, 83, 14);
    
    panel.add(portLabel);
    
    frame.setSize(671, 337);
    frame.setVisible(true);
    myModel = getData(dir);
    AuthServer fs = new AuthServer(7777, pass, dir.getAbsolutePath());
    (new Thread(fs)).start();
    portLabel.setText(fs.portNum + "");
    
    //ServerSocket sock = fs.getServerSocket();
    
    table.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent evnt) {
            if (evnt.getClickCount() == 1) {
                String[] fileInfo = new String[7];
                for(int i = 0; i < fileInfo.length; i++) {
                    fileInfo[i] = table.getValueAt(table.getSelectedRow(), i).toString();
                }
                ViewFile.main(fileInfo);
             }
         }
    });
    
  }
}



