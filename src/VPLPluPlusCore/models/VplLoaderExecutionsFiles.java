package VPLPluPlusCore.models;

import VPLPluPlusCore.utils.Files;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class VplLoaderExecutionsFiles {

  private ExecutionFile[] executionFiles = null;
  private String[] plugins = null;

  public VplLoaderExecutionsFiles(ExecutionFile[] executionFiles, String[] plugins) {
    this.executionFiles = executionFiles;
  }

  public ArrayList<Class> loadClasses() throws MalformedURLException {
    ArrayList<Class> arrayOfClasses = new ArrayList();
    for (ExecutionFile executionFile : executionFiles) {
      // Load classes from execution files
      arrayOfClasses.addAll(executionFile.getExecutionFilesClasses());
    }
    return arrayOfClasses;
  }

}
