/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.cli;

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

    private Options options;
    private String[] args;

    public VplCli(String[] args) {
        this.args = args;
        createOptions();
    }

    private void createOptions() {
        this.options = new Options();
        options.addOption("h", "help", false, "show help.");
        options.addOption("p", "plugin", true, "add Vpl Plus Plus Plugin");
        options.addOption("f", "files", true, "specify files to classpath execution");
    }

    public VplLoaderExecutionsFiles parse() throws ParseException {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, this.args);

        if (cmd.hasOption("h")) {
            this.help();
        }

        ExecutionFile[] executionFilesFromCommandLine = this.getExecutionFiles(cmd.getOptionValues("f"));

        return new VplLoaderExecutionsFiles(
                executionFilesFromCommandLine,
                this.getPluginsPath(cmd.getOptionValues("p")));
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
     * create the execution file for that path. Else, if the path is pointing
     * to a folder, then load all files as execution files in that folder.
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

}
