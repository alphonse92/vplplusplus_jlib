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

  public ApiExporterBadParameter(Options options, String param, String message) {
    super("Bad value for " + param + " parameter:" + message);
    HelpFormatter formater = new HelpFormatter();
    formater.printHelp("Api exporter help", options);
  }

  public ApiExporterBadParameter(Options options) {
    super("Api exporter bad parameters exception");
    HelpFormatter formater = new HelpFormatter();
    formater.printHelp("Api exporter help", options);
  }

}
