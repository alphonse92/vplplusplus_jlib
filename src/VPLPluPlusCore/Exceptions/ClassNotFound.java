/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.Exceptions;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class ClassNotFound extends VplException {

  public ClassNotFound(String classname) {
    super(" [ Error: " + classname + "  was not found ]: "
            + "The classes should be named as the file is called."
            + "For example: the class Calculator should be exist inside the file Calculator.class ");
  }

}
