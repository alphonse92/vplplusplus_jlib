/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore;

import VPLPluPlusCore.Exceptions.VplException;
import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.exporters.ApiExporter;
import VPLPluPlusCore.exporters.Printer;
import VPLPluPlusCore.factories.parser.VplParserFactory;
import VPLPluPlusCore.factories.runner.VplRunnerFactory;
import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.models.VplLoaderExecutionsFiles;
import VPLPluPlusCore.models.VplReportSuite;
import VPLPluPlusCore.models.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplLoader {

  private static VplLoader instance = null;
  private final String env ;
  private VplLoader(String env) {
    this.env = env;
  }

  /**
   * Singleton method for get VplLoader instance
   *
   * @return VplLoader's Instance
   */
  public static VplLoader getInstance(String env) {
    if (VplLoader.instance == null) {
      VplLoader.instance = new VplLoader(env);
    }

    return VplLoader.instance;
  }

  public VplLoader require(String pathToClass) {
    return this;
  }

  public VplReportSuite run(String[] args, VplLoaderExecutionsFiles files)
          throws VplTestException,
          MalformedURLException,
          ClassNotFoundException,
          ClassNotFoundException,
          URISyntaxException,
          URISyntaxException,
          Exception {

    // 0. Load the classes
    ArrayList<Class> classes = files.loadClasses();
    // 1. get the parser from the vplParserFactory
    // 2. Send the array of classes to parse all of these in a array of VplTests
    ArrayList<Test> arrayOfTests = VplParserFactory.getParser().parse(classes);
    // 3. Send the array of vpl tests to the runner. 
    VplReportSuite report = VplRunnerFactory.getRunner().run(arrayOfTests);
    // 4. load all exporters
    ArrayList<IExporter> exporters = new ArrayList();
    
    exporters.add(new Printer(report).setArgs(args));
    exporters.add(new ApiExporter(report).setArgs(args));
    
    // export all
    this.export(exporters);

    return report;
  }

  /**
   * This method take an array of exporters and run all exporters
   *
   * @param exporters
   */
  private void export(ArrayList<IExporter> exporters) {

    for (IExporter exporter : exporters) {
      try {
        exporter.export();
      } catch (VplException ex) {
        Logger.getLogger(VplLoader.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

}
