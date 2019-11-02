/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.logger;

import VPLPluPlusCore.APP;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class VplLogger {

  private static VplLogger instance;
  private String environment = APP.ENV_DEF;
  private boolean disableVerbose;

  private VplLogger(String environment) {
    String env = environment == null ? APP.ENV_DEF : environment;
    this.environment = env;
  }

  public static VplLogger getInstance(String environment) {
    if (VplLogger.instance == null) {
      VplLogger.instance = new VplLogger(environment);
    }
    return VplLogger.instance;
  }

  public static VplLogger getInstance() {
    if (VplLogger.instance == null) {
      VplLogger.instance = new VplLogger(APP.ENV_DEF);
    }
    return VplLogger.instance;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public void setDisableVerbose(boolean disable) {
    this.disableVerbose = disable;
  }

  public String getEnviromnent() {
    return this.environment;
  }

  public void logLn(String str) {
    if (!this.isDev()) {
      return;
    }

    System.out.println(str);
  }

  public void log(String str) {
    if (!this.isDev()) {
      return;
    }
    
    System.out.print(str);
  }
  
  private boolean isDev(){
    boolean isdev = this.environment.equalsIgnoreCase("development");
    return isdev;
  }

  public void error(String name, Exception e) {
    Logger.getLogger(name).log(Level.SEVERE, null, e);
  }

  public void error(String name, String message) {
    Logger.getLogger(name).log(Level.SEVERE, null, message);
  }

}
