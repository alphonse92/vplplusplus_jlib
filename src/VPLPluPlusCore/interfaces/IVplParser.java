/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.interfaces;

import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.models.VplTest;
import java.util.ArrayList;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public interface IVplParser {

  public ArrayList<VplTest> parse(ArrayList<Class> classes) throws VplTestException;
}
