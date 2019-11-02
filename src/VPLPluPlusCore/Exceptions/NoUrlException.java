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
public class NoUrlException extends VplException {
  
  public NoUrlException(){
    super("Cant export to API. Url is not defined");
  }
  
}
