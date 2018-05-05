package Server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel implements Serializable {
  private static final long serialVersionUID = 1113799434508676095L;
  protected File dir;
  protected String[] filenames;
  

  protected String[] columnNames = new String[] {
    "name", "size", "last modified", "directory?", "File?", "Hidden?", "Filepath"
  };

  protected Class[] columnClasses = new Class[] { 
    String.class, Long.class, Date.class, 
      Boolean.class, Boolean.class, Boolean.class, String.class
  };

  // This table model works for any one given directory
  public FileTableModel(File dir) { 
    this.dir = dir; 
    this.filenames = dir.list();  // Store a list of files in the directory
  }

  // These are easy methods.
  public int getColumnCount() { return 7; }  // A constant for this model
  public int getRowCount() { return filenames.length; }  // # of files in dir

  // Information about each column.
  public String getColumnName(int col) { return columnNames[col]; }
  public Class getColumnClass(int col) { return columnClasses[col]; }

  // The method that must actually return the value of each cell.
  public Object getValueAt(int row, int col) {
    File f = new File(dir, filenames[row]);
    switch(col) {
    case 0: return filenames[row];
    case 1: return new Long(f.length());
    case 2: return new Date(f.lastModified());
    case 3: return f.isDirectory() ? Boolean.TRUE : Boolean.FALSE;
    case 4: return f.isFile() ? Boolean.TRUE : Boolean.FALSE;
    case 5: return f.isHidden() ? Boolean.TRUE : Boolean.FALSE;
    case 6: return new String(f.getAbsolutePath());
    default: return null;
    }
  }
  
  public String getChecksum(String filename) throws IOException {
	  MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try (InputStream is = Files.newInputStream(Paths.get(filename));
	       DigestInputStream dis = new DigestInputStream(is, md)) 
	  {
	    /* Read decorated stream (dis) to EOF as normal... */
	  }
	  return md.digest().toString();
  }
}
