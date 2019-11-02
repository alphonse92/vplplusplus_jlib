package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.ClassNotFound;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class VplLoaderExecutionsFiles {

  private ExecutionFile[] executionFiles = null;
  private String[] plugins = null;

  public int size() {
    return this.executionFiles.length;
  }

  public VplLoaderExecutionsFiles(ExecutionFile[] executionFiles, String[] plugins) {
    this.executionFiles = executionFiles;
  }

  public ArrayList<Class> loadClasses() throws MalformedURLException, ClassNotFound {
    ArrayList<Class> arrayOfClasses = new ArrayList();
    for (ExecutionFile executionFile : executionFiles) {
      // Load classes from execution files
      arrayOfClasses.addAll(executionFile.getExecutionFilesClasses());
    }
    return arrayOfClasses;
  }

}
