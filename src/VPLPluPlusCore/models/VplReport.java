/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import java.util.ArrayList;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReport {

  private VplTest vplTest;

  public VplReport(VplTest vplTest) {
    this.vplTest = vplTest;
  }

  public double getGrade() {

    double totalOfTests = this.vplTest.getTestDescriptorsSize();
    double approved = this.vplTest.getApproved();

    System.out.println("Total tests: " + String.valueOf(totalOfTests) + " Approved: " + String.valueOf(approved));
    if (approved == 0) {
      return 100;
    }
    if (approved == totalOfTests) {
      return 0;
    }
   
    double percentage = (approved / totalOfTests) * 100;

    return percentage;

  }

}
