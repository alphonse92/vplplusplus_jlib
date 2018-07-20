/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

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
public class ExecutionFile{

  private File mount;
  private File[] files;
  public ExecutionFile(File mount, File[] files){
    this.mount = mount;
    this.files = files;
  }

  public File getMount(){
    return this.mount;
  }
  public File[] getFiles(){
    return this.files;
  }

  public ArrayList<Class> getExecutionFilesClasses() throws MalformedURLException{
    //default
    if(this.files.length == 0){
      return new ArrayList();
    }
    //init elements
    int nClassFiles = this.files.length;
    String[] classNames = new String[nClassFiles];
    ArrayList<Class> out = new ArrayList();
    for(byte i = 0;i < nClassFiles;i++){
      File currentFile = this.files[i];//get the iteration current file isntance
      String filename = currentFile.getName(); //get the name's file
      String classname = String.join(".", filename
              .split(File.pathSeparator))
              .replace("." + Files.EXTENSION_CLASS, ""); //remove extension from the name's file
      classNames[i] = classname;
    }

//    try{
//      File f = new File("k:/");
//      System.out.println("Is file? " + (f.isFile() ? "true" : "false"));
//      URLClassLoader c = URLClassLoader.newInstance(new URL[]{f.toURI().toURL()}, ClassLoader.getSystemClassLoader());
//      c.loadClass("MyClass");
//      System.out.println("My class was loaded");
//    }catch(Exception e){
//      System.out.println("My class wasnt load");
//    }
    URL url = this.mount.toURI().toURL();
    System.out.println("Loading classes from: " + url.toString());
    URLClassLoader cl = URLClassLoader.newInstance(new URL[]{url}, ClassLoader.getSystemClassLoader());

    for(int i = 0;i < nClassFiles;i++){
      String classname = classNames[i];
      try{
        System.out.print("  |..." + classname);
        out.add(cl.loadClass(classname));
        System.out.println(" [Ok]");
      }catch(ClassNotFoundException e){
        System.out.println(" [Error: Class not found ]");
        e.printStackTrace();
      }
    }

    return out;
  }
}
