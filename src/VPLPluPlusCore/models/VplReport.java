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
    
    int totalOfTests = this.vplTest.getTestDescriptorsSize();
    int failures = this.vplTest.getNotApprove();
    
    if(failures == 0) return 100;
    if(failures == totalOfTests ) return 0;
    
    return (failures/totalOfTests) *100 ;
    
  }

}
