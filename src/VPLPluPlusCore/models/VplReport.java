/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReport {

  private Test vplTest;

  public VplReport(Test vplTest) {
    this.vplTest = vplTest;
  }

  public double getGrade() {

    double totalOfTests = this.vplTest.getTestCasesQuantity();
    double approved = this.vplTest.getTestCasesApprovedQuantity();

    if (approved == 0) {
      return 0;
    }
    if (approved == totalOfTests) {
      return 100;
    }
   
    double percentage = (approved / totalOfTests) * 100;

    return percentage;

  }
  
  public String toJSON(){
    return "";
  }

}
