/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore;

import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.factories.parser.VplParserFactory;
import VPLPluPlusCore.factories.runner.VplRunnerFactory;
import VPLPluPlusCore.models.VplLoaderExecutionsFiles;
import VPLPluPlusCore.models.VplReportSuite;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplLoader {

  private static VplLoader instance = null;

  private VplLoader() {
  }

  /**
   * Singleton method for get VplLoader instance
   *
   * @return VplLoader's Instance
   */
  public static VplLoader getInstance() {
    if (VplLoader.instance == null) {
      VplLoader.instance = new VplLoader();
    }

    return VplLoader.instance;
  }

  public VplLoader require(String pathToClass) {
    return this;
  }

  public VplReportSuite run(VplLoaderExecutionsFiles files) throws VplTestException, MalformedURLException, ClassNotFoundException, ClassNotFoundException, URISyntaxException, URISyntaxException {
    return VplRunnerFactory
            // get te current runner
            .getRunner()
            // Run the runner
            .run(
                    // Get the parser
                    VplParserFactory
                            .getParser()
                            .parse(
                                    files.loadClasses()
                            )
            );
  }

}
