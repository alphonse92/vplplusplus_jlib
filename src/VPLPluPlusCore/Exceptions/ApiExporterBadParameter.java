/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.Exceptions;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class ApiExporterBadParameter extends VplException {

  public ApiExporterBadParameter(Options options,String param) {
    super("Api exporter bad parameters");
    HelpFormatter formater = new HelpFormatter();
    formater.printHelp("Api exporter help", options);
    System.err.println("Missing parameter " + param);
  }

  public ApiExporterBadParameter(Options options) {
    super("Api exporter bad parameters");
    HelpFormatter formater = new HelpFormatter();
    formater.printHelp("Api exporter help", options);
  }

}
