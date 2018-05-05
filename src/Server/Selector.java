package Server;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Selector {
	

	
  public static File main(String s[]) {
	
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    chooser.setDialogTitle("choosertitle");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);

    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      //System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
      //System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
      String[] a = new String[2];
      return chooser.getSelectedFile();
    } else {
      System.out.println("No Selection ");
      return null;
    }
  }
}
