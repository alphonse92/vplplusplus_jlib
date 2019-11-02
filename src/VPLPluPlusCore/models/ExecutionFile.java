/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.ClassNotFound;
import VPLPluPlusCore.logger.VplLogger;
import VPLPluPlusCore.utils.Files;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class ExecutionFile {

  private File mount;
  private File[] files;

  public ExecutionFile(File mount, File[] files) {
    this.mount = mount;
    this.files = files;
  }

  public File getMount() {
    return this.mount;
  }

  public File[] getFiles() {
    return this.files;
  }

  /**
   * This method load the classes from execution File.
   *
   * @return @throws MalformedURLException
   */
  public ArrayList<Class> getExecutionFilesClasses() throws MalformedURLException, ClassNotFound {
    //default
    if (this.files == null || this.files.length == 0) {
      return new ArrayList();
    }
    //init elements
    int nClassFiles = this.files.length;
    String[] classNames = new String[nClassFiles];

    // As standar says, the class should be capitalized, and the filename
    // should be called as the class name. So, the class
    // Calculator should exist inside Calculator.java or Calculator.class
    // so, we cut the file name to get the class name
    for (byte i = 0; i < nClassFiles; i++) {
      // Get the file
      File currentFile = this.files[i];
      // Get the file name of currentFile
      String filename = currentFile.getName();
      // Get the classname from path /folder/to/The/Calculator.class 
      // getting the Calculator.class and after cut by dot extension
      String classname = String.join(".", filename
              .split(File.pathSeparator))
              .replace("." + Files.EXTENSION_CLASS, ""); //remove extension from the name's file
      // the result is the class name. In the example before: Calculator.
      // and append to the classNAmes array
      classNames[i] = classname;
    }

    // get the current url where is the execution file
    URL url = this.mount.toURI().toURL();

    VplLogger.getInstance().logLn("Loading classes from: " + url.toString());
    // Get the class loader
    URLClassLoader currentClassLoader = URLClassLoader.newInstance(new URL[]{url}, ClassLoader.getSystemClassLoader());
    // Declare the out
    ArrayList<Class> arrayOfClasses = new ArrayList();
    // start to load each class 
    for (int i = 0; i < nClassFiles; i++) {
      // get the class name
      String classname = classNames[i];
      try {
        VplLogger.getInstance().log("  |..." + classname);
        // try to load the class from the url
        arrayOfClasses.add(currentClassLoader.loadClass(classname));
        // print ok if all works fine
        VplLogger.getInstance().logLn(" [Ok]");
      } catch (ClassNotFoundException e) {
        throw new ClassNotFound(classname);
      }
    }

    // return an array of loaded classes
    return arrayOfClasses;

  }
}
