
import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.VplLoader;
import VPLPluPlusCore.cli.VplCli;
import VPLPluPlusCore.models.VplLoaderExecutionsFiles;
import VPLPluPlusCore.utils.Files;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.ParseException;

/**
 *
 * RUNNER CLASS
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 *
 * @description This class is a wrapper for VplLoader. This class has the main
 * method for init the vpl execution.
 *
 * PARAMETERS: 1. Plugin: A string with the plugins location, separated by
 * semicolons It will be load a VPl++ plugin, a vpl plugin is a vpl plugin
 * interface implementation. This interface has some methods for flow control
 * execution. Vpl ++ will launch some events, the plugin class can listen this
 * events subscribing to hooks. (The observer pattern will be implemented);
 *
 * @Example You can run the executor calling the VplPlusPlus compiled jar, for
 * example: java VplPlusPlus.jar
 * --plugin="/home/teacher/plugins/plugin1;/home/teacher/plugins/plugin2"
 *
 * @installation 1. Copy compiled jar to /usr/bin/ or add the route to it to
 * $PATH environment variable. 2. Code happy and Enjoy it !!
 *
 *
 * VPLLOADER CLASS
 *
 * Called for vplexecutor
 *
 * METHODS
 *
 * METHOD NAME : require PARAMETERS : String or null RETURNS : VplLoader
 * instance RESPONSABILITY : Add files to classpath DESCRIPTION : require method
 * has a string parameter with the clases to load to classpath if path is null
 * then add to classpath all java files from loader was called
 *
 * METHOD NAME : run PARAMETERS : none RETURNS : VplReportSuite RESPONSABILITY :
 * Run VPL tests DESCRIPTION : After files are added then run all vpl test
 * (JUnit tests)
 *
 * Code example
 *
 * VplLoader loader = VplLoader.getInstance(); loader
 * .require(path/to/folder/Student1/exercise) : VplLoader
 * .require(path/to/folder/Student2/exercise) : VplLoader .run() :
 * VplReportSuite
 *
 */
public class main {

  public static void main(String[] args) {
    try {

      System.out.println("Running VPL Runner from " + Files.EXECUTION_PATH);
      VplLoaderExecutionsFiles files = new VplCli(args).parse();
      if (files != null) {
        VplLoader
                .getInstance()
                .run(files);
      }
    } catch (VplTestException ex) {
      Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
      Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MalformedURLException ex) {
      Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (URISyntaxException ex) {
      Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
