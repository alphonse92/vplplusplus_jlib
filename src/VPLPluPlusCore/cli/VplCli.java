/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.cli;

import VPLPluPlusCore.APP;
import VPLPluPlusCore.models.VplLoaderExecutionsFiles;
import VPLPluPlusCore.models.ExecutionFile;
import VPLPluPlusCore.utils.Files;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Class that handles the CLI
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplCli {

  private final String[] args;
  private final CommandLine cmd;
  private final Options options;

  public VplCli(String[] args) throws ParseException {

    this.args = args;

    this.options = new Options();

    this.options.addOption("h", "help", false, "show help.");
    this.options.addOption("f", "files", true, "specify files to classpath execution");
    this.options.addOption("e", "environment", true, "Specify the environment");

    CommandLineParser parser = new DefaultParser();
    this.cmd = parser.parse(options, this.args, true);
    if (this.cmd.hasOption("h")) {
      this.help();
    }

  }

  public VplLoaderExecutionsFiles getFiles() {
    ExecutionFile[] executionFilesFromCommandLine = this.getExecutionFiles(this.cmd.getOptionValues("f"));
    return new VplLoaderExecutionsFiles(
            executionFilesFromCommandLine,
            this.getPluginsPath(this.cmd.getOptionValues("p")));
  }

  public String getEnvironment() {

    String environment = this.cmd.getOptionValue("e");
    String val = environment == null ? APP.ENV_DEF : environment;
    return val;
  }

  private void help() {
    HelpFormatter formater = new HelpFormatter();
    formater.printHelp("Main", this.options);
    System.exit(0);
  }

  /**
   * This method validate each path in paths String paths. If paths is null or
   * an empty array, the execution files will taken from execution path. Else,
   * iterates over paths array.
   *
   * If files has not been passed, then take the files from the execution PATH
   * Else iterate over paths array
   *
   * For each path will be validate if the path is pointing to a file then
   * create the execution file for that path. Else, if the path is pointing to a
   * folder, then load all files as execution files in that folder.
   *
   *
   * @param paths, array of paths
   * @return File[] Instance Array
   */
  private ExecutionFile[] getExecutionFiles(String[] paths) {

    File executionMountPath = new File(Files.EXECUTION_PATH);

    if (paths == null || paths.length == 0) {
      return new ExecutionFile[]{
        new ExecutionFile(executionMountPath,
        Files.getFiles(executionMountPath, Files.EXTENSION_CLASS)
        )
      };
    }

    ArrayList<ExecutionFile> out = new ArrayList();

    for (String value : paths) {
      File valueFileInstance = new File(value);
      if (valueFileInstance.isFile()) {
        out.add(new ExecutionFile(valueFileInstance.getParentFile(), new File[]{valueFileInstance}));
      } else {
        out.add(new ExecutionFile(valueFileInstance, Files.getFiles(valueFileInstance, Files.EXTENSION_CLASS)));
      }
    }

    return out.toArray(new ExecutionFile[out.size()]);

  }

  private String[] getPluginsPath(String[] values) {
    return values;
  }

  public boolean isVerbose() {
    String verboseValue = this.cmd.getOptionValue("v");
    boolean isVerbose = verboseValue != null && verboseValue.equalsIgnoreCase("true");
    return isVerbose;
  }

}
