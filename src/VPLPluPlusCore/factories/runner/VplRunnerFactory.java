package VPLPluPlusCore.factories.runner;

import VPLPluPlusCore.interfaces.IVplRunner;
import VPLPluPlusCore.runners.VplJRunner;

/**
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplRunnerFactory{

  public static final byte DEFAULT = 0;
  public static IVplRunner getRunner(){
    return new VplJRunner();
  }
  public static IVplRunner getRunner(byte factory){
    if(factory == DEFAULT){
      return new VplJRunner();
    }
    return new VplJRunner();
  }
}
