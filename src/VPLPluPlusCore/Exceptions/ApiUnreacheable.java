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
public class ApiUnreacheable extends VplException {
  
  public ApiUnreacheable(String url) {
    super("API is unreacheable "+ url);
  }
  
}
