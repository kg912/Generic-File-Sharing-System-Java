package Client;

import java.io.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class FileClient implements Runnable {

    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;
    private String ipadd;
    private int port;
    private String path;
    public static String[][] ftm;
    public static String filepath = "";
    
    public FileClient(String ip, int port, String path) {
        this.ipadd = ip;
        this.port = port;
        this.path = path;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int len = 0;
        byte[] data;
        String password = "";
           try {
            sock = new Socket(ipadd, port);
            stdin = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream into = new DataInputStream(sock.getInputStream());
            int size = into.readInt();
            data = new byte[size]; 
            do {
                len = into.read(data, data.length - size, size);
                size -= len;
            } while (size > 0);
            password = new String(data);
            //OptionPane.showMessageDialog(null, "Incorrect Password. Please restart the application and try again");
            
            //JOptionPane.showMessageDialog(null, "Authentication Successful!");
            os = new PrintStream(sock.getOutputStream());
            InputStream in = sock.getInputStream();
            AuthClient.readModel(in);
            getResult(this.path);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to Server. Please try again later");
            System.exit(1);
        }
        
       
    }

    public static void main(String ipadd, String myPass, int port) throws IOException {
        
    }

    public static String selectAction() throws IOException {
        System.out.println("1. Send file.");
        System.out.println("2. Recieve file.");
        System.out.print("\nMake selection: ");

        return stdin.readLine();
    }
    
    public static String[][] readModel(InputStream in)  {
        try {
            ObjectInputStream ois = new ObjectInputStream(in);
            //System.out.println("Got the table model!");
            return (String[][]) ois.readObject();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public void getResult(String filename) throws NumberFormatException, IOException {
              os.println("2");
              os.println(filename);
              receiveFile();
              sock.close();
    }
    
  
    
    public void displayView(Socket soc) {
        try {
            InputStream in = soc.getInputStream();
            //ftm = Client.AuthClient.readModel(in);
        } catch(Exception e) {
            
        }
    }

    /*public static void sendFile() {
        try {
            System.err.print("Enter file name: ");
            fileName = stdin.readLine();

            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = sock.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to Server.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
    }*/

    public void receiveFile() {
        try {
            int bytesRead;
            InputStream in = sock.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_server_" + fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close();
            in.close();

            //System.out.println("File "+fileName+" received from Server.");
            JOptionPane.showMessageDialog(null, "File "+fileName+" received from Server.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not receive file. Please check with server admin");
            //Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
