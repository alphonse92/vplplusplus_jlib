/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.interfaces;

import VPLPluPlusCore.Exceptions.VplException;

/**
 *
 * @author elieceralejandromolinavergel
 */
public interface IExporter {
  public void export() throws VplException;
  public IExporter setArgs(String[] args) throws Exception;
}
